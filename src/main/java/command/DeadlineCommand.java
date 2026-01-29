package command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import task.Task;

public class DeadlineCommand extends Command {
    private final Task deadline;

    public DeadlineCommand(Task deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        tasks.addTask(this.deadline);
        ui.showAddMessage(this.deadline, tasks.getSize());
    }
}
