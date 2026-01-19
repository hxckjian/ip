import java.util.Scanner;

public class Neko {
    public static void main(String[] args) {
        // Starting message
        String message = """
                ____________________________________________________________
                  /\\_/\\\s
                 ( o.o )  Hello! I'm Neko.
                  > ^ <   I'm here to listen — what can I do for you?
                ____________________________________________________________""";
        System.out.println(message);

        // Scans input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // String array of size 100
        String[] dataArray = new String[100];
        int currentIndex = 0;

        // Scans input until input is "bye"
        while (!input.equals("bye")) {

            if (input.equals("list")) {
                // List out data
                System.out.println("____________________________________________________________");
                for (int i = 1; i <= currentIndex; i++) {
                    System.out.println(i + ". " + dataArray[i - 1]);
                }
                System.out.println("____________________________________________________________");
            } else {
                // Echo message
                String echoMessage = "____________________________________________________________\n"
                        + " Neko added: "
                        + input
                        + "\n____________________________________________________________";
                System.out.println(echoMessage);

                // Add input into dataArray
                dataArray[currentIndex] = input;
                currentIndex++;
            }

            // Take next input
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
