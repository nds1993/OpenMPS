/**
 * Project : OpenMPS MIS
 */

define
	(
		[
			"Logger", "FormBox", "NDSProps"
		],
		function(Logger, FormBox, NDSProps)
		{
			/**
			 * openMPS 콘텐츠의 헤드 정보를 담당한다.
			 * 제목, 메뉴 위치, 콘텐츠에 대한 사용자의 기능 버튼을 구현한다.
			 */
			var WorkAreaHeader = FormBox.extend(
			{
				constructor: function(options)
				{
					options = this._makeParams(options);
					WorkAreaHeader.__super__.constructor.call(this, options);
				},
				initialize: function(options)
				{
					WorkAreaHeader.__super__.initialize.call(this, options);
				},
				/**
				 * 제공된 파라메터를 기반으로 콘텐츠 헤더 생성을 위한 파라메터를 구성한다.
				 *
				 * @param
				 * {
				 * 		title : {label:{string},icon:{string}},
				 * 		path : [{string}, ...] 경로의 각 단계를 문자열 배열로 제공,
				 * 		feature : [{string}, ...] 사용 가능한 기능 목록은 2 가지 형식으로 지정 가능하다.
				 * 				{string} 형식 또는 {featureId:label} 형식으로 지정할 수 있다. 후자의 경우 지정한 label 이 사용된다.
				 * }
				 */
				_makeParams: function(seedValue)
				{
					var header = this;
					var makeBreadcrumbsItem = function(list) {
						var params = [];
						for (var i in list) {
							params.push({
								"label": list[i],
								"active": false,
								"cmd": null
							});
						}

						return params;
					};
					var makeFeatureButton = function(list) {
						var params = [];
						var getBtnParams = function(festureItem)
						{
							var featureId = festureItem, label = null;
							if( typeof festureItem !== 'string' )
							{
								// { featureId: label } 형식인 경우
								featureId = _.keys( festureItem )[0];
								label = festureItem[featureId];
							}

							var item = {
								"buttonId": null,
								"label": null,
								"style": "btn-primary",
								"icon": {
									"view": true,
									"type": "fa",
									"value": "fa-bandcamp"
								},
								"cmd": null
							};

							switch (featureId)
							{
								case "create":
									item.label = "신규";
									item.icon.view = false;
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.CREATE);
									};
									break;
								case "query":
									item.label = "조회";
									item.icon.view = false;
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.QUERY);
									};
									break;
								case "edit":
									item.label = "편집";
									item.icon.view = false;
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.EDIT);
									};
									break;
								case "delete":
									item.label = "삭제";
									item.icon.view = false;
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.DELETE);
									};
									break;
								case "save":
									item.label = "저장";
									item.icon.view = false;
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.SAVE);
									};
									break;
								case "cancel":
									item.label = "취소";
									item.icon.view = false;
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.CANCEL);
									};
									break;
								case "download":
									item.icon.view = false;
									item.style = "hide";
									break;
								case "share":
									item.label = "공유";
									item.icon.view = false;
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.SHARE);
									};
									break;
								case "favorite":
									item.label = "";
									item.icon.value = "fa-star";
									item.style = "btn_icon btn_favorite";
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.FAVORITE);
									};
									break;
								case "print":
									item.label = "";
									item.icon.view = false;
									item.icon.value = "fa-print";
									//item.style = "btn_icon";
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.PRINT);
									};
									break;
								case "close":
									item.icon.view = false;
									item.style = "hide";
									break;
								case "request":
									item.label = "요청";
									item.icon.view = false;
									item.style = "btn_icon";
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.REQUEST);
									};
									break;
								case "confirm":
									item.label = "승인";
									item.icon.view = false;
									item.style = "btn_icon";
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.CONFIRM);
									};
									break;
								case "reject":
									item.label = "반려";
									item.icon.view = false;
									item.style = "btn_icon";
									item.cmd = function() {
										header.broadcastEvent(WorkAreaHeader.EVENT.REJECT);
									};
									break;
								default:
									//
									// 사용자 정의 버튼
									//
									item.label = featureId;
									item.icon.view = false;
									item.cmd = function() {
										header.broadcastEvent("content:"+featureId);
									};
									break;
							}

							if( label )
							{
								item.label = label;
							}

							item.buttonId = featureId;

							return item;
						}

						for (var i in list) {
							params.push(getBtnParams(list[i]));
						}

						return params;
					};
					var params = {
						"className": seedValue.title.className || "pageheader_box",
						"order": [ "page_title" ],
						"activation": {
							"defaultWidget": "",
							"method": "one-page"
						},
						"custom_form": {
							"mode": "form",
							"items": {}
						},
						"page_title": {
							"module": "manifest!Title-1.0.0",
							"options": {
								"label": seedValue.title.label,
								"icon": {
									"pos": "prefix",
									"view": true,
									"type": "fa",
									"value": seedValue.title.icon
								},
								"title_type": seedValue.title.title_type || "h1"
							}
						}
					};

					//
					if( seedValue.path )
					{
						params["order"].push("page_breadcrumbs");
						params["page_breadcrumbs"] =
						{
							"module": "manifest!Breadcrumbs-1.0.0",
							"options": {
								"breadcrumbs_item": makeBreadcrumbsItem(seedValue.path),
								"text_align": "text-right"
							}
						};
					}

					//
					if( seedValue.feature )
					{
						params["order"].push("top_button");
						params["top_button"] =
						{
							"module": "manifest!ButtonMulti-1.0.0",
							"options": {
								"btn_item": makeFeatureButton(seedValue.feature),
								"btn_group_layout": "btn-openmps",
								"text_align": "text-right"
							}
						};
					}

					//
					if( seedValue.corpCode == true )
					{
						/*
						params["order"].push("corpCode");
						params["custom_form"]["items"] =
						{
							"corpCode": {
								"module": "combobox",
								"label": "회사구분",
								"required": false,
								"selector": ".corpCode",
								"items": [
								          {label:"openMPS",value:"1001"},
								          {label:"openMPS",value:"1002"}
								          ]
							}
						};
						*/
					}

					return params;
				},
				onShow: function()
				{
					var self = this;
					var useButtons = this.getParam("top_button")!=null;
					UCMS.retry(function()
					{
						if( useButtons == true && self._getWidgetInstance("top_button") == null )
						{
							// 버튼이 사용되는 경우에는 상태 설정을 위해 접근이 필요하다.
							return false;
						}
						if( self.getParam("corpCode") == true && self.$el.find(".corpCode").length == 0 )
						{
							return false;
						}
						self.setButtonMode( WorkAreaHeader.MODE.READY );
						self.$el.find(".corpCode").change(function(evt)
						{
							self.onChangeCorp(evt);
						});
					}
					, 2000)
					.fail(function()
					{
						Logger.warn("WorkAreaHeader.onShow() - Failed to initialize. ");
					});
				}
				,
				/**
				 * 버튼 활성화 모드를 설정한다.
				 * @param {string} mode - WorkAreaHeader.MODE 에 선언된 활성화 모드 식별자.
				 *
				 */
				setButtonMode: function(mode)
				{
					var btns = this._getWidgetInstance("top_button");
					if(! btns )
					{
						return;
					}
					//
					this._mode = mode;
					switch(mode)
					{
					case WorkAreaHeader.MODE.READY:
						btns.getButton("query").prop("disabled", false);
						btns.getButton("create").prop("disabled", false);
						btns.getButton("save").prop("disabled", true);
						btns.getButton("delete").prop("disabled", false);
						btns.getButton("cancel").prop("disabled", true);
						break;

					case WorkAreaHeader.MODE.TRANSATION:
						btns.getButton("query").prop("disabled", true);
						btns.getButton("create").prop("disabled", true);
						btns.getButton("save").prop("disabled", false);
						btns.getButton("delete").prop("disabled", true);
						btns.getButton("cancel").prop("disabled", false);
						break;

					case WorkAreaHeader.MODE.NEWTRANSATION:
						btns.getButton("query").prop("disabled", true);
						btns.getButton("create").prop("disabled", true);
						btns.getButton("save").prop("disabled", false);
						btns.getButton("delete").prop("disabled", true);
						btns.getButton("cancel").prop("disabled", false);
						break;
					}
				}
				,
				getButtonMode: function()
				{
					return this._mode;
				}
				,
				/**
				 * 지정한 버튼의 상태를 설정한다.
				 * @param {array} featureIds - 설정 대상이 되는 버튼 기능 목록
				 * @param {boolean} bEnable - 설정 상태. true 면 활성화되고, false 이면 비활성화된다.
				 * @param {boolean} bShow - show/hide 상태 설정.
				 */
				setButtonState: function(featureIds, bEnable, bShow)
				{
					var btns = this._getWidgetInstance("top_button");
					if(! btns )
					{
						return;
					}

					featureIds || (featureIds = []);
					for(var i in featureIds)
					{
						var btn = btns.getButton(featureIds[i]);
						if( btn )
						{
							if(typeof bEnable == "boolean")
							{
								btn.prop("disabled", bEnable == false);
							}
							if(typeof bShow == "boolean")
							{
								btn[bShow==true?"show":"hide"]();
							}
						}
					}
				}
				,
				onChangeCorp: function(evt)
				{
					var $corp = $(evt.target);
					var corpCode = $corp.val();
					NDSProps.set( "corpCode", corpCode );
					Logger.debug("CoreCode : "+corpCode);

					var self = this;
					UCMS.alert("\""+$corp.find("option:selected").text()+"\" 관리 화면으로 이동합니다.")
					.then(function()
					{
						self.broadcastEvent(WorkAreaHeader.EVENT.QUERY);
					});
				}
			}
			,
			{
				EVENT:
				{
					CREATE: "content:create",
					QUERY: "content:query",
					EDIT: "content:edit",
					DELETE: "content:delete",
					SAVE: "content:save",
					CANCEL: "content:cancel",
					DOWNLOAD: "content:excel",
					SHARE: "content:share",
					PRINT: "content:print",
					CLOSE: "content:close",
					REQUEST: "content:request",
					CONFIRM: "content:confirm",
					REJECT: "content:reject",
					MANUAL: "content:manual"
				}
				,
				MODE:
				{
					READY: "mode:ready",
					TRANSATION: "mode:transation",
					NEWTRANSATION: "mode:transation:new"
				}
			});

			return WorkAreaHeader;
		});
