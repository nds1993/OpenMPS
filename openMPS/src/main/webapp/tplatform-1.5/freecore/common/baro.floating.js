/**
 * Project : MobileOven
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman 
 */

define(
[
	"Logger"
],
function( Logger, html )
{
	UCMSPlatform.SPA.AppMain.initResource( html );
	
	/**
	 * 콘텐츠 위젯을 Floating 방식으로 출력하는 판넬
	 */
	var FloatingPanel = UCMS.SPA.Panel.extend(
	{
		// 클래스 이림은 외부에서 지정하는 경우 변경 가능하다.
		//className: "floating_region",
		className: "modal_box",
		template: function()
		{
			return "<div class='contents_box'></div>";
		},
		ui:
		{
			contents_box : ".contents_box"
		},
		// 부모 노드의 jquery 셀렉터
		_$parentBox : null,
		_content : undefined,
		
		/**
		 * @param options	{
		 * 						content: 					플로팅될 콘텐츠 판넬의 인스턴스
		 *						title: {string}				지정한 경우 플로팅 박스의 제목 영역 생성
		 *						close: {boolean|function}
		 *													true 인 경우 닫기 버튼 추가됨.
		 *													함수를 지정하는 경우 닫기 버튼 클릭시 callback 됨.
		 * 					}
		 * 					호출하는 측에서 인스턴스를 생성하고 플로팅 동작을 시작한다.
		 */
		initialize: function(options)
		{
			this._content = options.content;
			this._header = {
				title: options.title || null,
				close: ( options.close != undefined )
			};
			
			if( typeof options.close == 'function' )
			{
				this._header.cbClose = options.close;
			}
			if( this._header.close == true && this._header.title == null )
			{
				this._header.title = "";
			}
		},
		
		onRender: function()
		{
			if( this._content != undefined )
			{
				// 이벤트 'before:render', 'render' 가 발생됨
				this._content.render();
				this._content.triggerMethod("before:show");
			}
			else
			{
				Logger.warn("FloatingPanel.onRender() - You need to pass a content as Widget!");
			}
		},
		
		onShow: function()
		{
			this._showHeader();
			
			//
			if( this._content != undefined )
			{
				this.ui.contents_box.append(this._content.$el);
				this._content.triggerMethod("show");
			}
		},
		
		/**
		 * @return { $.promise } 처리 완료를 콜백하는 Promise 객체
		 */
		close: function()
		{
			var self = this;
			var d = $.Deferred();
			
			if( this._content == undefined )
			{
				d.reject();
				return d.promise();
			}
			
			this._content.$el.fadeOut( "fast", function()
			{
				// 플로팅 판넬이 남아있는 경우 다른 위치에 플로팅 판넬을 생성시 문제가 발생된다.
				// 그러므로 판넬을 닫고, 필요시 다시 생성하는 방법을 사용한다.
				FloatingPanel.__super__.close.call( self );

			    if(self._content.isClosed){ return; }
			    if (self._content.close) { self._content.close(); }
			    else if (self._content.remove) { self._content.remove(); }
			    self._content.triggerMethod("close");
				delete self._content;
				
				if( self._$parentBox )
				{
					// 지정한 경우만 fadeIn() 가 수행된다.
					self._$parentBox.fadeIn();
				}
				
				Logger.debug("FloatingPanel._floating.close() - removed a content.");
				
				//
				d.resolve();
			});
			
			return d.promise();
		}
		,
		/**
		 * 부모 노드의 jquery 셀렉터를 셋한다.
		 * 제공된 경우 플로팅 판넬이 출력되는 동안 감춰지게된다.
		 */
		setParentBox: function($box)
		{
			this._$parentBox = $box;
		}
		,
		/**
		 * 헤더 영역을 출력한다.
		 */
		_showHeader: function()
		{
			if( this._header.title == null )
			{
				return;
			}

			var $header = $("<div class=floating_title_box></div>");
			$header.append( "<div class=title>"+this._header.title+"</div>" );
			if( this._header.close )
			{
				var $btn = $("<button class='btn btn-link btn_floating_close'>×</button>");
				if( this._header.cbClose )
				{
					$btn.click( this._header.cbClose );
				}
				else
				{
					$btn.click( function()
					{
						FloatingPanel.close();
					});
				}
				$header.append($btn);
			}

			//
			this.ui.contents_box.empty();
			this.ui.contents_box.append($header);
		}
	}
	,
	{
		// 생성된 플로팅 영역의 jquery 객체
		_$holder: null,
		// 플로팅 박스 인스턴스. 복수개의 플로팅 박스를 보관할 수 있다.
		_floating: []
		,
		isFloating: function()
		{
			return ( FloatingPanel._floating.length > 0 );
		}
		,
		count: function()
		{
			return FloatingPanel._floating.length;
		}
		,
		/**
		 * 플로팅 판넬을 오픈한다.
		 * 플로팅이 활성화되면 body 이 스크롤되지 않게 설정되기 때문에,
		 * 필히 플로팅이 종료될 때는 close() 에 의해 플로팅을 닫는 절차를 거쳐야한다.
		 * 
		 * @param widgetKlass		플로팅 판넬에 추가될 콘텐츠 위젯 클래스 모듈.
		 * 							외부에서 생성된 위젯을 띄우는 경우, 위젯을 외부에서 생성해서 넘기고,
		 * 							widgetOptions 는 null 을 제공한다.
		 * 							콘텐츠 위젯은 생성자로 제공되는 { deferred: $.Deferred() } 을 받아서 처리 결과를 반환한다.
		 * @param widgetOptions		콘텐츠 위젯 생성시 사용되는 파라메터.
		 * 							null 인 경우 widgetKlass 에는 위젯의 인스턴스가 제공된다.
		 * @param options			{
		 * 								parentBox: {string} 	선택사항. 부모 태그를 선택할 수 있는 셀렉트 스트링.
		 * 														지정한 경우 해당 부모 태그가 hide 된다.
		 * 								unique: {boolean}		true 인 경우 하나의 플로팅 박스만 출력된다. 기본값은 false.
		 * 														기존에 활성화된 플로팅 박스가 있다면, 제거된 후 새로운 플로팅 박스가 활성화된다.
		 * 								className: {string}		생성되는 플로팅 판넬의 class 를 지정한다. 기본값으로 modal_box 가 지정됨
		 *														값을 넘길때 클래스명은 floating_box 와함께 아래의 두개의 클래스중 용도에 따라 선택해서 사용되어야 합니다. 두재중 하나는 반드시 넘어와야 합니다.
		 *														page_box : 전체 페이지용 클래스
		 *														modal_box : 모달 팝업용 클래스 
		 *								title: {string}			지정한 경우 플로팅 박스의 제목 영역 생성
		 *								close: {boolean|function}
		 *														true 인 경우 닫기 버튼 추가됨.
		 *														함수를 지정하는 경우 닫기 버튼 클릭시 callback 됨.
		 * 							}
		 * 							지정하지 않은 경우 팝업 방식으로 콘텐츠가 활성화된다.
		 * @return { $.Promise }	콘텐츠 위젯의 처리 결과를 반환하는 Promise 객체
		 * 							콘텐츠 위젯이 생성자로 수신하는 deferred 객체를 사용하는 방식에 따라 반환되는 방식이 결정된다. 
		 */
		open: function(widgetKlass, widgetOptions, options)
		{
			var d = $.Deferred();
			/**
			 * 플로팅 판넬을 활성화한다.
			 * @return {FloatingPanel} 추가된 플로팅 판넬 인스턴스
			 */
			var showFloatingPanel = function()
		    {
				if( widgetOptions != undefined && widgetOptions != null )
				{
					widgetKlass = new widgetKlass(_.extend({ deferred: d }, widgetOptions));
				}
				
				var panel = new FloatingPanel(
					_.extend(
							{ "content": widgetKlass },
							_.pick(options, "className", "title", "close")
					)
				);

				// region.show() 의 동작을 흉내낸다.
				panel.render();
				panel.triggerMethod("before:show");
				FloatingPanel._$holder.append( panel.$el );
				panel.triggerMethod("show");
				
				//
				FloatingPanel._floating.push(panel);
				
				// 배경이 스크롤되는 것을 막는다.
				// close() 동작에서 해제된다.
				$('body').css('overflow', 'hidden');
				
				return panel;
		    };
		    
		    options || ( options = {} );
		    
		    if( widgetKlass )
		    {
		    	if( options.unique == true )
				{
					// 기존의 판넬을 닫는다.
					FloatingPanel.close();
				}
		    	
			    var $box = $(options.parentBox);
			    if( $box.length > 0 )
			    {
			    	FloatingPanel._initFloatingHolder($box);
			    	
			    	// 부모를 비활성화 처리 후 콘텐츠 활성화
			    	$box.fadeOut( "fast", function()
			    	{
			    		showFloatingPanel().setParentBox($box);
			    	} );
			    }
			    else
			    {
			    	FloatingPanel._initFloatingHolder();
			    	
			    	// 팝업 모델로 활성화
			    	showFloatingPanel();
			    }
		    }
		    else
		    {
		    	Logger.error("FloatingPanel.open() - Need to a widget parameter.");
		    	d.reject();
		    }
		    
		    return d.promise();
		}
		,
		/**
		 * 화면을 꽉 채우는 페이지 형식으로 위젯을 플로팅한다.
		 */
		openAsPage: function(widgetKlass, widgetOptions, options)
		{
			options || ( options = {} );
			options.className = "page_box";
			return FloatingPanel.open(widgetKlass, widgetOptions, options);
		}
		,
		/**
		 * 오픈된 판넬을 닫는다.
		 * 
		 * @return { $.Promise }	플로팅 판넬이 종료되는 경우 성공 콜백. 판넬이 존재하지 않는 경우 실패 콜백.
		 */
		close: function()
		{
			var promise = null;
			
			if( FloatingPanel.isFloating() == true )
			{
				var panel = FloatingPanel._floating.pop();
				promise = panel.close()
				.always(function()
				{
					delete panel;
					
					if( FloatingPanel.count() == 0 )
					{
						FloatingPanel._$holder.remove();
						FloatingPanel._$holder = null;
					}
					
					Logger.info("FloatingPanel.close() - done.");
				});
				$('body').css('overflow', 'auto');
			}
			else
			{
				Logger.info("FloatingPanel.close() - Not open!");
				
				var d = $.Deferred();
				
				promise = d.promise();
				d.reject();
			}
			
			return promise;
		}
		,
		/**
		 * 플로팅 박스 영역을 초기화한다.
		 * 
		 * @param $parentBox		플로팅박스를 호출한 영역의 jquery 객체
		 */
		_initFloatingHolder: function($parentBox)
		{
			if( $parentBox )
			{
				FloatingPanel._$holder = $parentBox.next(".floating_region");
		    	if( FloatingPanel._$holder.length == 0 )
		    	{
		    		// 없는 경우 생성한다.
		    		$parentBox.after($("<div class='floating_region'></div>"));
		    		FloatingPanel._$holder = $parentBox.next(".floating_region ");
		    	}
			}
			else
			{
				// 팝업 박스 초기화
				FloatingPanel._$holder = $(".floating_region");
		    	if( FloatingPanel._$holder.length == 0 )
		    	{
		    		// 없는 경우 생성한다.
		    		$("body").append($("<div class='floating_region'></div>"));
		    		FloatingPanel._$holder = $(".floating_region");
		    	}
			}

	    	return FloatingPanel._$holder;
		}
	});
	
	return FloatingPanel;
});
