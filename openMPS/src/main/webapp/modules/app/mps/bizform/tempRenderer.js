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
 				headerParams.options.feature =
 				[
					{"query":"조회"},
					{"create":"신규"},
					{"save":"저장"},
					{"delete":"삭제"},
					{"cancel":"취소"},
					{"print":"인쇄"}
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
			//
			// 그리드에 엑셀다운로드 버튼 추가
			//
			gridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("grid_id","filename",false);
			});
			,
			// 조회 처리
			getQueryData: function(params)
			{
				var data = RendererBase.Method.getQueryData.call( this, params );
				data["proCode"] = data["proCode"].result;

				// 전체 추가시 예외 처리
				if( data.plantCode == 0 )
				{
					delete data.plantCode;
				}
				return data;
			}
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
			totGirdItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0302_grid_5","생산계획_입력_CM_합계",false);
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
		,
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
		getQueryData: function(params)
		{
			var data = RendererBase.Method.getQueryData.call( this, params );
			//data["custCode"] = data["custCode"].result; //농장(새창으로 불러서 값을 부모창에 전달할때)
			return data;
		},

		onQuery: function()
 		{
 			var self = this;
			var queryData = this.getQueryData();
			if( new Date(queryData.strtDate) > new Date(queryData.lastDate) )
			{
				UCMS.alert("조회 기간이 잘못 지정되었습니다.");
				return;
			}
 			Renderer.__super__.onQuery.call(this)
 		}
		,
		/**
		 * Task commit 시 데이타의 유효성을 검증한다.
		 * 기본적으로 true 를 반환하며, 검증이 필요한 랜더러에서 상속 받아서 제공되는 Task 정보의 유효성 검증 결과를 boolean 값으로 반환처리한다.
		 * @param {string} gridId - commit 이 발생된 그리드의 식별자
		 * @param {object} taskItem - 트랜잭션 항목 객체
		 * @return {boolean} false 를 리턴하면, 해당 commit 동작은 reject 되며, 문제가 된 task 항목에 대한 정보 { id, task } 가 반환된다.
		 */
		isValidRowChecker: function(gridId, taskItem)
		{
			if( gridId == "resultBox" )
			{
				if( taskItem.address.trim().length > 0 && taskItem.zipCode.trim().length == 0 )
				{
					// 상세 주소가 존재한다면 우편번호도 등록되어 있어야 한다.
					return "주소";
				}
				if( taskItem.plantCode.length > 2 )
				{
					return "공장코드";
				}

				return ( taskItem.plantCode && taskItem.plantName && taskItem.grupPlantName && taskItem.prdtTypeName ) ? true : false;
			}
			return true;
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
