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
	var DaumMap = Backbone.Model.extend(
	{
		_map: null
		,
		
		/**
		 * 지도 파라메터를 초기화한다.
		 * 
		 * @param options	{
		 * 						apiKey: "",
		 * 						selector: $(""),
		 * 						point: { lat: ##, lng: ## },
		 * 						zoom: #,
		 * 					} 
		 */
		initialize: function(options)
		{
			DaumMap.__super__.initialize.apply( this, arguments );
			
			if( this.get("apiKey") == undefined )
			{
				Logger.error("DaumMap.initialize() - Need a parameter[apiKey]");
			}
			if( this.get("selector") == undefined )
			{
				Logger.error("DaumMap.initialize() - Need a parameter[selector]");
			}
			if( this.get("point") == undefined )
			{
				this.set("point", { lat: 37.5010226, lng: 127.0396037 });
			}
			if( this.get("zoom") == undefined )
			{
				this.set("zoom", 10);
			}

			Logger.debug("DaumMap.initialize() - seed : "+JSON.stringify(this.toJSON()));
		}
		,
		/**
		 * 다음 지도 객체를 생성한다.
		 * @return { $.promise } 다음 맵 객체 생성 결과를 반환하는 promise 객체
		 */
		create: function()
		{
			if( this._map != null )
			{
				Logger.error("DaumMap.create() - Already has created a map!");
				return;
			}
			
			var self = this;
			var sdkPath = DaumMap.getSDKPath( this.get("apiKey") );
			var d = $.Deferred();
			
			require
			(
				[ sdkPath ]
				, function()
				{
					Logger.debug("DaumMap.create() - succeeded. sdk path : "+sdkPath);
					
					// 추가 라이브러리 로딩
					daum.maps.load(function()
					{
						var pos = self.get("point");
						var eles = UCMS.selectElement(self.get("selector"));
						self._map = DaumMap.createMap(typeof eles.length == "number" ? eles[0] : eles,
						{
							center : DaumMap.createPoint( pos.lat, pos.lng ),
			                level: self.get("zoom"),
			                mapTypeId : daum.maps.MapTypeId.ROADMAP
						});
						
						var zoomControl = new daum.maps.ZoomControl();
		
						// 지도 오른쪽에 줌 컨트롤이 표시되도록 지도에 컨트롤을 추가한다.
						self._map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
						
						Logger.debug("DaumMap.create() - complete to loading map sdk.");
						d.resolve();
					});
				}
				, function(err)
				{
					Logger.error("DaumMap.create() - Failed to load the daummap sdk.");
					d.reject(err);
				}
			);
			
			return d.promise();
		}
		,
		destroy: function()
		{
			this._map = null;
			Logger.debug("DaumMap.destroy() - succeeded");
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
				Logger.error("DaumMap.setPosition() - You need to create a Map instance!");
				return;
			}
			
			this._map.setCenter( DaumMap.createPoint(lat, lng) );
		}
		,
		/**
		 * 지도의 중심을 패닝시킨다.
		 * 
		 * @param lat		{ Number } 위도. 가로선.
		 * @param lng		{ Number } 경도. 세로선.
		 */
		panning: function(lat, lng)
		{
			if( this._map == null )
			{
				Logger.error("DaumMap.panning() - You need to create a Map instance!");
				return;
			}

			this._map.panTo( DaumMap.createPoint( lat, lng ) );
		}
		,
		setLevel: function(level)
		{
			if( this._map == null )
			{
				Logger.error("DaumMap.setLevel() - You need to create a Map instance!");
				return;
			}
			
			this._map.setLevel( level );
		}
		,
		addOverlay: function(target)
		{
			if( this._map == null )
			{
				Logger.error("DaumMap.addOverlay() - You need to create a Map instance!");
				return;
			}
			
			target.setMap( this._map );
		}
		,
		activeEvent: function(evtName, cbFunc)
		{
			if( this._map == null )
			{
				Logger.error("DaumMap.activeEvent() - You need to create a Map instance!");
				return;
			}
			
			this._map.attach( evtName, cbFunc );
		}
		,
		/**
		 * 주소를 좌표로 변환한다.
		 */
		getGeocode: function(addr)
		{
			var d = $.Deferred();
			var geocoder = DaumMap.createGeocoder();
			
			geocoder.addr2coord(addr, function(status, result)
			{
				if( status === daum.maps.services.Status.OK )
				{
					Logger.info(result);
					d.resolve(result);
				}
				else
				{
					d.reject();
				}
			});
			
			return d.promise();
		}
		,
		/**
		 * 좌표를 주소로 변환한다.
		 */
		getReverseGeocode: function(lat, lng)
		{
			var d = $.Deferred();
			var geocoder = DaumMap.createGeocoder();
			
			geocoder.coord2addr(DaumMap.createPoint(lat, lng), function(status, result)
			{
				if( status === daum.maps.services.Status.OK )
				{
					Logger.info(result);
					d.resolve(result);
				}
				else
				{
					d.reject();
				}
			});
			
			return d.promise();
		}
		,
		getCenter: function()
		{
			if( this._map == null )
			{
				Logger.error("DaumMap.setPosition() - You need to create a Map instance!");
				return;
			}
			
			return this._map.getCenter();
		}
		,
		//배열의 좌표들이 모두 보이게 지도 범위를 재설정합니다
		//points : [{lat, lng}]
		setBounds : function(posArray)
		{
			var self = this;
			if(posArray == undefined) return;
			
			Logger.debug("map api setBounds");
			Logger.debug(posArray);
			
			var points = [];
			for (i = 0; i < posArray.length; i++) {
				points.push(new daum.maps.LatLng(posArray[i].lat, posArray[i].lng));
			}
			
			// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
			var bounds = new daum.maps.LatLngBounds();    
			
			var i, marker;
			for (i = 0; i < points.length; i++) {
			      // 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
				//marker =     new daum.maps.Marker({ position : points[i] });
				//marker.setMap(self._map);
			  
				// LatLngBounds 객체에 좌표를 추가합니다
				bounds.extend(points[i]);
			}
			
			self._map.setBounds(bounds);
		}
		,
		getBounds : function(){
			
			return this._map.getBounds();

		}
		,
		//배열의 좌표들을 원반경으로 표시합니다
		//points : [{lat, lng}]
		drawCircle : function(posArray)
		{
			var self = this;
			if(posArray == undefined) return;
			
			for (i = 0; i < posArray.length; i++) {
				var circle = new daum.maps.Circle({
				    center : new daum.maps.LatLng(posArray[i].lat, posArray[i].lng),  // 원의 중심좌표 입니다 
				    radius: 50, // 미터 단위의 원의 반지름입니다 
				    strokeWeight: 5, // 선의 두께입니다 
				    strokeColor: '#75B8FA', // 선의 색깔입니다
				    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
				    strokeStyle: 'dashed', // 선의 스타일 입니다
				    fillColor: 'red', // 채우기 색깔입니다
				    fillOpacity: 0.7  // 채우기 불투명도 입니다   
				}); 

				// 지도에 원을 표시합니다 
				circle.setMap(self._map); 
			}
		}
		,
		
		//배열의 좌표들을 원반경으로 표시합니다
		//points : [{lat, lng}]
		polyLine : function(points, lineOpt)
		{
			/**선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
			var linePath = [
			    new daum.maps.LatLng(33.452344169439975, 126.56878163224233),
			    new daum.maps.LatLng(33.452739313807456, 126.5709308145358),
			    new daum.maps.LatLng(33.45178067090639, 126.5726886938753) 
			];
			***/
			var linePath = [];
			$.each(points, function(i, point){
				if(point.lat && point.lng )
				{
					linePath.push( new daum.maps.LatLng( point.lat, point.lng ) );
				}
			});
			
			/*** 지도에 표시할 선을 생성합니다
			 * lineOpt = 
			 * {
				    path: linePath, // 선을 구성하는 좌표배열 입니다
				    strokeWeight: 5, // 선의 두께 입니다
				    strokeColor: '#FFAE00', // 선의 색깔입니다
				    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
				    strokeStyle: 'solid' // 선의 스타일입니다
				}
			 */
			if(linePath.length == 0) return;
			
			lineOpt.path = linePath;
			var polyline = new daum.maps.Polyline(lineOpt);
	
			// 지도에 선을 표시합니다 
			polyline.setMap(this._map);  
			
			return polyline;
	
		}
		,
		//커스텀 오버레이에 표시할 내용입니다     
		// HTML 문자열 또는 Dom Element 입니다 
		customOverlay : function(posArray)
		{
			var self = this;
			if(posArray == undefined) return;
			
			var cutOverObjs = [];
			
			for (i = 0; i < posArray.length; i++) {
				var content = posArray[i].body ;

				// 커스텀 오버레이가 표시될 위치입니다 
				var position = new daum.maps.LatLng(posArray[i].lat, posArray[i].lng);  

				// 커스텀 오버레이를 생성합니다
				var customOverlay = new daum.maps.CustomOverlay({
				    position: position,
				    content: content   
				});

				// 커스텀 오버레이를 지도에 표시합니다
				customOverlay.setMap(self._map);
				
				cutOverObjs.push(customOverlay);
			}
			
			return cutOverObjs;
		}
		,
		//커스텀 오버레이에 표시할 내용입니다     
		// HTML 문자열 또는 Dom Element 입니다 
		customOneOverlay : function(pos, option)
		{
			var self = this;
			
			var content = pos.body ;

			// 커스텀 오버레이가 표시될 위치입니다 
			var position = new daum.maps.LatLng(pos.lat, pos.lng);  

			// 커스텀 오버레이를 생성합니다
			var customOverlay = new daum.maps.CustomOverlay(
			_.extend({
			    position: position,
			    content: content,
			    clickable : true
			}, option));

			// 커스텀 오버레이를 지도에 표시합니다
			customOverlay.setMap(self._map);
			
			return customOverlay;
		}
		,
		/**
		 * remove
		 *  * Marker
		 *  * CustomOverlay 
		 * 
		 * */
		clearObjects : function(items)
		{
			if(items){
				try{
					$.each(items, function(i, itm){
						itm.setMap(null);
					});
				}catch(e){}
			}
		}
	}
	,
	{
		PATH_SDK: 				"http://apis.daum.net/maps/maps3.js?libraries=services&autoload=false&apikey="
		,
		getSDKPath: function(apiKey)
		{
			return DaumMap.PATH_SDK+apiKey;
		}
		,
		createMap: function(container, options)
		{
			return new daum.maps.Map(container, options);
		}
		,
		createPoint: function(lat, lng)
		{
			return new daum.maps.LatLng(lat, lng);
		}
		,
		createSize: function(width, height)
		{
			return new daum.maps.Size(width, height);
		}
		,
		/**
		 * 다음 지도 마커를 생성한다.
		 * 
		 * @param options	{ } 생성자 파라메터
		 * @param point		{ daum.maps.LatLng } 마커가 위치할 좌표 객체. 필요한 경우 지정한다.
		 */
		createMarker: function(options, point)
		{
			var marker = new daum.maps.Marker(options);

			if(point instanceof daum.maps.LatLng)
			{
				marker.setPoint(point);
			}
			
			return marker;
		}
		,
		/**
		 * 마커를 위한 이미지 객체를 생성한다.
		 * 
		 * @param src			{ String } 이미지 경로
		 * @param size			{ daum.maps.Size } 이미지 객체 사이즈
		 * @param options		{ Object } 이미지 생성 옵션
		 * 						{
		 * 						     offset: new daum.maps.Point(16, 34),
		 * 						     alt: "마커 이미지 예제",
		 *  					    shape: "poly",
		 *  					    coords: "1,20,1,9,5,2,10,0,21,0,27,3,30,9,30,20,17,33,14,33"
		 *  					}
		 */
		createImage: function(src, size, options)
		{
			var markerImage = new daum.maps.MarkerImage(src, size, options);

			return markerImage;
		}
		,
		createLabel: function(options, marker)
		{
			var label = new daum.maps.MarkerLabel(options);
			
			if(marker instanceof daum.maps.Marker)
			{
				label.setVisible(true, marker);
			}
			
			return label;
		}
		,
		createWindow: function()
		{
			return new daum.maps.InfoWindow();
		}
		,
		createGeocoder: function()
		{
			return new daum.maps.services.Geocoder();
		}
	});
	
	return DaumMap;
});