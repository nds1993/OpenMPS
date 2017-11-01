/**
 * Project : OpenMPS MIS
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
(
[
	"BaroPanelBase", "Logger", "NDSProps", "APIClient", "SPA.Platform.Helper"
]
,
function( BaroPanelBase, Logger, NDSProps, APIClient)
{
	/**
	 * 인증 페이지를 구현한다.
	 * 인증 성공한 경우 SignBox.EVENT.SUCCESS 이벤트가 발생된다.
	 * SignBox.EVENT.SUCCESS 이벤트 발생시 파라메터로 { userId: "" } 가 전달된다.
	 */
	var SignBox = BaroPanelBase.extend(
	{
		template: '#sign_html',
		className: 'login_box',
		ui:
		{
			user_id: 'input[name=userId]',
			user_pwd: 'input[name=pwd]',
			btn_login: '.btn_go_login',
			select_label : '.select_design > .select_label',
			select : '.select_design select',
		},
		events :
		{
			"click @ui.btn_login": "onLogin",
			"keyup @ui.user_pwd": "onInputPassword"
		}
		,
		initialize: function(options)
		{
			SignBox.__super__.initialize.apply(this, arguments);
        }
		,
		onLogin: function()
		{
			var self = this;
			var userId = this.ui.user_id.val();
			if( userId.length == 0 )
			{
				UCMS.alert("사용자 ID 를 입력하세요.")
				.then(function()
				{
					self.ui.user_id.focus();
				});
				return;
			}

			var userPwd = this.ui.user_pwd.val();
			if( userPwd.length == 0 )
			{
				UCMS.alert("비밀번호를 입력하세요.")
				.then(function()
				{
					self.ui.user_pwd.focus();
				});
				return;
			}
			
			var client = this._getClient();
			var reset = function()
			{
				self.ui.user_id.val('');
				self.ui.user_pwd.val('');
				UCMS.alert("로그인 계정을 확인하시고<br>다시 시도해 주세요.");
			};
			var user =
			{
				id: userId,
				pwd: Sha256.hash(userPwd)
			};
			client.create( user )
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					//NDSProps.set('user', user);
					NDSProps.set('user', res.extraData.user);
					self.trigger(SignBox.EVENT.SUCCESS, { "userId": userId, "userName": res.extraData.user.name});
				}
				else
				{
					reset();
				}
			}
			,
			reset);
		}
		,
		onShow: function(){
			var self = this;

			// 처음 로딩할 때 한번 값을 설정함.
			this.onSelect();

			// 바뀔대 마다 값을 설정함.
		    this.ui.select.change(function(){
		    	self.onSelect();
	    	});

		}
		,
		onSelect: function(){
	        var select_name = this.ui.select.children("option:selected").text();
	        this.ui.select_label.text(select_name);
		}
		,
		onInputPassword: function(e)
		{
			if(e.keyCode == 13)
			{
		        this.onLogin();
		    }
			else
			{
				Logger.debug("ID : "+this.ui.user_id.val());
				Logger.debug("PWD : "+this.ui.user_pwd.val());
			}
		}
		,
		_getClient: function()
		{
			var hosts = NDSProps.get('hosts') || { api: '' };
			return new APIClient(
			{
				host: hosts.api,
				systemCode: NDSProps.get('systemCode'),
				contentId: "signbox"
			});
		}
	}
	,
	{
		EVENT:
		{
			SUCCESS: "sign:success"	// 파라메터 { userId: {string} }
		}
	});

	return SignBox;
});
