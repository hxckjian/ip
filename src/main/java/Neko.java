import java.util.Scanner;

public class Neko {
    public enum InputType {
        DEADLINE, EVENT, TODO, LIST,
        MARK, UNMARK, DEFAULT
    }

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
            String[] split = input.split(" ", 2);
            String inputMessage = split[0].trim().toUpperCase();
            InputType inputType;
            try {
                inputType = InputType.valueOf(inputMessage);
            } catch (IllegalArgumentException e) {
                inputType = InputType.DEFAULT;
            }

            switch (inputType) {
                case DEADLINE: {
                    String[] content = split[1].split("/by");
                    String description = content[0].trim();
                    String by = content[1].trim();

                    // Add deadline
                    Task deadline = new Deadline(description, by);
                    dataArray[currentIndex] = deadline;
                    currentIndex++;

                    // Echo Message
                    printEchoMessage(deadline.toString(), currentIndex);
                    break;
                }
                case EVENT: {
                    String[] content = split[1].split("/from");
                    String description = content[0].trim();
                    String[] date = content[1].split("/to");
                    String from = date[0].trim();
                    String by = date[1].trim();

                    // Add deadline
                    Task event = new Event(description, from, by);
                    dataArray[currentIndex] = event;
                    currentIndex++;

                    // Echo Message
                    printEchoMessage(event.toString(), currentIndex);
                    break;
                }
                case TODO: {
                    String description = split[1].trim();

                    // Add deadline
                    Task todo = new ToDo(description);
                    dataArray[currentIndex] = todo;
                    currentIndex++;

                    // Echo Message
                    printEchoMessage(todo.toString(), currentIndex);
                    break;
                }
                case LIST: {
                    // List out data
                    String listOfData = generateListOfTasks(dataArray, currentIndex);
                    printListMessage(listOfData);
                    break;
                }
                case MARK: {
                    System.out.println("____________________________________________________________");
                    int markIndex = Integer.parseInt(split[1]);
                    dataArray[markIndex - 1].markDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(dataArray[markIndex - 1]);
                    System.out.println("____________________________________________________________");
                    break;
                }
                case UNMARK: {
                    System.out.println("____________________________________________________________");
                    int unMarkIndex = Integer.parseInt(split[1]);
                    dataArray[unMarkIndex - 1].markUnDone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println(dataArray[unMarkIndex - 1]);
                    System.out.println("____________________________________________________________");
                    break;
                }
                default: {
                    // Echo Message
                    printEchoMessage(input, currentIndex + 1);

                    // Add input into dataArray
                    Task task = new Task(input);
                    dataArray[currentIndex] = task;
                    currentIndex++;
                    break;
                }
            }

//            if (split.length >= 2) {
//                // Assume second element is Integer first on Level-3
//                int index = Integer.parseInt(split[1]);
//                System.out.println("____________________________________________________________");
//                if (split[0].equals("mark")) {
//                    dataArray[index - 1].markDone();
//                    System.out.println(" Nice! I've marked this task as done:");
//                } else if (split[0].equals("unmark")) {
//                    dataArray[index - 1].markUnDone();
//                    System.out.println(" OK, I've marked this task as not done yet:");
//                }
//                System.out.println(dataArray[index - 1]);
//                System.out.println("____________________________________________________________");
//            } else if (input.equals("list")) {
//                // List out data
//                String listOfData = generateListOfTasks(dataArray, currentIndex);
//                printListMessage(listOfData);
//            } else {
//                // Echo message
//                printEchoMessage(input);
//
//                // Add input into dataArray
//                Task task = new Task(input);
//                dataArray[currentIndex] = task;
//                currentIndex++;
//            }

            // Take next input
            input = scanner.nextLine();
        }

        // End Message
        printEndMessage();
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

    public static String generateListOfTasks(Task[] tasks, int currentIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= currentIndex; i++) {
            sb.append(i).append(". ").append(tasks[i - 1]);
            if (i != currentIndex) {
                sb.append("\n");
            }
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

    public static void printEchoMessage(String input, int currentIndex) {
        String echoMessage = "____________________________________________________________\n"
                + " Neko added this task:\n"
                + input
                + "\nNow you have " + currentIndex +
                " tasks in the list.\n____________________________________________________________";
        System.out.println(echoMessage);
    }

}
