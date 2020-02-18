package ru.naumow.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Node.Builder.class)
public class Node {
    private String   name;
    private NodeType type;
    private int      priority;

    private List<Node> children = new ArrayList<>();

    @JsonIgnore
    private Node parent;

    private Node(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.priority = builder.priority;
        addAllChildren(Objects.requireNonNullElse(builder.children, new ArrayList<>()));
    }

    public void addAllChildren(List<Node> children) {
        for (Node child : children) {
            addChild(child);
        }
    }

    public void addChild(Node child) {
        this.children.add(child);
        child.parent = this;
    }

    public void removeChildren(Node child) {
        this.children.remove(child);
        child.parent = null;
    }

    public List<Node> getChildren() {
        return Collections.unmodifiableList(children);
    }


    @JsonIgnore
    public String getPath() {
        Deque<String> deque = new LinkedList<>();
        Node cur = this;
        while (cur != null) {
            deque.addFirst(cur.getName());
            cur = cur.getParent();
        }
        return String.join(",", deque);
    }

    public String getName() {
        return this.name;
    }

    public NodeType getType() {
        return this.type;
    }

    public int getPriority() {
        return this.priority;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return "GeoNode(name=" + this.getName() + ", type=" + this.getType() + ", priority=" + this.getPriority() + ", children=" + this.getChildren() + ")";
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private String   name;
        private NodeType type;
        private int        priority;
        private List<Node> children;

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

        public Builder children(List<Node> children) {
            this.children = children;
            return this;
        }

        public Node build() {
            return new Node(this);
        }
    }

}
