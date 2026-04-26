package pt.xavier.tms.shared.enums;

/**
 * Severity levels for system-generated alerts (Requisito 11.5).
 */
public enum AlertSeverity {

    /** Informational — no immediate action required. */
    INFO,

    /** Warning — action should be taken soon. */
    AVISO,

    /** Critical — immediate action required; triggers email notification (Requisito 11.10). */
    CRITICO
}
