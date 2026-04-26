package pt.xavier.tms.shared.enums;

/**
 * Status of a vehicle or driver document (Requisitos 3.4, 6.5).
 */
public enum DocumentStatus {

    /** Document is current and valid. */
    VALIDO,

    /** Document has passed its expiry date — allocation blocked (Requisitos 3.8, 6.8). */
    EXPIRADO,

    /** Document is approaching expiry and renewal has been initiated. */
    PENDENTE_RENOVACAO,

    /** Document has been cancelled and is no longer valid. */
    CANCELADO
}
