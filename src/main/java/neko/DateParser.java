package neko;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateParser {
    public static LocalDate parseTextIntoDate(String text) throws NekoException {
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException e) {
            throw new NekoException("What's that? Please input in yyyy-mm-dd format!");
        }
    }
}
