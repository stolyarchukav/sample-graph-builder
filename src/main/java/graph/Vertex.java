package graph;

import lombok.Getter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.UUID;

@ToString(exclude = "edges")
public class Vertex<T> {

    @Getter
    private final UUID id;

    @Getter
    private final T data;

    @Getter
    private final LinkedHashMap<UUID, Weight> edges;

    public Vertex(UUID id, T data) {
        this.id = id;
        this.data = data;
        edges = new LinkedHashMap<>();
    }

    public void addEdge(Vertex<T> node, Weight weight) {
        edges.putIfAbsent(node.getId(), weight);
    }

}
