package com.example.routefinder;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.routefinder.CostedPath.findCheapestPathDijkstra;
import static com.example.routefinder.CostedPath.searchGraphDepthFirstCheapestPath;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RouteFinderControllerTest {

    @Test
    void findPathDepthFirstReturnsValidPathBetweenNodes() {
        Node<String> pear = new Node<>("Pear");
        Node<String> orange = new Node<>("Orange");
        Node<String> cherry = new Node<>("Cherry");
        Node<String> apple = new Node<>("Apple");
        Node<String> plum = new Node<>("Plum");
        Node<String> mango = new Node<>("Mango");
        Node<String> kiwi = new Node<>("Kiwi");
        Node<String> coconut = new Node<>("Coconut");
        Node<String> banana = new Node<>("Banana");

        pear.connectToNodeUndirected(apple);
        pear.connectToNodeUndirected(kiwi);
        apple.connectToNodeUndirected(cherry);
        apple.connectToNodeUndirected(plum);
        cherry.connectToNodeUndirected(plum);
        plum.connectToNodeUndirected(mango);
        mango.connectToNodeUndirected(kiwi);
        kiwi.connectToNodeUndirected(coconut);
        kiwi.connectToNodeUndirected(orange);
        mango.connectToNodeUndirected(banana);

        List<Node<?>> path = RouteFinderController.findPathDepthFirst(pear, null, cherry.data);
        //System.out.println(path);
        assertTrue(path.contains(pear) && path.contains(cherry));
    }

    @Test
    void findPathDepthFirstReturnsValidPathBetweenNodesWithEncounteredNodes() {
        Node<String> pear = new Node<>("Pear");
        Node<String> orange = new Node<>("Orange");
        Node<String> cherry = new Node<>("Cherry");
        Node<String> apple = new Node<>("Apple");
        Node<String> plum = new Node<>("Plum");
        Node<String> mango = new Node<>("Mango");
        Node<String> kiwi = new Node<>("Kiwi");
        Node<String> coconut = new Node<>("Coconut");
        Node<String> banana = new Node<>("Banana");

        pear.connectToNodeUndirected(apple);
        pear.connectToNodeUndirected(kiwi);
        apple.connectToNodeUndirected(cherry);
        apple.connectToNodeUndirected(plum);
        cherry.connectToNodeUndirected(plum);
        plum.connectToNodeUndirected(mango);
        mango.connectToNodeUndirected(kiwi);
        kiwi.connectToNodeUndirected(coconut);
        kiwi.connectToNodeUndirected(orange);
        mango.connectToNodeUndirected(banana);

        List<Node<?>> encountered = new ArrayList<>();
        encountered.add(apple);
        List<Node<?>> path = RouteFinderController.findPathDepthFirst(pear, encountered, cherry.data);
        System.out.println(path);
        assertTrue(path.contains(pear) && path.contains(cherry) && !path.contains(apple));
    }

    @Test
    void findPathDepthFirstReturnsNoPathBetweenNodesWithAllNodesEncountered() {
        List<Node<?>> encountered = new ArrayList<>();
        Node<String> pear = new Node<>("Pear");
        Node<String> orange = new Node<>("Orange");
        Node<String> cherry = new Node<>("Cherry");
        Node<String> apple = new Node<>("Apple");
        Node<String> plum = new Node<>("Plum");
        Node<String> mango = new Node<>("Mango");
        Node<String> kiwi = new Node<>("Kiwi");
        Node<String> coconut = new Node<>("Coconut");
        Node<String> banana = new Node<>("Banana");

        encountered.add(pear);
        encountered.add(orange);
        encountered.add(cherry);
        encountered.add(apple);
        encountered.add(plum);
        encountered.add(plum);
        encountered.add(mango);
        encountered.add(kiwi);
        encountered.add(coconut);
        encountered.add(banana);

        pear.connectToNodeUndirected(apple);
        pear.connectToNodeUndirected(kiwi);
        apple.connectToNodeUndirected(cherry);
        apple.connectToNodeUndirected(plum);
        cherry.connectToNodeUndirected(plum);
        plum.connectToNodeUndirected(mango);
        mango.connectToNodeUndirected(kiwi);
        kiwi.connectToNodeUndirected(coconut);
        kiwi.connectToNodeUndirected(orange);
        mango.connectToNodeUndirected(banana);

        List<Node<?>> path = RouteFinderController.findPathDepthFirst(pear, encountered, cherry.data);
        //System.out.println(path);
        assertTrue(path == null);
    }

    @Test
    void findAllPathsDepthFirstReturnsAllValidPaths() {
        List<Node<?>> encountered = new ArrayList<>();
        Node<String> pear = new Node<>("Pear");
        Node<String> orange = new Node<>("Orange");
        Node<String> cherry = new Node<>("Cherry");
        Node<String> apple = new Node<>("Apple");
        Node<String> plum = new Node<>("Plum");
        Node<String> mango = new Node<>("Mango");
        Node<String> kiwi = new Node<>("Kiwi");
        Node<String> coconut = new Node<>("Coconut");
        Node<String> banana = new Node<>("Banana");

        pear.connectToNodeUndirected(apple);
        pear.connectToNodeUndirected(kiwi);
        apple.connectToNodeUndirected(cherry);
        apple.connectToNodeUndirected(plum);
        cherry.connectToNodeUndirected(plum);
        plum.connectToNodeUndirected(mango);
        mango.connectToNodeUndirected(kiwi);
        kiwi.connectToNodeUndirected(coconut);
        kiwi.connectToNodeUndirected(orange);
        mango.connectToNodeUndirected(banana);

        List<List<Node<?>>> allPaths = RouteFinderController.findAllPathsDepthFirst(pear, encountered, cherry.data);
        //System.out.println(allPaths);
        assertTrue(allPaths.size() == 4 && allPaths.get(0).contains(pear) && allPaths.get(0).contains(cherry));
    }

    @Test
    void findAllPathsDepthFirstReturnsAllValidPathsForEncounteredNodes() {
        List<Node<?>> encountered = new ArrayList<>();
        Node<String> pear = new Node<>("Pear");
        Node<String> orange = new Node<>("Orange");
        Node<String> cherry = new Node<>("Cherry");
        Node<String> apple = new Node<>("Apple");
        Node<String> plum = new Node<>("Plum");
        Node<String> mango = new Node<>("Mango");
        Node<String> kiwi = new Node<>("Kiwi");
        Node<String> coconut = new Node<>("Coconut");
        Node<String> banana = new Node<>("Banana");

        pear.connectToNodeUndirected(apple);
        pear.connectToNodeUndirected(kiwi);
        apple.connectToNodeUndirected(cherry);
        apple.connectToNodeUndirected(plum);
        cherry.connectToNodeUndirected(plum);
        plum.connectToNodeUndirected(mango);
        mango.connectToNodeUndirected(kiwi);
        kiwi.connectToNodeUndirected(coconut);
        kiwi.connectToNodeUndirected(orange);
        mango.connectToNodeUndirected(banana);

        encountered.add(pear);
        encountered.add(orange);
        encountered.add(apple);

        List<List<Node<?>>> allPaths = RouteFinderController.findAllPathsDepthFirst(pear, encountered, cherry.data);
        //System.out.println(allPaths);
        assertTrue(allPaths.size() == 1 && allPaths.get(0).contains(pear) && allPaths.get(0).contains(cherry));
    }

    @Test
    void findAllPathsDepthFirstReturnsNullIfAllNodesAreEncountered() {
        List<Node<?>> encountered = new ArrayList<>();
        Node<String> pear = new Node<>("Pear");
        Node<String> orange = new Node<>("Orange");
        Node<String> cherry = new Node<>("Cherry");
        Node<String> apple = new Node<>("Apple");
        Node<String> plum = new Node<>("Plum");
        Node<String> mango = new Node<>("Mango");
        Node<String> kiwi = new Node<>("Kiwi");
        Node<String> coconut = new Node<>("Coconut");
        Node<String> banana = new Node<>("Banana");

        encountered.add(pear);
        encountered.add(orange);
        encountered.add(cherry);
        encountered.add(apple);
        encountered.add(plum);
        encountered.add(plum);
        encountered.add(mango);
        encountered.add(kiwi);
        encountered.add(coconut);
        encountered.add(banana);

        pear.connectToNodeUndirected(apple);
        pear.connectToNodeUndirected(kiwi);
        apple.connectToNodeUndirected(cherry);
        apple.connectToNodeUndirected(plum);
        cherry.connectToNodeUndirected(plum);
        plum.connectToNodeUndirected(mango);
        mango.connectToNodeUndirected(kiwi);
        kiwi.connectToNodeUndirected(coconut);
        kiwi.connectToNodeUndirected(orange);
        mango.connectToNodeUndirected(banana);


        List<List<Node<?>>> allPaths = RouteFinderController.findAllPathsDepthFirst(pear, encountered, cherry.data);
        //System.out.println(allPaths);
        assertTrue(allPaths == null);
    }

    @Test
    void findPathBreadthFirstReturnsAValidPath() {
        List<Node<?>> encountered = new ArrayList<>();
        Node<String> pear = new Node<>("Pear");
        Node<String> orange = new Node<>("Orange");
        Node<String> cherry = new Node<>("Cherry");
        Node<String> apple = new Node<>("Apple");
        Node<String> plum = new Node<>("Plum");
        Node<String> mango = new Node<>("Mango");
        Node<String> kiwi = new Node<>("Kiwi");
        Node<String> coconut = new Node<>("Coconut");
        Node<String> banana = new Node<>("Banana");

        pear.connectToNodeUndirected(apple);
        pear.connectToNodeUndirected(kiwi);
        apple.connectToNodeUndirected(cherry);
        apple.connectToNodeUndirected(plum);
        cherry.connectToNodeUndirected(plum);
        plum.connectToNodeUndirected(mango);
        mango.connectToNodeUndirected(kiwi);
        kiwi.connectToNodeUndirected(coconut);
        kiwi.connectToNodeUndirected(orange);
        mango.connectToNodeUndirected(banana);

        List<Node<?>> path = RouteFinderController.findPathBreadthFirstly(pear,null, null, cherry.data);
        //System.out.println(path);
        assertTrue(path.contains(pear) && path.contains(cherry));
    }

    @Test
    void findPathBreadthFirstReturnsAValidPathWithEncounteredNodes() {
        List<Node<?>> encountered = new ArrayList<>();
        Node<String> pear = new Node<>("Pear");
        Node<String> orange = new Node<>("Orange");
        Node<String> cherry = new Node<>("Cherry");
        Node<String> apple = new Node<>("Apple");
        Node<String> plum = new Node<>("Plum");
        Node<String> mango = new Node<>("Mango");
        Node<String> kiwi = new Node<>("Kiwi");
        Node<String> coconut = new Node<>("Coconut");
        Node<String> banana = new Node<>("Banana");

        encountered.add(orange);
        encountered.add(apple);

        pear.connectToNodeUndirected(apple);
        pear.connectToNodeUndirected(kiwi);
        apple.connectToNodeUndirected(cherry);
        apple.connectToNodeUndirected(plum);
        cherry.connectToNodeUndirected(plum);
        plum.connectToNodeUndirected(mango);
        mango.connectToNodeUndirected(kiwi);
        kiwi.connectToNodeUndirected(coconut);
        kiwi.connectToNodeUndirected(orange);
        mango.connectToNodeUndirected(banana);

        List<Node<?>> path = RouteFinderController.findPathBreadthFirstly(pear, encountered,null, cherry.data);
        System.out.println(path);
        assertTrue(path.contains(pear) && path.contains(cherry) && !path.contains(apple));
    }

    @Test
    void costedPathReturnsCheapestPath() {
        NodeWithCost<Character> q=new NodeWithCost<>('Q'); NodeWithCost<Character> w=new NodeWithCost<>('W'); NodeWithCost<Character> e=new NodeWithCost<>('E'); NodeWithCost<Character> r=new NodeWithCost<>('R'); NodeWithCost<Character> t=new NodeWithCost<>('T'); NodeWithCost<Character> y =new NodeWithCost<>('Y');
        q.connectToNodeUndirected(w,5); w.connectToNodeUndirected(y,20); w.connectToNodeUndirected(e,2);
        e.connectToNodeUndirected(t,7);
        t.connectToNodeUndirected(r,4);
        t.connectToNodeUndirected(y,7);
        r.connectToNodeUndirected(y,6);

//        System.out.println("The cheapest path currentNode Q to Y is:");
        CostedPath cp=searchGraphDepthFirstCheapestPath(q,null,0,'Y');
//        for(NodeWithCost<?> n : cp.pathList)
//        System.out.println(n.data);
//        System.out.println("The total path cost is: "+cp.pathCost);
        assertTrue(cp.pathCost == 21.0);
    }

    @Test
    void dijkstrasAlgorithimReturnsCorrectPath() {
    NodeWithCost<String> a=new NodeWithCost<>("Silver"); NodeWithCost<String> b=new NodeWithCost<>("Bronze"); NodeWithCost<String> c=new NodeWithCost<>("Lead"); NodeWithCost<String> d=new NodeWithCost<>("Tin"); NodeWithCost<String> e=new NodeWithCost<>("Copper"); NodeWithCost<String> f=new NodeWithCost<>("Brass"); NodeWithCost<String> g=new NodeWithCost<>("Iron"); NodeWithCost<String> h=new NodeWithCost<>("Gold");
    a.connectToNodeUndirected(b, 5); a.connectToNodeUndirected(c, 9); b.connectToNodeUndirected(c, 2); b.connectToNodeUndirected(d, 6); c.connectToNodeUndirected(e, 5); d.connectToNodeUndirected(h, 8); d.connectToNodeUndirected(g, 9); e.connectToNodeUndirected(g, 3); e.connectToNodeUndirected(f, 7); f.connectToNodeUndirected(g, 6); g.connectToNodeUndirected(h, 2);
        CostedPath cpa = findCheapestPathDijkstra(a,"Gold");
   // System.out.println("The cheapest path currentNode Silver to Gold"); System.out.println("using Dijkstra's algorithm:"); System.out.println("-------------------------------------");
    //for(NodeWithCost<?> n : cpa.pathList) System.out.println(n.data);
    //System.out.println("\nThe total path cost is: "+cpa.pathCost);
    assertTrue(cpa.pathCost == 17.0);
    }
}