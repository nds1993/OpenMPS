/**
 * Project : OpenMPS MIS
 *
 * 생산 > 공장-창고맵핑등록
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
			this._contentId = "PP0107";
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
			        "resultBox.rightGrid",
			        "resultBox.rightGrid.header",
			        "resultBox.rightGrid.content"
			        ];
		}
		,
		getHeaderGridName: function()
		{
			return this._selected
				// 창고 그리드
				? "resultBox.rightGrid.content"
				// 공장 그리드
				: "resultBox.leftGrid";
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachFormItem("queryBox");
			var gridHead = this.attachGridItem("resultBox.leftGrid");
			gridHead.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				self.onSelectRow( item );
			});
			gridHead.getItem().on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0107_leftGrid_1","공장_창고맵핑등록_공장",false);
			});

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
				// { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
				Logger.debug( item.id );
				Logger.debug( item.cell );

				if( item.cell.name == "whCode" )
				{
					self.updatePlant(item);
				}
				else if( item.cell.name == "memo" )
				{
					self.updateMemo(item);
				}
				else if( item.cell.name == "useYn" )
				{
					self.updateUseYN(item);
				}
			});
			gridDetail.getItem().on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0107_rightGrid_1","공장_창고맵핑등록_상세",false);
			});
			
			//
			this.$el.find("input.plantCode").focus();
			this.onQuery();
		}
		,
		getQueryData: function()
		{
			var queryBox = this.getRendererItem("queryBox");
			var params = {};
			if( this._selected )
			{
				// 창고 조회용 파라메터
				params.plantCode = this._selected.data.plantCode;
			}
			else
			{
				// 공장 목록 조회용 파라메터
				params.useYn = "Y";
			}
			return _.extend(queryBox.getItemData(), params);
		}
		,
		onQuery: function()
		{
			//
			this._selected = null;
			var gridItem = this.attachGridItem("resultBox.rightGrid.content");
			gridItem.clear();

			//
			Renderer.__super__.onQuery.call( this, "left" )
			.then(function(res)
			{
				/*
				 * TODO 첫번째 항목 자동 선택
				 * id 목록이 조회되지 않아서 비활성화
				var ids = gridItem.getItem().getRowIds();
				if( ids.length > 0 )
				{
					gridItem.getItem().setSelectRow(ids[0], false);
				}
				*/
			});
		}
		,
		onSelectRow: function(selected)
		{
			Logger.debug("onSelectRow()");
			Logger.debug(selected);

			var self = this;
			var fetching = function()
			{
				self._selected = selected;
				Renderer.__super__.onQuery.call( self, "right" );
			};
			if( this.isTransactionMode() == true )
			{
				return UCMS.confirm("진행 중인 작업을 저장할까요?", { confirm: "예", cancel: "아니요" })
				.then(function()
				{
					return self.saveTransaction("right", false);
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
				return fetching();
			}
		}
		,
		onAddItem: function()
		{
			Logger.debug("onAddItem()");
			if(! this._selected )
			{
				UCMS.alert("공장을 선택하세요.");
				return;
			}
			this.beginTransaction();
			var rowId = this.attachGridItem("resultBox.rightGrid.content").createRow({"useYn":"Y"}, "last");
			// 창고 입력 셀 선택
			$("#"+rowId+" .codesearch").click();
		}
		,
		onDeleteItem: function()
		{
			Logger.debug("onDeleteItem()");
			if(! this._selected )
			{
				UCMS.alert("공장을 선택하세요.");
				return;
			}
			this.beginTransaction();
			var gridItem = this.attachGridItem("resultBox.rightGrid.content");
			gridItem.removeRow( gridItem.getItem().getSelRowId() );
		}
		,
		updatePlant: function(item)
		{
			Logger.debug("updatePlant()");
			var self = this;
			var gridItem = this.attachGridItem("resultBox.rightGrid.content");
			
			// 팝업 콜백 내에서 행 데이타를 읽으면,
			// 편집중인 셀의 내용이 읽히지 않는 문제가 발생된다.
			// 탭 키로 셀 편집을 완료한 경우 다음 셀에서 이런 문제가 발견되었음.
			// 그래서 팝업 전에 데이타를 읽도록 변경
			var rowData = gridItem.getItem().getRowData( item.id );
			Logger.debug( rowData );
			this.popupBox("codesearch",
			{
				// 창고 코드 조회
				"codeType": "TMCOCD10",
				"keyword": item.cell.value,
	            "params": {
	            	"groupCode": "SDDC"
	            },
	            "callback": function(res)
	            {
	            	if( res &&
	            		res.code.length > 0 &&
	            		res.label.length > 0 )
	            	{
	            		self.beginTransaction();
	            		rowData.whCode = res.code;
	            		rowData.dcnm = res.label;
	            	}
	            	else
	            	{
	            		// 입력된 값 클리어
	            		rowData.whCode = "";
	            		rowData.dcnm = "";
	            	}
	            	gridItem.setRow( item.id, rowData );
	            }
			});
		}
		,
		updateMemo: function(item)
		{
			Logger.debug("updateMemo()");
			this.beginTransaction();
			this.addTransaction(item);
		}
		,
		updateUseYN: function(item)
		{
			Logger.debug("updateUseYN()");
			this.beginTransaction();
			this.addTransaction(item);
		}
		,
		/**
		 * 변경된 창고 정보를 저장한다.
		 */
		onSave: function()
		{
			var self = this;
			Renderer.__super__.saveTransaction.call( this, "right", false )
			.then(function(data)
			{
				self.onCancel(true);
				Renderer.__super__.onQuery.call( self, "right" );
			});
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
			if( gridId == "resultBox.rightGrid.content" )
			{
				return ( taskItem.whCode && taskItem.dcnm ) ? true : "유효하지 않은 값이 입력되었습니다.";
			}
			return true;
		}
		,
		addTransaction: function(item)
		{
			var gridItem = this.attachGridItem("resultBox.rightGrid.content");
			var rowData = gridItem.getItem().getRowData( item.id );
    		rowData[ item.cell.name ] = item.cell.value;
    		gridItem.setRow( item.id, rowData );
		}
		,
		onCancel: function(silent)
		{
			var self = this;
			this.endTransaction(true, this.getHeaderGridName())
			.then(function(cmd)
			{
				self._newRowId = null;
				if( typeof silent != "boolean" || silent == false )
				{
					UCMS.alert("취소되었습니다.");
				}
				self.onSelectRow( self._selected );
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
