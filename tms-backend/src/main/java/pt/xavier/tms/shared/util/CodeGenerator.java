package pt.xavier.tms.shared.util;

import java.time.Year;

/**
 * Utility for generating human-readable sequential codes used across the TMS.
 *
 * <p>Activity codes follow the format {@code ACT-{YEAR}-{SEQUENCE:04d}}
 * as specified in Requisito 8.6. The actual sequence counter is managed by
 * {@code ActivityCodeGenerator} inside the {@code activity} module, which
 * queries the database within a transaction to avoid duplicates under
 * concurrent load. This class provides only the formatting logic.</p>
 */
public final class CodeGenerator {

    private CodeGenerator() {
        // utility class — no instantiation
    }

    /**
     * Formats an activity code from the current year and a sequence number.
     *
     * <p>Example: {@code formatActivityCode(42)} → {@code "ACT-2025-0042"}</p>
     *
     * @param sequence 1-based sequential number for the current year
     * @return formatted activity code
     */
    public static String formatActivityCode(long sequence) {
        return formatActivityCode(Year.now().getValue(), sequence);
    }

    /**
     * Formats an activity code from an explicit year and a sequence number.
     * Useful for testing with a fixed year.
     *
     * @param year     the four-digit year
     * @param sequence 1-based sequential number
     * @return formatted activity code, e.g. {@code "ACT-2025-0042"}
     */
    public static String formatActivityCode(int year, long sequence) {
        return String.format("ACT-%d-%04d", year, sequence);
    }

    /**
     * Returns the code prefix used to count existing activities for the given year.
     * Example: {@code "ACT-2025-"}
     */
    public static String activityCodePrefix(int year) {
        return String.format("ACT-%d-", year);
    }

    /**
     * Returns the code prefix for the current year.
     */
    public static String activityCodePrefix() {
        return activityCodePrefix(Year.now().getValue());
    }
}
