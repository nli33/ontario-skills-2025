import java.io.*;
import java.util.*;

public class Meet {
    private ArrayList<Event> events;
    private int heatSize;
    private Seeding seeding;
    private Time startTime;
    
    public Meet(
        SwimmerList swimmers,
        int heatSize,
        Seeding seeding
    ) {
        this.heatSize = heatSize;
        this.seeding = seeding;
        HashMap<EventInfo,Event> eventTypes = new HashMap<EventInfo,Event>();
        for (Swimmer s : swimmers) {
            for (EventInfo e : s.getEvents().keySet()) {
                EventInfo existingE = null;
                for (EventInfo prevE : eventTypes.keySet()) {
                    if (e.equals(prevE)) {
                        existingE = prevE;
                        break;
                    }
                }
                if (existingE != null) {
                    eventTypes.get(existingE).addSwimmer(s.getEntry(e));
                } else {
                    eventTypes.put(e, new Event(e, heatSize, seeding));
                }
            }
        }
        events = new ArrayList<>(eventTypes.values());
    }

    public void createPsych(String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("Meet Psych Sheet\n\n");
            for (int i = 0; i < events.size(); i++) {
                Event ev = events.get(i);
                bw.write("Event " + (i+1) + ": " + ev.getEventInfo() + "\n");
                int h = 1;
                for (Heat heat: ev.getHeats()) {
                    bw.write("Heat " + h + "\n");
                    for (SwimmerEntry s: heat.getSwimmers()) {
                        bw.write(s + "\n");
                    }
                    h++;
                }
                bw.write("\n");
            }
        }
    }

    public void createSchedule(String scheduleFile, String startTimeStr, int interval) throws IOException {
        Time startTime = new Time(startTimeStr);
        this.startTime = startTime;
        Time currTime = new Time(startTime);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(scheduleFile))) {
            bw.write("Predicted Meet Schedule\n\n");

            for (int i = 0; i < events.size(); i++) {
                Event ev = events.get(i);
                int h = 1;
                for (Heat heat: ev.getHeats()) {
                    int maxTime = 0;
                    for (SwimmerEntry s: heat.getSwimmers()) {
                        maxTime = Math.max(maxTime, s.getSeedTime().getTotal());
                    }
                    bw.write("Event " + (i+1) + ", Heat " + h + ": " + currTime + "\n");
                    h++;
                    currTime = currTime.getTimeAfter(interval + maxTime);
                }
            }
        }
    }

    public ArrayList<Event> getEvents() { return events; }
}
