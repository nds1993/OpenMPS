/**
 * Project : openMPS MIS
 *
 * 영업 > 할인단가승인
 *
 */


 define([
 	"Logger",
 	"NDSProps",
 	"BaroBox",
 	"FormBox",
 	"WorkAreaHeader",
 	"RendererBase",
 	"SD0205Renderer",
 	"manifest!jqGrid4-1.0.0#widget"
 ], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, RendererBase, SD0205Renderer, JQGrid)
 {
 	var Renderer = SD0205Renderer.extend(
 	{
 		initialize: function(options)
 		{
 			Renderer.__super__.initialize.apply( this, arguments );
 			this._contentId = "SD0207";
 		}
 		,
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
					{"manual":"도움말"}
				];

				SD0205Renderer.__super__.initSubHeader.call( self, headerParams );
			});
		}
 		,
 		getItemList: function()
 		{
 			return [
 			        "queryBox",
					"resultBox_1",
					"resultBox_1.header",
					"resultBox_1.content",
					"purposeBox",
					"resultBox_2"
 			        ];
 		}
 		,
 		getHeaderGridName: function()
 		{
			return "resultBox_1.content";
 		}
 		,
		getProductGridName: function()
		{
			return "resultBox_2";
		}
 		,
 		onInitRendererItemEvents: function()
 		{
 			var self = this;

			this.attachHeaderItem("headerBox");
			this.attachFormItem("queryBox");
			var headGridItem = this.attachGridItem("resultBox_1.content");
			headGridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0207_grid_1","할인단가_요청목록",false);
			});

			headGridItem.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				Logger.debug("SELECTCELL row id : "+item.id+" - Editing : "+headGridItem.getItem().isEditMode());
				Logger.debug(item);

				var data = headGridItem.getItem().getRowData(item.id);
				self.onSelectRow( data );
			});
			var gridItem = this.attachGridItem("resultBox_2");
			gridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("SD0207_grid_2","할인단가_상세내용",false);
			});

			this.attachHeaderItem("resultBox_1.header", function(evt)
			{
				if( evt.cmd == "content:confirm" )
				{
					self.onConfirm();
				} else if( evt.cmd == "content:reject" )
				{
					self.onReject();
				}
			});

			// SD0205 의 메소드가 오류없도록 하기 위해 호출힌다.
			// 본 랜더러 내에서는 트랜잭션 관리가 필요없음.
			this.initTaskCollector();
 		}
 		,
		onConfirm: function()
 		{
			var self = this;
			UCMS.confirm("단가를 승인을 하시겠습니까?", {"confirm":"승인", "cancel":"취소"})
			.then(function()
			{
				// 승인 처리
				return self.request("yappro");
			})
			.then(function()
			{
				return self.onQuery();
			});
 		}
		,
		onReject: function()
		{
			var self = this;
			UCMS.inputBox("반려 메시지를 입력하세요.", {"confirm":"반려", "cancel":"취소"})
			.then(function(msg)
			{
				if(msg == null)
				{
					Logger.error("Invalid input box state!!!!");
					return UCMS.retReject();
				}

				// 반려처리
				return self.request("nappro", msg);
			})
			.then(function()
			{
				return self.onQuery();
			});
		}
		,
		setPurposeMsg: function(msg)
		{
			var box = this.getItemInstance("purposeBox");
			box.getWidget$Element().find("div.discTitle").html( msg );
		}
		,
		/**
		 * @param {string} status - 승인(yappro)/반려(nappro)
		 */
		request: function(status, memo)
		{
			var gridItem = this.getActiveGrid();
			if(! gridItem)
			{
				Logger.info("request() - Find not found a grid item.");
				return;
			}

			var reqData = [];
			var ids = gridItem.getItem().getSelRowId(true);
			if( !ids || ids.length == 0 )
			{
				UCMS.alert("먼저 대상 거래처를 선택해 주세요.");
				return;
			}

			for(var i in ids)
			{
				var data = gridItem.getItem().getRowData(ids[i]);
				reqData.push(
				{
					custCode: data.custCode,
					approMemo: memo,
					strtDate : data.strtDate,
					lastDate : data.lastDate
				});
			}

			var client = this.getClient();
			var queryData = Renderer.__super__.getQueryData.call( this );
			queryData.status = status;
			queryData.strtDate = data.strtDate;
			queryData.lastDate = data.lastDate;

			return client.transaction(client.getAPIPath("create", null, queryData), reqData)
			.done(function()
			{
				UCMS.alert("처리 되었습니다.");
			})
			.fail(function(err)
			{
				UCMS.reportError(err);
			});
		}

 	});

 	return Renderer;
 });
