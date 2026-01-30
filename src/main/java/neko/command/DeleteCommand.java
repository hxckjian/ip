package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import neko.task.Task;

public class DeleteCommand extends Command {
    public final int inputIndex;

    /**
     * Creates a command to delete a task at the specified index.
     *
     * @param inputIndex One-based index of the task to delete.
     */
    public DeleteCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    /**
     * Executes the delete command.
     * Removes the specified task from the task list and displays a confirmation.
     *
     * @param tasks Task list to be modified or accessed.
     * @param ui User interface used to display output.
     * @param storage Storage instance for saving data.
     * @throws NekoException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        Task removedTask = tasks.getTask(inputIndex - 1);
        tasks.removeTask(inputIndex);
        ui.showDeletionOfTask(removedTask, tasks.getSize());
    }
}
