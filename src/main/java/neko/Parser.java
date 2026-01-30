package neko;

import neko.task.Deadline;
import neko.task.Event;
import neko.task.Task;

import java.time.LocalDate;

import neko.command.*;
import neko.task.ToDo;

public class Parser {
    private enum InputType {
        DEADLINE, EVENT, TODO, LIST,
        MARK, UNMARK, DELETE, BYE,
        FIND
    }

    public static Command parse(String input) throws NekoException {
        String[] split = input.trim().split(" ", 2);
        String inputMessage = split[0].trim().toUpperCase();
        InputType inputType;

        // Check for invalid input
        try {
            inputType = InputType.valueOf(inputMessage);

            switch (inputType) {
            case TODO:
//                // Add todo's neko.task
//                Task todo = handleToDo(split);
//
//                // Add into list
//                arrList.add(todo);
//
//                // Echo Message
//                printEchoMessage(todo.toString(), arrList.size());
                return parseTodo(split);
            case DEADLINE:
//                // Add deadline
//                Task deadline = handleDeadline(split);
//
//                // Add into list
//                arrList.add(deadline);
//
//                // Echo Message
//                printEchoMessage(deadline.toString(), arrList.size());
                return parseDeadline(split);
            case EVENT:
//                // Add event
//                Task event = handleEvent(split);
//
//                // Add into list
//                arrList.add(event);
//
//                // Echo Message
//                printEchoMessage(event.toString(), arrList.size());
                return parseEvent(split);
            case LIST:
                // List out data
//                String listOfData = generateListOfTasks(arrList);
//                printListMessage(listOfData);
                return parseList();
            case MARK:
//                int markIndex = Integer.parseInt(split[1]);
//                markSpecifiedTask(arrList, markIndex);
                int markIndex = Integer.parseInt(split[1]);
                return parseMark(markIndex);
            case UNMARK:
//                int unMarkIndex = Integer.parseInt(split[1]);
//                unMarkSpecifiedTask(arrList, unMarkIndex);
                int unMarkIndex = Integer.parseInt(split[1]);
                return parseUnmark(unMarkIndex);
            case DELETE:
//                int deleteIndex = Integer.parseInt(split[1]);
//                deleteTask(arrList, deleteIndex);
                int deleteIndex = Integer.parseInt(split[1]);
                return parseDelete(deleteIndex);
            case BYE:
                return parseBye();
            case FIND:
                return parseFind(split[1]);
            }
        } catch (IllegalArgumentException e) {
            throw new NekoException("""
                 I pawed at it, sniffed it, and… nope. (￣ω￣;)
                 I don’t know what that means.
                """);
        }
        return null;
    }

    public static Command parseTodo(String[] content) throws NekoException {
        if (content.length == 1) {
            throw new NekoException("Oops! A todo's content can’t be empty, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        }
        Task todo = new ToDo(content[1].trim());
        return new TodoCommand(todo);
    }

    public static Command parseDeadline(String[] split) throws NekoException {
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

        // format "by" data into LocalDate
        LocalDate date = DateParser.parseTextIntoDate(by);

        // Add deadline
        Task deadline = new Deadline(description, date);

        return new DeadlineCommand(deadline);
    }

    public static Command parseEvent(String[] split) throws NekoException {
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
            throw new NekoException("Oops! The event's /to content is not specified, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (indexFrom > indexTo) {
            throw new NekoException("Oops! The deadline's /from is after /to, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        }
        String[] splitFrom = split[1].split("/from");
        String description = splitFrom[0].trim();
        String[] date = splitFrom[1].split("/to");

        String from = date[0].trim();
        String to = date[1].trim();

        LocalDate dateFrom = DateParser.parseTextIntoDate(from);
        LocalDate dateTo = DateParser.parseTextIntoDate(to);

        // Add event
        Task event = new Event(description, dateFrom, dateTo);

        return new EventCommand(event);
    }

    public static Command parseList() {
        return new ListCommand();
    }

    public static Command parseMark(int inputIndex) {
        return new MarkCommand(inputIndex);
    }

    public static Command parseUnmark(int inputIndex) {
        return new UnmarkCommand(inputIndex);
    }

    public static Command parseDelete(int inputIndex) {
        return new DeleteCommand(inputIndex);
    }

    public static Command parseBye() {
        return new ByeCommand();
    }

    public static Command parseFind(String keyword) {
        return new FindCommand(keyword);
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
