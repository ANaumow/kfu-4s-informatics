package ru.naumow.geo.iterator;

import ru.naumow.geo.GeoNode;

import java.util.*;

public class IteratorDfs implements Iterator<GeoNode> {

    private Stack<Queue<GeoNode>> stack = new Stack<>();

    public IteratorDfs(GeoNode root) {
        Queue<GeoNode> queue = newQueue();
        queue.add(root);
        stack.add(queue);
        addChildrenDeeply(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public GeoNode next() {
        Queue<GeoNode> queue = stack.peek();
        GeoNode n = queue.remove();
        if (queue.isEmpty()) {
            stack.pop();
        } else {
            addChildrenDeeply(queue.element());
        }
        return n;
    }

    private void addChildrenDeeply(GeoNode cur) {
        List<GeoNode> children;
        while (!(children = cur.getChildren()).isEmpty()) {
            Queue<GeoNode> q = newQueue();
            q.addAll(children);
            stack.add(q);
            cur = q.peek();
        }
    }

    private Queue<GeoNode> newQueue() {
        return new LinkedList<>();
    }

}
