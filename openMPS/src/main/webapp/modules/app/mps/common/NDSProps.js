/**
 * Project : OpenMPS MIS with NDS, FREECORE
 */
 
define
(
[]
,
function()
{
	/**
	 * 글로벌 앱 저장소를 구현한다.
	 */
	var	NDSProps =
	{
		init : function(options)
		{
			this.set(options);
		}
	};
	
	/**
	 * 메모리에만 저장되는 저장소
	 */
	var MemAttr = 
	{
		_attributes : {},
		
		/**
		 * 지정한 이름에 해당하는 속성값을 얻는다.
		 * 
		 * @param {string} name - 데이타를 얻을 속성 이름
		 */
		get : function(name)
		{
			return this._attributes[name];
		},
		
		/**
		 * 속성을 저장한다.
		 * 
		 * @param {string|object} name - 속성 이름 또는 저장할 객체. 객체가 지정되는 경우 value 는 사용되지 않는다.
		 * @param {object|string|number|array} value - 속성 이름으로 저장될 데이타.
		 */
		set : function(name, value)
		{
			switch(typeof name)
			{
			case 'string':
				this._attributes[name] = value;
				break;
				
			case 'object':
				_.extend(this._attributes, name);
				break;
			}
		},
		
		/**
		 * 저장된 모든 속성값을 json 형식으로 얻는다.
		 */
		toJSON : function()
		{
			return _.extend({}, this._attributes);
		}
	};
	
	_.extend(NDSProps, Backbone.Events, MemAttr);
	
	return NDSProps;
});