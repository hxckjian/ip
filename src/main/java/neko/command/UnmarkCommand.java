package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import neko.task.Task;

public class UnmarkCommand extends Command {
    private final int inputIndex;

    /**
     * Creates a command to unmark a task at the specified index.
     *
     * @param inputIndex One-based index of the task to unmark.
     */
    public UnmarkCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    /**
     * Executes the unmark command.
     * Marks the specified task as not done and displays a confirmation.
     *
     * @param tasks Task list to be modified or accessed.
     * @param ui User interface used to display output.
     * @param storage Storage instance for saving data.
     * @throws NekoException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        tasks.unmarkTask(this.inputIndex);
        Task unmarkedTask = tasks.getTask(this.inputIndex - 1);
        return ui.showUnmarkedTask(unmarkedTask);
    }
}
