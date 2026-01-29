package command;

import java.TaskList;
import java.Ui;
import java.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws NekoException;

    public boolean isExit() {
        return false;
    }
}