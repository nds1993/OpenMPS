/**
 * Project : OpenMPS MIS
 */

define
(
[
	"BaroPanelBase", "NDSProps"
]
,
function( BaroPanelBase, NDSProps )
{
	var MainHome = BaroPanelBase.extend(
	{
		className:"dashboard_box",
				
		initialize: function()
		{
			if(NDSProps.get("systemCode") == 'PORTAL')
			{
				this.template = "#portalhome_html";
			}
			else
			{
				this.template = "#mainhome_html";
			}
			MainHome.__super__.initialize.call( this );
        }
	});
	
	return MainHome;
});