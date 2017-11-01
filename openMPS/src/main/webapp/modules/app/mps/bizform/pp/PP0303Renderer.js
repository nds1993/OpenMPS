/**
 * Project : OpenMPS MIS
 *
 * 생산 > 생산계획서조회/발행(CM)
 *
 */

define([
	"Logger",
	"NDSProps",
	"RDNFRenderer",
	"WorkAreaHeader",
	"WorkAreaRenderer",
	"SubContainer",
	"manifest!jqGrid4-1.0.0"
], function(Logger, NDSProps, RDNFRenderer, WorkAreaHeader, WorkAreaRenderer, SubContainer, JQGrid)
{
	var Renderer = RDNFRenderer.extend(
	{
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "PP0303";
		}
		,
		_initResultGrid: function()
		{
			var self = this;
			UCMS.retry(function()
			{
				var container = self._box.result.getItemElement("upGrid");
				if( container == null )
				{
					return false;
				}

				self._initGrid("downGrid")
				.then(function(grid)
				{
					grid.on
					(
						JQGrid.widget.EVENT.EDITCELL
						,
						function(result)
						{
							Logger.debug("EDITCELL : "+result.id);
							self.triggerMethod(WorkAreaRenderer.EVENT.GRID_EDITCELL, result);
						}
					);
					grid.on(
						JQGrid.widget.EVENT.CLICKBUTTON,
						function(btn)
						{
							self.downloadExcel("PP0303_downGrid_1","생산계회서_CM",false);
						}
					);

				});

				return true;
			});
			return UCMS.retResolve();
		}
		,
		_getHeaderGridName: function()
		{
			return "downGrid";
		}
		,
		_getResultGrid: function(gridID)
		{
			return this._box.result.getItemElement(gridID);
		}
		,
		onQueryMasterGrid: function()
		{
			if (this._box.query == null)
			{
				UCMS.alert("검색 조건이 존재하지 않습니다.");
				return;
			}

			var query = this._getQueryData();
			if (!query) {
				return;
			}

			var self = this;
			this._queryGridData( "upGrid", query, function(res)
			{
				self._setResult(res, "upGrid");
			});
			this._queryGridData( "downGrid", query, function(res)
			{
				self._setResult(res, "downGrid");
				self._backupGrid();
			});
		}
		,
		_initSubHeader: function()
		{
			Logger.debug("Renderer.initSubHeader()");

			var self = this;
			return this._queryHeaderInfo().then(function(headerParams)
			{
				//
				headerParams.options.feature =
				[
					{"query":"조회"}
				];

				Renderer.__super__._initSubHeader.call( self, headerParams );
			});
		}

		/*onCreate: function()
		{
			UCMS.alert("지원하지 않는 기능입니다.");
		}
		,
		onDelete: function()
		{
			UCMS.alert("지원하지 않는 기능입니다.");
		}
		,
		onSave: function()
		{
			UCMS.alert("지원하지 않는 기능입니다.");
		}*/
	});

	return Renderer;
});
