import java.util.ArrayList;
import java.util.Collections;

public class Heat {
    private ArrayList<SwimmerEntry> swimmers;

    public Heat() {
        this.swimmers = new ArrayList<>();
    }

    public Heat(
        ArrayList<SwimmerEntry> swimmers
    ) {
        this.swimmers = swimmers;
    }

    public ArrayList<SwimmerEntry> getSwimmers() { return swimmers; }

    // fastest in middle
    // [7, 5, 3, 1, 2, 4, 6, 8]
    public void order() {
        ArrayList<SwimmerEntry> ordered = new ArrayList<>(this.swimmers);
        Collections.sort(ordered, new SortByTime());
        int length = this.swimmers.size();
        for (int i = 0; i < length/2; i++) {
            int idx1 = length/2 - i - 1;
            int idx2 = idx1 + 2*i + 1;
            this.swimmers.set(idx1, ordered.get(2*i));
            this.swimmers.set(idx2, ordered.get(2*i + 1));
        }
    }

    public void addSwimmer(SwimmerEntry s) {
        swimmers.add(s);
    }

    public String toString() {
        return swimmers.toString();
    }
}
