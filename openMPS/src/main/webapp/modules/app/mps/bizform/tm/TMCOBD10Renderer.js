/**
 * Project : NDS MPS
 * 
 * 게시판 관리 화면을 구현한다.
 * ID : TMCOBD30
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
	}
	
	var WorkAreaMethod = 
	{
			
	};
	
	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend(OverridingMethod, WorkAreaMethod)
	);
	 
	return Renderer;
});
