package neko.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an deadline task with an end date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a new deadline task with the specified description and due date.
     * The task is initially marked as not done.
     *
     * @param description Description of the deadline task.
     * @param by Due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new deadline task with the specified description,
     * due date, and completion status.
     *
     * @param description Description of the deadline task.
     * @param by Due date of the task.
     * @param isDone Whether the task is completed.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task for display.
     *
     * @return Formatted deadline task string.
     */
    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Formats the deadline task into a string suitable for file storage.
     *
     * @return Serialized deadline task representation.
     */
    @Override
    public String formatIntoData() {
        return "D | " + super.formatIntoData() + " | " + this.by;
    }
}
