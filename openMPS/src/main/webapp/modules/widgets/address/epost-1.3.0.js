/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved. 
 */

define([
        "BaroPanelBase",
        "BaroProps",
        "Logger",
        "FormItemPanel"
], function( BaroPanelBase , BaroProps, Logger, FormItemPanel)
{
	var zip_list_empty_html = UCMS.SPA.Component.extend(
	{
		// 주소 결과가 없을 경우 노출합니다. 
		template: "#zip_list_empty_html",
		tagName : "div",
		className :"zip_list_empty_box",
	});
	
	var RowData = Backbone.Model.extend(
	{
		/**
		 * 각 Row 의 콘텐츠 필드 중에서 화면에 출력되기 전에 가공이 필요한 처리를 진행한다.
		 * 각 Content ItemView 에서 수행해도 된다.
		 */
	    parse: function(response, options)
	    {	
	    	return response;
	    }
	});
	
	
	
	var ListData = Backbone.Collection.extend(
	{
		//model: RowData,
		/**
		 * ResultEx(List) 형식으로 반환되는 결과값을 Collection 에 셋하는 방법을 지정한다. 
		 */
		parse: function(response)
        {
			Logger.debug("response>>>>>>>");
			Logger.debug(response);
			return response.results.juso;
			
        }
	});
	
	var	liRow = Backbone.Marionette.ItemView.extend(
	{
		template: "#zip_row_html",
	    tagName: "li",
	    className: "list-group-item btn_go_zip_input",
	
	    initialize: function(options)
		{
	    	this._options = options;
	    	Logger.debug(options);
		}
		,
	
		ui : 
		{
			btn_go_detailAddr: ".btn_go_detailAddr",
		}
		,
		events : 
		{
			"click @ui.btn_go_detailAddr": "onDetail",
		}
		,
		onBeforeRender: function(){
			Logger.debug("liRow onBeforeRender");
			var zipNo = this.model.get("zipNo");
			var jibunAddr = this.model.get("jibunAddr");
			var roadAddr = this.model.get("roadAddr");
			this.model.set({
				"zipNo": zipNo,
				"jibunAddr": jibunAddr,
				"roadAddr": roadAddr,
			});
			
		}
		,
		onRender: function(){
		}
		,
		onDetail: function(){
			var self = this;
			var zipNo = this.model.get("zipNo");
			var jibun = this.model.get("jibunAddr");
			var roadAddr = this.model.get("roadAddr");
			
			
			//지번주소 또는 도로명 주소 둘중에 하나만 넘겨줘야 한 줄에 다볼수있음.
			this._options.result.trigger("detailInfo", {zipNo: zipNo, jibun: jibun, roadAddr: roadAddr});
			
			$(".zip_floating_region").html("");
		}
	});
	
	
	var zip_list_html = UCMS.SPA.Widget.extend(
	{
		template: "#zip_list_html",
		tagName : "div",
		className :"zip_list_box",
		
		collection: new ListData,
	    itemView: liRow,
	    
	    _options: null, 
	    	
	    
		model: new Backbone.Model(),
		initialize: function(options){
			this._options = options;
			var self = this;
			this.itemViewOptions = { result : this };
			
			
			this.on("detailInfo", function(info){
				//Logger.debug("zip_list_html >>>>>>");
				options.result.trigger("detailInfo",info);
				
			});
			
		}
		,
		onBeforeRender: function()
		{
			//Logger.debug("zip_list_html onBeforeRender");
			this.model.destroy();
		}
		,
		onRender: function(){
			var self = this;
			
		}
		,
		ui : 
		{
			btn_myappmore: ".btn_myappmore",
		}
		,
		events : 
		{
			"click @ui.btn_myappmore": "onAnymore",
		}
		,
		fetchList: function(ajaxData, clear, keyword){
			var self = this;
			
			this.collection.url = "http://www.juso.go.kr/addrlink/addrLinkApiJsonp.do";
			
			
			var countPerPage = this.model.get("countPerPage");
			if(countPerPage == undefined ){
				self.model.set({countPerPage : 5});
			}else{
				countPerPage = parseInt(countPerPage) + 5;
				self.model.set({countPerPage : countPerPage});
			}
			var perPage = this.model.get("countPerPage");
			
			
			var perPage = countPerPage;
        	var addParam = {
        			confmKey: ZipWidget.APIKEY,
        			currentPage: '1',
        			countPerPage: perPage,
        			keyword: keyword,
        			resultType: 'json',
        	};
        	
        	var ajaxParams = {
        		dataType: "jsonp",
        		crossDomain: true,
        	};
        	
        	ajaxData = _.extend(ajaxData, addParam);
        	
        	self._options.result.showLoading();
        	
        	Logger.debug("fetchList() - url : "+this.collection.url);
        	var fetchParams = { data: ajaxData || null, remove: clear || false };
        	
			return this.collection.fetch(_.extend(fetchParams, ajaxParams))
			.then(function(result){
				
				Logger.debug(result);
				
				var totalCount = result.results.common.totalCount;
				
				
				self._options.result.trigger("totalCount", {totalCount: totalCount});
				
				setTimeout(function(){
					self._options.result.hideLoading();
				}, 500);
				
				
			}, function(err){
				Logger.debug("API Err >>>>>" + err);
				UCMS.alert("잠시 후에 다시 시도해보세요.");
			});
		}
		,
		onAnymore: function(){
			var keyword = $("#zip_search").val();
			this.fetchList({}, true, keyword);
			
		}
		
	});

	var zip_list_default_html = UCMS.SPA.Widget.extend(
	{
		// 리스트에 기본으로 노출되는 내
		template: "#zip_list_default_html",
		tagName : "div",
		className :"zip_list_default_box",
		
	});
	
	
	var zip_floating_html = BaroPanelBase.extend(
	{
		template: "#zip_floating_html",
		tagName : "div",
		className :"floating_region popup_zip container-fluid modal_box",
		model: new Backbone.Model(),
		regions: 
		{
			"scroll_box" 	   			: ".scroll_box",
			"zip_list_region"			: ".zip_list_region",
			"zip_list_empty_region"		: ".zip_list_empty_region",
			"zip_list_default_region"	: ".zip_list_default_region"
    	},
		
		ui : 
		{
			"btn_zip_close" 			: ".btn_zip_close",
			"btn_go_zip_input" 	     	: ".btn_go_zip_input",
			"btn_zip_search"			: ".btn_zip_search",
			searchAddr					: "#zip_search",
			searchBtn					: "#searchBtn",
			"result_box"				: ".result_box",
			"btn_myappmore"				: ".btn_myappmore",
			"zip_list_region"			: ".zip_list_region",
			"zip_list_default_region"	: ".zip_list_default_region",
			"zip_list_empty_region"		: ".zip_list_empty_region"
			
		}
    	,
		events : {
			"click @ui.btn_zip_close"		: "onClose",
			"click @ui.btn_go_zip_input"	: "onSubmit",
			"click @ui.searchBtn"			: "onSearch",
			"click @ui.btn_myappmore"		: "onAnymore",
			"keypress @ui.searchAddr"		: "onKeyUp"
		}
    	,
		onKeyUp : function(e)
		{
			if( e.keyCode == 13 )
			{
    			this.onSearch();
    		}
		}
		,
		initialize: function(){
			
			var self = this;
			Logger.debug("check here>>>>>>>>");
			Logger.debug(this);
			this.on("totalCount", function(result){
				//$(".zip_floating_region").html("");
				var totalCount = result.totalCount;
				self.ui.result_box.html("검색결과 : <b>"+totalCount+"</b> 건");
				
				if(totalCount != 0){
					self.ui.zip_list_region.show();
					self.ui.zip_list_default_region.hide();
					self.ui.zip_list_empty_region.hide();
				}else{
					self.ui.zip_list_region.hide();
					self.ui.zip_list_empty_region.show();
					self.ui.zip_list_default_region.show();
				}
			});
			
		}
		,
		_defaults: new zip_list_default_html(),
		_emptyPanel: new zip_list_empty_html(),

		onShow: function(){
			
			//this._defaults = new zip_list_default_html();
			//this._emptyPanel = new zip_list_empty_html();
			
			var self = this;
			self.zip_list_empty_region.show(self._emptyPanel);
			self.ui.zip_list_empty_region.hide();
			
			self.ui.zip_list_region.hide();
			
			self.zip_list_default_region.show(self._defaults);
			
		}
		
		,
		onClose  : function(){
			$(".zip_floating_region").html("");
		}
		,
		onSearch: function(){
			var self = this;
			
			var keyword = this.ui.searchAddr.val();
			if(keyword == 'select'){
				UCMS.alert("검색어에 SQL예약어 금지");
			}
			else if(keyword == '>' || keyword == '<' || keyword == '=' || keyword == '%'){
				UCMS.alert("특수문자 입력은 안됩니다.");
			}
			else if(keyword == ""){
				UCMS.alert("검색어를 입력하세요");
			}
			else{
				var self = this;
				self.testPromise(keyword);
			}
			
		}
		,
		testPromise: function(keyword){
			var self = this;
			this._fetchList = new zip_list_html({result : self});
			
			self._fetchList.fetchList({}, true, keyword);
			
			setTimeout(function(){
				self.scroll_box.show( self._fetchList );
			},500);
			/**
			var p1 = new Promise(function(resolve, reject){
				
				self.showLoading();
				
				self._fetchList.model.set({countPerPage: 0});
				resolve(self._fetchList.fetchList({}, true, keyword));
				Logger.debug("first>>>>>");
				
				self.hideLoading();
			});
			
			p1.then(function(){
				self.scroll_box.show( self._fetchList );
				Logger.debug("second>>>>>");
			});
			*/
		}
		, 
		onAnymore: function(){
			this._fetchList.onAnymore();
		}
		,
		showLoading : function()
		{
			Logger.debug("showLoading>>>>>");
			this.ui.btn_myappmore.find("i").addClass("fa-spin");	
		}
		,
		hideLoading : function()
		{
			Logger.debug("hideLoading>>>>>");
			this.ui.btn_myappmore.find("i").removeClass("fa-spin");	
		}
	});
		
	var ZipWidget = FormItemPanel.extend(
	{
		_options : null,
		
		template: "#zip_one_html",
		tagName: "div",
		className: "zip_box",

		/**
		 * 커버 위젯을 초기화한다.
		 * 
		 * @param options	파라메터 객체
		 * 		{
		 * 			template: {string} 템플릿 지정. #zip_one_html(기본값), #zip_two_html 중 선택한다.
		 * 			itemId: {string}
		 * 			confirm : {
		 * 				show : true|false
		 * 			},
		 * 			value: {object} getItemData() 에서 반환된 초기화 값이 입력된다.
		 * 		}
		 */
   	    initialize: function(options)
		{
   	    	ZipWidget.__super__.initialize.apply(this, arguments);
   	    	this.model = new Backbone.Model
   	    	(
   	    		options.value || { zipNo: "", addr: "", detailAddr: "" }
   	    	);
   	    	if( options.resource.nls )
			{
   	    		this.model.set( options.resource.nls );
			}
		},

		regions: 
		{
			"zip_floating_region" : ".zip_floating_region"
    	},
		
		ui : 
		{
			"btn_go_confirm" 		: ".btn_go_confirm",
			"btn_go_zip_search" 	: ".btn_go_zip_search",
			"zip_floating_region"	: ".zip_floating_region",
			
			"zip_no"				: ".zip_no",
			"address"				: ".address",
			"address_detail"		: ".address_detail"
		},
		
		events : {
			"click @ui.btn_go_confirm"		: "onConfirm",
			"click @ui.btn_zip_close"		: "onClose",
			"click @ui.btn_go_zip_search"	: "onZipSearch",
			"focus @ui.address"				: "onFocus",
			"blur @ui.address"				: "onBlur",
			"focus @ui.address_detail"		: "onFocus",
			"blur @ui.address_detail"		: "onBlur"
		}
		,
		onRender: function(){
			if(this.getParam("confirm") && this.getParam("confirm").show == true)
			{
				this.ui.btn_go_confirm.removeClass("hide");
			}
			
			if(this.getParam("defaultAddr") != null)
			{
				var addrs = this.getParam("defaultAddr").split(" ");
				var detailaddr = addrs[addrs.length-1];
				var mainaddr = this.getParam("defaultAddr").replace(detailaddr, "");
				
				this.ui.address.val(mainaddr.trim());
				this.ui.address_detail.val(detailaddr);
			}
			
			this._initTabIndex( this.ui.btn_go_zip_search );
			this._initTabIndex( this.ui.address );
			this._initTabIndex( this.ui.address_detail );
		}
		,
		onZipSearch: function()
		{
			var self = this;
			var float = new zip_floating_html();
			this.zip_floating_region.show(float);	 
			
			float.on("detailInfo", function(info){
				Logger.debug("ZipWidget event callback>>>>>>>");
				
				self.model.set("zipinfo", info);
				self.ui.zip_no.val( info.zipNo );
				self.ui.address.val( info.roadAddr );
				self.ui.address_detail.val("");
				
				//
				self.fireChangeEvent( info );
			});
			
		},
		onConfirm : function()
		{
			var value = this.getItemData();
			
			if( value.zipNo == "" || value.addr == "")
			{
				UCMS.alert("주소를 검색해 주세요");
			}
			else if( value.detailAddr == "")
			{
				UCMS.alert("상세 주소를 입력해 주세요.");
			}
			else
			{
				this.trigger("epost:return", value);
			}
		}
		,
		/**
		 * 위젯에 설정된 정보를 반환한다.
		 * 
		 * @return	입력된 주소 정보
		 * 			{
		 * 				zipNo: {string},
		 * 				addr: {string},
		 * 				detailAddr: {string}
		 * 			}
		 */
		getItemData: function()
		{
			var itemData = {
				zipNo : this.ui.zip_no.val() || "",
				addr : this.ui.address.val() || "",
				detailAddr : this.ui.address_detail.val() || ""
			};
			UCMS.debug("getItemData");
			UCMS.debug(itemData);

			return itemData;
		}
		,
		onFocus: function()
		{
			UCMS.info("onFocus()");
			this._curAddr = this.getItemData();
		}
		,
		onBlur: function()
		{
			UCMS.info("onBlur()");
			this.fireChangeEvent( this.getItemData() );
		}
		,
		setItemData: function(data)
		{
			data || (data={});
			
			if( this.$el.find(".address_detail").length == 0 )
			{
				data.addr = data.detailAddr;
				data.detailAddr = null;
			}
			else
			{
				data.detailAddr = data.addr?data.addr+" "+data.detailAddr||"":data.detailAddr;
				data.addr = "";
			}
			
			this.ui.zip_no.val( data.zipNo || '' );
			this.ui.address.val( data.addr || '' );
			this.ui.address_detail.val( data.detailAddr || '' );
		}
		,
		fireChangeEvent: function(newAddr)
		{
			if( !newAddr ||
				!this._curAddr ||
				newAddr.zipNo != this._curAddr.zipNo ||
				newAddr.addr != this._curAddr.addr ||
				newAddr.detailAddr != this._curAddr.detailAddr 
				)
			{
				// 다른 위젯들에게 전파
				this.broadcastEvent( ZipWidget.EVENT.CHANGE, newAddr );
				
				// formbox 로 전파
				this.trigger( FormItemPanel.EVENT.CHANGE, { "id": this.getParam("itemId"), "value": newAddr } );
			}
		}
	}
	,
	{
		EVENT: {
			// 주소가 변경되면 발생된다.
			// 새로운 주소가 파라메터로 전달된다.
			CHANGE: "address:change"
		}
		,
		APIKEY: 'U01TX0FVVEgyMDE2MTIxNTE4MDgxMjE3Mzkz'
	});

	return ZipWidget;
	
});