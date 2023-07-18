package app;

public class Map extends DirectedGraph {
    Path dg;
    @Override
    public void addEdge(Path edge){

        this.dg = edge;
        if(ghu.contains(edge)) return;

        this.ghu.add(edge);
        this.ghu.add(new Path(edge.getDestination(), edge.getSource(), edge.getDistance()));
        for(Location vertex : map.keySet()){
            if (vertex == edge.getSource()){
                map.get(vertex).add(edge.getDestination());
            }
        }


        for(Location vertex : map.keySet()){
            if (vertex == edge.getDestination()){
                map.get(vertex).add(edge.getSource());
            }
        }
    }

    public int getDistance(Location locations, Location locations1){
        return dg.getDistance();
    }
}
