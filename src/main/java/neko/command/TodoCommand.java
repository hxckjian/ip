package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import neko.task.Task;

public class TodoCommand extends Command {
    private final Task todo;

    /**
     * Creates a command to add the specified todo task.
     *
     * @param todo Todo task to be added.
     */
    public TodoCommand(Task todo) {
        this.todo = todo;
    }

    /**
     * Executes the todo command.
     * Adds the todo task to the task list and displays a confirmation.
     *
     * @param tasks Task list to be modified or accessed.
     * @param ui User interface used to display output.
     * @param storage Storage instance for saving data.
     * @throws NekoException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        tasks.addTask(this.todo);
        return ui.showAddMessage(this.todo, tasks.getSize());
    }
}
