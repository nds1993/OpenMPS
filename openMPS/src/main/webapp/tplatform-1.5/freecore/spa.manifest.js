/**
 * Project : Trunk-Platform SPA Platform
 *
 * Copyright (c) 2016 FREECORE, Inc. All rights reserved.
 * 
 * @author	dbongman
 */

(function () 
{
    'use strict';

    define(['module', 'Logger'], function (module, Logger)
    {
        var masterConfig = module.config ? module.config() : {};
        
        Logger.log("masterConfig : ");
        Logger.log(masterConfig);
        
        function getModulePath(manifestPath)
        {
        	var modulePath = manifestPath.replace('/manifest.json','');
        	return modulePath;
        }
        
        function BuildToLoad(toLoad, basePath, resourcePath, prefix)
        {
        	if(typeof resourcePath == 'string' && resourcePath.length > 0)
        	{
        		var path = basePath+(resourcePath.charAt(0)=='/'?resourcePath:'/'+resourcePath);
        		toLoad.push( prefix?prefix+path:path );
        		
        		return true;
        	}
        }
        
        function returnError(onLoad, err)
        {
        	Logger.log(err);
    		
    		if (onLoad.error) 
    		{
                onLoad.error(err);
            }
        }

        return {
            version: '1.0.0',
            /**
             * Manifest 기반의 모듈 로딩 방법을 제공한다.
             * @param {string} name -	모듈 Manifest 경로. 루트 경로로 시작한다.
             * 							#widget 또는 #manager 를 붙여서 특정 리소스만 수신할 수 있다.
             */
            load: function (name, req, onLoad, config)
            {
                config = config || {};
                
                Logger.log("SPA.Manifest : ");
                Logger.log(config);
                Logger.log(name);
                
                var partTag = null;
                
                name = name.split('#');
                if( name.length > 1 )
                {
                	partTag = name[1];
                }
                
                name = name[0];
                if( name.indexOf('/manifest.json') == -1 )
                {
                	if( config.paths[name] )
                    {
                    	name = config.paths[name]+'/manifest.json';
                    	
                    	if( typeof partTag == 'string' )
                    	{
                    		name += '#' + partTag;
                    	}
                    	req(['manifest!'+name], function(Module)
                    	{
                    		Logger.log('Manifest Path : '+name);
                    		Logger.log(Module);
                    		
                    		onLoad(Module);
                    	});
                    }
                	else if( name.charAt(0) == '/' || 
                			/^(https?):\/\/([a-z0-9-]+\.)+[a-z0-9]{2,4}.*$/.test(name) )
                	{
                		// Manifest.json 경로를 지정한 경우
                		name = name+'/manifest.json';
                		req(['manifest!'+name], function(Module)
                    	{
                    		Logger.log('Manifest Path : '+name);
                    		Logger.log(Module);
                    		
                    		onLoad(Module);
                    	});
                	}
                	else
                	{
                		returnError(onLoad, 'invalid manifest path : '+name);
                	}
                }
                else
                {
	                var manifestPath = config.baseUrl=='/' ? name: (config.baseUrl + name);
	                var modulePath = getModulePath(manifestPath);
	                
	                Logger.log("Module Path : "+modulePath);
	                Logger.log("SPA.Manifest Path : "+manifestPath);
	                
	                req(['text!'+manifestPath], function(raw)
	                {
	                	var manifest = JSON.parse(raw);
	                	Logger.log( manifest );
	                	manifest.startup || (manifest.startup = {});
	                	manifest.resource || (manifest.resource = {});
	                	manifest.platform || (manifest.platform = {});
	                	
	                	if( UCMS.isCompatible(manifest.platform['spa.platform']) == false )
	                	{
	                		returnError(onLoad, 'Not compatible the SPA Platform! required ver : '+manifest.platform['spa.platform']);
	                		return;
	                	}
	                	
	                	var toLoad = [], loaded = { resource: {} }
	                	
	                	if( BuildToLoad(toLoad, modulePath, manifest.resource.template, 'text!') == true )
	                	{
	                		loaded['resource']['template'] = toLoad.length-1;
	                	}
	                	if( BuildToLoad(toLoad, modulePath, manifest.resource.nls, 'i18n!') == true )
	                	{
	                		loaded['resource']['nls'] = toLoad.length-1;
	                	}
	                	
	                	if( partTag == null || partTag == 'widget' )
	                	{
	                		if( BuildToLoad(toLoad, modulePath, manifest.startup.widget) == true )
		                	{
		                		loaded['widget'] = toLoad.length-1;
		                	}
	                	}
	                	
	                	if( partTag == null || partTag == 'manager' )
	                	{
	                		if( BuildToLoad(toLoad, modulePath, manifest.startup.manager) == true )
		                	{
		                		loaded['manager'] = toLoad.length-1;
		                	}
	                	}
	
	                	Logger.info(toLoad);
	                	req(toLoad, function()
	                	{
	                		if(typeof loaded['resource']['template'] == 'number')
	                		{
	                			loaded['resource']['template'] = arguments[loaded['resource']['template']];
	                			
	                			// 플러그인에서 리소스를 등록해야 중복으로 처리되지 않는다.
	                			UCMSPlatform.SPA.AppMain.initResource( loaded['resource']['template'] );
	                		}
	                		if(typeof loaded['resource']['nls'] == 'number')
	                		{
	                			loaded['resource']['nls'] = arguments[loaded['resource']['nls']];
	                		}
	                		if(typeof loaded['manager'] == 'number')
	                		{
	                			loaded['manager'] = arguments[loaded['manager']];
	                		}
	                		if(typeof loaded['widget'] == 'number')
	                		{
	                			loaded['widget'] = arguments[loaded['widget']];
	                		}
	                		loaded['manifest'] = manifest;
	                		
	                		//
	                		Logger.info(loaded);
	                		if( loaded[partTag] )
	                		{
	                			onLoad( loaded[partTag] );
	                		}
	                		else
	                		{
	                			onLoad( loaded );	
	                		}
	                	});
	                });
                } // endif
            }
        };
    });
}());
