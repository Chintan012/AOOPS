package commands;

import interpreters.Expression;
import org.junit.jupiter.api.Test;
import program.Context;
import program.TurtleGraphics;
import visitors.CommandVisitor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chintan B
 */
class CommandTest {

    @Test
    void testAllCommandExecution() throws Exception {

        TurtleGraphics turtleGraphics = new TurtleGraphics("FileWithBearingToAndDistanceTo.txt");
        Context context = turtleGraphics.getContext();
        CommandVisitor commandVisitor = new CommandVisitor(context);
        List<Expression> expressions = turtleGraphics.getExpressions();
        for (Expression expression : expressions) {
            expression.accept(commandVisitor);
        }

        List<Command> commands = commandVisitor.getListOfCommands();
        for (Command currentCommand : commands) {
            currentCommand.execute();
        }

        String actualOutput = context.getTurtle().location().toString();

        assertEquals("10 18", actualOutput);

    }

    @Test
    void testUndoMoveCommand() throws Exception {
        TurtleGraphics turtleGraphics = new TurtleGraphics("TestUndoMoveCommand.txt");
        Context context = turtleGraphics.getContext();
        CommandVisitor commandVisitor = new CommandVisitor(context);
        List<Expression> expressions = turtleGraphics.getExpressions();
        for (Expression expression : expressions) {
            expression.accept(commandVisitor);
        }

        List<Command> commands = commandVisitor.getListOfCommands();

        Command moveCommand = commands.get(0);
        moveCommand.execute();

        String actualOutputBeforeUndo = context.getTurtle().location().toString();

        moveCommand.undo();

        String actualOutputAfterUndo = context.getTurtle().location().toString();

        assertEquals("10 0", actualOutputBeforeUndo);
        assertEquals("0 0", actualOutputAfterUndo);
    }

    @Test
    void testUndoTurnCommand() throws Exception {
        TurtleGraphics turtleGraphics = new TurtleGraphics("TestUndoTurnCommand.txt");
        Context context = turtleGraphics.getContext();
        CommandVisitor commandVisitor = new CommandVisitor(context);
        List<Expression> expressions = turtleGraphics.getExpressions();
        for (Expression expression : expressions) {
            expression.accept(commandVisitor);
        }

        List<Command> commands = commandVisitor.getListOfCommands();

        Command turnCommand = commands.get(0);
        turnCommand.execute();

        int actualOutputBeforeUndo = context.getTurtle().getDirection();

        turnCommand.undo();

        int actualOutputAfterUndo = context.getTurtle().getDirection();

        assertEquals(45, actualOutputBeforeUndo);
        assertEquals(0, actualOutputAfterUndo);

    }

    @Test
    void testUndo_With_RepeatCommand() throws Exception {
        TurtleGraphics turtleGraphics = new TurtleGraphics("TestUndoWithRepeatCommand.txt");
        Context context = turtleGraphics.getContext();
        CommandVisitor commandVisitor = new CommandVisitor(context);
        List<Expression> expressions = turtleGraphics.getExpressions();
        for (Expression expression : expressions) {
            expression.accept(commandVisitor);
        }

        List<Command> commands = commandVisitor.getListOfCommands();
        for (Command currentCommand : commands) {
            currentCommand.execute();

        }

        String actualOutputBeforeUndo = context.getTurtle().location().toString();

        Command lastCommand = commands.get(commands.size() - 1);
        lastCommand.undo();

        String actualOutputAfterUndo = context.getTurtle().location().toString();

        assertEquals("80 0", actualOutputBeforeUndo);
        assertEquals("60 0", actualOutputAfterUndo);

    }

}