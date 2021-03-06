/**
 * Project : NDS MPS
 * 
 * 회사 관리 화면을 구현한다.
 * ID : TMCOOS10
 * 
 */

define([
	"Logger",
	"NDSProps",
	"RendererBase",
	"BaroBox",
	"FormBox",
	"WorkAreaHeader",
	"manifest!jqGrid4-1.0.0#widget",
	"WorkAreaRenderer2"
], function(Logger, NDSProps, RendererBase, BaroBox, FormBox, WorkAreaHeader, JQGrid, WorkAreaRenderer2)
{
	var OverridingMethod =
	{
		/**
		 * 5. 랜더러 아이템 관계성 초기화
		 */
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachFormItem("queryBox");
			
			//
			var formItem = this.attachFormItem("formBox");
			formItem.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				// 주소 정보 설정
				if (self.$el.find(".address_region input.zip_no").val() != "") {
					
					self.$el.find("input.zipCode").val(self.$el.find(".address_region input.zip_no").val());
					self.$el.find("input.addrCode1").val(self.$el.find(".address_region input.address").val());
					self.$el.find("input.addrCode2").val(self.$el.find(".address_region input.address_detail").val());
					
				}
				
				self.onChangeFormData( gridItem, formItem );
			});
			formItem.getItem().disabled(true );
			
			//
			var gridItem = this.attachGridItem("resultBox");
			gridItem.getItem().on(JQGrid.EVENT.SELECT, function(item)
			{
				self.onSelectRow( item );
			});
			
			this.onQuery();
		}
	
	}
	
	var WorkAreaMethod = 
	{
		onSelectRow: function(selected)
		{
			var self = this;
			var formItem = this.getActiveForm();
			formItem.setData( selected.data, true );

			self.$el.find(".address_region input.zip_no").val(selected.data.zipCode);
			self.$el.find(".address_region input.address").val(selected.data.addrCode1);
			self.$el.find(".address_region input.address_detail").val(selected.data.addrCode2);
			
			formItem.getItem().disabled(false);
		}
		,
		onSave: function()
		{
			var self = this;
			
			if (self.$el.find("input.corpCode").val() == "" || self.$el.find("input.corpName").val() == ""){
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
	};
	
	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend(OverridingMethod, WorkAreaMethod)
	);
	 
	return Renderer;
});
