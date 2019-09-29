package graph;

public class PathWriter {
    
    public <T>String writeToString(Path<T> path) {
        StringBuilder builder = new StringBuilder();
        path.getSegments().forEach((vertex, weight) -> {
            if (weight != null) {
                builder.append(" --").append(weight).append("--> ");
            }
            builder.append(vertex.getData());
        });
        builder.append(". Total weight: ").append(path.getTotalWeight());
        return builder.toString();
    }
    
}
