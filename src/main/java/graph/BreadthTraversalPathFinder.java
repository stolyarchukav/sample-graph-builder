package graph;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

public class BreadthTraversalPathFinder implements PathFinder {

    @Override
    public <T> Collection<Path<T>> getAllPaths(Map<UUID, Vertex<T>> vertices, UUID firstVertexId, UUID secondVertexId) {
        Vertex<T> vertex = vertices.get(firstVertexId);
        Path<T> initialPath = new Path<>(vertex);
        if (firstVertexId.equals(secondVertexId)) {
            return Collections.singleton(initialPath);
        } else {
            Set<Path<T>> finalPaths = new HashSet<>();
            Set<PathWithTail<T>> currentPaths = new HashSet<>();
            currentPaths.add(new PathWithTail<>(initialPath, vertex));
            while(! currentPaths.isEmpty()) {
                currentPaths = expandPaths(currentPaths, finalPaths, vertices, secondVertexId);
            }
            return finalPaths;
        }
    }

    private <T> Set<PathWithTail<T>> expandPaths(Set<PathWithTail<T>> currentPaths, Set<Path<T>> finalPaths, Map<UUID,
            Vertex<T>> vertices, UUID secondVertexId) {
        return currentPaths.stream().map(item ->
            item.getTail().getEdges().entrySet().stream().map(entry -> {
                Path<T> nextPath = new Path<>(item.getPath());
                Vertex<T> tail = vertices.get(entry.getKey());
                if (nextPath.addSegment(tail, entry.getValue())) {
                    if (tail.getId().equals(secondVertexId)) {
                        finalPaths.add(nextPath);
                    } else {
                        return new PathWithTail<>(nextPath, tail);
                    }
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toSet())
        ).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    @Getter
    @RequiredArgsConstructor
    private static class PathWithTail<T> {
        private final Path<T> path;
        private final Vertex<T> tail;
    }

}
