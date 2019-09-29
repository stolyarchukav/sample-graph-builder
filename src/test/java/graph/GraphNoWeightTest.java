package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GraphNoWeightTest extends GraphTestBase {

    private Graph<String> graph;
    private List<UUID> ids;

    @BeforeEach
    void setUp() {
        graph = Graph.create();
        UUID p1 = graph.addVertex("p1");
        UUID p2 = graph.addVertex("p2");
        UUID p3 = graph.addVertex("p3");
        UUID p4 = graph.addVertex("p4");

        UUID p5 = graph.addVertex("p5");
        UUID p6 = graph.addVertex("p6");

        graph.addEdge(p1, p2);
        graph.addEdge(p2, p3);
        graph.addEdge(p1, p3);
        graph.addEdge(p3, p4);

        graph.addEdge(p5, p6);
        ids = Arrays.asList(p1, p2, p3, p4, p5, p6);
    }

    @Test
    void findAllPathsExistInBothDirections() {
        ids.forEach(startPoint -> ids.forEach(finishPoint -> {
            Collection<Path<String>> pathsDirect = graph.getAllPaths(startPoint, finishPoint);
            Collection<Path<String>> pathsReverse = graph.getAllPaths(finishPoint, startPoint);
            assertEquals(pathsDirect.size(), pathsReverse.size());
        }));
    }

    @Test
    void findBestPathsExistsInBothDirections() {
        ids.forEach(startPoint -> ids.forEach(finishPoint -> {
            Optional<Path<String>> pathsDirect = graph.getPath(startPoint, finishPoint);
            Optional<Path<String>> pathsReverse = graph.getPath(finishPoint, startPoint);
            System.out.println(pathsDirect);
            System.out.println(pathsReverse);
            pathsDirect.ifPresent(path -> {
                if (pathsReverse.isPresent()) {
                    assertEquals(path.getTotalWeight(), pathsReverse.get().getTotalWeight());
                    assertEquals(path.getSegments().size(), pathsReverse.get().getSegments().size());
                } else {
                    fail("Reverse path does not exist while direct is found");
                }
            });
        }));
    }

    @Test
    void printAllPathsBetweenAllCities() {
        ids.forEach(startPoint -> ids.forEach(finishPoint -> {
            Collection<Path<String>> paths = graph.getAllPaths(startPoint, finishPoint);
            System.out.println(graph.getData(startPoint) + " to " + graph.getData(finishPoint));
            System.out.println("Paths:");
            paths.stream().map(pathWriter::writeToString).forEach(System.out::println);
        }));
    }

    @Test
    void printBestPathsBetweenAllCities() {
        ids.forEach(startPoint -> ids.forEach(finishPoint -> {
            System.out.println(graph.getData(startPoint) + " to " + graph.getData(finishPoint));
            graph.getPath(startPoint, finishPoint).ifPresent(path -> System.out.println(pathWriter.writeToString(path)));
        }));
    }

}
