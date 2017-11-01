/**
 * 
 */
package nds.mpm.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * property 파일에 대한 access point 를 제공한다.
 * 
 * @author dbongman
 *
 */
@Component
public class EnvProperties 
{
	@Autowired
    private Environment		_env;
	
	public String getPropertyForString(String name)
	{
		return _env.getProperty( name );
	}
	
	public long getPropertyForLong(String name)
	{
		return _env.getProperty( name, Long.class );
	}
	
	public int getPropertyForInt(String name)
	{
		return _env.getProperty( name, Integer.class );
	}
}
