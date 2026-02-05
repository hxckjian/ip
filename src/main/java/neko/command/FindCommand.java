package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        return ui.showKeywordList(tasks.find(this.keyword));
    }
}
