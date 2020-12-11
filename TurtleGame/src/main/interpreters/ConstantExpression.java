package interpreters;

import program.Context;
import visitors.Visitor;

/**
 * @author Chintan B
 */
public class ConstantExpression implements TerminalExpression {

    private int value;

    public ConstantExpression(int value) {
        this.value = value;
    }

    @Override
    public int interpret(Context context) {
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConstant(this);
    }
}
