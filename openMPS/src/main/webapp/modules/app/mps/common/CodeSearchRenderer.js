/**
 * Project : OpenMPS MIS
 * 
 * 코드서치 패널을 구현한다.
 */

define([
	"Logger",
	"NDSProps",
	"SubContainer",
	"APIClient",
	"manifest!jqGrid4-1.0.0#widget"
], function(Logger, NDSProps, SubContainer, APIClient, GRID)
{
	/**
	 * 코드 검색 패널을 구현한다.
	 * 패널은 BaroFloating 에 의해 호출된다.
	 * 선택된 코드는 { code: ##, label: ## } 의 형식으로 반환된다.
	 */
	var CodeSearchRenderer = SubContainer.extend(
	{
		/**
		 * @param {object} options
		 * 			{
		 * 				header: {object},
		 * 				content: {object},
		 * 				codeType: {string},	// 코드검색 API 유형. CodeSearchRenderer.TYPE 의 값 중 하나를 지정한다.
		 * 									// 지정하지 않은 경우 "공통 코드 조회" 창으로 설정된다.
		 *				keyword: {string}	// 기본 검색 키워드
		 * 				params: {object}	// codeType 에 따라서 요구되는 파라메터
		 * 							// 공통코드 파라메터
		 * 							baseCode: {string}	// 그룹 코드 유형
		 * 							etc01: {boolean}
		 * 							etc02: {boolean}
		 * 							etc03: {boolean}
		 * 			}
		 */
		initialize: function(options)
		{
			CodeSearchRenderer.__super__.initialize.call(this, options);
			
			this._deferred = options.deferred || null;
			this._searchParams =
			{
				codeType : options.codeType,
				params : options.params || {},
				keyword : options.keyword || null
			};
		}
		,
		onShowComplete: function()
		{
			var self = this;
			
			this._box = {
				query: null,
				result: null
			};

			UCMS.retry(function()
			{
				if(! self.getContent()._getWidgetInstance("queryBox"))
				{
					return false;
				}
				if(! self.getContent()._getWidgetInstance("resultBox"))
				{
					return false;
				}
				if( self.getContent()._getWidgetInstance("queryBox").getWidget$Element().find("input.searchKeyword").length == 0 )
				{
					return false;
				}
				if( self.$el.find("#gbox_mesgInfo_list").length == 0 )
				{
					return false;
				}

				//
				self.initQueryBox();
				self.initResultBox();
				
				/*
				if( self._searchParams.keyword && self._searchParams.keyword.length > 0 )
				{
					self.onClickButton(
					{
						cmd: "content:query"
					});
				}
				*/
				// 무조건 코드 목록이 출력되는 정책 적용
				self.onClickButton(
				{
					cmd: "content:query"
				});
			});
		}
		,
		initQueryBox: function()
		{
			var self = this;
			this._box.query = this.getContent()._getWidgetInstance("queryBox");
			this._box.query.setItemData({ "searchKeyword": this._searchParams.keyword });
			this._box.query.getWidget$Element().find("input.searchKeyword").keypress(function(evt)
			{
				if( event.which == 13 )
				{
					// Enter
					self.onClickButton({"cmd":"content:query"});
				}
			});
		}
		,
		returnSelectCode: function(result)
		{
			if( this._deferred )
			{
				this._deferred.resolve( result );
			}
			else
			{
				Logger.info( result );
			}
		}
		,
		initResultBox: function()
		{
			var self = this;
			this._box.result = this.getContent()._getWidgetInstance("resultBox");
			this._box.result.on(GRID.EVENT.SELECT, function(selected)
			{
				self.returnSelectCode( selected.data );
			});
			this.initEtcColumns( this._box.result );
		}
		,
		initEtcColumns: function(jqgrid)
		{
			var colNames = ["코드", "코드명"];
			var etcModels = [];
			for( var i=1; i<=3; ++i )
			{
				if( this._searchParams.params["etc0"+i] == true )
				{
					colNames.push("기타정보 "+i);
					etcModels.push(
					{
						"name": "etc0"+i,
						"label": "기타정보 "+i,
						"width": 100,
						"align": "left",
						"editable": false,
						"hidden": true
					});
				}
			}
			if( etcModels.length > 0 )
			{
				var colModel = jqgrid.getGridParam("colModel");
				jqgrid.setGridParam({ "colModel":_.union(colModel, etcModels), "colNames":colNames });
			}
		}
		,
		getSearchKeyword: function()
		{
			if(! this._box.query )
			{
				Logger.warn("getSearchKeyword() - invalid call step!!");
				return {};
			}
			var queryData = this._box.query.getItemData();
			return queryData;
		}
		,
		onClickButton: function(event)
		{
			if(! this._box.result )
			{
				Logger.warn("onClickButton() - invalid call step!!");
				return {};
			}
			if( event.cmd !== "content:query" )
			{
				// Unknown Event
				return;
			}
			
			var self = this;
			var params = this.makeParams();
			if(! params)
			{
				UCMS.alert("지원하지 않는 검색 유형입니다.");
				return;
			}

			this.showLoading();
			this.searchCode(this._searchParams.codeType, params)
			.done(function(resData)
			{
				// 조회 결과 설정
				// 한 번에 추가하면 목록에 거꾸로 보여진다.
				//self._box.result.addRow( resData, "first", true );
				self._box.result.clear();
				for(var i in resData)
				{
					self._box.result.addRow( resData[i], "last", false );
				}
				if( resData.length == 1 )
				{
					self.returnSelectCode( resData[0] );
				}
			})
			.always(function()
			{
				self.hideLoading();	
			});
		}
		,
		/**
		 * 지정한 코드를 검색한다.
		 * @param {string} codeType - 검색 코드 유형.
		 * 							BASECODE: "TMCOCD10",	// 공통 코드 조회
		 * 							CUST: "SD0101",			// 거래처 코드 조회
		 * 							PRODUCT: "PP0103",		// 제품 코드 조회
		 * 							PLANT: "PP0101"			// 공장 코드 조회
		 * @param {object} params - 각 코드가 요구하는 파라메터 객체
		 * 							BASECODE: 공통 코드는 groupCode 추가
		 * 							CUST: 거래처 코드는 custType 추가
		 * 							PRODUCT: 제품 코드는 키워드과 검색 대상을 지정하는 animalKind 사용 가능.
		 * 									부자재 제품은 animalKind : 9 를 지정한다.
		 * 							PLANT: 공장 코드는 키워드만 사용
		 */
		searchCode: function(codeType, params)
		{
			var self = this;
			var hosts = NDSProps.get('hosts') || { api: '' };
			var client = new APIClient(
			{
				host: hosts.api,
				systemCode: NDSProps.get('systemCode'),
				
				// 코드검색 API 유형 지정 
				contentId: codeType
			}), apiID;
			
			switch(codeType)
			{
			case CodeSearchRenderer.TYPE.SALESCUST:
				apiID = "resultBox.leftGrid.content";
				break;
				
			default:
				apiID = "code";
				break;
			}
			
			return client.read( params, apiID )
			.then(function(res)
			{
				return self.transformListData(res.extraData.result, codeType);
			});
		}
		,
		/**
		 * API 유형에 맞는 조회 파라메터를 생성한다.
		 * @return {object}
		 */
		makeParams: function()
		{
			var seed = this.getSearchKeyword();
			var params = null;
			
			switch( this._searchParams.codeType )
			{
			case CodeSearchRenderer.TYPE.BASECODE:
				params = _.extend( seed, this._searchParams.params );
				break;
				
			case CodeSearchRenderer.TYPE.CUST:
				params = _.extend( seed, this._searchParams.params );
				break;
				
			case CodeSearchRenderer.TYPE.PRODUCT:
				params = _.extend( seed, this._searchParams.params );
				break;
				
			case CodeSearchRenderer.TYPE.PLANT:
				params = _.extend( seed, this._searchParams.params );
				break;
				
			case CodeSearchRenderer.TYPE.CORP:
				params = this._searchParams.params;
				break;
				
			case CodeSearchRenderer.TYPE.HEAD:
				params = this._searchParams.params;
				break;
				
			case CodeSearchRenderer.TYPE.TEAM:
				params = this._searchParams.params;
				break;
				
			case CodeSearchRenderer.TYPE.DEPT:
				params = this._searchParams.params;
				break;
				
			case CodeSearchRenderer.TYPE.SALESCUST:
				params = this._searchParams.params;
				break;

			case CodeSearchRenderer.TYPE.FAXNO:
				params = this._searchParams.params;
				break;
			}
			
			return _.extend(params||{}, seed);
		}
		,
		/**
		 * 조회 모드에 맞춰서 반환 값을 그리드 데이타 구조에 맞도록 변형한다.
		 */
		transformListData: function(seed, codeType)
		{
			var params = seed;
			
			codeType || (codeType = this._searchParams.codeType);
			switch( codeType )
			{
			
			case CodeSearchRenderer.TYPE.CORP:
				params = _.map(seed, function(item)
				{
					return {
						code: item.corpCode,
						label: item.corpName
					};
				});
				break;
				
			case CodeSearchRenderer.TYPE.HEAD:
				params = _.map(seed, function(item)
				{
					return {
						code: item.headCode,
						label: item.headName
					};
				});
				break;
			
			case CodeSearchRenderer.TYPE.TEAM:
				params = _.map(seed, function(item)
						{
					return {
						code: item.teamCode,
						label: item.teamName
					};
						});
				break;
				
			case CodeSearchRenderer.TYPE.DEPT:
				params = _.map(seed, function(item)
						{
					return {
						code: item.deptCode,
						label: item.deptName
					};
						});
				break;
				
			case CodeSearchRenderer.TYPE.PLANT:
				params = _.map(seed, function(item)
				{
					return {
						code: item.plantCode,
						label: item.plantName
					};
				});
				break;
				
			case CodeSearchRenderer.TYPE.SALESCUST:
				params = _.map(seed, function(item)
				{
					return {
						code: item.salesmanCust,
						label: item.salesmanCustname
					};
				});
				break;

			case CodeSearchRenderer.TYPE.FAXNO:
				params = _.map(seed, function(item)
				{
					return {
						code: item.sendPhone,
						label: item.sendName
					};
				});
				break;

			}
			
			return params;
		}
	}
	,
	{
		TYPE:
		{
			BASECODE: "TMCOCD10",	// 공통 코드 조회
			CUST: "SD0101",			// 거래처 코드 조회
			SALESCUST: "SD0103",	// 영업사원별 거래처 조회
			PRODUCT: "PP0103",		// 제품 코드 조회
									// 부자재 조회시 파라메터 animalKind 에9 지정
			PLANT: "PP0101",		// 공장 코드 조회
			CORP: "TMCOOS10",		// 회사 코드 조회
			HEAD: "TMCOOS50",		// 본부 코드 조회
			TEAM: "TMCOOS60",		// 팀 코드 조회
			DEPT: "TMCOOS70",		// 부서 코드 조회
			FAXNO: "TMCOMT60",		// FAX번호
		}
	});

	return CodeSearchRenderer;
});
