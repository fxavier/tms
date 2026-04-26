package pt.xavier.tms.shared.exception;

import java.util.Collections;
import java.util.List;

/**
 * Thrown when an activity allocation fails due to one or more blockers.
 * Maps to HTTP 422 Unprocessable Entity.
 *
 * <p>Carries the full list of blocking reasons so the caller can present
 * a consolidated summary to the user (Requisito 9.8).</p>
 */
public class AllocationException extends RuntimeException {

    private final List<AllocationBlocker> blockers;

    public AllocationException(List<AllocationBlocker> blockers) {
        super("Allocation blocked: " + blockers.size() + " blocker(s) found");
        this.blockers = Collections.unmodifiableList(blockers);
    }

    public AllocationException(String code, String message) {
        super(message);
        this.blockers = List.of(new AllocationBlocker(code, message));
    }

    public List<AllocationBlocker> getBlockers() {
        return blockers;
    }

    /**
     * A single reason that prevented the allocation.
     *
     * @param code    machine-readable blocker code (e.g. {@code VEHICLE_IN_MAINTENANCE})
     * @param message human-readable description
     */
    public record AllocationBlocker(String code, String message) {}
}
