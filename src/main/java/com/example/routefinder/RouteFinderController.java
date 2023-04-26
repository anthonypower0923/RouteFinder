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
        try
        {
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


                Station station = new Station(Integer.parseInt(id),Double.parseDouble(latitude),Double.parseDouble(longitude), (String) e.get(3), Double.parseDouble(zone), Integer.parseInt(totalLines));
                stations.add(station);
                }
        }
        catch (Exception e)
        {
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
            if (initial - 1 < 308 && (dest -1 < 308)) {
                node = new Node(stations.get(initial - 1));
                otherNode = new Node(stations.get(dest - 1));
                node.connectToNodeUndirected(otherNode);
                graph.add(node);
            }
        }
        traverseGraphDepthFirst(graph,null);
    }

    public static <T> void traverseGraphDepthFirst(List<Node<?>> agenda,List<Node<?>> encountered){
        if(agenda.isEmpty()) {
            return;
        }
        Node<?> next=agenda.remove(0);
        System.out.println(next.data.toString());
        if(encountered==null) encountered=new ArrayList<>();
        encountered.add(next);
        for(Node<?> adjNode : next.adjList)
            if (!encountered.contains(adjNode) && !agenda.contains(adjNode)) {
                agenda.add(0,adjNode);
            }
            traverseGraphDepthFirst(agenda,encountered); //Tail call
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
