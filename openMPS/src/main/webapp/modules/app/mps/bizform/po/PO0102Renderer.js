/**
 * Project : OpenMPS MIS
 *
 * 구매 > 등판자료 등록
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
		constructor: function(options)
		{
			var gridOptions = options.resultBox.options.gridParams;
			_.extend
			(
				gridOptions,
				this.makeAutoScrollingOptions2_local(50)
			);
			Renderer.__super__.constructor.call(this, options);
		}
		,
		initialize: function()
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "PO0102";
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
			        "queryBox",
			        "exUpload",
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

			this.attachHeaderItem("headerBox");
			this.attachFormItem("queryBox");

			// 개별 업로드
			var file_p = this.attachFormItem("exUpload").getItem().getItemElement("exFileUpload_p");
			file_p.change(function(item)
			{
				if( self._uploading == true )
				{
					// IE 에서 중복 호출되는 상황이 발생하고 있음
					// 2 번째 호출은 무시되도록 처리
					Logger.warn("Duplication Event : file change");
					return;
				}

				self._uploading = true;
				self._uploadMode = "per";
				RendererBase.Method.onChangeUploadFile.call( self, this, item )
				.always(function()
				{
					self._uploading = false;
				});
			});

			// 전체 업로드
			var file_a = this.attachFormItem("exUpload").getItem().getItemElement("exFileUpload_a");
			file_a.change(function(item)
			{
				if( self._uploading == true )
				{
					// IE 에서 중복 호출되는 상황이 발생하고 있음
					// 2 번째 호출은 무시되도록 처리
					Logger.warn("Duplication Event : file change");
					return;
				}

				self._uploading = true;
				self._uploadMode = "all";
				RendererBase.Method.onChangeUploadFile.call( self, this, item )
				.always(function()
				{
					self._uploading = false;
				});
			});

			this.attachGridItem("resultBox").getItem()
			.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
			{
				self.downloadExcel("PO0102_grid_1","등판자료등록",false);
			});
		}
	};

	var WorkAreaMethod =
	{
		onCancel: function()
		{
			this.endTransaction(true);
		}
		,
		getQueryData: function(params)
		{
			var formItem = this.attachFormItem("queryBox");
			if(! formItem )
			{
				return null;
			}
			var queryBox = formItem.getItem();
			return _.extend({ "excelGubun": this._uploadMode }, queryBox.getItemData(), params);
		}
	};

	var Renderer = WorkAreaRenderer2.extend
	(
		_.extend({}
			, OverridingMethod
			, WorkAreaMethod
		)
		,
		{
			EVENT:
			{
			}
		}
	);

	return Renderer;
});
