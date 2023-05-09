package com.example.routefinder;
import java.util.*;

public class Node<T> {

    public T data;
    public List<Node> adjList=new ArrayList<>(); //Could use any List implementation

    public Node(T data) {
        this.data=data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }

    public void connectToNodeDirected(Node<T> destNode) {
        adjList.add(destNode);
    }

    public void connectToNodeUndirected(Node<?> destNode) {
        if (!destNode.adjList.contains(this) || (!this.adjList.contains(destNode))) {
            adjList.add(destNode);
            destNode.adjList.add(this);
        }
    }

    public T getData() {
        return data;
    }


}
