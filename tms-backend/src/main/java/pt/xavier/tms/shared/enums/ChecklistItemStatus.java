package pt.xavier.tms.shared.enums;

/**
 * Status of a single checklist inspection item (Requisito 5.3).
 */
public enum ChecklistItemStatus {

    /** Item is present and in good condition. */
    OK,

    /** Item is present but damaged or malfunctioning. */
    AVARIA,

    /** Item is missing. */
    FALTA
}
