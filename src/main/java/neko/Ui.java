package neko;

import neko.task.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the greeting message when the application starts.
     */
    public String showGreetingMessage() {
        String message = """
                   /\\_/\\
                  ( o.o )  Hello! I'm Neko.
                   > ^ <   I'm here to listen — what can I do for you?
                   """;
        return message;
    }

    /**
     * Displays the farewell message when the application exits.
     */
    public String printEndMessage() {
        String endMessage = """
                 Bye! I'm curling up for a nap now.
                 ⠀⠀⠀⠀⢀⡴⣄⠀⠀⠀⠀⢠⣄⠀⠀⠀⠀⠀⠀⠀⣼⣿⡟⠃
                 ⠀⠀⠀⣰⠋⠀⠈⠓⠒⠒⠒⠚⠈⢳⡄⠀⠀⠀⠀⠀⣿⣿
                 ⠀⠀⣼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣤⣤⣤⣤⣤⣿⣿⣄
                 ⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀⠀⠀⠀⠙⣷⡴⠶⣦
                 ⠀⠀⢷⡀⠀⠉⠉⠀⠀⠀⠉⠉⠀⠀⣠⡿⠀⠀⠀⢀⣀⣠⣤⠿⠞⠛⠋
                 ⣠⠾⠋⠙⣶⠤⠤⠤⠤⣤⡤⠤⠤⠞⣠⡴⠶⠚⠋⠉⠁
                 ⠛⠒⠛⠉⠉⠀⠀⠀⣴⠟⢃⡴⠛⠋⠉
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠋
                 """;
//        this.showDividerLine();
        return endMessage;
//        this.showDividerLine();
    }

    /**
     * Prints the task list.
     *
     * @param list Formatted string representing the task list.
     * @throws NekoException If the task list is empty.
     */
    public String printListMessage(String list) throws NekoException {
//        this.showDividerLine();
        if (list.isEmpty()) {
            throw new NekoException("The list is empty!");
        } else {
            return "Here are the tasks in your list:\n "
                    + list;
        }
//        this.showDividerLine();
    }

    /**
     * Displays add task message.
     *
     * @param task Task that was added.
     * @param length Total number of tasks after addition.
     */
    public String showAddMessage(Task task, int length) {
//        this.showDividerLine();
        String echoMessage = " I have added this task:\n"
                + task
                + "\nNow you have " + length +
                " tasks in the list.";
        return echoMessage;
//        this.showDividerLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task Task that was marked as done.
     */
    public String showMarkedTask(Task task) {
//        this.showDividerLine();
//        System.out.println(" Nice! I've marked this task as done:");
//        System.out.println(task);
        return "Nice! I've marked this task as done:\n"
                + task;
//        this.showDividerLine();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task Task that was unmarked.
     */
    public String showUnmarkedTask(Task task) {
//        this.showDividerLine();
//        System.out.println(" OK, I've marked this task as not done yet:");
//        System.out.println(task);
        return "OK, I've marked this task as not done yet:\n"
                + task;
//        this.showDividerLine();
    }

    /**
     * Displays a message after a task is deleted.
     *
     * @param task Task that was deleted.
     * @param size Remaining number of tasks in the list.
     */
    public String showDeletionOfTask(Task task, int size) {
//        this.showDividerLine();
//        System.out.println(" Roger nya! I have deleted this task:");
//        System.out.println(task);
//        System.out.println("Now you have " + size + " tasks in the list.");
        return "Roger nya! I have deleted this task:\n"
                + task
                + "\nNow you have " + size + " tasks in the list.";
//        this.showDividerLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message Content of error message.
     */
    public String showError(String message) {
//        System.out.println("OOPS!!! " + message);
        return "OOPS!!! " + message;
    }

    /**
     * Displays a divider line to separate sections of output.
     */
//    public String showDividerLine() {
//        return "____________________________________________________________";
//    }

    /**
     * Reads a command entered by the user.
     *
     * @return User input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public String showKeywordList(String list) {
//        this.showDividerLine();
//        System.out.println("I found them! Here are the matching tasks in your list:");
//        System.out.println(list);
        return "I found them! Here are the matching tasks in your list:\n"
                + list;
//        this.showDividerLine();
    }
}
