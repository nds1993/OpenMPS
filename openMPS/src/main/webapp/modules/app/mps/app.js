/**
 * Project : OpenMPS with NDS, ICT Partners, Netfly, FREECORE
 */

define(
[
	"/app/mps/common/ResourcesDB.js", "BaroAppBase", "Logger", "PlugInManager"
]
, function(OpenMPSRepo, BaroAppBase, Logger, PlugInManager)
{

	var OpenMPSApp = BaroAppBase.extend(
	{
		_initPromise: null,

		/**
		 * 1. 앱 초기화 전에 필요한 준비 동작 수행
		 */
		onInitializeBefore: function(options)
		{
			Logger.debug("onInitializeBefore()");

			//
			// 앱에서 사용할 플러그인을 활성화한다.
			//
			PlugInManager.loadLib("Moment");
			PlugInManager.loadLib("DatetimePickerV4");
			PlugInManager.loadLib("jquery-mCustomScrollbar-3.1.5");
			// formatter 초기화 시점 보다 먼저 로딩되어야 한다.
			PlugInManager.loadLib("jqgrid4-4.7.0");
			
			//
			this.initApplication
			(
				options
				,
				OpenMPSRepo.getResourceMap()
				,
				UCMS.getRootPath()
			);

			this.addRegions(
			{
				body: options.bodyTag
			});
		}
		,
		/**
		 * 2. onBeforeStart() - 앱 시작 전 필요한 초기화 진행
		 */
		onBeforeStart: function(options)
		{
			var self = this;

			this._signBox = null;
			this._mainBox = null;

			//
			this._initPromise = UCMS.loadModuleByPath(
			[
				"NDSProps", "NLSManager", 
				"SPA.Platform.Helper", "moment", "DatetimePickerV4", "TableCSVExport", "jquery-mCustomScrollbar-3.1.5"
			]
			, function(NDSProps, NLSManager)
			{
				NLSManager.getManager().addResource({});

				//
				// openMPS 모듈에서 공유하는 정보를 설정한다.
				//
				NDSProps.init(options);
				NDSProps.set("AppRepo", OpenMPSRepo.getGallery());
				// 등록된 패널 리소스
				NDSProps.set("BoxRepo", _.extend( {}, OpenMPSRepo.getGallery().panel.repo, OpenMPSRepo.getGallery().formPanel.repo ));
				// 인증 정보
				NDSProps.set(
				{
					user: { id: self._params.user, name: self._params.userName },
					token: self._params.token
				});

				return NDSProps;
			});
		}
		,
		/**
		 * 3. 초기화 완료 후 처리 진행
		 */
		onInitializeAfter: function()
		{
			Logger.debug("onInitializeAfter()");
		}
		,
		/**
		 * 4. 앱 동작 시작!
		 */
		onStart: function(options)
		{
			var self = this;

			this._initPromise.then(function(NDSProps)
			{
				self._initRoute(NDSProps);

				// 앱이 시작하면 무조건 home 으로 간다!!!
				Backbone.history.start();
				//location.href = "#home";
			});
		}
		,
		/**
		 * 앱 명령어 처리 라우터 초기화 진행.
		 */
		_initRoute: function(NDSProps)
		{
			var		app = this;
			var		boxRepo = NDSProps.get("BoxRepo");

			Logger.debug("BaangApp._initRoute()");

			this._route = new (Backbone.Marionette.AppRouter.extend(
			{
				routes:
				{
					"" : "onSign",
					"home" : "onSign",
					"main" : "onMain",
					"box/:contentId" : "onShowBox",
					"go/:contentId" : "onShowBoxGo", // 메뉴 자동닫힘 사용 안함.
					"popup/:contentId" : "onPopup",
					"web/:url" : "onWeb",
					"web/:url/:width/:height" : "onWeb"
				},

				onRoute: function( name, path, route )
				{
					Logger.debug("Routing : "+name+", path: "+path+", route: "+route);
				},

				onSign: function()
				{
					if( app._mainBox )
					{
						// history back 으로 인해 다시 인증 화면으로 진입하는 경우,
						// main 화면을 종료한다.
						app._mainBox.close();
						app._mainBox = null;
					}

					UCMS.loadModuleByManifest("SignBox-1.0.0")
					.then(function(Manifest)
					{
						app._signBox = new Manifest.widget({ resource: Manifest.resource });
						app.body.show( app._signBox );

						//
						app._signBox.on(Manifest.widget.EVENT.SUCCESS, function(params)
						{
							if(typeof params.userId == 'string' )
							{
								var url = "?user="+params.userId+"&userName=" + params.userName + "&token=-----";
								url = encodeURI(url);
								window.open(url+"#main", "OpenMPS", "width=1280,height=900,history=no,resizable=yes,status=no,scrollbars=no,menubar=no")
							}
						});
					});
				},
				onMain: function()
				{
					if( !NDSProps.get("user") || !NDSProps.get("user").id )
		        	{
		        		UCMS.alert("사용자 정보를 확인할 수 없습니다.<br>다시 로그인해 주세요.")
		        		.then(function()
		        		{
		        			window.close();
		        		});
		        		return;
		        	}
					if( app._mainBox == null )
					{
						UCMS.createModuleByManifest("MainBox-1.0.0")
						.then(function(box)
						{
							app._mainBox = box;
							app.body.show( box );
							UCMS.onMenuSlide();
						});
					}
					else
					{
						if( app._mainBox.setHome() == false )
						{
							Logger.warn("Invalid moving a page.");
							location.href = UCMS.getRootPath();
						}
					}
				}
				,
				onShowBox: function(contentId, floating)
				{
					if(! app._mainBox )
					{
						Logger.error("onShowBox() - Wrong access step.");
						return;
					}

					var boxInfo = boxRepo[ contentId ] || null;

					Logger.info("onShowBox() - contentId : "+contentId);
					Logger.info(boxInfo);

					if( boxInfo == null )
					{
						UCMS.alert("지원하지 않는 콘텐츠입니다.<br>관리자에게 문의하세요.");
						return;
					}

					var showContent = function(module, options)
					{
						if( module && options )
						{

							app._mainBox.showContent(contentId, module, options);
							//UCMS.onMenuSlide("close");
						}
						else
						{
							UCMS.alert("지원하지 않는 리소스입니다.");
						}
					}

					//$.get( boxInfo.detail, function(boxParams)
					UCMS.loadBoxResource( boxInfo.detail )
					.then(function(boxParams)
					{
						if( floating == true)
						{
							UCMS.loadModuleByPath(["BaroFloating", boxParams.module])
							.then(function(BaroFloating, Module)
							{
								var appName = NDSProps.get("appName");
								boxParams.options["contentId"] = contentId;
								BaroFloating.open(Module, boxParams.options, { title: appName, close: true });
							});
						}
						else
						{
							showContent( boxParams.module, boxParams.options );
						}
					});
				}
				,
				onPopup: function(contentId)
				{
					app._route.onShowBox( contentId, true );
				}
				,
				onWeb: function(url, width, height)
				{
					width || (width = 1280);
					height || (height = 900);
					window.open(url, "OpenMPS", "width="+width+",height="+height+",history=no,resizable=yes,status=no,scrollbars=no,menubar=no");
				}
			}
			));
		}
	});

	return OpenMPSApp;
});
