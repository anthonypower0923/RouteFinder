package com.example.routefinder;

import java.util.*;

public class NodeWithCost<T> {

    public T data;
    public List<GraphLink> adjList=new ArrayList<>(); //Could use any List implementation
    public int nodeValue = Integer.MAX_VALUE;

    public NodeWithCost(T data) {
        this.data=data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }

    public void connectToNodeDirected(NodeWithCost<T> destNode,int cost) {
        adjList.add(new GraphLink(destNode,cost));
    }

    public void connectToNodeUndirected(NodeWithCost<T> destNode,int cost) {
        adjList.add(new GraphLink(destNode,cost));
        destNode.adjList.add(new GraphLink(this,cost));
    }

    public T getData() {
        return data;
    }


}
