/**
 * Project : OpenMPS MIS
 *
 * 생산 > 자재 소요량 산출
 *
 *
 */

define([
	"Logger",
	"NDSProps",
	"RendererBase",
	"NFRenderer",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, RendererBase, NFRenderer, JQGrid)
{
	var Renderer = NFRenderer.extend(
	{
		initialize: function(options)
		{
			Renderer.__super__.initialize.apply( this, arguments );
			RendererBase.Method.initMethod.apply( this, arguments );

			this._contentId = "PP0401";
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
				 	{"compute":"소요량 산출"},
					{"query":"조회"}
				];

				Renderer.__super__.initSubHeader.call( self, headerParams );
			});
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachHeaderItem("headerBox", function(evt)
			{
				Logger.debug(evt.cmd);
				switch(evt.cmd)
				{
				case "content:compute":
					self.computeQuantity();
					break;
				}
			});
			this.attachFormItem("queryBox");
			var gridItem = this.attachGridItem("resultBox");
			gridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
			{
				// 트랜잭션 기록
				var rowData = gridItem.getItem().getRowData(item.id);
				gridItem.setRow(item.id, rowData);
				self.setUpdateMode();
			});
			gridItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0401_grid_1","자재소요량산출",false);
				});

		}
		,
		getQueryData: function(params)
		{
			if( this._quantityParams )
			{
				// 마지막 사용된 조회/산출 파라메터 재사용.
				// "저장" 동작에서 발생될 수 있는 조건
				return _.pick(this._quantityParams, "workDate", "plantCode");
			}
			var queryBox = this.attachFormItem("queryBox").getItem();
			return _.extend(queryBox.getItemData(), params);
		}
		,
		onQuery: function(featureId, options)
		{
			if( this.isTransactionMode() == true )
			{
				UCMS.alert("수정 작업을 완료하세요.");
				return;
			}

			this._quantityParams = null;
			var params = this.getQueryData();
			if( params.plantCode == 0 )
			{
				UCMS.alert("공장그룹을 선택하세요.");
				return;
			}

			// 조회시 사용한 파라메터 보관
			// 수정사항 저장시 사용됨
			this._quantityParams = params;

			Renderer.__super__.onQuery.apply(this, arguments);
		}
		,
		/**
		 * 자재 소요량 산출
		 */
		computeQuantity: function()
		{
			if( this.isTransactionMode() == true )
			{
				UCMS.alert("수정 작업을 완료하세요.");
				return;
			}

			this._quantityParams = null;
			var params = this.getQueryData();
			if( params.plantCode == 0 )
			{
				UCMS.alert("공장그룹을 선택하세요.");
				return;
			}

			// 산출시 사용한 파라메터 보관
			// 수정사항 저장시 사용됨
			this._quantityParams = params;

			var self = this;
			var client = this.getClient();
			if( !client )
			{
				return UCMS.retReject();
			}
			var create = function(forceD)
			{
				self.showLoading();
				if( forceD )
				{
					self._quantityParams.forceD = forceD;
				}
				return client.create( self._quantityParams, "calc" )
				.then
				(
					function(res)
					{
						if( res.resultCode == 0 )
						{
							self.attachGridItem("resultBox").setData(res.extraData.result);
						}
						else if( res.resultCode == -3 )
						{
							// Find not found
							UCMS.alert("\"자재 소요량 산출\"을 위한 데이타를<br>찾을 수 없습니다.");
						}
						else if( res.resultCode == -4 )
						{
							// Dup
							UCMS.confirm("이미 산출된 소요량이 존재 합니다.<br>삭제 후 재산출하시겠습니까?")
							.then(function()
							{
								create("Y");
							});
						}
						else
						{
							UCMS.reportError(res);
						}
					}
					,
					function(err)
					{
						UCMS.reportError(err);
					}
				)
				.always(function()
				{
					self.hideLoading();
				});
			};
			create();
		}
	});

	return Renderer;
});
