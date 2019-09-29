package graph;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.*;

@ToString
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Graph<T> {

    private final Map<UUID, Vertex<T>> vertices;

    private final PathFinder pathFinder;

    private final boolean oriented;

    public static <T>Graph<T> create() {
        return create(false);
    }

    public static <T>Graph<T> create(boolean oriented) {
        return create(oriented, new BreadthTraversalPathFinder());
    }

    public static <T>Graph<T> create(PathFinder pathFinder) {
        return create(false, pathFinder);
    }

    public static <T>Graph<T> create(boolean oriented, PathFinder pathFinder) {
        return new Graph<>(new HashMap<>(), pathFinder, oriented);
    }

    public UUID addVertex(T data) {
        UUID id = UUID.randomUUID();
        Vertex<T> vertex = new Vertex<>(id, data);
        vertices.put(id, vertex);
        return id;
    }

    public void addEdge(UUID firstVertexId, UUID secondVertexId) {
        addEdgeInternal(firstVertexId, secondVertexId, NoWeight.INSTANCE);
    }

    public void addEdge(UUID firstVertexId, UUID secondVertexId, Weight weight) {
        addEdgeInternal(firstVertexId, secondVertexId, weight);
    }

    public Optional<Path<T>> getPath(UUID firstVertexId, UUID secondVertexId) {
        verifyExist(firstVertexId, secondVertexId);
        return getBestPath(pathFinder.getAllPaths(vertices, firstVertexId, secondVertexId));
    }

    public Collection<Path<T>> getAllPaths(UUID firstVertexId, UUID secondVertexId) {
        return pathFinder.getAllPaths(vertices, firstVertexId, secondVertexId);
    }

    public T getData(UUID vertexId) {
        return vertices.get(vertexId).getData();
    }

    private void verifyNonNull(Vertex<T> firstVertex, Vertex<T> secondVertex) {
        verifyNonNull(firstVertex);
        verifyNonNull(secondVertex);
    }

    private void verifyNonNull(Vertex<T> vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException();
        }
    }

    private void verifyExist(UUID firstVertexId, UUID secondVertexId) {
        Vertex<T> firstVertex = vertices.get(firstVertexId);
        Vertex<T> secondVertex = vertices.get(secondVertexId);
        verifyNonNull(firstVertex, secondVertex);
    }

    private Optional<Path<T>> getBestPath(Collection<Path<T>> paths) {
        return paths.stream().min(getPathComparator());
    }

    private <T> Comparator<Path<T>> getPathComparator() {
        Comparator<Path<T>> comparator = Comparator.comparing(Path::getTotalWeight);
        return comparator.thenComparing(Path::getSegmentsCount);
    }

    public void addEdgeInternal(UUID firstVertexId, UUID secondVertexId, Weight weight) {
        Vertex<T> firstVertex = vertices.get(firstVertexId);
        Vertex<T> secondVertex = vertices.get(secondVertexId);
        verifyNonNull(firstVertex, secondVertex);
        firstVertex.addEdge(secondVertex, weight);
        if (! oriented) {
            secondVertex.addEdge(firstVertex, weight);
        }
    }

}
