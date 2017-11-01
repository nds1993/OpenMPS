package nds.frm.util;

import java.io.File;
import java.util.ArrayList;

import nds.frm.exception.ExceptionHelper;
import nds.frm.exception.MainException;
import nds.frm.fileupload.ConflictSafeBySeq;
import nds.frm.fileupload.FileUploadPolicy;



import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;

/**
 * <b>class : </b> ExcelUtil
 * <b>Class Description</b><br>
 * Excel을 읽고 생성하는 컴포넌트
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class ExcelUtil {
    public ExcelUtil() {
    }

    /**
     * 엑셀파일을 문자열로 변환
     * <pre>
     * 		엑셀파일의 첫번째 시트를 읽어 컬럼과 로우단위로 구분자를 갖는 문자열을 반환.
     * </pre>
     *
     * @param File excelfile 문자열로 변환하고자 하는 엑셀파일
     * @param String colDelimiter Col 구분자
     * @param String rowDelimiter Row 구분자
     * @param boolean firstSkip   첫번째 레코드 Skip 여부
     * @return 변환된 문자열
     * @since 1.0
     */
    static public String file2String(File excelFile, String colDelimiter, String rowDelimiter, int startRow) throws MainException {

        String targetStr = "";
        Workbook workbook = null;

        try {

            workbook = Workbook.getWorkbook(excelFile); ;

        }
        catch (Exception e) {

            throw ExceptionHelper.getException(e,
                                               "ExcelUtil.file2String()에서 오류발생");

        }

        targetStr = workbook2String(workbook, colDelimiter, rowDelimiter,
                                    startRow);

        workbook.close();

        return (targetStr);

    }

    /**
     * Workbook 객체에 엑셀파일을  문자열로 변환
     * <pre>
     *      Workbook 객체에 엑셀파일을  문자열로 변환
     *      첫번째 시트만 읽는다.
     * </pre>
     *
     * @param Workbook workbook 문자열로 변환하고자 하는 엑셀파일
     * @param String colDelimiter Col 구분자
     * @param String rowDelimiter Row 구분자
     * @param boolean firstSkip   첫번째 레코드 Skip
     * @return 변환된 문자열
     * @since 1.0
     */
    static private String workbook2String(Workbook workbook, String colDelimiter, String rowDelimiter, int startRow) {

        StringBuffer sb = new StringBuffer();
        Sheet sheet = workbook.getSheet(0);
        Cell[] row = null;

        for (int inx = startRow; inx < sheet.getRows(); inx++) {

            row = sheet.getRow(inx);

            if (row.length > 0) {

                if (!row[0].isHidden()) {

                    sb.append(row[0].getContents());

                }

                for (int jnx = 1; jnx < row.length; jnx++) {

                    sb.append(colDelimiter);

                    if (!row[jnx].isHidden()) {

                        sb.append(row[jnx].getContents());

                    }

                }

            }

            sb.append(rowDelimiter);

        }

        return (sb.toString());

    }

    /**
     * Excel파일을 생성(1개의 시트만 생성)
     * @param column String[]           Header정보
     * @param columnType String[]       Column 데이터타입(문자형:C , 숫자형:N , 날짜형:D -- 날짜형 현재 지원안함.)
     * @param contentList ArrayList     Excel의 내용의 담은 객체(ArrayList안에 ArrayList를 담고 있는 형태)
     * @param filePath String           Excel 파일을 저장하기위한 절대 경로(경로+파일명)
     * @param sheetName String          Sheet 이름
     * @return String                   생성된 Excel파일 이름
     */
    static public String createExcel(String[] column, String[] columnType, ArrayList contentList, String filePath, String sheetName) throws MainException{
        String mkFileName = "";
        try {
            int intSheetNo = 0; //시트번호
            if(sheetName.equals("")){
                sheetName = "Sheet1";
            }
            // 1. Excel파일 생성
            jxl.write.WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(filePath));
            jxl.write.WritableSheet sheet = writableWorkbook.createSheet(sheetName, intSheetNo);

            // 2. Excel파일의 기본 설정
            // 2.1 Text 형(Header부분)
            jxl.write.WritableCellFormat textTypeHeader = new WritableCellFormat();
            textTypeHeader.setBackground(jxl.format.Colour.GRAY_25);
            textTypeHeader.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            // 2.2 Text 형(내용부분)
            jxl.write.WritableCellFormat textType = new WritableCellFormat();
            textType.setBackground(jxl.format.Colour.WHITE);
            textType.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            // 2.3 숫자형
            jxl.write.NumberFormat numberFormat = new NumberFormat("#,##0.00");
            jxl.write.WritableCellFormat numberType = new WritableCellFormat(numberFormat);
            numberType.setBackground(jxl.format.Colour.WHITE);
            numberType.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            // 2.3_1 정수형
            jxl.write.NumberFormat intFormat = new NumberFormat("#,##0");
            jxl.write.WritableCellFormat intType = new WritableCellFormat(intFormat);
            intType.setBackground(jxl.format.Colour.WHITE);
            intType.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            // 2.4 날짜형
            jxl.write.DateFormat dateFormat = new DateFormat("yyyy-mm-dd");
            jxl.write.WritableCellFormat dateType = new WritableCellFormat(dateFormat);
            dateType.setBackground(jxl.format.Colour.WHITE);
            dateType.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            // CELL Color & BackGround 지정하기

            // 헤더 정보 생성
            int colnum = 0;
            int rownum = 0;
            Label label;
            for (colnum = 0; colnum < column.length; colnum++) {
                label = new jxl.write.Label(colnum, 0, column[colnum], textTypeHeader);
                sheet.addCell(label);
            }

			//jxl.write.WritableCellFormat cellFormat = null;
            
            WritableCell numCell;
            // Content 구성
            if(contentList != null){
                for (rownum = 1; rownum <= contentList.size(); rownum++) {
                    ArrayList rowList = (ArrayList) contentList.get(rownum - 1);
                    for (colnum = 0; colnum < column.length; colnum++) {
                        if (columnType[colnum].equals("N")) {
                            numCell = new jxl.write.Number(colnum, rownum, Double.parseDouble(StringUtil.null2zeroString( (String) rowList.get(colnum))),
                                numberType);
                            sheet.addCell(numCell);
                        }else if (columnType[colnum].equals("I")) {
                            numCell = new jxl.write.Number(colnum, rownum, Double.parseDouble(StringUtil.null2zeroString( (String) rowList.get(colnum))),
                                intType);
                            sheet.addCell(numCell);
                        }
                        else {
                            numCell = new jxl.write.Label(colnum, rownum, StringUtil.null2void( (String) rowList.get(colnum)), textType);
                            sheet.addCell(numCell);
                        }
                    }
                }
            }
            writableWorkbook.write();
            writableWorkbook.close();
        }
        catch (Exception ex) {
            throw ExceptionHelper.getException(ex, "Excel 파일 생성중 에러 발생");
        }
        return mkFileName;
    }

    /**
     * Excel파일을 생성(1개의 시트만 생성)
     * @param title String              Title정보
     * @param column String[]           Header정보
     * @param columnType String[]       Column 데이터타입(문자형:C , 숫자형:N , 날짜형:D -- 날짜형 현재 지원안함.)
     * @param contentList ArrayList     Excel의 내용의 담은 객체(ArrayList안에 ArrayList를 담고 있는 형태)
     * @param fileNm String             Excel 파일의 이름
     * @param sheetName String          Sheet 이름
     * @return String                   생성된 Excel파일 이름
     */
    static public String createExcel(String title,String[] column, String[] columnType,int[] colWidth, ArrayList contentList, String fileNm, String sheetName) throws MainException{
        String mkFileName = ""; //생성된 파일명
        try {
            String filePath = FileUploadPolicy.UPLOADPATH + "/" + fileNm + ".xls";
            // 같은 이름의 파일이 존재하면 다른 이름으로..
            File file = new File( FileUploadPolicy.UPLOADPATH + "/" + FileUtil.getFileNameChop(filePath));
            file = ConflictSafeBySeq.getConflictSafeFile(file);

            // 파일의 경로
            String dirPath = FileUploadPolicy.UPLOADPATH;
            // 생성된 파일의 명칭
            mkFileName = file.getName();
            filePath = dirPath + "/" + mkFileName;

            int intSheetNo = 0; //시트번호
            if(sheetName.equals("")){
                sheetName = "Sheet1";
            }
            // 1. Excel파일 생성
            jxl.write.WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(filePath));
            jxl.write.WritableSheet sheet = writableWorkbook.createSheet(sheetName, intSheetNo);

            // 2. Excel파일의 기본 설정
            // 2.1 Text 형(Header부분)
            jxl.write.WritableCellFormat textTypeHeader = new WritableCellFormat();
            textTypeHeader.setBackground(jxl.format.Colour.GRAY_25);
            textTypeHeader.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            textTypeHeader.setAlignment(Alignment.CENTRE); //가운데 정렬(수평)
            textTypeHeader.setVerticalAlignment(VerticalAlignment.CENTRE); //가운데 정렬(수직)

            // 2.2 Text 형(내용부분)
            jxl.write.WritableCellFormat textType = new WritableCellFormat();
            textType.setBackground(jxl.format.Colour.WHITE);
            textType.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            textType.setWrap(true); // 자동 줄바꿈
            textType.setAlignment(Alignment.LEFT); //좌측 정렬(수평)

            // 2.3 숫자형
            jxl.write.NumberFormat numberFormat = new NumberFormat("#,##0.00");
            jxl.write.WritableCellFormat numberType = new WritableCellFormat(numberFormat);
            numberType.setBackground(jxl.format.Colour.WHITE);
            numberType.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            numberType.setAlignment(Alignment.RIGHT); //우측 정렬(수평)

            // 2.3_1 정수형
            jxl.write.NumberFormat intFormat = new NumberFormat("#,##0");
            jxl.write.WritableCellFormat intType = new WritableCellFormat(intFormat);
            intType.setBackground(jxl.format.Colour.WHITE);
            intType.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            // 2.4 날짜형
            jxl.write.DateFormat dateFormat = new DateFormat("yyyy-mm-dd");
            jxl.write.WritableCellFormat dateType = new WritableCellFormat(dateFormat);
            dateType.setBackground(jxl.format.Colour.WHITE);
            dateType.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            // 2.5 Text 형(빈 로우부분)
            jxl.write.WritableCellFormat textBlank = new WritableCellFormat();
            textBlank.setBackground(jxl.format.Colour.WHITE);
            //textBlank.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            // CELL Color & BackGround 지정하기

            Label label;
            // Title 구성하기
            label = new jxl.write.Label(0, 0, title, textTypeHeader);
            sheet.addCell(label);
            sheet.setRowView(0,500); // 타이틀 부분의 Row Height
            sheet.mergeCells(0,0,column.length-1,0); //타이틀 부분 셀 병합하기

            // 빈 Row추가하기
            label = new jxl.write.Label(0, 1, "", textBlank);
            sheet.addCell(label);

            // 헤더 정보 생성
            int colnum = 0;
            int rownum = 0;
            for (colnum = 0; colnum < column.length; colnum++) {
                label = new jxl.write.Label(colnum, 2, column[colnum], textTypeHeader);
                sheet.addCell(label);
            }

            WritableCell numCell;
            // Content 구성
            int intRow = 3;
            if(contentList != null){
                for (rownum = 1; rownum <= contentList.size(); rownum++) {
                    ArrayList rowList = (ArrayList) contentList.get(rownum - 1);
                    for (colnum = 0; colnum < column.length; colnum++) {
                        if (columnType[colnum].equals("N")) {
                            numCell = new jxl.write.Number(colnum, intRow, Double.parseDouble(StringUtil.null2zeroString( (String) rowList.get(colnum))),
                                numberType);
                            sheet.addCell(numCell);
                        }else if (columnType[colnum].equals("I")) {
                            numCell = new jxl.write.Number(colnum, intRow, Double.parseDouble(StringUtil.null2zeroString( (String) rowList.get(colnum))),
                                intType);
                            sheet.addCell(numCell);
                        }
                        else {
                            numCell = new jxl.write.Label(colnum, intRow, StringUtil.null2void( (String) rowList.get(colnum)), textType);
                            sheet.addCell(numCell);
                        }
                        sheet.setColumnView(colnum,colWidth[colnum]);
                    }
                    intRow = intRow + 1;
                }
            }
            writableWorkbook.write();
            writableWorkbook.close();
        }
        catch (Exception ex) {
            throw ExceptionHelper.getException(ex, "Excel 파일 생성중 에러 발생");
        }
        return mkFileName;
    }
}
