package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import neko.task.Task;

public class TodoCommand extends Command {
    private final Task todo;

    public TodoCommand(Task deadline) {
        this.todo = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        tasks.addTask(this.todo);
        ui.showAddMessage(this.todo, tasks.getSize());
    }
}
