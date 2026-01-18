import java.util.Scanner;

public class Neko {
    public static void main(String[] args) {
        // Starting message
        String message = """
                ____________________________________________________________
                 Hello! I'm Neko
                 What can I do for you?
                ____________________________________________________________""";
        System.out.println(message);

        // Scans input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Scans input until input is "bye"
        while (!input.equals("bye")) {
            String echoMessage = "____________________________________________________________\n"
                    + " "
                    + input
                    + "\n____________________________________________________________";
            System.out.println(echoMessage);
            input = scanner.nextLine();
        }

        // End Message
        String endMessage = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(endMessage);
    }
}
