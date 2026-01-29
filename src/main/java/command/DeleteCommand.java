package command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import task.Task;

public class DeleteCommand extends Command {
    public final int inputIndex;

    public DeleteCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        Task removedTask = tasks.getTask(inputIndex - 1);
        tasks.removeTask(inputIndex);
        ui.showDeletionOfTask(removedTask, tasks.getSize());
    }
}
