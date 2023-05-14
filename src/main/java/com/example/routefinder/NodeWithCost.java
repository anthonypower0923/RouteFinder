package com.example.routefinder;

import java.util.*;

public class NodeWithCost<T> {

    public T data;
    public List<GraphLink> adjList=new ArrayList<>(); //Could use any List implementation
    public double nodeValue = Double.MAX_VALUE;

    public NodeWithCost(T data) {
        this.data=data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }

    public void connectToNodeDirected(NodeWithCost<T> destNode,double cost) {
        adjList.add(new GraphLink(destNode,cost));
    }

    public void connectToNodeUndirected(NodeWithCost<T> destNode, double cost) {
        adjList.add(new GraphLink(destNode,cost));
        destNode.adjList.add(new GraphLink(this,cost));
    }

    public T getData() {
        return data;
    }


}
