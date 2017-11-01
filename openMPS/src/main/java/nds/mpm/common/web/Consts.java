/**
 * Project : FREECORE Enterprise Java Library
 *
 * Copyright (c) 2012 FREECORE, Inc. All rights reserved.
 */
package nds.mpm.common.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author dbongman
 *
 */
@Service
public class Consts {

	/**
	 * 
	 */
	private static int RC_OK_CODE=0;
	private static String RC_OK_MSG="Completed a requested task!";
	private static int RC_ERROR_CODE=-1;
	private static String RC_ERROR_MSG="Failed with error during a requested task!";
	private static int RC_INVALID_PARAMS_CODE=-2;
	private static String RC_INVALID_PARAMS_MSG="Invalid parameter!";
	private static int RC_FIND_NOT_FOUND_CODE=-3;
	private static String RC_FIND_NOT_FOUND_MSG="Find not found!";
	private static int RC_DUPLICATE_CODE=-4;
	private static String RC_DUPLICATE_MSG="Occurred a Duplicate Data!";
	
	private static int RC_NOT_OWNER_CODE=-100;
	private static String RC_NOT_OWNER_MSG="Not allowed a certificate about target resource!";
	private static int RC_DENINED_CODE=-101;
	private static String RC_DENINED_MSG="The denied your request from target service!";
	
	private static int RC_EXCEPTION_CODE=-200;
	private static String RC_EXCEPTION_MSG="Occurred an exception condition!";
	private static int RC_EXCEPTION_DATAACCESS_CODE=-201;
	private static String RC_EXCEPTION_DATAACCESS_MSG="Occurred an error with database access!";
	
	private static String EN_US_CODE="en_US";
	private static String KO_KR_CODE="ko_KR";
	
	@Value("#{systemProperties['RC_OK_CODE']}")
	private String privRC_OK_CODE;
	@Value("#{systemProperties['RC_OK_MSG']}")
	private String privRC_OK_MSG;
	
	@Value("#{systemProperties['RC_ERROR_CODE']}")
	private String privRC_ERROR;
	@Value("#{systemProperties['RC_ERROR_MSG']}")
	private String privRC_ERROR_MSG;
	
	@Value("#{systemProperties['RC_FIND_NOT_FOUND_CODE']}")
	private String privRC_FIND_NOT_FOUND_CODE;
	@Value("#{systemProperties['RC_FIND_NOT_FOUND_MSG']}")
	private String privRC_FIND_NOT_FOUND_MSG;
	
	@Value("#{systemProperties['RC_INVALID_PARAMS_CODE']}")
	private String privRC_INVALID_PARAMS;
	@Value("#{systemProperties['RC_INVALID_PARAMS_MSG']}")
	private String privRC_INVALID_PARAMS_MSG;
	
	@Value("#{systemProperties['RC_DUPLICATE_CODE']}")
	private String privRC_DUPLICATE_CODE;
	@Value("#{systemProperties['RC_DUPLICATE_MSG']}")
	private String privRC_DUPLICATE_MSG;
	@Value("#{systemProperties['RC_NOT_OWNER_CODE']}")
	private String privRC_NOT_OWNER_CODE;
	@Value("#{systemProperties['RC_NOT_OWNER_MSG']}")
	private String privRC_NOT_OWNER_MSG;
	@Value("#{systemProperties['RC_DENINED_CODE']}")
	private String privRC_DENINED_CODE;
	@Value("#{systemProperties['RC_DENINED_MSG']}")
	private String privRC_DENINED_MSG;
	@Value("#{systemProperties['RC_EXCEPTION_CODE']}")
	private String privRC_EXCEPTION_CODE;
	@Value("#{systemProperties['RC_EXCEPTION_MSG']}")
	private String privRC_EXCEPTION_MSG;
	@Value("#{systemProperties['RC_EXCEPTION_DATAACCESS_CODE']}")
	private String privRC_EXCEPTION_DATAACCESS_CODE;
	@Value("#{systemProperties['RC_EXCEPTION_DATAACCESS_MSG']}")
	private String privRC_EXCEPTION_DATAACCESS_MSG;
	
	@Value("#{systemProperties['EN_US_CODE']}")
	private String privEN_US_CODE;
	@Value("#{systemProperties['KO_KR_CODE']}")
	private String privKO_KR_CODE;
	
