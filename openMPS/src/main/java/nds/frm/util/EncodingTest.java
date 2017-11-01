package nds.frm.util;


public class EncodingTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try{
       
        String koStr = "PMS-030201-20(주간업무보고-2011년7월4째주).doc";
        String charsetArr[] = {"euc-kr", "ksc5601", "iso-8859-1", "8859_1", "ascii", "UTF-8","EUC-KR", "utf-8", "KSC5601"};
        for(int i=0; i<charsetArr.length; i++) {
            for(int j=0 ; j<charsetArr.length; j++) {
                if(i == j) {
                    continue;
                } else {
                    System.out.print(koStr + " >>> "+charsetArr[i] + " : " + charsetArr[j] + " : " + new String(koStr.getBytes(charsetArr[i]), charsetArr[j]) + "\n");
                }
            }
        } 
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
