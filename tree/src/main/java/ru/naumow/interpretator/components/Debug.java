package ru.naumow.interpretator.components;

import ru.naumow.tree.Node;
import ru.naumow.tree.NodeType;
import ru.naumow.tree.Tree;
import ru.naumow.interpretator.Context;
import ru.naumow.interpretator.core.anotation.CmdMapping;
import ru.naumow.interpretator.core.util.Commands;

import java.util.Iterator;
import java.util.Objects;

public class Debug {

    // return children <тип> <название>
    @CmdMapping("return children")
    private void returnChildren(Context context, String[] args) {
        Commands.requireCount(args, 2);

        String rawType = args[0];
        String name = args[1];

        NodeType type;
        try {
            type = NodeType.valueOf(rawType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Wrong type: " + rawType + " found");
        }

        Tree tree = context.getAttribute(Context.ATTR_TREE, Tree.class);

        Node foundParent = Components.find(tree.iterator(),
                node -> node.getName().endsWith(name) && node.getType().equals(type));

        Objects.requireNonNull(foundParent, "Suitable parent is not found");
        for (Node child : foundParent.getChildren()) {
            System.out.println(child.getName());
        }
    }

}
