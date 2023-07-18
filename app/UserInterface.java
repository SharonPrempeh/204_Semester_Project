package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static app.Main.graph;
import java.awt.Color;

public class UserInterface extends JFrame {

    private final JComboBox<String> sourceCombo;
    private final JComboBox<String> destinationCombo;
    private final JLabel shortestPathDisplay;
    private final JLabel distanceDisplay;
    private final JButton landMarkButton;

    UserInterface(){
        this.setTitle("The shortest to your destination");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(690, 330);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);

        JLabel currentLocation = new JLabel();
        currentLocation.setText("Current location:");
        currentLocation.setBounds(30, 35, 200, 40);
        currentLocation.setFont(new Font("Serif", Font.BOLD, 20));
        // Set the text color
        currentLocation.setForeground(Color.WHITE);
        this.add(currentLocation);

       
        JLabel destinationLocation = new JLabel();
        destinationLocation.setText("Destination:");
        destinationLocation.setBounds(30, 80, 200, 40);
        destinationLocation.setFont(new Font("Serif", Font.BOLD, 20));
        currentLocation.setForeground(Color.WHITE);
         // Set the text color
         destinationLocation.setForeground(Color.WHITE);
        this.add(destinationLocation);

        String[] places =
        {"Akuafo Hall","UG Fire Service","Law school", "Night Market", "Main Gate", "CS Department", "Athletic Oval"
        ,"Diaspora","Volta Hall","Common wealth hall","Business School", "Legon Hall", "Great Hall",
        "NNB", "N Block", "JQB", "Balme+ Library","UGCS", "Pentagon Hall"};

        sourceCombo = new JComboBox<>(places);
        sourceCombo.setBounds(185, 40, 200, 30);
        this.add(sourceCombo);

        destinationCombo = new JComboBox<>(places);
        destinationCombo.setBounds(145, 85, 200, 30);
        this.add(destinationCombo);



        shortestPathDisplay = new JLabel();
        shortestPathDisplay.setBounds(50, 170, 600, 40);
        shortestPathDisplay.setBorder(new CornerRadius(10));
        shortestPathDisplay.setFont(new Font("Serif",Font.BOLD, 15));
        this.add(shortestPathDisplay);

        distanceDisplay = new JLabel();
        distanceDisplay.setBounds(90, 210, 600, 40);
        distanceDisplay.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(distanceDisplay);

        JButton getPossiblePaths = new JButton("Get shortest path");
        getPossiblePaths.setBounds(120, 125, 130, 25);
        getPossiblePaths.setBorder(new CornerRadius(10));
        getPossiblePaths.setFocusable(false);
        getPossiblePaths.setForeground(Color.WHITE); // Set text color to white
        // Set the background color
getPossiblePaths.setBackground(Color.BLUE);

landMarkButton = new JButton("Use a Landmark");
landMarkButton.setBounds(120, 258, 130, 25);
landMarkButton.setFocusable(false);
landMarkButton.setBorder(new CornerRadius(10));
landMarkButton.setForeground(Color.WHITE); // Set text color to white

Color buttonBackgroundColor = new Color(0, 0, 255); // Example: Set the background color to blue
landMarkButton.setBackground(buttonBackgroundColor);



        this.add(getPossiblePaths);
        this.add(landMarkButton);
        this.setVisible(true);

        getPossiblePaths.addActionListener(this::getPaths);
        landMarkButton.addActionListener(this::landMarkPath);

    }

    private void getPaths(ActionEvent actionEvent) {
        try {
            String theOrigin = sourceCombo.getSelectedItem().toString();
            String theEnd = destinationCombo.getSelectedItem().toString();

            Location sourceDijkstra = graph.getNodeByName(theOrigin);
            Location destinationDijkstra = graph.getNodeByName(theEnd);

            DijkstraAlgorithm.findShortestPath(graph, sourceDijkstra, destinationDijkstra);
            String path = DijkstraAlgorithm.getShortestPath(sourceDijkstra, destinationDijkstra);
            shortestPathDisplay.setText(path);
            shortestPathDisplay.setForeground(Color.WHITE);

            distanceDisplay.setText("Total distance in km: "+DijkstraAlgorithm.getTotalDistance(destinationDijkstra));
            distanceDisplay.setForeground(Color.WHITE); // Set text color to white
        }catch (NullPointerException exception){
            System.out.println(exception.getMessage());
        }
    }

    private void landMarkPath(ActionEvent actionEvent){

        if (actionEvent.getSource() == landMarkButton){
            this.dispose();
            new SecondWindow();
        }
    }

}
