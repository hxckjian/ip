package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;

/**
 * Represents a command that terminates the application.
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command.
     * Saves tasks to storage and displays the farewell message.
     *
     * @param tasks Task list to be modified or accessed.
     * @param ui User interface used to display output.
     * @param storage Storage instance for saving data.
     * @throws NekoException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        storage.write(tasks);
        return ui.printEndMessage();
    }

    /**
     * Indicates that this command exits the application.
     *
     * @return {@code true} to signal application termination.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
