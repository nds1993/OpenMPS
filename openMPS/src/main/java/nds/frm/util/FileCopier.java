package nds.frm.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <b>class : </b> FileCopier
 * <b>Class Description</b><br>
 * 파일을 복사하는 컴포넌트
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class FileCopier {

    public static void copy(String inFile, String outFile) {
        FileInputStream fileIn = null;
        FileOutputStream fileOut = null;
        try {
            fileIn = new FileInputStream(inFile);
            fileOut = new FileOutputStream(outFile);
            copy(fileIn, fileOut);
        } catch (Exception e) {
            System.err.println("Error running FileCopier.copy()");
            e.printStackTrace();
        } finally {
            try {
                if (fileIn != null) {
                    fileIn.close();
                }
            } catch (Exception e) {
                System.err.println("Error closing fileIn");
            }
            try {
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (Exception e) {
                System.err.println("Error closing fileOut");
            }
        }
    }

    public static void copy(InputStream in, OutputStream out) throws IOException{
        try {
            synchronized (in) {
                synchronized (out) {
                    byte[] buffer = new byte[256];
                    while (true) {
                        int bytesRead = in.read(buffer);
                        if (bytesRead == -1) {
                            break;
                        } else {
                            out.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
        } catch (IOException ie) {
            throw new IOException();
        }
    }

}
