/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define(
[
	"/common/ResourcesDB.js", "BaroAppBase", "BaroProps", "Logger"
]
, function(Repo, BaroAppBase, BaroProps, Logger)
{
	var TPStudioBase = BaroAppBase.extend(
	{
		onInitializeBefore: function(options)
		{
			Logger.debug("onInitializeBefore()");

			var self = this;
			var repoPath = this._params["db"] || null;

			BaroProps.set("repoPath", repoPath);

			if( repoPath != null )
			{
				UCMS.loadModuleByPath([ repoPath ], function(outerDB)
				{
					self._initApplication( options, outerDB );
				}
				,
				function()
				{
					UCMS.alert("제공된 Repo 경로가 잘못되었습니다!<br>기본 DB 로 시작합니다!")
					.then(function()
					{
						self._initApplication( options );
					});
				});
			}
			else
			{
				this._initApplication( options );
			}
		}
		,
		_initApplication: function(options, outerDB)
		{
			this.initApplication
			(
				options
				,
				outerDB ? _.extend({}, Repo.getResourceMap(), outerDB.getResourceMap()) : Repo.getResourceMap()
				,
				UCMS.getRootPath()
			);

			this.addRegions(
			{
				body: options.bodyTag
			});

			this._initRoute( options );
			this._initUI( options );

			//
			UCMS.loadModuleByPath(["PlugInManager", "NLSManager", "SPA.Platform.Helper"], function(PlugInManager, NLSManager)
			{
				//
				// 앱에서 사용할 플러그인을 활성화한다.
				//
				PlugInManager.loadLib("Moment");
				PlugInManager.loadLib("DatetimePicker");
				PlugInManager.loadLib("jquery-mCustomScrollbar-3.1.5");
				NLSManager.getManager().addResource({});
			});
		}
		,
		_initRoute: function( options )
		{
			var		self = this;

			Logger.debug("BaangApp._initRoute()");

			this._route = new (Backbone.Marionette.AppRouter.extend(
			{
				routes:
				{
					"home" : "goHome"
				},

				onRoute: function( name, path, route )
				{
					Logger.debug("Routing : "+name+", path: "+path+", route: "+route);
				},

				goHome: function()
				{
					UCMS.hideLoading();
				}
			}
			));
		}
		,
		onBeforeStart: function(options)
		{

		}
		,
		onStart: function()
		{
			Backbone.history.start();
		}
	});

	return TPStudioBase;
});
