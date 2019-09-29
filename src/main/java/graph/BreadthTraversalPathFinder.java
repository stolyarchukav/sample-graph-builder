package graph;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

public class BreadthTraversalPathFinder implements PathFinder {

    @Override
    public <T>Path<T> findPath(Map<UUID, Vertex<T>> vertices, UUID firstVertexId, UUID secondVertexId) {
        return findPaths(vertices, firstVertexId, secondVertexId).stream()
                .min(Comparator.comparing(Path::getTotalWeight))
                .orElse(null);
    }

    @Override
    public <T> Collection<Path<T>> findPaths(Map<UUID, Vertex<T>> vertices, UUID firstVertexId, UUID secondVertexId) {
        Set<Path<T>> paths = new HashSet<>();
        Set<PathWithTail<T>> nextPaths = new HashSet<>();
        Vertex<T> vertex = vertices.get(firstVertexId);
        Path<T> path = new Path<>(vertex);
        if (firstVertexId.equals(secondVertexId)) {
            return Collections.singleton(path);
        }
        nextPaths.add(new PathWithTail<>(path, vertex));
        while(! nextPaths.isEmpty()) {
            nextPaths = nextPaths.stream().map(item ->
                item.getTail().getEdges().entrySet().stream().map(entry -> {
                    Path<T> nextPath = new Path<>(item.getPath());
                    Vertex<T> tail = vertices.get(entry.getKey());
                    if (nextPath.addSegment(tail, entry.getValue())) {
                        if (tail.getId().equals(secondVertexId)) {
                            paths.add(nextPath);
                        } else {
                            return new PathWithTail<>(nextPath, tail);
                        }
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toSet())
            ).flatMap(Collection::stream).collect(Collectors.toSet());
        }
        return paths;
    }

    @Getter
    @RequiredArgsConstructor
    private static class PathWithTail<T> {
        private final Path<T> path;
        private final Vertex<T> tail;
    }

}
