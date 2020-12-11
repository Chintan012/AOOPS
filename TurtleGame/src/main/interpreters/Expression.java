package interpreters;

import program.Context;
import visitors.Visitor;

/**
 * @author Chintan B
 */
public interface Expression {

    int interpret(Context context);

    void accept(Visitor visitor);

}
