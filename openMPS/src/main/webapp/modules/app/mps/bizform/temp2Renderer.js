/**
 * Project : OpenMPS MIS
 *
 * 구매 > 더느림지급액조회
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
 			this._contentId = "PO0205";
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
					{"download":"엑셀"},
					{"delete":"삭제"},
					{"cancel":"취소"}
 				];

 				RendererBase.Method.initSubHeader.call( self, headerParams );
 			});
 		}
 		,
		_initSubHeader: function() //구식 랜더러
	   {
		   Logger.debug("Renderer.initSubHeader()");

		   var self = this;
		   return this._queryHeaderInfo().then(function(headerParams)
		   {
			   //
			   headerParams.options.feature =
			   [
				   {"query":"조회"},
				   {"delete":"삭제"},
				   {"save":"저장"},
				   {"cancel":"취소"}
			   ];

			   Renderer.__super__._initSubHeader.call( self, headerParams );
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
 			        "resultBox.content"
 			        ];
 		}
 		,
 		/**
		 * 3. 현재 활성화된 그리드에 접근할 수 있는 이름을 반환한다.
		 */
 		getHeaderGridName: function()
 		{
			return "resultBox.content";
 				// 세트상품 그리드
 				//? "resultBox.setitem"
 				// 세부 제품 그리드
 				//: "resultBox.setdetail.content";
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
			var gridItem = this.attachGridItem("resultBox.content");

			//
			// 푸터에 정보 출력
			//
			gridItem.getItem().on(JQGrid.EVENT.GRIDCOMPLETE, function(data)
			{
				// 그리드에 데이타 로딩이 완료되면,
				// grouping 된 영역의 계산된 데이타를 얻어서
				// 푸터에 의도한 포멧으로 출력한다.
				Logger.debug("JQGrid.EVENT.LOADCOMPLETE");

				// 출력할 데이타 취득
				var sum = gridItem.getItem().getCol( "pigWeig3", false, "sum" );
				Logger.debug(sum);

				// 푸터에 데이타 출력
				// 출력될 셀의 formatter 와 호환되는 데이타만 출력됨
				gridItem.getItem().footerData('set', { facCustName: "2번째", sexNo1: 777 });
				gridItem.getItem().footerData('set', { dochId: "무게 합계", pigWeig3: sum });
			});
			gridItem.getItem().on(JQGrid.EVENT.SELECTCELL, function(item)
			{
				// { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
				var queryData = self.getQueryData();
				self.popupBox("PP0303_popup",
				{
					plantCode: queryData.plantCode,
					strtDate: queryData.strtDate,
					proCode: {
						proCode: "10348",
						barCode: "170427241044820179624",
						barinTime: "23:01:01",
						proYn: "50",
						dcYn: "30"
					}
				});
			});


			//
			// 그리드에 엑셀다운로드 버튼 추가
			//

			var gridItem = this.attachGridItem("tabAreaBox.tab_commonview")
			gridItem.getItem()
			.on(JQGrid4.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("","",false);
			});

			//
			// 옛방식중 하나. SD0202 참고
			//


		//
			// 그리드에 캘린더를 사용한 날짜 데이타 입력
			//
			gridItem.getItem().on(JQGrid.EVENT.BEFOREEDITCELL, function(item)
			{
				//
				// 셀의 데이타를 입력하는 모드인 셀 편집 모드에서만 동작된다.
				// 본 이벤트는 셀 편집이 시작되면 발생된다.
				//
				Logger.debug("BEFOREEDITCELL row id : "+item.id);
				Logger.debug(item);

				if( item.cell.name == "strtDate" || item.cell.name == "lastDate" )
				{
					// 날짜를 입력할 필드인 경우 캘린더를 팝업한다.
					self.popupGridCalender( item.id, item.cell.name );
				}
			});

			this.attachHeaderItem("resultBox_1.header", function(evt)
			{
				if( evt.cmd == "content:confirm" )
				{
					self.onConfirm();
				} else if( evt.cmd == "content:return" )
				{
					self.onReturn();
				}
			});
			this.initSalesHeadCombo();//연관된 콤보박스 처리(다중셀박)

 		}
 		,
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			if( params.plantCode )
			{
				params.plantCode = params.plantCode.result;
			}

	//		data["custCode"] = data["custCode"].result; //농장(새창으로 불러서 값을 부모창에 전달할때)
			return data;
		},
		onConfirm: function()
 		{
			UCMS.confirm("단가승인을 하시겠습니까?");
 		}
		,
		onReturn: function(){
			UCMS.confirm("<div class=\"popup_no_icon\"></div><div class=\"popup_no_icon_box\"><div class=\"title\">반려의견</div> <div class=\"body\"><textarea class=\"form-control\" style=\"height:100px\" placeholder=\"반려의견을 입력하여 주세요\"></textarea></div></div>");
		}
		,
		initSalesHeadCombo: function()
		{ // 연관된 콤보박스 처리(다중셀박:부모코드 정의)
			var box = this.attachFormItem("queryBox").getItem();
			var $headCust = box.getItemElement("headCust");
			$headCust.change(function(item)
			{
				box.setItemParams("teamCode", {
					"module": "combobox",
					"label":"영업파트",
					"required": false,
					"selector": ".teamCode",
					"disabled": false,
					"fetcher": {
						"url": "<%= host %>/rest/tmm/TMCOOS60?headCode="+$(this).find("option:selected").val(),
						"parser": "return UCMS.parseComboSalesTeam(arguments[0]);"
					}
				});
			});
		},
		onSave: function()
		{
			var formItem = this.attachFormItem("formBox");
			var formData = formItem.getData();
			if((formData.suyulDusu <= 0)||(formData.suyulWei1 <= 0)||(formData.suyulWei2 <= 0))
			{
				UCMS.alert("필수입력값을 입력하세요!");
			}
			else if( formData.suyulWei1 <= formData.suyulWei2 )
			{
				UCMS.alert("\"지육중량\" 입력값이 적당하지 않습니다.");
			}
			else if( formData.suyulDusu != (formData.dusu1 + formData.dusu2 + formData.dusu3) )
			{
				UCMS.alert("입력 두수의 합이 가공두수와 일치하지 않습니다!");
			}
			else if( UCMS.getByteLength(formData.memo) > 200 )
			{
				UCMS.alert("\"비고\"의 입력값은 200 bytes 를 넘어설 수 없습니다.");
			}
			else
			{
				this._hisNo = undefined;
				this.saveTransaction();
			}

		},
		updateTransactionData: function(head, detail)
		{
			this._transData || this.createTransactionData();
			this._transData.dsType = "U";
			if( head )
			{
				this._transData.head = head;
			}
			if( detail )
			{
				for(var i in detail)
				{
					var task = detail[i];
					if( UCMS.getByteLength(task.memo) > 200 )
					{
						UCMS.alert("\"비고\"의 입력값은 200 bytes 를 넘어설 수 없습니다.");
						return null;
					}
				}

				this._transData.detail = detail;
				this._transData.dsType = detail[0].dsType;
			}
			return this._transData;
		},
		isValidRowChecker: function(gridId, taskItem)
		{
			if( gridId == "formBox" )
			{
				return ( taskItem.bomCode && taskItem.bomVer && taskItem.proCode && taskItem.plantCode ) ? true : false;
			}
			else if( gridId == "resultBox.content" )
			{
				if( taskItem.qty <= 0 )
				{
					return "\"소요량\"은 0 보다 큰 수로 입력해 주세요.";
				}
				return ( taskItem.proType && taskItem.buProCode && taskItem.qty ) ? true : false;
			}
			return true;
		}
		,
 		onQuery: function()
 		{
 			var self = this;
			var queryData = this.getQueryData();
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

 			Renderer.__super__.onQuery.call(this)
 		},
 		onQuery: function()
 		{
 			var self = this;
			var queryData = this.getQueryData();
            if (queryData.proCode == ""){
            	UCMS.alert("자재코드를 입력하여 주세요.");
            	return;
            }
 			Renderer.__super__.onQuery.call(this)
 		}// 전체시
		/**
		 *  로딩바를 출력한다.
		 * @param parent_selector jquery selector 를 사용한다.
		 *
		 * @return { Boolean } 출력 성공시 true, 이미 존재하는 경우 false.
		 */
		showLoadingThis:function(parent_selector)
		{
			if($ && $(".loading_box").length == 0)
			{
				$("body").append("<div class='loading_box'><div class='loading_img'></div><div style='display: block;position: fixed;top: 50%;width: 290px;background: #fff;font-size: 20px;text-align: center;left: 50%;margin-left: -145px;margin-top: 40px;z-index: 2;'>생산계획자료 형성중 입니다</div></div>");
				return true;
			}
			else
			{
				return false;
			}
		},
		hideLoadingThis:function()
		{
		 $("body > .loading_box").remove();
		},
		_initResultGrid: function()
		{
			var self = this;
			return Renderer.__super__._initResultGrid.call(this)
			.done(function(grid)
			{
				self._initGridHeader();
				grid.on
				(JQGrid.EVENT.CLICKBUTTON
					,
					function(btn)
					{
					self.downloadExcel("SD0402_grid_1","출고승인요청",false);
					}
				);

			});
		},
		_initRightGrid: function()
		{
			var self = this;
			Renderer.__super__._initRightGrid.call(this)
			.then(function(grid)
			{
				grid.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel();
				});
			});
		}
		,
		// 전체 예외 처리 예시
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			data["proCode"] = data["proCode"].result;
			if( data.plantCode == 0 )
			{
				delete data.plantCode;
			}
			return data;
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