	@PostConstruct
	public void init(){
		
		try{
		
			RC_OK_CODE = Integer.parseInt(privRC_OK_CODE);
			RC_OK_MSG = privRC_OK_MSG;
			RC_ERROR_CODE = Integer.parseInt(privRC_ERROR);
			RC_ERROR_MSG = privRC_ERROR_MSG;
			RC_INVALID_PARAMS_CODE = Integer.parseInt(privRC_INVALID_PARAMS);
			RC_INVALID_PARAMS_MSG = privRC_INVALID_PARAMS_MSG;
			RC_FIND_NOT_FOUND_CODE = Integer.parseInt(privRC_FIND_NOT_FOUND_CODE);
			RC_FIND_NOT_FOUND_MSG = privRC_FIND_NOT_FOUND_MSG;
			RC_DUPLICATE_CODE = Integer.parseInt(privRC_DUPLICATE_CODE);
			RC_DUPLICATE_MSG = privRC_DUPLICATE_MSG;
			
			RC_NOT_OWNER_CODE = Integer.parseInt(privRC_NOT_OWNER_CODE);
			RC_NOT_OWNER_MSG = privRC_NOT_OWNER_MSG;
			RC_DENINED_CODE = Integer.parseInt(privRC_DENINED_CODE);
			RC_DENINED_MSG = privRC_DENINED_MSG;
			RC_EXCEPTION_CODE = Integer.parseInt(privRC_EXCEPTION_CODE);
			RC_EXCEPTION_MSG = privRC_EXCEPTION_MSG;
			RC_EXCEPTION_DATAACCESS_CODE = Integer.parseInt(privRC_EXCEPTION_DATAACCESS_CODE);
			RC_EXCEPTION_DATAACCESS_MSG = privRC_EXCEPTION_DATAACCESS_MSG;
			
			EN_US_CODE = privEN_US_CODE;
			KO_KR_CODE = privKO_KR_CODE;
			
		}catch(Exception e){
			
			//e.printStackTrace();
		}
	}
	
	public Consts() 
	{
	}

	public enum ResultCode
	{
		RC_OK(RC_OK_CODE, RC_OK_MSG),
		
		RC_ERROR(RC_ERROR_CODE, RC_ERROR_MSG),
		RC_INVALID_PARAMS(RC_INVALID_PARAMS_CODE, RC_INVALID_PARAMS_MSG),
		RC_FIND_NOT_FOUND(RC_FIND_NOT_FOUND_CODE, RC_FIND_NOT_FOUND_MSG),
		RC_DUPLICATE(RC_DUPLICATE_CODE, RC_DUPLICATE_MSG),
		
		RC_NOT_OWNER(RC_NOT_OWNER_CODE, RC_NOT_OWNER_MSG),
		RC_DENINED(RC_DENINED_CODE, RC_DENINED_MSG),
		
		RC_EXCEPTION(RC_EXCEPTION_CODE, RC_EXCEPTION_MSG),
		RC_EXCEPTION_DATAACCESS(RC_EXCEPTION_DATAACCESS_CODE, RC_EXCEPTION_DATAACCESS_MSG);
		
		private int			_nCode;
		private String		_szDesc;
		
		ResultCode(int nCode)
		{
			_nCode = nCode;
			_szDesc = null;
		}
		
		ResultCode(int nCode, String szDesc)
		{
			_nCode = nCode;
			_szDesc = szDesc;
		}
		
		public int getCode()
		{
			return _nCode;
		}
		
		public String getDesc()
		{
			return _szDesc;
		}
		
		public void setDesc(String szDesc)
		{
			_szDesc = szDesc;
		}
	}
	
	public enum Locale
	{
		NONE(null),
		EN_US(EN_US_CODE),
		KO_KR(KO_KR_CODE);
		
		private String		_locale;
		
		Locale(String locale)
		{
			_locale = locale;
		}
		
		public String getValue()
		{
			return _locale;
		}
		
		public boolean isEqual(String locale)
		{
			if( locale == null )
			{
				return false;
			}
			
			return _locale.compareToIgnoreCase( locale.trim() ) == 0;
		}
		
		public static Locale resolve(String locale)
		{
			return EN_US.isEqual(locale)
					? EN_US
					: KO_KR.isEqual(locale)
					? KO_KR
					: NONE;
		}
	}
}
