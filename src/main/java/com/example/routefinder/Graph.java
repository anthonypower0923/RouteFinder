package com.example.routefinder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    @Override
    public String toString() {
        return "Graph " + "\n" +
                "nodes= " + nodes;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
