package neko.task;

/**
 * Represents an todo task.
 */
public class ToDo extends Task {
    /**
     * Creates a new todo task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a new todo task with the specified description and status.
     *
     * @param description Description of the todo task.
     * @param isDone Whether the task is completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo task for display.
     *
     * @return Formatted todo task string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the todo task into a string suitable for file storage.
     *
     * @return Serialized todo task representation.
     */
    @Override
    public String formatIntoData() {
        return "T | " + super.formatIntoData();
    }
}
