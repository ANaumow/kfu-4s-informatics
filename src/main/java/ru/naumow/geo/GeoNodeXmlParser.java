package ru.naumow.geo;

import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GeoNodeXmlParser {

    public static void main(String[] args) {
        GeoNodeXmlParser geoNodeXmlParser = new GeoNodeXmlParser();
        geoNodeXmlParser.parse(GeoNodeXmlParser.class.getResource("data.xml"));
    }

    @SneakyThrows
    public GeoNode parse(URL resource) {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(resource.openStream());
        Node tree = document.getDocumentElement();
        return f(tree);
    }

    private GeoNode f(Node node) {
        NamedNodeMap attributes = node.getAttributes();

        String name = attributes.getNamedItem("name").getTextContent();
        NodeType type = NodeType.valueOf(node.getNodeName().toUpperCase()/*attributes.getNamedItem("type").getTextContent().*/);
        int priority = Integer.parseInt(attributes.getNamedItem("priority").getTextContent());
        List<GeoNode> children = new ArrayList<>();

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node cur = nodeList.item(i);
            if (cur.getNodeType() == Node.ELEMENT_NODE) {
                children.add(f(cur));
            }
        }
        return new GeoNode.Builder()
                .name(name)
                .type(type)
                .priority(priority)
                .children(children)
                .build();
    }

}
