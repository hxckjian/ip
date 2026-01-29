package command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import task.Task;

public class MarkCommand extends Command {
    private final int inputIndex;

    public MarkCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        tasks.markTask(this.inputIndex);
        Task markedTask = tasks.getTask(this.inputIndex - 1);
        ui.showMarkedTask(markedTask);
    }
}
