package ru.naumow.iterator;

import ru.naumow.tree.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class IteratorBfs implements Iterator<Node> {

    private Queue<Node> queue = new LinkedList<>();

    public IteratorBfs(Node root) {
        queue.add(root);
    }

    @Override
    public Node next() {
        Node node = queue.remove();
        queue.addAll(node.getChildren());
        return node;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

}
