/**
 * Project : OpenMPS MIS
 *
 * 구매 > 생돈구매세부입력
 *
 */

define([
	"Logger",
	"NDSProps",
	"RendererBase",
	"BaroBox",
	"FormBox",
	"WorkAreaHeader",
	"manifest!jqGrid4-1.0.0#widget",
	"WorkAreaRenderer2"
], function(Logger, NDSProps, RendererBase, BaroBox, FormBox, WorkAreaHeader, JQGrid, WorkAreaRenderer2)
{
	var OverridingMethod =
	{
		constructor: function(options)
		{
			var gridOptions = options.resultBox_1.options.content.options.gridParams;
			_.extend
			(
				gridOptions,
				this.makeAutoScrollingOptions2_local(20)
			);
			gridOptions = options.resultBox_2.options.gridParams;
			_.extend
			(
				gridOptions,
				this.makeAutoScrollingOptions2_local(30)
			);
			Renderer.__super__.constructor.call(this, options);
		}
		,
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "PO0201";
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
					{"download":"엑셀"},
					{"cancel":"취소"}
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
			        "resultBox_1",
			        "resultBox_1.content",
			        "resultBox_2"
			        ];
		}
		,
		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
		getHeaderGridName: function()
		{
			if( this._hisNo )
			{
				// Detail
				return "resultBox_2";
			}
			else
			{
				// Head
				return "resultBox_1.content";
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
			this.attachFormItem("queryBox");
			var headGrid = this.attachGridItem("resultBox_1.content").getItem();

			headGrid.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PO0201_grid_1","생돈구매세부입력_일반정보",false);
			});
			headGrid.on(JQGrid.EVENT.SELECTCELL, function(item)
			{
				Logger.debug("JQGrid.EVENT.SELECTCELL - Editing : "+headGrid.isEditMode());
				Logger.debug(item);

				var data = headGrid.getRowData(item.id);
				self.onSelectRow( data );
			});
			headGrid.on(JQGrid.EVENT.EDITCELL, function(item)
			{
				var data = headGrid.getRowData(item.id);
				self.onEditCell(item, data);
			});
			this.attachGridItem("resultBox_2").getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PO0201_grid_2","생돈구매세부입력_상세정보",false);
			});
		}
	};

	//
	// 5. 이벤트 처리
	// 초기화 완료된 아이템은 getRendererItem() 로 직접 아이템 내부 객체에 접근 가능하다.
	//
	var WorkAreaMethod =
	{
		getQueryData: function()
		{
			var params = this.attachFormItem("queryBox").getItem().getItemData();

			return RendererBase.Method.getQueryData.call( this,
			{
				hisNo: this._hisNo,
				custCode : params.custCode.result
			} );
		}
		,
		onQuery: function(featureId)
		{
		    var self = this;
		    var queryData = this.getQueryData();

		    if( new Date(queryData.strtDate) > new Date(queryData.lastDate) )
		    {
		        UCMS.alert("조회 기간이 잘못 지정되었습니다.");
		        return;
		    }
			this.getRendererItem("resultBox_2").clear();

			//
			featureId || (this._hisNo = undefined);
			Renderer.__super__.onQuery.call( this, featureId );
		}
		,
		/**
		 * 마스터 그리드 행 선택 이벤트
		 */
		onSelectRow: function(params)
		{
			Logger.debug(params);

			var self = this;
			var fetching = function()
			{
				self._hisNo = params.hisNo.trim();
				Logger.debug("onSelectRow() - hisNo : "+self._hisNo);
				self.onQuery("detail");
			};

			if( this.isTransactionMode() == true )
			{
				return UCMS.confirm("진행 중인 작업을 저장할까요?", { confirm: "예", cancel: "아니요" })
				.done(function()
				{
					self.saveTransaction(null, true);
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
		/**
		 * 셀 편집 이벤트
		 * @param {object} item - 셀 정보
		 * 						{
		 * 							id:"row id",
		 * 							cell:
		 * 							{
		 * 								row: iRow, col: iCol, name: cellname, value: value
		 * 							}
		 * 						}
		 * @param {object} rowData - 편집된 행 데이타
		 */
		onEditCell: function(item, rowData)
		{
			var self = this;
			var gridItem = this.attachGridItem("resultBox_1.content");
			if( item.cell.name == "repCustname" )
			{
				// 대표농장
				this.popupBox("codesearch",
				{
					"codeType": "SD0101",
					"keyword": item.cell.value,
					"params": {
						// 농장
						custType: 4
					},
					"callback": function(result)
					{
						self.beginTransaction(true);
						if( result )
						{
							rowData.repCust = result.code;
							rowData.repCustname = result.label;

						}
						else
						{
							rowData.repCust = "";
							rowData.repCustname = "";
						}
						gridItem.setRow( item.id, rowData );
						/*
							_.pick(rowData
									,"custCode"
									,"dochDate"
									, "hisNo"
									, "repCust"
									, "repCustname"
									)
						);
						*/
					}
				});
			}
			else if( item.cell.name == "brandCodename" )
			{
				// 브랜드
				this.popupBox("codesearch",
				{
					"codeType": "TMCOCD10",
					"keyword": item.cell.value,
					"params": {
						"groupCode": "MP007"
					},
					"callback": function(result)
					{
						self.beginTransaction(true);
						if( result )
						{
							rowData.brandCode = result.code;
							rowData.brandCodename = result.label;

						}
						else
						{
							rowData.brandCode = "";
							rowData.brandCodename = "";
						}
						gridItem.setRow( item.id, rowData );
					}
				});
			}
			else
			{
				// 브랜드, 생체중
				this.beginTransaction(true);
				gridItem.setRow( item.id, rowData );
				/*
					_.pick(rowData
							,"custCode"
							,"dochDate"
							, "brandCode"
							, "pigWeig1"
							, "hisNo"
							, "repCust"
							)
				);
				*/
			}
		}
		,
		onSave: function()
		{
			this._hisNo = undefined;
			this.saveTransaction();
		}
		,
		onCancel: function()
		{
			this._hisNo = undefined;
			Renderer.__super__.onCancel.apply( this, arguments );
		}
		,
		isValidRowChecker: function(gridId, taskItem)
		{
			if( gridId == "resultBox_1.content" )
			{
				if( taskItem.pigWeig1 == "0.0" )
				{
					return false;
				}
				return taskItem.repCustname && taskItem.brandCodename ? true : false;
			}
			return true;
		}
	};

	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend({}
			, OverridingMethod
			, WorkAreaMethod
		)
		,
		{
			EVENT:
			{
			}
		}
	);

	return Renderer;
});
