package nds.frm.startup;

import nds.frm.component.Dto;

public class CommonCode extends Dto{

    private static final long serialVersionUID = 1L;

    public CommonCode(String group, String key, String value){
        this.group		= group;
    	this.key 		= key;
        this.value   	= value;
        this.setUID(group + key + value);
    }
    
    private String group;
    private String key;
    private String value;

    public String getUID() {
        return uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
