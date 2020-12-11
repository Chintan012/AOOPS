package visitors;

import commands.*;
import interpreters.*;
import program.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chintan B
 */
public class CommandVisitor implements Visitor {

    private Context context;

    private List<Command> listOfCommands;
    private int totalDistance;

    public CommandVisitor(Context context) {
        this.context = context;
        listOfCommands = new ArrayList();
    }

    public List<Command> getListOfCommands() {
        return listOfCommands;
    }

    @Override
    public void visitAssignment(AssignmentExpression assignmentExpression) {
        assignmentExpression.interpret(context);
    }

    @Override
    public void visitConstant(ConstantExpression constantExpression) {
        totalDistance = totalDistance + constantExpression.interpret(context);
    }

    @Override
    public void visitVariable(VariableExpression variableExpression) {
        totalDistance = totalDistance + variableExpression.interpret(context);
    }

    @Override
    public void visitMove(MoveExpression moveExpression) {
        Command moveCommand = new MoveCommand(context.getTurtle(), moveExpression.getValueExpression().interpret(context));
        listOfCommands.add(moveCommand);
    }

    @Override
    public void visitTurn(TurnExpression turnExpression) {
        Command turnCommand = new TurnCommand(context.getTurtle(), turnExpression.getValueExpression().interpret(context));
        listOfCommands.add(turnCommand);
    }

    @Override
    public void visitRepeat(RepeatExpression repeatExpression) {
        int repetitions = repeatExpression.getRepetitions();
        while (repetitions-- > 0) {
            for(Expression nextExpression : repeatExpression.getExpressionList()) {
                nextExpression.accept(this);
            }
        }
    }

    @Override
    public void visitBearingTo(BearingToExpression bearingToExpression) {
        bearingToExpression.interpret(context);
    }

    @Override
    public void visitDistanceTo(DistanceToExpression distanceToExpression) {
        distanceToExpression.interpret(context);
    }

    @Override
    public void visitPoint(PointExpression pointExpression) {
        pointExpression.interpret(context);
    }
}
