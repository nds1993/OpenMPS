/**
 * Project : OpenMPS MIS
 *
 * 생산 > 세트 등록
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
			this._contentId = "PP0104";
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
				//	{"download":"엑셀"},
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
			        "resultBox.setitem",
			        "resultBox.setdetail",
			        "resultBox.setdetail.header",
			        "resultBox.setdetail.content"
			        ];
		}
		,
		getHeaderGridName: function()
		{
			return this._selected == null
				// 세트상품 그리드
				? "resultBox.setitem"
				// 세부 제품 그리드
				: "resultBox.setdetail.content";
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachFormItem("queryBox");
			var gridHead = this.attachGridItem("resultBox.setitem");
			gridHead.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				self.onSelectRow( item );
			});
			gridHead.getItem().on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0104_grid_1","세트등록_세트",false);
			});

			this.attachHeaderItem("resultBox.setdetail.header", function(evt)
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
			var gridDetail = this.attachGridItem("resultBox.setdetail.content");
			gridDetail.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
			{
				// { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
				Logger.debug( item.id );
				Logger.debug( item.cell );
				self.updateProductCell(item);
			});
			gridDetail.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0104_grid_2","세트등록_상품",false);
			});

			//
			this.attachFormItem("queryBox").getItem().getItemElement("setCode")
			.keypress(function(key)
			{
				if(key.which == 13)
				{
					// Enter
					self.onQuery();
				}
			});
		}
		,
		getQueryData: function()
		{
			var params = {};
			if( this._selected )
			{
				params.setCode = this._selected.data.proCode;
			}
			else
			{
				// 세트상품 조회
				params.setYn = "Y";

				var $keyword = this.attachFormItem("queryBox").getItem().getItemElement("setCode");
				if( $keyword.val().length > 0 )
				{
					params.searchKeyword = $keyword.val();
				}
			}

			return params;
		}
		,
		onQuery: function()
		{
			//
			this._selected = null;
			this.attachGridItem("resultBox.setdetail.content").clear();

			//
			Renderer.__super__.onQuery.call( this );
		}
		,
		onSelectRow: function(selected)
		{
			this._selected = selected;
			if( selected )
			{
				Renderer.__super__.onQuery.call( this, "detail", {"params":{"setCode": selected.data.proCode}} );
			}
		}
		,
		onAddItem: function()
		{
			if(! this._selected )
			{
				UCMS.alert("세트 제품을 선택하세요.");
				return;
			}
			this.beginTransaction();
			this.attachGridItem("resultBox.setdetail.content").createRow({"useYn":"Y"});
		}
		,
		onDeleteItem: function()
		{
			Logger.debug("onDeleteItem()");
			if(! this._selected )
			{
				UCMS.alert("세트 제품을 선택하세요.");
				return;
			}
			this.beginTransaction();
			var gridItem = this.attachGridItem("resultBox.setdetail.content");
			var ids = gridItem.getItem().getSelRowId(true);
			for(var i in ids)
			{
				gridItem.removeRow( ids[i] );
			}
		}
		,
		updateProductCell: function(item)
		{
			var gridDetail = this.attachGridItem("resultBox.setdetail.content");
			var rowData = gridDetail.getItem().getRowData( item.id );

			if( item.cell.name == "proCode" )
			{
				var self = this;

				// 제품 코드
				this.popupBox("codesearch",
				{
					// 제품 코드 조회
					"codeType": "PP0103",
					"keyword": item.cell.value,
		            "params": {
		            	"setYn": "N"
		            },
		            "callback": function(res)
		            {
		            	if( res &&
		            		res.code.length > 0 &&
		            		res.label.length > 0 )
		            	{
		            		self.beginTransaction();

		            		//
		            		rowData.proCode = res.code;
		            		rowData.proName = res.label;
		            	}
		            	else
		            	{
		            		// 입력된 값 클리어
		            		rowData.proCode = "";
		            		rowData.proName = "";
		            	}
		            	gridDetail.setRow( item.id, rowData );
		            }
				});
			}
			else
			{
				this.beginTransaction();
				//rowData[item.cell.name] = item.cell.value;
				gridDetail.setRow( item.id, rowData );
			}
		}
		,
		onCancel: function(silent)
		{
			var self = this;
			this.endTransaction(true, this.getHeaderGridName())
			.then(function(cmd)
			{
				if( cmd == null )
				{
					if( typeof silent != "boolean" || silent == false )
					{
						UCMS.alert("취소되었습니다.");
					}
				}
				else if( cmd == "refresh" )
				{
					//
					// Backup Model 을 사용하지 않는 모드. 기존 데이타로 되돌린다.
					//
					self.onSelectRow( self._selected );
				}
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
			if( gridId == "resultBox.setdetail.content" )
			{
				if(! taskItem.proCode )
				{
					return "제품을 입력해 주세요.";
				}
				if( taskItem.setQty == 0 )
				{
					return "수량을 입력해 주세요.";
				}
				return true;
			}
			return true;
		}
		,
		onSave: function()
		{
			this.saveTransaction("detail", false);
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
