package commands;

/**
 * @author Chintan B
 */
public interface Command {

    public boolean execute();
    public boolean undo();

}
