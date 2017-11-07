/**
 * Project : OpenMPS MIS
 *
 * headerBox 로 식별되는 조회폼 1 개,
 * resultBox 로 식별되는 그리드 1 개,
 * formBox 로 식별되는 상세보기폼 1 개로 구성된
 * 가장 기본적인 업무 화면을 구현한다.
 *
 * 그 외의 구성에 대해서는 본 모듈을 상속 받아서 해당하는 업무 로직을 추가한 랜더러를 구현한다.
 * 표준 모듈로 동작하기 위해서는 표준화된 식별자로 해당 인스턴스에 접근할 수 있도록 설계한다.
 */

define([
	"Logger",
	"NDSProps",
	"BaroBox",
	"FormBox",
	"APIClient",
	"WorkAreaHeader",
	"manifest!jqGrid4-1.0.0",
	"manifest!CodeSearch-1.0.0",
	"SubContainer",
	"RendererBase"
], function(Logger, NDSProps, BaroBox, FormBox, APIClient, ContentHeader, JQGrid, CodeSearch, SubContainer, RendererBase)
{
	//
	JQGrid = JQGrid.widget;
	CodeSearch = CodeSearch.widget;

	var HeaderMethod =
	{
		_initSubHeader: function(headerParams)
		{
			Logger.debug("WorkAreaRenderer._initSubHeader()");

			if( this.$el.find(".headerBox_region").length > 0 )
			{
				this.headerBox.close();
			}
			else
			{
				// 모든 레전의 제일 앞에 배치
				this.$el.prepend('<div class="headerBox_region"></div>');
				this.addRegions({
					"headerBox": ".headerBox_region"
				});
			}

			var self = this;
			var setHeaderParams = function(headerParams) {
				// 콘텐츠 헤더 정보를 제일 앞에 추가한다.
				var order = self.getParam('order') || [];
				order.unshift("headerBox");
				self.setParam("order", order)
				self.setParam("headerBox", headerParams);
			};
			if( headerParams )
			{
				setHeaderParams( headerParams );
			}
			else
			{
				return this._queryHeaderInfo().then(setHeaderParams);
			}
		}
		,
		/**
		 * 사용자별로 제공되는 권한을 담은 파라메터 정보를 조회한다.
		 * 해당 정보는 WorkAreaHeader 에 제공된다.
		 */
		_queryHeaderInfo: function()
		{
			var d = $.Deferred();
			var makeContentPath = function(contentId)
			{
				var path = [];
				var menuItems = NDSProps.get("menuItems") || {};
				var rootItem = menuItems[ menuItems.defaultMenu ] || {};
				var makePath = function(node)
				{
					if(! node.order )
					{
						path.push( node.label );
						return;
					}
					var list = node.order;
					for( var i in list )
					{
						var childId = list[i];
						if( contentId.indexOf( childId ) >= 0 )
						{
							path.push( node.label );
							makePath( menuItems[ childId ] );
						}
					}
				}

				var list = rootItem.order;
				for( var i in list )
				{
					makePath( menuItems[ list[i] ] );
				}

				return path;
			};
			var itemPath = makeContentPath( this._contentId );
			var headerParams = {
				"module": "WorkAreaHeader",
				"options": {
					"title": {
						label: itemPath[ itemPath.length-1 ],
						icon: "fa-bookmark"
					},
					"path": itemPath,

					// 사용자별로 콘텐츠에 대한 권한을 API 로 부터 취득한다.
					"feature": [
        						"query",
        						"create",
        						"save",
        						"delete",
        						"cancel",
        						"close"
        					],

        			"corpCode": true
				}
			};

			Logger.debug("WorkAreaRenderer._queryHeaderInfo() - Content Id : " + this._contentId);

			// TODO 잊지 말자!!!
			// FIXME API 호출 지연을 테스트하기 위해 타이머를 추가했음. API 적용 후에 필히 제거해야 한다!!!
			/*
			    setTimeout(function()
			    {
			    	UCMS.warn("WorkAreaRenderer._queryHeaderInfo() - Emulated a delay.");
			    	d.resolve( headerParams );
			    }
			    ,
			    100);
			    */
			d.resolve(headerParams);

			return d.promise();
		}
		,
		onSave: function()
		{
			Logger.info("WorkAreaRenderer.onSave()");

			this._saveGridTask();
		}
		,
		onCancel: function()
		{
			Logger.info("WorkAreaRenderer.onCancel()");

			this._getTaskPool().reset();
			this._endTransaction(true);
			this._newRowId = null;
		},
		onDownloadXLS: function()
		{
			Logger.info("WorkAreaRenderer.onDownloadXLS()");
			var client = this._getClient();
			if( !client )
			{
				return UCMS.retReject();
			}
			var params = this._getQueryData();
			var path = client.getAPIPath("read", "export", params) + "/export";
			Logger.info(path);
			var dnWnd = window.open( path, "_download_" );
			UCMS.alert("엑셀이 다운로드되었습니다.");
		},
		onPrint: function()
		{
			var data = this._getQueryData();
			
			var partId = this._contentId.substr(0,2);
			var jrfDir = 'report/'+partId+'/'+this._contentId+'_report.jrf';
			RendererBase.Method.popupReportView.call( this, jrfDir , data);
		},
		onShare: function()
		{
			Logger.info("WorkAreaRenderer.onShare()");
			UCMS.alert("Share : " + this._contentId);
		},
		// 닫기 버튼 추가
		onCloseRenderer: function()
		{
			Logger.info("WorkAreaRenderer.onClose()");
			if( this._box.header.getButtonMode() != ContentHeader.MODE.READY )
			{
				var self = this;
				UCMS.confirm("작업중인 데이타가 존재합니다.<br>종료할까요?")
				.done(function()
				{
					RendererBase.Method.closeRenderer.call( self );
				});
			}
			else
			{
				RendererBase.Method.closeRenderer.call( this );
			}
		},
		// 즐겨찾기 기능 추가
		onFavorite: function()
		{
			$btn_fav = this.$el.find(".btn_favorite");
			if ($btn_fav.hasClass("active")){
				UCMS.confirm("해당 메뉴를 즐겨찾기 메뉴에서 <br>삭제 하시겠습니까?", { confirm: "삭제", cancel: "취소" })
				.then(function()
				{
					$btn_fav.removeClass("active"); // 클래스 빼기
				})
			} else {
				UCMS.confirm("해당 메뉴를 즐겨찾기 메뉴에 <br>추가 하시겠습니까?", { confirm: "추가", cancel: "취소" })
				.then(function()
				{
				$btn_fav.addClass("active");
				})

			}
		//	this.$el.find(".btn_favorite").hasClass("background","#000");
			Logger.info("WorkAreaRenderer.onFavorite()"+this);
		//	UCMS.alert("Favorite : " + this._contentId);

		}
		,
		onManual: function()
		{
			UCMS.alert("매뉴얼이 추가될 예정입니다.");
		}
	};

	var FormMethod =
	{
		_initForm: function()
		{
			if(! this._box.form )
			{
				return;
			}

			var self = this;
			this._box.form.on(FormBox.EVENT.CHANGE, function(item)
			{
				UCMS.debug(item);

				var formData = self._getFormData();
				if( formData )
				{
					self._updateSelectRowData(self._selRowId[self._getHeaderGridName()], formData);

					//
					self._beginTransaction();
				}
			});
			this._box.form.disabled(true);
		}
		,
		/**
		 * 데이타 조회 조건을 얻는다.
		 * @param {boolean} bCreate - 생성용 파라메터 생성시 true 지정
		 * @return {object}
		 */
		_getQueryData: function(bCreate)
		{
			var query = this._box.query;

			if (query && typeof query.getItemData == 'function')
			{
				var data = query.getItemData();
				Logger.info(data);
				//UCMS.alert(JSON.stringify(data));
				return data;
			}

			return null;
		}
		,
		/**
		 * 폼 인스턴스를 제공한다.
		 */
		getForm: function()
		{
			return this._box.form || null;
		}
		,
		/**
		 * 폼 데이타를 얻는다.
		 * @return {object}
		 */
		_getFormData: function()
		{
			var form = this._box.form;

			if (form && typeof form.getItemData == 'function')
			{
				var data = form.getItemData();
				Logger.info(data);
				//UCMS.alert(JSON.stringify(data));
				return data;
			}
			return null;
		}
		,
		/**
		 * 그리드에서 제공된 정보를 폼에 설정한다.
		 */
		_setFormData: function(formData)
		{
			var form = this._box.form;
			if (form && typeof form.setItemData == 'function')
			{
				form.setItemData( formData );
				form.disabled(false);
			}
		}
		,
		/**
		 * 폼에 설정된 데이타를 제거한다.
		 */
		_clearFormData: function()
		{
			this._setFormData({});
		}
		,
		/**
		 * @param {string} boxId - 팝업 박스 식별자
		 * @param {object} options - { callback: function(result){} }
		 * 							지정한 팝업 박스가 요하는 파라메터를 추가할 수 있다.
		 * 							callbakck 은 결과를 수신하기 위한 필수 요소이다.
		 */
		popupBox: function(boxId, options)
		{
			UCMS.debug("WorkAreaRenderer.onSearchCode()");
			UCMS.debug(options);

			var boxRepo = NDSProps.get("BoxRepo");
			var appName = NDSProps.get("appName");

			UCMS.loadBoxResource( boxRepo[boxId].detail )
			.then(function(boxParams)
			{
				options || (options={});
				UCMS.loadModuleByPath(["BaroFloating", boxParams.module])
				.then(function(BaroFloating, Module)
				{
					var cbResult = function(result)
					{
						if( options.callback )
						{
							options.callback( result );
						}
						BaroFloating.close();
					};

					BaroFloating.open
					(
						Module,
						_.extend(options, boxParams.options),
						{
							title: appName,
							close: function()
							{
								cbResult(null);
							}
							,
							className: "modal_box "+boxId
						}
					)
					.done(function(result)
					{
						cbResult(result);
					});
				});
			});
		}
		,
		/**
		 * 코드검색 위젯의 검색 요청을 처리한다.
		 * 선택완료되는 경우 콘텐츠 아이
		 * @param {object} params - { codeType: ##, params: {}, keyword: ##, callback: function(baseCode, keyword, result){} }
		 */
		onSearchCode: function(options)
		{
			this.popupBox("codesearch", options);
		}
	};

	var GridInnerMethod =
	{
		_initGrid: function(gridId)
		{
			var d = $.Deferred(), cnt = 0;
			var self = this;

			if(! this._selRowId )
			{
				// gridId:rowId 로 설정된
				this._selRowId = {};
			}

			UCMS.retry(function()
			{
				var grid = self._getResultGrid(gridId);
				if(! grid )
				{
					Logger.warn("_initGrid() - Not ready a grid... "+cnt);
					return false;
				}
				self._initTransaction(gridId);
				d.resolve(grid);
			}
			, 1000);

			return d.promise();
		}
		,
		_queryGridData: function(gridId, params, cbResult)
		{
			var client = this._getClient();
			if (!client) {
				return;
			}

			var self = this;

			this.showLoading();
			return client.read(params, gridId)
			.then(function(res)
			{
				if( res && res.resultCode < 0 )
				{
					UCMS.error(res);
					UCMS.reportError(res, "처리 중 오류가 발생했습니다.<br>조회 조건을 확인해 주세요.");
				}
				else if( cbResult )
				{
					if(res.extraData.result && res.extraData.result.length == 0)
					{
						UCMS.alert("조회된 데이타가 없습니다.");
					}
					cbResult( res );
				}
			})
			.fail(function(e)
			{
				UCMS.error(e);
				UCMS.reportError(e, "처리 중 오류가 발생했습니다.<br>조회 조건을 확인해 주세요.");
			})
			.always(function()
			{
				self.hideLoading();
			});
		}
		,
		/**
		 * 그리드에 신규 row 를 추가한다.
		 * @param {string} gridId - 대상이 되는 그리드 식별자. 지정하지 않으면 기본 값이 사용된다.
		 * @param {string} defaultData - 신규 row 가 갖는 기본 값. 지정하지 않은 경우 빈 필드로 생성된다.
		 * @return {string} 생성된 row 의 식별자. 생성하지 못한 경우 null.
		 */
		_createGridRow: function(gridId, defaultData)
		{
			var newRowId = null;
			var grid = this._beginTransaction(gridId);
			if( grid )
			{
				defaultData || (defaultData = {});
				newRowId = grid.addRow( defaultData, "last" );
				grid.setSelectRow( newRowId );

				//
				this._box.header.setButtonMode( ContentHeader.MODE.NEWTRANSATION );
			}

			return newRowId;
		}
		,
		_deleteGridData: function(gridId, bMultiple)
		{
			gridId = gridId || this._getHeaderGridName();
			if( this.isCreateMode(gridId) )
			{
				UCMS.alert("신규 항목의 등록 완료후 다시 시도해 주세요.");
				return;
			}
			var grid = this._getResultGrid(gridId);
			if( grid )
			{
				var ids = grid.getSelRowId(bMultiple);
				if( bMultiple == true && ids.length == 0 )
				{
					ids = [null];
				}
				if( ids[0] == null )
				{
					UCMS.alert("선택된 행이 없습니다.");
					return;
				}

				var self = this;
				UCMS.confirm(ids.length+"건의 데이타를 삭제하시겠습니까?", {confirm:"삭제", cancel:"취소"})
				.then(function()
				{
					var deleteTasks = [];
					for(var i in ids)
					{
						var rowData = grid.getRowData( ids[i] );
						rowData.dsType = "D";
						deleteTasks.push( rowData );
						grid.removeRow( ids[i], true );
					}

					var client = self._getClient();
					var apiPath = client.getAPIPath("create", null, self._getQueryData());

					self.showLoading();
					return client.transaction( apiPath, deleteTasks )
					.then(function(res)
					{
						if( res.resultCode == 0 )
						{
							UCMS.alert("삭제되었습니다.");
						}
						else
						{
							UCMS.reportError(res);
						}
					})
					.fail(function(err)
					{
						UCMS.reportError(err);
					})
					.always(function()
					{
						self.hideLoading();
						if( bMultiple == true )
						{
							grid.getWidget$Element().find("thead th input[type=checkbox]").prop("checked", false);
						}
					});
				});
			}
			else
			{
				UCMS.alert("대상 그리드가 존재하지 않습니다.");
			}
		}
		,
		_saveGridTask: function(gridId, params)
		{
			var client = this._getClient();
			if( !client )
			{
				return UCMS.retReject();
			}
			var self = this;
			var save = function()
			{
				var apiPath = client.getAPIPath("create", gridId, params || self._getQueryData( true ));
				var taskList = self._getTaskPool(gridId).getList();

				return client.transaction( apiPath, taskList )
				.then(function(result)
				{
					if( result.resultCode == 0 )
					{
						self._getTaskPool(gridId).reset();
						self._endTransaction(false, gridId);
						self._backupGrid(gridId);
						UCMS.alert("저장되었습니다.");
					}
					else
					{
						UCMS.error(result);
						UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");
					}
					
					return result;
				})
				.fail(function(e)
				{
					UCMS.error(e);
					UCMS.alert("처리 중 오류가 발생했습니다.<br>변경된 항목을 확인해 주세요.");

					// 오류의 원인이 제거되면, 다시 시도할 수 있도록 트랜젝션을 유지한다.
				})
				.always(function()
				{
					self._newRowId = null;
				});
			};

			gridId || ( gridId = this._getHeaderGridName() );
			if( this._getTaskCount(gridId) > 0 )
			{
				return UCMS.confirm("변경사항을 저장하시겠습니까?")
				.then(save);
			}
			else
			{
				this._newRowId = null;
				this._endTransaction(true, gridId);
				return UCMS.retResolve();
			}
		}
	};

	var GridMethod =
	{
		// jqGrid, realgrid 중 사용하는 그리드 모드를 하나 지정
		GRIDMODE: "realgrid",
		_selRowId: null,

		_getHeaderGridName: function()
		{
			return "grid";
		},

		/**
		 * 그리드를 초기화한다.
		 * @return {$.Promise}
		 */
		_initResultGrid: function(gridId)
		{
			var self = this;
			gridId || (gridId = this._getHeaderGridName());

			return this._initGrid(gridId)
				.then(function(grid)
				{
					grid.on
					(
						JQGrid.EVENT.SELECT
						,
						function(params)
						{
							self.onSelectItem(params, self._getHeaderGridName());
						}
					);
					grid.on
					(
						JQGrid.EVENT.EDITROW
						,
						function(row)
						{
							self._beginTransaction(gridId);
							self._updateSelectRowData( gridId, row.id, row.data );
						}
					);
					grid.on
					(
						JQGrid.EVENT.EDITCELL
						,
						function(result)
						{
							Logger.debug("EDITCELL : "+result.id);
							self.triggerMethod(WorkAreaRenderer.EVENT.GRID_EDITCELL, result);
						}
					);

					return grid;
				});
		}
		,
		/**
		 * 결과를 설정하는 그리드 객체를 얻는다.
		 * @return {BaroPanelBase} 그리드 위젯
		 */
		_getResultGrid: function(gridId)
		{
			var resultGrid = null;

			switch( gridId || this._contentId )
			{
				//
				// 관리 > 관리
				//
				case "MCOMAA20_07":
					// 부서등록
					resultGrid = this._box.result.getItemElement("headinfo");
					break;
				case "MCOMAA20_08":
					// 사용자권한관리
					resultGrid = this._box.result.getItemElement("userinfo");
					break;
				case "MCOMAA20_10":
					// 세트등록
					resultGrid = this._box.result.getItemElement("setitem");
					break;

			////// ----------
				// 영업 > 관리
				// 영업 > 마스터
				case "MSALAA20_01":
					// 담보이력관리
					resultGrid = this._box.result.getItemElement("damboinfo");
					break;
				// 영업 > 분석
				// 영업 > 포털

				case "grid":
				default:
					if( this._box.result )
					{
						resultGrid = (typeof this._box.result.getContent == "function" ? this._box.result.getContent() : this._box.result);
					}
					else
					{
						Logger.warn("_getResultGrid() - Not initialize a result area : "+gridId);
					}
					break;
			}

			if(!resultGrid || typeof resultGrid.addRow != "function" )
			{
				Logger.warn("_getResultGrid() - Invalid grid : "+gridId);
				resultGrid = null;
			}

			return resultGrid;
		}
		,
		/**
		 * 결과를 Grid 영역에 출력한다.
		 * @param {object} - API 로 부터 획득한 결과값
		 */
		_setResult: function(resData, gridId)
		{
			Logger.debug("_setResult() - Displayed a result in grid area.");
			Logger.debug(resData);
			var resultGrid = this._getResultGrid(gridId);
			if( resultGrid != null && typeof resultGrid.addRow == "function" )
			{
				if( GridMethod.GRIDMODE == "jqGrid" && this._localPagingMode == true )
				{
					// 로컬 페이징 모드
					// RendererBase.Method.makeAutoScrollingOptions2_local() 을 통해 페이징 설정이 된 경우
					resultGrid.setGridParam({data: resData.extraData.result});
				}
				else
				{
					// 데이타가 자라난다.
					resultGrid.addRow( resData.extraData.result, "first", true );
				}
			}
			else
			{
				Logger.warn("_setResult() - Invalid result grid object.");
			}
		}
		,
		onCreate: function()
		{
			Logger.info("WorkAreaRenderer.onCreate()");
			this._createEmptyRow(null, false);
		},
		onQuery: function()
		{
			var self = this;
			if( this._box.header.getButtonMode() != ContentHeader.MODE.READY )
			{
				if( this._getTaskCount(this._getHeaderGridName()) > 0 )
				{
					UCMS.confirm("변경사항이 무시됩니다.<br>진행할까요?")
					.done(function()
					{
						self.onCancel();
						self.triggerMethod("query:master:grid");
					});
					return;
				}
				else
				{
					this._box.header.setButtonMode(ContentHeader.MODE.READY);
				}
			}

			this.triggerMethod("query:master:grid");
		},
		/**
		 * 마스터 그리드의 조회 기능을 구현한다.
		 * onQuery() 에 의해 호출된다.
		 */
		onQueryMasterGrid: function()
		{
			var query = null;
			if(this._box.query)
			{
				query = this._getQueryData();
				if(! query)
				{
					UCMS.alert("조회 조건을 입력해 주세요.");
					return;
				}
			}
			else
			{
				// 파라메터가 없는 API 도 존재한다.
			}

			var self = this;
			this._queryGridData( this._getHeaderGridName(), query, function(res)
			{
				self._setResult(res);
				self._backupGrid();
			});
		}
		,
		onDelete: function()
		{
			Logger.info("WorkAreaRenderer.onDelete()");
			this._deleteGridData();
		}
		,
		/**
		 * Grid 데이타 항목이 선택됐을 때 발생한다.
		 * @param {object} params - 그리드 데이타.
		 * 					{
		 * 						id: {string},	그리드 row id
		 * 						data: {object}	선택된 row 의 데이타. jqgrid colModel 에 설정된 컬럼의 데이타가 담긴다.
		 * 					}
		 * @param {boolean} edit - true 인 경우 현재 선택된 행을 편집 모드로 전환한다.
		 * @param {string} gridId - 대상 그리드 식별자.
		 */
		onSelectItem: function(params, gridId)
		{
			UCMS.debug("WorkAreaRenderer.onSelectItem() - ");
			UCMS.debug(params);

			//
			this._selRowId[gridId] = params.id;
			this._setFormData( params.data );
		}
		,
		/**
		 * 지정한 데이타를 현재 선택된 row 에 설정한다.
		 * @param {string} gridId - 그리드 식별자
		 * @param {string} rowId - 데이타가 설정될 row 식별자
		 * @param {object} rowData - 첫 번째 파라메터로 gridId 가 지정된 경우, 해당 그리드에 설정될 row 데이타 객체.
		 */
		_updateSelectRowData: function(gridId, rowId, rowData)
		{
			if( rowData == undefined )
			{
				//
				// Single Grid 모드
				// 그리드가 지정되지 않고 호출된 경우
				//
				rowData = rowId;
				rowId = gridId;
				gridId = undefined;
			}

			UCMS.debug("_updateSelectRowData() : "+ rowId);
			if(! rowId )
			{
				return;
			}

			//
			// TODO boolean 값을 Y/N 로 변경하는 정책이 필요함
			//
			if( rowData.useYn != undefined )
			{
				rowData.useYn = (new Boolean(rowData.useYn) == true ? "Y" : "N");
			}
			if( rowData.programYn != undefined )
			{
				rowData.programYn = (new Boolean(rowData.programYn) == true ? "Y" : "N");
			}
			if( rowData.smsYn != undefined )
			{
				rowData.smsYn = (new Boolean(rowData.smsYn) == true ? "Y" : "N");
			}
			if( rowData.mailYn != undefined )
			{
				rowData.mailYn = (new Boolean(rowData.mailYn) == true ? "Y" : "N");
			}
			if( rowData.personYn != undefined )
			{
				rowData.personYn = (new Boolean(rowData.personYn) == true ? "Y" : "N");
			}
			if( rowData.salesYn != undefined )
			{
				rowData.salesYn = (new Boolean(rowData.salesYn) == true ? "Y" : "N");
			}
			if( rowData.prodYn != undefined )
			{
				rowData.prodYn = (new Boolean(rowData.prodYn) == true ? "Y" : "N");
			}
			if( rowData.logYn != undefined )
			{
				rowData.logYn = (new Boolean(rowData.logYn) == true ? "Y" : "N");
			}
			if( rowData.purYn != undefined )
			{
				rowData.purYn = (new Boolean(rowData.purYn) == true ? "Y" : "N");
			}
			// FAX 보내기
			if( rowData.coverFlag != undefined )
			{
				rowData.coverFlag = (new Boolean(rowData.coverFlag) == true ? "Y" : "N");
			}

			var grid = this._getResultGrid(gridId);
			grid.setRowData( rowId, rowData );

			// 갱신 Task 기록
			this._getTaskPool(gridId).add(new RendererBase.UpdateTask(rowId, rowData));
		}
		,
		/**
		 * 지정한 그리드에 빈 행을 추가한다.
		 * @param {string} gridId -
		 * @param {boolean} silent - true 이면 행 선택 이벤트를 발생시키지 않는다.
		 * 							셀 편집 모드에서는 true 를 지정해야 한다.
		 */
		_createEmptyRow: function(gridId, silent)
		{
			if( this.isCreateMode(gridId) )
			{
				UCMS.alert("신규 항목의 등록을 완료하세요.");
				return;
			}

			this._newRowId = this._createGridRow( gridId );
			if( this._newRowId != null )
			{
				this._getTaskPool(gridId).add( new RendererBase.CreateTask(this._newRowId, {}) );
				Logger.info("newRowId : "+this._newRowId);

				var grid = this._getResultGrid(gridId);
				grid.setSelectRow( this._newRowId, silent );
				//!silent || grid.editRow( newRowId );
			}
			return this._newRowId;
		}
		,
		/**
		 * 메소드 이벤트 GRID_EDITCELL 의 핸들러
		 * 편집 완료된 셀에 대한 후속 로직을 수행한다.
		 * @param {object} result - 편집된 셀 정보
		 * 					{
		 * 						id: {string} 편집된 셀의 rowId
		 * 						cell: {row: iRow, col: iCol, name: cellname, value: value}
		 * 								편집된 셀의 상세 정보
		 * 					}
		 * @param {string} gridId - 편집된 그리드의 식별자. 그리드가 하나인 경우 undefined 된다.
		 */
		onGridEditCell: function(result, gridId)
		{
			var grid = this._getResultGrid( gridId );
			var rowData = grid.getRowData( result.id );
			Logger.debug(rowData);

			this._beginTransaction(gridId);
			this._updateSelectRowData( gridId, result.id, rowData );
		}
		,
		/**
		 * 신규 항목 입력 모드인지 확인한다.
		 * @returns {boolean}
		 */
		isCreateMode: function(gridId)
		{
			var counter = this._getTaskPool(gridId).getCounter();
			return ( counter.create > 0 )
		}
		,
		getNewRowId: function()
		{
			return this._newRowId;
		}
	};

	/**
	 * openMPS 관련 콘텐츠 Box 리소스를 출력한다.
	 */
	var MainMethod =
	{
		/**
		 * Box 랜더러를 초기화한다.
		 *
		 * @param {object} options - BaroBox 의 options 을 넘긴다.
		 */
		initialize: function(options)
		{
			WorkAreaRenderer.__super__.initialize.call(this, options);
			Logger.debug("WorkAreaRenderer() - ");
			Logger.debug(options);

			this._box = {
				header: null,
				form: null,
				query: null,
				result: null
			};
			this._client = null;

			if (options.contentId)
			{
				// 초기화되는 콘텐츠 식별자
				this._contentId = options.contentId;
			}

			//
			_.extend(FormBox.ItemSettings,
			{
				"host": (NDSProps.get('hosts')||{api:"http://localhost:8080"}).api,
				"corpCode": NDSProps.get('corpCode') || "1001",
				"systemCode": NDSProps.get('systemCode') || "MPS"
			});
		},
		onRender: function() {
			this.showLoading();
		},
		onBeforeShow: function() {
			this._initPromise = this._initSubHeader();
		},
		onShow: function() {
			var renderer = this;

			this._initPromise.then(function() {
				renderer._showContentsBox();
			}).then(function() {
				renderer._activeDefaultTab();
			}).always(function() {
				renderer.hideLoading();
			});
		},
		onShowComplete: function()
		{
			Logger.debug("WorkAreaRenderer.onShowComplete()");

			this._box.header = this._getBoxInstance('headerBox');
			this._box.query = this._getBoxInstance('queryBox');
			this._box.form = this._getBoxInstance('formBox');
			this._box.result = this._getBoxInstance('resultBox');

			Logger.debug(this._box.form);
			Logger.debug(this._box.query);
			Logger.debug(this._box.result);

			var self = this;

			this._initForm();
			this._initResultGrid();

			//FAX 보내기 버튼(save 이벤트)
/*			this.$el.find("button.btn_user_check").click(function()
			{
				UCMS.confirm("FAX를 보내시겠습니까?")
				.done(function()
				{
					self.triggerMethod("save");
				}
				).fail(doing);
				return false;
			});
*/		}
		,
		/**
		 * 자식 모듈들이 발생시킨 이벤트를 처리한다.
		 */
		onEventHandler: function(event)
		{
			Logger.debug(event);

			switch( event.cmd )
			{
			//////
				// 헤더 이벤트
				//
				case ContentHeader.EVENT.CREATE:
					this.onCreate();
					break;
				case ContentHeader.EVENT.QUERY:
					this.onQuery();
					break;
				case ContentHeader.EVENT.DELETE:
					this.onDelete();
					break;
				case ContentHeader.EVENT.SAVE:
					this.onSave();
					break;
				case ContentHeader.EVENT.CANCEL:
					this.onCancel();
					break;
				case ContentHeader.EVENT.DOWNLOAD:
					this.onDownloadXLS();
					break;
				case ContentHeader.EVENT.SHARE:
					this.onShare();
					break;
				case ContentHeader.EVENT.PRINT:
					this.onPrint();
					break;
				case ContentHeader.EVENT.CLOSE:
					this.onCloseRenderer();
					break;
				case ContentHeader.EVENT.FAVORITE:
					this.onFavorite();
					break;
				case ContentHeader.EVENT.MANUAL:
					this.onManual();
					break;

			//////
				// Form 이벤트
				//
				case CodeSearch.EVENT.SEARCH:
					this.onSearchCode(event.params);
					break;

			//////
				// Grid 이벤트
				//

				//
				default:
					return false;
			}

			return true;
		}
		,
		/**
		 * 콘텐츠에 해당하는 클라이언트 모듈을 얻는다.
		 *
		 * @return {ClientBase} client - SPA Platform 의 ClientBase 을 상속받아 구현한 클라이언트 모듈
		 */
		_getClient: function()
		{
			var hosts = NDSProps.get('hosts') || { api: '' };
			return new APIClient(
			{
				host: hosts.api,
				systemCode: NDSProps.get('systemCode'),
				corpCode: NDSProps.get('corpCode'),
				contentId: this._contentId
			});
		}
		,
		/**
		 * 지정한 box Id 의 인스턴스를 얻는다.
		 * Renderer 의 구조에 따라서 지정한 인스턴스를 구하는 방식이 달라질 수 있다.
		 * 그런 경우 본 메소드를 오버라이딩하여 인스턴스를 반환하는 코드를 작성한다.
		 */
		_getBoxInstance: function(boxId)
		{
			return this._getWidgetInstance(boxId);
		}
		,
		/**
		 * CSV를 다운로드 합니다.
		 * @see http://www.jqueryscript.net/table/Multi-functional-Table-To-CSV-Converter-With-jQuery-TableCSVExport.html
		 * @param {string} gridId - 해당 그리드 아이디
		 * @param {string} fileName - 저장할 파일 이름
		 * @param {boolean} hidden - 히트 테이블 보이기
		 */
		downloadExcel: function(gridId,fileName,hidden)
		{

			UCMS.confirm("엑셀 파일을<br>다운로드 하시겠습니까?")
			.done(function()
			{
				jQuery("#"+gridId+"_list").TableCSVExport({
				  separator: ',',
				  header: [],
				  columns: [],
				  extraHeader: "",
				  extraData: [],
				  insertBefore: fileName ,
				  delivery: 'download' /* popup, value, download */,
				  emptyValue: '',
				  showHiddenRows: hidden,
				  rowFilter: "",
				  filename: fileName + ".csv"
				});
				// UCMS.alert("엑셀이 다운로드되었습니다.");
			});


			/*
			var client = this.getClient();
			if( !client )
			{
				return UCMS.retReject();
			}
			var params = this.getQueryData();
			var path = client.getAPIPath("read", featureId, params)
						+ "/export?"
						+ this.makeUrlParams(params);
							*/

			/*
			var dnWnd = window.open( path, "_download_" );
			_.delay(function()
			{
				dnWnd.close();
			}
			, 3000);
			*/

			//UCMS.alert(featureId)


		}
	};

	var TransactionMethod =
	{
		_initTransaction: function(gridId)
		{
			gridId || (gridId = this._getHeaderGridName());

			if(! this._backupModel)
			{
				this._backupModel = new RendererBase.BackupModel();
			}
			if(! this._taskPool)
			{
				this._taskPool = {};
			}
			this._taskPool[gridId] = new RendererBase.TaskPool();
		}
		,
		_getTaskPool: function(gridId)
		{
			if(! this._taskPool)
			{
				Logger.warn("_getTaskPool() - Not initialize a task pool!");
				return null;
			}

			gridId || (gridId = this._getHeaderGridName());
			if(! this._taskPool[gridId] )
			{
				Logger.warn("_getTaskPool() - Invalid Grid Id : "+gridId);
				// Dummy Pool
				return new RendererBase.TaskPool();
			}
			return this._taskPool[gridId];
		}
		,
		_getTaskCount: function(gridId)
		{
			gridId || ( gridId = this._getHeaderGridName() );
			var pool = this._getTaskPool(gridId);
			return pool ? pool.getTotCount() : 0;
		}
		,
		/**
		 * 그리드 트랜젝션 시작
		 * @param {boolean} backup - 그리드 데이타 백업 진행 여부. true 가 지정되어야 백업이 진행됨.
		 * @param {string} gridId - 그리드 식별자. 지정되지 않으면 Content Id 가 사용됨
		 * @return {Grid}
		 */
		_beginTransaction: function(backup, gridId)
		{
			if( typeof backup == 'string' )
			{
				// 첫번째 파라메터에 gridId 가 전달된 경우
				gridId = backup;
				backup = false;
			}
			else
			{
				gridId || (gridId = this._getHeaderGridName());
				backup || (backup = false);
			}

			//
			var grid = this._getResultGrid(gridId);
			if( grid )
			{
				if( backup )
				{
					this._backupModel.backup(gridId, grid.getRowData());
				}
			}
			this._box.header.setButtonMode(ContentHeader.MODE.TRANSATION);

			return grid;
		}
		,
		/**
		 * 그리드 트랜젝션을 종료한다.
		 * @param {boolean} restore - 데이타 복구 옵션. true 인 경우 백업된 데이타로 복원된다.
		 * @param {string} gridId - 대상 그리드 식별자. 생략된 경우 content id 가 적용됨
		 * @return {boolean} 트랜젝션 종료가 완료된 경우 true.
		 */
		_endTransaction: function(restore, gridId)
		{
			var self = this;
			var doing = function()
			{
				var grid = self._getResultGrid(gridId);
				if( grid )
				{
					if( restore == true )
					{
						if( self._backupModel.has(gridId) )
						{
							grid.addRow( self._backupModel.get( gridId ), "first", true );
						}
						else if( self._backupModel.isDummy != undefined )
						{
							// Backup Model 을 사용하지 않는 모드. 기존 데이타로 되돌린다.
							self.onQuery();
						}
						else
						{
							grid.clear();
						}
					}
				}
				self._box.header.setButtonMode(ContentHeader.MODE.READY);
				self._clearFormData();
			};

			if( typeof restore == 'string' )
			{
				// 첫번째 파라메터에 gridId 가 전달된 경우
				gridId = restore;
				restore = false;
			}
			else
			{
				gridId || (gridId = this._getHeaderGridName());
				restore || (restore = false);
			}

			//
			if( this._getTaskCount(gridId) > 0 )
			{
				UCMS.confirm("변경사항을 적용할까요?")
				.done(function()
				{
					self.triggerMethod("save");
				}
				).fail(doing);
				return false;
			}
			else
			{
				doing();
				return true;
			}
		}
		,
		_backupGrid: function(gridId)
		{
			if(! this._backupModel)
			{
				Logger.warn("_backupGrid() - Not initialize a backup model!");
				return null;
			}

			gridId || (gridId = this._getHeaderGridName());
			var grid = this._getResultGrid(gridId);
			if( grid )
			{
				this._backupModel.reset(gridId);
				var rowsData = grid.getRowData();
				if( rowsData.length > 0 )
				{
					this._backupModel.backup(gridId, rowsData);
				}
			}
		}
	};

	var WorkAreaRenderer = BaroBox.extend
	(
		_.extend({}, MainMethod, HeaderMethod, FormMethod, GridMethod, TransactionMethod, GridInnerMethod)
		,
		{
			CreateTask: RendererBase.CreateTask,
			UpdateTask: RendererBase.UpdateTask,
			DeleteTask: RendererBase.DeleteTask,
			TaskPool: RendererBase.TaskPool,

			EVENT:
			{
				GRID_EDITCELL: "grid:edit:cell"
			}
		}
	);

	return WorkAreaRenderer;
});
