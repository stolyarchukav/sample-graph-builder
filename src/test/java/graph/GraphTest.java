package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.UUID;

import static java.util.Arrays.asList;

public class GraphTest {

    private Graph<String> graph;

    private PathPrinter pathPrinter = new PathPrinter();

    private Collection<UUID> cities;

    @BeforeEach
    void setUp() {
        graph = Graph.create();
        UUID roma = graph.addVertex("Roma");

        UUID napoli = graph.addVertex("Napoli");
        graph.addEdge(roma, napoli, new Distance(227));

        UUID orvieto = graph.addVertex("Orvieto");
        graph.addEdge(roma, orvieto, new Distance(121));

        UUID cerveteri = graph.addVertex("Cerveteri");
        graph.addEdge(cerveteri, orvieto, new Distance(130));
        graph.addEdge(cerveteri, roma, new Distance(55));

        UUID bracciano = graph.addVertex("Bracciano");
        graph.addEdge(bracciano, orvieto, new Distance(97));
        graph.addEdge(bracciano, cerveteri, new Distance(20));

        cities = asList(roma, napoli, orvieto, cerveteri, bracciano);
    }

    @Test
    void findAllPathsBetweenAllCities() {
        cities.forEach(startPoint -> cities.forEach(finishPoint -> {
            System.out.println(graph.getData(startPoint) + " to " + graph.getData(finishPoint));
            pathPrinter.print(graph.findPaths(startPoint, finishPoint));
        }));
    }

    @Test
    void findBestPathsBetweenAllCities() {
        cities.forEach(startPoint -> cities.forEach(finishPoint -> {
            System.out.println(graph.getData(startPoint) + " to " + graph.getData(finishPoint));
            pathPrinter.print(graph.findPath(startPoint, finishPoint));
        }));
    }

}
