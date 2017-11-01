/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2013 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 *-------------------------------------------------------------
 * Project : Mobile Oven 
 * 2014.12.15
 * @author escteam 
 */

define([
        "FormItemPanel",
        "BaroProps",
        "Logger",
        "osapi",
        "DaumMap"
        ], function( FormItemPanel , BaroProps, Logger, osapi, MapAPI)
{
	var mapMethod =
	{
		template: "#map_box_html",
		tagName: "div",
		className: "map_box container-fix",

		ui : {
			
			logo_img : ".image_box",
			title_box : ".title_box",
			btn_go_moven : ".btn_go_moven",
			address : ".addr_box",
			map_view : ".map_view",
			desc_box : ".text_box"
		},
		
		events : {
			
			"click @ui.btn_go_moven" : "onGoMoven"
			
		},

		/**
		 * 
		 * @param options	바로앱용 파라메터 또는 폼 아이템용 파라메터가 전달된다.
		 * 
		 * 					폼 아이템 파라메터
		 * 					{
		 * 						value:
		 * 						{
		 * 							title: "",
		 * 							caption: "",
		 * 							view_method: "",
		 * 							body: "",
		 * 							addr: "",
		 * 							coordinate: { lat: ##, lng: ## }
		 * 						}
		 * 					}
		 * 					바로앱 위젯 파라메터
		 * 					{
		 * 						title: "",
		 * 						caption: "",
		 * 						view_method: "",
		 * 						body: "",
		 * 						addr: "",
		 * 						coordinate: { lat: ##, lng: ## }
		 * 					}
		 */
   	    initialize: function(options)
		{
   	    	this.model = new Backbone.Model();
   	    	this._map = null;
   			this._curMarkers = [];
   			this._curOverlays = [];
   			
   	    	if( options.value )
   	    	{
   	    		// 폼 아이템으로 설정되는 경우
   	    		this.model.set( _.extend({ title: "", caption: "", body: "" }, _.pick(options.value, "title", "caption", "body")) );
   	    		this.model.set( "mode", "item" );
   	    	}
   	    	else
   	    	{
   	    		// 바로앱 위젯으로 설정되는 경우
   	    		this.model.set( _.extend({ title: "", caption: "", body: "" }, _.pick(options, "title", "caption", "body")) );
   	    		this.model.set( "mode", "widget" );
   	    	}
   	    	
   	    	FormItemPanel.prototype.initialize.apply( this, arguments );
		},

		onBeforeRender: function()
		{
			if( this.model.has("addr") == false )
			{
				this.model.set("addr", "해당 주소가 입력되지 않아 준비중입니다.<br>관리자일 경우에는 모바일오븐에 접속하시어 주소를 입력하여 주세요.")
			}

			Logger.debug("MapWidget onBeforeRender()");
			Logger.debug(this.model);
		}
		,
		onRender : function()
		{
			if( this.model.get("mode") == "item" )
			{
				this.ui.title_box.hide();
				this.ui.desc_box.hide();
			}
			
			Logger.debug("MapWidget::onRender()");
		}
		,
	    onShow: function()
		{
	    	UCMSPlatform.log("showing map widget!" );

	    	var type = this.model.get("view_method");
	    	var addr = this.model.get("addr");
	    	
	    	if (type == "new_page")
	    	{
		    	title = '<h1><i class="fa fa-info-circle"></i> 모바일오븐 <span class="btn_go_close" onClick="$(\'#popup_moven\').addClass(\'hide\');"><i class="fa fa-close"></i> <span class="hide"> 닫기</span></span></h1>';
		    	title = title + '<div class="btn_box"><a href="http://www.moven.net" class="btn" target="_blank">모바일 오븐 바로가기</a></div><br><br>';

	    		//$(self.ui.title_box).html(title);
	    	}
	    	
	    	if(this.model.get("icon_font")){
	    		
	    		this.ui.title_box.find(".fa").removeClass("fa-bookmark");
	    		this.ui.title_box.find(".fa").addClass(this.model.get("icon_font"));
	    	}
	    	
	    	if( addr != null && addr != "")
	    	{
	    		this._setMap();
	    	}
	    	else
	    	{
	    		this.ui.map_view.hide();
	    	}
	    	
		},
		
		onClose: function()
		{
			this.model.off("change");
			Logger.debug("MapWidget::onClose()");
		},
		
		onGoMoven : function(){
			
			Logger.debug("onGoMoven()");
			
			if( UCMS.SPA.isAppOS() == true) {
				
				UCMS.showLoading();
				
				var cs = osapi.getModule("CookerApp");
				var actors = BaroProps.getActors();
				cs.runApp("cooker", 
				{
					svc_id : actors.svc_id,
					con_id : actors.con_id
				});
			}
			else{
				
				window.open( "http://www.moven.net", "_blank", "");
				
			}
			
		},
		
		_setMap: function()
		{
			var pos = this.model.get("coordinate");

			if( this._map == null )
			{
				// XXX 테스트를 위해 임시로 다음 지도 apikey 를 셋
				BaroProps.setApiKey({ daummap: "253387f1cadae0bca05911c343917dcc" });

				var self = this;
				var mapOpt = {
					apiKey: BaroProps.getApiKey().daummap,
					selector: MapWidget.SELECTOR,
					zoom: 4
				};

				if(	pos != null && pos.lat && pos.lng	)
				{
					mapOpt.point = { lat: pos.lat, lng: pos.lng };
				}
				
				this._map = new MapAPI(mapOpt);
				this._map.create().then(function()
				{
					self._markerToCenter();
				});
			}
			else if( pos != null && pos.lat && pos.lng )
			{
				this._map.setPosition( pos.lat, pos.lng );
				this._markerToCenter();
			}
			
			Logger.debug("MapAPIPanel._setMap() - Succeeded");
		}
		,
		_markerToCenter: function()
		{
			if(this._map)
			{
				if(this._curMarkers && this._curMarkers.length > 0)
				{
					this.mapMarkClear(this._curMarkers);
					this._curMarkers = [];
				}
			
				var latlng = this._map.getCenter();
				if( latlng )
				{
					this._curMarkers.push( this.setDefaultMarker(latlng.getLat(), latlng.getLng()) );
				}
				
				var addr = this.model.get("addr");
				this.ui.address.text( addr );
			}
		}
		,
		setDefaultMarker : function(lat, lng){
			
			var imageSrc =  BaroProps.getHosts().resource + '/themes/base/img/map_pin.png', // 마커이미지의 주소입니다    
		    imageSize = MapAPI.createSize(40, 40), // 마커이미지의 크기입니다
		    //imageOption = {offset: MapAPI.createPoint(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
		    imageOption = {};
			
			// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
			var markerImage = MapAPI.createImage(imageSrc, imageSize, imageOption),
			    markerPosition = MapAPI.createPoint(lat, lng); // 마커가 표시될 위치입니다
	
			// 마커를 생성합니다
			var marker = MapAPI.createMarker({
			  position: markerPosition,
			  image: markerImage // 마커이미지 설정 
			});
			
			this._map.addOverlay(marker);
			
			return marker;
		}
		,
		setAppMarker : function(appInfo){
			
			var self = this;
			var lat = appInfo.lat;
			var lng = appInfo.lng;
			var baro_id = appInfo.baro_id;
			var link = BaroProps.getHosts().baang + "/" + baro_id + ".web";
			var app_name = appInfo.name;
			var category = appInfo.category;
			var icon = appInfo.icon;
			var imageSrc =  BaroProps.getHosts().resource + '/themes/base/img/map_pin.png';
			
			if(icon != null)
			{
				imageSrc = icon;
			}
			
		    imageSize = MapAPI.createSize(40, 40); // 마커이미지의 크기입니다
		    imageOption = {};
		    
			// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
			var markerImage = MapAPI.createImage(imageSrc, imageSize, imageOption),
			    markerPosition = MapAPI.createPoint(lat, lng); // 마커가 표시될 위치입니다
	
			// 마커를 생성합니다
			var marker = MapAPI.createMarker({
			  position: markerPosition,
			  image: markerImage, // 마커이미지 설정 
			  clickable: true 
			});
			
			// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
			var iwContent = '<div class="map_point_app">'
					+ '<li class="list-group-item">'
					+ '<img class="btn_go_close btn_close" src="http://i1.daumcdn.net/localimg/localimages/07/mapjsapi/2x/bt_close.gif" />'
					+ '   <div class="media pr30">'
					+ '        <div class="media-left media-top">'
					+ '            <div class="app_icon app_icon_m"><a class="btn_item btn_go_item" ><img src="'+icon+'" onerror="this.src=\'http://resource.moven.net/themes/base/img/icon_none.png\'"></a>'
					+ '            </div>'
					+ '        </div>'
					+ '         <div class="media-body">'
					+ '            <h4 class="media-heading mt5"><a class="btn_item btn_go_item" id="">'+app_name+'<a/></h4>'
					+ '            <div class="desc">'
					+ '                 <div class="app_category">카테고리 : <span class="color_1st">'+category+'</span>'
					+ '                </div>'
					+ '                  <div class="app_address"></div>'
					+ '                 <button class="btn btn-xs btn-info btn_go_app"><i class="fa-external-link fa"></i> 바로가기</button>'
					+ '              </div>'
					+ '           </div>'
					+ '       </div>'
					+ '      <span class="right_icon_box"> <i class="fa fa-chevron-right"></i></span>'
					+ '</li>'
					+ '</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

			// 인포윈도우를 생성합니다
			var infowindow = new daum.maps.InfoWindow({
			    content : iwContent
			});
			
			infowindow.link = BaroProps.getHosts().baang + "/" + baro_id + ".web";

			// 마커에 클릭이벤트를 등록합니다
			daum.maps.event.addListener(marker, 'click', function() {
			      // 마커 위에 인포윈도우를 표시합니다
			      infowindow.open(self._map._map, marker); 
			      
			      $(".btn_go_close").click(function(){
			    	  infowindow.close();
			      });
			      $(".btn_go_app").click(function(){
			    	  window.open(link,"_blank","");
			      });
			      $(".btn_go_item").click(function(){
			    	  window.open(link,"_blank","");
			      });
			      
			});
			
			this._map.addOverlay(marker);
			
			return marker;
		}
		,
		mapMarkClear : function()
		{
			var self = this;
			self._map.clearObjects(self._curMarkers);
			self._map.clearObjects(self._curOverlays);
			
			self._curMarkers= [];
			self._curOverlays=[];
		}
		,
		/**
		points = [
		              	{lat: 35.8151, lng : 127.153, body: '<div class="map_point" >'+ 100 +'</div>',icon:'http://file.moven.net/home/b/baroappcs/1461834033944_icon.png',name:'삼락헌',category:'카테고리입력'}
						,{lat: 35.8147, lng : 127.152, body: '<div class="map_point" >'+ 200 +'</div>'}
						,{lat: 35.8156, lng : 127.153, body: '<div class="map_point" >'+ 300 +'</div>'}
          ];
        **/
		setBoundByPoints: function(points)
		{
			var self = this;
			self.mapMarkClear();
			
			Logger.debug("setBoundByPoints==");
	        
	        $.each(points,function(i,itm){
	        	self._curMarkers.push(self.setMarker(itm));
	        });
	        
	        self._curOverlays = self._map.customOverlay(points);
			
		}
		,
		clearPoints : function(points)
		{
			var self = this;
			self._map.clearObjects(points);
			
			points = [];
		}
		,
		polyLine : function(points, lineStyle)
		{
			/*** 지도에 표시할 선 스타일을 지정합니다.
			 * lineOpt = 
			 * {
				    path: linePath, // 선을 구성하는 좌표배열 입니다
				    strokeWeight: 5, // 선의 두께 입니다
				    strokeColor: '#FFAE00', // 선의 색깔입니다
				    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
				    strokeStyle: 'solid' // 선의 스타일입니다
				}
			 */
			var lineOpt = 
			_.extend({
			    strokeWeight: 5, // 선의 두께 입니다
			    strokeColor: '#FFAE00', // 선의 색깔입니다
			    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
			    strokeStyle: 'solid' // 선의 스타일입니다
			}, lineStyle);
			
			return this._map.polyLine(points, lineOpt);
		}
		,
		getTimeHTML : function (distance) {

		    // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
		    var walkkTime = distance / 67 | 0;
		    var walkHour = '', walkMin = '';

		    // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
		    if (walkkTime > 60) {
		        walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 '
		    }
		    walkMin = '<span class="number">' + walkkTime % 60 + '</span>분'

		    // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
		    var bycicleTime = distance / 227 | 0;
		    var bycicleHour = '', bycicleMin = '';

		    // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
		    if (bycicleTime > 60) {
		        bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 '
		    }
		    bycicleMin = '<span class="number">' + bycicleTime % 60 + '</span>분'

		    // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
		    var content = '<ul class="dotOverlay distanceInfo">';
		    content += '    <li>';
		    content += '        <span class="label">총거리</span><span class="number">' + distance + '</span>m';
		    content += '    </li>';
		    content += '    <li>';
		    content += '        <span class="label">도보</span>' + walkHour + walkMin;
		    content += '    </li>';
		    content += '    <li>';
		    content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
		    content += '    </li>';
		    content += '</ul>'

		    return content;
		}
		,
		viewDistanceWindow : function(content, position)
		{
			var self = this;
			// 커스텀 오버레이를 생성하고 지도에 표시합니다
	        distanceOverlay = new daum.maps.CustomOverlay({
	            map: self._map._map, // 커스텀오버레이를 표시할 지도입니다
	            content: content,  // 커스텀오버레이에 표시할 내용입니다
	            position: position, // 커스텀오버레이를 표시할 위치입니다.
	            xAnchor: 0,
	            yAnchor: 0,
	            zIndex: 3  
	        });
		}
	};

	/**
	 * 폼 아이템으로 동작시 사용되는 메소드
	 */
	var formItemMethod = 
	{
		onEventHandler: function(cmd, params)
		{
			Logger.debug("MapWidget::onEventHandler("+cmd+")");
			if( cmd != "address:change" )
			{
				return;
			}
			if( !params )
			{
				return;
			}
			Logger.debug(params);
			
			var self = this;
			
			this.model.set("addr", params.addr+" "+params.detailAddr);
			this._map.getGeocode( this.model.get("addr") )
			.then(function(result)
			{
				Logger.debug(result);
				var addr;
				if( result.addr instanceof Array )
				{
					addr = result.addr[0];
				}
				else
				{
					addr = result.addr;
				}
				self.model.set("coordinate", { lat: addr.lat, lng: addr.lng });
				self._setMap();
			});
		},
		
		/**
		 * @return
		 * 			{
		 * 						title: "",
		 * 						caption: "",
		 * 						content: "",
		 * 						addr: "",
		 * 						coordinate: { lat: ##, lng: ## }
		 *			}
		 */
		getItemData: function()
		{
			Logger.debug("MapWidget::getItemData()");
			var itemData = _.pick(this.model.toJSON(), "title", "caption", "content", "addr", "coordinate");
			
			if( itemData.coordinate && (!itemData.coordinate.lat || !itemData.coordinate.lng) )
			{
				itemData = null;
			}
			
			return itemData;
		}
	};
	
	var MapWidget = FormItemPanel.extend( _.extend( mapMethod, formItemMethod )
	,
	{
		// TestMobileoven
		SELECTOR: ".map_view"
	});

	return MapWidget;
	
});