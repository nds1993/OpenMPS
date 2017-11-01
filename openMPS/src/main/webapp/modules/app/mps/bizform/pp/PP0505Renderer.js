/**
 * Project : OpenMPS MIS
 *
 * 생산 > PM라벨실적입고요청
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
 	"manifest!jqGrid4-1.0.0#widget",
 	"xml2json"
 ], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, RendererBase, WorkAreaRenderer2, JQGrid, X2JS)
 {
	var _old_strt_date = null; 
	 
 	var Renderer = WorkAreaRenderer2.extend(
 	{
 		initialize: function(options)
 		{
 			Renderer.__super__.initialize.apply( this, arguments );
 			this._contentId = "PP0505";
 		}
 		,
 		initSubHeader: function()
 		{
 			Logger.debug("Renderer.initSubHeader()");

 			var self = this;
 			return this.queryHeaderInfo().then(function(headerParams)
 			{
 				//
 				headerParams.options.feature =
 				[
 					{"query":"조회"}
 				];

 				RendererBase.Method.initSubHeader.call( self, headerParams );
 			});
 		}
 		,
 		getItemList: function()
 		{
 			return [
 			        "queryBox",
 			        "resultBox",
 			        "resultBox.content"
 			        ];
 		}
 		,
 		getHeaderGridName: function()
 		{
			return "resultBox.content";
 		}
 		,
 		onInitRendererItemEvents: function()
 		{
 			var self = this;

			this.attachHeaderItem("headerBox");
			var queryBox = this.attachFormItem("queryBox");
			this.attachHeaderItem("resultBox.header", function(evt)
			{
				if( evt.cmd == "content:confirm" )
				{
					self.onConfirm();
				}
			});
			var gridItem = this.attachGridItem("resultBox.content");
			gridItem.getItem().on(JQGrid.EVENT.SELECTCELL, function(item)
			{
				// { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
				var rowData = gridItem.getItem().getRowData( item.id );
				var queryData = self.getQueryData();

				self.popupBox("PP0505_popup",
				{
					plantCode: queryData.plantCode,
					strtDate: queryData.strtDate,
					product: {
						proCode: rowData.proCode,
						proName: rowData.proName,
						workQty: rowData.workQty,
						inQty: rowData.inQty
					}
				});
			});
			gridItem.getItem().on(JQGrid.EVENT.GRIDCOMPLETE, function(data)
			{
				// 그리드에 데이타 로딩이 완료되면,
				// grouping 된 영역의 계산된 데이타를 얻어서
				// 푸터에 의도한 포멧으로 출력한다.
				Logger.debug("JQGrid.EVENT.LOADCOMPLETE");

				// 출력할 데이타 취득
				var workQtysum = gridItem.getItem().getCol( "workQty", false, "sum" );
				var workWeightsum = gridItem.getItem().getCol( "workWeight", false, "sum" );
				var inQtysum = gridItem.getItem().getCol( "inQty", false, "sum" );

				// 푸터에 데이타 출력
				// 출력될 셀의 formatter 와 호환되는 데이타만 출력됨
				gridItem.getItem().footerData('set', { proName: "합계", workQty: workQtysum,workWeight:workWeightsum, inQty:inQtysum});
			});

			gridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0505_grid_1","PM라벨실적입고요청",false);
			});
			
			UCMS.retry(function()
			{
				var strtDateobj = self.attachFormItem("queryBox").getItem().getWidget$Element().find(".strtDate");
				queryBox.getItem().getWidget$Element().find(".strtDate").blur(function(){
					
					if(_old_strt_date != null && _old_strt_date != strtDateobj.val())
						gridItem.clear();
				});
			});


 		}
 		,
 		onConfirm: function()
 		{
 			var gridItem = this.attachGridItem("resultBox.content");
 			if( gridItem.getData().length == 0 )
 			{
 				UCMS.alert("요청할 내역이 없습니다.");
 				return;
 			}

 			var self = this;
 			var request = function()
 			{
				var client = self.createAPIClient(null, {dataType: "xml"});
				var params = self.getQueryData();

				self.showLoadingIpgo();
				client.read(params, "ipgo")
				.then(function(xmlRes)
				{
					// WMS API 가 반환하는 XML 반환값을 처리한다.
					var x2js = new X2JS();
					var jsonRes = x2js.xml2json( xmlRes );

					if( jsonRes.data.result.rst == 'S' )
					{
						UCMS.alert( jsonRes.data.result.msg )
						.then(function()
						{
							self.triggerMethod("query");
						});
					}
					else
					{
						UCMS.reportError(jsonRes, "입고 요청 중 장애가 발생했습니다.");
					}
				})
				.fail(function(err)
				{
					UCMS.reportError(err);
				})
				.always(function()
				{
					self.hideLoadingThis();
				});
 			};
 			
 			self.checkIpgoClose()
			.then(function(result){
				
				Logger.debug(result);
				if(result.resultCode == -101)
				{
					UCMS.alert("입고일자가 마감되었습니다.");
				}
				else
				{
					UCMS.confirm("입고요청 하시겠습니까?", {confirm:"요청", cancel:"취소"})
		 			.done(
		 					
		 				function()
		 				{
		 					self.checkIpgoClose()
		 					.then(request);
		 				}
		 			);
				}
			});
 			
 			
 		}
 		,
 		checkIpgoClose: function()
 		{
 			var self = this;
 			var client = self.createAPIClient(null,null);
			var params = self.getQueryData();

			return client.read(params, "ipgocheck")
			;
 		}
 		,
 		showLoadingIpgo:function()
		{
			if($ && $(".loading_box").length == 0)
			{
				$("body").append("<div class='loading_box'><div class='loading_img'></div><div style='display: block;position: fixed;top: 50%;width: 290px;background: #fff;font-size: 20px;text-align: center;left: 50%;margin-left: -145px;margin-top: 40px;z-index: 2;'>처리중 입니다</div></div>");
				return true;
			}
			else
			{
				return false;
			}
		}
 		,
 		hideLoadingThis:function()
		{
		 $("body > .loading_box").remove();
		}
 		,
		onQuery: function(featureId, options)
		{
			var self = this;
			var queryData = this.getQueryData();
		
			this.showLoading();
			this.fetchingGrid( featureId, options )
			.then(function(data)
			{
				self.hideLoading();
				
				if(data.resultCode == 0)
				{
					if(data.extraData.result && data.extraData.result.length > 0)
					{
						var strtDateobj = self.attachFormItem("queryBox").getItem().getWidget$Element().find(".strtDate");
						_old_strt_date = strtDateobj.val();
					}
					else
					{
						UCMS.alert("조회된 결과가 없습니다.");
					}
					
				}
				else
				{
					UCMS.alert("실패하였습니다.");
				}	
				
			});
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
