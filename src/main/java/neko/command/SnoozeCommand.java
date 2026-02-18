package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import neko.task.Task;

/**
 * Represents a command that snoozes a task by a specified number of days.
 * The task is identified using a 1-based index provided by the user.
 */
public class SnoozeCommand extends Command {

    private final int index;
    private final int days;

    /**
     * Creates a SnoozeCommand with the given task index and number of days.
     *
     * @param index 1-based index of the task to snooze.
     * @param days Number of days to postpone the task by.
     */
    public SnoozeCommand(int index, int days) {
        this.index = index;
        this.days = days;
    }

    /**
     * Executes the snooze operation on the specified task.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui User interface used to display output.
     * @param storage Storage instance for saving data.
     * @return A confirmation message including the updated task.
     * @throws NekoException If the task index is invalid or the task
     *                       cannot be snoozed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws NekoException {
        tasks.snoozeTask(index, days);
        int zeroIndex = tasks.toZeroBasedIndex(index);
        Task task = tasks.getTask(zeroIndex);
        return "Nya! I have snoozed this task:\n  " + task;
    }
}
