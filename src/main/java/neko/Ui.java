package neko;

import java.util.Scanner;

import neko.task.Task;

/**
 * Handles all user-facing messages and formatting for output.
 *
 * This class is responsible for generating messages shown to the user,
 * including feedback, prompts, and formatted task-related responses.
 */
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
        return endMessage;
    }

    /**
     * Prints the task list.
     *
     * @param list Formatted string representing the task list.
     * @throws NekoException If the task list is empty.
     */
    public String printListMessage(String list) throws NekoException {
        if (list.isEmpty()) {
            throw new NekoException("The list is empty!");
        } else {
            return "Here are the tasks in your list:\n "
                    + list;
        }
    }

    /**
     * Displays add task message.
     *
     * @param task Task that was added.
     * @param length Total number of tasks after addition.
     */
    public String showAddMessage(Task task, int length) {
        String echoMessage = " I have added this task:\n"
                + task
                + "\nNow you have "
                + length
                + " tasks in the list.";
        return echoMessage;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task Task that was marked as done.
     */
    public String showMarkedTask(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task Task that was unmarked.
     */
    public String showUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + task;
    }

    /**
     * Displays a message after a task is deleted.
     *
     * @param task Task that was deleted.
     * @param size Remaining number of tasks in the list.
     */
    public String showDeletionOfTask(Task task, int size) {
        return "Roger nya! I have deleted this task:\n"
                + task
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message Content of error message.
     */
    public String showError(String message) {
        return "OOPS!!! " + message;
    }

    /**
     * Reads a command entered by the user.
     *
     * @return User input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns a formatted message displaying tasks that match a search keyword.
     *
     * @param list Formatted list of matching tasks.
     * @return Message containing the matching tasks.
     */
    public String showKeywordList(String list) {
        return "I found them! Here are the matching tasks in your list:\n"
                + list;
    }
}
