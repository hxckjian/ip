package command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        storage.write(tasks);
        ui.printEndMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
