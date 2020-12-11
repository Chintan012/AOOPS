package interpreters;

import program.Context;
import visitors.Visitor;

/**
 * @author Chintan B
 */
public class TurnExpression implements Expression {

    private TerminalExpression valueExpression;

    public TurnExpression(TerminalExpression degrees) {
        this.valueExpression = degrees;
    }

    public TerminalExpression getValueExpression() {
        return valueExpression;
    }

    @Override
    public int interpret(Context context) {
        int degrees = valueExpression.interpret(context);
        context.getTurtle().turn(degrees);

        return degrees;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitTurn(this);
    }
}
