import java.util.ArrayList;
import java.util.Collections;

enum Seeding {
    Random,
    TimedFinals,
    Circle,
}

public class Event {
    private EventInfo info;
    private ArrayList<SwimmerEntry> swimmers = new ArrayList<>();
    private ArrayList<Heat> heats;
    private int heatSize;
    private Seeding seeding;

    public Event(
        EventInfo info,
        int heatSize,
        Seeding seeding
    ) {
        this.info = info;
        this.heatSize = heatSize;
        this.seeding = seeding;
    }

    public void initHeats() {
        int numSwimmers = swimmers.size();
        heats = new ArrayList<>();
        for (int i = 0; i < (int)Math.ceil(1.0 * numSwimmers / heatSize); i++) {
            heats.add(new Heat());
        }

        switch (seeding) {
        case Circle:
            ArrayList<SwimmerEntry> swimmersCopy = new ArrayList<>(swimmers);
            swimmersCopy.sort(new SortByTime());
            for (int i = 0; i < numSwimmers; i++) {
                heats.get(i % heats.size()).addSwimmer(swimmersCopy.get(i));
            }
            break;
        case Random:
            Collections.shuffle(swimmers);
        case TimedFinals:
            swimmers.sort(new SortByTime());
            for (int i = numSwimmers - 1; i >= 0; i -= heatSize) {
                int heat = i / heatSize;
                for (int j = 0; j < heatSize; j++) {
                    if (0 <= i-j && i-j < numSwimmers) {
                        heats.get(heat).addSwimmer(swimmers.get(numSwimmers-(i-j)-1));
                    }
                }
            }
            break;
        default:
            break;
        }
    }

    public void addSwimmer(SwimmerEntry s) {
        swimmers.add(s);
    }

    public ArrayList<Heat> getHeats() { return heats; }
    public EventInfo getEventInfo() { return info; }
}
