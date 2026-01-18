import java.util.Scanner;

public class Neko {
    public static void main(String[] args) {
        String message = "____________________________________________________________\n"
                + " Hello! I'm Neko\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String echoMessage = "____________________________________________________________\n"
                    + " "
                    + input
                    + "\n____________________________________________________________";
            System.out.println(echoMessage);
            input = scanner.nextLine();
        }
        String endMessage = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(endMessage);
    }
}
