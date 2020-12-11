package visitors;

import interpreters.*;

/**
 * @author Chintan B
 */
public interface Visitor {

    public void visitAssignment(AssignmentExpression assignmentExpression);
    public void visitConstant(ConstantExpression constantExpression);
    public void visitVariable(VariableExpression variableExpression);
    public void visitMove(MoveExpression moveExpression);
    public void visitTurn(TurnExpression turnExpression);
    public void visitRepeat(RepeatExpression repeatExpression);
    public void visitBearingTo(BearingToExpression bearingToExpression);
    public void visitDistanceTo(DistanceToExpression distanceToExpression);
    public void visitPoint(PointExpression pointExpression);

}
