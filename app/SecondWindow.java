package app;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import static app.Main.graph;

public class SecondWindow extends JFrame {

    private final JButton backButton;
    private final JComboBox<String> sourceCombo;
    private final JComboBox<String> landMarkCombo;
    private final JComboBox<String> destinationCombo;
    private final JLabel firstHalfPath;
    private final JLabel secondHalfPath;
    private final JLabel distanceDisplay;

    SecondWindow(){

        backButton = new JButton();
        backButton.setText("Back");
        backButton.setBounds(0,0,100,30);
        backButton.setFocusable(false);
        backButton.setBorder(new CornerRadius(10));
        backButton.setForeground(Color.WHITE); // Set text color to white
backButton.setBackground(Color.RED); // Set background color to red
        backButton.addActionListener(this::mainPage);
        this.add(backButton);

        JLabel currentLocation = new JLabel();
        currentLocation.setText("Current location:");
        currentLocation.setBounds(30, 35, 200, 40);
        currentLocation.setFont(new Font("Serif", Font.BOLD, 20));
        // Set the text color
        currentLocation.setForeground(Color.WHITE);
        this.add(currentLocation);

        JLabel landMarkLocation = new JLabel();
        landMarkLocation.setText("landmark:");
        landMarkLocation.setBounds(30, 80, 250, 40);
        landMarkLocation.setFont(new Font("Serif", Font.BOLD, 20));
        // Set the text color
        landMarkLocation.setForeground(Color.WHITE);
        this.add(landMarkLocation);

        JLabel destinationLocation = new JLabel();
        destinationLocation.setText("Destination:");
        destinationLocation.setBounds(30, 120, 200, 40);
        destinationLocation.setFont(new Font("Serif", Font.BOLD, 20));
         // Set the text color
         destinationLocation.setForeground(Color.WHITE);
        this.add(destinationLocation);

        String[] places =
                {"Akuafo Hall","UG Fire Service","Law school", "Night Market", "Main Gate", "CS Department", "Athletic Oval"
                        ,"Diaspora","Volta Hall","Common wealth hall","Business School", "Legon Hall", "Great Hall",
                        "NNB", "N Block", "JQB", "Balme+ Library","UGCS", "Pentagon Hall"};

        sourceCombo = new JComboBox<>(places);
        sourceCombo.setBounds(189, 40, 200, 30);
        this.add(sourceCombo);

        landMarkCombo = new JComboBox<>(places);
        landMarkCombo.setBounds(135,85,200,30);
        this.add(landMarkCombo);

        destinationCombo = new JComboBox<>(places);
        destinationCombo.setBounds(140, 125, 200, 30);
        this.add(destinationCombo);

        JLabel initialPath = new JLabel();
        initialPath.setText("Path to landmark:");
        initialPath.setBounds(10, 225, 250, 40);
        initialPath.setFont(new Font("Serif",Font.BOLD, 20));
       // Set the text color
       initialPath.setForeground(Color.WHITE);
        this.add(initialPath);

        JLabel secondPath = new JLabel();
        secondPath.setText("Final Destination: ");
        secondPath.setBounds(10, 270, 250, 40);
        secondPath.setFont(new Font("Serif",Font.BOLD, 20));
        // Set the text color
        secondPath.setForeground(Color.WHITE);
        this.add(secondPath);

        firstHalfPath = new JLabel();
        firstHalfPath.setBounds(175, 225, 570, 40);
        firstHalfPath.setBorder(new CornerRadius(10));
        firstHalfPath.setFont(new Font("Serif",Font.BOLD, 15));
        this.add(firstHalfPath);

        secondHalfPath = new JLabel();
        secondHalfPath.setBounds(175, 270, 570, 40);
        secondHalfPath.setBorder(new CornerRadius(10));
        secondHalfPath.setFont(new Font("Serif",Font.BOLD, 15));
        this.add(secondHalfPath);

        distanceDisplay = new JLabel();
        distanceDisplay.setBounds(250, 310, 400, 40);
        distanceDisplay.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(distanceDisplay);

        JButton getPossiblePaths = new JButton("Get shortest path ");
        getPossiblePaths.setBounds(150, 180, 135, 25);
        getPossiblePaths.setBorder(new CornerRadius(10));
        getPossiblePaths.setFocusable(false);
        getPossiblePaths.setForeground(Color.WHITE); // Set the text color
                // Set the background color
getPossiblePaths.setBackground(Color.BLUE);
        this.add(getPossiblePaths);


        getPossiblePaths.addActionListener(this::getPaths);


        this.setLayout(null);
        this.setSize(760,374);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        this.setTitle("Shortest routes based on landmarks");
        this.setVisible(true);
    }

    // Handles switching from landmark page to UI page.
    private void mainPage(ActionEvent actionEvent){
        if (actionEvent.getSource() == backButton){
            this.dispose();
            new UserInterface();
        }
    }

    // Prints paths and total distance.
    private void getPaths(ActionEvent actionEvent) {
        try {
            String theOrigin = sourceCombo.getSelectedItem().toString();
            String theLandMark = landMarkCombo.getSelectedItem().toString();
            String theEnd = destinationCombo.getSelectedItem().toString();

            // Gets item of source, landmark and destination.
            Location sourceDijkstra = graph.getNodeByName(theOrigin);
            Location landMarkDijkstra = graph.getNodeByName(theLandMark);
            Location destinationDijkstra = graph.getNodeByName(theEnd);

            //  Print path from source to landmark and landmark to destination.

            // Finds path and get distance between source and landmark.
            DijkstraAlgorithm.findShortestPath(graph, sourceDijkstra, landMarkDijkstra);
            String firstPath = DijkstraAlgorithm.getShortestPath(sourceDijkstra, landMarkDijkstra);
            float firstPathDistance = DijkstraAlgorithm.getTotalDistance(landMarkDijkstra);

            // Finds path and get distance between landmark and destination.
            DijkstraAlgorithm.findShortestPath(graph, landMarkDijkstra, destinationDijkstra);
            String secondPath = DijkstraAlgorithm.getShortestPath(landMarkDijkstra, destinationDijkstra);
            float secondPathDistance = DijkstraAlgorithm.getTotalDistance(destinationDijkstra);

            float totalPathDistance = firstPathDistance + secondPathDistance;

            firstHalfPath.setText(firstPath);
            secondHalfPath.setText(secondPath);

            firstHalfPath.setForeground(Color.WHITE);
        secondHalfPath.setForeground(Color.WHITE);

            distanceDisplay.setText("Total distance in km: " + totalDistance(totalPathDistance));
            distanceDisplay.setForeground(Color.WHITE); // Set text color to white
        }catch (NullPointerException exception){
            System.out.println(exception.getMessage());
        }
    }
    private static String totalDistance(float total){
        return String.format("%.3f", total);
    }

}
