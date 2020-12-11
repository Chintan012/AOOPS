package visitors;

import commands.Command;
import commands.MoveCommand;
import commands.TurnCommand;
import interpreters.*;
import program.Context;

/**
 * @author Chintan B
 */
public class TotalDistanceTravelledVisitor implements Visitor {

    private Context context;
    private int totalDistance;

    public TotalDistanceTravelledVisitor(Context context) {
        this.context = context;
    }

    public int getDistance() {
        return totalDistance;
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
        moveExpression.getValueExpression().accept(this);
    }

    @Override
    public void visitTurn(TurnExpression turnExpression) {

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
