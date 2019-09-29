package graph;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface PathFinder {

    <T> Collection<Path<T>> getAllPaths(Map<UUID, Vertex<T>> vertices, UUID firstVertexId, UUID secondVertexId);

}
