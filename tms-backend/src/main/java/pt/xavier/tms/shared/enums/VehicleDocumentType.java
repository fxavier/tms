package pt.xavier.tms.shared.enums;

/**
 * Types of documents that can be associated with a vehicle (Requisito 3.1).
 */
public enum VehicleDocumentType {

    /** Vehicle registration booklet (livrete). */
    LIVRETE,

    /** Periodic technical inspection (inspeção periódica). */
    INSPECAO,

    /** Vehicle insurance policy. */
    SEGURO,

    /** Circulation licence. */
    LICENCA,

    /** Cargo manifest. */
    MANIFESTO,

    /** Radio tax. */
    TAXA_RADIO,

    /** Any other document type configured by an administrator. */
    OUTRO
}
