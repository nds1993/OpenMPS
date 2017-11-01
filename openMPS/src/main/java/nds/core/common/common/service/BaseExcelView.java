package nds.core.common.common.service;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;


public abstract class BaseExcelView extends AbstractExcelView {

    protected HSSFSheet sheet = null;
    protected HSSFFont  fontTitle   = null;
    protected HSSFFont  fontHeader  = null;
    protected HSSFFont  fontData    = null;
    protected HSSFFont  fontRed     = null;
    protected HSSFCellStyle  title  = null;
    protected HSSFCellStyle  header = null;
    protected HSSFCellStyle  subHeader = null;

    protected HSSFCellStyle  cellLeft   = null;
    protected HSSFCellStyle  cellRight  = null;
    protected HSSFCellStyle  cellCenter = null;

    protected HSSFDataFormat dataFormat = null; 
    
    protected int row = 0;
    
//    public void setInitial(HSSFWorkbook wb, String SheetName) {
//        this.setInitial(wb, 0, SheetName);
//    }
    
    public HSSFSheet setInitial(HSSFWorkbook wb, int sheetIndex, String SheetName) {
        HSSFSheet iSheet = null;
        iSheet = wb.createSheet(SheetName);
//        wb.setSheetName(sheetIndex, SheetName, HSSFWorkbook.ENCODING_UTF_16);
        
        this.row = 0;

        this.dataFormat = wb.createDataFormat();

        // 타이틀
        this.fontTitle = wb.createFont();
        this.fontTitle.setColor(new HSSFColor.BLACK().getIndex());
        this.fontTitle.setFontHeightInPoints((short) 11);  // set font 1 to 12 point type
        this.fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        this.title = wb.createCellStyle();
        this.title.setFont(this.fontTitle);
        this.title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        this.title.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
        this.title.setFillBackgroundColor(new HSSFColor.GREY_40_PERCENT().getIndex());

        this.fontRed = wb.createFont();
        this.fontRed.setFontHeightInPoints((short) 10);  // set font 1 to 12 point type
        this.fontRed.setColor(new HSSFColor.RED().getIndex());
        this.fontRed.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // 헤더
        this.fontHeader = wb.createFont();
        this.fontHeader.setFontHeightInPoints((short) 10);  // set font 1 to 12 point type
        this.fontHeader.setColor(new HSSFColor.BLACK().getIndex());
        this.fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        this.header = wb.createCellStyle();
        this.header.setFont(this.fontHeader);
        this.header.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        this.header.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        this.header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
        this.header.setFillForegroundColor(new HSSFColor.GREY_40_PERCENT().getIndex());
        this.header.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.header.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        this.header.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.header.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

        this.subHeader = wb.createCellStyle();
        this.subHeader.setFont(this.fontHeader);
        this.subHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        this.subHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        this.subHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
        this.subHeader.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());
        this.subHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.subHeader.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        this.subHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.subHeader.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

        this.fontData = wb.createFont();
        this.fontData.setFontHeightInPoints((short) 10);  // set font 1 to 12 point type
        this.fontData.setColor(new HSSFColor.BLACK().getIndex());
        
        this.cellLeft   = wb.createCellStyle();
        this.cellLeft.setFont(this.fontData);
        this.cellLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);  
        this.cellLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);  
        this.cellLeft.setWrapText(true);
        this.cellLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.cellLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
        this.cellLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.cellLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        this.cellRight  = wb.createCellStyle();
        this.cellRight.setFont(this.fontData);
        this.cellRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);  
        this.cellRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);        
        this.cellRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.cellRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
        this.cellRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.cellRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        this.cellCenter = wb.createCellStyle();
        this.cellCenter.setFont(this.fontData);
        this.cellCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);  
        this.cellCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);        
        this.cellCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.cellCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
        this.cellCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.cellCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        
        return iSheet;
    }

    protected String[] xlsColnumName = null;
    protected String[] xlsColnumAlign = null;
    protected int[] xlsColnumWidth = null;
    
    public int setColumnHeader(HSSFSheet sheet, String[] colInfo) {
        // We put now the headers of the list on the sheet
        HSSFCell cell;
        
        this.xlsColnumName  = new String[colInfo.length];
        this.xlsColnumAlign = new String[colInfo.length];
        this.xlsColnumWidth = new int[colInfo.length];

        String element[] = null;
        
        // 타이틀:타입(C,N,D):정렬(L,C,R):폭(숫자)
        for (int i = 0; i < colInfo.length; i++) {
            element = colInfo[i].toUpperCase().split("[:]");
            switch(element.length) {
                case 1 :
                    xlsColnumName[i] = element[0];
                    xlsColnumAlign[i] = "C";
                    xlsColnumWidth[i] = 100;
                    break;
                case 2 :
                    xlsColnumName[i] = element[0];
                    xlsColnumAlign[i] = element[1];
                    xlsColnumWidth[i] = 100;
                    break;
                case 3 :
                    xlsColnumName[i] = element[0];
                    xlsColnumAlign[i] = element[1];
                    xlsColnumWidth[i] = Integer.parseInt(element[2]);
                    break;
            }
            cell = getCell(sheet, this.row, i);
            cell.setCellStyle(this.header);
//            cell.setEncoding(HSSFCell.ENCODING_UTF_16);         
            cell.setCellValue(xlsColnumName[i]);
            sheet.setColumnWidth((int) i, (int) (xlsColnumWidth[i] * 25.6));
            
        }
        this.row++;
        
        return colInfo.length - 1;
    }
}
