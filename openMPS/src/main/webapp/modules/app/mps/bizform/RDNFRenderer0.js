/**
 * Project : OpenMPS MIS
 *
 * 조회폼 1 개,
 * 주종의 관계를 갖는 그리드 2 개로 구성된 업무 화면을 구현한다.
 *
 * 조회폼 리소스의 이름은 "headerBox" 로 식별되며, this._box.query 에 인스턴스가 담겨있다.
 * 조회 결과를 담는 그리드 2개는 "resultBox" 에 담겨 있으며, "leftGrid", "rightGrid" 로 각각의 인스턴스에 접근할 수 있다.
 * _getResultGrid() 에서 그리드에 접근하는 절차가 구현된다.
 *
 */

define([
	"Logger",
	"NDSProps",
	"APIClient",
	"WorkAreaRenderer",
	"WorkAreaHeader",
	"SubContainer",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, APIClient, WorkAreaRenderer, ContentHeader, SubContainer, JQGrid)
{
	var MainMethod =
	{
		initialize: function(options)
		{
			Renderer.__super__.initialize.call(this, options);
		},
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			data["custCode"] = data["custCode"].result;
			return data;
		}
	};

	var Renderer = WorkAreaRenderer.extend
	(
		_.extend({}, MainMethod)

	);

	return Renderer;
});
