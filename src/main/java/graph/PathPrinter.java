package graph;

import java.util.Collection;

public class PathPrinter {
    
    public <T>void print(Path<T> path) {
        StringBuilder builder = new StringBuilder();
        path.getSegments().forEach((vertex, weight) -> {
            if (weight != null) {
                builder.append(" --").append(weight).append("--> ");
            }
            builder.append(vertex.getData());
        });
        builder.append(". Total weight: ").append(path.getTotalWeight());
        System.out.println(builder.toString());
    }

    public <T>void print(Collection<Path<T>> paths) {
        System.out.println("Paths:");
        paths.forEach(this::print);
    }
    
}
