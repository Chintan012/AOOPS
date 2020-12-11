package interpreters;

import program.Context;
import visitors.Visitor;

/**
 * @author Chintan B
 */
public class MoveExpression implements Expression {

    private TerminalExpression valueExpression;

    public MoveExpression(TerminalExpression distance) {
        this.valueExpression = distance;
    }

    public TerminalExpression getValueExpression() {
        return valueExpression;
    }

    @Override
    public int interpret(Context context) {
        int distance = valueExpression.interpret(context);
        context.getTurtle().move(distance);
        return distance;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitMove(this);
    }
}
