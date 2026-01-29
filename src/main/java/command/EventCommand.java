package command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import task.Task;

public class EventCommand extends Command {
    private final Task event;

    public EventCommand(Task deadline) {
        this.event = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        tasks.addTask(this.event);
        ui.showAddMessage(this.event, tasks.getSize());
    }
}
