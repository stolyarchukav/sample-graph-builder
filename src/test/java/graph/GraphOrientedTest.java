package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class GraphOrientedTest extends GraphTestBase {

    private Graph<String> graph;
    private List<UUID> cityIds;

    @BeforeEach
    void setUp() {
        graph = Graph.create(true);
        cityIds = fillGraph(graph);
    }

    @Test
    void findBestPathsDirectAndReverseAreDifferent() {
        Optional<Path<String>> path = graph.getPath(cityIds.get(4), cityIds.get(0));
        assertTrue(path.isPresent());
        assertEquals(new Distance(75), path.get().getTotalWeight());
        assertEquals(3, path.get().getSegmentsCount());

        assertFalse(graph.getPath(cityIds.get(0), cityIds.get(4)).isPresent());
    }

}
