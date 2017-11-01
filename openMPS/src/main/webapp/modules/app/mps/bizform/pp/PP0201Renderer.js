/**
 * Project : OpenMPS MIS
 *
 * 구매 > 수율기초등록
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
 			this._contentId = "PP0201";
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
				//	{"download":"엑셀"},
					{"delete":"삭제"},
					{"cancel":"취소"}
 				];

 				RendererBase.Method.initSubHeader.call( self, headerParams );
 			});
 		}
 		,
 		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachFormItem("queryBox");

			//
			var formItem = this.attachFormItem("formBox");
			var form$el = formItem.getItem().getWidget$Element();
			formItem.getItem().disabled(true );
			formItem.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData( item, gridItem, formItem );
			});

			//
			var gridItem = this.attachGridItem("resultBox");
			gridItem.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				if( self.isNewTransactionMode() == true )
				{
					if( item.id != self._newRowId )
					{
						UCMS.alert("신규 항목 작성을 완료해 주세요.");
					}
					gridItem.getItem().setSelectRow( self._newRowId, true );
					return;
				}
				self.onSelectRow( item );
			});
			gridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0201_grid_1","수율기초등록",false);
			});
			gridItem.getItem().on(JQGrid.EVENT.GRIDCOMPLETE, function(data)
			{

				Logger.debug("JQGrid.EVENT.LOADCOMPLETE");

				// 출력할 데이타 취득
				var suyulDusuSum = gridItem.getItem().getCol( "suyulDusu", false, "sum" );
				var suyulWei1Sum = gridItem.getItem().getCol( "suyulWei1", false, "sum" );
				var suyulWei2Sum = gridItem.getItem().getCol( "suyulWei2", false, "sum" );
				var jiyukDusuSum = gridItem.getItem().getCol( "jiyukDusu", false, "sum" );


				// 푸터에 데이타 출력
				// 출력될 셀의 formatter 와 호환되는 데이타만 출력됨
				gridItem.getItem().footerData('set',
				{
					grupPlantname: "합계",
					suyulDusu: suyulDusuSum,
					suyulWei1: suyulWei1Sum,
					suyulWei2: suyulWei2Sum,
					jiyukDusu: jiyukDusuSum

				});
			});
		}
 		,
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
 			.then(function()
			{
 				var formItem = self.attachFormItem("formBox");
 				var $form = formItem.getItem().getWidget$Element();
 				self.clearPlantTable( $form );
			});

 		}
 		,
 		onChangeFormData: function(item, gridItem, formItem)
		{
			this.beginTransaction();

			var formData = formItem.getData();
			var form$el = formItem.getItem().getWidget$Element();
			var $table = form$el.find(".formbox_inner_table");

			if( item.id == "grupPlant" || item.id == "workDate" )
			{
				var rows = gridItem.getItem().getRowData("grupPlant", formData.grupPlant);
				if( rows.length > 0 )
				{
					for(var i in rows)
					{
						if( this.getNewRowId() != rows[i].id && rows[i].data.workDate == formData.workDate )
						{
							UCMS.alert("동일날짜에 동일그룹의 공장은 추가할 수 없습니다!");
							return;
						}
					}
				}
				
				// 다른 필드 클리어
				formData.suyulDusu = null;
				formData.dusu1Yn = null;
				formData.dusu2Yn = null;
				formData.dusu3Yn = null;
				formData.dusu1 = null;
				formData.dusu2 = null;
				formData.dusu3 = null;
				formData.suyulWei1 = null;
				formData.suyulWei2 = null;
				formData.jiyukDusu = null;
				formData.docheQty1 = null;
				formData.docheQty2 = null;
				formData.docheQty3 = null;
				formData.memo = null;
				formItem.setData( formData );
				//
				this.fetchPlant( form$el, formData.grupPlant );
			}
			else if( item.id == "suyulDusu" )
			{
				this.clearPlantTable(form$el);
				$table.find("tbody tr:nth-child(1) td:nth-child(2) input").val(item.value);
				formData.dusu1 = item.value;
				formData.dusu2 = formData.dusu3 = 0;
			}
			else if( item.id.length == 5 && item.id.indexOf("dusu") == "0" )
			{
				var idx = item.id.charAt(4);
				$table.find("tbody tr:nth-child(1) td:nth-child("+(idx+1)+") input").val(item.value);
				// 가공두수 재분배
				switch( idx )
				{
				case "1":
					if( formData.suyulDusu < item.value)
					{
						UCMS.alert("입력된 두수가 가공두수 보다 많습니다.");
					}
					else
					{
						formData.dusu1 = item.value;
						var remaniDusu = formData.suyulDusu - Number(formData.dusu1);
						if( this._plantList && this._plantList.length > 1 )
						{
							formData.dusu2 = remaniDusu;
						}
						else if( remaniDusu > 0 )
						{
							UCMS.alert(remaniDusu+" 두수를 할당할 공장이 없습니다.");
							formData.dusu2 = "";
						}
						$table.find("tbody tr:nth-child(1) td:nth-child(3) input").val(formData.dusu2);
					}
					break;
				case "2":
					if( formData.suyulDusu < (formData.dusu1 +  item.value) )
					{
						UCMS.alert("입력된 두수가 가공두수보다 많습니다.");
					}
					else
					{
						formData.dusu2 = item.value;
						var remaniDusu = formData.suyulDusu - (Number(formData.dusu1) + Number(formData.dusu2));
						if( this._plantList && this._plantList.length > 2 )
						{
							formData.dusu3 = remaniDusu;
						}
						else if( remaniDusu > 0 )
						{
							UCMS.alert(remaniDusu+" 두수를 할당할 공장이 없습니다.");
							formData.dusu3 = "";
						}
						$table.find("tbody tr:nth-child(1) td:nth-child(4) input").val(formData.dusu3);
					}
					break;
				case "3":
					if( formData.suyulDusu != (formData.dusu1 + formData.dusu2 + item.value) )
					{
						UCMS.alert("입력된 두수가 가공두수와 일치하지 않습니다.");
						formData.dusu3 = formData.suyulDusu - (formData.dusu1 + formData.dusu2);
						$table.find("tbody tr:nth-child(1) td:nth-child(4) input").val(formData.dusu3);
					}
					else
					{
						formData.dusu3 = item.value;
					}
					break;
				}
			}

			var rowId = gridItem.getItem().getSelRowId();
			var gridData = gridItem.getItem().getRowData(rowId);

			Logger.debug("changed row : "+rowId);
			
			//
			formData.grupPlantname = form$el.find(".grupPlant option:checked").text();
			
			// 폼에는 그리드의 모든 필드 정보가 노출되지 않을 수도 있다.
			// TODO 그리드의 필드에 변경된 폼의 정보를 부가하는 방식을 사용해야, 트랜잭션 데이타가 누락되는 필드없이 생성된다.
			gridItem.setRow(rowId, _.extend(gridData, formData));
		}
 		,
		getQueryData: function(params)
		{
			var formItem = this.attachFormItem("queryBox");
			if(! formItem )
			{
				return null;
			}
			var queryParams = _.extend(formItem.getItem().getItemData(), params);
			if( queryParams.grupPlant == 0 )
			{
				queryParams = _.pick(queryParams, "strtDate", "lastDate");
			}
			return queryParams;
		}
 		,
 		clearPlantTable: function($form)
 		{
 			var $table = $form.find(".formbox_inner_table");
			$table.find("input[type=text]").each(function(){$(this).val("");});
 		}
 		,
 		fetchPlant: function($form, grupPlant, data)
 		{
 			var self = this;
 			var params = this.getQueryData();
 			
 			this._plantList = null;
 			grupPlant || (grupPlant = $form.find(".grupPlant option:selected").val());
 			
 			return this.getClient("PP0101").read({grupPlant: grupPlant || params.grupPlant}, "plant")
 			.then(function(res)
 			{
 				if( res.resultCode != 0 )
 				{
 					Logger.error(res);
 					return;
 				}

 				var $table = $form.find(".formbox_inner_table");
 				if( res.extraData.totalRecordCount > 0 )
 				{
	 				//
	 				// 공장 목록 설정
	 				//
	 				$table.removeClass("disabled_box");

	 				var $td = $table.find("tbody td");
					// 테이블이 3 개의 공장만 설정할 수 있는 크기임.
					// 테이블 컬럼 보다 공장 개수가 더 많으면, 나머지 공장 정보는 출력되지 않는다.
					for(var i=0; i<3; ++i)
	 				{
						if( res.extraData.result[i] )
						{
							$($td[i]).removeClass("disabled_box").find("input").val(data?data["dusu"+(i+1)]:"0").prop("disabled", false);
						}
						else
						{
							$($td[i]).addClass("disabled_box").find("input").val("").prop("disabled", true);
						}
	 				}
	 				self._plantList = res.extraData.result;
 				}
 				else
 				{
 					$table.addClass("disabled_box");
 				}
 			})
 			.fail(function(err)
 			{
 				self.clearPlantTable( $form );
				UCMS.reportError(err);
 			});
 		}
 		,
 		onCreate: function()
 		{
 			var formItem = this.attachFormItem("formBox");
 			var $form = formItem.getItem().getWidget$Element();
 			this.clearPlantTable( $form );
 			this.fetchPlant( $form );

 			//
 			Renderer.__super__.onCreate.call( this );
 		}
 		,
 		onSelectRow: function(selected)
		{
			var formItem = this.getActiveForm();
			if( formItem )
			{
				var $form = formItem.getItem().getWidget$Element();
				this.clearPlantTable( $form );
				formItem.setData( selected.data, true );
				formItem.getItem().disabled(false);
				this.fetchPlant( $form, selected.data.grupPlant, selected.data );
				
				// 변경 불가능한 필드를 비활성화
				$form.find(".grupPlant").addClass("disabled");
				$form.find(".grupPlant select").prop("disabled", true);
				$form.find("input.workDate").prop("disabled", true);
			}
		}
		,
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
				UCMS.alert("\"지육중량\" 값은 \"생체중량\" 값 보다 작아야합니다.");
			}
			else if( formData.suyulDusu != (formData.dusu1 + formData.dusu2 + formData.dusu3) )
			{
				UCMS.alert("입력 두수의 합이 가공두수와 일치하지 않습니다!");
			}
			else if( formData.suyulDusu < (formData.docheQty1 + formData.docheQty2 + formData.docheQty3) )
			{
				UCMS.alert("원료별 합("+(formData.docheQty1 + formData.docheQty2 + formData.docheQty3)+")이 가공두수("+formData.suyulDusu+") 보다 클 수 없습니다!");
			}
			else if( UCMS.getByteLength(formData.memo) > 200 )
			{
				UCMS.alert("\"비고\"는 최대 200 bytes 까지 입력할 수 있습니다.");
			}
			else
			{
				var self = this;
				
				this._hisNo = undefined;
				this.saveTransaction()
				.then(function(data)
				{
					self.onCancel(true);
					self.onQuery();
				});
			}
		}
		,
		onDelete: function()
		{
			var self = this;
			
			Renderer.__super__.onDelete.call( this )
			.then(function(res)
			{
				if( res.resultCode == 0 )
				{
					var gridItem = self.attachGridItem("resultBox");
					var ids = gridItem.getItem().getRowIds();
					if( ids.length == 0 || gridItem.getItem().getSelRowId() == null )
					{
						self.attachFormItem("formBox").clear();
					}
				}
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
