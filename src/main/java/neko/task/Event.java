package neko.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start date and an end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Creates a new event task with the specified description and date range.
     * The task is initially marked as not done.
     *
     * @param description Description of the event.
     * @param from Start date of the event.
     * @param to End date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new event task with the specified description,
     * date range, and completion status.
     *
     * @param description Description of the event.
     * @param from Start date of the event.
     * @param to End date of the event.
     * @param isDone Whether the task is completed.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task for display.
     *
     * @return Formatted event task string.
     */
    @Override
    public String toString() {
        String formattedDateFrom = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedDateTo = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + formattedDateFrom + " to: " + formattedDateTo + ")";
    }

    /**
     * Formats the event task into a string suitable for file storage.
     *
     * @return Serialized event task representation.
     */
    @Override
    public String formatIntoData() {
        return "E | " + super.formatIntoData() + " | " + this.from + " | " + this.to;
    }
}
