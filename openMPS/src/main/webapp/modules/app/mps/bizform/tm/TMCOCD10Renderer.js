/**
 * Project : openMPS
 *
 * 코드 관리
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
			this._contentId = "TMCOCD10";
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
					{"delete":"삭제"},
					{"cancel":"취소"}
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
			        "resultBox.leftGrid",
			        "resultBox.leftGrid.content",
			        "resultBox.rightGrid",
			        "resultBox.rightGrid.header",
			        "resultBox.rightGrid.content"
			        ];
		}
		,
		getHeaderGridName: function()
		{
			return this._selected ? "resultBox.rightGrid.content" : "resultBox.leftGrid.content";
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;

			//
			this.attachFormItem("queryBox").getItem().getItemElement("searchKeyword")
			.keypress(function(key)
			{
				if(key.which == 13)
				{
					// Enter
					self.onQuery();
				}
			});
			
			//
			var gridHead = this.attachGridItem("resultBox.leftGrid.content");
			gridHead.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				self.onSelectRow( item );
			});
			gridHead.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
			{
				gridHead.setRow(item.id, gridHead.getItem().getRowData(item.id));
				self.beginTransaction();
			});

			//
			this.attachHeaderItem("resultBox.rightGrid.header", function(evt)
			{
				switch(evt.cmd)
				{
				case "content:create":
					self.onAddItem();
					break;

				case "content:delete":
					self.onDeleteItem();
					break;
				}
			});
			var gridDetail = this.attachGridItem("resultBox.rightGrid.content");
			gridDetail.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
			{
				gridDetail.setRow(item.id, gridDetail.getItem().getRowData(item.id));
				self.beginTransaction();
			});
			
			self.$el.find(".leftGrid_region").on( "click", function() {
				  self.onLeftGridChange();
			});
			

			self.$el.find(".leftGrid_region").on( "keyup", function() {
				self.onLeftGridChange();
			});
			
		}
		,onLeftGridChange: function() {
			var self = this;
			var gridHead = this.attachGridItem("resultBox.leftGrid.content");
			
			if (this._currentLeftGridRowId == null) {				
				this._currentLeftGridRowId = gridHead.getItem().getSelRowId();
				this._currentLeftGridRowData = gridHead.getItem().getRowData(this._currentLeftGridRowId);
				
				this._currentLeftGridRowData.groupCode = self.$el.find("#"+this._currentLeftGridRowId +"_groupCode").val();
				this._currentLeftGridRowData.groupCdnm = self.$el.find("#"+this._currentLeftGridRowId +"_groupCdnm").val();
			} else if (this._currentLeftGridRowId != gridHead.getItem().getSelRowId()) {
				
				// 이전 데이터 비교
				var prevRowData = this._currentLeftGridRowData;
				var currRowData = gridHead.getItem().getRowData(this._currentLeftGridRowId);
				
				if ( prevRowData.groupCode != currRowData.groupCode || prevRowData.groupCdnm != currRowData.groupCdnm || prevRowData.useYn != currRowData.useYn) {
					
					gridHead.setRow(self._currentLeftGridRowId, currRowData);
					self.beginTransaction();
					
					UCMS.confirm("진행중인 작업을 저장할까요?", {confirm:"저장", cancel:"취소"}).then(
						function()
						{
							self.saveTransaction("resultBox.leftGrid.content", gridHead.getTaskList());
						}							
					);
				}
				
				// 선택 데이터 변경
				this._currentLeftGridRowId = gridHead.getItem().getSelRowId();
				this._currentLeftGridRowData = gridHead.getItem().getRowData(this._currentLeftGridRowId);
				
				this._currentLeftGridRowData.groupCode = self.$el.find("#"+this._currentLeftGridRowId +"_groupCode").val();
				this._currentLeftGridRowData.groupCdnm = self.$el.find("#"+this._currentLeftGridRowId +"_groupCdnm").val();
			}
			else {
				// 이전 데이터 비교
				var prevRowData = this._currentLeftGridRowData;
				var currRowData = gridHead.getItem().getRowData(this._currentLeftGridRowId);
				
				currRowData.groupCode = self.$el.find("#"+this._currentLeftGridRowId +"_groupCode").val();
				currRowData.groupCdnm = self.$el.find("#"+this._currentLeftGridRowId +"_groupCdnm").val();
				
				if ( prevRowData.groupCode != currRowData.groupCode || prevRowData.groupCdnm != currRowData.groupCdnm || prevRowData.useYn != currRowData.useYn) {
					self.beginTransaction("resultBox.leftGrid.content");
					/*
					var headTask = gridHead.getTaskList();
					if ( headTask.length == 0 ) {
						gridHead.setRow(this._currentLeftGridRowId, currRowData);
						self.beginTransaction();
					} else if(headTask[0].dsType == 'U') {
						gridHead.setRow(this._currentLeftGridRowId, currRowData);
						self.beginTransaction();
					}
					*/
				}
			}
		}
		,
		getQueryData: function(gridId)
		{
			var params = this.attachFormItem("queryBox").getData();
			if(gridId == "resultBox.rightGrid.content" && this._selected)
			{
				// 우측 그리드 동작시에만 사용됨
				params["groupCode"] = this._selected.data.groupCode;
			}
			return params;
		}
		,
		onQuery: function()
		{
			//
			this._selected = null;
			this._currentLeftGridRowData = null;
			this._currentLeftGridRowId = null;
			this.attachGridItem("resultBox.rightGrid.content").clear();

			//
			Renderer.__super__.onQuery.call( this );
		}
		,
		onSelectRow: function(selected)
		{
			var leftgridItem = this.attachGridItem("resultBox.leftGrid.content");
			var selectedRow = leftgridItem.getItem().getSelRowId();
			var rowData = leftgridItem.getItem().getRowData( selectedRow );
			
			selected || ( selected = this._selected );
			if( rowData && rowData.groupCode )
			{
				this._selected = selected;
				Renderer.__super__.onQuery.call( this, null, 
				{
					"params":
					{
						"groupCode": selected.data.groupCode
					}
				});
			}
		}
		,
		onAddItem: function()
		{
			/*
			var leftgridItem = this.attachGridItem("resultBox.leftGrid.content");
			var selectedRow = leftgridItem.getItem().getSelRowId();
			var rowData = leftgridItem.getItem().getRowData( selectedRow );
			*/
			var rowData = this._selected.data;
			if(! rowData )
			{
				UCMS.alert("그룹 코드를 선택하세요.");
				return;
			}		
			
			this.beginTransaction();
			var gridItem = this.attachGridItem("resultBox.rightGrid.content");
			gridItem.createRow({groupCode: rowData.groupCode, useYn: true});
		}
		,
		onDeleteItem: function()
		{
			Logger.debug("onDeleteItem()");
			if(! this._selected )
			{
				UCMS.alert("그룹 코드를 선택하세요.");
				return;
			}
			this.beginTransaction();
			var gridItem = this.attachGridItem("resultBox.rightGrid.content");
			var rowId = gridItem.getItem().getSelRowId();
			if( rowId == -1 )
			{
				UCMS.alert("삭제할 행을 선택해 주세요.");
				return;
			}
			gridItem.removeRow( rowId );
			
			/*
			var counter = gridItem.getTaskCounter();
			if( counter.create == 0 && counter.delete == 0 && counter.update == 0 )
			{
				this.endTransaction("resultBox.rightGrid.content");
				// 왼쪽 그리드의 트랜잭션 개수도 확인해야 함.
			}
			*/
		}
		,
		onCreate: function()
		{
			if( this.isTransactionMode() == true )
			{
				UCMS.alert("작업 내용을 먼저 저장해 주세요.");
				return;
			}
			var gridItem = this.attachGridItem("resultBox.leftGrid.content");
			gridItem.createRow({useYn: "Y"});
			this.beginTransaction();
			this.attachGridItem("resultBox.rightGrid.content").clear();
		}
		,
		onCancel: function(silent)
		{
			this.endTransaction("resultBox.leftGrid.content");
			this.endTransaction("resultBox.rightGrid.content");
			this.onQuery();
		}
		,
		/**
		 * Task commit 시 데이타의 유효성을 검증한다.
		 * 기본적으로 true 를 반환하며, 검증이 필요한 랜더러에서 상속 받아서 제공되는 Task 정보의 유효성 검증 결과를 boolean 값으로 반환처리한다.
		 * @param {string} gridId - commit 이 발생된 그리드의 식별자
		 * @param {object} taskItem - 트랜잭션 항목 객체
		 * @return {boolean} false 를 리턴하면, 해당 commit 동작은 reject 되며, 문제가 된 task 항목에 대한 정보 { id, task } 가 반환된다.
		 */
		isValidRowChecker: function(gridId, taskItem)
		{
			if( gridId == "resultBox.leftGrid.content" )
			{
				if(! taskItem.groupCode )
				{
					return "그룹코드를 입력해 주세요.";
				}
				if( taskItem.groupCdnm == 0 )
				{
					return "코드명을 입력해 주세요.";
				}
				return true;
			}
			else if( gridId == "resultBox.rightGrid.content" )
			{
				if(! taskItem.codeId )
				{
					return "세부코드를 입력해 주세요.";
				}
				if(! taskItem.codeName )
				{
					return "세부코드명을 입력해 주세요.";
				}
				return true;
			}
			return true;
		}
		,
		onSave: function()
		{
			if( this.isTransactionMode() == false )
			{
				UCMS.alert("저장할 작업이 없습니다.");
				return;
			}
			
			var self = this, p;
			var headGrid = this.getActiveGrid("resultBox.leftGrid.content");
			var headTask = headGrid.getTaskList();
			
			if( headTask.length == 0 ) {
				var selectedRow = headGrid.getItem().getSelRowId();
				var RowData = headGrid.getItem().getRowData(headGrid.getItem().getSelRowId());
				RowData.groupCode = self.$el.find("#"+selectedRow+"_groupCode").val();
				RowData.groupCdnm = self.$el.find("#"+selectedRow+"_groupCdnm").val();
				headGrid.setRow(selectedRow, RowData);
				headTask = headGrid.getTaskList();
			}
			
			if( headTask.length > 0 )
			{
				p = UCMS.confirm("진행중인 작업을 저장할까요?", {confirm:"저장", cancel:"취소"})
				.then(function()
				{
					
					var leftgridItem = self.attachGridItem("resultBox.leftGrid.content");
					var selectedRow = leftgridItem.getItem().getSelRowId();
					
					if (headTask[0].dsType == "C") {
						headTask[0].groupCode = self.$el.find("#"+selectedRow+"_groupCode").val();
						headTask[0].groupCdnm = self.$el.find("#"+selectedRow+"_groupCdnm").val();
					}
					
					return self.saveTransaction("resultBox.leftGrid.content", headTask);	
				});
			}

			var detailGrid = this.getActiveGrid("resultBox.rightGrid.content");
			var detailTask = detailGrid.getTaskList();
			if( detailTask.length > 0 )
			{
				if(! p )
				{
					p = UCMS.confirm("진행중인 작업을 저장할까요?", {confirm:"저장", cancel:"취소"});
				}
				
				p.then(function()
				{
					return self.saveTransaction("resultBox.rightGrid.content", detailTask)
					.then(function(res)
					{
						if( res && res.resultCode == 0 )
						{
							UCMS.alert("모두 저장되었습니다.");
							self.onSelectRow();
						}
					});
				});
			}
			else
			{
				// 헤드 그리드만 변경된 경우
				p.then(function(res)
				{
					if( res && res.resultCode == 0 )
					{
						UCMS.alert("모두 저장되었습니다.");
						self.onQuery();
					}
				});
			}
		}
		,
		saveTransaction: function(gridId, taskList)
		{			
			
			if(gridId == null) {
				return;
			}
			
			var gridItem = this.getActiveGrid(gridId);
			if( gridItem )
			{
				if( taskList )
				{
					var params = this.getQueryData(gridId), self = this;
					
					if(gridId == "resultBox.rightGrid.content"){
						/*
						*/	
						var leftgridItem = this.attachGridItem("resultBox.leftGrid.content");
						var selectedRow = leftgridItem.getItem().getSelRowId();
						var rowData = leftgridItem.getItem().getRowData( selectedRow );
						params["groupCode"] = rowData.groupCode;
						params["groupCode"] = this._selected.data.groupCode;
					}
				
					this.showLoading();
					return gridItem.commit( params, null, taskList, this.isValidRowChecker )
					.then(function(res)
					{
						self.hideLoading();
						
						//
						if( res.resultCode != 0 )
						{
							if( res.resultCode == -4 )
							{
								UCMS.alert("이미 저장된 데이타가 존재합니다.");
							}
							else
							{
								UCMS.reportError(res);
							}
						}
						else
						{
							self.endTransaction();
						}
						return res;
					})
					.fail(function(e)
					{
						self.hideLoading();
						UCMS.reportError(e);
					});
				}
				else
				{
					UCMS.alert("저장할 데이타가 존재하지 않습니다.");
					return UCMS.retReject();
				}
			}
			else
			{
				return UCMS.retReject();
			}
		}
		,
		onDelete: function()
		{
			var gridItem = this.getActiveGrid("resultBox.leftGrid.content");
			if(! gridItem)
			{
				Logger.info("onDelete() - Find not found a grid item.");
				return UCMS.retReject();
			}
			var counter = gridItem.getTaskCounter();
			if( counter.create != 0 )
			{
				UCMS.alert("신규 항목의 등록 완료후 다시 시도해 주세요.");
				return UCMS.retReject();
			}

			var self = this;
			var grid = gridItem.getItem();
			var selId = grid.getSelRowId(false);
			if( selId == -1 )
			{
				UCMS.alert("선택된 행이 없습니다.");
				return UCMS.retReject();
			}
			
			var remove = function()
			{
				var deleteTasks = [];
				var rowData = grid.getRowData( selId );
				
				rowData.groupCode = self.$el.find("#"+selId+"_groupCode").val();
				rowData.groupCdnm = self.$el.find("#"+selId+"_groupCdnm").val();
				
				if( rowData )
				{
					rowData.dsType = "D";
					deleteTasks.push( rowData );
				}
				else
				{
					Logger.warn("onDelete() - Invalid row Id : "+selId);
				}

				self.showLoading();
				return self.sendTrasction(null, self.getQueryData(), deleteTasks)
				.then(function(res)
				{
					if( res.resultCode == 0 )
					{
						UCMS.alert("삭제되었습니다.");
						grid.removeRow( selId );
						self.getActiveGrid("resultBox.rightGrid.content").clear();
					}
					else
					{
						UCMS.reportError(res);
					}
					
					return res;
				})
				.fail(function(err)
				{
					UCMS.reportError(err);
				})
				.always(function()
				{
					self.hideLoading();
				});
			};
			return UCMS.confirm("선택된 항목을 삭제하시겠습니까?", {confirm:"삭제", cancel:"취소"})
			.then(remove);
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