package interpreters;

import models.Point;
import program.Context;
import visitors.Visitor;

import java.util.List;

/**
 * @author Chintan B
 */
public class AssignmentExpression implements Expression {

    private String variable;
    private Point point;
    private int value;


    public AssignmentExpression(List<String> tokenList, int value) {
        this.variable = tokenList.get(0);
        this.value = value;
    }

    @Override
    public int interpret(Context context) {
        context.setValue(variable, value);
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitAssignment(this);
    }
}
