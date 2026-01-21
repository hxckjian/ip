import java.util.Scanner;

public class Neko {
    public enum InputType {
        DEADLINE, EVENT, TODO, LIST,
        MARK, UNMARK, DEFAULT
    }

    public static void main(String[] args) throws NekoException {
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
            String[] split = input.trim().split(" ", 2);
            String inputMessage = split[0].trim().toUpperCase();
            InputType inputType;

            // Check for invalid input
            try {
                inputType = InputType.valueOf(inputMessage);

                switch (inputType) {
                    case DEADLINE: {
                        // Add deadline
                        Task deadline = handleDeadline(split);

                        // Add into list
                        dataArray[currentIndex++] = deadline;

                        // Echo Message
                        printEchoMessage(deadline.toString(), currentIndex);
                        break;
                    }
                    case EVENT: {
                        // Add event
                        Task event = handleEvent(split);

                        // Add into list
                        dataArray[currentIndex++] = event;

                        // Echo Message
                        printEchoMessage(event.toString(), currentIndex);
                        break;
                    }
                    case TODO: {
                        // Add todo
                        Task todo = handleToDo(split);

                        // Add into list
                        dataArray[currentIndex++] = todo;

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
                }
            } catch (IllegalArgumentException e) {
//                inputType = InputType.DEFAULT;
                handleIncorrectStatement();
            } catch (NekoException e) {
                System.out.println(e.getMessage());
            }
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

    public static String generateListOfTasks(Task[] tasks, int currentIndex) throws NekoException {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= currentIndex; i++) {
            sb.append(i).append(". ").append(tasks[i - 1]);
            if (i != currentIndex) {
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

    public static void printEchoMessage(String input, int currentIndex) {
        String echoMessage = "____________________________________________________________\n"
                + " Neko added this task:\n"
                + input
                + "\nNow you have " + currentIndex +
                " tasks in the list.\n____________________________________________________________";
        System.out.println(echoMessage);
    }

    public static void handleIncorrectStatement() throws NekoException {
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
        String by = date[1].trim();

        // Add event
        return new Event(description, from, by);
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

        // Add deadline
        return new Deadline(description, by);
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
}
