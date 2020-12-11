package interpreters;

import models.Point;
import program.Context;
import visitors.Visitor;

import java.util.List;

/**
 * @author Chintan B
 */
public class BearingToExpression implements Expression {

    String pointVariable;
    String assignmentVariable;
    private Point point;

    public BearingToExpression(List<String> tokenList) {
        this.pointVariable = tokenList.get(tokenList.size() - 1).substring(1);
        this.assignmentVariable = tokenList.get(0);
    }

    @Override
    public int interpret(Context context) {
        point = context.getPoint(pointVariable);
        int value = context.getTurtle().bearingTo(point);
        context.setValue(assignmentVariable, value);
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitBearingTo(this);
    }
}
