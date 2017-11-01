/**
 * Project : MobileOven
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
  	"BaroBox",
	"RowBox",
	"BaroProps",
	"FormItemPanel",
	"Logger",
	"NLSManager",
	"moment"
]
,
function( BaroBox, RowBox, BaroProps, FormItemPanel, Logger, NLSManager, moment )
{
	/**
	 * 기등록된 폼과 사용자 정의된 입력 폼을 구현한다.
	 */
	var FormBox = RowBox.extend(
	{
		ui:
		{
			// Inner Item 선택 공간
		},
		events:
		{

		},
		regions:
		{
			// Outer Item 배치 공간
		}
		,
		constructor: function(options)
		{
			// RowBox 생성자는 사용하지 않는다.
			RowBox.__super__.constructor.apply( this, arguments );
		}
		,
		/**
		 * 콘텐츠 구조에 맞는 폼을 생성한다.
		 * 
		 * @param options		BaroBox + RowBox 파라메터의 조합에 Form 관련 파라메터 추가
		 * 						{
		 * 							module: 'FormBox',
		 * 							order: [폼위젯id 목록],
		 * 							col_option: {string},
		 * 							col_size: {number||object} 기본값 1. 1 ~ 12 사이에서 지정 가능.
		 * 										BaroBox 파라메터로 초기화하는 경우 {object} 지정.
		 * 										추가되는 위젯 별로 사이즈를 지정한다. "widgetId : col_size" 형식으로 지정한다. 
		 * 							custom_form: 폼을 구성하는 폼위젯의 제어 파라메터 및 디자인 정보를 지정한다. 
		 * 							{
		 * 								name: 이름,
		 * 								mode: "page" or "form"
		 * 								container_id: "",
		 * 								template: "", 사용자 정의 폼인 경우 디자인 html 이 지정된다. 자식 클래스에서 getTemplate() 을 오버라이딩하여 직접 템플릿을 지정할 수도 있다.
		 * 								items:
		 * 								{
		 * 									폼위젯id:{
		 * 										module		{string} 내부 폼 아이템 모듈을 사용하는 경우 모듈 식별자 지정
		 * 										selector	{string} 지정된 template 내의 폼 아이템 선택을 위한 선택자 지정
		 * 										label		{string}
		 * 										required	{boolean}
		 * 										value		{object}
		 * 										flat		{boolean}
		 * 									}, ...
		 * 								},
		 * 								data: { object } preset 데이타. 수정 모드인 경우 _collectInputData() 에서 취합된 데이타 형식으로 지정된다.
		 * 							}
		 * 							,
		 * 							폼위젯id:{}, ...
		 * 						}
		 */
		initialize: function(options)
		{
			// RowBox 생성자는 건너뛴다. column 관련 설정은 필요한 경우 직접 수행한다.
			BaroBox.__super__.initialize.apply( this, arguments );
			
			//
			// 인스턴스 보관용
			//
			this._widgets = new Backbone.Model();
			this._showCompleteCnt = 0;
			
			//
			// RowBox 기능 활성화
			//
			if( this.getParam('col_size') )
			{
				this._colOption = this._detectColOption();
			}
			
			//
			// 템플릿에 바인딩 되는 데이타용
			//
			this.model = new Backbone.Model();
			if( options.custom_form && options.custom_form.data )
			{
				// 사용자 정의 템플릿을 사용하는 경우 해당 템플릿에 초기 값을 전달하는 용도로 사용한다.
				this.model.set( options.custom_form.data );
			}
			this.model.set({ hosts : BaroProps.getHosts() });
			Logger.debug(this.model);
			
			//
			// 폼 아이템 설정용
			//
			if(! FormBox.ItemSettings["hosts"] )
			{
				FormBox.ItemSettings["hosts"] = BaroProps.getHosts();
			}
			if(! FormBox.ItemSettings["tabindex"] )
			{
				FormBox.ItemSettings["tabindex"] = 1;
			}
		}
		,
		render: function()
		{
			// BaroBox.render() 에서는 region 을 생성하는 동작이 구현되어 있다.
			// BaroBox.render() 가 사용되지 않도록 부모의 render 호출
			// FormBox 에서는 _makeUIElement() 동작으로 항목 영역을 초기화한다.
			// 본 동작에서 before:render, render 이벤트가 발생된다.
			UCMS.SPA.Panel.prototype.render.call(this);

			// 콘텐츠 높이 설정
			this._initBoxHeight();
			// Activation 필드 활성화
			this._initActivation();
		}
		,
		/**
		 * 입력 폼을 위한 템플릿을 얻는다.
		 * 자식 클래스에서 오버라이딩하여 원하는 템플릿 리소스를 직접 사용할 수도 있다.
		 */
		getTemplate: function()
		{
			Logger.debug("getTemplate() - custom form template.");
			return this._makeCustomTemplate();
		}
		,
		/**
		 * 사용자 정의 폼의 html 템플릿을 생성한다.
		 * 폼 초기화 파라메터의 custom_form.template 가 반환되거나, item 파라메터를 기반으로 생성된 template 이 반환된다.
		 * 
		 * @return { function }
		 */
		_makeCustomTemplate: function()
		{
			var custom_form = this.getParam("custom_form");
			if(! custom_form )
			{
				// 파라메터가 존재하지 않는 경우 오류가 발생하는 것을 방지하기 위한 empty 파라메터를 추가한다.
				// 화면에는 아무것도 생성되지 않는다.
				this.setParam("custom_form", { items: [] });
				custom_form = this.getParam("custom_form");
			}
			
			if( custom_form.template == undefined )
			{
				// 지정되지 않은 경우 items 의 항목을 기반으로 자동 생성된다.
				// 자동 생성된 골격인 최소한의 폼 UI 구성을 위한 html 로만 생성된다.
				// TODO 임의의 디자인 항목들을 필요로 하는 경우에는 해당 설정이 존재하는 html 을 외부에서 제공한다.
				custom_form.template = this._makeBoxSkeleton();
				Logger.debug("_makeCustomTemplate()");
				Logger.debug(custom_form.template);
			}
			
			return function(data)
			{
				if( NLSManager.isActive() )
				{
					// NLS 리소스 적용
					return NLSManager.template( custom_form.template, data );
				}
				else
				{
					return custom_form.template;
				}
			}
		}
		,
		_makeBoxSkeleton: function()
		{
			var itemIdList = this._getWidgetIdList();
			var items = this.getParam("custom_form").items;
			var $dummyTag = $("<div></div>");

			for(var i in itemIdList)
			{
				var $group;
				var itemId = itemIdList[i];
				if( this._getWidgetParams(itemId) )
				{
					//
					// 위젯
					//
					$group = $("<div>").attr("class", itemId+"_region");
				}
				else
				{
					$group = $("<div class=form-group></div>");
					//
					// 폼 콘트롤
					//
					var $item;
					switch( items[itemId].module )
					{
					case "text":
						$item = $('<input type="text" class="form-control">');
						break;
						
					case "textarea":
						$item = $('<textarea rows="3" class="form-control"></textarea>');
						break;
					
					case "date":
					case "datetime":
						$item = $('<input type="text" class="form-control form_type_date" placeholder="">');
								//.html('<input type="text" class="form-control" placeholder=""><span class="input-group-btn"><button class="btn btn-default btn_go_month" type="button"><i class="fa fa-fw fa-calendar"></i><span class="sr-only">월선택</span></button></span>');
						break;
						
					case "checkbox":
						switch( items[itemId].labelPos )
						{
						case 'backward':
							{
							//$item = $('<div class="checkbox"><label><input type="checkbox" name="'+itemId+'" class="'+itemId+'" value="">'+items[itemId].label+'</label></div>');
								var $label = $('<label></label>')
										.append('<input type="checkbox" name="'+itemId+'" class="'+itemId+'">')
										.append(items[itemId].label);
								$item = $('<div class="checkbox"></div>').append( $label );
								items[itemId].label = null;
							}
							break;
							
						case 'forward':
						default:
							$item = $('<input type="checkbox" name="'+itemId+'">');
							break;
						}
						break;
						
					case "radiobox":
						$item = $('<div class="radio">');
						break;
						
					case "combobox":
						$item = $('<div class="select_design">').html('<span class="select_label">선택해 주세요</span><select class="form-control"></select>');
						break;
						
					case "price":
						$item = $('<div class="input-group">')
								.html('<span class="input-group-addon">₩</span><input type="text" class="form-control text-right">');
						break;
						
					case "password":
						$item = $('<input type="password" class="form-control">');
						break;
						
					default:
						UCMS.warn("_makeBoxSkeleton() - Not supported form control module : "+items[itemId].module);
						$item = $('<input type="text" class="form-control">');
						break;
					}
					
					//
					if( typeof items[itemId].label == 'string' )
					{
						var $label = $('<label></label>').text(items[itemId].label);
						if( items[itemId].required )
						{
							$label.addClass('required');
						}
						$group.append( $label );
					}
					$group.append( $item.addClass(itemId) );
					
				} // endif
				
				$dummyTag.append( $group );
			}
			
			return $dummyTag.html();
		}
		,
		/**
		 * 위젯 파라메터를 기반으로 하는 ui 항목을 구성한다.
		 * @param {object} skeleton - 폼 초기화 파라메터의 custom_form 필드로 제공된 값 
		 */
		_makeUIElement: function(custom_form)
		{
			Logger.debug("FormBox._makeUIElement()");
			Logger.debug(custom_form);

			var itemIdList = this._getWidgetIdList();
			
			// 폼 아이템 구성
			for(var i in itemIdList)
			{
				var itemId = itemIdList[i];
				var item = custom_form.items[itemId]
				if( item )
				{
					//
					// Form Item
					//
					
					if( item.module )
					{
						//
						// Inner Item
						//
						
						if( item.selector )
						{
							//
							// 폼 콘트롤 모드
							//
							
							// 아이템 항목 선택을 위한 항목 추가
							this.ui[ itemId ] = item.selector;
							continue;
						}
						else
						{
							//
							// 폼 위젯 모드
							//
							
							// regions 에 배치된다.
							// 아래 region 설정 코드로 계속...
						}
					}

					
					//
					// Outer Item
					//
					{
						// 폼 위젯이 담기는 region 을 추가.
						// "itemid_region" 가 부여된 영역이 생성된 위젯이 배치된다.
						this.regions[ itemId ] = "."+itemId+"_region";
					}
				}
				else
				{
					//
					// Designed Item
					//
					this.regions[ itemId ] = "."+itemId+"_region";
				}
			}
		}
		,
		/**
		 * UI 원소를 구성한 후 html template 가 el 에 추가되고, this.bindUIElements() 가 호출된다.
		 * 그 후 onRender() 가 호출된다.
		 * 
		 * @see ItemView.render()
		 */
		onBeforeRender: function()
		{
			Logger.debug("FormBox.onBeforeRender()");
			var custom_form = this.getParam("custom_form");
			
			if( custom_form != undefined )
			{
				this._makeUIElement( custom_form );
			}
			else
			{
				Logger.error("FormBox.onBeforeRender() - Invalid form parameters[custom_form].");
			}
		}
		,
		/**
		 * bindUIElements() 호출 후 발생되는 이벤트 핸들러.
		 */
		onRender: function()
		{
			Logger.debug("FormBox.onRender()");
			
			// 폼 위젯이 배치될 Region 을 생성한다.
			// 각 폼 위젯 region 은 위젯의 id 로 식별된다.
			this.addRegions(this.regions);
		}
		,
		onBeforeShow: function()
		{
			this._showCompleteCnt = 0;
			var custom_form = this.getParam("custom_form");
			var itemIdList = this._getWidgetIdList();
			for(var i in itemIdList)
			{
				var itemId = itemIdList[i];
				// 디자인 위젯의 파라메터.
				var widgetParams = this._getWidgetParams(itemId) || {};
				// 폼 일반의 파라메터. 디자인 위젯인 경우 undefined
				var formItemParams = _.extend
				(
					{"module": widgetParams.module}, 
					widgetParams.options, 
					custom_form.items[itemId]
				);
				this._initItemElement
				(
					itemId, 
					formItemParams
				);
				custom_form.items[itemId] = formItemParams;
			}
		}
		,
		/**
		 * 수정 모드인지 확인한다.
		 * 초기화 파라메터로 data 필드가 전달된 경우, 수정 모드로 동작한다.
		 */
		_isModifyMode: function()
		{
			return (this.getParam("custom_form").data != undefined);
		}
		,
		/**
		 * 항목을 선택하는 더 좋은 방법이 필요하다.
		 * 
		 */
		onClickElement: function(itemId)
		{
			Logger.info("Click : "+itemId);
		}
		,
		/**
		 * 아이템 항목 별 필요한 초기화를 진행한다. 
		 * 
		 * @param params			사용자 정의 폼 항목의 설정 파라메터
		 * 							{
										module: 폼 위젯 또는 내장 콘트롤 식별자,
										// 정보 이름
										label: "제목",
										// 항목 $ 셀렉터
										selector: "#field_title",
										// 필수 유무
										required: true,
										// form 데이타 생성시 종속 관계 유지 플레그.
										// true 면 종속관계 없이 형제 레벨로 폼 데이타가 추가된다.
										flat: true/false,
										value: 초기값,
		 * 							}
		 */
		_initItemElement: function(widgetId, params)
		{
			Logger.debug("_initItemElement()");
			{
				// 초기화 값 구하기
				// 모델에 설정된 값이 먼저 사용된다. 없을 경우 폼 파라메터에 있는 기본값이 사용된다.
				var value = this.model.get( widgetId );
				if( value == undefined )
				{
					// 초기화 값이 없는 경우, 파라메터 기본값을 적용한다.
					value = params.value;
				}
				
				// 아이템 초기화 값
				params.value = value;
			}
			Logger.debug(params);

			var self = this;
			var formItemCount = self._getWidgetIdList().length;
			// 폼 아이템인가? 확인한다.
			var isFormItem = function(itemId)
			{
				var custom_form = self.getParam("custom_form");
				return custom_form.items[itemId] != undefined;
			}
			var checkComplete = function()
			{
				Logger.info("_initItemElement() - Ready : "+widgetId+", Item Count : "+self.getShowCompleteCount());
				if( self.getShowCompleteCount() == formItemCount )
				{
					// 모든 항목이 활성화되면 onShowComplete() 가 호출된다.
					Logger.debug("_initItemElement() - Completed : "+widgetId);
					self._fireShowComplete();
				}
			}
			
			if(! params.module)
			{
				Logger.warn("_initItemElement() - ["+widgetId+"] Find not found a item parameter field : module");
				
				//
				this._showCompleteCnt++;
				checkComplete();
				return;
			}
			
			//
			params.itemId = widgetId;
			
			//
			var itemParams = params;
			var tabIndex = FormBox.ItemSettings["tabindex"]++;
			var setItemModule = function( ItemModule, params )
			{
				// TODO 위젯에 tabindex 를 할당한다.
				// 할당 받은 tabindex 를 위젯 태그에 적용하는 것은 위젯 개발자의 역할이다.
				params.tabindex = tabIndex;
				var _INSTANCE_ = new ItemModule( params );

				//
				_INSTANCE_.showPanel( self._isTabMode() == false || isFormItem(widgetId) == false );
				self._setWidgetInstance( widgetId, _INSTANCE_ );
				//
				_INSTANCE_.getWidget$Element()
				.click(function()
				{
					self.onClickElement.call(self, widgetId)
				});
				//
				_INSTANCE_.on(FormBox.EVENT.CHANGE, function(params)
				{
					// 위젯에서 수신한 데이타를 위젯 식별자와 함께 전파한다.
					self.trigger( FormBox.EVENT.CHANGE, {id: widgetId, data: params} );
				});
				//self.getRegion( widgetId ).show( _INSTANCE_ );
				FormBox.OuterItemMethod.set.call(self.getRegion( widgetId ), _INSTANCE_);

				//
				// 사용 준비 완료
				self._showCompleteCnt++;
				checkComplete();
			}
			var elem = FormBox.InnerItemMethod[params.module];
			if( elem )
			{
				//
				// Inner Item
				//

				if( elem.MODULE == null )
				{
					//
					// 폼 콘트롤 모드
					//
					
					var $item = this.ui[widgetId];
					if( $item && $item.length > 0 )
					{
						var $inner = elem.set.call( $item, params )
						.focus(function(e)
						{
							Logger.debug("focus : "+widgetId);
							$(this).parent().addClass("focus");
						})
						.blur(function(e)
						{
							Logger.debug("blur : "+widgetId);
							$(this).parent().removeClass("focus");
						})
						.click(function(e)
						{
							//self.onClickElement.call(self, widgetId)
							elem.click.call( $(this), e );
						})
						.change(function(e) 
						{
							var params = { id: widgetId, value: null };
							if( elem.change )
							{
								// 폼 아이템의 최상위 노드를 전달해야 한다.
								// combobox 의 경우 화면에 노출되는 선택된 정보는 <select> 의 형제 노드이다.
								params.value = elem.change.call( $item, e, itemParams );
							}
							if( UCMS.isIE() == true )
							{
								var num = $(this).val().replace(/,/gi,'');
								if( itemParams.module == "number" )
								{
									num = UCMS.isInteger(num) 
										? UCMS.numberWithCommas(Number(num))
										: UCMS.parseNumber(num);	
								}
								else if( itemParams.module == "price" )
								{
									num = UCMS.isInteger(num) 
										? UCMS.numberWithCommas(Number(num))
										: 0;
								}
								$(this).val(num);
							}
							
							// 내용이 변경된 아이템 정보 전달
							self.trigger( FormBox.EVENT.CHANGE, params );
						})
						.attr('tabindex', tabIndex);
						
						if( itemParams.module == "date" )
						{
							$inner.on("dp.change", function(e)
							{
								self.trigger( FormBox.EVENT.CHANGE, 
								{
									id: widgetId, 
									value: elem.change.call( this, e, itemParams )
								} );
							});
						}
						
						if( params.focus == false )
						{
							// 포커스를 받지 않도록 설정
							$inner.attr('tabindex', '-1');
						}
					}
					else
					{
						Logger.warn("_initItemElement() - ["+widgetId+"] Find not found a ui item.");
					}
					
					//
					// 사용 준비 완료
					this._showCompleteCnt++;
					checkComplete();
				}
				else 
				{
					//
					// 폼 위젯 모드
					//

					if( typeof elem.MODULE == "string" )
					{
						// 모듈 로딩
						UCMS.loadModuleByPath([ elem.MODULE ], function(ItemModule)
						{
							if( typeof ItemModule != 'function' && typeof ItemModule['widget'] == 'function' )
							{
								// Manifest 기반 로딩 방식인 경우
								// 리소스 복사
								_.extend( params, { resource: (ItemModule.resource || {}) } );
								// 폼 패널에서는 무조건 widget 이 반환되도록 한다.
								ItemModule = ItemModule['widget'];
							}
							
							// TODO 로딩된 모듈로 대체된다.
							elem.MODULE = ItemModule;
							setItemModule( ItemModule, params );
						});
					}
					else
					{
						// 이미 로딩된 모듈인 경우.
						setItemModule( elem.MODULE, params );
					}
				}
			}
			else
			{
				//
				// Outer Item
				//

				Logger.debug("_initItemElement() - Outer Item Module : "+params.module);
				UCMS.loadModuleByPath([ params.module ], function(ItemModule)
				{
					if( typeof ItemModule != 'function' && typeof ItemModule['widget'] == 'function' )
					{
						// Manifest 기반 로딩 방식인 경우
						// 리소스 복사
						_.extend( params, { resource: (ItemModule.resource || {}) } );
						// 폼 패널에서는 무조건 widget 이 반환되도록 한다.
						ItemModule = ItemModule['widget'];
					}
					
					setItemModule( ItemModule, params );
				});
			}
		}
		,
		/**
		 * 입력된 사용자 데이타를 검증 후 취합한다.
		 * 입력 데이타 형식이 입력 조건에 어긋다는 경우 취합은 중단되고 null 을 반환한다.
		 * 
		 * @param {Boolean} bNoti - 필수항목 누락시 확인창 출력 사용 유무를 지정한다.
		 * @return { Object } 입력 형식 검증이 완료되고 취합된 데이타 객체. 실패한 경우 null 반환됨
		 */
		_collectInputData: function(bNoti)
		{
			var self = this;
			var data = {};
			var custom_form = this.getParam("custom_form");
			var itemIdList = this._getWidgetIdList();
			var items = custom_form.items;
			// 제공된 json 의 key 에 prefix 를 추가한다.
			var appendPrefix = function(prefix, json)
			{
				var data = {};
				var keys = _.keys(json);
				for(var i in keys)
				{
					data[prefix+keys[i]] = json[keys[i]];
				}
				return data;
			};
			
			for(var i in itemIdList)
			{
				var itemId = itemIdList[i];
				var formItemParams = items[itemId];
				if(! formItemParams)
				{
					Logger.warn("_collectInputData() - unknown item : "+itemId);
					continue;
				}
				var _INSTANCE_ = this._getWidgetInstance( itemId );
				var elem = FormBox.InnerItemMethod[formItemParams.module];
				if( elem )
				{
					//
					// Inner Item
					//
					
					var $item;
					
					if( elem.MODULE == null )
					{
						//
						// 폼 콘트롤
						//
						
						if(! this.ui[ itemId ] )
						{
							Logger.warn("_collectInputData() - Invalid Form Item : "+itemId);
							continue;
						}
						
						$item = this.ui[ itemId ];
						
						if( $item.length > 0 )
						{
							// 아이템 노드에서 설정된 값을 얻는다.
							data[ itemId ] = elem.get.call( $item, formItemParams );
						}
						else
						{
							// 폼 파라메터로 제공된 초기값이 그대로 반환된다.
							data[ itemId ] = formItemParams.value;
						}
					}
					else if(! _INSTANCE_ )
					{
						Logger.warn("_collectInputData() - Not ready inner widget item : "+itemId);
						data[itemId] = null;
						continue;
					}
					else if( _INSTANCE_ instanceof FormItemPanel || _INSTANCE_ instanceof FormBox )
					{
						//
						// 폼 위젯
						//
						
						$item = _INSTANCE_.getWidget$Element();
						
						// 아이템 컴퍼넌트에서 설정된 값을 얻는다.
						var itemData = _INSTANCE_.getItemData();
						if( formItemParams.flat == true && typeof itemData == 'object' )
						{
							_.extend(data, appendPrefix(itemId+".", itemData));
						}
						
						//
						data[ itemId ] = itemData;
					}
					else
					{
						Logger.warn("_collectInputData() - Invalid Form Item : "+itemId);
						continue;
					}
				}
				else if(! _INSTANCE_ )
				{
					Logger.warn("_collectInputData() - Not ready Outer widget item : "+itemId);
					data[itemId] = null;
					continue;
				}
				else
				{
					//
					// Outer Item
					//
					
					if( _INSTANCE_ instanceof FormItemPanel || _INSTANCE_ instanceof FormBox )
					{
						$item = _INSTANCE_.getWidget$Element();
						
						Logger.log("_collectInputData() - Outer Item ID : "+itemId);
						var itemData = _INSTANCE_.getItemData();
						if( formItemParams.flat == true && typeof itemData == 'object' )
						{
							_.extend(data, appendPrefix(itemId+".", itemData));
						}
						
						//
						data[ itemId ] = itemData;
					}
					else
					{
						// 디자인 항목
						// 데이타 취합의 대상이 아님.
						Logger.debug("_collectInputData() - Designed Item ID : "+itemId);
					}
				}
				
				// 필수항목 체크
				if( (formItemParams.required || false) && ( data[itemId] == undefined || data[itemId] == null || data[itemId].length == 0 ) )
				{
					if( bNoti == true )
					{
						UCMS.alert("'"+formItemParams.label+"'은 필수 항목입니다.").then(function()
						{
							if( elem )
							{
								elem.click.call( $item, formItemParams );
							}
							else
							{
								FormBox.OuterItemMethod.click.call( _INSTANCE_.getWidget$Element(), formItemParams );
							}
						});
					}
					
					return null;
				}
			}
			
			Logger.debug("_collectInputData()");
			Logger.debug( data );
			
			return data;
		}
		,
		/**
		 * 폼에 포함된 위젯의 데이타를 얻는다.
		 * 
		 * @param {Boolean} bNoti - 필수항목 누락시 확인창 출력 사용 유무를 지정한다.
		 * @return { Object } 입력 형식 검증이 완료되고 취합된 데이타 객체. 실패한 경우 null 반환됨
		 */
		getItemData: function(bNoti)
		{
			return this._collectInputData(bNoti);
		}
		,
		/**
		 * 폼 아이템 원소 객체를 얻는다.
		 * @param {string} eleId - 폼 원소 식별자
		 * @return {BaroPanelBase|$} 위젯 인스턴스 또는 아이템의 $ 을 반환한다. 
		 * 							존재하지 않는 경우 null 을 반환한다.
		 */
		getItemElement: function(eleId)
		{
			// 폼 콘트롤인 경우
			var item = this.ui[eleId] || null;
			if(! item )
			{
				// 폼 위젯인 경우
				item = this._getWidgetInstance( eleId );
			}
			
			return item;
		}
		,
		/**
		 * 폼 데이타를 설정한다.
		 * @param {object} data - 설정 데이타
		 * @param {boolean} silent - true 이면 변경 이벤트가 발생되지 않는다.
		 * 							폼 콘트롤 아이템은 본 메소드에 의해서 값이 변경될 때에는 "form:change" 이벤트가 발생되지 않는다.
		 * 							폼 위젯 아이템에 대해서 본 파라메터가 적용되며, 해당 위젯 개발시 silent 옵션의 상태에 기반한 이벤트 전파 기능을 구현해야 한다.
		 */
		setItemData: function(data, silent)
		{
			if(! data)
			{
				Logger.warn("setItemData() - Invalid parameter.");
				return;
			}
			var self = this;
			var custom_form = this.getParam("custom_form") || {};
			var itemList = custom_form.items || [];
			var setFormWidgetValue = function(itemId, value)
			{
				var formItem = self._getWidgetInstance( itemId );
				if( formItem )
				{
					if( formItem instanceof FormItemPanel || formItem instanceof FormBox )
					{
						Logger.debug("setItemData() - Form Widget Item ID : "+itemId);
						formItem.setItemData( value, silent );
					}
					else
					{
						// 디자인 항목이 필드로 지정된 경우
						// 파라메터가 잘못 사용되었음
						// 데이타 취합의 대상이 아님
						Logger.warn("setItemData() - Designed Item ID : "+itemId);
					}
				}
				else
				{
					// 발생할 수 없는 상황.
					// 폼 초기화가 잘못 진행된 경우.
				}
			};
			
			for(var itemId in itemList)
			{
				var formItemParams = itemList[itemId];
				var elem = FormBox.InnerItemMethod[formItemParams.module];
				if( elem )
				{
					//
					// Inner Item
					//
					if( elem.MODULE == null )
					{
						//
						// Form Control
						//
						
						var $item = this.ui[ itemId ];
						if(! $item )
						{
							// Order 정보가 없는 경우
							Logger.warn("setItemData() - Missing Order information : "+itemId);
						}
						else if( $item.length > 0 )
						{
							Logger.debug("setItemData() - Form Control ID : "+itemId);
							
							// 아이템 노드에 지정된 값을 설정한다.
							formItemParams.value = data[ itemId ];
							elem.set.call( $item, formItemParams );
						}
						else
						{
							// 템플릿이 잘못 설정된 경우
							Logger.warn("setItemData() - Invalid Form Control ID : "+itemId);
						}
					}
					else
					{
						//
						// Inner Form Widget
						//
						setFormWidgetValue(itemId, data[ itemId ]);
					}
				}
				else
				{
					//
					// Outer Form Widget
					//
					setFormWidgetValue(itemId, data[ itemId ]);
				}
			}
		}
		,
		/**
		 * 폼 아이템의 옵션을 재설정한다.
		 * 아이템 설정을 동적으로 변경할 수 있다.
		 */
		setItemParams: function(itemId, params)
		{
			var itemEle = this.getItemElement(itemId);
			if(! itemEle )
			{
				return false;
			}
			var itemList = this.getParam("custom_form").items || [];
			var formItemParams = itemList[itemId];
			
			var elem = FormBox.InnerItemMethod[formItemParams.module];
			if( elem )
			{
				//
				// Inner Item
				//
				if( elem.MODULE == null )
				{
					//
					// 폼 콘트롤 모드
					//
					elem.set.call( itemEle, params );
					itemList[itemId] = params;
					return true;
				}
				else
				{
					//
					// 폼 위젯 모드 지원안함
					//
				}
			}
			else
			{
				//
				// Outer Item 지원안함
				//
			}
			
			return false;
		}
		,
		/**
		 * onBeforeShow() 에서 리셋되고, Ready 된 아이템 개수가 카운트된다.
		 * @return {number} _getWidgetIdList() 에 의해 반환되는 Id 개수와 동일한다.
		 */
		getShowCompleteCount: function()
		{
			return this._showCompleteCnt;
		}
		,
		/**
		 * 모든 폼 아이템 데이타를 클리어한다.
		 */
		clear: function()
		{
			var resetData = {};
			var custom_form = this.getParam("custom_form") || {};
			var itemList = custom_form.items || [];
			for(var itemId in itemList)
			{
				resetData[itemId] = null;
			}
			
			this.setItemData(resetData, true);
		}
		,
		disabled: function(bDisabled)
		{
			FormBox.__super__.disabled.call( this, bDisabled );
		
			var itemList = this.getParam("custom_form").items || [];
			for(var itemId in itemList)
			{
				var formItemParams = itemList[itemId];
				var elem = FormBox.InnerItemMethod[formItemParams.module];
				if( elem )
				{
					//
					// Inner Item
					//
					if( elem.MODULE == null )
					{
						//
						// 폼 콘트롤 모드
						//
						if( bDisabled == true )
						{
							formItemParams = UCMS.copyJSON(formItemParams);
							formItemParams.disable = true;
							// 파라메터를 복사할 때 null 이 {} 로 바뀌는 상황이 발생된다.
							// 이전 값을 다시 설정한다.
							formItemParams.value = itemList[itemId].value;
						}
						else
						{
							// 활성화는 본래 아이템 파라메터가 갖고 있는 설정에 따라 적용된다.
						}
						elem.set.call( this.getItemElement(itemId), formItemParams );
					}
				}
			} // end for
		}
	}
	,
	{
		EVENT:
		{
			// 폼 아이템 내용이 변경된 경우 발생
			CHANGE: "form:change"		// { id: itemId, value: ## }
		}
		,
		/**
		 * 폼 아이템 구동을 위해 필요한 설정들을 담는다.
		 */
		ItemSettings :
		{
			// TODO 탭 인덱스 시작값 설정. 모듈이 로딩되는 시점에 초기화되어 계속 1 씩 증가되며 사용된다.
			tabindex: 1,
			
			// 아이템 모듈이 갖는 기본값을 정의한다.
			// 선언방법은 "폼 콘트롤 모듈명_속성명" 의 규칙을 따른다
			defaults: 
			{
				price_maxbytes: 15,
				number_maxbytes: 15
			}
		}
		,
		/**
		 * 폼 항목에 값을 set/get 하는 메소드를 구현한다.
		 * 
		 * var $item = this.ui[itemParams.id];
		 * setter 호출 : InnerItemMethod[itemParams.type].set.call( $item, value );
		 * getter 호출 : data[itemParams.id] = InnerItemMethod[params.type].get.call( $item );
		 */
		InnerItemMethod : 
		{
			"text" : 
			{
				MODULE: null
				,
				/**
				 * @return $(item) 대상 item 의 $ 선택 객체
				 */
				set: function(params)
				{
					if( params.placeholder )
					{
						this.attr('placeholder', params.placeholder);
					}
					if( params.max_size )
					{
						this.attr('maxlength', params.max_size);
					}
					if( params.style )
					{
						this.attr('style', params.style);
					}
					if( params.value != undefined )
					{
						if( typeof params.value == 'string' )
						{
							params.value = UCMS.removeTagBR(params.value);
						}
						this.val( params.value );
					}
					else
					{
						// clear
						this.val('');
					}
					
					//
					if( params.readonly )
					{
						this.attr('readonly', true);
					}
					else
					{
						this.prop("disabled", params.disable == true);
					}
					
					return this;
				},
				get: function()
				{
					// trim() 을 수행하면 의도한 공백이 제거된다.
					//return this.val().trim();
					return this.val();
				}
				,
				click: function(e)
				{
					UCMS.elemScroll( this );
				}
				,
				change: function(e, params)
				{
					params.value = this.val();
					return params.value;
				}
			} // end text
			,
			"textarea" : 
			{
				MODULE: null
				,
				/**
				 * @return $(item) 대상 item 의 $ 선택 객체
				 */
				set: function(params)
				{
					if( params.placeholder )
					{
						this.attr('placeholder', params.placeholder);
					}
					if( params.max_size )
					{
						this.attr('maxlength', params.max_size);
					}
					if( params.style )
					{
						this.attr('style', params.style);
					}
					if( params.rows )
					{
						this.attr('rows', params.rows);
					}
					if( params.value != undefined )
					{
						this.val( UCMS.removeTagBR(params.value) );
					}
					else
					{
						// clear
						this.val('');
					}
					
					//
					if( params.readonly )
					{
						this.attr('readonly', true);
					}
					else
					{
						this.prop("disabled", params.disable == true);
					}
					
					return this;
				}
				,
				get: function(params)
				{
					// trim() 을 수행하면 의도한 공백이 제거된다.
					//return this.val().trim();
					params.value = this.val();
					return params.value;
				}
				,
				click: function(e)
				{
					UCMS.elemScroll( this );
				}
				,
				change: function(e, params)
				{
					params.value = this.val();
					return params.value;
				}
			} // end textarea
			,
			"date" : 
			{
				MODULE: null
				,
				/**
				 * @return $(item) 대상 item 의 $ 선택 객체
				 */
				set: function(params)
				{
					var $item = $(this);
					if( $item.length > 0 && $item.datetimepicker )
					{
						$item.datetimepicker(
								_.extend({"format": 'YYYY-MM-DD'}, 
										_.pick(params, "format", "useCurrent", "locale")));
					}
					else
					{
						Logger.warn("Invalid form item : "+params.itemId);
					}
					
					var dateFormat = params.format||'YYYY-MM-DD';
					
					// 기본값 설정
					if(typeof params.value == 'string' && params.value != "")
					{
						this.val( moment(params.value).format(dateFormat) );
					}
					else if( params.defaultDate == true && params.useCurrent == true )
					{
						this.val( moment().format(dateFormat) );
					}
					else if( typeof params.defaultDate == "string" )
					{
						this.val( moment(params.defaultDate).format(dateFormat) );
					}
					else
					{
						// clear
						this.val('');
					}
					
					//
					if( params.readonly )
					{
						$item.attr('readonly', true);
					}
					else
					{
						$item.prop("disabled", params.disable == true);
						//this.find('button').prop("disabled", true);
					}
					
					return $item;
				}
				,
				get: function(params)
				{
					params.value = $(this).val();//moment($(this).val()).format( params.format||'YYYY-MM-DD' );
					return params.value;
				}
				,
				click: function(e)
				{
					UCMS.elemScroll( this );
				}
				,
				change: function(e, params)
				{
					params.value = $(this).val();
					return params.value;
				}
			} // end date
			,
			"checkbox" : 
			{
				MODULE: null
				,
				/**
				 * @return $(item) 대상 item 의 $ 선택 객체
				 */
				set: function(params)
				{
					switch( params.value )
					{
					case true:
					case "true":
					case "TRUE":
					case "Yes":
					case "YES":
					case "Y":
						params.value = true;
						break;
					
					default:
						params.value = false;
						break;
					}
					this.prop("checked", !!params.value);
					this.prop("disabled", params.disable == true);
					
					return this;
				}
				,
				get: function(params)
				{
					params.value = this.is(":checked");
					return params.value;
				}
				,
				click: function(e)
				{
					UCMS.elemScroll( this );
				}
				,
				change: function(e, params)
				{
					params.value = this.is(":checked");
					return params.value;
				}
			} // end checkbox
			,
			"radiobox" : 
			{
				MODULE: null
				,
				/**
				 * @return $(item) 대상 item 의 $ 선택 객체
				 */
				set: function(params)
				{
					var self = this;
					var makeItems = function(params)
					{
						self.empty();
						for(var i in params.items )
						{
							var item = params.items[i];
							self.append( $("<label>")
								.append('<input type="radio" name="'+params.itemId+'" value="'+item.value+'"> '+item.label) );
						}
						
						if( params.value != undefined && params.value != null )
						{
							self.find("[value="+params.value+"]").attr("checked", true);
						}
						
						//
						self.find('input').prop("disabled", params.disable == true);
					};
					var fetchItems = function(fetcher)
					{
						fetcher || (fetcher = {});
						if(! fetcher.url )
						{
							Logger.error("FormBox::radiobox - invalid parameter : fetcher");
							return UCMS.retReject();
						}
						if(! fetcher.parser )
						{
							var parser = function(res)
							{
								// [{label:##, value:##}, ...] 형식의 배열을 반환한다. 
								return res.extraData;
							};
							fetcher.parser = String(parser);
						}
						
						self.append( $("<label>")
							.append('<input type="radio" name="'+params.itemId+'" value="0"> loading..') );
						
						return $.ajax({
							type: 'GET',
							dataType: "json",
							error: function( XHR, textStatus, errorThrown )
						    {
						    	Logger.error("error status: "+textStatus);
						    	self.append( $("<label>")
									.append('<input type="radio" name="'+params.itemId+'" value="0"> fail') );
						    },
						    success: function(data, textStatus, jqXHR)
						    {
						    	self.empty();   	
						    },
							url: _.template(fetcher.url, FormBox.ItemSettings)
						})
						.then(function(data)
						{
							var parserBody;
							if( fetcher.parser.indexOf('function') == 0 )
							{
								parserBody = "return ("+fetcher.parser+")(res);";
							}
							else
							{
								parserBody = fetcher.parser;
							}
							
					    	var parser = new Function( "res", parserBody );
					    	return parser( data );
						});
					};
					
					if( !params.items && params.fetcher )
					{
						var p = null;
						if( typeof params.fetcher == 'string' )
						{
							var fetcher = new Function( params.fetcher );
							p = fetcher();
						}
						else
						{
							p = fetchItems(params.fetcher);
						}
						p.then(function(items)
						{
							params.items = items;
							makeItems( params );
						});
					}
					else
					{
						makeItems( params );
					}
					
					return this;
				}
				,
				get: function(params)
				{
					params.value = this.find("input:checked").val();
					return params.value;
				}
				,
				click: function(e)
				{
					UCMS.elemScroll( this );
				}
				,
				change: function(e, params)
				{
					params.value = this.find("input:checked").val();
					return params.value;
				}
			} // end radiobox
			,
			"combobox" : 
			{
				MODULE: null
				,
				/**
				 * @return $(item) 대상 item 의 $ 선택 객체
				 */
				set: function(params)
				{
					//this.find(".select_label").text("선택해 주세요.")
					var self = this;
					var $select = this.find("select");
					var selecteItem = function()
					{
						var select_name = $select.find("option:selected").text();
						self.find(".select_label").text(select_name);
					};
					var makeItems = function(params)
					{
						$select.empty();
						for(var i in params.items )
						{
							var item = params.items[i];
							$select.append( $("<option value='"+item.value+"'>").text(item.label) );
						}
						
						if( params.value != undefined && params.value != null )
						{
							$select.find("[value="+params.value+"]").attr("selected", "selected");	
						}
						
						if( params.disable == true )
						{
							$select.prop("disabled", true);
							$select.parent().addClass("disabled");
						}
						else
						{
							$select.prop("disabled", false);
							$select.parent().removeClass("disabled");
						}
						
						selecteItem();
					};
					var fetchItems = function(fetcher)
					{
						fetcher || (fetcher = {});
						if(! fetcher.url )
						{
							Logger.error("FormBox::combobox - invalid parameter : fetcher");
							return UCMS.retReject();
						}
						if(! fetcher.parser )
						{
							var parser = function(res)
							{
								// [{label:##, value:##}, ...] 형식의 배열을 반환한다. 
								return res.extraData;
							};
							fetcher.parser = String(parser);
						}
						
						$select.append( $("<option value='0'>").text('loading..') );
						selecteItem();
						
						return $.ajax({
							type: 'GET',
							dataType: "json",
							error: function( XHR, textStatus, errorThrown )
						    {
						    	Logger.error("error status: "+textStatus);
						    	$select.empty().append($("<option value='0'>").text('fail'));
						    },
						    success: function(data, textStatus, jqXHR)
						    {
						    	$select.empty();   	
						    },
							url: _.template(fetcher.url, FormBox.ItemSettings)
						})
						.then(function(data)
						{
							var parserBody;
							if( fetcher.parser.indexOf('function') == 0 )
							{
								parserBody = "return ("+fetcher.parser+")(res);";
							}
							else
							{
								parserBody = fetcher.parser;
							}
							
					    	var parser = new Function( "res", parserBody );
					    	return parser( data );
						});
					};

					if( !params.items && params.fetcher )
					{
						var p = null;
						if( typeof params.fetcher == 'string' )
						{
							var fetcher = new Function( params.fetcher );
							p = fetcher();
						}
						else
						{
							p = fetchItems(params.fetcher);
						}
						p.then(function(items)
						{
							params.items = items;
							makeItems( params );
						});
					}
					else
					{
						makeItems( params );
					}
					
					return $select;
				}
				,
				get: function(params)
				{
					params.value = this.find("option:selected").val();
					return params.value;
				}
				,
				click: function(e)
				{
					UCMS.elemScroll( this );
				}
				,
				change: function(e, params)
				{
					var $option = this.find("option:selected");
					// 바뀐 옵션의 값을 가져와서 레이블에 넣어줍니다. 
					var select_name = $option.text();
					this.find(".select_label").text(select_name);
					
					params.value = $option.val();
					return params.value;
				}
			} // end combobox
			,
			"price":
			{
				MODULE: null
				,
				/**
				 * @return $(item) 대상 item 의 $ 선택 객체
				 */
				set: function(params)
				{
					if( params.unit && params.unit.label )
					{
						this.find('span').text( params.unit.label );
					}
					if( params.value != undefined )
					{
						if( typeof params.value == 'string' )
						{
							params.value = params.value.replace(/,/ig, '');
						}
						params.value = UCMS.isInteger(params.value) 
								? UCMS.numberWithCommas(Number(params.value))
								: 0;
					}
					var $input = this.find('input');
					if( params.value != undefined )
					{
						$input.val( params.value );
					}
					else
					{
						// clear
						$input.val('');
					}
					$input.keyup(function()
					{
						var newPrice = $(this).val().replace(/,/gi,'');
						if( UCMS.isIE() )
						{
							/*
							 * TODO 키 입력 과정에서 콤마를 붙이는 작업을 하면, IE 에서 change 이벤트가 발생되지 않는다.
							 * 그래서 우선은 문자를 제거하는 동작만 수행하기로 한다.
							 */
							//$(this).val(UCMS.parseNumber( num ));
							/*
							 * TODO 키 이벤트 처리 중에 값을 변경하면, change 가 발생되지 않는 IE 도 존재한다.
							 * 다른 PC 의 IE11 에서는 값을 변경하지 않아야만 change 이벤트가 발생되고 있다.
							 * 그래서 아예 IE 인 경우 키 입력시에는 숫자 처리 코드를 제거하고, 
							 * IE 인 경우 change 핸들러에서 포멧 검증 관련 처리를 진행한다. 
							 */
						}
						else
						{
							if( UCMS.isInteger(newPrice) )
							{
								$(this).val(UCMS.numberWithCommas( Number(newPrice) ));
							}
							else
							{
								$(this).val(0);
							}
						}
					});
					//
					if( params.readonly )
					{
						$input.attr('readonly', true);
					}
					else
					{
						$input.prop("disabled", params.disable == true);
					}
					//
					params.max_bytes || (params.max_bytes = FormBox.ItemSettings.defaults.price_maxbytes);
					$input.attr('maxlength', params.max_bytes);
					
					return $input;
				},
				get: function(params)
				{
					var price = this.find('input').val().replace(/,/gi,'');
					params.value = UCMS.isInteger(price) ? Number(price) : 0;
					return params.value;
				}
				,
				/**
				 * 화페 입력 태그인 input 의 click 이벤트 핸들러
				 */
				click: function(e)
				{
					UCMS.elemScroll( this );
					// price 는 화폐 단위와 금액을 표현하는 복합 아이템 구조이다.
					var $input = this.parent().find('input');
					var num = $input.val().replace(/,/gi,'');
					if( num == 0 )
					{
						$input.val("");
					}
				}
				,
				change: function(e, params)
				{
					var $input = this.parent().find('input');
					var price = $input.val().replace(/,/gi,'');
					params.value = UCMS.isInteger(price) ? Number(price) : 0;
					return params.value;
				}
			}
			,
			"number":
			{
				MODULE: null
				,
				/**
				 * @return $(item) 대상 item 의 $ 선택 객체
				 */
				set: function(params)
				{
					if( params.unit && params.unit.label )
					{
						this.find('span').text( params.unit.label );
					}
					var $input = this;
					if( params.value != undefined )
					{
						if( typeof params.value == 'string' )
						{
							params.value = params.value.replace(/,/ig, '');
						}
						params.value = UCMS.isInteger(params.value) 
								? UCMS.numberWithCommas(params.value)
								: UCMS.parseNumber(params.value);
						$input.val( params.value );
					}
					else
					{
						// clear
						$input.val('');
					}
					$input.keyup(function()
					{
						var num = $(this).val().replace(/,/gi,'');
						if( UCMS.isIE() )
						{
							/*
							 * TODO 키 입력 과정에서 콤마를 붙이는 작업을 하면, IE 에서 change 이벤트가 발생되지 않는다.
							 * 그래서 우선은 문자를 제거하는 동작만 수행하기로 한다.
							 */
							//num = UCMS.parseNumber(num);
							/*
							 * TODO 키 이벤트 처리 중에 값을 변경하면, change 가 발생되지 않는 IE 도 존재한다.
							 * 다른 PC 의 IE11 에서는 값을 변경하지 않아야만 change 이벤트가 발생되고 있다.
							 * 그래서 아예 IE 인 경우 키 입력시에는 숫자 처리 코드를 제거하고, 
							 * IE 인 경우 change 핸들러에서 포멧 검증 관련 처리를 진행한다. 
							 */
						}
						else
						{
							num = UCMS.isInteger(num) 
									? UCMS.numberWithCommas(Number(num))
									: UCMS.parseNumber(num);
									
							$(this).val(num);
						}
					});
					//
					if( params.readonly )
					{
						$input.attr('readonly', true);
					}
					else
					{
						$input.prop("disabled", params.disable == true);
					}
					//
					params.max_bytes || (params.max_bytes = FormBox.ItemSettings.defaults.number_maxbytes);
					$input.attr('maxlength', params.max_bytes);
					
					return $input;
				},
				get: function(params)
				{
					var num = this.val().replace(/,/gi,'');
					params.value = Number(UCMS.parseNumber(num)); 
					return params.value;
				}
				,
				click: function(e)
				{
					UCMS.elemScroll( this );
					var num = this.val().replace(/,/gi,'');
					if( num == 0 )
					{
						this.val("");
					}
				}
				,
				change: function(e, params)
				{
					var num = this.val().replace(/,/gi,'');
					params.value = Number(UCMS.parseNumber(num));
					return params.value;
				}
			}
			,
			"password":
			{
				MODULE: null
				,
				/**
				 * @return $(item) 대상 item 의 $ 선택 객체
				 */
				set: function(params)
				{
					if( params.value )
					{
						this.val(params.value);
					}
					else
					{
						// clear
						this.val('');
					}
					//
					if( params.readonly )
					{
						this.attr('readonly', true);
					}
					else
					{
						this.prop("disabled", params.disable == true);
					}
					
					return this;
				}
				,
				get: function(params)
				{
					var passwd = this.val();
					if( typeof params.validation == 'string' )
					{
						params.value = (new RegExp(params.validation).test(passwd)? passwd : null);
					}
					else
					{
						params.value = passwd;
					}
					return params.value;
				}
				,
				click: function(e)
				{
					UCMS.elemScroll( this );
				}
				,
				change: function(e, params)
				{
					params.value = this.val();
					return params.value;
				}
			}
		}
		,
		/**
		 * 외부에서 공급되는 폼 위젯에 대한 접근 메소드를 구현한다.
		 */
		OuterItemMethod:
		{
			/**
			 * 생성된 FormItem 인스턴스를 지정한 Region 에 추가한다.
			 * this 는 FormItem 이 추가될 Region 의 {Marionette.Region}
			 * @param {FormItemPanel} item - 생성된 FormItem
			 * @return $(item) 대상 item 의 $ 선택 객체 
			 */
			set: function(item)
			{
				// 모든 위젯을 폼에서 사용할 수 있게 정책 변경
				// 단 폼위젯이 아닌경우 디자인 항목으로 사용된다.
				this.show( item );
				return item.getWidget$Element();
			}
			,
			/**
			 * FormItem 으로 부터 입력된 값을 얻는다.
			 * this 는 FormItem 이 추가될 Region 의 {Marionette.Region}
			 * @param {FormItemPanel} item - 생성된 FormItem 
			 */
			get: function(item)
			{
				if( item instanceof FormItemPanel )
				{
					var data = item.getItemData();
					Logger.info("OuterItemMethod Getter :");
					Logger.info(data);
					if( data != null )
					{
						return data;
					}
					else
					{
						return null;
					}
				}
				else
				{
					Logger.error("OuterItemMethod Getter - invalid item instance!!!");
				}
			}
			,
			click: function(e)
			{
				UCMS.elemScroll( this );
			}
		}
	});
	
	return FormBox;
});