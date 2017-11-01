/**
 * Project : FREECORE Enterprise Java Library
 *
 * Copyright (c) 2012 FREECORE, Inc. All rights reserved.
 */
package nds.mpm.common.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dbongman
 *
 */
public final class Toolkit
{
	protected static String			_cookie_domain = null;
	
	public static void initCookie(String url)
	{
		_cookie_domain = url;
	}
	
	public static void setCookie(HttpServletResponse res, String name, String value, int secTTL)
	{
		Cookie		cook = null;
		
		try
		{
			cook = new Cookie( name, URLEncoder.encode(value, "UTF-8") );
		}
		catch(UnsupportedEncodingException e)
		{
			cook = new Cookie( name, value );
		}
		
		if( _cookie_domain != null )
		{
			cook.setDomain( _cookie_domain );
		}
		cook.setPath("/");
		cook.setMaxAge(secTTL);
		
		res.addCookie(cook);
	}
	
	public static void removeCookie(HttpServletResponse res, String name)
	{
		Cookie		cook;
		
		cook = new Cookie( name, "" );
		cook.setPath("/");
		cook.setMaxAge(0);
		
		if( _cookie_domain != null )
		{
			cook.setDomain( _cookie_domain );
		}
		
		res.addCookie(cook);
	}
	
	public static String getCookie(HttpServletRequest req, String name)
	{
		Cookie[]		cookies = req.getCookies();
		String			value = null;
		
		if( cookies == null )
		{
			return null;
		}
		
		for( Cookie cook : cookies )
		{
			if( cook.getName().compareToIgnoreCase( name ) == 0 )
			{
				value = cook.getValue();
				break;
			}
		}
		
		return value;
	}
	
	/**
	 * 랜덤 숫자를 생성한다.
	 * @param algorithm	원하는 랜덤 숫자 생성 알고리즘 이름. null 인 경우 기본 알고리즘을 사용.
	 * @return
	 */
	public static String randomNum(String algorithm)
	{
		SecureRandom	prng = null;
		
		try 
		{
			if( algorithm != null )
			{
				prng = SecureRandom.getInstance( algorithm );
			}
			else
			{
				prng = new SecureRandom();	
			}
			
			prng.setSeed( prng.generateSeed(20) );
		}
		catch (NoSuchAlgorithmException e) 
		{

		}

		return new Integer( prng.nextInt() ).toString();
	}
	
	public static String md5Hex(String szSrc)
	{
		String		szDst = null;
		
        try
        {
        	MessageDigest md5 = MessageDigest.getInstance("MD5");
        	
        	md5.update( szSrc.getBytes() );
        	szDst = HexEncode( md5.digest() ); 
        } 
        catch (Exception ex) 
        {
        }
        
        return szDst;
	}
	
	public static String HexEncode(byte[] aInput)
	{
	    StringBuilder	result = new StringBuilder();
	    char[]			digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
	    
	    for ( int idx = 0; idx < aInput.length; ++idx )
	    {
			byte b = aInput[idx];
			result.append( digits[ (b&0xf0) >> 4 ] );
			result.append( digits[ b&0x0f] );
	    }
	    
	    return result.toString();
	}

	public static String encodeURIComponent(String s)
	{
		String		r;
		
		try
		{
			r = URLEncoder.encode(s, "UTF-8")
					.replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
		}
		catch(UnsupportedEncodingException e)
		{
			r = s;
		}	
		
		return r;
	}
	
	public static String URLEncode(String s)
	{
		String		r;
		
		try
		{
			r = URLEncoder.encode(s, "UTF-8");
		}
		catch(UnsupportedEncodingException e)
		{
			r = null;
		}	
		
		return r;
	}

	public static String URLDecode(String s)
	{
		String		r;
		
		try
		{
			r = URLDecoder.decode(s, "UTF-8");
		}
		catch(UnsupportedEncodingException e)
		{
			r = null;
		}	
		
		return r;
	}
	
