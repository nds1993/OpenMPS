/**
 * 축산물 이력 포장실적 List 정렬 용 Comparator class
 * 
 * Service level에서 List 정보를 Json으로 변환할 때 정렬되어 있어야 정확한 정보를 축산물 이력 Site로 넘길 수 있음
 * 정렬 기준은 포장일자 -> 묶음번호 순
 * 
 * * MpPighisPackVO는 MP_PIGHIS_PACK_M테이블 객체로 변경해 줘야함!!!
 * * getter method 역시 위 사항에 맞춰 변경해 줘야 함!!!
 * 
 */
package nds.mpm.prod.PP0802.vo;

import java.util.Comparator;

public class MpPighisPackComparator implements Comparator<MpPighisPackVO> {

	@Override
	public int compare(MpPighisPackVO first, MpPighisPackVO second) {
		// TODO Auto-generated method stub
		
		int compVal = 0;
		compVal = first.getPack_date().compareTo(second.getPack_date());
		
		if(compVal == 0)
			compVal = first.getBunch_no().compareTo(second.getBunch_no());
		
		return compVal;
	}

}
