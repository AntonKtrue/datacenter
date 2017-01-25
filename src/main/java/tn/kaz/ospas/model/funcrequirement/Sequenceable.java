package tn.kaz.ospas.model.funcrequirement;

/**
 * Created by Anton on 24.01.2017.
 */
public interface Sequenceable {
    public int getSequence();
    public void setSequence(int order);
    public FuncRequirement getFuncRequirement();
    public void setFuncRequirement(FuncRequirement funcRequirement);
    public String getDescription();
    public void setDescription(String description);
}
