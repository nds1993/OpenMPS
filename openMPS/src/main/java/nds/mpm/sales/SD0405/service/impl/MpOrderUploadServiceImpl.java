package nds.mpm.sales.SD0405.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0405.service.MpOrderUploadDAO;
import nds.mpm.sales.SD0405.service.MpOrderUploadService;
import nds.mpm.sales.SD0405.vo.MpOrderUploadDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderUploadVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderUploadServiceImpl.java
 * @Description : MpOrderUpload Business Implement class
 * @Modification Information
 *
 * @author ㅇ
 * @since ㅇ
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Service("mpOrderUploadService")
public class MpOrderUploadServiceImpl extends EgovAbstractServiceImpl implements
		MpOrderUploadService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MpOrderUploadServiceImpl.class);

	@Resource(name = "mpOrderUploadDAO")
	private MpOrderUploadDAO mpOrderUploadDAO;

	/** ID Generation */
	// @Resource(name="{egovMpOrderUploadIdGnrService}")
	// private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_order_upload을 등록한다.
	 * 
	 * @param vo
	 *            - 등록할 정보가 담긴 MpOrderUploadVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public ResultEx insertMpOrderUpload(EgovMap reqVo) throws Exception {

		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		if(StringUtils.isEmpty((String)reqVo.get("dsType")))
		{
			return new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS );
		}
		
		if("C".equals((String)reqVo.get("dsType")) && selectMpOrderUpload(reqVo) != null)
		{
			return new ResultEx( Consts.ResultCode.RC_DUPLICATE );
		}
		
		if("C".equals((String)reqVo.get("dsType")))
		{
			mpOrderUploadDAO.insertMpOrderUpload(reqVo);
		}
		else if("U".equals((String)reqVo.get("dsType")))
		{
			mpOrderUploadDAO.updateMpOrderUpload(reqVo);
		}
		else if("D".equals((String)reqVo.get("dsType")))
		{
			mpOrderUploadDAO.deleteMpOrderUpload(reqVo);
		}

		result.setExtraData(reqVo);
		return result;
	}

	/**
	 * mp_order_upload을 수정한다.
	 * 
	 * @param vo
	 *            - 수정할 정보가 담긴 MpOrderUploadVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateMpOrderUpload(EgovMap vo) throws Exception {
		mpOrderUploadDAO.updateMpOrderUpload(vo);
	}

	/**
	 * mp_order_upload을 삭제한다.
	 * 
	 * @param vo
	 *            - 삭제할 정보가 담긴 MpOrderUploadVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteMpOrderUpload(EgovMap vo) throws Exception {
		mpOrderUploadDAO.deleteMpOrderUpload(vo);
	}

	/**
	 * mp_order_upload을 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 MpOrderUploadVO
	 * @return 조회한 mp_order_upload
	 * @exception Exception
	 */
	public EgovMap selectMpOrderUpload(EgovMap vo)
			throws Exception {
		EgovMap resultVO = mpOrderUploadDAO.selectMpOrderUpload(vo);
		return resultVO;
	}

	/**
	 * mp_order_upload 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return mp_order_upload 목록
	 * @exception Exception
	 */
	public List<?> selectMpOrderUploadList(MpOrderUploadDefaultVO searchVO)
			throws Exception {
		return mpOrderUploadDAO.selectMpOrderUploadList(searchVO);
	}

	/**
	 * 엑셀에서 추출할 셀정보를 읽어온다 mp_order_upload 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return mp_order_upload 목록
	 * @exception Exception
	 */
	public Map selectMpOrderUploadExcelCol(MpOrderUploadDefaultVO searchVO)
			throws Exception {

		Map excelColMap = new HashMap();

		List<?> mpOrderUploadList = mpOrderUploadDAO.selectMpOrderUploadList(searchVO);

		if (mpOrderUploadList != null && mpOrderUploadList.size() == 1) {
			EgovMap metaCol = (EgovMap) mpOrderUploadList.get(0);

			Iterator keys = metaCol.keySet().iterator();
			String key_name = null;
			while (keys.hasNext()) {
				key_name = (String) keys.next();
				if (metaCol.get(key_name) != null) {
					//System.out.println("keys name :" + key_name);
					//System.out.println("keys value :" + metaCol.get(key_name));

					if (key_name != null 
						&& ( key_name.indexOf("col") > -1 || key_name.indexOf("sort") > -1)) {
						excelColMap.put(StringUtils.upperCase(key_name), toCamelCase((String) metaCol.get(key_name), true));
					}
				}
			}
		} else {
			return null;
		}

		return excelColMap;
	}

	/**
	 * mp_order_upload 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 VO
	 * @return mp_order_upload 총 갯수
	 * @exception
	 */
	public int selectMpOrderUploadListTotCnt(MpOrderUploadDefaultVO searchVO) {
		return mpOrderUploadDAO.selectMpOrderUploadListTotCnt(searchVO);
	}

	private String toCamelCase(String value, boolean startWithLowerCase) {
		String[] strings = StringUtils.split(value.toLowerCase(), "_");
		for (int i = startWithLowerCase ? 1 : 0; i < strings.length; i++) {
			strings[i] = StringUtils.capitalize(strings[i]);
		}
		return StringUtils.join(strings);
	}

}
