<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
<title>[${param.app}] Runner Index</title>

<!-- stylesheet begin -->
	<!-- bootstrap CSS -->
	<link rel="stylesheet" href="/plugin/bootstrap/css/bootstrap.min.css"/>
	<!-- bootstrap checkbox & radiobox CSS -->
	<link rel="stylesheet" href="/plugin/checkbox3/checkbox3.min.css"/>
	<!-- 웹아이콘 -->
	<link rel="stylesheet" href="/plugin/fa/css/font-awesome.min.css"/>
	<!-- scroller -->
	<link rel="stylesheet" href="/plugin/mCSB/jquery.mCustomScrollbar.min.css">

	<!-- Trunk Platform  -->
	<link rel="stylesheet" href="/skin/tp_base/tp_base-0.1.0.css">
	<link rel="stylesheet" href="/skin/mps/mps-0.1.0.css">
	<link rel="stylesheet" href="/skin/builder/builder-0.1.0.css">
  	 
<!-- stylesheet end -->

<!-- External Library Begin -->
	<script src="/lib/jquery-1.9.1.js"></script>
	<script src="/lib/jquery.json.js"></script>
	<script src="/lib/jquery.cookie.js"></script>
	
	<!-- PlugIn -->
	<script src="/plugin/sha256.js"></script>
	
	<!-- Framework -->
	<script src="/lib/underscore-1.6.0.js"></script>
	<script src="/lib/backbone-1.1.2.js"></script>
	<script src="/lib/backbone.marionette-1.8.1.js"></script>
	<script src="/lib/require-2.1.15.js"></script>
<!-- External Library End -->

<!-- UCMS SPA -->
<script src="/lib/freecore/spa.platform.js"></script>

<script>
define("test.app/props", function()
{
	var appName = "${param.app}";
	if( appName.length == 0 )
	{
		appName = "home";
	}

	return {
		appRoot: "",
		appPath: "/app/"+appName+"/app.js",
		options:
		{
			debug: true,
			bodyTag: "body",
			hosts: 
			{
				api : "http://localhost:8080",
				resource : "http://localhost:8080"
			}
		}
	};
});

require(
["TPlatform", "test.app/props"],
function(TPlatform, props)
{
	var	App = new TPlatform
	(
		props.appRoot,
		true,
		function()
		{
			TPlatform.startApplication
			(
				props.appPath
				,
				props.options
				,
				null
				,
				function(err)
				{
					if( err )
					{
						alert("웹 페이지 로딩에 실패하였습니다. 인터넷 연결을 확인해 주세요.");
					}
				}
			);
		}
	);
});
</script>
<style>
  .jcrop-active {max-width: 100% !important;
    margin: auto;}
</style>
</head>

<body class="TPJS">
</body>
</html>
