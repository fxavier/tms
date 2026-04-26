package pt.xavier.tms.shared.enums;

/**
 * Type of operation recorded in an audit log entry (Requisito 12.2).
 */
public enum AuditOperation {

    /** A new entity was created. */
    CRIACAO,

    /** An existing entity was updated. */
    ATUALIZACAO,

    /** An entity was logically deleted (soft delete). */
    ELIMINACAO
}
