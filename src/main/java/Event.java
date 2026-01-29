import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String formattedDateFrom = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedDateTo = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + formattedDateFrom + " to: " + formattedDateTo + ")";
    }

    @Override
    public String formatIntoData() {
        return "E | " + super.formatIntoData() + " | " + this.from + " | " + this.to;
    }
}
