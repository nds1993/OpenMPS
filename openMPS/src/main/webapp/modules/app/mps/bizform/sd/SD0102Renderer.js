/**
 * Project : OpenMPS MIS
 *
 * SD 영업 > 거래처관리 > 거래처 제품
 * SD0102
 *
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaRenderer2",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, WorkAreaRenderer2, JQGrid4)
{
	var Renderer = WorkAreaRenderer2.extend(
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "SD0102";
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
 				Renderer.__super__.initSubHeader.call( self, headerParams );
 			});
 		}
		,
		getItemList: function()
		{
			return [
					"headerBox",
					"queryBox",
			        "resultBox"
			        ];
		}
		,
		getHeaderGridName: function()
		{
			return "resultBox";
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachHeaderItem("headerBox");
			this.attachFormItem("queryBox");

			//
			var gridItem = this.attachGridItem("resultBox");
			gridItem.getItem()
			.on(JQGrid4.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0102_grid_1","거래처제품",false);
			});


			gridItem.getItem().on(JQGrid4.EVENT.BEFOREEDITCELL, function(item)
			{
				if( item.cell.name == "proCode" )
				{
					self.onSearchCode(
					{
						// 제품 검색
						codeType: "PP0103",
						params: {},
						keyword: item.cell.value,
						callback: function(selected)
						{
							if(!selected)
							{
								Logger.debug("Canceled to edit a cell. rowId : "+item.id);
								self._selProduct = null;
								return;
							}

							$("#"+item.id+" input[name=proCode]").val(selected.code);
							self._selProduct = {
								proCode : selected.code,
								proName : selected.label
							};
							self.beginTransaction();
						}
					});
				}
			});
			gridItem.getItem().on(JQGrid4.EVENT.EDITCELL, function(item)
			{
				Logger.debug("EDITCELL : "+item.id);
				var rowData = gridItem.getItem().getRowData( item.id );
				if( item.cell.name == "proCode" && self._selProduct )
				{
					// 선택된 제품 적용
					rowData.proCode = self._selProduct.proCode;
					rowData.proName = self._selProduct.proName;
				}
				gridItem.setRow( item.id, rowData );
				self.beginTransaction();
			});
		}
		,
		getQueryData: function()
		{
			var data = this.getRendererItem("queryBox").getItemData();
			data.custCode || (data.custCode={});
			return {"custCode":data.custCode.result};
		}
		,
		onCreate: function()
		{
			var data = this.getQueryData();
			if( !data.custCode )
			{
				UCMS.alert("추가할 제품의 거래처를 지정해 주세요");
				return;
			}

			Renderer.__super__.onCreate.call( this );

			var gridItem = this.getActiveGrid();
			var rowId = gridItem.getItem().getSelRowId();
			gridItem.setRow(rowId, {"custCode": data.custCode, "corpCode": NDSProps.get('corpCode')});
		}
	});

	return Renderer;
});
