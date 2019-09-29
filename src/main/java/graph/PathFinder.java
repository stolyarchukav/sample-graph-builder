package graph;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface PathFinder {

    <T>Path<T> findPath(Map<UUID, Vertex<T>> vertices, UUID firstVertexId, UUID secondVertexId);

    <T> Collection<Path<T>> findPaths(Map<UUID, Vertex<T>> vertices, UUID firstVertexId, UUID secondVertexId);

}
