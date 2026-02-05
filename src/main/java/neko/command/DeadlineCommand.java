package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import neko.task.Task;

/**
 * Represents a command that adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private final Task deadline;

    /**
     * Creates a command to add the specified deadline task.
     *
     * @param deadline Deadline task to be added.
     */
    public DeadlineCommand(Task deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes the deadline command.
     * Adds the deadline task to the task list and displays a confirmation.
     *
     * @param tasks Task list to be modified or accessed.
     * @param ui User interface used to display output.
     * @param storage Storage instance for saving data.
     * @throws NekoException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        tasks.addTask(this.deadline);
        return ui.showAddMessage(this.deadline, tasks.getSize());
    }
}
