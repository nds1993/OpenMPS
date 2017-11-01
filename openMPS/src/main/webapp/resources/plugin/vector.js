var Vector = function() {
    this.type = "vector";
    this.length = 0;
    this.data = [];
    this.dataType = [];
    this.attributeName = [];
    this.attributeValue = [];
};

Vector.prototype.add = function( _xml ) {
    try {
        this.data.push( _xml + "" );
        this.length++;
    } catch( e ) {
        alert( e );
    }
};

Vector.prototype.elementAt = function( i ) {
    var ret = null;
    try {
        i = parseInt( i, 10 );
        if ( i >= 0 && i < this.length ) {
            ret = this.data[i];
        }
    } catch( e ) {
        printStackTrace( e );
    }
    return ret;
};

Vector.prototype.remove = function( i ) {
    var ret = null;
    try {
        i = parseInt( i, 10 );
        if ( i >= 0 && i < this.length ) {
            ret = this.data[i];
            this.data.splice( i, 1 );
            this.length--;
        }
    } catch( e ) {
        alert( e );
    }
    return ret;
};

Vector.prototype.size = function() {
    return this.length;
};

Vector.prototype.set = function( i, _xml ) {
    var ret = null;
    try {
        i = parseInt( i, 10 );
        if ( i >= 0 && i < this.length ) {
            this.data[i] = _xml + "";
        }
    } catch( e ) {
        alert( e );
    }
    return ret;
};