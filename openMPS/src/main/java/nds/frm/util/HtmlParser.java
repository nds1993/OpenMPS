package nds.frm.util;

import java.io.StringReader;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;


public class HtmlParser {
    private StringBuffer out_text = null;
    private int parser_ok = 0;
    
    public static HtmlParser getInstance(){
        return new HtmlParser();
    }

    public String getConvData(String htmlData)
    {
     String return_val = "";
     out_text = new StringBuffer();
    
     if(htmlData.indexOf("<meta") != -1)
     {
      String temp = "";
      while(htmlData.indexOf("<meta") != -1)
      {
       temp = htmlData.substring(0, htmlData.indexOf("<meta"));
       htmlData = htmlData.substring(htmlData.indexOf(">",htmlData.indexOf("<meta"))+1, htmlData.length());
       htmlData = temp + htmlData;
      }
     }

     try
     {
      HTMLEditorKit.ParserCallback callback = new HTMLEditorKit.ParserCallback ()
      {
       public void handleStartTag(HTML.Tag tag, MutableAttributeSet a, int pos)
       {
        if (tag.equals(HTML.Tag.BODY)) parser_ok = 1;
       }
       public void handleEndTag(HTML.Tag tag, MutableAttributeSet a, int pos)
       {
        if (tag.equals(HTML.Tag.BODY)) parser_ok = 0;
       }
       public void handleText(char[] data, int pos)
       {
        if(parser_ok == 1)
        {
         for(int k = 0; k < data.length; k++)
         {
          if(data[k] == (char)160) data[k] = (char)32;
          else out_text.append(data[k]);
         }
        }
       }
      };

      new ParserDelegator().parse(new StringReader(htmlData), callback, false);
     }
     catch(Exception e)
     {
      e.printStackTrace();
     }
    
     return out_text.toString();
    }


}
