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
