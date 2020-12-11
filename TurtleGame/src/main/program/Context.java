package program;

import models.Point;
import models.Turtle;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chintan B
 */
public class Context {

    private Turtle turtle;
    private Map<String, Integer> mapOfValues;
    private Map<String, Point> mapOfPoints;

    public Context(Turtle turtle) {
        this.turtle = turtle;
        mapOfValues = new HashMap<>();
        mapOfPoints = new HashMap<>();
    }

    public int getValue(String variableName) {
        return mapOfValues.get(variableName);
    }

    public void setValue(String variableName, int value) {
        mapOfValues.put(variableName, value);
    }

    public Point getPoint(String variableName) {
        return mapOfPoints.get(variableName);
    }

    public void setPoint(String variableName, Point newPoint) {
        mapOfPoints.put(variableName, newPoint);
    }

    public Turtle getTurtle() {
        return turtle;
    }

}
