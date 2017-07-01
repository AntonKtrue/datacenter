package tn.kaz.ospas.event;

/**
 * Created by Anton on 20.02.2017.
 */
public aspect AspectNew2 {
    public AspectNew2() {}
    pointcut perfomance() : execution(* perform(..));
}
