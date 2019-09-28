package graph;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ToString
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Graph<T> {

    private final Map<UUID, Vertex<T>> vertices;

    private final PathFinder pathFinder = new DepthTraversalPathFinder();

    public static <T>Graph<T> create() {
        return new Graph<>(new HashMap<>());
    }

    public UUID addVertex(T data) {
        UUID id = UUID.randomUUID();
        Vertex<T> vertex = new Vertex<>(id, data);
        vertices.put(id, vertex);
        return id;
    }

    public T getData(UUID vertexId) {
        return vertices.get(vertexId).getData();
    }

    public void addEdge(UUID firstVertexId, UUID secondVertexId) {
        addEdge(firstVertexId, secondVertexId, NoWeight.INSTANCE);
    }

    public void addEdge(UUID firstVertexId, UUID secondVertexId, Weight weight) {
        Vertex<T> firstVertex = vertices.get(firstVertexId);
        Vertex<T> secondVertex = vertices.get(secondVertexId);
        firstVertex.addEdge(secondVertex, weight);
        secondVertex.addEdge(firstVertex, weight);
    }

    public Path<T> findPath(UUID firstVertexId, UUID secondVertexId) {
        return pathFinder.findPath(vertices, firstVertexId, secondVertexId);
    }

    public List<Path<T>> findPaths(UUID firstVertexId, UUID secondVertexId) {
        return pathFinder.findPaths(vertices, firstVertexId, secondVertexId);
    }

}
