/**
 * Project : OpenMPS MIS
 */

define
(
[
	"BaroPanelBase", "Logger", "NDSProps", "APIClient", "SPA.Platform.Helper"
]
,
function( BaroPanelBase, Logger, NDSProps, APIClient)
{
	var FavHeader = BaroPanelBase.extend(
	{
		template: "#favheader_html",
		className : "favheader_box",
		ui : {
			btn_go_fav : ".fav_btn_box .btn",
			fav_menu_list : ".fav_menu_list",
			fav_group_1 : ".fav_group_1",
			fav_group_2 : ".fav_group_2",
			fav_group_3 : ".fav_group_3",
			fav_menu_list_box : ".fav_menu_list_box",
			btn_go_logout : ".btn_go_logout",
		},
		events : {
			"click @ui.btn_go_fav": "onFavView",
			"click @ui.btn_go_logout": "onLogout"
		},

		initialize: function()
		{
			FavHeader.__super__.initialize.call( this );
        },
		onFavView : function(event){
			var self = this;
			var $hasTab = $(event.currentTarget)
			if($hasTab.hasClass("active")){
				return ;
			} else {
				//console.log(self.ui.fav_menu_list)
				self.ui.btn_go_fav.removeClass("active");
				self.ui.fav_menu_list.removeClass("active");
				$hasTab.addClass("active");
				if($hasTab[0].classList[1].indexOf("1") > 0) {self.ui.fav_group_1.addClass("active");}
				if($hasTab[0].classList[1].indexOf("2") > 0) {self.ui.fav_group_2.addClass("active");}
				if($hasTab[0].classList[1].indexOf("3") > 0) {self.ui.fav_group_3.addClass("active");}
			}
		},
		onLogout: function()
		{
			var logout = function()
			{
				var hosts = NDSProps.get('hosts') || { api: '' };
				var client = new APIClient(
				{
					host: hosts.api,
					systemCode: NDSProps.get('systemCode'),
					contentId: "signbox"
				});
				UCMS.showLoading();
				client.delete()
				.then(function(res)
				{
					NDSProps.set('user', null);
					window.close();
				})
				.fail(function(e)
				{
					UCMS.hideLoading();
					UCMS.reportError(e, "로그아웃 처리 중에 오류가 발생했습니다.<br>관리자에게 문의하세요.");
				});
			};
			UCMS.confirm("로그아웃 하시겠습니까?", {confirm: "로그아웃", cancel: "취소"})
			.then(logout);
		}
	});

	return FavHeader;
});
