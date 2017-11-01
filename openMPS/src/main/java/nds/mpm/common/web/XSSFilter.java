/**
 * 
 */
package nds.mpm.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author dbongman
 *
 */
public class XSSFilter implements Filter 
{
	private static final Logger logger = LoggerFactory.getLogger(XSSFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		logger.debug("XSSFilter.init()");
	}

	@Override
	public void destroy() 
	{
		logger.debug("XSSFilter.destroy()");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		logger.debug("XSSFilter.doFilter()");
		
		String contentType = request.getContentType();
		if(contentType != null && contentType.indexOf("multipart/form-data") > -1 )
		{
			logger.debug( "XSSFilter check contentType : " + contentType );	
			chain.doFilter(request, response); 
		}
		else
		{
			chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
		}
	}

}
