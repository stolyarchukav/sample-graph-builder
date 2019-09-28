package graph;

import java.util.*;

public class DepthTraversalPathFinder implements PathFinder {

    @Override
    public <T>Path<T> findPath(Map<UUID, Vertex<T>> vertices, UUID firstVertexId, UUID secondVertexId) {
        return findPaths(vertices, firstVertexId, secondVertexId).stream()
                .min(Comparator.comparing(Path::getTotalWeight))
                .orElse(null);
    }

    @Override
    public <T>List<Path<T>> findPaths(Map<UUID, Vertex<T>> vertices, UUID firstVertexId, UUID secondVertexId) {
        Path<T> path = new Path<>(vertices.get(firstVertexId));
        return findSubPaths(vertices, path, firstVertexId, secondVertexId);
    }

    private <T>List<Path<T>> findSubPaths(Map<UUID, Vertex<T>> vertices, Path<T> currentPath,
                                                    UUID currentVertexId, UUID targetVertexId) {
        if (currentVertexId.equals(targetVertexId)) {
            return Collections.singletonList(currentPath);
        }
        Vertex<T> vertex = vertices.get(currentVertexId);
        List<Path<T>> newPaths = new ArrayList<>();
        for (Map.Entry<UUID, Weight> entry : vertex.getEdges().entrySet()) {
            UUID vertexId = entry.getKey();
            Path<T> newPath = new Path<>(currentPath);
            if (newPath.addSegment(vertices.get(vertexId), entry.getValue())) {
                newPaths.addAll(findSubPaths(vertices, newPath, vertexId, targetVertexId));
            }
        }
        return newPaths;
    }

}
