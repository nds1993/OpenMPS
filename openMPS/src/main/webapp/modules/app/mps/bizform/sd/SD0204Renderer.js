/**
 * Project : openMPS MIS
 *
 * 영업 > 거래처단가등급
 *
 */


 define([
 	"Logger",
 	"NDSProps",
 	"BaroBox",
 	"FormBox",
 	"WorkAreaHeader",
 	"RendererBase",
 	"WorkAreaRenderer2",
 	"manifest!jqGrid4-1.0.0#widget"
 ], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, RendererBase, WorkAreaRenderer2, JQGrid)
 {
 	var Renderer = WorkAreaRenderer2.extend(
 	{
 		initialize: function(options)
 		{
 			Renderer.__super__.initialize.apply( this, arguments );
 			this._contentId = "SD0204";
 		}
 		,
 		/**
		 * 1. 헤더 기능 버튼 초기화
		 */
 		initSubHeader: function()
 		{
 			Logger.debug("Renderer.initSubHeader()");

 			var self = this;
 			return this.queryHeaderInfo().then(function(headerParams)
 			{
 				//
 				headerParams.options.feature =
 				[
					{"query":"조회"},
				//	{"create":"신규"},
					{"save":"저장"},
				//	{"delete":"삭제"},
					{"cancel":"취소"},
					{"manual":"도움말"}
 				];
 				RendererBase.Method.initSubHeader.call( self, headerParams );
 			});
 		}
 		,
 		/**
		 * 2. 접근이 필요한 아이템 목록 지정
		 */
 		getItemList: function()
 		{
 			return [
 			        "queryBox",
 			        "gridBox1",
					"gridBox2",
 			        "gridBox2.content"
 			        ];
 		}
 		,
 		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
 		getHeaderGridName: function()
 		{
 			if( this._custCode )
			{
				// Detail
				return "gridBox2.content";
			}
			else
			{
				// Head
				return "gridBox1";
			}
 		}
 		,
 		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
 		onInitRendererItemEvents: function()
 		{
 			var self = this;

			this.attachHeaderItem("headerBox");
			var queryBox = this.attachFormItem("queryBox");
			var gridBox1 = this.attachGridItem("gridBox1");
			
			gridBox1.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				//self.downloadExcel("SD0204_grid_1","거래처단가등급_거래처",false);
				var data = gridBox1.getItem().getRowData(item.id);
				self.onSelectRow(data);
				
			});
			;
			this.attachGridItem("gridBox2.content").getItem()
			.on(JQGrid.EVENT.EDITCELL, function(btn)
			{
				//self.downloadExcel("SD0204_grid_2","거래처단가등급_단가등급",false);
				self.beginTransaction();
			});
			
			UCMS.retry(function(){
				
				queryBox.getItem().getWidget$Element()
 				.find(".partCode select").change(function(){
 					
 					var partCode = $(this).val();
 					self.fetchUsers(partCode);
 				});
				
			});

 		},
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );

			if(data.partCode == 0)
			{
				delete data.partCode;
			}
				
			if(data.salesman == 0)
			{
				delete data.salesman;
			}
				
			return data;
		}
 		,
		onQuery: function(featureId)
		{
		    var self = this;
		    var queryData = this.getQueryData();

		    this.getRendererItem("gridBox1").clear();
			this.getRendererItem("gridBox2.content").clear();

			//
			//this.showLoading();
			this.fetchingGrid("gridBox1")
			.then(function(res)
			{
				self.hideLoading();

				if(! res )
				{
					UCMS.reportError(res,"거래처 조회를 실패하였습니다.");
				}
			});
		}
 		,
 		fetchingGrid: function(featureId, options)
		{
			var params = options || this.getQueryData();
			var gridItem = this.getActiveGrid(featureId);
			
			if(featureId == "gridBox2.content")
				featureId = "detail";

			gridItem.clear();
			return gridItem.fetch( params, featureId )
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					Logger.debug("Fetched grid data count : "+res.extraData.result.length);
					gridItem.setData(res.extraData.result);
				}
				return res;
			})
			.fail(function(e)
			{
				UCMS.reportError(e,"데이타 조회를 실패하였습니다.");
			});
		}
 		,
 		onSelectRow: function(params)
		{
			Logger.debug(params);

			var self = this;
			var fetching = function()
			{
				self._custCode = params.custCode.trim();
				Logger.debug("onSelectRow() - _custCode : "+self._custCode);
				self.fetchingGrid("gridBox2.content", { "custCode" : self._custCode } );
			};

			if( this.isTransactionMode() == true )
			{
				return UCMS.confirm("진행 중인 작업을 저장할까요?", { confirm: "예", cancel: "아니요" })
				.done(function()
				{
					self.saveTransaction("detail", false);
				})
				.fail(function()
				{
					self.onCancel(true);
				})
				.always(function()
				{
					fetching();
				});
			}
			else
			{
				fetching();
			}
		}
 		,
		onSave: function()
		{
			var self = this;
			var gridItem = this.getActiveGrid("gridBox2.content");
			if( this.isTransactionMode() == true )
			{
				var reqData = gridItem.getItem().getRowData();
				
				$.each(reqData, function(i, item){
					if(Number(item.priceClass) < 0 || Number(item.priceClass) > 10)
					{
						UCMS.alert("단가 등급은 1~10까지 지정할 수 있습니다");
						return;
					}
				})
				
				
				UCMS.confirm("진행중인 작업을 저장할까요?", {confirm:"저장", cancel:"취소"})
				.done(function(){
					
					self.saveTransaction();
					
				});
			}
			else
			{
				self.saveTransaction();
			}
		}
 		,
 		saveTransaction: function()
		{
 			var self = this;
			var gridItem = this.getActiveGrid("gridBox2.content");
			
			if( gridItem )
			{
				var reqData = gridItem.getItem().getRowData();
				
				var apply = function()
				{
					var sendData = [];
					$.each(reqData, function(i, item){
						item.dsType = "U";
						
						if(item.priceClass && item.priceClass.length > 0){
							
							sendData.push(item);
						}
					})
					
					var params = self.getQueryData();
					params.custCode = self._custCode;
					return gridItem.commit( params, "detail", sendData )
					.then(function(res)
					{
						if( res.resultCode != 0 )
						{
							UCMS.reportError(res);
						}
						else
						{
							// TODO onSave 현재 상태로 롤백을 위해 그리드의 저장된 상태를 백업한다.
							gridItem.beginTransaction(true);
							self.setReadyMode();

							//
							UCMS.alert("저장되었습니다.");
						}
					})
					.fail(function(e)
					{
						UCMS.reportError(e);
					});
				};
				if( reqData && reqData.length > 0 )
				{
					return apply();
				}
				else
				{
					UCMS.alert("저장할 작업이 없습니다.");
				}
			}
			else
			{
				return UCMS.retResolve();
			}
		}
 		,
 		onCancel : function()
 		{
			var self = this;
			return this.endTransaction(true, this.getHeaderGridName())
			.then(function(cmd)
			{
				self._newRowId = null;
				//
				// Backup Model 을 사용하지 않는 모드. 기존 데이타로 되돌린다.
				//
				var gridItem = self.getActiveGrid();
				var selRowId = gridItem.getItem().getSelRowId();
				var promise = self.fetchingGrid("gridBox2.content", { "custCode" : self._custCode } );
				if( promise )
				{
					promise.always(function()
					{
						gridItem.getItem().setSelectRow( selRowId );
					});
				}
				else
				{
					_.delay(function()
					{
						gridItem.getItem().setSelectRow( selRowId );
					}
					, 500);
				}
			});
 		}
 		,
 		fetchUsers: function(partCode)
 		{
 			var self = this;
			return this.getClient().read( this.getQueryData(), "users" )
 			.then(function(res)
 			{
 				var $select = self.attachFormItem("queryBox").getItem().getWidget$Element().find(".salesman select");
 				var $option =  $("<option value=0>선택</option>");
 				$select.empty();
 				$select.append( $option ).change();

 				if( res.resultCode != 0 )
 				{
 					return;
 				}
 				for(var i in res.extraData.result)
 				{
 					var time = res.extraData.result[i];
	 				$option = $("<option></option>").text(time.codeName).val(time.codeId);
	 				$select.append( $option ).change();
 				}
 			})
 			;
 		}
 	}
 	,
 	{
 		EVENT:
 		{
 		}
 	});

 	return Renderer;
 });
