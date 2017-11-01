/**
 * 
 */
package nds.mpm.common.util;

import java.util.Random;

/**
 * @author dbongman
 *
 */
public class IDGenerator 
{
	/**
	 * 유니크한 식별자를 초기화한다.
	 * 기본 길이는 2 이다.
	 */
	public IDGenerator() 
	{
		this( 2 );
	}
	
	/**
	 * 유니크한 식별자를 초기화한다.
	 * 길이가 길수록 중복 확률이 낮아진다.
	 * 
	 * @param nIDLen	식별자 길이
	 */
	public IDGenerator(int nIDLen) 
	{
		_nIDLen = nIDLen;
	}
	
	/**
	 * 지정된 길이의 대소문자 구별이 없는 유니크한 식별자를 생성한다.
	 * @return
	 */
	public String genID()
	{
		Random		random = new Random();
		
		return WebUtil.generateString( random, _szRndSeedShort, _nIDLen );
	}
	
	/**
	 * 지정된 길이의 대소문자 식별되는 유니크한 ID 를 생성한다.
	 * @return
	 */
	public String genCasseInsensitiveID()
	{
		Random		random = new Random();
		
		return WebUtil.generateString( random, _szRndSeedLong, _nIDLen );
	}

	public void incIDLength()
	{
		_nIDLen++;
		
		System.out.println("Increase ID Length : " + _nIDLen);
	}
	
	public void setIDLength(int nLen)
	{
		_nIDLen = nLen;
		
		System.out.println("New ID Length : " + _nIDLen);
	}

	protected int				_nIDLen;
	private static String		_szRndSeedShort = "0123456789abcdefghijklmnopqrstuvwxyz";
	private static String		_szRndSeedLong = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
}
