package nds.frm.property;

import java.util.Enumeration;
import java.util.Properties;

import nds.frm.exception.MainException;




public interface IPropertyUtil {
	Enumeration getTTFrameProp() throws MainException;
	String getTTFrameProp(String attrName);
	Properties getProperties(String filename) throws MainException;
}
