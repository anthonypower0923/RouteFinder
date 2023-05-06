package com.example.routefinder;
import java.io.*;
import java.security.Timestamp;
import java.util.*;

import com.opencsv.CSVReader;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RouteFinderController<T> {
    @FXML
    MenuBar menubar;
    @FXML
    AnchorPane pane;
    @FXML
    ScrollPane scrollpane;
    @FXML
    ImageView imageView;

    static Stage stage;
    ArrayList<Station> stations = new ArrayList<>();
    List<Node<?>> graph = new ArrayList<>();
    static boolean passedWaypoint = false;


    public void openCSVFile() throws IOException {
        List<List<String>> list = new ArrayList<List<String>>();
        CSVReader reader = null;
        try {
//parsing a CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader("/Users/anthonypower/Documents/semester4/data_structures_&_algorithims2/RouteFinder/src/main/resources/com/example/routefinder/london_underground.csv"));
            String[] values = null;
            while ((values = reader.readNext()) != null) {
                list.add(Arrays.asList(values));
            }
            for (List e : list) {
                //This is because of invisible characters as this was made on a Mac
                String id = (String) e.get(0);
                id = id.replaceAll("\\uFEFF", "");
                String latitude = (String) e.get(1);
                latitude = latitude.replaceAll("\\uFEFF", "");
                String longitude = (String) e.get(2);
                longitude = longitude.replaceAll("\\uFEFF", "");
                String zone = (String) e.get(4);
                zone = zone.replaceAll("\\uFEFF", "");
                String totalLines = (String) e.get(5);
                totalLines = totalLines.replaceAll("\\uFEFF", "");


                Station station = new Station(Integer.parseInt(id), Double.parseDouble(latitude), Double.parseDouble(longitude), (String) e.get(3), Double.parseDouble(zone), Integer.parseInt(totalLines));
                stations.add(station);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(stations.toString());
    }

    public void createGraphOfStations() throws IOException {
        List<List<String>> list = new ArrayList<List<String>>();
        CSVReader reader = null;
        reader = new CSVReader(new FileReader("/Users/anthonypower/Documents/semester4/data_structures_&_algorithims2/RouteFinder/src/main/resources/com/example/routefinder/actual_connections.csv"));
        String[] values = null;
        Node<T> node = null;
        Node<T> otherNode = null;
        int i = 0;

        while ((values = reader.readNext()) != null) {
            list.add(Arrays.asList(values));
        }

        for (List e : list) {
            String station1 = (String) e.get(0);
            station1 = station1.replaceAll("\\uFEFF", "");
            int initial = Integer.parseInt(station1);
            String station2 = (String) e.get(1);
            station2 = station2.replaceAll("\\uFEFF", "");
            int dest = Integer.parseInt(station2);
            for (Station station : stations) {
                if (i < 308) {
                    node = new Node(stations.get(i));
                    graph.add(node);
                }
                i++;
            }

            if ((initial - 1 < stations.size()) && (dest - 1 < stations.size())) {
                node = (Node<T>) graph.get(initial - 1);
                otherNode = (Node<T>) graph.get(dest - 1);
                node.connectToNodeUndirected(otherNode);
//                System.out.println(" ");
//                System.out.println(node.adjList);
            }
        }
        //traverseGraphDepthFirst(graph.get(20),null);
        //System.out.println(findAllPathsDepthFirst(graph.get(20),null,null,otherNode.data));
        findAllPathsDepthFirst(graph.get(20),null,null,otherNode.data);
    }


    public static <T> List<Node<?>> findPathDepthFirst(Node<?> currentNode, List<Node<?>> encountered, T lookingfor) {
        List<Node<?>> result;
        if (currentNode.data.equals(lookingfor)) { //Found it
        result = new ArrayList<>();
        result.add(currentNode); //Add the current node as the only/last entry in the path list
        return result; //Return the path list
    }

    if(encountered==null)
        encountered=new ArrayList<>(); //First node so create new (empty) encountered list
        encountered.add(currentNode);

    for(Node<?> adjNode :currentNode.adjList)
        if(!encountered.contains(adjNode)) {
            result = findPathDepthFirst(adjNode, encountered, lookingfor);
            if (result != null) {
               result.add(0,currentNode);
               return result;
            }
        }
    return null;
}


    public static void traverseGraphDepthFirst(Node<?> fromNode,List<Node<?>> encountered){
        if(encountered==null) {
            encountered = new ArrayList<>();
        }
        encountered.add(fromNode);
        for (Node<?> adjNode : fromNode.adjList) {
            if (!encountered.contains(adjNode)) {
                traverseGraphDepthFirst(adjNode, encountered);
            }
        }
    }

    public static <T> List<List<Node<?>>> findAllPathsDepthFirst(Node<?> currentNode, List<Node<?>> encountered,
                                                                 Node<?> waypoint , T lookingfor) {
        List<List<Node<?>>> result = null;
        List<List<Node<?>>> temp2 = null;

        // check if the currentNode is the node we are looking for
        if (currentNode.data.equals(lookingfor)) { //Found it
            List<Node<?>> temp = new ArrayList<>(); //Create new single solution path list
            temp.add(currentNode); //Add current node to the new single path list
            result = new ArrayList<>();
            result.add(temp);
            return result;
        }

        if (encountered == null) {
            encountered = new ArrayList<>(); //First node so create new (empty) encountered list
        }
        encountered.add(currentNode);
        System.out.println(encountered.size());

        for (Node<?> adjNode : currentNode.adjList) {
            if (!encountered.contains(adjNode)) {
                temp2 = findAllPathsDepthFirst(adjNode,new ArrayList<>(encountered),waypoint,lookingfor);

                if (temp2 != null) { //Result of the recursive call contains one or more paths to the solution node
                    for (List<Node<?>> x : temp2)
                        x.add(0,currentNode);
                    if(result==null) result=temp2;
                    else result.addAll(temp2);
                }
            }
        }
        return result;
    }

    public void initialiseFruitGraph() {
        Node<String> pear=new Node<>("Pear");
        Node<String> orange=new Node<>("Orange");
        Node<String> cherry=new Node<>("Cherry");
        Node<String> apple=new Node<>("Apple");
        Node<String> plum=new Node<>("Plum");
        Node<String> mango=new Node<>("Mango");
        Node<String> kiwi=new Node<>("Kiwi");
        Node<String> coconut=new Node<>("Coconut");
        Node<String> banana=new Node<>("Banana");

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

        findAllPathsDepthFirst(pear, null,null,cherry);
        //System.out.println("Coconut Adj List " + coconut.adjList);
        //System.out.println("Orange Adj List " + orange.adjList);
        //System.out.println("Kiwi Adj List " + kiwi.adjList);
        //System.out.println("Mango Adj List " + mango.adjList);

        //System.out.println(traverseGraphDepthFirst(g,null));
    }

    public static <T> List<Node<?>> findPathBreadthFirstly(Node<?> startNode, Node<?> waypoint , T lookingfor) {
        List<List<Node<?>>> agenda = new ArrayList<>(); //Agenda comprised of path lists here!
        List<Node<?>> firstAgendaPath = new ArrayList<>(), resultPath;
        firstAgendaPath.add(startNode);
        agenda.add(firstAgendaPath);
        resultPath = findPathBreadthFirst(agenda, null, waypoint , lookingfor); //Get single BFS path (will be shortest)
        Collections.reverse(resultPath); //Reverse path (currently has the goal node as the first item)
        return resultPath;
    }

    public static <T> List<Node<?>> findPathBreadthFirst(List<List<Node<?>>> agenda, List<Node<?>> encountered, Node<?> waypoint , T lookingfor) {
        if (agenda.isEmpty())
            return null; //Search failed

        List<Node<?>> nextPath = agenda.remove(0); //Get first item (next path to consider) off agenda
        Node<?> currentNode = nextPath.get(0); //The first item in the next path is the current node
        if (currentNode.data.equals(lookingfor))
            return nextPath;


        if (encountered == null)
            encountered = new ArrayList<>(); //First node considered in search so create new (empty) encountered list
            encountered.add(currentNode); //Record current node as encountered so it isn't revisited again


        for (Node<?> adjNode : currentNode.adjList) //For each adjacent node
            if (!encountered.contains(adjNode)) { //If it hasn't already been encountered
                List<Node<?>> newPath = new ArrayList<>(nextPath); //Create a new path list as a copy of the current/next path
                newPath.add(0,adjNode); //And add the adjacent node to the front of the new copy
                agenda.add(newPath); //Add the new path to the end of agenda (end->BFS!)
            }
        return findPathBreadthFirst(agenda, encountered, waypoint , lookingfor); //Tail call
    }

    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }

    public void initialize() throws FileNotFoundException {
        menubar.prefWidthProperty().bind(pane.widthProperty());
        scrollpane.prefWidthProperty().bind(pane.widthProperty());
        scrollpane.prefHeightProperty().bind(pane.heightProperty());
        imageView.fitHeightProperty().bind(scrollpane.widthProperty());
        imageView.fitWidthProperty().bind(scrollpane.widthProperty());
        FileInputStream fileInputStream = new FileInputStream("/Users/anthonypower/Documents/semester4/data_structures_&_algorithims2/RouteFinder/src/main/resources/com/example/routefinder/desktop-1920x1080.jpg");
        Image image = new Image(fileInputStream);
        imageView.setImage(image);
    }


    //This exists to test costed path method for errors

//    NodeWithCost<Character> q=new NodeWithCost<>('Q'); NodeWithCost<Character> w=new NodeWithCost<>('W'); NodeWithCost<Character> e=new NodeWithCost<>('E'); NodeWithCost<Character> r=new NodeWithCost<>('R'); NodeWithCost<Character> t=new NodeWithCost<>('T'); NodeWithCost<Character> y =new NodeWithCost<>('Y');
//        q.connectToNodeUndirected(w,5); w.connectToNodeUndirected(y,20); w.connectToNodeUndirected(e,2);
//        e.connectToNodeUndirected(t,7);
//        t.connectToNodeUndirected(r,4);
//        t.connectToNodeUndirected(y,7);
//        r.connectToNodeUndirected(y,6);
//
//        System.out.println("The cheapest path currentNode Q to Y is:"); CostedPath cp=searchGraphDepthFirstCheapestPath(q,null,0,'Y'); for(NodeWithCost<?> n : cp.pathList)
//            System.out.println(n.data);
//        System.out.println("The total path cost is: "+cp.pathCost);


    //This is to test dijkstra's algorithm
//    NodeWithCost<String> a=new NodeWithCost<>("Silver"); NodeWithCost<String> b=new NodeWithCost<>("Bronze"); NodeWithCost<String> c=new NodeWithCost<>("Lead"); NodeWithCost<String> d=new NodeWithCost<>("Tin"); NodeWithCost<String> e=new NodeWithCost<>("Copper"); NodeWithCost<String> f=new NodeWithCost<>("Brass"); NodeWithCost<String> g=new NodeWithCost<>("Iron"); NodeWithCost<String> h=new NodeWithCost<>("Gold");
//    a.connectToNodeUndirected(b, 5); a.connectToNodeUndirected(c, 9); b.connectToNodeUndirected(c, 2); b.connectToNodeUndirected(d, 6); c.connectToNodeUndirected(e, 5); d.connectToNodeUndirected(h, 8); d.connectToNodeUndirected(g, 9); e.connectToNodeUndirected(g, 3); e.connectToNodeUndirected(f, 7); f.connectToNodeUndirected(g, 6); g.connectToNodeUndirected(h, 2);
//    System.out.println("The cheapest path currentNode Silver to Gold"); System.out.println("using Dijkstra's algorithm:"); System.out.println("-------------------------------------");
//    CostedPath cpa = findCheapestPathDijkstra(a,"Gold");
//for(NodeWithCost<?> n : cpa.pathList) System.out.println(n.data);
//System.out.println("\nThe total path cost is: "+cpa.pathCost);
}
