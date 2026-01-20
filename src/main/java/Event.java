public class Event extends Task {
    private String start;
    private String by;

    public Event(String description, String from, String to) {
        super(description);
        this.start = from;
        this.by = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + by + ")";
    }
}
