/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2014 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman 
 */

define(
[
	"Logger"
]
,
function(Logger)
{
	var NaverMap = Backbone.Model.extend(
	{
		_map: null
		,
		
		/**
		 * 지도 파라메터를 초기화한다.
		 * 
		 * @param options	{
		 * 						clientId: "",
		 * 						selector: $(""),
		 * 						point: { lat: ##, lng: ## },
		 * 						size: { width: ##, height: ## },
		 * 						mode: #,
		 * 						zoom: #,
		 * 						level: [ min, max ]
		 * 					} 
		 */
		initialize: function(options)
		{
			NaverMap.__super__.initialize.apply( this, arguments );
			
			if( this.get("clientId") == undefined )
			{
				Logger.error("NaverMap.initialize() - Need a parameter[clientId]");
			}
			if( this.get("secret") == undefined )
			{
				Logger.error("NaverMap.initialize() - Need a parameter[secret]");
			}
			if( this.get("selector") == undefined )
			{
				Logger.error("NaverMap.initialize() - Need a parameter[selector]");
			}
			if( this.get("point") == undefined )
			{
				this.set("point", { lat: 37.5010226, lng: 127.0396037 });
			}
			if( this.get("size") == undefined )
			{
				this.set("size", { width: 500, height: 400 });
			}
			if( this.get("mode") == undefined )
			{
				this.set("mode", 0);
			}
			if( this.get("zoom") == undefined )
			{
				this.set("zoom", 10);
			}
			if( this.get("level") == undefined )
			{
				this.set("level", [1, 14]);
			}

			Logger.debug("NaverMap.initialize() - seed : "+JSON.stringify(this.toJSON()));
		}
		,
		create: function()
		{
			if( this._map != null )
			{
				Logger.error("NaverMap.create() - Already has created a map!");
				return;
			}
			
			var pos = this.get("point");
			var eles = UCMS.selectElement(this.get("selector"));
			var size = this.get("size");
			
			nhn.api.map.setDefaultPoint('LatLng');
			this._map = new nhn.api.map.Map(eles[0], {
				point : NaverMap.createPoint(37.5010226, 127.0396037),
				zoom : this.get("zoom"),
				enableWheelZoom : false,
				enableDragPan : true,
				enableDblClickZoom : false,
				mapMode : this.get("mode"),
				activateTrafficMap : false,
				activateBicycleMap : false,
				minMaxLevel : this.get("level"),
				size : NaverMap.createSize( size.width, size.height )
			});			
			
			Logger.debug("NaverMap.create() - succeeded");
		}
		,
		destroy: function()
		{
			this._map = null;
			Logger.debug("NaverMap.destroy() - succeeded");
		}
		,
		/**
		 * 지도의 중심에 놓여지는 좌표를 지정한다.
		 * 
		 * @param lat		{ Number } 위도. 가로선.
		 * @param lng		{ Number } 경도. 세로선.
		 */
		setPosition: function(lat, lng)
		{
			if( this._map == null )
			{
				Logger.error("NaverMap.setPosition() - You need to create a Map instance!");
				return;
			}
			
			this._map.setCenter( NaverMap.createPoint(lat, lng) );
		}
		,
		/**
		 * 지도의 중심을 패닝시킨다.
		 * 
		 * @param nX		{ Number } x축은 + 값이 오른쪽, - 값이 왼쪽.
		 * @param nY		{ Number } y축은 -값이 위쪽, +값이 아래쪽.
		 */
		panning: function(nX, nY)
		{
			if( this._map == null )
			{
				Logger.error("NaverMap.panning() - You need to create a Map instance!");
				return;
			}
			var panningOptions = { useEffect : true, centerMark : true };
			
			this._map.setCenterBy( nX, nY, panningOptions );
		}
		,
		setLevel: function(level)
		{
			if( this._map == null )
			{
				Logger.error("NaverMap.setLevel() - You need to create a Map instance!");
				return;
			}
			
			this._map.setLevel( level );
		}
		,
		addOverlay: function(target)
		{
			if( this._map == null )
			{
				Logger.error("NaverMap.addOverlay() - You need to create a Map instance!");
				return;
			}
			
			this._map.addOverlay( target );
		}
		,
		activeEvent: function(evtName, cbFunc)
		{
			if( this._map == null )
			{
				Logger.error("NaverMap.activeEvent() - You need to create a Map instance!");
				return;
			}
			
			this._map.attach( evtName, cbFunc );
		}
	}
	,
	{
		PATH_SDK_V2: 			"http://openapi.map.naver.com/openapi/v2/maps.js?clientId=",
		PATH_GEOCODE: 			"https://openapi.naver.com/v1/map/geocode",
		PATH_REVERSE_GEOCODE: 	"https://openapi.naver.com/v1/map/reversegeocode",
		PATH_STATIC: 			"https://openapi.naver.com/v1/map/staticmap.bin"
		,
		getSDKv2Path: function(clientId)
		{
			return NaverMap.PATH_SDK_V2+clientId;
		}
		,
		/**
		 * 주소를 좌표로 변환한다.
		 */
		getGeocode: function(clientId, addr)
		{
			return $.ajax(
			{
				type: "GET",
				url: NaverMap.PATH_GEOCODE,
				crossDomain: true,
				data:
				{
					query: addr,
					encoding: "utf-8",
					coord: "latlng",
					output: "json",
					key: clientId
				},
				dataType: "json",
			    error: function( XHR, textStatus, errorThrown )
			    {
			    	Logger.error(textStatus);
			    },
			    success: function(data, textStatus, jqXHR)
			    {
			    	Logger.debug(data);
			    }
			});
		}
		,
		/**
		 * 좌표를 주소로 변환한다.
		 */
		getReverseGeocode: function(clientId, lat, lng)
		{
			return $.ajax(
			{
				type: "GET",
				url: NaverMap.PATH_REVERSE_GEOCODE,
				crossDomain: true,
				data:
				{
					query: lat+","+lng,
					encoding: "utf-8",
					coord: "latlng",
					output: "json",
					key: clientId
				},
				dataType: "json",
				contentType: 'application/json; charset=utf-8',
			    error: function( XHR, textStatus, errorThrown )
			    {
			    	Logger.error(textStatus);
			    },
			    success: function(data, textStatus, jqXHR)
			    {
			    	Logger.debug(data);
			    }
			});
		}
		,
		createMap: function(container, options)
		{
			return new nhn.api.map.Map(container, options);
		}
		,
		createPoint: function(lat, lng)
		{
			return new nhn.api.map.LatLng(lat, lng);
		}
		,
		createSize: function(width, height)
		{
			return new nhn.api.map.Size(width, height);
		}
		,
		/**
		 * 네이버 지도 마커를 생성한다.
		 * 
		 * @param options	{ } 생성자 파라메터
		 * @param point		{ nhn.api.map.LatLng } 마커가 위치할 좌표 객체. 필요한 경우 지정한다.
		 */
		createMarker: function(options, point)
		{
			var marker = new nhn.api.map.Marker(options);

			if(point instanceof nhn.api.map.LatLng)
			{
				marker.setPoint(point);
			}
			
			return marker;
		}
		,
		createLabel: function(options, marker)
		{
			var label = new nhn.api.map.MarkerLabel(options);
			
			if(marker instanceof nhn.api.map.Marker)
			{
				label.setVisible(true, marker);
			}
			
			return label;
		}
		,
		createWindow: function()
		{
			return new nhn.api.map.InfoWindow();
		}
	});
	
	return NaverMap;
});