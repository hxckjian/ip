package neko;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateParser {
    /**
     * Parses a text into a {@link LocalDate}.
     * The expected format is yyyy-mm-dd.
     *
     * @param text Date string to be parsed.
     * @return Parsed {@link LocalDate}.
     * @throws NekoException If the text is not in the appropriate format.
     */
    public static LocalDate parseTextIntoDate(String text) throws NekoException {
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException e) {
            throw new NekoException("What's that? Please input in yyyy-mm-dd format!");
        }
    }
}
