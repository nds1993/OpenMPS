/**
 * Project : openMPS MIS
 *
 * SD 영업 > 거래처관리 > 사원별 주문목록
 * SD0103
 *
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaRenderer2",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, WorkAreaRenderer2, JQGrid)
{
	var Renderer = WorkAreaRenderer2.extend(
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "SD0103";
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
					{"save":"저장"},
					{"cancel":"취소"},
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
					"resultBox",
					"resultBox.leftGrid",
					"resultBox.leftGrid.header",
					"resultBox.leftGrid.content",
					"resultBox.centerUpGrid",
					"resultBox.centerUpGrid.content",
					"resultBox.centerDownGrid",
					"resultBox.rightGrid",
					"resultBox.rightGrid.header",
					"resultBox.rightGrid.content"
			        ];
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;

			//
			this.attachHeaderItem("headerBox");
			this.attachHeaderItem("resultBox.leftGrid.header", function(event)
			{
				self.onSubButtonHandler(event);
			});
			this.attachHeaderItem("resultBox.rightGrid.header", function(event)
			{
				self.onSubButtonHandler(event);
			});

			//
			this.attachFormItem("queryBox");
			var $transBtn = this.attachFormItem("resultBox").getItem().getWidget$Element().find(".trans_btn_box");
			$transBtn.find("button.btn_trans_right").click(function()
			{
				// 거래처 제품 제거
				self.removeProduct();
			});
			$transBtn.find("button.btn_trans_left").click(function()
			{
				// 거래처 제품 추가
				self.addProduct();
			});

			//
			var custGridItem = this.attachGridItem("resultBox.leftGrid.content");
			custGridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0103_grid_1","주문목록_거래처",false);
			});

			custGridItem.getItem().on(JQGrid.EVENT.SELECTCELL, function(item)
			{
				Logger.debug("SELECTCELL row id : "+item.id+" - Editing : "+custGridItem.getItem().isEditMode());
				var data = custGridItem.getItem().getRowData(item.id);
				self.onSelectRow(data);
			});
			custGridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
			{
				// 거래처 입력
				Logger.debug(item);
				self.popupBox("codesearch",
				{
					// 거래처 코드 조회
					"codeType": "SD0101",
					"keyword": item.cell.value,
		            "params": {},
		            "callback": function(res)
		            {
		            	var rowData = custGridItem.getItem().getRowData( item.id );
		            	if( res &&
		            		res.code.length > 0 &&
		            		res.label.length > 0 )
		            	{
		            		self.beginTransaction();

	            			//
		            		rowData.salesman = self.getQueryData().salesman;
		            		rowData.salesmanCust = res.code;
		            		rowData.salesmanCustname = res.label;
		            		rowData.priceType = res.priceType;
		            		rowData.priceTypeName = res.priceTypeName;
		            	}
		            	custGridItem.setRow( item.id, rowData );
		            }
				});
			});
			var custProdList = this.attachGridItem("resultBox.rightGrid.content");
			custProdList.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0103_grid_4","주문목록_제품목록",false);
			});
			custProdList.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
			{
				if( item.cell.name == "salesmanProd" )
				{
					// 제품 입력
					Logger.debug(item);
					self.popupBox("codesearch",
					{
						// 제품 코드 조회
						"codeType": "PP0103",
						"keyword": item.cell.value,
			            "params": {},
			            "callback": function(res)
			            {
			            	var rowData = custProdList.getItem().getRowData( item.id );
			            	if( res &&
			            		res.code.length > 0 &&
			            		res.label.length > 0 )
			            	{
			            		self.beginTransaction();
			            		rowData.salesman = self.getQueryData().salesman;
			            		rowData.salesmanProd = res.code;
			            		rowData.salesmanProdname = res.label;
			            	}
			            	custProdList.setRow( item.id, rowData );
			            }
					});
				}
				else
				{
					var rowData = custProdList.getItem().getRowData( item.id );
					custProdList.setRow( item.id, rowData );
					self.beginTransaction();
				}
			});
			this.attachGridItem("resultBox.centerUpGrid.content");
			this.attachGridItem("resultBox.centerDownGrid").getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0103_grid_3","주문목록_거래처별_주문목록",false);
			});
			//
			this.onQuery();
		}
		,
		onSubButtonHandler: function(evt)
		{
			Logger.debug("onSubButtonHandler() - "+evt.cmd);

			switch(evt.cmd)
			{
			case "content:leftcreate":
				this.attachGridItem("resultBox.leftGrid.content")
				.createRow();
				this.beginTransaction(false, "resultBox.leftGrid.content");
				break;

			case "content:leftdelete":
				{
					var gridItem = this.attachGridItem("resultBox.leftGrid.content");
					var ids = gridItem.getItem().getSelRowId(true);
					if( ids == null || ids.length == 0 )
					{
						UCMS.alert("삭제할 거래처를 선택해 주세요.");
						return;
					}
					if( typeof ids == 'string' )
					{
						ids = [ids];
					}
					for(var i in ids)
					{
						gridItem.removeRow(ids[i]);
					}
					if( gridItem.getTaskCount() > 0)
					{
						this.beginTransaction(false, "resultBox.leftGrid.content");
					}
					else
					{
						this.endTransaction(false, "resultBox.leftGrid.content");
					}
				}
				break;

			case "content:rightcreate":
				this.attachGridItem("resultBox.rightGrid.content")
				.createRow();
				this.beginTransaction(false, "resultBox.rightGrid.content");
				break;

			case "content:rightdelete":
				{
					var gridItem = this.attachGridItem("resultBox.rightGrid.content");
					var ids = gridItem.getItem().getSelRowId(true);
					if( ids == null || ids.length == 0 )
					{
						UCMS.alert("삭제할 제품을 선택해 주세요.");
						return;
					}
					if( typeof ids == 'string' )
					{
						ids = [ids];
					}
					for(var i in ids)
					{
						gridItem.removeRow(ids[i]);
					}
					if( gridItem.getTaskCount() > 0)
					{
						this.beginTransaction(false, "resultBox.rightGrid.content");
					}
					else
					{
						this.endTransaction(false, "resultBox.rightGrid.content");
					}
				}
				break;
			}
		}
		,
		addProduct: function()
		{
			if(! this._selCust)
			{
				UCMS.alert("거래처를 선택하세요.");
				return;
			}
			/*
			if(typeof this._selCust.priceClass != "string" || this._selCust.priceClass.length == 0 )
			{
				UCMS.alert("해당 거래처의 단가 등급을 입력하고 제품을 등록해주세요.");
				return;
			}
			*/

			var prodGridItem = this.attachGridItem("resultBox.rightGrid.content");
			var mappingGridItem = this.attachGridItem("resultBox.centerDownGrid");
			var datas = prodGridItem.getItem().getMultiData();

			if( datas.length == 0 )
			{
				UCMS.alert("추가할 제품을 선택하세요.");
				return;
			}
			Logger.debug("addProduct()");

			for(var i in datas)
			{
				var product = datas[i];
				if( mappingGridItem.getItem().getRowData("salesmanProd", product.salesmanProd)
				.length > 0)
				{
					UCMS.alert("\""+product.salesmanProdname+"\"은 이미 추가된 제품입니다.");
					return;
				}
			}
			for(var i in datas)
			{
				var product = datas[i];
				mappingGridItem.createRow(
					_.extend(
							{
								salesman: this.getQueryData().salesman,
								salesmanCust: this._selCust.salesmanCust,
								priceType: this._selCust.priceType,
								priceClass: this._selCust.priceClass,
							},
							_.pick(product, "salesmanProd", "salesmanProdname")
					));
			}
			this.beginTransaction(false, "resultBox.centerDownGrid");
		}
		,
		removeProduct: function()
		{
			var gridItem = this.attachGridItem("resultBox.centerDownGrid");
			var ids = gridItem.getItem().getSelRowId(true);
			if( ids == null || ids.length == 0 )
			{
				UCMS.alert("삭제할 거래처 제품을 선택해 주세요.");
				return;
			}
			if( typeof ids == 'string' )
			{
				ids = [ids];
			}
			for(var i in ids)
			{
				gridItem.removeRow(ids[i]);
			}
			this.beginTransaction(false, "resultBox.centerDownGrid");
			Logger.debug("removeProduct()");
		}
		,
		getHeaderGridName: function()
		{
			return null;
		}
		,
		onQuery: function()
		{
			var self = this, nDone = 0;

			//
			if( this.isTransactionMode() == true )
			{
				UCMS.alert("진행중인 작업을 완료하세요.");
				return;
			}


			//
			this.attachGridItem("resultBox.centerUpGrid.content").clear();
			this.attachGridItem("resultBox.centerDownGrid").clear();
			this._selCust = null;

			//
			this.showLoading();
			this.fetchingGrid("resultBox.leftGrid.content")
			.then(function(res)
			{
				++nDone;
				if(! res )
				{
					UCMS.reportError(res,"거래처 조회를 실패하였습니다.");
				}
				if( nDone == 2 )
				{
					self.hideLoading();
				}
			});
			this.fetchingGrid("resultBox.rightGrid.content")
			.then(function(res)
			{
				++nDone;
				if(! res )
				{
					UCMS.reportError(res,"제품 조회를 실패하였습니다.");
				}
				if( nDone == 2 )
				{
					self.hideLoading();
				}
			});
		}
		,
		fetchingGrid: function(featureId, options)
		{
			var params = options || this.getQueryData();
			var gridItem = this.getActiveGrid(featureId);

			gridItem.clear();
			return gridItem.fetch( params, featureId )
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					Logger.debug("Fetched grid data count : "+res.extraData.result.length);
					gridItem.setData(res.extraData.result);
					if( res.extraData.result.length > 0 )
					{
						// TODO fetchingGrid 롤백을 위해서 새로 채워진 데이타를 백업한다.
						gridItem.beginTransaction(true);
					}
				}
				return res;
			})
			.fail(function(e)
			{
				UCMS.reportError(e,"데이타 조회를 실패하였습니다.");
			});
		}
		,
		onCancel: function(silent)
		{
			this.endTransaction(true, "resultBox.leftGrid.content");
			this.endTransaction(true, "resultBox.centerDownGrid");
			this.endTransaction(true, "resultBox.rightGrid.content");

			//
			this.onQuery();
			UCMS.alert("취소되었습니다.");
		}
		,
		onSelectRow: function(selectedCust)
		{
			if( this._selCust && this._selCust.salesmanCust == selectedCust.salesmanCust)
			{
				// 중복 조회
				return;
			}
			Logger.debug(selectedCust);
			var titleItem = this.attachGridItem("resultBox.centerUpGrid.content");
			titleItem.clear();
			titleItem.setData(selectedCust);
			this._selCust = selectedCust;

			//
			this.fetchingGrid("resultBox.centerDownGrid", selectedCust)
			.then(function(res)
			{
				if(! res || res.resultCode != 0 )
				{
					UCMS.reportError(res,"거래처 제품 조회에 실패하였습니다.");
				}
				else if( res.extraData.result.length == 0 )
				{
					UCMS.alert("거래처 제품이 없습니다.");
				}
			});
		}
		,
		onSave: function()
		{
			var self = this, nDone = 0, nCnt = 0;
			var done = function()
			{
				if( ++nDone < nCnt )
				{
					return;
				}

				//
				self.hideLoading();
				UCMS.alert("저장이 완료되었습니다.");
			};
			var save = function()
			{
				self.showLoading();
				if( self.getActiveGrid("resultBox.leftGrid.content").getTaskCount() > 0 )
				{
					++nCnt;
					self.saveTransaction("resultBox.leftGrid.content").then(done);
				}
				if( self.getActiveGrid("resultBox.centerDownGrid").getTaskCount() > 0 )
				{
					++nCnt;
					self.saveTransaction("resultBox.centerDownGrid").then(done);
				}
				if( self.getActiveGrid("resultBox.rightGrid.content").getTaskCount() > 0 )
				{
					++nCnt;
					self.saveTransaction("resultBox.rightGrid.content").then(done);
				}
			};

			if( this.isTransactionMode() == true )
			{
				UCMS.confirm("진행중인 작업을 저장할까요?", {confirm:"저장", cancel:"취소"})
				.done(save);
			}
			else
			{
				save();
			}
		}
		,
		saveTransaction: function(featureId)
		{
			var gridItem = this.getActiveGrid(featureId);
			if( gridItem )
			{
				var self = this;
				var apply = function()
				{
					var params = self.getQueryData();
					return gridItem.commit( params, featureId )
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
				if( gridItem.getTaskCount() > 0 )
				{
					return apply();
				}
				else
				{
					Logger.debug("saveTransaction() - Empty task : "+featureId);
				}
			}
			else
			{
				return UCMS.retResolve();
			}
		}
	});

	return Renderer;
});
