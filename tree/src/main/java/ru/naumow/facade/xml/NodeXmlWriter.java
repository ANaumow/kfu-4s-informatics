package ru.naumow.facade.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.naumow.App;
import ru.naumow.tree.Node;
import ru.naumow.tree.Tree;
import ru.naumow.iterator.IteratorBfs;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class NodeXmlWriter {

    public void write(File file, Node root) {
        DocumentBuilder documentBuilder;
        Document document;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.newDocument();
            document.setXmlStandalone(true);
            document.appendChild(f(root, document));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (TransformerException | ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    private Element f(Node root, Document document) {
        Element element = document.createElement(root.getType().name().toLowerCase());
        element.setAttribute(App.ATTR_NAME, root.getName());
        element.setAttribute(App.ATTR_PRIORITY, String.valueOf(root.getPriority()));
        for (Node child : root.getChildren()) {
            element.appendChild(f(child, document));
        }
        return element;
    }

}
