package nds.mpm.common.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @Class Name : EgovComExcepHndlr.java
 * @Description : MPM 공통서비스의 exception 처리 클래스, RESTFUL 호출클라이언트로 Exception ResultEx 반환
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *
 * @author MPM TEAM
 * @since 2017.06.27
 * @version
 * @see
 *
 */
public class TMMControllerExcepHndlr {

    protected Log log = LogFactory.getLog(this.getClass());

    @Autowired
	protected CorsFilter		_filter;
    /*
     */
    /**
     * 발생된 Exception을 처리한다.
     */
    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResultEx> exceptionHandler(
    		HttpServletRequest req,
    		HttpServletResponse res,
    		Exception ex) {
    	
    	log.debug(" MPMControllerExcepHndlr Exception...............");
    	
    	ex.printStackTrace();
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_EXCEPTION );
    	result.setExtraData(ex.getMessage());
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
     * 발생된 SQLException을 처리한다.
     */
    @ExceptionHandler(value=SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResultEx> sqlExceptionHandler(
    		HttpServletRequest req,
    		HttpServletResponse res,
    		SQLException ex) {
    	
    	log.debug(" MPMControllerExcepHndlr SQLException...............");
    	
    	ex.printStackTrace();
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_EXCEPTION_DATAACCESS );
    	result.setExtraData(ex.getMessage());
    	
    	return _filter.makeCORSEntity( result );
    }
}
