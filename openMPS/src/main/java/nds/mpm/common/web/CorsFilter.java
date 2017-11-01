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
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author dbongman
 * 
 * Cross-Origin Resource Sharing 구현을 위한 필터.
 * 
 * 사용방법 :
 * 
 * 절차 1. web.xml 에 필터 설정 추가
 * 
 * 	<filter>
 *	 <filter-name>cors</filter-name>
 *	 <filter-class>com.freecore.lib.web.CorsFilter</filter-class>
 *	</filter>
 *	
 *	<filter-mapping>
 *	 <filter-name>cors</filter-name>
 *	 <url-pattern>/mbw/*</url-pattern>
 *	</filter-mapping>
 * 
 * 절차 2. API 구현에 CORS 를 위한 반환 헤더 추가
 *
 * 	public ResponseEntity<ResultEx> testAPI(HttpServletResponse response, @RequestBody ReqParam stParam)
 *	{
 *		ResultEx		result = new ResultEx( Consts.ResultCode.RC_OK );
 *		
 *		System.out.println("testAPI()");
 *	
 *       result.setMsg(stParam.desc);
 *      
 *     // CORS "pre-flight" request
 *		HttpHeaders headers = new HttpHeaders();
 *        headers.add("Access-Control-Allow-Origin", "*");
 *		
 *		ResponseEntity<ResultEx> ret = new ResponseEntity<ResultEx>(result, headers, HttpStatus.OK);
 *		
 *		return ret;
 *	}
 */
@Component
public class CorsFilter implements Filter 
{
	private Logger			_log = LoggerFactory.getLogger( CorsFilter.class );
	
	private String			_allowMethods;
	private String			_allowHeaders;
	private String			_maxAge;
	private String			_allowOrigin;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		_log.debug("init() : "+filterConfig.getFilterName());
		
		this._allowHeaders	= "Accept, Content-Type, Authorization, Content-Length, X-Requested-With, unime-token, sp-name, sp-key";
		
		
		this._allowMethods = filterConfig.getInitParameter("allowMethods");
		this._allowHeaders = filterConfig.getInitParameter("allowHeaders");
		if( this._allowHeaders == null )
		{
			this._allowHeaders = "Accept, Content-Type, Authorization, Content-Length, X-Requested-With, unime-token, sp-name, sp-key";
		}
		this._maxAge = filterConfig.getInitParameter("maxAge");
		if( this._maxAge == null )
		{
			// 초 단위로 지정
			this._maxAge = "1800"; // 30 min
		}
		this._allowOrigin = filterConfig.getInitParameter("allowOrigin");
		
		//
		_log.debug("[param] allowMethods : "+this._allowMethods);
		_log.debug("[param] allowHeaders : "+this._allowHeaders);
		_log.debug("[param] maxAge : "+this._maxAge);
		_log.debug("[param] allowOrigin : "+this._allowOrigin);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest	req = (HttpServletRequest) request;
		HttpServletResponse	res = (HttpServletResponse) response;
		
		_log.debug("doFilter() - "+req.getRequestURI());

		//
		// Allow Origin
		//
		if(this._allowOrigin != null)
		{
			res.addHeader("Access-Control-Allow-Origin", this._allowOrigin);	
		}

		//
		// For CORS "pre-flight" request
		//
        if( req.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(req.getMethod()) )
        {
        	_log.debug("Path : "+req.getPathInfo());
        	_log.debug("Allow Method : "+this._allowMethods);
        	_log.debug("Allow Header : "+this._allowHeaders);

        	if( this._allowMethods != null )
        	{
        		res.addHeader("Access-Control-Allow-Methods", this._allowMethods);
        	}
            res.addHeader("Access-Control-Allow-Headers", this._allowHeaders);
			res.addHeader("Access-Control-Max-Age", this._maxAge);
        }
        
        chain.doFilter(req, res);
	}

	@Override
	public void destroy() 
	{
		_log.debug("destroy()");
	}
	
	/**
	 * 리스폰스 헤더에 Access-Control-Allow-Origin 을 세팅한다.
	 * 
	 * @param entity
	 * @return
	 */
	public <T> ResponseEntity<T> makeCORSEntity(T entity)
	{
		HttpHeaders		headers = new HttpHeaders();

		if(this._allowOrigin != null)
		{	
			headers.add("Access-Control-Allow-Origin", this._allowOrigin);
		}
        
		return new ResponseEntity<T>(entity, headers, HttpStatus.OK);
	}
	
}
