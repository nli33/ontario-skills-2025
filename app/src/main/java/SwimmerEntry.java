import java.util.Comparator;

public class SwimmerEntry {
    private Swimmer swimmer;
    private EventInfo event;
    private SwimTime seedTime;
    private SwimTime resultTime;

    public SwimmerEntry(
        Swimmer swimmer,
        EventInfo event, 
        SwimTime seedTime, 
        SwimTime resultTime
    ) {
        this.swimmer = swimmer;
        this.event = event;
        this.seedTime = seedTime;
        this.resultTime = resultTime;
    }

    public SwimmerEntry(
        Swimmer swimmer,
        EventInfo event,
        SwimTime seedTime
    ) {
        this.swimmer = swimmer;
        this.event = event;
        this.seedTime = seedTime;
    }

    public String toString() {
        return swimmer.getName() + " - " + swimmer.getGender().toUpperCase().charAt(0) + swimmer.getAge() + " - " + seedTime;
    }

    public SwimTime getSeedTime() { return seedTime; }
    public SwimTime getResultTime() { return resultTime; }
}

class SortByTime implements Comparator<SwimmerEntry> {
    @Override
    public int compare(SwimmerEntry a, SwimmerEntry b) {
        return a.getSeedTime().getTotal() - b.getSeedTime().getTotal();
    }
}