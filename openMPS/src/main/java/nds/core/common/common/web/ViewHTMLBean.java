package nds.core.common.common.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ViewHTMLBean {
    private URL url;

    public ViewHTMLBean() {
        // Nothing to do
    }

    public void setURL(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            url = null;
        }
    }

    public String getURL() {
        if (url == null)
            return "";
        else
            return url.toString();
    }

    public String getSource() {
        if (url == null)
            return "";
        String source = "";
        BufferedReader reader;
        StringBuffer strbuf = new StringBuffer();
        String line;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = reader.readLine()) != null)
                strbuf.append(line + "\n");
            reader.close();
            source = strbuf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            source = "Unable to establish connection to " + url;
        }
        return source;
    }

}
