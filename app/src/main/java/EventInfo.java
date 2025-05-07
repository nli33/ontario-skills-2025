public class EventInfo {
    private String stroke = "";
    private int distance;

    public EventInfo(String stroke, int distance) {
        this.stroke = stroke;
        this.distance = distance;
    }

    public boolean equals(EventInfo e) {
        return e.getStroke().equals(this.stroke) && e.getDistance() == this.distance;
    }

    public static boolean equals(EventInfo a, EventInfo b) {
        return a.equals(b);
    }

    public String toString() {
        return this.distance + "m " + this.stroke;
    }

    public String getStroke() { return stroke; }
    public int getDistance() { return distance; }
}
