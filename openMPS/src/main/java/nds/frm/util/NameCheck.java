// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameCheck.java

package nds.frm.util;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;
import java.text.DecimalFormat;

public class NameCheck
{

    public NameCheck()
    {
        ErrCode = 0;
        ChkName = "";
        Jumin = "";
        SiteCode = "";
        EncJumin = "";
        TimeOut = 0;
    }

    public String getEnc()
    {
        return EncJumin;
    }

    public void setChkName(String s)
    {
        ChkName = s;
        try
        {
            String as[] = null;
            as = (new String[] {
                "KS_C_5601-1987", "EUC-KR", "ISO_8859-1", "ISO_8859-2", "ISO-10646-UCS-2", "IBM037", "IBM273", "IBM277", "IBM278", "IBM280", 
                "IBM284", "IBM285", "IBM297", "IBM420", "IBM424", "IBM437", "IBM500", "IBM775", "IBM850", "IBM852", 
                "IBM855", "IBM857", "IBM860", "IBM861", "IBM862", "IBM863", "IBM864", "IBM865", "IBM866", "IBM868", 
                "IBM869", "IBM870", "IBM871", "IBM918", "IBM1026", "Big5-HKSCS", "UNICODE-1-1", "UTF-16BE", "UTF-16LE", "UTF-16", 
                "UTF-8", "ISO-8859-13", "ISO-8859-15", "GBK", "GB18030", "JIS_Encoding", "Shift_JIS", "Big5", "TIS-620", "us-ascii", 
                "iso-8859-1", "iso-8859-2", "iso-8859-3", "iso-8859-4", "iso-8859-5", "iso-8859-6", "iso-8859-7", "iso-8859-8", "iso-8859-9", "koi8-r", 
                "euc-cn", "euc-tw", "big5", "euc-jp", "shift_jis", "euc-kr"
            });
            for(int i = 0; i < as.length; i++)
            {
                for(int j = 0; j < as.length; j++)
                {
                    String s1;
                    if(i != j)
                        s1 = new String(s.getBytes(as[i]), as[j]);
                }

            }

        }
        catch(Exception exception) { }
    }

    public String setJumin(String s)
    {
        Jumin = s.trim();
        return String.valueOf(getEncJumin(Jumin, 21));
    }

    public void setSiteCode(String s)
    {
        SiteCode = s;
    }

    public void setTimeOut(int i)
    {
        TimeOut = i;
    }

    public String getRtn()
    {
        return getNameCheck();
    }

    private int getRandom()
    {
        return Math.abs((new Long(System.currentTimeMillis())).intValue());
    }

