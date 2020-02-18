package ru.naumow.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.naumow.facade.xml.NodeXmlReader;
import ru.naumow.tree.Node;
import ru.naumow.tree.Tree;
import ru.naumow.facade.xml.NodeXmlWriter;

import java.io.File;
import java.io.IOException;

public class TreeFileConverterImpl implements TreeFileConverter {

    private static TreeFileConverter instance = new TreeFileConverterImpl();

    public static TreeFileConverter getInstance() {
        return instance;
    }

    private TreeFileConverterImpl() {
    }

    @Override
    public Tree readTree(String filename) {
        if (filename.endsWith(".xml")) {
            return readTreeAsXml(new File(filename));
        } else if (filename.endsWith(".json")) {
            return readTreeAsJson(new File(filename));
        } else {
            throw new IllegalArgumentException("No support for file type");
        }
    }

    @Override
    public void writeTree(String filename, Tree tree) {
        if (filename.endsWith(".xml")) {
            writeTreeAsXml(new File(filename), tree);
        } else if (filename.endsWith(".json")) {
            writeTreeAsJson(new File(filename), tree);
        } else {
            throw new IllegalArgumentException("No support for file type");
        }
    }

    private void writeTreeAsXml(File file, Tree tree) {
        NodeXmlWriter writer = new NodeXmlWriter();
        writer.write(file, tree.getRoot());
    }

    private void writeTreeAsJson(File file, Tree tree) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(file, tree.getRoot());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Tree readTreeAsXml(File file) {
        NodeXmlReader parser = new NodeXmlReader();
        return new Tree(parser.read(file));
    }

    private Tree readTreeAsJson(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return new Tree(mapper.readValue(file, Node.class));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
