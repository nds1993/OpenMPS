/**
 * Project : OpenMPS MIS
 * 
 * 표준 랜더러 v2.0
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
		initialize: function(options)
		{
			Renderer.__super__.initialize.apply( this, arguments );
			RendererBase.Method.initMethod.apply( this, arguments );
			
			this._contentId = options.contentId;
		}
		,
		/**
		 * 1. 헤더 기능 버튼 초기화
		 */
		initSubHeader: function(headerParams) 
		{
			Logger.debug("Renderer.initSubHeader()");
			return RendererBase.Method.initSubHeader.call( this, headerParams );
		}
		,
		/**
		 * 2. 접근이 필요한 아이템 목록 지정
		 */
		getItemList: function()
		{
			return [
			        "queryBox",
			        "formBox",
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
		 * 4. 랜더러 기본 폼 이름을 반환한다.
		 */
		getFormName: function()
		{
			return "formBox";
		}
		,
		/**
		 * 5. 랜더러 아이템 관계성 초기화
		 */
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachFormItem("queryBox");
			
			//
			var formItem = this.attachFormItem("formBox");
			formItem.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData( gridItem, formItem );
			});
			formItem.getItem().disabled(true );
			
			//
			var gridItem = this.attachGridItem("resultBox");
			gridItem.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				self.onSelectRow( item );
			});
		}
	};
	
	//
	// 6. 이벤트 처리
	// 초기화 완료된 아이템은 getRendererItem() 로 직접 아이템 내부 객체에 접근 가능하다.
	//
	var WorkAreaMethod =
	{

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
