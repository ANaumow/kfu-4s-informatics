package ru.naumow.geo;

import lombok.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString
@Setter
@Getter
public class GeoNode {
    private String   name;
    private NodeType type;
    private int      priority;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private List<GeoNode> children = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private GeoNode parent;

    private GeoNode(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.priority = builder.priority;
        addAllChildren(Objects.requireNonNullElse(builder.children, new ArrayList<>()));
    }

    public void addAllChildren(List<GeoNode> children) {
        for (GeoNode child : children) {
            addChild(child);
        }
    }

    public void addChild(GeoNode child) {
        this.children.add(child);
        child.parent = this;
    }

    public void removeChildren(GeoNode child) {
        this.children.remove(child);
        child.parent = null;
    }

    public List<GeoNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public String getPath() {
        Deque<String> deque = new LinkedList<>();
        GeoNode cur = this;
        while (cur != null) {
            deque.addFirst(cur.getName());
            cur = cur.getParent();
        }

        return String.join(",", deque);

        /*Stack<String> stack = new Stack<>();
        for (GeoNode cur = this; cur != null; cur = cur.getParent()){
            stack.push(cur.getName());
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
            if (!stack.isEmpty())
                stringBuilder.append(",");
        }
        return stringBuilder.toString();*/
    }

    static class Builder {
        private String   name;
        private NodeType type;
        private int      priority;
        private List<GeoNode> children;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(NodeType type) {
            this.type = type;
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder children(List<GeoNode> children) {
            this.children = children;
            return this;
        }

        public GeoNode build() {
            return new GeoNode(this);
        }
    }

}
