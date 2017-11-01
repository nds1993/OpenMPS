/**
 * Project : OpenMPS MIS
 *
 * 생산계획 Setup
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
 			this._contentId = "PP0301";
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
					{"0302":"생산계획입력(CM)"},
					{"query":"조회"},
					{"save":"저장"},
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
 			        "headerBox",
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
		onEventHandler: function(event)
		{
			if( RendererBase.Method.onEventHandler.apply( this, arguments ) == false )
			{
				if( event.cmd == "content:0302" )
				{
					UCMS.reloadPage("#box/PP0302");
				}
				else
				{
					return false;
				}
			}
			return true;
		}
		,

 		/**
		 * 4. 랜더러 아이템 관계성 초기화
		 */
 		onInitRendererItemEvents: function()
 		{
 			var self = this;

 			UCMS.retry(function()
 			{
 				if( self.$el.find("#gbox_PP0301_grid_1_list").length == 0 )
 				{
 					return false;
 				}

 				self.attachHeaderItem("headerBox");
 				var resultGridItem = self.attachGridItem("resultBox");
 				resultGridItem.getItem()
 				/*
 				.on(JQGrid.EVENT.BEFOREEDITCELL, function(item)
 				{
 					var $cell = $("#"+item.id+" select[name="+item.cell.name+"]");
 					$cell.change(function()
 					{
 						item.cell.value = $(this).find("option:selected").val();
 						var data = resultGridItem.getData();
 						data[item.cell.name] = item.cell.value;
 						resultGridItem.setData( data );
 						resultGridItem.getItem().saveRow( item.id, item.cell );
 					});
 				})
 				*/
 				.on(JQGrid.EVENT.EDITCELL, function(item)
 				{
 					var data = resultGridItem.getItem().getRowData(item.id);
 					resultGridItem.setRow(item.id, data);

 					//
 					self.beginTransaction(false);
 				});

 				self.onQuery();

 			});
 			/*
			$( "select" ).change(function() {
			  alert( "Handler for .change() called." );
			});
			*/
 		}
 		,
		onSave: function()
		{
			var self = this;
			this.saveTransaction()
			.then(function()
			{
				setTimeout(function()
				{
					self.onQuery();
				}
				,
				500);
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
