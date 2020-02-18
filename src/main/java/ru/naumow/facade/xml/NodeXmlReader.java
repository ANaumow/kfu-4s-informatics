package ru.naumow.facade.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.naumow.App;
import ru.naumow.tree.NodeType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NodeXmlReader {

    public ru.naumow.tree.Node read(File file) {
        DocumentBuilder documentBuilder = null;
        Document document = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(new FileInputStream(file));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalStateException(e);
        }
        Element documentElement = document.getDocumentElement();
        return f(documentElement);
    }

    private ru.naumow.tree.Node f(Node node) {
        NamedNodeMap attributes = node.getAttributes();

        String name = attributes.getNamedItem(App.ATTR_NAME).getTextContent();
        NodeType type = NodeType.valueOf(node.getNodeName().toUpperCase());
        int priority = Integer.parseInt(attributes.getNamedItem(App.ATTR_PRIORITY).getTextContent());
        List<ru.naumow.tree.Node> children = new ArrayList<>();

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node cur = nodeList.item(i);
            if (cur.getNodeType() == Node.ELEMENT_NODE) {
                children.add(f(cur));
            }
        }
        return new ru.naumow.tree.Node.Builder()
                .name(name)
                .type(type)
                .priority(priority)
                .children(children)
                .build();
    }

}
