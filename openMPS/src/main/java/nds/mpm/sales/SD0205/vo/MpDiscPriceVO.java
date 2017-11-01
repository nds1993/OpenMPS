package nds.mpm.sales.SD0205.vo;

/**
 * @Class Name : MpDiscPriceVO.java
 * @Description : MpDiscPrice VO class
 * @Modification Information
 *
 * @author n
 * @since n
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpDiscPriceVO extends MpDiscPriceDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** strt_date */
    private java.lang.String strtDate;
    
    /** last_date */
    private java.lang.String lastDate;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** disc_price */
    private java.math.BigDecimal discPrice;
    
    /** sale_price */
    private java.math.BigDecimal salePrice;
    
    /** week_ago_3 */
    private java.math.BigDecimal weekAgo3;
    
    /** week_ago_2 */
    private java.math.BigDecimal weekAgo2;
    
    /** week_ago_1 */
    private java.math.BigDecimal weekAgo1;
    
    /** target_box */
    private java.math.BigDecimal targetBox;
    
    /** mon_ago_3 */
    private java.lang.String monAgo3;
    
    /** mon_sale_3 */
    private java.math.BigDecimal monSale3;
    
    /** mon_rece_3 */
    private java.math.BigDecimal monRece3;
    
    /** mon_misu_3 */
    private java.math.BigDecimal monMisu3;
    
    /** mon_turnover_3 */
    private java.math.BigDecimal monTurnover3;
    
    /** mon_ago_2 */
    private java.lang.String monAgo2;
    
    /** mon_sale_2 */
    private java.math.BigDecimal monSale2;
    
    /** mon_rece_2 */
    private java.math.BigDecimal monRece2;
    
    /** mon_misu_2 */
    private java.math.BigDecimal monMisu2;
    
    /** mon_turnover_2 */
    private java.math.BigDecimal monTurnover2;
    
    /** mon_ago_1 */
    private java.lang.String monAgo1;
    
    /** mon_sale_1 */
    private java.math.BigDecimal monSale1;
    
    /** mon_rece_1 */
    private java.math.BigDecimal monRece1;
    
    /** mon_misu_1 */
    private java.math.BigDecimal monMisu1;
    
    /** mon_turnover_1 */
    private java.math.BigDecimal monTurnover1;
    
    /** salesman */
    private java.lang.String salesman;
    
    /** appro_request */
    private java.sql.Timestamp approRequest;
    
    /** appro_yn */
    private java.lang.String approYn;
    
    /** appro_memo */
    private java.lang.String approMemo;
    
    /** part_code */
    private java.lang.String partCode;
    
    /** part_appro */
    private java.lang.String partAppro;
    
    /** part_date */
    private java.sql.Timestamp partDate;
    
    /** head_code */
    private java.lang.String headCode;
    
    /** head_appro */
    private java.lang.String headAppro;
    
    /** head_date */
    private java.sql.Timestamp headDate;
    
    /** ceo_appro */
    private java.lang.String ceoAppro;
    
    /** ceo_date */
    private java.sql.Timestamp ceoDate;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.sql.Timestamp mdDate;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.sql.Timestamp crDate;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getStrtDate() {
        return this.strtDate;
    }
    
    public void setStrtDate(java.lang.String strtDate) {
        this.strtDate = strtDate;
    }
    
    public java.lang.String getLastDate() {
        return this.lastDate;
    }
    
    public void setLastDate(java.lang.String lastDate) {
        this.lastDate = lastDate;
    }
    
    public java.lang.String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(java.lang.String custCode) {
        this.custCode = custCode;
    }
    
    public java.lang.String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    public java.math.BigDecimal getDiscPrice() {
        return this.discPrice;
    }
    
    public void setDiscPrice(java.math.BigDecimal discPrice) {
        this.discPrice = discPrice;
    }
    
    public java.math.BigDecimal getSalePrice() {
        return this.salePrice;
    }
    
    public void setSalePrice(java.math.BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    
    public java.math.BigDecimal getWeekAgo3() {
        return this.weekAgo3;
    }
    
    public void setWeekAgo3(java.math.BigDecimal weekAgo3) {
        this.weekAgo3 = weekAgo3;
    }
    
    public java.math.BigDecimal getWeekAgo2() {
        return this.weekAgo2;
    }
    
    public void setWeekAgo2(java.math.BigDecimal weekAgo2) {
        this.weekAgo2 = weekAgo2;
    }
    
    public java.math.BigDecimal getWeekAgo1() {
        return this.weekAgo1;
    }
    
    public void setWeekAgo1(java.math.BigDecimal weekAgo1) {
        this.weekAgo1 = weekAgo1;
    }
    
    public java.math.BigDecimal getTargetBox() {
        return this.targetBox;
    }
    
    public void setTargetBox(java.math.BigDecimal targetBox) {
        this.targetBox = targetBox;
    }
    
    public java.lang.String getMonAgo3() {
        return this.monAgo3;
    }
    
    public void setMonAgo3(java.lang.String monAgo3) {
        this.monAgo3 = monAgo3;
    }
    
    public java.math.BigDecimal getMonSale3() {
        return this.monSale3;
    }
    
    public void setMonSale3(java.math.BigDecimal monSale3) {
        this.monSale3 = monSale3;
    }
    
    public java.math.BigDecimal getMonRece3() {
        return this.monRece3;
    }
    
    public void setMonRece3(java.math.BigDecimal monRece3) {
        this.monRece3 = monRece3;
    }
    
    public java.math.BigDecimal getMonMisu3() {
        return this.monMisu3;
    }
    
    public void setMonMisu3(java.math.BigDecimal monMisu3) {
        this.monMisu3 = monMisu3;
    }
    
    public java.math.BigDecimal getMonTurnover3() {
        return this.monTurnover3;
    }
    
    public void setMonTurnover3(java.math.BigDecimal monTurnover3) {
        this.monTurnover3 = monTurnover3;
    }
    
    public java.lang.String getMonAgo2() {
        return this.monAgo2;
    }
    
    public void setMonAgo2(java.lang.String monAgo2) {
        this.monAgo2 = monAgo2;
    }
    
    public java.math.BigDecimal getMonSale2() {
        return this.monSale2;
    }
    
    public void setMonSale2(java.math.BigDecimal monSale2) {
        this.monSale2 = monSale2;
    }
    
    public java.math.BigDecimal getMonRece2() {
        return this.monRece2;
    }
    
    public void setMonRece2(java.math.BigDecimal monRece2) {
        this.monRece2 = monRece2;
    }
    
    public java.math.BigDecimal getMonMisu2() {
        return this.monMisu2;
    }
    
    public void setMonMisu2(java.math.BigDecimal monMisu2) {
        this.monMisu2 = monMisu2;
    }
    
    public java.math.BigDecimal getMonTurnover2() {
        return this.monTurnover2;
    }
    
    public void setMonTurnover2(java.math.BigDecimal monTurnover2) {
        this.monTurnover2 = monTurnover2;
    }
    
    public java.lang.String getMonAgo1() {
        return this.monAgo1;
    }
    
    public void setMonAgo1(java.lang.String monAgo1) {
        this.monAgo1 = monAgo1;
    }
    
    public java.math.BigDecimal getMonSale1() {
        return this.monSale1;
    }
    
    public void setMonSale1(java.math.BigDecimal monSale1) {
        this.monSale1 = monSale1;
    }
    
    public java.math.BigDecimal getMonRece1() {
        return this.monRece1;
    }
    
    public void setMonRece1(java.math.BigDecimal monRece1) {
        this.monRece1 = monRece1;
    }
    
    public java.math.BigDecimal getMonMisu1() {
        return this.monMisu1;
    }
    
    public void setMonMisu1(java.math.BigDecimal monMisu1) {
        this.monMisu1 = monMisu1;
    }
    
    public java.math.BigDecimal getMonTurnover1() {
        return this.monTurnover1;
    }
    
    public void setMonTurnover1(java.math.BigDecimal monTurnover1) {
        this.monTurnover1 = monTurnover1;
    }
    
    public java.lang.String getSalesman() {
        return this.salesman;
    }
    
    public void setSalesman(java.lang.String salesman) {
        this.salesman = salesman;
    }
    
    public java.sql.Timestamp getApproRequest() {
        return this.approRequest;
    }
    
    public void setApproRequest(java.sql.Timestamp approRequest) {
        this.approRequest = approRequest;
    }
    
    public java.lang.String getApproYn() {
        return this.approYn;
    }
    
    public void setApproYn(java.lang.String approYn) {
        this.approYn = approYn;
    }
    
    public java.lang.String getApproMemo() {
        return this.approMemo;
    }
    
    public void setApproMemo(java.lang.String approMemo) {
        this.approMemo = approMemo;
    }
    
    public java.lang.String getPartCode() {
        return this.partCode;
    }
    
    public void setPartCode(java.lang.String partCode) {
        this.partCode = partCode;
    }
    
    public java.lang.String getPartAppro() {
        return this.partAppro;
    }
    
    public void setPartAppro(java.lang.String partAppro) {
        this.partAppro = partAppro;
    }
    
    public java.sql.Timestamp getPartDate() {
        return this.partDate;
    }
    
    public void setPartDate(java.sql.Timestamp partDate) {
        this.partDate = partDate;
    }
    
    public java.lang.String getHeadCode() {
        return this.headCode;
    }
    
    public void setHeadCode(java.lang.String headCode) {
        this.headCode = headCode;
    }
    
    public java.lang.String getHeadAppro() {
        return this.headAppro;
    }
    
    public void setHeadAppro(java.lang.String headAppro) {
        this.headAppro = headAppro;
    }
    
    public java.sql.Timestamp getHeadDate() {
        return this.headDate;
    }
    
    public void setHeadDate(java.sql.Timestamp headDate) {
        this.headDate = headDate;
    }
    
    public java.lang.String getCeoAppro() {
        return this.ceoAppro;
    }
    
    public void setCeoAppro(java.lang.String ceoAppro) {
        this.ceoAppro = ceoAppro;
    }
    
    public java.sql.Timestamp getCeoDate() {
        return this.ceoDate;
    }
    
    public void setCeoDate(java.sql.Timestamp ceoDate) {
        this.ceoDate = ceoDate;
    }
    
    public java.lang.String getMdUser() {
        return this.mdUser;
    }
    
    public void setMdUser(java.lang.String mdUser) {
        this.mdUser = mdUser;
    }
    
    public java.sql.Timestamp getMdDate() {
        return this.mdDate;
    }
    
    public void setMdDate(java.sql.Timestamp mdDate) {
        this.mdDate = mdDate;
    }
    
    public java.lang.String getCrUser() {
        return this.crUser;
    }
    
    public void setCrUser(java.lang.String crUser) {
        this.crUser = crUser;
    }
    
    public java.sql.Timestamp getCrDate() {
        return this.crDate;
    }
    
    public void setCrDate(java.sql.Timestamp crDate) {
        this.crDate = crDate;
    }
    
}
