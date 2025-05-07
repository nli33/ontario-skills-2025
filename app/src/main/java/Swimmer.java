import java.util.HashMap;

public class Swimmer {
    private int id;
    private String name;
    private int age;
    private String gender;
    private HashMap<EventInfo,SwimTime> events;

    public Swimmer(
        int id, 
        String name, 
        int age, 
        String gender,
        HashMap<EventInfo,SwimTime> events
    ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.events = events;
    }

    public Swimmer(String csvRowStr) {
        String[] csvRow = csvRowStr.strip().split(",");
        this.id = Integer.parseInt(csvRow[0]);
        this.name = csvRow[1];
        this.age = Integer.parseInt(csvRow[2]);
        this.gender = csvRow[3];
        this.events = new HashMap<>();

        for (int i = 4; i < csvRow.length; i += 3) {
            String stroke = csvRow[i];
            int distance = Integer.parseInt(csvRow[i + 1]);
            SwimTime time = new SwimTime(csvRow[i + 2]);
            this.events.put(new EventInfo(stroke, distance), time);
        }
    }

    public SwimmerEntry getEntry(EventInfo event) {
        return new SwimmerEntry(this, event, this.events.get(event));
    }

    public HashMap<EventInfo,SwimTime> getEvents() { return events; }

    public String toString() {
        return "<" + this.id + " " + this.name + " age=" + this.age + " gender=" + this.gender + " events=" + this.events + " >";
    }

    public String getName() { return name; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
}
