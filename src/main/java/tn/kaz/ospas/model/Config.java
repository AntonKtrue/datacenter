package tn.kaz.ospas.model;

/**
 * Created by Anton on 13.01.2017.
 */
public class Config {
    public static final String JPA_UNIT = "appVaadinUnit";
    public static final String PARENT_FIELD = "parent";
    public static final String DOC_DIR = "E:/TEMP/";
    public static final Double TWIPS_IN_CM = 566.929133858;
    public static Long cmToTwips(double cm) {
        return new Double(cm * TWIPS_IN_CM).longValue();
    }

}
