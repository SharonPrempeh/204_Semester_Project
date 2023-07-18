package app;

import java.util.ArrayList;
import java.util.HashMap;

public class DijkstraAlgorithm {
    private static final ArrayList<Location> NOT_VISITED = new ArrayList<>();

    private static final HashMap<Location, Integer> DISTANCE_MAP = new HashMap<>();

    private static final HashMap<Location, Location> PREVIOUS_VERTEX = new HashMap<>();

    public static void findShortestPath(DirectedGraph graph, Location source, Location destination) {
        if (source == destination) {
            System.out.print(source.getName());
        }

        for (Location vertex : graph.getNodes()) {
            DISTANCE_MAP.put(vertex, Integer.MAX_VALUE);
            PREVIOUS_VERTEX.put(vertex, null);
            NOT_VISITED.add(vertex);
        }

        DISTANCE_MAP.put(source, 0);

        Location minNode = findVertexWithMinDist();
        while (NOT_VISITED.size() > 0 && minNode != null) {
            minNode = findVertexWithMinDist();

            NOT_VISITED.remove(minNode);

            ArrayList<Path> edges = graph.getDestinationEdges(minNode);
            for (Path edge : edges) {
               
                if (NOT_VISITED.contains(edge.getDestination())) {

                    // Alternative cost
                    int alt = DISTANCE_MAP.get(minNode) + edge.getDistance();

                    if (alt < DISTANCE_MAP.get(edge.getDestination())) { 
                        // Update of min cost to reach this vertex.
                        DISTANCE_MAP.put(edge.getDestination(), alt);

                        // Update of previous vertex to reach this current vertex.
                        PREVIOUS_VERTEX.put(edge.getDestination(), minNode);
                    }
                }
            }
        }
        printShortestPath(source, destination);
        printDistances(destination);
    }

    private static void printDistances(Location destination) {
        System.out.println("Approximate Distance: " +
                String.format("%.3f", DISTANCE_MAP.get(destination)/1000F) + "km \n");
    }

    public static float getTotalDistance(Location destination) {
        return DISTANCE_MAP.get(destination)/1000F;
    }



    public static String getShortestPath(Location source, Location destination) {
        ArrayList<Location> path = new ArrayList<>();

        while (PREVIOUS_VERTEX.get(destination) != null) {
            path.add(destination);
            destination = PREVIOUS_VERTEX.get(destination);
        }
        path.add(source);

        StringBuilder builder = new StringBuilder();
        for (int i = path.size() - 1; i >= 0; i--) {
            Location vertex = path.get(i);
            builder.append(vertex.getName());
            builder.append(" -> ");
        }
        return builder.toString();
    }

    private static void printShortestPath(Location source, Location destination) {
        // Using the preceding Vertex, re-create the path to your target/destination.
        System.out.println("\nShortest path from '" + source.getName() + "' to '" +
                destination.getName() + "'");
        ArrayList<Location> path = new ArrayList<>();
        System.out.print(source.getName());
        while (PREVIOUS_VERTEX.get(destination) != null) {
            path.add(destination);
            destination = PREVIOUS_VERTEX.get(destination);
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            Location vertex = path.get(i);
            System.out.print(" --> " + vertex.getName());
        }
        System.out.println();
    }

    private static Location findVertexWithMinDist() {
        // Linear search for the min cost vertex based on the distance. 
        Location minNode = null;
        long minDistance = Long.MAX_VALUE;
        for (HashMap.Entry<Location, Integer> entry : DISTANCE_MAP.entrySet()) {
            Location vertex = entry.getKey();
            int distance = entry.getValue();
            if (NOT_VISITED.contains(vertex) && distance < minDistance) {
                minDistance = distance;
                minNode = vertex;
            }
        }
        return minNode;
    }
}
