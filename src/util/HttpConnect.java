package util;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 *
 * @author kanishka
 */
public class HttpConnect {

    InputStream in;
    String url = "http://localhost:8080/SNVIBa/";

    public Document getHttp(String end) throws Exception {
        System.out.println(end);
        in = new URL((url + end).replaceAll("\\s", "%20")).openStream();
        DocumentBuilderFactory buildf=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=buildf.newDocumentBuilder();
        Document doc=builder.parse(in);

        return doc;
    }
}
