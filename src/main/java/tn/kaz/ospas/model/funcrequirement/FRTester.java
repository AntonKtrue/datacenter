package tn.kaz.ospas.model.funcrequirement;

import com.vaadin.addon.jpacontainer.JPAContainer;
import tn.kaz.ospas.data.SimpleJPAContainer;

/**
 * Created by Anton on 19.01.2017.
 */
public class FRTester {
    public static void main(String[] args) {
        JPAContainer<FuncRequirement> fr = new SimpleJPAContainer<FuncRequirement>(FuncRequirement.class);
        java.util.Collection<Object> c = fr.getItemIds();
        for(int i = 0; i < c.size(); i++) {
            System.out.println(i);
        }
       //System.exit(0);
    }
}
