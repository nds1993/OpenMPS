/**
 * 
 */
package nds.mpm.common.web;

/**
 * 
 */
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * URL 파라메터와 Body 로 넘겨지는 사용자 데이타에서 스크립트를 비활성화시킨다.
 * @author dbongman
 *
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper 
{
	private static final Logger logger = LoggerFactory.getLogger(XSSRequestWrapper.class);
	private static Pattern[] patterns = new Pattern[] 
	{
		Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
		Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		Pattern.compile("</script>", Pattern.CASE_INSENSITIVE)
	};
	
	private final String reqBody;

	/**
	 * @param request
	 * @throws IOException 
	 */
	public XSSRequestWrapper(HttpServletRequest request) throws IOException 
	{
		super(request);
		
		String	contentType = request.getContentType();

		if( contentType != null && contentType.indexOf("json") != -1 )
		{
			// application/json
			reqBody = peckRequestBody( request );
		}
		else
		{
			// application/x-www-form-urlencoded
			reqBody = "";
		}
		
		//logger.info("Request Params [ "+request.getContentLength()+" bytes Type : "+contentType+" ]");
		//logger.info("Request Body : "+reqBody);
	}

	@Override
	public String[] getParameterValues(String parameter) 
	{
		logger.debug("getParameterValues : "+parameter);
		
		String[] values = super.getParameterValues(parameter);

		if (values == null) 
		{
			return null;
		}

		int count = values.length;

		String[] encodedValues = new String[count];

		for (int i = 0; i < count; i++) 
		{
			encodedValues[i] = stripScript(values[i]);
		}

		return encodedValues;
	}

	@Override
	public String getParameter(String parameter)
	{
		logger.debug("getParameter : "+parameter);

		return stripScript( super.getParameter(parameter) );

	}

	@Override
	public String getHeader(String name)
	{
		logger.debug("getHeader : "+name);

		return stripScript( super.getHeader(name) );

	}
	
	//
	// Body 를 처리하는 메소드
	//
	
	protected String peckRequestBody(HttpServletRequest request) throws IOException
	{
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		
		try 
		{
			InputStream inputStream = request.getInputStream();
			
			if (inputStream != null) 
			{
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) 
				{
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
			else 
			{
				stringBuilder.append("");
			}
		} 
		catch (IOException ex) 
		{
			throw ex;
		}
		finally 
		{
			if (bufferedReader != null) 
			{
				try 
				{
					bufferedReader.close();
				}
				catch (IOException ex) 
				{
					throw ex;
				}
			}
		}
		
		return stripScript( stringBuilder.toString() );
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException
	{
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(reqBody.getBytes());
		ServletInputStream servletInputStream = new ServletInputStream() 
		{
			public int read() throws IOException 
			{
				return byteArrayInputStream.read();
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		
		return servletInputStream;
	}

	@Override
	public BufferedReader getReader() throws IOException
	{
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

	public String getBody() 
	{
		return reqBody;
	}
	
	//
	// 스크립트 제거
	//

	private String stripScript(String value)
	{
		logger.debug("stripScript: "+value);
		
		if (value != null)
		{
			for (Pattern scriptPattern : patterns)
			{
				Matcher m = scriptPattern.matcher(value);
				
				if ( m.find() )
				{
					logger.warn("html : "+m.group() );
					value=value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
					break;
				}
			}
		}
		
		logger.debug("result: "+value);
		
		return value;
	}
}

