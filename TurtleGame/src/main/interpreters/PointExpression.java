package interpreters;

import models.Point;
import program.Context;
import visitors.Visitor;

import java.util.List;

/**
 * @author Chintan B
 */
public class PointExpression implements Expression {

    private String variable;
    private int XCoordinate, YCoordinate;
    private Point newPoint;

    public PointExpression(List<String> tokenList) {
        this.variable = tokenList.get(0).substring(2);
        this.XCoordinate = Integer.parseInt(tokenList.get(1));
        this.YCoordinate = Integer.parseInt(tokenList.get(2));

    }

    @Override
    public int interpret(Context context) {
        newPoint = new Point(XCoordinate, YCoordinate);
        context.setPoint(variable, newPoint);
        return 0;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitPoint(this);
    }
}
