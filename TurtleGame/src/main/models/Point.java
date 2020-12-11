package models;

import java.text.DecimalFormat;

/**
 * @author Chintan B
 */
public class Point {

    // x and y co-ordinates
    private int x;
    private int y;

    Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point secondPoint) {
        this.x = secondPoint.getX();
        this.y = secondPoint.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return this.getX() + " " + this.getY();
    }

}
