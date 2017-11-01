package nds.frm.util;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

/**
 * <p>Title: Encrypt</p>
 * <p>Description: 암호화 처리를 담당하는 Utility</p>
 * <p><b>Histroy<b></p>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class Encrypt {
    public Encrypt() {
    }

    static void des_key(long ek[], String myKey) {
        deskey(myKey, (short) 0);
        cpkey(ek);
        deskey(myKey, (short) 1);
        cpkey(dk);
    }

    static void DES_SetKey(String keyVal) {
        des_key(ek, keyVal);
    }

    static void deskey(String KeyStr, short edf) {
        char pclm[] = new char[56];
        char pcr[] = new char[56];
        long kn[] = new long[32];
        char mKey[] = KeyStr.toCharArray();
        
        //주석해제 시작_2010-02-02
        for (int j = 0; j < 56; j++) {
            int l = pc1[j];
            int m = l & 7;
            int mk = mKey[l >> 3];
            int ik = mk & bytebit[m];

            if (ik == 0)
                pclm[j] = '\0';
            else
                pclm[j] = '\001';

			@SuppressWarnings("unused")
            int bj = pclm[j];
        }
        //주석해제 종료_2010-02-02
         
        
        for (int i = 0; i < 16; i++) {
            int m;

            if (edf == 1)
                m = 15 - i << 1;
            else
                m = i << 1;

            int n = m + 1;
            kn[m] = kn[n] = 0L;

            for (int j = 0; j < 28; j++) {
                int l = j + totrot[i];
                if (l < 28)
                    pcr[j] = pclm[l];
                else
                    pcr[j] = pclm[l - 28];
            }

            for (int j = 28; j < 56; j++) {
                int l = j + totrot[i];
                if (l < 56)
                    pcr[j] = pclm[l];
                else
                    pcr[j] = pclm[l - 28];
            }

            for (int j = 0; j < 24; j++) {
                int p2 = pcr[pc2[j]];
                if (p2 != 0)
                    kn[m] |= bigbyte[j];
                p2 = pcr[pc2[j + 24]];
                if (p2 != 0)
                    kn[n] |= bigbyte[j];
            }
        }
        cookey(kn);
    }

    static void cookey(long raw1[]) {
        long dough[] = new long[32];
        long cook[] = dough;
        long raw0[] = raw1;
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < 16) {
            raw0[i] = raw1[j++];
            cook[k] = (raw0[i] & 0xfc0000L) << 6;
            cook[k] |= (raw0[i] & 4032L) << 10;
            cook[k] |= (raw1[j] & 0xfc0000L) >> 10;
            cook[k++] |= (raw1[j] & 4032L) >> 6;
            cook[k] = (raw0[i] & 0x3f000L) << 12;
            cook[k] |= (raw0[i] & 63L) << 16;
            cook[k] |= (raw1[j] & 0x3f000L) >> 4;
            cook[k++] |= raw1[j] & 63L;
            i++;
            j++;
        }
        usekey(dough);
    }

    static void usekey(long from[]) {
        long to[] = KnL;
        for (int i = 0; i < 32; i++)
            to[i] = from[i];
    }

    static void cpkey(long into[]) {
        long from[] = KnL;
        for (int i = 0; i < 32; i++)
            into[i] = from[i];
    }

    static void desfunc(long block[], long keys[]) {
        int round = 0;
        long leftt = block[0];
        long right = block[1];
        long work = (leftt >> 4 ^ right) & 0xf0f0f0fL;
        right ^= work;
        leftt ^= work << 4;
        work = (leftt >> 16 ^ right) & 65535L;
        right ^= work;
        leftt ^= work << 16;
        work = (right >> 2 ^ leftt) & 0x33333333L;
        leftt ^= work;
        right ^= work << 2;
        work = (right >> 8 ^ leftt) & 0xff00ffL;
        leftt ^= work;
        right ^= work << 8;
        right = (right << 1 | right >> 31 & 1L) & 0xffffffffL;
        work = (leftt ^ right) & 0xaaaaaaaaL;
        leftt ^= work;
        right ^= work;
        leftt = (leftt << 1 | leftt >> 31 & 1L) & 0xffffffffL;
        int i = 0;

        for (round = 0; round < 8; round++) {
            work = right << 28 | right >> 4;
            work ^= keys[i++];
            long fval = SP7[(int) (work & 63L)];
            fval |= SP5[(int) (work >> 8 & 63L)];
            fval |= SP3[(int) (work >> 16 & 63L)];
            fval |= SP1[(int) (work >> 24 & 63L)];
            work = right ^ keys[i++];
            fval |= SP8[(int) (work & 63L)];
            fval |= SP6[(int) (work >> 8 & 63L)];
            fval |= SP4[(int) (work >> 16 & 63L)];
            fval |= SP2[(int) (work >> 24 & 63L)];
            leftt ^= fval;
            work = leftt << 28 | leftt >> 4;
            work ^= keys[i++];
            fval = SP7[(int) (work & 63L)];
            fval |= SP5[(int) (work >> 8 & 63L)];
            fval |= SP3[(int) (work >> 16 & 63L)];
            fval |= SP1[(int) (work >> 24 & 63L)];
            work = leftt ^ keys[i++];
            fval |= SP8[(int) (work & 63L)];
            fval |= SP6[(int) (work >> 8 & 63L)];
            fval |= SP4[(int) (work >> 16 & 63L)];
            fval |= SP2[(int) (work >> 24 & 63L)];
            right ^= fval;
        }
        right = right << 31 | right >> 1;
        work = (leftt ^ right) & 0xaaaaaaaaL;
        leftt ^= work;
        right ^= work;
        leftt = leftt << 31 | leftt >> 1;
        work = (leftt >> 8 ^ right) & 0xff00ffL;
        right ^= work;
        leftt ^= work << 8;
        work = (leftt >> 2 ^ right) & 0x33333333L;
        right ^= work;
        leftt ^= work << 2;
        work = (right >> 16 ^ leftt) & 65535L;
        leftt ^= work;
        right ^= work << 16;
        work = (right >> 4 ^ leftt) & 0xf0f0f0fL;
        leftt ^= work;
        right ^= work << 4;
        block[0] = right;
        block[1] = leftt;
    }

    static void scrunch(char outof[], long into[]) {
        if (outof.length > 0) {
            int i = 0;
            if (outof.length > i)
                into[0] = ((long) outof[i++] & 255L) << 24;
            if (outof.length > i)
                into[0] |= ((long) outof[i++] & 255L) << 16;
            if (outof.length > i)
                into[0] |= ((long) outof[i++] & 255L) << 8;
            if (outof.length > i)
                into[0] |= (long) outof[i++] & 255L;
            if (outof.length > i)
                into[1] = ((long) outof[i++] & 255L) << 24;
            if (outof.length > i)
                into[1] |= ((long) outof[i++] & 255L) << 16;
            if (outof.length > i)
                into[1] |= ((long) outof[i++] & 255L) << 8;
            if (outof.length > i)
                into[1] |= (long) outof[i] & 255L;
        } else {
            into[0] = 0L;
            into[1] = 0L;
            return;
        }
    }

    static String unscrun(long outof[], String into) {
        into = "";
        into = into + (char) (int) (outof[0] >> 24 & 255L);
        into = into + (char) (int) (outof[0] >> 16 & 255L);
        into = into + (char) (int) (outof[0] >> 8 & 255L);
        into = into + (char) (int) (outof[0] & 255L);
        into = into + (char) (int) (outof[1] >> 24 & 255L);
        into = into + (char) (int) (outof[1] >> 16 & 255L);
        into = into + (char) (int) (outof[1] >> 8 & 255L);
        into = into + (char) (int) (outof[1] & 255L);
        return into;
    }

    static String des_dec(String dat, int blocks) {
        long work[] = new long[2];
        int share = 0;
        //int subArg = 0;
        String myStr = new String();
        String resStr = "";
        char cp[] = dat.toCharArray();
        int size = cp.length;
        share = size / 8;

        for (int i = 0; i < blocks; i++) {
            scrunch(cp, work);
            desfunc(work, dk);
            myStr = unscrun(work, String.valueOf(cp));

            if (share > 0) {
                dat = dat.substring(8);
                //int nSize = dat.length();
                char nCp[] = dat.toCharArray();
                cp = nCp;
            } else {
                dat = "";
                cp = dat.toCharArray();
            }
            share--;
            resStr = resStr + myStr;
        }
        return resStr;
    }

    static String des_enc(String dat, int blocks) {
        long work[] = new long[2];
        int share = 0;
        //int subArg = 0;
        String myStr = new String();
        String resStr = "";
        char cp[] = dat.toCharArray();
        int size = cp.length;
        share = size / 8;

        for (int i = 0; i < blocks; i++) {
            scrunch(cp, work);
            desfunc(work, ek);
            myStr = unscrun(work, String.valueOf(cp));

            if (share > 0) {
                dat = dat.substring(8);
                //int nSize = dat.length();
                char nCp[] = dat.toCharArray();
                cp = nCp;
            } else {
                dat = "";
                cp = dat.toCharArray();
            }
            share--;
            resStr = resStr + myStr;
        }
        return resStr;
    }

    static String DES_Encrypt(String pchDataBuf, int nNumOfBlocks) {
        return des_enc(pchDataBuf, nNumOfBlocks);
    }

    static String StrToHexBlk(String pSrc, int SrcLen) {
        char ch = ' ';
        String hstr = "";
        String pHexHead = "";
        StringBuffer pHex = new StringBuffer();
        hstr = "0123456789abcdef";

        for (int i = 0; i < pSrc.length(); i++) {
            ch = pSrc.charAt(i);
            pHex.append(hstr.charAt((ch & 0xf0) >> 4));
            pHex.append(hstr.charAt(ch & 0xf));
        }
        pHexHead = pHex.toString();
        return pHexHead;
    }

    public static String com_EncodeJtoJ(String pPlainText) {
        String pEncKey = "";
        String pCT = "";
        String pCTHex = "";
        int iCTLen = 0;
        int iBlkSize = 0;
        pPlainText = pPlainText + "|";
        pPlainText = Ko2En(pPlainText);
        pEncKey = "SINGLE_v_4.0_OFFICE_v_4.0";
        iCTLen = pPlainText.length() + 8;
        iBlkSize = (iCTLen - 1) / 8 + 1;
        iCTLen = iBlkSize * 8 + 1;
        pCT = pPlainText;
        DES_SetKey(pEncKey);
        pCT = DES_Encrypt(pCT, iBlkSize);
        pCTHex = StrToHexBlk(pCT, iBlkSize * 8);
        int pCTHexNo = pCTHex.length();

        for (int i = 0; i < iBlkSize * 2 - pCTHexNo; i++)
            pCTHex = pCTHex + "0";
        return pCTHex;
    }

    public static String com_Encode(String pPlainText) {
        String pEncKey = "";
        String pCT = "";
        String pCTHex = "";
        int iCTLen = 0;
        int iBlkSize = 0;
        pPlainText = Ko2En(pPlainText);
        pEncKey = "SINGLE_v_4.0_OFFICE_v_4.0";
        iCTLen = pPlainText.length() + 8;
        iBlkSize = (iCTLen - 1) / 8 + 1;
        iCTLen = iBlkSize * 8 + 1;
        pCT = pPlainText;
        DES_SetKey(pEncKey);
        pCT = DES_Encrypt(pCT, iBlkSize);
        pCTHex = StrToHexBlk(pCT, iBlkSize * 8);
        int pCTHexNo = pCTHex.length();

        for (int i = 0; i < iBlkSize * 2 - pCTHexNo; i++)
            pCTHex = pCTHex + "0";
        return pCTHex;
    }

    static String HexToStrBlk(String pHex) {
        int i = 0;
        int SrcLen = 0;
        int hv = 0;
        
		@SuppressWarnings("unused")
        String pStrHead = "";
        
        String pStr = "";
        SrcLen = pHex.length();

        for (i = 0; i < SrcLen; i++) {
            if (pHex.charAt(i) >= '0' && pHex.charAt(i) <= '9')
                hv = pHex.charAt(i) - 48;
            else
                hv = (pHex.charAt(i) - 97) + 10;

            hv *= 16;
            i++;

            if (pHex.charAt(i) >= '0' && pHex.charAt(i) <= '9')
                hv += pHex.charAt(i) - 48;
            else
                hv += (pHex.charAt(i) - 97) + 10;

            pStr = pStr + (char) hv;
        }
        return pStr;
    }

    public static String com_DecodeJtoJ(String pCryptHex) {
        String pCT = "";
        String pEncKey = "";
        int iCTLen = 0;
        int iBlkSize = 0;
        pEncKey = "SINGLE_v_4.0_OFFICE_v_4.0";
        iCTLen = pCryptHex.length();
        iBlkSize = (iCTLen / 2 - 1) / 8 + 1;
        iCTLen = iBlkSize * 8 + 1;
        pCT = HexToStrBlk(pCryptHex);
        DES_SetKey(pEncKey);
        pCT = DES_Decrypt(pCT, iBlkSize);
        pCT = En2Ko(pCT);
        StringTokenizer token = new StringTokenizer(pCT.trim(), "|");
        pCT = token.nextToken();
        return pCT;
    }

    public static String com_Decode(String pCryptHex) {
        String pCT = "";
        String pEncKey = "";
        int iCTLen = 0;
        int iBlkSize = 0;
        pEncKey = "SINGLE_v_4.0_OFFICE_v_4.0";
        iCTLen = pCryptHex.length();
        iBlkSize = (iCTLen / 2 - 1) / 8 + 1;
        iCTLen = iBlkSize * 8 + 1;
        pCT = HexToStrBlk(pCryptHex);
        DES_SetKey(pEncKey);
        pCT = DES_Decrypt(pCT, iBlkSize);
        pCT = En2Ko(pCT);
        return pCT;
    }

    static String DES_Decrypt(String pchDataBuf, int nNumOfBlocks) {
        return des_dec(pchDataBuf, nNumOfBlocks);
    }

    public static synchronized String En2Ko(String english) {
        String korean = null;

        if (english == null)
            return null;

        try {
            korean = new String(new String(english.getBytes("8859_1"), "KSC5601"));
        } catch (UnsupportedEncodingException e) {
            korean = new String(english);
        }
        return korean;
    }

    public static synchronized String Ko2En(String korean) {
        String english = null;

        if (korean == null)
            return null;
        english = new String(korean);

        try {
            english = new String(new String(korean.getBytes("KSC5601"), "8859_1"));
        } catch (UnsupportedEncodingException e) {
            english = new String(korean);
        }
        return english;
    }

    static final short EN0 = 0;
    static final short DE1 = 1;
    static long KnL[] = new long[32];
    static long ek[] = new long[32];
    static long dk[] = new long[32];
    static short bytebit[] = { 128, 64, 32, 16, 8, 4, 2, 1 };

    static long bigbyte[] = {0x800000L, 0x400000L, 0x200000L, 0x100000L, 0x80000L, 0x40000L, 0x20000L, 0x10000L,
            32768L,	16384L,	8192L, 4096L, 2048L, 1024L, 512L, 256L, 128L, 64L, 32L, 16L, 8L, 4L, 2L, 1L };

    static char pc1[] = {'8', '0', '(', ' ', '\030', '\020', '\b', '\0', '9', '1', ')', '!', '\031', '\021',
            '\t', '\001', ':', '2', '*', '"', '\032', '\022', '\n', '\002', ';', '3', '+', '#', '>', '6',
            '.', '&', '\036', '\026', '\016', '\006', '=', '5', '-', '%', '\035', '\025', '\r', '\005',
            '<', '4', ',', '$', '\034', '\024', '\f', '\004', '\033', '\023', '\013', '\003' };

    static char pc2[] =
        {
            '\r',
            '\020',
            '\n',
            '\027',
            '\0',
            '\004',
            '\002',
            '\033',
            '\016',
            '\005',
            '\024',
            '\t',
            '\026',
            '\022',
            '\013',
            '\003',
            '\031',
            '\007',
            '\017',
            '\006',
            '\032',
            '\023',
            '\f',
            '\001',
            '(',
            '3',
            '\036',
            '$',
            '.',
            '6',
            '\035',
            '\'',
            '2',
            ',',
            ' ',
            '/',
            '+',
            '0',
            '&',
            '7',
            '!',
            '4',
            '-',
            ')',
            '1',
            '#',
            '\034',
            '\037' };

    static char totrot[] =
        {
            '\001',
            '\002',
            '\004',
            '\006',
            '\b',
            '\n',
            '\f',
            '\016',
            '\017',
            '\021',
            '\023',
            '\025',
            '\027',
            '\031',
            '\033',
            '\034' };

    static long SP1[] =
        {
            0x1010400L,
            0L,
            0x10000L,
            0x1010404L,
            0x1010004L,
            0x10404L,
            4L,
            0x10000L,
            1024L,
            0x1010400L,
            0x1010404L,
            1024L,
            0x1000404L,
            0x1010004L,
            0x1000000L,
            4L,
            1028L,
            0x1000400L,
            0x1000400L,
            0x10400L,
            0x10400L,
            0x1010000L,
            0x1010000L,
            0x1000404L,
            0x10004L,
            0x1000004L,
            0x1000004L,
            0x10004L,
            0L,
            1028L,
            0x10404L,
            0x1000000L,
            0x10000L,
            0x1010404L,
            4L,
            0x1010000L,
            0x1010400L,
            0x1000000L,
            0x1000000L,
            1024L,
            0x1010004L,
            0x10000L,
            0x10400L,
            0x1000004L,
            1024L,
            4L,
            0x1000404L,
            0x10404L,
            0x1010404L,
            0x10004L,
            0x1010000L,
            0x1000404L,
            0x1000004L,
            1028L,
            0x10404L,
            0x1010400L,
            1028L,
            0x1000400L,
            0x1000400L,
            0L,
            0x10004L,
            0x10400L,
            0L,
            0x1010004L };
    static long SP2[] =
        {
            0x80108020L,
            0x80008000L,
            32768L,
            0x108020L,
            0x100000L,
            32L,
            0x80100020L,
            0x80008020L,
            0x80000020L,
            0x80108020L,
            0x80108000L,
            0x80000000L,
            0x80008000L,
            0x100000L,
            32L,
            0x80100020L,
            0x108000L,
            0x100020L,
            0x80008020L,
            0L,
            0x80000000L,
            32768L,
            0x108020L,
            0x80100000L,
            0x100020L,
            0x80000020L,
            0L,
            0x108000L,
            32800L,
            0x80108000L,
            0x80100000L,
            32800L,
            0L,
            0x108020L,
            0x80100020L,
            0x100000L,
            0x80008020L,
            0x80100000L,
            0x80108000L,
            32768L,
            0x80100000L,
            0x80008000L,
            32L,
            0x80108020L,
            0x108020L,
            32L,
            32768L,
            0x80000000L,
            32800L,
            0x80108000L,
            0x100000L,
            0x80000020L,
            0x100020L,
            0x80008020L,
            0x80000020L,
            0x100020L,
            0x108000L,
            0L,
            0x80008000L,
            32800L,
            0x80000000L,
            0x80100020L,
            0x80108020L,
            0x108000L };
    static long SP3[] =
        {
            520L,
            0x8020200L,
            0L,
            0x8020008L,
            0x8000200L,
            0L,
            0x20208L,
            0x8000200L,
            0x20008L,
            0x8000008L,
            0x8000008L,
            0x20000L,
            0x8020208L,
            0x20008L,
            0x8020000L,
            520L,
            0x8000000L,
            8L,
            0x8020200L,
            512L,
            0x20200L,
            0x8020000L,
            0x8020008L,
            0x20208L,
            0x8000208L,
            0x20200L,
            0x20000L,
            0x8000208L,
            8L,
            0x8020208L,
            512L,
            0x8000000L,
            0x8020200L,
            0x8000000L,
            0x20008L,
            520L,
            0x20000L,
            0x8020200L,
            0x8000200L,
            0L,
            512L,
            0x20008L,
            0x8020208L,
            0x8000200L,
            0x8000008L,
            512L,
            0L,
            0x8020008L,
            0x8000208L,
            0x20000L,
            0x8000000L,
            0x8020208L,
            8L,
            0x20208L,
            0x20200L,
            0x8000008L,
            0x8020000L,
            0x8000208L,
            520L,
            0x8020000L,
            0x20208L,
            8L,
            0x8020008L,
            0x20200L };
    static long SP4[] =
        {
            0x802001L,
            8321L,
            8321L,
            128L,
            0x802080L,
            0x800081L,
            0x800001L,
            8193L,
            0L,
            0x802000L,
            0x802000L,
            0x802081L,
            129L,
            0L,
            0x800080L,
            0x800001L,
            1L,
            8192L,
            0x800000L,
            0x802001L,
            128L,
            0x800000L,
            8193L,
            8320L,
            0x800081L,
            1L,
            8320L,
            0x800080L,
            8192L,
            0x802080L,
            0x802081L,
            129L,
            0x800080L,
            0x800001L,
            0x802000L,
            0x802081L,
            129L,
            0L,
            0L,
            0x802000L,
            8320L,
            0x800080L,
            0x800081L,
            1L,
            0x802001L,
            8321L,
            8321L,
            128L,
            0x802081L,
            129L,
            1L,
            8192L,
            0x800001L,
            8193L,
            0x802080L,
            0x800081L,
            8193L,
            8320L,
            0x800000L,
            0x802001L,
            128L,
            0x800000L,
            8192L,
            0x802080L };
    static long SP5[] =
        {
            256L,
            0x2080100L,
            0x2080000L,
            0x42000100L,
            0x80000L,
            256L,
            0x40000000L,
            0x2080000L,
            0x40080100L,
            0x80000L,
            0x2000100L,
            0x40080100L,
            0x42000100L,
            0x42080000L,
            0x80100L,
            0x40000000L,
            0x2000000L,
            0x40080000L,
            0x40080000L,
            0L,
            0x40000100L,
            0x42080100L,
            0x42080100L,
            0x2000100L,
            0x42080000L,
            0x40000100L,
            0L,
            0x42000000L,
            0x2080100L,
            0x2000000L,
            0x42000000L,
            0x80100L,
            0x80000L,
            0x42000100L,
            256L,
            0x2000000L,
            0x40000000L,
            0x2080000L,
            0x42000100L,
            0x40080100L,
            0x2000100L,
            0x40000000L,
            0x42080000L,
            0x2080100L,
            0x40080100L,
            256L,
            0x2000000L,
            0x42080000L,
            0x42080100L,
            0x80100L,
            0x42000000L,
            0x42080100L,
            0x2080000L,
            0L,
            0x40080000L,
            0x42000000L,
            0x80100L,
            0x2000100L,
            0x40000100L,
            0x80000L,
            0L,
            0x40080000L,
            0x2080100L,
            0x40000100L };
    static long SP6[] =
        {
            0x20000010L,
            0x20400000L,
            16384L,
            0x20404010L,
            0x20400000L,
            16L,
            0x20404010L,
            0x400000L,
            0x20004000L,
            0x404010L,
            0x400000L,
            0x20000010L,
            0x400010L,
            0x20004000L,
            0x20000000L,
            16400L,
            0L,
            0x400010L,
            0x20004010L,
            16384L,
            0x404000L,
            0x20004010L,
            16L,
            0x20400010L,
            0x20400010L,
            0L,
            0x404010L,
            0x20404000L,
            16400L,
            0x404000L,
            0x20404000L,
            0x20000000L,
            0x20004000L,
            16L,
            0x20400010L,
            0x404000L,
            0x20404010L,
            0x400000L,
            16400L,
            0x20000010L,
            0x400000L,
            0x20004000L,
            0x20000000L,
            16400L,
            0x20000010L,
            0x20404010L,
            0x404000L,
            0x20400000L,
            0x404010L,
            0x20404000L,
            0L,
            0x20400010L,
            16L,
            16384L,
            0x20400000L,
            0x404010L,
            16384L,
            0x400010L,
            0x20004010L,
            0L,
            0x20404000L,
            0x20000000L,
            0x400010L,
            0x20004010L };
    static long SP7[] =
        {
            0x200000L,
            0x4200002L,
            0x4000802L,
            0L,
            2048L,
            0x4000802L,
            0x200802L,
            0x4200800L,
            0x4200802L,
            0x200000L,
            0L,
            0x4000002L,
            2L,
            0x4000000L,
            0x4200002L,
            2050L,
            0x4000800L,
            0x200802L,
            0x200002L,
            0x4000800L,
            0x4000002L,
            0x4200000L,
            0x4200800L,
            0x200002L,
            0x4200000L,
            2048L,
            2050L,
            0x4200802L,
            0x200800L,
            2L,
            0x4000000L,
            0x200800L,
            0x4000000L,
            0x200800L,
            0x200000L,
            0x4000802L,
            0x4000802L,
            0x4200002L,
            0x4200002L,
            2L,
            0x200002L,
            0x4000000L,
            0x4000800L,
            0x200000L,
            0x4200800L,
            2050L,
            0x200802L,
            0x4200800L,
            2050L,
            0x4000002L,
            0x4200802L,
            0x4200000L,
            0x200800L,
            0L,
            2L,
            0x4200802L,
            0L,
            0x200802L,
            0x4200000L,
            2048L,
            0x4000002L,
            0x4000800L,
            2048L,
            0x200002L };
    static long SP8[] =
        {
            0x10001040L,
            4096L,
            0x40000L,
            0x10041040L,
            0x10000000L,
            0x10001040L,
            64L,
            0x10000000L,
            0x40040L,
            0x10040000L,
            0x10041040L,
            0x41000L,
            0x10041000L,
            0x41040L,
            4096L,
            64L,
            0x10040000L,
            0x10000040L,
            0x10001000L,
            4160L,
            0x41000L,
            0x40040L,
            0x10040040L,
            0x10041000L,
            4160L,
            0L,
            0L,
            0x10040040L,
            0x10000040L,
            0x10001000L,
            0x41040L,
            0x40000L,
            0x41040L,
            0x40000L,
            0x10041000L,
            4096L,
            64L,
            0x10040040L,
            4096L,
            0x41040L,
            0x10001000L,
            64L,
            0x10000040L,
            0x10040000L,
            0x10040040L,
            0x10000000L,
            0x40000L,
            0x10001040L,
            0L,
            0x10041040L,
            0x40040L,
            0x10000040L,
            0x10040000L,
            0x10001000L,
            0x10001040L,
            0L,
            0x10041040L,
            0x41000L,
            0x41000L,
            4160L,
            4160L,
            0x40040L,
            0x10000000L,
            0x10041000L };
}
