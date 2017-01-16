package tn.kaz.ospas.model;

import java.util.Set;

/**
 * Created by Anton on 13.01.2017.
 */
public interface HasParent<T> {
    T getParent();
    void setParent(T t);
    Set<T> getChilds();
    void setChilds(Set<T> t);

}
