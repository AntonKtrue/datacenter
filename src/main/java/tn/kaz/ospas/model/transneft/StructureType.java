package tn.kaz.ospas.model.transneft;



/**
 * Created by Anton on 13.01.2017.
 */
public enum StructureType {
    AK,
    OST,
    UMN,
    NPS,
    OBJ;

    public static StructureType getTypeByOrdinal(final int ord) {
        StructureType result = null;
        for (StructureType viewType : values()) {
            if (viewType.ordinal() == ord) {
                result = viewType;
                break;
            }
        }
        return result;
    }
}
