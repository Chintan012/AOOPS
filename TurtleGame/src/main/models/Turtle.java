package models;

/**
 * @author Chintan B
 */

public class Turtle {

    private Point currentLocation;
    private int direction;

    public Turtle() {
        currentLocation = new Point();
        direction = 0;
    }

    public int getDirection() {
        return direction;
    }

    public void move(int distance) {
//        if (distance < 0) {
//            throw new IllegalArgumentException("Distance cannot be negative");
//        }

        double radians = direction * Math.PI / 180;
        double deltaX = (Math.cos(radians) * distance);
        double deltaY = (Math.sin(radians) * distance);

        int newX = (int) Math.round(currentLocation.getX() + deltaX);
        int newY = (int) Math.round(currentLocation.getY() + deltaY);

        currentLocation = new Point(newX, newY);
    }

    public void turn(int degrees) {
        direction = direction + degrees;
        direction = direction % 360;
    }

    public int direction() {
        return direction;
    }

    public Point location() {
        return currentLocation;
    }

    public int distanceTo(Point secondPoint) {
        int x1 = currentLocation.getX();
        int y1 = currentLocation.getY();
        int x2 = secondPoint.getX();
        int y2 = secondPoint.getX();

        return (int) Math.hypot(x1 - x2, y1 - y2);
    }

    public int bearingTo(Point secondPoint) {
        int x1 = currentLocation.getX();
        int y1 = currentLocation.getY();
        int x2 = secondPoint.getX();
        int y2 = secondPoint.getX();

        //check if both points are in the same axis : angle should be 0
        if ((x1 - x2) == 0) {
            return 0;
        } else {
            return (int) Math.toDegrees(Math.atan((y1 - y2) / (x1 - x2)));
        }
    }
}
