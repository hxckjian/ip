package neko.task;

import neko.NekoException;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new task with the specified description.
     * The task is marked as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new task with the specified description and completion status.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void setUnDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Snoozes the task by a given number of days.
     *
     * @param days Number of days to postpone.
     * @throws NekoException If the task type does not support snoozing.
     */
    public void snooze(int days) throws NekoException {
        throw new NekoException("This task cannot be snoozed.");
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return "["
                + this.getStatusIcon()
                + "] "
                + this.getDescription();
    }

    /**
     * Formats the task into a string suitable for file storage.
     *
     * @return Serialized task representation.
     */
    public String formatIntoData() {
        String n;
        if (this.isDone) {
            n = "1";
        } else {
            n = "0";
        }
        return n + " | " + this.description;
    }
}
