package pt.xavier.tms.shared.enums;

/**
 * Operational status of a driver (Requisito 6.2).
 */
public enum DriverStatus {

    /** Driver is active and eligible for allocation. */
    ATIVO,

    /** Driver is inactive — allocation blocked (Requisito 9.5). */
    INATIVO,

    /** Driver is suspended — allocation blocked (Requisito 9.5). */
    SUSPENSO
}