    private String getNameCheck()
    {
        String s = "";
        Socket socket = null;
        InputStream inputstream = null;
        PrintWriter printwriter = null;
        try
        {
            int i = getRandom();
            String s1 = "http://203.234.219.72/cnm.asp";
            URL url = new URL(s1);
            String s2 = url.getHost();
            int j = 81 + i % 5;
            String s3 = url.getFile();
            socket = new Socket(s2, j);
            socket.setSoTimeout(TimeOut);
            printwriter = new PrintWriter(socket.getOutputStream(), false);
            inputstream = socket.getInputStream();
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append(URLEncoder.encode("a3", "euc-kr") + "=" + URLEncoder.encode(ChkName, "euc-kr") + "&");
            stringbuffer.append(URLEncoder.encode("a2", "euc-kr") + "=" + URLEncoder.encode(EncJumin, "euc-kr") + "&");
            stringbuffer.append(URLEncoder.encode("a1", "euc-kr") + "=" + URLEncoder.encode(SiteCode, "euc-kr"));
            int k = stringbuffer.toString().length();
            StringBuffer stringbuffer1 = new StringBuffer();
            stringbuffer1.append("POST " + s3 + " HTTP/1.1\n");
            stringbuffer1.append("Accept: */*\n");
            stringbuffer1.append("Connection: close\n");
            stringbuffer1.append("Host: wtname.creditbank.co.kr\n");
            stringbuffer1.append("Content-Type: application/x-www-form-urlencoded\n");
            stringbuffer1.append("Content-Length: " + k + "\r\n");
            stringbuffer1.append("\r\n");
            stringbuffer1.append(stringbuffer.toString());
            printwriter.print(stringbuffer1.toString());
            printwriter.flush();
            stringbuffer1.setLength(0);
            String s4 = "";
            int l = 0;
            for(boolean flag = true; flag && l != -1; flag = (l = inputstream.read()) == 114 ? (l = inputstream.read()) == 101 ? (l = inputstream.read()) == 115 ? (l = inputstream.read()) == 117 ? (l = inputstream.read()) == 108 ? (l = inputstream.read()) == 116 ? (l = inputstream.read()) != 61 : true : true : true : true : true : true);
            byte abyte0[] = new byte[2];
            inputstream.read(abyte0);
            printwriter.close();
            inputstream.close();
            socket.close();
            socket = null;
            inputstream = null;
            printwriter = null;
            s = (new String(abyte0, "KSC5601")).toString();
        }
        catch(MalformedURLException malformedurlexception)
        {
            if(printwriter != null)
                try
                {
                    printwriter.close();
                    printwriter = null;
                }
                catch(Exception exception1) { }
            if(inputstream != null)
                try
                {
                    inputstream.close();
                    inputstream = null;
                }
                catch(Exception exception2) { }
            if(socket != null)
                try
                {
                    socket.close();
                    socket = null;
                }
                catch(Exception exception3) { }
            s = "62";
        }
        catch(NoRouteToHostException noroutetohostexception)
        {
            if(printwriter != null)
                try
                {
                    printwriter.close();
                    printwriter = null;
                }
                catch(Exception exception4) { }
            if(inputstream != null)
                try
                {
                    inputstream.close();
                    inputstream = null;
                }
                catch(Exception exception5) { }
            if(socket != null)
                try
                {
                    socket.close();
                    socket = null;
                }
                catch(Exception exception6) { }
            s = "61";
        }
        catch(Exception exception)
        {
            if(printwriter != null)
                try
                {
                    printwriter.close();
                    printwriter = null;
                }
                catch(Exception exception7) { }
            if(inputstream != null)
                try
                {
                    inputstream.close();
                    inputstream = null;
                }
                catch(Exception exception8) { }
            if(socket != null)
                try
                {
                    socket.close();
                    socket = null;
                }
                catch(Exception exception9) { }
            s = "63";
        }
        finally
        {
            if(printwriter != null)
                try
                {
                    printwriter.close();
                    printwriter = null;
                }
                catch(Exception exception11) { }
            if(inputstream != null)
                try
                {
                    inputstream.close();
                    inputstream = null;
                }
                catch(Exception exception12) { }
            if(socket != null)
                try
                {
                    socket.close();
                    socket = null;
                }
                catch(Exception exception13) { }
        }
        return s;
    }

    private int getEncJumin(String s, int i)
    {
        String s1 = "13814175622071120141181061768611993108841416921423107181672510714175411266712670119411737212225184002090820525212741182820947153241426022005196831631213938161862274312787181662007815703134602165910388182131264812368213511080911151159881253313998114131777122809215401592122930118692301418370102821668712210148061538513870120181727420355200961795413534192821169714960142231124510693129551063218404145651690617787165521359419983207241159515423148221137115237203671177021155195251257514999190251531020044";
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        String s2 = "";
        String s3 = "";
        String s4 = s;
        String s5 = "00";
        String s6 = "";
        String s7 = "";
        String s8 = "";
        s4.trim();
        if(i != s4.length())
            return 21;
        j = i - (i / 3) * 3;
        if(j == 2)
            s4 = s4 + "00";
        else
        if(j == 1)
            s4 = s4 + "0";
        k = (int)(Math.random() * 100D);
        s8 = s1.substring(k * 5, k * 5 + 5);
        l = Integer.valueOf(s8).intValue();
        i1 = s4.length() / 3 / 2;
        DecimalFormat decimalformat = null;
        decimalformat = new DecimalFormat("00");
        s6 = decimalformat.format(k);
        decimalformat = new DecimalFormat("00000");
        for(j1 = 0; j1 < i1; j1++)
        {
            String s9 = s4.substring(j1 * 2 * 3, j1 * 2 * 3 + 3);
            String s11 = decimalformat.format((new Integer(s9)).intValue() + l);
            s9 = s4.substring((j1 * 2 + 1) * 3, (j1 * 2 + 1) * 3 + 3);
            String s13 = decimalformat.format((new Integer(s9)).intValue() + l);
            s6 = s6 + s13;
            s6 = s6 + s11;
        }

        if(i1 * 2 < s4.length() / 3)
        {
            String s10 = s4.substring(j1 * 2 * 3, j1 * 2 * 3 + 3);
            String s12 = decimalformat.format((new Integer(s10)).intValue() + l);
            s6 = s6 + s12;
        }
        EncJumin = s6;
        return 0;
    }

    public int ErrCode;
    private String ChkName;
    private String Jumin;
    private String SiteCode;
    private String EncJumin;
    private int TimeOut;
    final short PROC_OK = 0;
    final short DATA_ERR = 21;
}
