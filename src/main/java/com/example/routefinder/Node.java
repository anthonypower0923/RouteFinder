package com.example.routefinder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    private String name;
    private String destName;
    private double disdance;

    private List<Node> shortestPath = new LinkedList<>();

    private Double distance = Double.MAX_VALUE;

    Map<Node, Double> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, double distance) {
        adjacentNodes.put(destination, distance);
        destName = destination.name;
        disdance = distance;
    }

    public Node(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node " +
                "name='" + name + '\'' +
                ", destName= " + destName +
                ", distance= " + disdance +
                "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Map<Node, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}
