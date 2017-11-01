/**
 * 
 */
package nds.mpm.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

/**
 * @author dbongman
 *
 */
public class RandomStringGenerator 
{
	protected static final String	RANDOM_ALGORITHM = "SHA1PRNG";
	protected static final String	DIGEST_ALGORITHM = "SHA-1";
	
	private SecureRandom			_random = null;
	private MessageDigest			_digest = null;

	/**
	 * 
	 */
	public RandomStringGenerator() 
	{
	}
	
	/**
	 * 생성기 관련 객체를 초기화한다. 
	 */
	public synchronized void resetGenerator()
	{
		_random = null;
		_digest = null;
	}
	
	/**
	 * 랜덤 객체를 지정한 알고리즘으로 초기화한다.
	 * 
	 * @param algorithm		SecureRandom 알고리즘 이름.
	 */
	public synchronized SecureRandom resetRandom(String algorithm)
	{
		_random = null;
		
		return getSecureRandom( algorithm );
	}
	
	/**
	 * Digest 객체를 지정한 알고리즘으로 초기화한다.
	 * 
	 * @param algorithm		MessageDigest 알고리즘 이름.
	 */
	public synchronized MessageDigest resetDigest(String algorithm)
	{
		_digest = null;
		
		return getDigest( algorithm );
	}
	
	/**
	 * 지정한 길이를 갖는 unique 문자열을 생성한다.
	 * 
	 * @param cbLen		생성될 문자열의 길이. byte 수.
	 * @return			생성된 문자열의 hex 스트링. cbLen * 2 의 길이를 갖는다.
	 */
	public synchronized String generateRandomString(int cbLen)
	{
		
		//System.out.println("generateRandomString start ====" + new Date());
		
		byte[]			buffer = new byte[cbLen];
		StringBuffer	result = new StringBuffer();
		int				retLen = 0;
		
		while( retLen < cbLen )
		{
			getSecureRandom().nextBytes( buffer );
			buffer = getDigest().digest( buffer );
			
			for(
					int j = 0; 
					j < buffer.length && retLen < cbLen; 
					j++ 
				)
            {
                byte b1 = (byte) ((buffer[j] & 0xf0) >> 4);
                
                if (b1 < 10)
                {
                	result.append((char) ('0' + b1));
                }
                else
                {
                	result.append((char) ('A' + (b1 - 10)));
                }

                byte b2 = (byte) (buffer[j] & 0x0f);
                if (b2 < 10)
                {
                	result.append((char) ('0' + b2));
                }
                else
                {
                	result.append((char) ('A' + (b2 - 10)));
                }

                ++retLen;
            }
		}
		
		//System.out.println("generateRandomString end ====" + new Date());
		
		return result.toString();
	}

	private MessageDigest getDigest()
	{
		return getDigest( DIGEST_ALGORITHM );
	}
	
	/**
	 * 지정한 알고리즘으로 구동되는 MD 객체를 얻는다.
	 * 
	 * @param algorithm		MD 알고리즘.
	 * @return				MD 객체.
	 */
	private MessageDigest getDigest(String algorithm)
    {
        if( _digest == null && algorithm != null )
        {
        	//System.out.println("getDigest begin ====" + new Date());
            try
            {
            	_digest = MessageDigest.getInstance( algorithm );
            }
            catch (NoSuchAlgorithmException ex)
            {
                try
                {
                	_digest = MessageDigest.getInstance( DIGEST_ALGORITHM );
                }
                catch (NoSuchAlgorithmException ex2)
                {
                	_digest = null;
                    throw new IllegalStateException("No algorithms for SecureRandomStringGenerator");
                }
            }

            //System.out.println("Using MessageDigest: " + _digest.getAlgorithm());
            
            //System.out.println("getDigest end ====" + new Date());
        }

        return _digest;
    }
	
	private SecureRandom getSecureRandom()
	{
		return getSecureRandom( RANDOM_ALGORITHM );
	}
	
	private SecureRandom getSecureRandom(String algorithm)
	{
		
		//System.out.println("getSecureRandom begin ====" + new Date());
		
	    if( _random == null && algorithm != null )
        {
            try
            {
            	_random = SecureRandom.getInstance( algorithm );
            }
            catch (NoSuchAlgorithmException ex)
            {
                try
                {
                	_random = SecureRandom.getInstance( RANDOM_ALGORITHM );
                }
                catch (NoSuchAlgorithmException ex2)
                {
                	_random = null;
                    throw new IllegalStateException("No algorithms for SecureRandomStringGenerator");
                }
            }
            
            //
            resetRandomSeed( 10 );

           // System.out.println("Using SecureRandom: " + _random.getAlgorithm());
        }

		//System.out.println("getSecureRandom end ====" + new Date());
	    
        return _random;
	}
	
	/**
	 * 생성기 SecureRandom 객체에 seed 값을 셋한다.
	 * 
	 * @param cbLen	초기화할 seed 값의 길이
	 */
	public void resetRandomSeed(int cbLen)
	{
		if( _random != null )
		{
			byte[] seed = _random.generateSeed( cbLen );
	    	_random.setSeed( seed );
		}
	}
}
