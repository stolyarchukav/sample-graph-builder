package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest extends GraphTestBase {

    private Graph<String> graph;
    private List<UUID> cityIds;

    @BeforeEach
    void setUp() {
        graph = Graph.create();
        cityIds = fillGraph(graph);
    }

    @Test
    void findAllPathsExistInBothDirections() {
        cityIds.forEach(startPoint -> cityIds.forEach(finishPoint -> {
            Collection<Path<String>> pathsDirect = graph.findPaths(startPoint, finishPoint);
            Collection<Path<String>> pathsReverse = graph.findPaths(finishPoint, startPoint);
            assertEquals(pathsDirect.size(), pathsReverse.size());
        }));
    }

    @Test
    void findBestPathsExistsInBothDirections() {
        cityIds.forEach(startPoint -> cityIds.forEach(finishPoint -> {
            Path<String> pathsDirect = graph.findPath(startPoint, finishPoint);
            Path<String> pathsReverse = graph.findPath(finishPoint, startPoint);
            assertEquals(pathsDirect.getTotalWeight(), pathsReverse.getTotalWeight());
            assertEquals(pathsDirect.getSegments().size(), pathsReverse.getSegments().size());
        }));
    }

    @Test
    void printAllPathsBetweenAllcityIds() {
        cityIds.forEach(startPoint -> cityIds.forEach(finishPoint -> {
            Collection<Path<String>> paths = graph.findPaths(startPoint, finishPoint);
            System.out.println(graph.getData(startPoint) + " to " + graph.getData(finishPoint));
            System.out.println("Paths:");
            paths.stream().map(pathWriter::writeToString).forEach(System.out::println);
        }));
    }

    @Test
    void printBestPathsBetweenAllcityIds() {
        cityIds.forEach(startPoint -> cityIds.forEach(finishPoint -> {
            System.out.println(graph.getData(startPoint) + " to " + graph.getData(finishPoint));
            System.out.println(pathWriter.writeToString(graph.findPath(startPoint, finishPoint)));
        }));
    }

}
