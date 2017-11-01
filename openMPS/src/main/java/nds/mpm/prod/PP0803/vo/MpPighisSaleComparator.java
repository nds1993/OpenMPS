/**
 * 축산물 이력 매출실적 List 정렬 용 Comparator class
 * 
 * Service level에서 List 정보를 Json으로 변환할 때 정렬되어 있어야 정확한 정보를 축산물 이력 Site로 넘길 수 있음
 * 정렬 기준은 매출일자 -> 사업등록번호 -> 묶음번호 순
 * 
 * * MpPighisSaleVO는 MP_PIGHIS_SALE_M테이블 객체로 변경해 줘야함!!!
 * * getter method 역시 위 사항에 맞춰 변경해 줘야 함!!!
 * 
 */
package nds.mpm.prod.PP0803.vo;

import java.util.Comparator;

public class MpPighisSaleComparator implements Comparator<MpPighisSaleVO> {

	@Override
	public int compare(MpPighisSaleVO first, MpPighisSaleVO second) {
		// TODO Auto-generated method stub
		int compVal = 0;
		compVal = first.getSale_date().compareTo(second.getSale_date());
		
		if(compVal == 0)
			compVal = first.getCust_regno().compareTo(second.getCust_regno());
		
		if(compVal == 0)
			compVal = first.getHis_bunch_no().compareTo(second.getHis_bunch_no());
		
		return compVal;
	}

}
