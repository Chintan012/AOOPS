package interpreters;

import program.Context;
import visitors.Visitor;

/**
 * @author Chintan B
 */
public class VariableExpression implements TerminalExpression {

    private String parameters;

    public VariableExpression(String parameters) {
        this.parameters = parameters;
    }

    @Override
    public int interpret(Context context) {
        return context.getValue(parameters);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitVariable(this);
    }
}
