package commands;

import models.Turtle;

/**
 * @author Chintan B
 */
public class MoveCommand implements Command {

    private Turtle turtle;
    private int distance;
    private boolean isSuccess = false;

    public MoveCommand(Turtle turtle, int distance) {
        this.turtle = turtle;
        this.distance = distance;
    }

    @Override
    public boolean execute() {
        turtle.move(distance);
        isSuccess = true;
        return true;
    }

    @Override
    public boolean undo() {
        if(isSuccess) {
            turtle.move(-(distance));
            return true;
        } else {
            throw new IllegalArgumentException("Cannot undo command");
        }
    }
}
