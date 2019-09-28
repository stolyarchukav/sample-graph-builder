package graph;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
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

}
