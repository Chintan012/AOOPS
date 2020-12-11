package interpreters;

import models.Point;
import program.Context;
import visitors.Visitor;

import java.util.List;

/**
 * @author Chintan B
 */
public class DistanceToExpression implements Expression {

    String pointVariable;
    String assignmentVariable;
    TerminalExpression valueExpression;
    private Point point;

    public DistanceToExpression(List<String> tokenList) {
        this.pointVariable = tokenList.get(tokenList.size() - 1).substring(1);
        this.assignmentVariable = tokenList.get(0);
    }

    public TerminalExpression getValueExpression() {
        return valueExpression;
    }

    @Override
    public int interpret(Context context) {
        point = context.getPoint(pointVariable);
        int value = context.getTurtle().distanceTo(point);
        context.setValue(assignmentVariable, value);
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitDistanceTo(this);
    }
}
