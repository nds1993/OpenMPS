/**
 * Project : NDS MPS
 * 
 * 본부 관리 화면을 구현한다.
 * ID : TMCOOS50
 * 
 */

define([
	"Logger",
	"NDSProps",
	"WorkAreaRenderer2"
], function(Logger, NDSProps, WorkAreaRenderer2)
{
	var BusinessMethod = 
	{
		onSearchCode: function(options) 
		{
			
			// 회사코드
			
			var self = this;
			
			this.popupBox("codesearch",
			{
				"codeType": "TMCOOS10",
				"keyword": options.keyword,
				"params": options.params,
				"value": {
					"keyword": null,
					"result": null
				},
				"callback": function(result)
				{
					if( result )
					{
						self.$el.find("input.corpCode").val(result.code);
						self.$el.find("input.corpName").val(result.label);
						self.$el.find(".corpCodeSearch_region input.val_keyword").val(result.label);
						self.$el.find(".corpCodeSearch_region input.val_result").val(result.code);
						
						self.$el.find("input.corpName").change();
					}
				}
			});
			
		}		
		,
		onSelectRow: function(selected)
		{
			var self = this;
			var formItem = this.getActiveForm();
			formItem.setData( selected.data, true );

			self.$el.find(".corpCodeSearch_region input.val_keyword").val(selected.data.corpName);
			self.$el.find(".corpCodeSearch_region input.val_result").val(selected.data.corpCode);
			
			formItem.getItem().disabled(false);
		}
		,
		onSave: function()
		{
			var self = this;
			
			if (self.$el.find("input.corpCode").val() == "" || self.$el.find("input.headCode").val() == "" || self.$el.find("input.headName").val() == ""){
				UCMS.alert("필수입력값을 입력하세요!");
			} else {
				self._hisNo = undefined;
				self.saveTransaction()
				.then(function(data)
				{
					self.onCancel(true);
					self.onQuery();
				});
			}
		}
		,
		onCreate: function()
		{
			var counter = this.getActiveGrid().getTaskCounter();
			if( counter.create != 0 )
			{
				UCMS.alert("신규 항목의 등록을 완료하세요.");
				return;
			}
			this._newRowId = this.getActiveGrid().createRow();
			if( this.getActiveForm() )
			{
				this.getActiveForm().getItem().disabled(false);
			}
			this.beginTransaction();
			
			this.$el.find("input[name=useYn]").prop("checked",true);
			
			return this._newRowId;
		}
		/*
		,
		onChangeFormData: function(gridItem, formItem)
		{
			var rowId = gridItem.getItem().getSelRowId(); 
			var formData = formItem.getData();
			
			//
			var self = this;
			this.checkDup({corpCode:formData.corpCode, headCode:formData.headCode})
			.then(function(res)
			{
				if( res.resultCode != 0 )
				{
					UCMS.reportError(res);
					return;
				}
				if( res.extraData == "Y" )
				{
					UCMS.alert("이미 존재하는 본부 코드입니다.");	
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
		checkDup: function(params)
		{
			return this._client.read( params, "dup" );
		}
		*/
	};
	
	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend(BusinessMethod)
	);
	 
	return Renderer;
});
