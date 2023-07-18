package app;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;



public class DirectedGraph {
    protected final HashMap<Location, ArrayList<Location>> map = new HashMap<>();
    protected final ArrayList<Path> ghu = new ArrayList<>();
    private int vertexSize = 0;

    public void addVertex(Location vertex){
        if (!map.containsKey(vertex)){
            map.put(vertex, new ArrayList<>());
            vertexSize++;
        }
    }

    public void addEdge(Path edge){
        if(ghu.contains(edge)) return;

        this.ghu.add(edge);
        for(Location source : map.keySet()){
            if (source == edge.getSource()){
                map.get(source).add(edge.getDestination());
            }
        }
    }

    public ArrayList<Path> getDestinationEdges(Location source){
        ArrayList<Path> destinations = new ArrayList<>();
        for (Path edge: this.ghu){
            if (edge.getSource() == source){
                 destinations.add(edge);
            }
        }
        return destinations;
    }

     public Path getEdge(Location source, Location destination) {
       for (Path edge: this.ghu){
           if (edge.getSource() == source && edge.getDestination() == destination){
               return edge;
           }
       }
       return null;
    }

    public Location getNodeByName(String name){
        for(Location vertex : map.keySet()){
            if (vertex.getName().toLowerCase().equals(name.toLowerCase())){
                return vertex;
            }
        }
        return null;
    }

    public Set<Location> getNodes(){
        return this.map.keySet();
    }

    public int getNodeSize() {
        return vertexSize;
    }

    public void printGraph(){
        System.out.println("\n          GRAPH: ADJACENCY LIST                ");
        System.out.println("              PLACES ON CAMPUS                 \n");
        for (HashMap.Entry<Location, ArrayList<Location>> entry : map.entrySet()) {
            Location vertex = entry.getKey();
            ArrayList<Location> destinations = entry.getValue();
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            boolean emptyList = true;
            for (Location destinatnion : destinations){
                if(emptyList)
                builder.append(destinatnion.getName());
                else
                builder.append(", "+destinatnion.getName());
                emptyList = false;
            }
            builder.append("]");
             System.out.println(vertex.getName() + "--?" + builder.toString());
            System.out.println("");
        }
    }

    public void listPlaces(Location except){
        int index = 1;
        for(Location vertex : map.keySet()){
            if (vertex != except){
                System.out.println(index + ". " + vertex.getName());
            }
            index++;
        }
    }

}
