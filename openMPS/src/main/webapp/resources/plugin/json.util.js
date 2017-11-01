var JsonUtils = function() {
};

JsonUtils.prototype.getObjects = function(obj, key, val) {
    var objects = [];
    for (var i in obj) {
        if (!obj.hasOwnProperty(i)) continue;
        if (typeof obj[i] == 'object') {
            objects = objects.concat(getObjects(obj[i], key, val));    
        } else 
        //if key matches and value matches or if key matches and value is not passed (eliminating the case where key matches but passed value does not)
        if (i == key && obj[i] == val || i == key && val == '') { //
            objects.push(obj);
        } else if (obj[i] == val && key == ''){
            //only add if the object is not already in the array
            if (objects.lastIndexOf(obj) == -1){
                objects.push(obj);
            }
        }
    }
    return objects;
};

/**
 * key== 특정키 를 갖고있는지 체크하고 찾은 객체를 반환한다. 
 * */
JsonUtils.prototype.getValues = function(obj, key) {
	var self = this;
    var objects = [];
    for (var i in obj) {
        if (!obj.hasOwnProperty(i)) continue;
        if (i == key) {
        	if(obj[i] != "null"  && obj[i] != null)
        		return obj[i];
        }
        else if (typeof obj[i] == 'object') {
        	objects = objects.concat(self.getValues(obj[i], key));
        }
    }
    return objects;
}