package graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Distance implements Weight {

    private final int kilometers;

    @Override
    public String toString() {
        return kilometers + "km";
    }

    @Override
    public Distance plus(Weight weight) {
        if (weight instanceof Distance) {
            return new Distance(((Distance) weight).kilometers + kilometers);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int compareTo(Weight o) {
        if (o instanceof Distance) {
            return kilometers - ((Distance) o).kilometers;
        }
        throw new IllegalArgumentException();
    }

}
