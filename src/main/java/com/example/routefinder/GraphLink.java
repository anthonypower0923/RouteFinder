package com.example.routefinder;

public class GraphLink {
    public NodeWithCost<?> destNode; //Could also store source node if required
    public int cost;

    //Other link attributes could be similarly stored
    public GraphLink(NodeWithCost<?> destNode,int cost) {
        this.destNode=destNode;
        this.cost=cost;
    }
}
