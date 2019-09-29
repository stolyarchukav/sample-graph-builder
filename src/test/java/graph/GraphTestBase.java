package graph;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

public abstract class GraphTestBase {

    protected PathWriter pathWriter = new PathWriter();

    protected List<UUID> fillGraph(Graph<String> graph) {
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

        return asList(roma, napoli, orvieto, cerveteri, bracciano);
    }

}