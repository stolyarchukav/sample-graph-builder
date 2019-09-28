package graph;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PathFinder {

    <T>Path<T> findPath(Map<UUID, Vertex<T>> vertices, UUID firstVertexId, UUID secondVertexId);

    <T>List<Path<T>> findPaths(Map<UUID, Vertex<T>> vertices, UUID firstVertexId, UUID secondVertexId);

}
