/**
 * Project : openMPS MIS
 *
 * SD 영업 > 단가관리 > 표준단가 주간
 * SD0203
 *
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaHeader",
	"WorkAreaRenderer2",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, ContentHeader, WorkAreaRenderer2, JQGrid4)
{
	var Renderer = WorkAreaRenderer2.extend(
	{
		constructor: function(options)
		{
			var gridOptions = options.resultBox.options.content.options.gridParams;
			_.extend
			(
				gridOptions,
				this.makeAutoScrollingOptions2_local(50)
			);
			Renderer.__super__.constructor.call(this, options);
		}
		,
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "SD0203";
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
					{"query":"조회"},
					{"create":"신규"},
					{"save":"저장"},
					//{"delete":"삭제"},
					{"cancel":"취소"},
					{"print":"인쇄"},
					{"manual":"도움말"}
				];
				Renderer.__super__.initSubHeader.call( self, headerParams );
			});
		}
		,
		getItemList: function()
		{
			return [
			        "queryBox",
					"strtDateBox",
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

			this.attachFormItem("queryBox");
			this.attachFormItem("strtDateBox");

			//
			this.attachHeaderItem("resultBox.header", function(evt)
			{
				if(evt.cmd == "content:request")
				{
					self.onRequestApproval();
				}
				else if(evt.cmd == "content:reqcancel")
				{
					self.onCancelApproval();
				}
			});
			//
			var gridItem = this.attachGridItem("resultBox.content");
			gridItem.getItem()
			.on(JQGrid4.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0203_grid_1","판매단가",false);
			});
			
			gridItem.getItem().on(JQGrid4.EVENT.SELECTCELL, function(item)
			{
				// { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
				Logger.debug(item);

				
			});

			gridItem.getItem().on(JQGrid4.EVENT.EDITCELL, function(item)
			{
				Logger.debug("EDITCELL :: " + item.id);
				self.onGridEditCell( gridItem, item.id );
			});
			
			setTimeout(function(){
				
				self.onButtonState(null, "new");
				
				$("#SD0203_grid_1_list checkbox").change(function(){
					Logger.debug("checkbox :: " + $(this));
				});
			
			},500);
			
		}
		,
		onGridEditCell: function(gridItem, rowId)
		{
			var rowData = this.applyPrice(gridItem.getItem().getRowData( rowId ));
			gridItem.setRow( rowId, rowData );

			//
			this.beginTransaction();
		}
		,
		applyPrice : function(rowData)
		{
			
			Logger.debug("rowData.totalPrice :: " + rowData.totalPrice);
			Logger.debug("rowData.stndPrice :: " + rowData.stndPrice);
			
			
			rowData.increase = Number(rowData.increase);
			rowData.salePrice = !isNaN(rowData.totalPrice) &&  rowData.totalPrice != "null" ? Number(rowData.totalPrice) + rowData.increase : 0;
			rowData.stndPrice = !isNaN(rowData.stndPrice) &&  rowData.stndPrice != "null"  ? Number(rowData.stndPrice) : 0;
			rowData.lastStndPrice = Number(rowData.lastStndPrice);
			rowData.increasePrice =  ( isNaN(rowData.firstInc) )
						? 0
						: ( rowData.increase - Number(rowData.firstInc) ) ;
			rowData.discPer = (rowData.salePrice == 0 || rowData.stndPrice == 0 )
						? 0
						: ( (1 - (rowData.salePrice / rowData.stndPrice))*100.0).toFixed(2);
			//
			return rowData;
		}
		,
		applyDiscPer : function(rowData)
		{
			
			Logger.debug("rowData.totalPrice :: " + rowData.totalPrice);
			Logger.debug("rowData.stndPrice :: " + rowData.stndPrice);
			
			Logger.debug("rowData.stndPrice :: " + !isNaN(rowData.totalPrice));
			
			rowData.increase = Number(rowData.increase);
			rowData.salePrice = !isNaN(rowData.salePrice) &&  rowData.salePrice != "null" ? Number(rowData.salePrice) + rowData.increase : 0;
			rowData.stndPrice = !isNaN(rowData.stndPrice) &&  rowData.stndPrice != "null"  ? Number(rowData.stndPrice) : 0;
			rowData.lastStndPrice = Number(rowData.lastStndPrice);
			rowData.increasePrice =  ( rowData.stndPrice == 0 &&  rowData.lastStndPrice == 0 )
						? 0
						: ( rowData.stndPrice - rowData.lastStndPrice) + (rowData.increase) ;
			rowData.discPer = (rowData.salePrice == 0 || rowData.stndPrice == 0 )
						? 0
						: ( (1 - (rowData.salePrice / rowData.stndPrice))*100.0).toFixed(2);
			//
			return rowData;
		}
		,
		/**
		 * 현재 선택된 주간 판매단가에 대한 승인을 요청한다.
		 */
		onRequestApproval: function()
		{

			var gridItem = this.getActiveGrid();

			var self = this;
			var grid = gridItem.getItem();
			var ids = grid.getSelRowId(true);
			

			if( ids[0] == null )
			{
				UCMS.alert("선택된 행이 없습니다.");
				return UCMS.retReject();
			}

			var deleteTasks = [];
			for(var i in ids)
			{
				var rowData = grid.getRowData( ids[i] );

				if(rowData.strtDate && rowData.strtDate.length > 0
				&& rowData.lastDate && rowData.lastDate.length > 0
				)
				{
					deleteTasks.push( rowData );
				}
				else
				{
					UCMS.alert("승인요청은 시작일,종료일이 존재해야 가능합니다.</br>가격을 저장하신후 재요청 바랍니다.");
					return;
				}
			}

			var client = this.getClient();

			var self = this;
			var params = this.getQueryData();
			var apiPath = client.getAPIPath("create", "reqappro", params);

			UCMS.showPrompt("처리중 입니다.");

			client.transaction( apiPath, deleteTasks )
			.done(function(result)
			{
				UCMS.hidePrompt();

				if( result.resultCode != 0 )
				{
					UCMS.warn(result);
					UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
				}
				else
				{
					UCMS.alert("승인요청되었습니다.").then(function(){
						self.onQuery();
					});
				}

				return ( result.resultCode==0 );
			})
			.fail(function(e)
			{
				UCMS.error(e);
				UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
			});
		}
		,
		/**
		 * 현재 선택된 주간 판매단가에 대한 승인을 요청을 취소한다.
		 */
		onCancelApproval: function()
		{

			var gridItem = this.getActiveGrid();

			var self = this;
			var grid = gridItem.getItem();
			var ids = grid.getSelRowId(true);
			

			if( ids[0] == null )
			{
				UCMS.alert("선택된 행이 없습니다.");
				return UCMS.retReject();
			}

			var deleteTasks = [];
			for(var i in ids)
			{
				var rowData = grid.getRowData( ids[i] );
				
				if(rowData.approYn == 'Y'
				)
				{
					UCMS.alert("승인된건은 취소불가능합니다.");
					return;
				}

				if(rowData.approYnName == '대기'
				)
				{
					deleteTasks.push( rowData );
				}
				else
				{
					UCMS.alert("승인요청된 건만 취소가능합니다.");
					return;
				}
			}

			var client = this.getClient();

			var self = this;
			var params = this.getQueryData();
			var apiPath = client.getAPIPath("create", "cancelappro", params);

			UCMS.showPrompt("처리중 입니다.");

			client.transaction( apiPath, deleteTasks )
			.done(function(result)
			{
				UCMS.hidePrompt();

				if( result.resultCode != 0 )
				{
					UCMS.warn(result);
					UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
				}
				else
				{
					UCMS.alert("승인요청 취소되었습니다.").then(function(){
						self.onQuery();
					});
				}

				return ( result.resultCode==0 );
			})
			.fail(function(e)
			{
				UCMS.error(e);
				UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
			});
		}
		,
		getSelRowData : function()
		{
			var self = this;
			var gridItem = this.getActiveGrid();
			var grid = gridItem.getItem();
			var ids = grid.getSelRowId(true);
			var retData = [];
			
			if( ids )
			{
				$.each(ids, function(i,item){
					var rowData = grid.getRowData( item );
					retData.push(rowData);
				});
				
			}

			if( retData.length == 0 )
			{
				UCMS.alert("선택된 행이 없습니다.");
				return false;
			}
			
			return retData;
		}
		,
		onQuery: function(newGugun)
		{
		    var self = this;
		    var featureId = "resultBox.content";
			
		    this.getRendererItem("resultBox.content").clear();
		    
			this.showLoading();
			var params = this.getQueryData();
			var gridItem = this.getActiveGrid(featureId);
			
			if(newGugun == "new")
			{
				var newStrtDateObj = this.attachFormItem("strtDateBox").getItem().getWidget$Element().find(".strtDate");
			    Logger.debug("newStrtDateObj ::" + newStrtDateObj.val());
				
				params.newStrtDate = newStrtDateObj.val();
				params.newGubun = newGugun;
			}
			else
			{
				delete params.newGubun;
			}
			
			if(params.custCode && params.custCode.result)
			{
				params.custCode = params.custCode.result;
			}
			
			gridItem.clear();
			return gridItem.fetch( params, featureId )
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					Logger.debug("Fetched grid data count : "+res.extraData.result.length);
					
					if(res.extraData.result.length == 0)
					{
						UCMS.alert("조회된 결과가 없습니다.");
					}
					else
					{
						var resData = res.extraData.result;
						var applyData = [];
						
						if(params.newGubun == "new")
						{
							$.each(resData, function(i, item){
								var applyItem = self.applyPrice(item);
								applyData.push(applyItem);
							});
							
							self.onButtonState(applyData, params.newGubun);

							self.beginTransaction(true);
						}
						else
						{
							$.each(resData, function(i, item){
								var applyItem = self.applyDiscPer(item);
								applyData.push(applyItem);
							});
							
							self.onButtonState(applyData);
							
							self.beginTransaction(true);
							
						}
						
						gridItem.setData( applyData, "local" );
						
						if(params.newGubun == "new")
						{
							self.beginTransaction(true);
						}
					}
				}
				else if(res.resultCode == -4)
				{
					UCMS.alert("이미 생성된 가격이 있습니다. 기준일자로 가격을 조회하시기 바랍니다.");
				}
				else
				{
					UCMS.reportError(e,"데이타 조회를 실패하였습니다.");
				}
				
				return res;
			})
			.fail(function(e)
			{
				UCMS.reportError(e,"데이타 조회를 실패하였습니다.");
			})
			.always(function(){
				self.hideLoading();
			});
			
		}
		,
		onButtonState : function(gridData, newGubun)
		{
			var self = this;
			if(gridData && newGubun != "new")
			{
				var ingRequest = false;
				for(var i=0 ; i < gridData.length ; i++){
					
					if(gridData[i].approRequest != "" 
						&& gridData[i].approRequest != null 
						&& gridData[i].approRequest > 0)
					{
						ingRequest = true;
						break;
					}
					
				};
				
				if(ingRequest)
				{
				}
				else
				{
				}
					
			}
			else
			{
			}

		}
		,
		onCreate : function()
		{
			this.onQuery("new");
		}
		,
		onSave: function()
		{
			var self = this;
			
			var reqData = this.getSelRowData();
			
			if(!reqData){
				
				UCMS.alert("선택된 행이 없습니다.");
				return;
			}
			else
			{
				UCMS.confirm("진행중인 작업을 저장할까요?", {confirm:"저장", cancel:"취소"})
				.done(function(){
					
					self.saveTransaction(reqData);
					
				});
			}
			
		}
		
 		,
 		saveTransaction: function(reqData)
		{
 			var self = this;
			var gridItem = this.getActiveGrid("resultBox.content");
			
			if( gridItem )
			{
				var apply = function()
				{
					var sendData = [];
					
					$.each(reqData, function(i, item){
						item.dsType = "C";
						
						sendData.push(item);	
						
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
							UCMS.alert("저장되었습니다.").then(function(){
							});
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
 		onCancel: function(silent)
		{
			this.clearForm();
			
			var self = this;
			return this.endTransaction(true, this.getHeaderGridName())
			.then(function(cmd)
			{
				self._newRowId = null;
				if( cmd == null )
				{
					if( typeof silent != "boolean" || silent == false )
					{
						UCMS.alert("취소되었습니다.");
					}
				}
			});
		}

	});

	return Renderer;
});
