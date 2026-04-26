package pt.xavier.tms.shared.util;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Utility methods for date and time operations used across the TMS modules.
 */
public final class DateUtils {

    private static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_LOCAL_DATE;

    private DateUtils() {
        // utility class — no instantiation
    }

    /**
     * Returns {@code true} if the given date is strictly before today (i.e. already expired).
     */
    public static boolean isExpired(LocalDate date) {
        return date != null && date.isBefore(LocalDate.now());
    }

    /**
     * Returns {@code true} if the given date falls within the next {@code daysAhead} days
     * (inclusive of today, exclusive of the boundary beyond {@code daysAhead}).
     */
    public static boolean isExpiringWithin(LocalDate date, int daysAhead) {
        if (date == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate threshold = today.plusDays(daysAhead);
        // not yet expired AND within the warning window
        return !date.isBefore(today) && !date.isAfter(threshold);
    }

    /**
     * Returns the current UTC timestamp.
     */
    public static OffsetDateTime nowUtc() {
        return OffsetDateTime.now(ZoneOffset.UTC);
    }

    /**
     * Formats a {@link LocalDate} as an ISO-8601 string ({@code yyyy-MM-dd}).
     */
    public static String format(LocalDate date) {
        return date == null ? null : date.format(ISO_DATE);
    }
}
