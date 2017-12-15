import org.jdom2.JDOMException;

import java.io.IOException;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 15.12.17
 */
public class ReaderXmlApp {
    public static void main(String[] args) {

        ReaderXmlJdom xmlReader = null;
        try {
            xmlReader = new ReaderXmlJdom("src/main/java/data.xml");
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        xmlReader.work();

    }
}
