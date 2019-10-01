package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
            Collection<Path<String>> pathsDirect = graph.getAllPaths(startPoint, finishPoint);
            Collection<Path<String>> pathsReverse = graph.getAllPaths(finishPoint, startPoint);
            assertEquals(pathsDirect.size(), pathsReverse.size());
        }));
    }

    @Test
    void findBestPathsExistsInBothDirections() {
        cityIds.forEach(startPoint -> cityIds.forEach(finishPoint -> {
            Optional<Path<String>> pathsDirect = graph.getPath(startPoint, finishPoint);
            Optional<Path<String>> pathsReverse = graph.getPath(finishPoint, startPoint);
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
        cityIds.forEach(startPoint -> cityIds.forEach(finishPoint -> {
            Collection<Path<String>> paths = graph.getAllPaths(startPoint, finishPoint);
            System.out.println(graph.getData(startPoint) + " to " + graph.getData(finishPoint));
            System.out.println("Paths:");
            paths.stream().map(PathWriter::writeToString).forEach(System.out::println);
        }));
    }

    @Test
    void printBestPathsBetweenAllCities() {
        cityIds.forEach(startPoint -> cityIds.forEach(finishPoint -> {
            System.out.println(graph.getData(startPoint) + " to " + graph.getData(finishPoint));
            graph.getPath(startPoint, finishPoint).ifPresent(path -> System.out.println(PathWriter.writeToString(path)));
        }));
    }

}
