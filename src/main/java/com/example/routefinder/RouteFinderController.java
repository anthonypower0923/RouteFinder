package com.example.routefinder;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RouteFinderController {
    static Stage stage;
    ArrayList<Station> stations = new ArrayList<>();


    public void openCSVFile() throws IOException {
        List<List<String>> list = new ArrayList<List<String>>();
        CSVReader reader = null;
        try
        {
//parsing a CSV file into CSVReader class constructor
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(stage);
            reader = new CSVReader(new FileReader(selectedFile.getPath()));
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
                String line = (String) e.get(6);
                line = line.replaceAll("\\uFEFF", "");


                Station station = new Station(Integer.parseInt(id),Double.parseDouble(latitude),Double.parseDouble(longitude), (String) e.get(3), Double.parseDouble(zone), Integer.parseInt(totalLines),Integer.parseInt(line));
                stations.add(station);
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
//        System.out.println(stations.toString());
    }

    public void shortestDistanceUsingDijkstrasAlgorithim() {
        Node node = null;
        Node otherNode = null;
        Graph graph = new Graph();
        for (Station station : stations) {
        for (int i = 1; i < stations.size(); i++) {
                node = new Node(station.getName());
            if (!stations.get(i).getName().equals(station.getName()))
                otherNode = new Node(stations.get(i).getName());

            if (station.getLine().equals(stations.get(i).getLine()) || station.getTotalLines() == 2) {
                node.addDestination(otherNode, distance(station.getLatitude(), stations.get(i).getLatitude(), station.getLongitude(), stations.get(i).getLongitude()));
                graph.addNode(node);
                i++;
            }
            }
        }
        System.out.println(graph);
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