	public static String HttpGet(String szURL)
	{
		String		szResult = "";
		
        try
        {
            URL url = new URL( szURL );
    
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;

            while ((str = in.readLine()) != null)
            {
            	szResult += str;
            }

            in.close();
        } 
        catch (MalformedURLException e)
        {
        	e.printStackTrace();
        	szResult = null;
        } 
        catch (IOException e)
        {
        	e.printStackTrace();
        	szResult = null;
        }
        
        return szResult;
	}
	
	public static String HttpGet(String szURL, Cookie[] cookies)
	{
		String		szResult = "";
		
        try
        {
            URL url = new URL( szURL );
            HttpURLConnection connection = applyCookieProperties( (HttpURLConnection) url.openConnection(), cookies );
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
    		        new InputStreamReader(connection.getInputStream()));
    		String inputLine;
    		StringBuffer response = new StringBuffer();

    		while ((inputLine = in.readLine()) != null) {
    			response.append(inputLine);
    		}
    		in.close();
    		
    		//
    		szResult = response.toString();
        } 
        catch (MalformedURLException e)
        {
        	e.printStackTrace();
        	szResult = null;
        } 
        catch (IOException e)
        {
        	e.printStackTrace();
        	szResult = null;
        }
        
        return szResult;
	}
	
	public static String HttpGet(String szURL, Map<String,String> mapHeader)
	{
		HttpURLConnection	connection = makeHttpConnection( szURL, mapHeader );
		StringBuffer	response = null;
		
		if( connection == null )
		{
			return null;
		}

		try
		{
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("User-Agent", "enterprise-common");
			
			// Send request
			DataOutputStream wr = new DataOutputStream( connection.getOutputStream() );
	
			wr.flush ();
			wr.close ();
	
			// Get Response	
			InputStream		is = connection.getInputStream();
			BufferedReader	rd = new BufferedReader(new InputStreamReader(is));
			
			response = new StringBuffer();
			
			String			line;
			while((line = rd.readLine()) != null)
			{
				response.append(line);
				response.append('\r');
			}
			
			rd.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return response.toString();
	}
	
	public static String HttpPost(String szURL, String szParams, String szContentType)
	{
		URL					url;
		HttpURLConnection	connection = null;
		
		if( szContentType == null )
		{
			szContentType = "application/x-www-form-urlencoded";
		}
		
		try 
		{
			// Create connection
			url = new URL( szURL );
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", szContentType);
			connection.setRequestProperty("Content-Language", "en-US");
		
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			return executePost( connection, szParams );
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String HttpPostByJSON(String szURL, String szBody)
	{
		return HttpPost( szURL, szBody, "application/json" );
	}
	
	public static String HttpPost(String szURL, String szParams, Map<String,String> mapHeader)
	{
		HttpURLConnection	connection = makeHttpConnection( szURL, mapHeader );
		
		if( connection == null )
		{
			return null;
		}

		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		return executePost( connection, szParams );
	}
	
	/**
	 * 지정한 URL 에 대한 Connection 을 생성하고, Http 헤더를 세팅한다.
	 * 
	 * @param szURL
	 * @param mapHeader
	 * @return
	 */
	public static HttpURLConnection makeHttpConnection(String szURL, Map<String,String> mapHeader)
	{
		URL					url = null;
		HttpURLConnection	connection = null;
		
		try 
		{
			url = new URL( szURL );
			connection = (HttpURLConnection) url.openConnection();

			if( mapHeader != null )
			{
				for( Map.Entry<String,String> item : mapHeader.entrySet() )
				{
				    connection.setRequestProperty( item.getKey(), item.getValue() );
				}
			}
		}
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
			return null;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
		return connection;
	}
	/**
	 * 세팅 완료된 connection 으로 POST 를 수행한다.
	 * @param connection
	 * @param szBody
	 * @return
	 */
	public static String executePost(HttpURLConnection connection, String szBody)
	{
		if( connection == null )
		{
			return null;
		}

		try 
		{
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(szBody.getBytes().length));
			connection.setRequestProperty("User-Agent", "enterprise-common");
			
			// Send request
			DataOutputStream wr = new DataOutputStream( connection.getOutputStream() );
			
			wr.writeBytes( szBody );
			wr.flush ();
			wr.close ();

			// Get Response	
			InputStream		is = connection.getInputStream();
			BufferedReader	rd = new BufferedReader(new InputStreamReader(is));
			StringBuffer	response = new StringBuffer();
			
			String			line;
			while((line = rd.readLine()) != null)
			{
				response.append(line);
				response.append('\r');
			}
			
			rd.close();
			return response.toString();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		finally 
		{
			if(connection != null) 
			{
				connection.disconnect(); 
			}
		}
	}
	
	/**
	 * POST 요청을 보낸다.
	 * 요청 결과는 호출한 측에서 connection 을 사용하여 직접 구한다.
	 * 
	 * @param connection
	 * @param szBody
	 * @return
	 */
	public static boolean sendPost(HttpURLConnection connection, String szBody)
	{
		if( connection == null )
		{
			return false;
		}

		try 
		{
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(szBody.getBytes().length));
			connection.setRequestProperty("User-Agent", "enterprise-common");
			
			// Send request
			DataOutputStream wr = new DataOutputStream( connection.getOutputStream() );
			
			wr.writeBytes( szBody );
			wr.flush ();
			wr.close ();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		finally 
		{
			if(connection != null) 
			{
				connection.disconnect(); 
			}
		}
		
		return true;
	}
	
	/**
	 * POST 요청을 수행한다.
	 * 
	 * @param szURL				Post 를 지원하는 End Point
	 * @param szParams			Body 에 담기는 파라메터
	 * @param szContentType		지정하지 않으면 자동으로 "application/x-www-form-urlencoded" 가 지정됨. Form 데이터는 일반적으로 "multipart/form-data"가 사용됨.
	 * @return
	 */
	public static HttpURLConnection RequestPost(String szURL, String szParams, String szContentType)
	{
		URL					url;
		HttpURLConnection	connection = null;
		
		if( szContentType == null )
		{
			szContentType = "application/x-www-form-urlencoded";
		}
		
		try 
		{
			// Create connection
			url = new URL( szURL );

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", szContentType);
			connection.setRequestProperty("Content-Language", "en-US");
		
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			return ( sendPost( connection, szParams ) ? connection : null );
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Connection 에 쿠키 속성을 적용한다.
	 * 
	 * @param conn
	 * @param cookies
	 * @return	쿠키 속성이 적용된 Http Connection.
	 */
	public static HttpURLConnection applyCookieProperties(HttpURLConnection conn, Cookie[] cookies)
	{
		if( cookies == null )
		{
			return conn;
		}
		
		int 		cnt = cookies.length;
		String		value = "";
		
		for( Cookie entry : cookies ) 
		{
			value += String.format("%s=%s", entry.getName(), entry.getValue());
			
			if(--cnt > 0)
			{
				value += "; ";
			}
		}
		
		conn.setRequestProperty("Cookie", value);
		
		return conn;
	}
	
	public static HttpURLConnection RequestPost(String szURL, String szParams, String szContentType, Cookie[] cookies)
	{
		URL					url;
		HttpURLConnection	connection = null;
		
		if( szContentType == null )
		{
			szContentType = "application/x-www-form-urlencoded";
		}
		
		try 
		{
			// Create connection
			url = new URL( szURL );

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", szContentType);
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//
			applyCookieProperties( connection, cookies );
			
			return ( sendPost( connection, szParams ) ? connection : null );
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static HttpURLConnection RequestDelete(String szURL, String szParams, String szContentType, Cookie[] cookies)
	{
		URL					url;
		HttpURLConnection	connection = null;
		
		if( szContentType == null )
		{
			szContentType = "application/x-www-form-urlencoded";
		}
		
		try 
		{
			// Create connection
			url = new URL( szURL );

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", szContentType);
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//
			applyCookieProperties( connection, cookies );
			
			return ( sendDelete( connection, szParams ) ? connection : null );
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean sendDelete(HttpURLConnection connection, String szBody)
	{
		if( connection == null )
		{
			return false;
		}

		try 
		{
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(szBody.getBytes().length));
			connection.setRequestProperty("User-Agent", "enterprise-common");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		finally 
		{
			if(connection != null) 
			{
				connection.disconnect(); 
			}
		}
		
		return true;
	}
}
