package com.example.routefinder;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node<Station>> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    @Override
    public String toString() {
        return "Graph " + "\n" +
                "nodes= " + nodes;
    }

    public Set<Node<Station>> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node<Station>> nodes) {
        this.nodes = nodes;
    }
}
