/**
 * 
 */
package nds.mpm.common.vo;

import nds.mpm.common.web.Consts;
import nds.mpm.common.web.Consts.ResultCode;

/**
 * @author 
 *
 */
public class ResultEx extends Result 
{
	public ResultEx()
	{
		
	}
	
	public ResultEx(Object exData)
	{
		super( Consts.ResultCode.RC_OK );
		this.extraData = exData;
	}

	/**
	 * @param eCode
	 */
	public ResultEx(ResultCode eCode) {
		super(eCode);
	}

	public final Object getExtraData() {
		return extraData;
	}

	public final void setExtraData(Object extraData) {
		this.extraData = extraData;
	}
	
	private Object			extraData;
}
