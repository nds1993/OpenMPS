/**
 * Project : Mobile Oven
 *
 * Copyright (c) 2014 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman 
 */

define(
[
	"ClientBase", "AResultClasses", "BaroProps", "Logger"
]
,
function(ClientBase, AResult, BaroProps, Logger)
{
	var AppRecipeClient = ClientBase.extend(
	{
		initialize: function()
		{
			AppRecipeClient.__super__.initialize.apply( this, arguments );
			Logger.debug("AppRecipeClient.initialize()");
		}
		,
		fetchRecipe: function(cat1, cat2)
		{
			var self = this;
			var apiPath = this.get("host")+"/2.0/gcon/baroapp/"+cat1+"/"+cat2;

			return this._get( apiPath );
		}
	});
	
	return AppRecipeClient;
});