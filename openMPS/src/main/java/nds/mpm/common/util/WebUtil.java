package nds.mpm.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WebUtil {
	
	public static String getURL(HttpServletRequest req) 
	{
	    String scheme = req.getScheme();             // http
	    String serverName = req.getServerName();     // hostname.com
	    int serverPort = req.getServerPort();        // 80
	    String contextPath = req.getContextPath();   // /mywebapp
	    String servletPath = req.getServletPath();   // /servlet/MyServlet
	    String pathInfo = req.getPathInfo();         // /a/b;c=123
	    String queryString = req.getQueryString();          // d=789

	    // Reconstruct original requesting URL
	    StringBuffer url =  new StringBuffer();
	    url.append(scheme).append("://").append(serverName);

	    if ((serverPort != 80) && (serverPort != 443)) {
	        url.append(":").append(serverPort);
	    }

	    url.append(contextPath).append(servletPath);

	    if (pathInfo != null) {
	        url.append(pathInfo);
	    }
	    if (queryString != null) {
	        url.append("?").append(queryString);
	    }
	    return url.toString();
	}
	
	public static String readUrl(HttpResponse res) throws Exception 
	{
		HttpEntity entity = res.getEntity();
		String szResult = null;
		   if (entity != null) {
		    
		    szResult = EntityUtils.toString(entity); 
		    
		   }
		
		return szResult;
	}

	public static String getRandomKey(int len)
	{
	    int nSeed = 0;
	    int nSeedSize = 10;
	    String strSrc = "0123456789";
	    String strKey = "";

	    for(int i=0; i<len; i++)
	    {
	        nSeed = (int)(Math.random() * nSeedSize) + 1;
	        strKey += String.valueOf(strSrc.charAt(nSeed-1));
	    }

	    return strKey;
	}
	
	public static String generateString(Random rng, String characters, int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	/**
	 * 랜덤한 문자열을 원하는 길이만큼 반환합니다.
	 * 
	 * @param length 문자열 길이
	 * @return 랜덤문자열
	 */
	public static String getRandomString(int length)
	{
	  StringBuffer	buffer = new StringBuffer();
	  Random		random = new Random();
	 
	  for( int i=0 ; i<length ; i++ )
	  {
		  buffer.append(_chars[random.nextInt( _charLen )]);
	  }
	  return buffer.toString();
	}
	
	private static String	_chars[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	private static int		_charLen = 26;
	
	/**
	 * 현재 서버의 IP 주소를 가져옵니다.
	 * 
	 * @return IP 주소
	 */
	public String getLocalServerIp()
	{
	        try
	        {
	            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
	            {
	                NetworkInterface intf = en.nextElement();
	                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
	                {
	                    InetAddress inetAddress = enumIpAddr.nextElement();
	                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress())
	                    {
	                        return inetAddress.getHostAddress().toString();
	                    }
	                }
	            }
	        }
	        catch( SocketException e )
	        {
	        	System.out.println( e.getMessage() );
	        }
	        
	        return null;
	}
	
	
	/*
	 * method get 서버에서 타 서버의 API를 호출합니다.
	 * Http Get request
	 * 
	 * @param url
	 * 
	 * */
	public static HttpResponse callHttpGet(String callUrl) throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient ();
		HttpGet getRequest = new HttpGet ( callUrl );

		HttpResponse fresponse = httpClient.execute( getRequest ); 
		       
		return fresponse;

	}
	
	/*
	 * method POST 서버에서 타 서버의 API를 호출합니다.
	 * Http POST request
	 * 
	 * @param json String parameter
	 * 
	 * */
	public static HttpResponse callHttpPost(String callUrl, String jsonParam) throws Exception
	{
		List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("parameter", jsonParam));
		
		HttpClient client = new DefaultHttpClient();
	    HttpPost post = new HttpPost(callUrl);
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        HttpResponse fresponse = client.execute(post);
		       
		return fresponse;

	}
	/*
	 * byte[]을   content Type으로 
	 * reponse 
	 * @param byte[] imgBin
	 * 
	 * */
	public static ResponseEntity<byte[]> responseMedia(byte[] imgBin, String contentType) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Content-Type", contentType);
    	return new ResponseEntity<byte[]>(imgBin, headers, HttpStatus.OK);
    }
	
	public static String toCamelCase(String value, boolean startWithLowerCase) {
		String[] strings = StringUtils.split(value.toLowerCase(), "_");
		for (int i = startWithLowerCase ? 1 : 0; i < strings.length; i++) {
			strings[i] = StringUtils.capitalize(strings[i]);
		}
		return StringUtils.join(strings);
	}
}
