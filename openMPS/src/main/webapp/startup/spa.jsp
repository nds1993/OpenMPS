<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SPA</title>

<!-- stylesheet begin -->  	 
<!-- stylesheet end -->

<!-- External Library Begin -->
<script src="/lib/jquery-1.9.1.js"></script>
<script src="/lib/require-2.1.15.js"></script>
<!-- External Library End -->

<script>

define(
"SPAMain"
, function()
{
	var SPA = 
	{
		canvas: null,
		
		startApplication: function()
		{
			SPA.canvas = new CanvasPanel();
			SPA.canvas.render();
			
			var $btn = $("<div class=button></div>");
			
			var $A = $("<a> PanelA </a>").click(function()
			{
				SPA.canvas.show(new PanelA());
			});
			$btn.append($A);
			
			var $B = $("<a> PanelB </a>").click(function()
			{
				SPA.canvas.show(new PanelB());
			});
			$btn.append($B);
			
			var $C = $("<a> PanelC </a>").click(function()
			{
				SPA.canvas.show(new PanelC());
			});
			$btn.append($C);
			
			$("body").append($btn);
			$("body").append( SPA.canvas.get$el() );
		}
	};
	
	var Base = function()
	{
		console.log("Base");
		this._el = "<div></div>";
		this._$el = null;
	};
	Base.prototype = 
	{
		_template: function()
		{
			return this._el;
		}
		,
		get$el: function()
		{
			return this._$el;
		}
		,
		render: function()
		{
			var el = this._template();
			this._$el = $("<div></div>");
			this._$el.html(el);
		}
		,
		show: function(panel)
		{
			if( this._$el == null )
			{
				return;
			}
			
			panel.render();
			this._$el.empty();
			this._$el.append(panel.get$el());
		}
	};
	
	var PanelA = function()
	{
		Base.call( this );
		console.log("PanelA");
		
	};
	PanelA.prototype = new Base();
	PanelA.prototype.constructor = PanelA;
	PanelA.prototype._template = function()
	{
		return "<img src='/app/intro/res/kakao9.jpeg'>";
	};
	
	var PanelB = function()
	{
		Base.call( this );
		console.log("PanelB");
	};
	PanelB.prototype = new Base();
	PanelB.prototype.constructor = PanelB;
	PanelB.prototype._template = function()
	{
		return "<img src='/app/intro/res/kakao8.jpeg'>";
	};
	
	var PanelC = function()
	{
		Base.call( this );
		console.log("PanelC");
	};
	PanelC.prototype = new Base();
	PanelC.prototype.constructor = PanelC;
	PanelC.prototype._template = function()
	{
		return "<img src='/app/intro/res/kakao7.jpeg'>";
	};
	
	var CanvasPanel = function()
	{
		Base.call( this );
		console.log("CanvasPanel");
	};
	CanvasPanel.prototype = new Base();
	CanvasPanel.prototype.constructor = CanvasPanel;
	
	return SPA;
});
		
require(
["SPAMain"],
function(TPlatform)
{
	TPlatform.startApplication();
});
</script>
</head>
<body>
</body>
</html>
