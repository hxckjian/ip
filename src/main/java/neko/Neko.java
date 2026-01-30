package neko;

import neko.command.Command;

public class Neko {
    public enum InputType {
        DEADLINE, EVENT, TODO, LIST,
        MARK, UNMARK, DELETE, DEFAULT
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    /**
     * Runs main application loop.
     * Continuously run application loop by reading user commands, parses
     * them, and executes corresponding actions until exit command is given.
     *
     */
    public void run() {
        ui.showGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDividerLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NekoException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Executes main application logic.
     * Creates a new Neko instance and starts the application.
     *
     * @param args command line arguments for configuration
     */
    public static void main(String[] args) {
        new Neko("data/neko.txt").run();
    }
}
