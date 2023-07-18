
package app.utils;

import app.Path;

import java.util.Comparator;

public class EdgeTimeComparator implements Comparator<Path>{

    @Override
    public int compare(Path first, Path second) {
        if (first.getTime() > second.getTime())
            return 1;
        else if (first.getTime()< second.getTime())
            return -1;
        return 0;
    }
}
