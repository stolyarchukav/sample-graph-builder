package graph;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoWeight implements Weight {

    public final static NoWeight INSTANCE = new NoWeight();

    @Override
    public String toString() {
        return "";
    }

    @Override
    public Weight plus(Weight weight) {
        return INSTANCE;
    }

    @Override
    public int compareTo(Weight o) {
        return 0;
    }

}
