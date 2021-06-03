import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class Main2 {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new FileInputStream(new File("data2.xml")));

        NodeList entries = doc.getElementsByTagName("JPanel");

        int num = entries.getLength();

        for (int i=0; i<num; i++) {
            Element node = (Element) entries.item(i);
            listAllAttributes(node);
        }

    }

    public static void listAllAttributes(Element element) {

        System.out.println("List attributes for node: " + element.getNodeName());

        // get a map containing the attributes of this node
        NamedNodeMap attributes = element.getAttributes();

        // get the number of nodes in this map
        int numAttrs = attributes.getLength();

        for (int i = 0; i < numAttrs; i++) {
            Attr attr = (Attr) attributes.item(i);

            String attrName = attr.getNodeName();
            String attrValue = attr.getNodeValue();

            System.out.println("Found attribute: " + attrName + " with value: " + attrValue);

        }
    }

}
