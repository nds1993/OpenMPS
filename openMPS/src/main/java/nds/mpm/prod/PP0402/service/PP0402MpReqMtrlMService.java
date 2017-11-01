package nds.mpm.prod.PP0402.service;

import java.util.List;

import nds.mpm.prod.PP0401.vo.MpReqMtrlMDefaultVO;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMVO;

/**
 * @Class Name : MpReqMtrlMService.java
 * @Description : MpReqMtrlM Business class
 * @Modification Information
 *
 * @author 123
 * @since 123
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PP0402MpReqMtrlMService {
	
	public List selectProcodeMtrlCodeMpReqMtrlMList_D(MpReqMtrlMDefaultVO searchVO) throws Exception;

    public int selectProcodeMtrlCodeMpReqMtrlMListTotCnt_S(MpReqMtrlMDefaultVO searchVO) ;

    public List selectMtrlCodeMpReqMtrlMList_D(MpReqMtrlMDefaultVO searchVO) throws Exception ;
    public int selectMtrlCodeMpReqMtrlMListTotCnt_S(MpReqMtrlMDefaultVO searchVO) ;
}
