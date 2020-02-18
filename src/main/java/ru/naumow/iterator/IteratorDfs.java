package ru.naumow.iterator;

import ru.naumow.tree.Node;

import java.util.*;

public class IteratorDfs implements Iterator<Node> {

    private Stack<Queue<Node>> stack = new Stack<>();

    public IteratorDfs(Node root) {
        Queue<Node> queue = newQueue();
        queue.add(root);
        stack.add(queue);
        addChildrenDeeply(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Node next() {
        Queue<Node> queue = stack.peek();
        Node n = queue.remove();
        if (queue.isEmpty()) {
            stack.pop();
        } else {
            addChildrenDeeply(queue.element());
        }
        return n;
    }

    private void addChildrenDeeply(Node cur) {
        List<Node> children;
        while (!(children = cur.getChildren()).isEmpty()) {
            Queue<Node> q = newQueue();
            q.addAll(children);
            stack.add(q);
            cur = q.peek();
        }
    }

    private Queue<Node> newQueue() {
        return new LinkedList<>();
    }

}
