/**
 * Project : OpenMPS MIS
 *
 * 영업 > 년간목표_영업사원
 *
 */


 define([
 	"Logger",
 	"NDSProps",
 	"BaroBox",
 	"FormBox",
 	"WorkAreaHeader",
 	"RendererBase",
 	"WorkAreaRenderer2",
 	"manifest!jqGrid4-1.0.0#widget"
 ], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, RendererBase, WorkAreaRenderer2, JQGrid)
 {
 	var Renderer = WorkAreaRenderer2.extend(
 	{
 		initialize: function(options)
 		{
 			Renderer.__super__.initialize.apply( this, arguments );
 			this._contentId = "SD0302";
 		}
 		,
 		/**
		 * 1. 헤더 기능 버튼 초기화
		 */
 		initSubHeader: function()
 		{
 			Logger.debug("Renderer.initSubHeader()");

 			var self = this;
 			return this.queryHeaderInfo().then(function(headerParams)
 			{
 				//
 				headerParams.options.feature =
 				[
					{"query":"조회"},
					{"create":"신규"},
					{"save":"저장"},
					{"delete":"삭제"},
					{"cancel":"취소"}
 				];

 				RendererBase.Method.initSubHeader.call( self, headerParams );
 			});
 		}
	   ,
 		/**
		 * 2. 접근이 필요한 아이템 목록 지정
		 */
 		getItemList: function()
 		{
 			return [
 			        "queryBox",
 			        "resultBox",
 			        "resultBox.gridBox"
 			        ];
 		}
 		,
 		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
 		getHeaderGridName: function()
 		{
			return "resultBox.gridBox";
 		}
 		,
 		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
 		onInitRendererItemEvents: function()
 		{
 			var self = this;

			this.attachHeaderItem("headerBox");
			this.attachFormItem("queryBox");
			this.attachGridItem("resultBox.content");
 		}
 		,
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			/*
			if( params.plantCode )
			{
				params.plantCode = params.plantCode.result;
			}
			*/
			//data["custCode"] = data["custCode"].result; //농장(새창으로 불러서 값을 부모창에 전달할때)
			return data;
		},

 		onQuery: function()
 		{
 			var self = this;
			var queryData = this.getQueryData();
			/*
			if( new Date(queryData.junStrtDate) > new Date(queryData.junLastDateBe) )
			{
				UCMS.alert("전기 조회 기간이 잘못 지정되었습니다.");
				return;
			}
			if( new Date(queryData.dangStrtDateNo) > new Date(queryData.dangLastDateNo) )
			{
				UCMS.alert("당기 조회 기간이 잘못 지정되었습니다.");
				return;
			}
			if (queryData.proCode == ""){
            	UCMS.alert("자재코드를 입력하여 주세요.");
            	return;
            }
			*/
 			Renderer.__super__.onQuery.call(this)
 		}
 	}
 	,
 	{
 		EVENT:
 		{
 		}
 	});

 	return Renderer;
 });
