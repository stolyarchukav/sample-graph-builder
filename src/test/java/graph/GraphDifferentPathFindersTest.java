package graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GraphDifferentPathFindersTest extends GraphTestBase {

    @Test
    void findAllPathsAreEqual() {
        List<String> paths1 = findAllPaths(Graph.create(new DepthTraversalPathFinder()));
        List<String> paths2 = findAllPaths(Graph.create(new BreadthTraversalPathFinder()));
        Assertions.assertEquals(paths1, paths2);
    }

    @Test
    void findBestPathAreEqual() {
        List<String> paths1 = findBestPath(Graph.create(new DepthTraversalPathFinder()));
        List<String> paths2 = findBestPath(Graph.create(new BreadthTraversalPathFinder()));
        Assertions.assertEquals(paths1, paths2);
    }

    private List<String> findAllPaths(Graph<String> graph) {
        List<UUID> cityIds = fillGraph(graph);
        return cityIds.stream()
                    .flatMap(startPoint -> cityIds.stream()
                            .flatMap(finishPoint -> graph.getAllPaths(startPoint, finishPoint).stream()
                                    .map(pathWriter::writeToString).sorted()))
                    .collect(Collectors.toList());
    }

    private List<String> findBestPath(Graph<String> graph) {
        List<UUID> cityIds = fillGraph(graph);
        return cityIds.stream()
                .flatMap(startPoint -> cityIds.stream()
                        .map(finishPoint -> graph.getPath(startPoint, finishPoint)
                                .map(pathWriter::writeToString).orElse(null)))
                .collect(Collectors.toList());
    }

}
