package nds.frm.property;

import java.text.SimpleDateFormat;

/**
 * <b>class : </b> Property
 * <b>Class Description</b><br>
 * property Class
 * <b>History</b><br>
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class Property {
	// �������� RPROPERTY������ 'ġ
	public static final String BIZBUILDERPROPERTY = "com.ttframe.property.TTFrame";
	
	// ��¥ ����� �⺻ ���; d��
	public static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat defaultTimeFormat = new SimpleDateFormat("HHmmss");	

    /**
     * �޽������, �޽����ڵ� �� �޽����; �����ϱ� '�� Delimiter.
     */
    public static final String MESSAGE_DELIMITER = new Character( (char) 0x8).toString();
}
