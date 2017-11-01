/**
 * Project : OpenMPS MIS
 *
 * 생산계획 추가하기 팝업을 구현한다.
 */

define([
	"Logger",
	"NDSProps",
	"SubContainer",
	"FormBox",
	"RendererBase",
	"manifest!CodeSearch-1.0.0",
	"WorkAreaHeader"
], function(Logger, NDSProps, SubContainer, FormBox, RendererBase, CodeSearch, WorkAreaHeader)
{
	var Renderer = SubContainer.extend(
	{
		/**
		 * @param {object} options
		 * 			{
		 * 				header: {object},
		 * 				content: {object}
		 * 			}
		 */
		initialize: function(options)
		{
			Renderer.__super__.initialize.call(this, options);

			this._deferred = options.deferred || null;
		}
		,
		onShowComplete: function()
		{
			var self = this;
			this.on(SubContainer.EVENT, function(event)
			{
				var cmd = event.cmd;
				var params = event.params;

				switch(cmd)
				{
				case WorkAreaHeader.EVENT.CONFIRM:
					self.onConfirm(params);
					break;
				}
			});
			var form = this.getContent();
			form.on(FormBox.WIDGET_EVENT, function(event)
			{
				self.onEventHandler(event);
			});
		}
		,
		/**
		 * 생산계획 입력
		 */
		onConfirm: function(params)
		{
			var form = this.getContent();
			var result = form.getItemData();

			//
			// 입력된 결과 생성
			//

			this._deferred.resolve(result||{});
		}
		,
		onEventHandler: function(event)
		{
			switch(event.cmd)
			{
			case CodeSearch.widget.EVENT.SEARCH:
				this.onSearchCode(event.params);
				break;
			}
		}
		,
		/**
		 * 코드검색 위젯의 검색 요청을 처리한다.
		 * 선택완료되는 경우 콘텐츠 아이
		 * @param {object} params - { codeType: ##, params: {}, keyword: ##, callback: function(baseCode, keyword, result){} }
		 */
		onSearchCode: function(options)
		{
			RendererBase.Method.popupBox.call(this, "codesearch", options);
		}
	}
	,
	{
	});

	return Renderer;
});
