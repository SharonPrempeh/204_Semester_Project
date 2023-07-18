package app;

public class Location {
    private String name;

    public Location(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location){
            Location other = (Location) obj;
            return other.getName().equals(getName());
            }
        return false;
    }
    
    @Override
    public String toString() {
        return this.name;
    }    
}
