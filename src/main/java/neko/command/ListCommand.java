package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;

/**
 * Represents a command that shows a list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     * Displays the current tasks in the task list.
     *
     * @param tasks Task list to be modified or accessed.
     * @param ui User interface used to display output.
     * @param storage Storage instance for saving data.
     * @throws NekoException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        return ui.printListMessage(tasks.toString());
    }
}
