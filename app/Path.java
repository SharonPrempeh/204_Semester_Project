package app;

import java.util.ArrayList;


public class Path implements Comparable<Path>{
    private Location source;
    private Location destination;
    private long time;
    private int distance;
    private ArrayList<String> landMarks = new ArrayList<>(); 

   
    public Path(Location source, Location destination, int distance){
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.time = -1;
    }

    public Path(Location source, Location destination, int distance, long time){
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.time = time;
    }

    public Location getDestination() {
        return destination;
    }

    public Location getSource() {
        return source;
    }

    public int getDistance() {
        return distance;
    }
    
    public long getTime() {
        return time;
    }

    
    @Override
    public String toString() {
        return source.getName() +  " -> " + destination.getName() + " " + getDistance();
    }

    @Override
    public int compareTo(Path other) {
        if (getDistance() > other.getDistance())
            return 1;
        else if (getDistance()< other.getDistance())
            return -1;
        return 0;
    }

}
