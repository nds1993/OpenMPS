HashMap = function(){
 this.HashMap = new Object();
};   
HashMap.prototype = {   
    put : function(key, value){   
        this.HashMap[key] = value;
    },   
    get : function(key){   
        return this.HashMap[key];
    },
    containsKey : function(key){    
     return key in this.HashMap;
    },
    containsValue : function(value){    
     for(var prop in this.HashMap){
      if(this.HashMap[prop] == value) return true;
     }
     return false;
    },
    isEmpty : function(key){    
     return (this.size() == 0);
    },
    clear : function(){   
     for(var prop in this.HashMap){
      delete this.HashMap[prop];
     }
    },
    remove : function(key){    
     delete this.HashMap[key];
    },
    keys : function(){   
        var keys = new Array();   
        for(var prop in this.HashMap){   
            keys.push(prop);
        }   
        return keys;
    },
    values : function(){   
     var values = new Array();   
        for(var prop in this.HashMap){   
         values.push(this.HashMap[prop]);
        }   
        return values;
    },
    size : function(){
      var count = 0;
      for (var prop in this.HashMap) {
        count++;
      }
      return count;
    }
};