package ru.naumow.interpretator.components;

import ru.naumow.tree.Node;
import ru.naumow.tree.NodeType;
import ru.naumow.tree.Tree;
import ru.naumow.facade.TreeFileConverter;
import ru.naumow.interpretator.Context;
import ru.naumow.interpretator.core.anotation.CmdMapping;
import ru.naumow.interpretator.core.util.Commands;

import java.util.Iterator;
import java.util.Objects;

public class DataCrud {

    // add <адрес родителя> <название ноды> <тип ноды> <приоритет>
    @CmdMapping("add")
    private void add(Context context, String[] args) {
        Commands.requireCount(args, 4);

        String parentPath = args[0];
        String newName = args[1];
        String newType = args[2];
        String newPriority = args[3];

        Node newNode = new Node.Builder()
                .name(newName)
                .type(NodeType.valueOf((newType.toUpperCase())))
                .priority(Integer.parseInt(newPriority))
                .build();

        Tree tree = context.getAttribute(Context.ATTR_TREE, Tree.class);
        Node foundParent = Components.find(tree.iterator(), node -> node.getPath().endsWith(parentPath));
        Objects.requireNonNull(foundParent, "Suitable parent is not found");
        foundParent.addChild(newNode);
    }

    // save
    @CmdMapping("save")
    private void save(Context context, String[] args) {
        TreeFileConverter converter = context.getAttribute(Context.ATTR_CONVERTER, TreeFileConverter.class);
        String outputFilename = context.getAttribute(Context.ATTR_OUTPUT_FILENAME, String.class);
        Tree tree = context.getAttribute(Context.ATTR_TREE, Tree.class);
        converter.writeTree(outputFilename, tree);
    }

    // delete <адрес ноды>
    @CmdMapping("delete")
    private void delete(Context context, String[] args) {
        Commands.requireCount(args, 1);

        String path = args[0];
        Tree tree = context.getAttribute(Context.ATTR_TREE, Tree.class);

        Node required = Components.find(tree.iterator(), node -> node.getPath().endsWith(path));
        Objects.requireNonNull(required, "Suitable node is not found");
        Node parent = required.getParent();
        parent.removeChildren(required);
    }

}
