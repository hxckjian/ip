package neko;

import neko.command.Command;

public class Neko {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Creates a new Neko application instance.
     * Loads existing tasks from the specified file path if available.
     *
     * @param filePath File path used to load and save task data.
     */
    public Neko(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NekoException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String getGreeting() {
        return ui.showGreetingMessage();
    }

    /**
     * Runs main application loop.
     * Continuously run application loop by reading user commands, parses
     * them, and executes corresponding actions until exit command is given.
     *
     */
    public String run(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            String outputMessage = c.execute(tasks, ui, storage);
            this.isExit = c.isExit();
            return outputMessage;
        } catch (NekoException e) {
            return ui.showError(e.getMessage());
        }
    }

    public boolean hasExited() {
        return this.isExit;
    }
}
