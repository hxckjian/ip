package neko;

import command.Command;

public class Neko {
    public enum InputType {
        DEADLINE, EVENT, TODO, LIST,
        MARK, UNMARK, DELETE, DEFAULT
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
        //...
    }

    public static void main(String[] args) {
        new Neko("data/tasks.txt").run();
    }
}
