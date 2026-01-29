import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showGreetingMessage() {
        String message = """
                ____________________________________________________________
                  /\\_/\\
                 ( o.o )  Hello! I'm Neko.
                  > ^ <   I'm here to listen — what can I do for you?
                ____________________________________________________________""";
        System.out.println(message);
    }

    public void printEndMessage() {
        String endMessage = """
                 Bye! Neko is curling up for a nap now.
                 ⠀⠀⠀⠀⢀⡴⣄⠀⠀⠀⠀⢠⣄⠀⠀⠀⠀⠀⠀⠀⣼⣿⡟⠃
                 ⠀⠀⠀⣰⠋⠀⠈⠓⠒⠒⠒⠚⠈⢳⡄⠀⠀⠀⠀⠀⣿⣿
                 ⠀⠀⣼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣤⣤⣤⣤⣤⣿⣿⣄
                 ⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀⠀⠀⠀⠙⣷⡴⠶⣦
                 ⠀⠀⢷⡀⠀⠉⠉⠀⠀⠀⠉⠉⠀⠀⣠⡿⠀⠀⠀⢀⣀⣠⣤⠿⠞⠛⠋
                 ⣠⠾⠋⠙⣶⠤⠤⠤⠤⣤⡤⠤⠤⠞⣠⡴⠶⠚⠋⠉⠁
                 ⠛⠒⠛⠉⠉⠀⠀⠀⣴⠟⢃⡴⠛⠋⠉
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠋""";
        this.showDividerLine();
        System.out.println(endMessage);
        this.showDividerLine();
    }

    public void printListMessage(String list) {
        this.showDividerLine();
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
        this.showDividerLine();
    }

    public void printEchoMessage(String input, int length) {
        this.showDividerLine();
        String echoMessage = " Neko added this task:\n"
                + input
                + "\nNow you have " + length +
                " tasks in the list.";
        System.out.println(echoMessage);
        this.showDividerLine();
    }

    public void handleIncorrectStatement() {
        this.showDividerLine();
        System.out.println("""
                 I pawed at it, sniffed it, and… nope. (￣ω￣;)
                 I don’t know what that means.
                """);
        this.showDividerLine();
    }

    public void showMarkedTask(Task task) {
//        taskArr.get(index - 1).markDone();
        this.showDividerLine();
        System.out.println(" Nice! I've marked this task as done:");
//        System.out.println(taskArr.get(index - 1));
        System.out.println(task);
        this.showDividerLine();
    }

    public void showUnmarkedTask(Task task) {
//        taskArr.get(index - 1).markUnDone();
        this.showDividerLine();
        System.out.println(" OK, I've marked this task as not done yet:");
//        System.out.println(taskArr.get(index - 1));
        System.out.println(task);
        this.showDividerLine();
    }

    public void showDeletionOfTask(Task task, int size) {
        this.showDividerLine();
        System.out.println(" Roger nya! I have deleted this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
//        System.out.println(taskArr.get(index - 1));
//        taskArr.remove(index - 1);
//        System.out.println("Now you have " + taskArr.size() + " tasks in the list.");
        this.showDividerLine();
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public void showDividerLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
