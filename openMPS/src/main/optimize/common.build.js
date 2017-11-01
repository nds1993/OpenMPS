require.config({
	//The top level directory that contains your app. If this option is used
    //then it assumed your scripts are in a subdirectory under this path.
    //This option is not required. If it is not specified, then baseUrl
    //below is the anchor point for finding things. If this option is specified,
    //then all the files from the app directory will be copied to the dir:
    //output area, and baseUrl will assume to be a relative path under
    //this directory.	
    appDir: "../webapp/modules",
    
    //By default, all modules are located relative to this path. If baseUrl
    //is not explicitly set, then all modules are loaded relative to
    //the directory that holds the build file. If appDir is set, then
    //baseUrl should be specified as relative to the appDir.    
    baseUrl: ".",
    
    //The directory path to save the output. If not specified, then
    //the path will default to be a directory called "build" as a sibling
    //to the build file. All relative paths are relative to the build file.    
    dir: "../webapp/export-min",
    
    //How to optimize all the JS files in the build output directory.
    //Right now only the following values
    //are supported:
    //- "uglify": (default) uses UglifyJS to minify the code.
    //- "uglify2": in version 2.1.2+. Uses UglifyJS2.
    //- "closure": uses Google's Closure Compiler in simple optimization
    //mode to minify the code. Only available if running the optimizer using
    //Java.
    //- "closure.keepLines": Same as closure option, but keeps line returns
    //in the minified files.
    //- "none": no minification will be done.    
    optimize: "uglify2",
    
    //If using UglifyJS for script optimization, these config options can be
    //used to pass configuration values to UglifyJS.
    //See https://github.com/mishoo/UglifyJS for the possible values.
    uglify: {
        toplevel: true,
        ascii_only: true,
        beautify: true,
        max_line_length: 1000,

        //How to pass uglifyjs defined symbols for AST symbol replacement,
        //see "defines" options for ast_mangle in the uglifys docs.
        defines: {
            DEBUG: ['name', 'false']
        },

        //Custom value supported by r.js but done differently
        //in uglifyjs directly:
        //Skip the processor.ast_mangle() part of the uglify call (r.js 2.0.5+)
        no_mangle: true
    },

    //If using UglifyJS for script optimization, these config options can be
    //used to pass configuration values to UglifyJS.
    //For possible values see:
    //http://lisperator.net/uglifyjs/codegen
    //http://lisperator.net/uglifyjs/compress
    uglify2: {
        //Example of a specialized config. If you are fine
        //with the default options, no need to specify
        //any of these properties.
        output: {
            beautify: false
        },
        compress: {
        	sequences     : true,  // join consecutive statemets with the “comma operator”
        	properties    : false,  // optimize property access: a["foo"] → a.foo
        	dead_code     : true,  // discard unreachable code
        	drop_debugger : true,  // discard “debugger” statements
        	unsafe        : true,  // some unsafe optimizations (see below)
        	conditionals  : true,  // optimize if-s and conditional expressions
        	comparisons   : true,  // optimize comparisons
        	evaluate      : true,  // evaluate constant expressions
        	booleans      : true,  // optimize boolean expressions
        	loops         : true,  // optimize loops
        	unused        : true,  // drop unused variables/functions
        	hoist_funs    : true,  // hoist function declarations
        	hoist_vars    : true, // hoist variable declarations
        	if_return     : true,  // optimize if-s followed by return/continue
        	join_vars     : true,  // join var declarations
        	cascade       : true,  // try to cascade `right` into `left` in sequences
        	side_effects  : true,  // drop side-effect-free statements
        	warnings      : true,  // warn about potentially dangerous optimizations/code
        	global_defs   : {}     // global definitions
        },
        warnings: false,
        mangle: true
    },    
    
    //If using Closure Compiler for script optimization, these config options
    //can be used to configure Closure Compiler. See the documentation for
    //Closure compiler for more information.
    closure: {
        CompilerOptions: {},
        CompilationLevel: 'SIMPLE_OPTIMIZATIONS',
        loggingLevel: 'WARNING'
    },
    
    removeCombined: false,
    
    //Introduced in 2.1.2: If using "dir" for an output directory, normally the
    //optimize setting is used to optimize the build bundles (the "modules"
    //section of the config) and any other JS file in the directory. However, if
    //the non-build bundle JS files will not be loaded after a build, you can
    //skip the optimization of those files, to speed up builds. Set this value
    //to true if you want to skip optimizing those other non-build bundle JS
    //files.
    skipDirOptimize: false,
    
    //Introduced in 2.1.1: If a full directory optimization ("dir" is used), and
    //optimize is not "none", and skipDirOptimize is false, then normally all JS
    //files in the directory will be minified, and this value is automatically
    //set to "all". For JS files to properly work after a minification, the
    //optimizer will parse for define() calls and insert any dependency arrays
    //that are missing. However, this can be a bit slow if there are many/larger
    //JS files. So this transport normalization is not done (automatically set
    //to "skip") if optimize is set to "none". Cases where you may want to
    //manually set this value:
    //1) Optimizing later: if you plan on minifying the non-build bundle JS files
    //after the optimizer runs (so not as part of running the optimizer), then
    //you should explicitly this value to "all".
    //2) Optimizing, but not dynamically loading later: you want to do a full
    //project optimization, but do not plan on dynamically loading non-build
    //bundle JS files later. In this case, the normalization just slows down
    //builds, so you can explicitly set this value to "skip".
    //Finally, all build bundles (specified in the "modules" or "out" setting)
    //automatically get normalization, so this setting does not apply to those
    //files.
    normalizeDirDefines: "all",
    
    //As of RequireJS 2.0.2, the dir above will be deleted before the
    //build starts again. If you have a big build and are not doing
    //source transforms with onBuildRead/onBuildWrite, then you can
    //set keepBuildDir to true to keep the previous dir. This allows for
    //faster rebuilds, but it could lead to unexpected errors if the
    //built code is transformed in some way.
    keepBuildDir: false
})