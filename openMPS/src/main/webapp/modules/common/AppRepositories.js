/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define
([
	"Logger",
	"BaroPanelBase"
]
,
function( Logger, BaroPanelBase )
{
	/**
	 * 지정된 리소스에 포함된 리소스 목록을 제공한다.
	 * 리소스가 선택되면 이벤트 AppResourceList.EVENT.SELECT 가 발생된다.
	 * 이벤트 AppResourceList.EVENT.SELECT 는 { moduleId: "" } 의 파라메터로 선택된 모듈 정보를 반환한다.
	 */
	var AppResourceList = UCMS.SPA.Widget.extend(
	{
		clasaName: "tab-pane fade"
		,
		getTemplate: function()
		{
			return function()
			{
				return "<ul class=list-group></ul>";
			}
		}
		,
		ui: 
		{
			"list": ".list-group",
			"moduleId": "h4"
		}
		,
		events:
		{
			"click @ui.moduleId": "onSelectModule"
		}
		,
		/**
		 * 리소스 목록을 초기화한다.
		 * @param {object} options - { resources: {} }
		 */
		initialize: function(options)
		{
			AppResourceList.__super__.initialize.call(this, options);
			this._resources = options.resources || {};
		}
		,
		onRender: function()
		{
			for(var widget in this._resources)
			{
				var item = null;
				var resource = this._resources[widget];
				if( typeof resource == 'string' )
				{
					item = "<li class='list-group-item'><h4>"+widget+"</h4><small>"+resource+"</small></li>";
				}
				else
				{
					item = "<li class='list-group-item'><h4>"+widget+"</h4><small>"+resource['desc']+"</small></li>";
				}
				this.ui.list.append(item);
			}
		}
		,
		onSelectModule: function(evt)
		{
			var moduleId = evt.target.textContent;
			this.trigger(AppResourceList.EVENT.SELECT, {"moduleId": moduleId,"item":this._resources[moduleId]});
		}
		,
		onClose: function()
		{
			Logger.debug("AppResourceList::onClose()");
		}
	}
	,
	{
		EVENT: {
			SELECT: "selected"
		}
	});
	
	/**
	 * 지정한 저장소의 앱 리소스를 구현한다.
	 */
	var AppRepositories = UCMS.SPA.Panel.extend(
	{
		getTemplate: function()
		{
			var self = this;
			return function() {
				var html = "<ul class='nav nav-tabs'></ul><div class=tab-content></div>";
				return self._useClose == true 
					? "<button class='btn btn-danger btn-block'>Close</button>"+html
					: html;
			}
		}
		,
		ui: 
		{
			"btn_close": "button",
			"tabs": ".nav-tabs",
		}
		,
		regions:
		{
			"contents": ".tab-content"
		}
		,
		events:
		{
			"click @ui.btn_close": "onCloseRepo",
			"click @ui.tabs": "onSelectRepo"
		}
		,
		/**
		 * 앱 저장소를 초기화한다.
		 * 
		 * @param {object} options - 앱 저장소 초기화 파라메터
		 * 					{
		 * 						repoPath: {string} - 엡 리소스 경로
		 * 						repoId: {string} - 리소스 저장소 초기화 목록
		 * 						useClose: {boolean} - 닫기 버튼 활성화 플레그
		 * 					}
		 */
		initialize: function(options)
		{
			AppRepositories.__super__.initialize.call(this);
			this._$curTab = null;
			this._list = null;
			if(options.deferred)
			{
				// BaroFloating 에 의해 활성화 되는 경우
				this._deferred = options.deferred;
			}
			this._repoId = options.repoId || null;
			this._useClose = options.useClose || false;
			this._repoPath = options.repoPath || '/common/ResourcesDB.js';
		}
		,
		onBeforeShow: function()
		{
			var self = this;
			
			this._loadGalleryRepo( this._repoPath )
			.then(function(gallery)
			{
				self._repos = gallery;
				self._initTabs();
				self.onSelectRepo( self._repoId );
			});
		}
		,
		_initTabs: function()
		{
			for(var id in this._repos)
			{
				this.ui.tabs.append("<li id='"+id+"'><a>"+this._repos[id].label+"</a></li>");
			}
		}
		,
		onSelectRepo: function(evt)
		{
			if( evt == null )
			{
				return;
			}
			if( this._$curTab )
			{
				this._$curTab.removeClass('active');
			}
			if( typeof evt == 'string' )
			{
				this._$curTab = this.$el.find('#'+evt);
			}
			else
			{
				this._$curTab = this.$el.find( evt.target.parentNode );
			}
			
			if( this._$curTab.length > 0 )
			{
				this._$curTab.addClass('active');
				this._showRepo( this._$curTab.attr('id') );
			}
		}
		,
		_showRepo: function(repoId)
		{
			var self = this;
			
			this.contents.close();
			this._list = new AppResourceList({resources : this._repos[repoId].repo});
			this._list.on(AppResourceList.EVENT.SELECT, function(module)
			{
				Logger.info(module);
				if( self._deferred )
				{
					self._deferred.resolve(module);
				}
				else
				{
					self.trigger("nopopupReturn",module);
				}
			});
			this.contents.show( this._list );
		}
		,
		onCloseRepo: function()
		{
			Logger.debug("onCloseRepo()");
			if( this._deferred )
			{
				this._deferred.resolve(null);
			}
			this.contents.close();
		}
		,
		/**
		 * 지정한 경로의 저장소를 로드한다.
		 * @param {string} path - 저장소 DB 경로
		 * @return {object} {"리소스식별자":{ label: "리소스 레이블", repo: {object} }, ...}
		 * 					repo 의 내용은 "리소스 식별자" : { ver, desc, thumbnail, detail } 으로 구성됨 
		 */
		_loadGalleryRepo: function(path)
		{
			return UCMS.loadModuleByPath([ path ])
			.then(function(AppRepo)
			{
				return AppRepo.getGallery();
			})
		}
	});
	
	return AppRepositories;
});