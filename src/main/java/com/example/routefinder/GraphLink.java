package com.example.routefinder;

public class GraphLink {
    public NodeWithCost<?> destNode; //Could also store source node if required
    public double cost;

    //Other link attributes could be similarly stored
    public GraphLink(NodeWithCost<?> destNode,double cost) {
        this.destNode=destNode;
        this.cost=cost;
    }
}
