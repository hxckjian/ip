package neko.command;

import neko.NekoException;
import neko.Storage;
import neko.TaskList;
import neko.Ui;

/**
 * Represents a command that shows a list based on a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NekoException {
        String result = tasks.find(keyword);

        if (result.isEmpty()) {
            return ui.showMessage(
                    "Nyaa… I couldn’t find any tasks matching \"" + keyword + "\"."
            );
        }
        return ui.showKeywordList(result);
    }
}
