package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks Task list to be modified or accessed.
     * @param ui User interface used to display output.
     * @param storage Storage instance for saving data.
     * @throws NekoException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws NekoException;

    /**
     * Indicates whether this command should terminate the application.
     *
     * @return {@code true} if the command exits the application,
     *         {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}