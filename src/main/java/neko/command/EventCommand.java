package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import neko.task.Task;

/**
 * Represents a command that adds a event task to the task list.
 */
public class EventCommand extends Command {
    private final Task event;

    /**
     * Creates a command to add the specified event task.
     *
     * @param event Event task to be added.
     */
    public EventCommand(Task event) {
        this.event = event;
    }

    /**
     * Executes the event command.
     * Adds the event task to the task list and displays a confirmation.
     *
     * @param tasks Task list to be modified or accessed.
     * @param ui User interface used to display output.
     * @param storage Storage instance for saving data.
     * @throws NekoException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        tasks.addTask(this.event);
        return ui.showAddMessage(this.event, tasks.getSize());
    }
}
