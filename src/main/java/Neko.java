import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
            } finally {
                ui.showDividerLine();
            }
        }
        //...
    }

    public static void main(String[] args) {

        new Neko("data/tasks.txt").run();
        // String array of size 100
        ArrayList<Task> arrList = new ArrayList<>();

        // Get data from file path
        try {
            setupData(arrList);
        } catch (NekoException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Starting message
        printGreetingMessage();

        // Scans input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Scans input until input is "bye"
        while (!input.equals("bye")) {
            String[] split = input.trim().split(" ", 2);
            String inputMessage = split[0].trim().toUpperCase();
            InputType inputType;

            // Check for invalid input
            try {
                inputType = InputType.valueOf(inputMessage);

                switch (inputType) {
                case DEADLINE:
                    // Add deadline
                    Task deadline = handleDeadline(split);

                    // Add into list
                    arrList.add(deadline);

                    // Echo Message
                    printEchoMessage(deadline.toString(), arrList.size());
                    break;
                case EVENT:
                    // Add event
                    Task event = handleEvent(split);

                    // Add into list
                    arrList.add(event);

                    // Echo Message
                    printEchoMessage(event.toString(), arrList.size());
                    break;
                case TODO:
                    // Add todo's task
                    Task todo = handleToDo(split);

                    // Add into list
                    arrList.add(todo);

                    // Echo Message
                    printEchoMessage(todo.toString(), arrList.size());
                    break;
                case LIST:
                    // List out data
                    String listOfData = generateListOfTasks(arrList);
                    printListMessage(listOfData);
                    break;
                case MARK:
                    int markIndex = Integer.parseInt(split[1]);
                    markSpecifiedTask(arrList, markIndex);
                    break;
                case UNMARK:
                    int unMarkIndex = Integer.parseInt(split[1]);
                    unMarkSpecifiedTask(arrList, unMarkIndex);
                    break;
                case DELETE:
                    int deleteIndex = Integer.parseInt(split[1]);
                    deleteTask(arrList, deleteIndex);
                }
            } catch (IllegalArgumentException e) {
                handleIncorrectStatement();
            } catch (NekoException e) {
                System.out.println(e.getMessage());
            }
            input = scanner.nextLine();
        }

        // End Message
        printEndMessage();

        // Save data
        try {
            saveData(arrList);
        } catch (NekoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printGreetingMessage() {
        String message = """
                ____________________________________________________________
                  /\\_/\\
                 ( o.o )  Hello! I'm Neko.
                  > ^ <   I'm here to listen — what can I do for you?
                ____________________________________________________________""";
        System.out.println(message);
    }

    public static void printListMessage(String list) {
        System.out.println("""
                ____________________________________________________________
                Here are the tasks in your list:""");
        System.out.println(list);
        System.out.println("____________________________________________________________");
    }

    public static String generateListOfTasks(ArrayList<Task> arrList) throws NekoException {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= arrList.size(); i++) {
            sb.append(i).append(". ").append(arrList.get(i - 1));
            if (i != (arrList.size())) {
                sb.append("\n");
            }
        }
        if (sb.isEmpty()) {
            throw new NekoException("List is empty!");
        }
        return sb.toString();
    }

    public static void printEndMessage() {
        String endMessage = """
                ____________________________________________________________
                 Bye! Neko is curling up for a nap now.
                 ⠀⠀⠀⠀⢀⡴⣄⠀⠀⠀⠀⢠⣄⠀⠀⠀⠀⠀⠀⠀⣼⣿⡟⠃
                 ⠀⠀⠀⣰⠋⠀⠈⠓⠒⠒⠒⠚⠈⢳⡄⠀⠀⠀⠀⠀⣿⣿
                 ⠀⠀⣼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣤⣤⣤⣤⣤⣿⣿⣄
                 ⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀⠀⠀⠀⠙⣷⡴⠶⣦
                 ⠀⠀⢷⡀⠀⠉⠉⠀⠀⠀⠉⠉⠀⠀⣠⡿⠀⠀⠀⢀⣀⣠⣤⠿⠞⠛⠋
                 ⣠⠾⠋⠙⣶⠤⠤⠤⠤⣤⡤⠤⠤⠞⣠⡴⠶⠚⠋⠉⠁
                 ⠛⠒⠛⠉⠉⠀⠀⠀⣴⠟⢃⡴⠛⠋⠉
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠋
                ____________________________________________________________""";
        System.out.println(endMessage);
    }

    public static void printEchoMessage(String input, int length) {
        String echoMessage = "____________________________________________________________\n"
                + " Neko added this task:\n"
                + input
                + "\nNow you have " + length +
                " tasks in the list.\n____________________________________________________________";
        System.out.println(echoMessage);
    }

    public static void handleIncorrectStatement() {
        System.out.println("""
                ____________________________________________________________
                 I pawed at it, sniffed it, and… nope. (￣ω￣;)
                 I don’t know what that means.
                ____________________________________________________________
                """);
    }

    public static Task handleToDo(String[] content) throws NekoException {
        if (content.length == 1) {
            throw new NekoException("Oops! A todo's content can’t be empty, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        }
        return new ToDo(content[1].trim());
    }

    public static Task handleEvent(String[] split) throws NekoException {
        if (split.length == 1) {
            throw new NekoException("Oops! The event's content can’t be empty, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        }
        String[] afterCommandArray = split[1].trim().split(" ");
        int numFrom = countNumDelimiter(afterCommandArray, "/from");
        int numTo = countNumDelimiter(afterCommandArray, "/to");

        int indexFrom = indexOfDelimiter(afterCommandArray, "/from");
        int indexTo = indexOfDelimiter(afterCommandArray, "/to");

        if (numFrom > 1) {
            throw new NekoException("Oops! There's more than one /from, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (indexFrom == -1) {
            throw new NekoException("Oops! There's no /from, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (numTo > 1) {
            throw new NekoException("Oops! There's more than one /to, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (indexTo == -1) {
            throw new NekoException("Oops! There's no /to, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (afterCommandArray[0].equals("/from")) {
            throw new NekoException("Oops! The event's content is not specified, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (afterCommandArray[afterCommandArray.length - 1].equals("/to")) {
            throw new NekoException("Oops! The deadline's /by is not specified, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (indexFrom > indexTo) {
            throw new NekoException("Oops! The deadline's /from is after /to, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        }
        String[] splitFrom = split[1].split("/from");
        String description = splitFrom[0].trim();
        String[] date = splitFrom[1].split("/to");

        String from = date[0].trim();
        String to = date[1].trim();

        LocalDate dateFrom = parseTextIntoDate(from);
        LocalDate dateTo = parseTextIntoDate(to);

        // Add event
        return new Event(description, dateFrom, dateTo);
    }

    public static Task handleDeadline(String[] split) throws NekoException {
        if (split.length == 1) {
            throw new NekoException("Oops! The deadline's content can’t be empty, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        }
        String[] afterCommandArray = split[1].trim().split(" ");
        int numBy = countNumDelimiter(afterCommandArray, "/by");
        if (numBy > 1) {
            throw new NekoException("Oops! There's more than one deadline, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (numBy == 0) {
            throw new NekoException("Oops! There's no deadline, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (afterCommandArray[0].equals("/by")) {
            throw new NekoException("Oops! The deadline's content is not specified, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (afterCommandArray[afterCommandArray.length - 1].equals("/by")) {
            throw new NekoException("Oops! The deadline's /by is not specified, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        }

        String[] afterCommandSplitBy = split[1].trim().split("/by");
        String description = afterCommandSplitBy[0].trim();
        String by = afterCommandSplitBy[1].trim();

        // format "by" data into LocalDate
        LocalDate date = parseTextIntoDate(by);

        // Add deadline
        return new Deadline(description, date);
    }

    public static LocalDate parseTextIntoDate(String text) throws NekoException {
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException e) {
            throw new NekoException("What's that? Please input in yyyy-mm-dd format!");
        }
    }

    public static int countNumDelimiter(String[] array, String del) {
        int num = 0;
        for (String s : array) {
            if (s.equals(del)) {
                num++;
            }
        }
        return num;
    }

    public static int indexOfDelimiter(String[] array, String del) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(del)) {
                return i;
            }
        }
        return -1;
    }

    public static void markSpecifiedTask(ArrayList<Task> taskArr, int index) {
        taskArr.get(index - 1).markDone();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(taskArr.get(index - 1));
        System.out.println("____________________________________________________________");
    }

    public static void unMarkSpecifiedTask(ArrayList<Task> taskArr, int index) {
        taskArr.get(index - 1).markUnDone();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(taskArr.get(index - 1));
        System.out.println("____________________________________________________________");
    }

    public static void deleteTask(ArrayList<Task> taskArr, int index) {
        System.out.println("____________________________________________________________");
        System.out.println(" Roger nya! I have deleted this task:");
        System.out.println(taskArr.get(index - 1));
        taskArr.remove(index - 1);
        System.out.println("Now you have " + taskArr.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void setupData(ArrayList<Task> taskArr) throws NekoException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")
                    + "/src/main/data/neko.txt"));
            String line = br.readLine();

            while (line != null) {
                String[] split = line.split(" \\| ");
                String type = split[0];
                String description = split[2];

                boolean isDone = split[1].equals("1");

                switch (type) {
                    case "T":
                        Task todo = new ToDo(description, isDone);
                        taskArr.add(todo);
                        break;
                    case "D":
                        String by = split[3];
                        LocalDate date = parseTextIntoDate(by);
                        Task deadline = new Deadline(description, date, isDone);
                        taskArr.add(deadline);
                        break;
                    case "E":
                        String from = split[3];
                        String to = split[4];
                        LocalDate dateFrom = parseTextIntoDate(from);
                        LocalDate dateTo = parseTextIntoDate(to);
                        Task event = new Event(description, dateFrom, dateTo, isDone);
                        taskArr.add(event);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new NekoException("Meow! I can't find neko.txt in my data folder!");
        } catch (IOException e) {
            throw new NekoException("An I/O error occurred nya!");
        }
    }

    public static void saveData(ArrayList<Task> taskArr) throws NekoException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")
                + "/src/main/data/neko.txt", false))) {
            for (Task task : taskArr) {
                writer.write(task.formatIntoData());
                writer.newLine();
            }
        }
        catch (IOException e) {
            throw new NekoException("I tried to write to the file but the file ran away…");
        }
    }
}
