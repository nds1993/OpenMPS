/**
 * 축산물 이력 매입실적 List 정렬 용 Comparator class
 * 
 * Service level에서 List 정보를 Json으로 변환할 때 정렬되어 있어야 정확한 정보를 축산물 이력 Site로 넘길 수 있음
 * 정렬 기준은 매입일자 -> 사업등록번호 -> 이력번호 순
 * 
 * * MpPighisBuyVO는 MP_PIGHIS_BUY_M테이블 객체로 변경해 줘야함!!!
 * * getter method 역시 위 사항에 맞춰 변경해 줘야 함!!!
 * 
 */

package nds.mpm.prod.PP0801.vo;

import java.util.Comparator;

public class MpPighisBuyComparator implements Comparator<MpPighisBuyVO> {

	@Override
	public int compare(MpPighisBuyVO first, MpPighisBuyVO second) {
		// TODO Auto-generated method stub
		int compVal = 0;
		compVal = first.getBuy_date().compareTo(second.getBuy_date());
		
		if(compVal == 0)
			compVal = first.getComp_ssno().compareTo(second.getComp_ssno());
		
		if(compVal == 0)
			compVal = first.getHis_no().compareTo(second.getHis_no());
		
		return compVal;
	}
	

}
