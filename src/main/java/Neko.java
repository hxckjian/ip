import java.util.Scanner;

public class Neko {
    public static void main(String[] args) {
        // Starting message
        printGreetingMessage();

        // Scans input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // String array of size 100
        Task[] dataArray = new Task[100];
        int currentIndex = 0;

        // Scans input until input is "bye"
        while (!input.equals("bye")) {
            String[] split = input.split(" ");

            if (split.length == 2) {
                // Assume second element is Integer first on Level-3
                int index = Integer.parseInt(split[1]);
                System.out.println("____________________________________________________________");
                if (split[0].equals("mark")) {
                    dataArray[index - 1].markDone();
                    System.out.println(" Nice! I've marked this task as done:");
                } else if (split[0].equals("unmark")) {
                    dataArray[index - 1].markUnDone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                }
                System.out.println("   [" + dataArray[index - 1].getStatusIcon()
                        +  "] " + dataArray[index - 1].getDescription());
                System.out.println("____________________________________________________________");
            } else if (input.equals("list")) {
                // List out data
                System.out.println("""
                        ____________________________________________________________\n
                        Here are the tasks in your list:""");
                for (int i = 1; i <= currentIndex; i++) {
                    System.out.println(i + ". [" + dataArray[i - 1].getStatusIcon()
                            +  "] " + dataArray[i - 1].getDescription());
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
                Task task = new Task(input);
                dataArray[currentIndex] = task;
                currentIndex++;
            }

            // Take next input
            input = scanner.nextLine();
        }

        // End Message
        printEndMessage();
    }

    public static void printGreetingMessage() {
        String message = """
                ____________________________________________________________
                  /\\_/\\\s
                 ( o.o )  Hello! I'm Neko.
                  > ^ <   I'm here to listen — what can I do for you?
                ____________________________________________________________""";
        System.out.println(message);
    }

    public static void printEndMessage() {
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
