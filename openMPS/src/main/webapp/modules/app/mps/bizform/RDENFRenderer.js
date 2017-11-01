/**
 * Project : OpenMPS MIS
 * 
 * RDENFRenderer
 * Form 은 없고, Result 와 ResultDetail 그리드를 갖는 뷰를 위한 랜더러이다.
 * RDNFRenderer 와 다른 점은 ResultDetail 요소에 대해서만 이벤트 처리가 수행된다.
 * 
 */

define([
	"Logger",
	"NDSProps",
	"RDNFRenderer",
	"WorkAreaHeader"
], function(Logger, NDSProps, RDNFRenderer, ContentHeader)
{	
	// 헤더 버튼 파트
	var HeaderMethod =
	{
		onCreate: function() 
		{
			if(! this._selRowId[this._getHeaderGridName()] )
			{
				UCMS.alert("마스터 아이템을 선택해 주세요.");
				return;
			}
			Logger.info("RDENFRenderer.onCreate()");
			this._createEmptyRow( "rightGrid" );
		}
		,
		onDelete: function() 
		{
			if(! this._selRowId[this._getHeaderGridName()] )
			{
				UCMS.alert("마스터 아이템을 선택해 주세요.");
				return;
			}
			Logger.debug("RDENFRenderer.onDelete()");
			this._deleteGridData( "rightGrid" );
		}
		,
		onSave: function()
		{
			Logger.info("RDENFRenderer.onSave()");

			var self = this;
			this.showLoading();
			this._saveRightGrid()
			.always(function()
			{
				self.hideLoading();
				self._box.header.setButtonMode(ContentHeader.MODE.READY);
			});
		}
	};
	
	// 프로그램 권한 편집에 대한 커스터마이징을 구현한다.
	var RightGridMethod =
	{
	};
	
	var Renderer = RDNFRenderer.extend
	(
		_.extend({}, HeaderMethod, RightGridMethod)
	);
	 
	return Renderer;
});
