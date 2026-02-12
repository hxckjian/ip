package neko;

import java.time.LocalDate;

import neko.command.ByeCommand;
import neko.command.Command;
import neko.command.DeadlineCommand;
import neko.command.DeleteCommand;
import neko.command.EventCommand;
import neko.command.FindCommand;
import neko.command.ListCommand;
import neko.command.MarkCommand;
import neko.command.SnoozeCommand;
import neko.command.TodoCommand;
import neko.command.UnmarkCommand;
import neko.task.Deadline;
import neko.task.Event;
import neko.task.Task;
import neko.task.ToDo;

/**
 * Handles loading and saving of task data to persistent storage.
 */
public class Parser {
    private enum InputType {
        DEADLINE, EVENT, TODO, LIST,
        MARK, UNMARK, DELETE, BYE,
        FIND, SNOOZE
    }

    /**
     * Parses the full input from user and returns the corresponding command.
     *
     * @param input Full input string by the user.
     * @return A {@link Command} representing the corresponding command according to input.
     * @throws NekoException If the input is invalid or cannot be parsed.
     */
    public static Command parse(String input) throws NekoException {
        String[] tokens = tokenize(input);
        InputType type = resolveCommandType(tokens[0]);
        return dispatchCommand(type, tokens);
    }

    /**
     * Tokenizes User input.
     *
     * @param input User input
     * @return String array that was split by first space.
     */
    private static String[] tokenize(String input) {
        return input.trim().split(" ", 2);
    }

    /**
     * Returns the corresponding {@link InputType} for the given command keyword.
     *
     * @param keyword Command keyword entered by the user.
     * @return The matching {@link InputType}.
     * @throws NekoException If the keyword does not correspond to a valid command type.
     */
    private static InputType resolveCommandType(String keyword) throws NekoException {
        try {
            return InputType.valueOf(keyword.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw unknownCommandException();
        }
    }

    /**
     * Returns the parsed task index from the given tokenized input.
     *
     * @param tokens Tokenized user input where the second element is expected
     *               to contain the task index.
     * @return Parsed integer index of the task.
     * @throws NekoException If the index is missing or not a valid integer.
     */
    private static int parseIndex(String[] tokens) throws NekoException {
        if (tokens.length < 2) {
            throw new NekoException("Missing index.");
        }

        try {
            return Integer.parseInt(tokens[1].trim());
        } catch (NumberFormatException e) {
            throw new NekoException("Index must be a number.");
        }
    }

    /**
     * Returns the {@link Command} corresponding to the specified input type and tokens.
     *
     * @param type Resolved command type.
     * @param tokens Tokenized user input.
     * @return The constructed {@link Command}.
     * @throws NekoException If the command cannot be parsed or constructed.
     */
    private static Command dispatchCommand(InputType type, String[] tokens)
            throws NekoException {

        switch (type) {
        case TODO:
            return parseTodo(tokens);
        case DEADLINE:
            return parseDeadline(tokens);
        case EVENT:
            return parseEvent(tokens);
        case LIST:
            return parseList();
        case MARK:
            return parseMark(parseIndex(tokens));
        case UNMARK:
            return parseUnmark(parseIndex(tokens));
        case DELETE:
            return parseDelete(parseIndex(tokens));
        case BYE:
            return parseBye();
        case FIND:
            return parseFind(parseKeyword(tokens));
        case SNOOZE:
            return parseSnooze(tokens);
        default:
            throw unknownCommandException();
        }
    }

    /**
     * Returns the keyword extracted from the tokenized input.
     *
     * @param tokens Tokenized user input where the second element is expected
     *               to contain the keyword.
     * @return The trimmed keyword string.
     * @throws NekoException If the keyword is missing or empty.
     */
    private static String parseKeyword(String[] tokens) throws NekoException {
        if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
            throw new NekoException("Meow! Keyword cannot be empty!");
        }
        return tokens[1].trim();
    }

    /**
     * Returns a {@link NekoException} indicating an unknown command.
     *
     * @return A {@link NekoException} representing an unknown command error.
     */
    private static NekoException unknownCommandException() {
        return new NekoException("""
         I pawed at it, sniffed it, and… nope. (￣ω￣;)
         I don’t know what that means.
        """);
    }

