package neko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class DateParserTest {
    @Test
    public void parseTextIntoDate_validDate_returnsLocalDate() throws NekoException {
        LocalDate date = DateParser.parseTextIntoDate("2026-01-29");
        assertEquals(LocalDate.of(2026, 1, 29), date);
    }

    @Test
    public void parseTextIntoDate_invalidDate_throwsNekoException() {
        assertThrows(NekoException.class, () -> {
            DateParser.parseTextIntoDate("29-01-2026");
        });
    }
}
