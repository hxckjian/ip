public class Event extends Task {
    private String from;
    private String by;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.by = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.by = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + by + ")";
    }

    @Override
    public String formatIntoData() {
        return "E | " + super.formatIntoData() + " | " + this.from + " | " + this.by;
    }
}
