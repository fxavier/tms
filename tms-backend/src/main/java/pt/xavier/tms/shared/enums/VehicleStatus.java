package pt.xavier.tms.shared.enums;

/**
 * Operational status of a vehicle (Requisito 1.6).
 */
public enum VehicleStatus {

    /** Vehicle is available for allocation. */
    DISPONIVEL,

    /** Vehicle is temporarily unavailable (e.g. administrative hold). */
    INDISPONIVEL,

    /** Vehicle is currently undergoing maintenance — allocation blocked (Requisito 4.5). */
    EM_MANUTENCAO,

    /** Vehicle has been decommissioned — allocation permanently blocked (Requisito 1.7). */
    ABATIDA
}
