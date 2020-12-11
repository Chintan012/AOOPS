package commands;

import models.Turtle;

/**
 * @author Chintan B
 */
public class TurnCommand implements Command {

    private Turtle turtle;
    private int degrees;
    private boolean isSuccess = false;

    public TurnCommand(Turtle turtle, int degrees) {
        this.turtle = turtle;
        this.degrees = degrees;
    }

    @Override
    public boolean execute() {
        turtle.turn(degrees);
        isSuccess = true;
        return true;
    }

    @Override
    public boolean undo() {
        if(isSuccess) {
            turtle.turn(-(degrees));
            return true;
        } else {
            throw new IllegalArgumentException("Cannot undo command");
        }
    }
}
