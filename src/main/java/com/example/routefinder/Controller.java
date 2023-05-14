package com.example.routefinder;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    AnchorPane anchorpane;
    @FXML
    AnchorPane pane;
    @FXML
    ScrollPane scrollpane = new ScrollPane();
    @FXML
    ImageView imageView = new ImageView();
    @FXML
    SplitPane splitpane;
    @FXML
    ListView<String> listview = new ListView<>();
    @FXML
    TextField initial;
    @FXML
    TextField destination;

    static ArrayList<Station> stations = new ArrayList<>();
    List<Node<?>> graph = new ArrayList<>();
    List<NodeWithCost<?>> graphWithCostNodes = new ArrayList<>();
    RouteFinderController routeFinderController = new RouteFinderController();
    CostedPath costedPath = new CostedPath();

    public void findPathDepthFirst() {
        listview.getItems().removeAll();
        String str = initial.getText();
        System.out.println(str);
        List<Node<?>> path = new ArrayList<>();
        Node<?> node = null;
        for (int i = 0; i < graph.size(); i++) {
            node = graph.get(i);
            if (node.toString().contains(str)) {
                System.out.println("It passed");
                path = routeFinderController.findPathDepthFirst(node, null, null, destination.getText());
                System.out.println(node.toString());
            }
        }
        listview.getItems().add("Single Path Using DFS");
        listview.getItems().add(path.toString());
    }

    public void findAllPathsDepthFirst() {
        listview.getItems().removeAll();
        String str = initial.getText();
        System.out.println(str);
        List<List<Node<?>>> paths = new ArrayList<>();
        Node<?> node = null;
        for (int i = 0; i < graph.size(); i++) {
            node = graph.get(i);
            if (node.toString().contains(str)) {
                System.out.println("It passed");
                paths = routeFinderController.findAllPathsDepthFirst(node, null, destination.getText());
                System.out.println(node.toString());
            }
        }
        listview.getItems().add("All Paths Using DFS");
        listview.getItems().add(paths.toString());
    }

    public void findAllPathBreathFirst() {
        listview.getItems().removeAll();
        String str = initial.getText();
        System.out.println(str);
        List<Node<?>> path = new ArrayList<>();
        Node<?> node = null;
        for (int i = 0; i < graph.size(); i++) {
            node = graph.get(i);
            if (node.toString().contains(str)) {
                System.out.println("It passed");
                path = routeFinderController.findPathBreadthFirstly(node, null,null, destination.getText());
                System.out.println(node.toString());
            }
        }
        listview.getItems().add("Path using BFS");
        listview.getItems().add(path.toString());
    }

    public void costedPath() {
        listview.getItems().removeAll();
        String str = initial.getText();
        System.out.println(str);
        List<Node<?>> path = new ArrayList<>();
        NodeWithCost<?> node = null;
        for (int i = 0; i < graphWithCostNodes.size(); i++) {
            node = graphWithCostNodes.get(i);
            if (node.toString().contains(str)) {
                costedPath = costedPath.searchGraphDepthFirstCheapestPath(node,null,90,destination.getText());
            }
        }
//        listview.getItems().add("Path using BFS");
        listview.getItems().add(costedPath.toString());
    }

    public void dijkstrasAlgorithim() {
        listview.getItems().removeAll();
        String str = initial.getText();
        System.out.println(str);
        List<Node<?>> path = new ArrayList<>();
        NodeWithCost<?> node = null;
        for (int i = 0; i < graphWithCostNodes.size(); i++) {
            node = graphWithCostNodes.get(i);
            if (node.toString().contains(str)) {
                costedPath = costedPath.findCheapestPathDijkstra(node, destination.getText());
            }
        }
//        listview.getItems().add("Path using BFS");
        listview.getItems().add(costedPath.toString());
    }


    public void initialize() throws IOException {
        pane.prefWidthProperty().bind(splitpane.widthProperty());
        pane.prefHeightProperty().bind(splitpane.heightProperty());
        anchorpane.prefWidthProperty().bind(splitpane.widthProperty());
        anchorpane.prefHeightProperty().bind(splitpane.heightProperty());
        listview.prefWidthProperty().bind(anchorpane.widthProperty());
        listview.prefHeightProperty().bind(anchorpane.heightProperty());
        imageView.fitHeightProperty().bind(anchorpane.widthProperty());
        imageView.fitWidthProperty().bind(anchorpane.widthProperty());
        FileInputStream fileInputStream = new FileInputStream("/Users/anthonypower/Documents/semester4/data_structures_&_algorithims2/RouteFinder/src/main/resources/com/example/routefinder/desktop-1920x1080.jpg");
        Image image = new Image(fileInputStream);
        imageView.setImage(image);

        routeFinderController.openCSVFile(stations);
        routeFinderController.createGraphOfStations(stations , graph);
        routeFinderController.createGraphOfStationsWithCost(stations , graphWithCostNodes);

//        System.out.println(findAllselectedItemDepthFirst(graph.get(67),null ,graph.get(69)));

    }
}
