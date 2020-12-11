package interpreters;

import program.Context;
import visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chintan B
 */
public class RepeatExpression implements Expression {

    private int repetitions;
    private List<Expression> expressionList;
    private Context context;

    public RepeatExpression(int repetitions, Context context) {
        this.repetitions = repetitions;
        this.context = context;
        expressionList = new ArrayList<>();
    }

    public int getRepetitions() {
        return repetitions;
    }

    public List<Expression> getExpressionList() {
        return expressionList;
    }

    public void add(Expression parseListOfTokens) {
        expressionList.add(parseListOfTokens);
    }

    @Override
    public int interpret(Context context) {
        int repetitions = this.repetitions;
        int value = 0;

        while (repetitions-- > 0) {
            for (Expression nextExpression : expressionList) {
                value = value + nextExpression.interpret(context);
            }
        }
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitRepeat(this);
    }
}
