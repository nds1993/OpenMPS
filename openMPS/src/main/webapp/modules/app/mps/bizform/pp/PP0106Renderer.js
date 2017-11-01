/**
 * Project : OpenMPS MIS
 *
 * 생산 > BOM 조회
 *
 */

define([
	"Logger",
	"NDSProps",
	"RendererBase",
	"BaroBox",
	"FormBox",
	"WorkAreaHeader",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, RendererBase, BaroBox, FormBox, WorkAreaHeader, JQGrid)
{
	var OverridingMethod =
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			RendererBase.Method.initMethod.apply( this, arguments );

			//
			this._contentId = "PP0106";
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
					{"query":"조회"}
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
			        "resultBox"
			        ];
		}
		,
		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
		getHeaderGridName: function()
		{
			return "resultBox";
		}
		,
		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachFormItem("queryBox").getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				if( item.id == "proCode" )
				{
					self.onQuery();
				}
			});
			this.attachGridItem("resultBox").getItem().on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0106_grid_1","BOM_조회",false);
			});
		}
	};

	//
	// 5. 이벤트 처리
	// 초기화 완료된 아이템은 getRendererItem() 로 직접 아이템 내부 객체에 접근 가능하다.
	//
	var WorkAreaMethod =
	{
		onQuery: function(featureId)
		{
			var params = this.getQueryData();
			if( params.proCode.result.length == 0 )
			{
				UCMS.alert("제품 정보를 입력하세요.");
				return;
			}
			RendererBase.Method.onQuery.call( this, featureId );
		}
	};

	var Renderer = BaroBox.extend
	(
		_.extend({}
			, RendererBase.Method
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
