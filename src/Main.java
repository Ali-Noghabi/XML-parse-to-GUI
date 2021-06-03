import org.w3c.dom.*;

import javax.swing.*;
import javax.xml.parsers.*;
import java.awt.*;
import java.io.*;

public class Main {
    public static JFrame frame = new JFrame();

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("data2.xml"));


            Element root = document.getDocumentElement();
            NamedNodeMap rootAttributes = root.getAttributes();
            Dimension a = new Dimension();
            for (int i = 0; i < rootAttributes.getLength(); i++) {
                Attr attr = (Attr) rootAttributes.item(i);
                System.out.println(attr.getNodeName() + " --> " + attr.getNodeValue());

                switch (attr.getNodeName()) {
                    case ("height"):
                        a.height = Integer.parseInt(attr.getNodeValue());
                        break;
                    case ("width"):
                        a.width = Integer.parseInt(attr.getNodeValue());
                        break;
                    case ("title"):
                        frame.setTitle(attr.getNodeValue());
                        break;

                }
                frame.setSize(a);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);

                frame.setVisible(true);

            }
            System.out.println();


            NodeList nodeList = root.getChildNodes();

            GetStuff(nodeList, frame);
            frame.revalidate();
            frame.repaint();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void GetStuff(NodeList nodeList, Object Jx) {


        if (nodeList != null)
            for (int j = 0; j < nodeList.getLength(); j++) {
                System.out.println(nodeList.item(j).getNodeName());
                NamedNodeMap attributes = nodeList.item(j).getAttributes();
                if (attributes != null)
                    for (int i = 0; i < attributes.getLength(); i++) {
                        Attr attr = (Attr) attributes.item(i);
                        switch (nodeList.item(j).getNodeName()) {
                            case ("JButton"):
                                JButton button = new JButton();
                                System.out.println("on Jbutton");
                                switch (attr.getNodeName()) {
                                    case ("text"):
//                                        System.out.println("im here ---------------------------------------.");
                                        button.setText(attr.getNodeValue());
                                        System.out.println("button name " + button.getText() + ((JPanel)Jx).getName());
                                        button.setBackground(Color.BLUE);
                                        try {
                                            ((JPanel) Jx).add(button);
                                            if (nodeList.item(j).getNodeType() == Node.ELEMENT_NODE
                                                    && nodeList.item(j).hasChildNodes()) {
                                                Jx = (JButton) button;
                                            }
                                        } catch (Exception e) {
                                        }
                                        break;
                                    default:
                                        break;

                                }
                                break;
                            case ("JPanel"):
                                JPanel panel = new JPanel();
                                System.out.println("On Jpanel");
                                switch (attr.getNodeName()) {
                                    case ("background"):
                                        switch (attr.getNodeValue()) {
                                            case ("black"):
                                                panel.setBackground(Color.BLACK);
                                                break;
                                            case ("red"):
                                                panel.setBackground(Color.RED);
                                                if (nodeList.item(j).getParentNode().getNodeName().equals("JFrame")) {
                                                    frame.add(panel, BorderLayout.NORTH);
                                                    Jx = (JPanel)panel;
                                                } else {
                                                    ((JFrame) Jx).add(panel, BorderLayout.NORTH);
                                                    Jx = (JPanel) panel;
                                                }
                                                break;
                                            case ("pink"):
                                                panel.setBackground(Color.PINK);
                                                ((JFrame) Jx).add(panel, BorderLayout.SOUTH);
                                                Jx = (JPanel) panel;
                                                break;
                                        }
                                        break;

                                }
//                                panel.add(new JButton("test"));
                                break;

                        }
//                        frame.add(panel);

                        System.out.println(attr.getNodeName() + " --> " + attr.getNodeValue());
                    }
//                System.out.println();

                if (nodeList.item(j).getNodeType() == Node.ELEMENT_NODE
                        && nodeList.item(j).hasChildNodes()) {
                    GetStuff(nodeList.item(j).getChildNodes(), Jx);
                }
            }
//        System.out.println(flag);
//        if (flag == 0) {
//            panel.add(button);
//            frame.add(panel);
//        }

    }


}
