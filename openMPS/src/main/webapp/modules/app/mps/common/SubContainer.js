/**
 * Project : OpenMPS MIS
 */

define([
	"Logger",
	"NDSProps",
	"BaroPanelBase",
	"WorkAreaHeader",
	"BaroBox"
], function(Logger, NDSProps, BaroPanelBase, SubHeader, BaroBox)
{	
	/**
	 * 헤더를 갖는 서브 콘텐츠를 담는 위젯을 구현한다.
	 * TODO 서브 콘텐츠의 구동을 위해서 전용 업무 로직이 필요한 경우 본 모듈을 상속 받아서 해당 로직을 구현한다. 
	 */
	var SubContainer = BaroPanelBase.extend(
	{
		template: function(data)
		{
			return "<div class=content_header_region></div><div class=content_region></div>";
		},
		tagName: "div",
		className: "sub_container_box",
		regions:
		{
			header: ".content_header_region",
			content: ".content_region"
		}
		,
		/**
		 * @param {object} options - 헤더와 콘텐츠 파라메터를 제공한다.
		 * 							헤더에서 기능 버튼 클릭시 "subcontainer:event" 이벤트가 외부로 전파된다.
		 * 					{
		 * 						header: {
		 * 							title : {
		 * 								//  기본:"contnet_header_box" 
		 * 								//  옵션: "h35" - 높이 35px 고정 - h35 - title 만 있는데 버튼 있는것과 높이를 맞춰야 하는 경우 
		 * 										 "h25" - 높이 25px 고정 - result box 에서는 기본적으로 높이가 35px로 고정됨. 높이를 25px로 하고 싶으면 사용 
		 * 								//  result box 이외에는 버튼 있으면 35px 타이틀만 있을 경우는 25px 로 보여짐 
		 * 								className:{string}, 
		 * 								label:{string}, 
		 * 								icon:{string},
		 * 								title_type:{string} //"h1~h5" 기본 : h1 
		 * 							},
		 * 							feature : [
		 * 								{string}, ...  사용 가능한 기능 목록
		 * 								또는 사용자 정의 레이블이 필요한 경우
		 * 								{featureId:label}, ... 의 형식으로 지정한다.
		 * 								]
		 * 						},
		 * 						content: {
		 * 							module: "###",	콘텐츠 모듈. 모듈명 지정시 Manifest 모드 또는 위젯 직접 지정 모두 사용 가능
		 * 							options: {} 	콘텐츠 모듈이 갖는 파라메터
		 * 						}
		 * 					}
		 */
		initialize: function(options) 
		{	          
			SubContainer.__super__.initialize.call(this, options);
			
			options.header.title = 
			{
				"className": options.header.title.className || "contnet_header_box", 
				"title_type": options.header.title.title_type || "h2", 
				"icon": options.header.title.icon || "fa-chevron-circle-right",
				"label":  options.header.title.label 
			};
			
			Logger.debug("SubContainer() - ");
			Logger.debug(options);
			
			this._sub = {};
		},
		onRender: function() 
		{
			this._setupHeader();
			this._setupContent();
		}
		,
		_setupHeader: function() 
		{
			Logger.debug("SubContainer._setupHeader()");

			var self = this;
			this._sub.header = new SubHeader(this.getParam("header"));
			this._sub.header.on( BaroPanelBase.WIDGET_EVENT, function(event)
			{
				// 외부로 이벤트 전파
				self.trigger( SubContainer.EVENT, event);
				// 내부에서 이벤트 사용
				self.triggerMethod( SubContainer.CLICK_BUTTON, event );
			});
			this.header.show( this._sub.header );
		}
		,
		_setupContent: function()
		{
			var self = this;
			var params = this.getParam("content");
			
			UCMS.loadModuleByPath([ params.module ])
			.then(function(Module)
			{
				self._sub.content = new (Module.widget ? Module.widget : Module)( params.options );
				// show:complete 이벤트를 발생시키지 않는 콘텐츠로 모듈이 설정되는 경우의 문제를 해결하기 위해서 DOM 이벤트를 사용함 
				//self._sub.content.on("show:complete",
				self._sub.content.getWidget$Element().ready(
				function()
				{
					self.triggerMethod( SubContainer.SHOW_COMPLETE );
				});
				self.content.show( self._sub.content );
				self.initContentEvent();
			});
		}
		,
		/**
		 * content 영역에 접근이 필요한 경우 오버라이딩한다.
		 */
		onShowComplete: function()
		{
		}
		,
		/**
		 * 헤더 버튼이 클릭된 경우 오버라이딩한다.
		 * @param { cmd: {string}, params: {object} } event - 버튼 이벤트 객체
		 */
		onClickButton: function(event)
		{
			Logger.debug(event);
		}
		,
		/**
		 * 콘텐츠 객체를 얻는다.
		 * @return {BaroPanelBase} 초기화가 진행되지 않은 경우 null 을 반환한다.
		 */
		getContent: function()
		{
			return this._sub.content || null;
		}
		,
		getHeader: function()
		{
			return this._sub.header || null;
		}
		,
		/**
		 * BaroBox 와 같은 방식으로 콘텐츠를 얻을 수 있도록 추가
		 * RendererBase.getItemInstance 에 의해 사용된다.
		 * @param {string} part - content, header 중 하나를 지정한다.
		 */
		_getWidgetInstance: function(part)
		{
			return part == "content" ? this.getContent() : this.getHeader();
		}
		,
		/**
		 * content 위젯에서 발생되는 Box 이벤트를 부모 Box 로 전파한다.
		 */
		initContentEvent: function()
		{
			var self = this;
			this._sub.content.on(BaroBox.WIDGET_EVENT, function(event)
			{
				self.trigger(BaroBox.WIDGET_EVENT, event);
			});
		}
	}
	,
	{
		EVENT: "subcontainer:event",
		SHOW_COMPLETE: "show:complete",		// 콘텐츠 영역의 출력이 완료시 이벤트 메소드 호출
		CLICK_BUTTON: "click:button"		// 헤더의 명령 버튼 클릭시 이벤트 메소드 호출
	});

	return SubContainer;
});
