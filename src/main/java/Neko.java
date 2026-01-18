import java.util.Scanner;

public class Neko {
    public static void main(String[] args) {
        // Starting message
        String message = """
                ____________________________________________________________
Ad                  /\\_/\\\s
                 ( o.o )  Hello! I'm Neko.
                  > ^ <   I'm here to listen — what can I do for you?
                ____________________________________________________________""";
        System.out.println(message);

        // Scans input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Scans input until input is "bye"
        while (!input.equals("bye")) {
            String echoMessage = "____________________________________________________________\n"
                    + " Neko heard: "
                    + input
                    + "\n____________________________________________________________";
            System.out.println(echoMessage);
            input = scanner.nextLine();
        }

        // End Message
        String endMessage = """
                ____________________________________________________________
                 Bye! Neko is curling up for a nap now.
                 ⠀⠀⠀⠀⢀⡴⣄⠀⠀⠀⠀⢠⣄⠀⠀⠀⠀⠀⠀⠀⣼⣿⡟⠃⠀⠀⠀                                                                                                                                                                                       \s
                 ⠀⠀⠀⣰⠋⠀⠈⠓⠒⠒⠒⠚⠈⢳⡄⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀                                                                                                                                                                                       \s
                 ⠀⠀⣼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣤⣤⣤⣤⣤⣿⣿⣄⠀⠀⠀⠀                                                                                                                                                                                       \s
                 ⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀⠀⠀⠀⠙⣷⡴⠶⣦                                                                                                                                                                                       \s
                 ⠀⠀⢷⡀⠀⠉⠉⠀⠀⠀⠉⠉⠀⠀⣠⡿⠀⠀⠀⢀⣀⣠⣤⠿⠞⠛⠋                                                                                                                                                                                       \s
                 ⣠⠾⠋⠙⣶⠤⠤⠤⠤⣤⡤⠤⠤⠞⣠⡴⠶⠚⠋⠉⠁⠀⠀⠀⠀⠀⠀                                                                                                                                                                                       \s
                 ⠛⠒⠛⠉⠉⠀⠀⠀⣴⠟⢃⡴⠛⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                                                                                                                                                                       \s
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠋⠀⠀
                ____________________________________________________________""";
        System.out.println(endMessage);
    }
}
