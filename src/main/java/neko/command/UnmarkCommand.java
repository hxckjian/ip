package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import neko.task.Task;

public class UnmarkCommand extends Command {
    private final int inputIndex;

    public UnmarkCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        tasks.unmarkTask(this.inputIndex);
        Task unmarkedTask = tasks.getTask(this.inputIndex - 1);
        ui.showUnmarkedTask(unmarkedTask);
    }
}
