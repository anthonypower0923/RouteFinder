package com.example.routefinder;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RouteFinderController<T> {
    static Stage stage;
    ArrayList<Station> stations = new ArrayList<>();
    List<Node<?>> graph = new ArrayList<>();


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
        reader = new CSVReader(new FileReader("/Users/anthonypower/Documents/semester4/data_structures_&_algorithims2/RouteFinder/src/main/resources/com/example/routefinder/connections.csv"));
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
            if ((initial - 1 < 308) && (dest - 1 < 308)) {
                node = (Node<T>) graph.get(initial - 1);
                otherNode = (Node<T>) graph.get(dest - 1);
                node.connectToNodeUndirected(otherNode);
//                System.out.println(" ");
//                System.out.println(node.adjList);
            }
        }
        System.out.println(findPathDepthFirst(graph.get(200), null,otherNode.data));
    }


    public static <T> List<Node<?>> findPathDepthFirst(Node<?> from, List<Node<?>> encountered, T lookingfor) {
        List<Node<?>> result;
        if (from.data.equals(lookingfor)) { //Found it
        result = new ArrayList<>();
        result.add(from); //Add the current node as the only/last entry in the path list
        return result; //Return the path list
    }

    if(encountered==null)
        encountered=new ArrayList<>(); //First node so create new (empty) encountered list
        encountered.add(from);

    for(Node<?> adjNode :from.adjList)
        if(!encountered.contains(adjNode)) {
            result = findPathDepthFirst(adjNode, encountered, lookingfor);
            if (result != null) {
               result.add(0,from);
               return result;
            }
        }
    return null;
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

}