    /**
     * Parses Todo command.
     *
     * @param content Tokenized user input.
     * @return A {@link TodoCommand} containing the parsed todo task.
     * @throws NekoException If the todo description is not given.
     */
    public static Command parseTodo(String[] content) throws NekoException {
        if (content.length == 1) {
            throw new NekoException("Oops! A todo's content can’t be empty, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        }
        Task todo = new ToDo(content[1].trim());
        return new TodoCommand(todo);
    }

    /**
     * Parses Deadline command.
     *
     * @param split Tokenized user input.
     * @return A {@link DeadlineCommand} containing the parsed deadline task.
     * @throws NekoException If the deadline format or date is invalid.
     */
    public static Command parseDeadline(String[] split) throws NekoException {
        if (split.length == 1) {
            throw new NekoException("Oops! The deadline's content can’t be empty,"
                    + " meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        }

        String[] afterCommandArray = split[1].trim().split(" ");
        int numBy = countNumDelimiter(afterCommandArray, "/by");

        if (numBy > 1) {
            throw new NekoException("Oops! There's more than one deadline,"
                    + " meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (numBy == 0) {
            throw new NekoException("Oops! There's no deadline, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (afterCommandArray[0].equals("/by")) {
            throw new NekoException("Oops! The deadline's content is not specified,"
                    + " meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (afterCommandArray[afterCommandArray.length - 1].equals("/by")) {
            throw new NekoException("Oops! The deadline's /by is not specified,"
                    + " meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
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

    /**
     * Parses Event command.
     *
     * @param split Tokenized user input.
     * @return A {@link EventCommand} containing the parsed deadline task.
     * @throws NekoException If the event format or date is invalid.
     */
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
            throw new NekoException("Oops! There's more than one /from, meow."
                    + " ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (indexFrom == -1) {
            throw new NekoException("Oops! There's no /from, meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (numTo > 1) {
            throw new NekoException("Oops! There's more than one /to,"
                    + " meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (indexTo == -1) {
            throw new NekoException("Oops! There's no /to, meow."
                    + " ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (afterCommandArray[0].equals("/from")) {
            throw new NekoException("Oops! The event's content is not specified, meow."
                    + " ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (afterCommandArray[afterCommandArray.length - 1].equals("/to")) {
            throw new NekoException("Oops! The event's /to content is not specified, meow."
                    + " ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        } else if (indexFrom > indexTo) {
            throw new NekoException("Oops! The deadline's /from is after /to,"
                    + " meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
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

    public static Command parseSnooze(String[] split) throws NekoException {
        if (split.length == 1) {
            throw new NekoException("Oops! The deadline's content can’t be empty,"
                    + " meow. ╮(ᵕ—ᴗ—)╭\nTell me what is it!\n");
        }

        String[] afterCommandArray = split[1].trim().split(" ");
        int index = Integer.parseInt(afterCommandArray[0]);
        int days = Integer.parseInt(afterCommandArray[1]);
        return new SnoozeCommand(index, days);
    }

    /**
     * Creates a list command.
     *
     * @return A {@link ListCommand}.
     */
    public static Command parseList() {
        return new ListCommand();
    }

    /**
     * Creates a mark command.
     *
     * @param inputIndex Inputted index of the task to be marked.
     * @return A {@link MarkCommand}.
     */
    public static Command parseMark(int inputIndex) {
        return new MarkCommand(inputIndex);
    }

    /**
     * Creates an unmark command.
     *
     * @param inputIndex Inputted index of the task to be unmarked.
     * @return A {@link UnmarkCommand}.
     */
    public static Command parseUnmark(int inputIndex) {
        return new UnmarkCommand(inputIndex);
    }

    /**
     * Creates a delete command.
     *
     * @param inputIndex Inputted index of the task to be deleted.
     * @return A {@link DeleteCommand}.
     */
    public static Command parseDelete(int inputIndex) {
        return new DeleteCommand(inputIndex);
    }

    /**
     * Creates a bye command.
     *
     * @return A {@link DeleteCommand}.
     */
    public static Command parseBye() {
        return new ByeCommand();
    }

    public static Command parseFind(String keyword) {
        return new FindCommand(keyword);
    }

    /**
     * Counts the number of occurrences of a delimiter in an array.
     *
     * @param array Array of tokens.
     * @param del Delimiter to count.
     * @return Number of times the delimiter appears.
     */
    public static int countNumDelimiter(String[] array, String del) {
        int num = 0;
        for (String s : array) {
            if (s.equals(del)) {
                num++;
            }
        }
        return num;
    }

    /**
     * Finds the first occurrence index of delimiter.
     *
     * @param array Array of tokens.
     * @param del Delimiter to find.
     * @return Index of the first delimiter, or -1 if not found.
     */
    public static int indexOfDelimiter(String[] array, String del) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(del)) {
                return i;
            }
        }
        return -1;
    }
}
