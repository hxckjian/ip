package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        ui.printListMessage(tasks.toString());
    }
}
