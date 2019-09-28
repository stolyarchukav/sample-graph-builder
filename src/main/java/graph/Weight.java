package graph;

public interface Weight extends Comparable<Weight> {

    Weight plus(Weight weight);

}
