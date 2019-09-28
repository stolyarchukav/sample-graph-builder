package graph;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class Path<T> {

    private final Map<Vertex<T>, Weight> segments;

    private Weight totalWeight;

    public Path(Vertex<T> vertex) {
        this.segments = new LinkedHashMap<>();
        addSegment(vertex, null);
    }

    public Path(Path<T> path) {
        this.segments = new LinkedHashMap<>(path.getSegments());
        totalWeight = path.getTotalWeight();
    }

    public boolean addSegment(Vertex<T> vertex, Weight weight) {
        if (! segments.containsKey(vertex)) {
            segments.put(vertex, weight);
            totalWeight = totalWeight != null ? totalWeight.plus(weight) : weight;
            return true;
        }
        return false;
    }

}
