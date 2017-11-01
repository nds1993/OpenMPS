/**
 * Project : OpenMPS MIS
 *
 * 생산 > 공장 등록
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
			this._contentId = "PP0101";
			this._client = this.createAPIClient();
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
					{"cancel":"취소"}
 				];

 				RendererBase.Method.initSubHeader.call( self, headerParams );
 			});
 		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;
			var gridItem = this.attachGridItem("resultBox");
			gridItem.getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PP0101_grid_1","공장등록",false);
			})
			.on(JQGrid.EVENT.SELECT, function(item)
			{
				if( self.isNewTransactionMode() == true )
				{
					if( self._newRowId && item.id != self._newRowId )
					{
						UCMS.alert("신규 항목 작성을 완료해 주세요.");
					}
					gridItem.getItem().setSelectRow( self._newRowId, true );
					return;
				}
				self.onSelectRow( item );
			});
			
			var formItem = this.attachFormItem("formBox");
			formItem.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				self.onChangeFormData( gridItem, formItem );
			});
			formItem.getItem().disabled(true );
		}
		,
		getQueryData: function()
		{
			var params = this.attachFormItem("queryBox").getItem().getItemData();
			if( params.plantCode )
			{
				params.plantCode = params.plantCode.result;
			}
			return params;
		}
		,
		onQuery: function(featureId)
		{
			var self = this;
			Renderer.__super__.onQuery.apply( this, arguments )
			.then(function()
			{
				if( self._plantCode )
				{
					var gridItem = self.getActiveGrid();
					var data = gridItem.getItem().getRowData("plantCode", self._plantCode);
					if( data.length > 0 )
					{
						// 이전 선태된 행 재선택
						gridItem.getItem().setSelectRow( data[0].id );
					}
				}
			});
			this.getActiveForm().clear();
		}
		,
		findComboLabel: function(name, code)
		{
			return this.$el.find("."+name+" select option[value="+code+"]").text();
		}
		,
		onChangeFormData: function(gridItem, formItem)
		{
			var rowId = gridItem.getItem().getSelRowId();
			var formData = formItem.getData();
			Logger.debug("changed row : "+rowId);
			if(! formData )
			{
				return;
			}
			if( formData.address.detailAddr.length > 0 && formData.address.zipNo.length == 0 )
			{
				UCMS.alert("우편번호를 입력해 주세요.");
				return;
			}
			if( formData.address )
			{
				formData.zipCode = formData.address.zipNo;
				formData.address = formData.address.addr + " " + formData.address.detailAddr;
			}
			formData.plantKindName = this.findComboLabel("plantKind", formData.plantKind);
			formData.prdtTypeName = this.findComboLabel("prdtType", formData.prdtType);
			formData.grupPlantName = this.findComboLabel("grupPlant", formData.grupPlant);

			//
			var self = this;
			this.checkDup({plantCode:formData.plantCode})
			.then(function(res)
			{
				if( res.resultCode != 0 )
				{
					UCMS.reportError(res);
					return;
				}
				if( res.extraData == "Y" )
				{
					UCMS.alert("이미 존재하는 공장 코드입니다.");
				}
				else
				{
					return self.checkDup({plantName:formData.plantName});
				}
			})
			.then(function(res)
			{
				if( res.resultCode != 0 )
				{
					UCMS.reportError(res);
					return;
				}
				if( res.extraData == "Y" )
				{
					UCMS.alert("이미 존재하는 공장 이름입니다.");
				}
				else
				{
					self.beginTransaction(true);
					gridItem.setRow(rowId, formData);
				}
			})
			.fail(function(err)
			{
				UCMS.reportError(err);
			});
		}
		,
		onSelectRow: function(selected)
		{
			var formItem = this.getActiveForm();

			//
			selected.data.address = {
				zipNo: selected.data.zipCode,
				addr: null,
				detailAddr: selected.data.address
			};

			//
			this._plantCode = selected.data.plantCode;

			//
			formItem.setData( selected.data );
			formItem.getItem().disabled(false);
			
			// 변경 불가능한 필드를 비활성화
			var $form = formItem.getItem().getWidget$Element();
			$form.find("input.plantCode").prop("disabled", true);
		}
		,
		onDelete: function()
		{
			var gridItem = this.getActiveGrid();
			if(! gridItem)
			{
				Logger.info("onDelete() - Find not found a grid item.");
				return;
			}
			var id = gridItem.getItem().getSelRowId();
			if( id )
			{
				this.beginTransaction(true);
				gridItem.removeRow(id);
			}
			else
			{
				UCMS.alert("선택된 행이 없습니다.");
			}
		}
		,
		checkDup: function(params)
		{
			return this._client.read( params, "dup" );
		}
		,
		onCreate: function()
		{
			if( this.isTransactionMode() == true )
			{
				UCMS.alert("신규 항목의 등록을 완료하세요.");
				return;
			}

			//
			if( this.getActiveForm() )
			{
				this.getActiveForm().clear();
				this.getActiveForm().getItem().disabled(false);
			}
			this._newRowId = this.getActiveGrid().createRow({"useYn":true}, "last");
			this.$el.find("input.plantCode").prop("disabled", false).focus();
			
			//
			this.beginTransaction();
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
				if( !taskItem.plantCode )
				{
					return "공장코드를 입력해 주세요.";
				}
				
				if(taskItem.plantCode.length > 2)
				{
					return "공장코드는 2자리 이하입니다.";
				}
				
				if( taskItem.address && taskItem.address.trim().length > 0 && taskItem.zipCode.trim().length == 0 )
				{
					// 상세 주소가 존재한다면 우편번호도 등록되어 있어야 한다.
					return "주소 입력이 완료되지 않았습니다.";
				}

				return ( taskItem.plantName && taskItem.grupPlantName && taskItem.prdtTypeName ) ? true : false;
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
