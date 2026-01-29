public class Parser {
    private enum InputType {
        DEADLINE, EVENT, TODO, LIST,
        MARK, UNMARK, DELETE, DEFAULT
    }

    public static Command parse(String input) throws NekoException {
        String[] split = input.trim().split(" ", 2);
        String inputMessage = split[0].trim().toUpperCase();
        Neko.InputType inputType;

        // Check for invalid input
        try {
            inputType = Neko.InputType.valueOf(inputMessage);

            switch (inputType) {
            case DEADLINE:
                // Add deadline
                Task deadline = handleDeadline(split);

                // Add into list
                arrList.add(deadline);

                // Echo Message
                printEchoMessage(deadline.toString(), arrList.size());
                break;
            case EVENT:
                // Add event
                Task event = handleEvent(split);

                // Add into list
                arrList.add(event);

                // Echo Message
                printEchoMessage(event.toString(), arrList.size());
                break;
            case TODO:
                // Add todo's task
                Task todo = handleToDo(split);

                // Add into list
                arrList.add(todo);

                // Echo Message
                printEchoMessage(todo.toString(), arrList.size());
                break;
            case LIST:
                // List out data
                String listOfData = generateListOfTasks(arrList);
                printListMessage(listOfData);
                break;
            case MARK:
                int markIndex = Integer.parseInt(split[1]);
                markSpecifiedTask(arrList, markIndex);
                break;
            case UNMARK:
                int unMarkIndex = Integer.parseInt(split[1]);
                unMarkSpecifiedTask(arrList, unMarkIndex);
                break;
            case DELETE:
                int deleteIndex = Integer.parseInt(split[1]);
                deleteTask(arrList, deleteIndex);
            }
        } catch (IllegalArgumentException e) {
            handleIncorrectStatement();
        } catch (NekoException e) {
            System.out.println(e.getMessage());
        }
    }
}
