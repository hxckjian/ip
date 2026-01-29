package neko.command;

import neko.NekoException;
import neko.TaskList;
import neko.Ui;
import neko.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NekoException;

    public boolean isExit() {
        return false;
    }
}