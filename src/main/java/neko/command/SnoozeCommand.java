package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;
import neko.task.Task;

public class SnoozeCommand extends Command {

    private final int index;
    private final int days;

    public SnoozeCommand(int index, int days) {
        this.index = index;
        this.days = days;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws NekoException {
        tasks.snoozeTask(index, days);
        int zeroIndex = tasks.toZeroBasedIndex(index);
        Task task = tasks.getTask(zeroIndex);
        return "I have snoozed this task:\n  " + task;
    }
}