package pt.xavier.tms.shared.enums;

/**
 * Physical condition of a vehicle accessory (Requisito 2.3).
 */
public enum AccessoryStatus {

    /** Accessory is present and in good condition. */
    PRESENTE,

    /** Accessory is missing from the vehicle. */
    AUSENTE,

    /** Accessory is present but damaged. */
    DANIFICADO
}
