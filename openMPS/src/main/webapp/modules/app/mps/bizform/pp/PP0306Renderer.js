/**
 * Project : OpenMPS MIS
 *
 * 생산 > 생산계획서 조회/발행(PM)
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
 	"manifest!jqGrid4-1.0.0#widget",
 	"moment"
 ], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, RendererBase, WorkAreaRenderer2, JQGrid, moment)
 {
 	var Renderer = WorkAreaRenderer2.extend(
 	{
 		initialize: function(options)
 		{
 			Renderer.__super__.initialize.apply( this, arguments );
 			this._contentId = "PP0306";
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
					{"query":"조회"}
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
 			        "resultBox"
 			        ];
 		}
 		,
 		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
 		getHeaderGridName: function()
 		{
			return "resultBox";
 		}
 		,
 		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
 		onInitRendererItemEvents: function()
 		{
 			var self = this;
 			var $query = self.attachFormItem("queryBox").getItem().getWidget$Element();
 			
 			UCMS.retry(function()
 			{
 				if(!( self.$el.find(".plantCode option:first").val() > 0 ))
 				{
 					return false;
 				}
 				
 				$query.find(".btnRefresh_region").click(function()
	 			{
	 				self.fetchConfirmTime();
	 			});
 				
 				self.fetchConfirmTime();
 			});

 			$query.find(".plantCode select").change(function()
 			{
 				self.fetchConfirmTime();
 			});
 			$query.find(".workDate").change(function()
 			{
 				self.fetchConfirmTime();
 			});

			var gridItem = this.attachGridItem("resultBox");

			gridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0306_grid_1","생산계획서_조회_발행_PM",false);
			});

			
 		}
 		,
		getQueryData: function(params)
		{
			var data = Renderer.__super__.getQueryData.call( this, params );
			if( data.planTime == "0" )
			{
				data = _.pick( data, "workDate", "plantCode" );
			}
			return data;
		}
		,
 		fetchConfirmTime: function()
 		{
 			var self = this;
			return this.getClient().read( this.getQueryData(), "time" )
 			.then(function(res)
 			{
 				var $select = self.attachFormItem("queryBox").getItem().getWidget$Element().find(".planTime select");
 				var $option = $("<option value=0>선택</option>");
 				$select.empty();
 				$select.append( $option )
 				.change();

 				if( res.resultCode != 0 )
 				{
 					UCMS.reportError(res);
 					return;
 				}
 				if( res.extraData.result.length == 0 )
 				{
 					UCMS.info("fetchConfirmTime() - Empty confirm time.");
 					return;
 				}
 				for(var i in res.extraData.result)
 				{
 					var time = res.extraData.result[i];
	 				$option = $("<option></option>").text(time.label).val(time.code);
	 				$select.append( $option );
 				}
 				$select.change();
 			})
 			.fail(function(err)
 			{
 				UCMS.reportError(err);
 			});
 		}
		,
 		onQuery: function(featureId, options)
 		{
 			var self = this;
 			var queryData = this.getQueryData();
			
 			if( new Date(queryData.strtDate) > new Date(queryData.lastDate) )
			{
				UCMS.alert("조회 기간이 잘못 지정되었습니다.");
				return;
			}
 			//
 			this.showLoading();
 			this.fetchingGrid( featureId, options )
 			.always(function()
			{
				self.hideLoading();
				
			});
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
