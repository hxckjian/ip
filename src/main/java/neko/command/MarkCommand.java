package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import neko.task.Task;

/**
 * Represents a command that marks a task.
 */
public class MarkCommand extends Command {
    private final int inputIndex;

    /**
     * Creates a command to mark a task at the specified index.
     *
     * @param inputIndex One-based index of the task to mark.
     */
    public MarkCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    /**
     * Executes the mark command.
     * Marks the specified task as done and displays a confirmation.
     *
     * @param tasks Task list to be modified or accessed.
     * @param ui User interface used to display output.
     * @param storage Storage instance for saving data.
     * @throws NekoException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        tasks.markTask(this.inputIndex);
        Task markedTask = tasks.getTask(this.inputIndex - 1);
        return ui.showMarkedTask(markedTask);
    }
}
