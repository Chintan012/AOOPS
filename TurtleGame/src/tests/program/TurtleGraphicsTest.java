package program;

import models.Turtle;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chintan B
 */
class TurtleGraphicsTest {

    @Test
    void testTurtleLocation_And_Direction() throws Exception {
        TurtleGraphics turtleGraphics = new TurtleGraphics("BasicOperations.txt");

        while (turtleGraphics.hasMoreCommands()) {
            turtleGraphics.readFile();
        }

        String actualLocation = turtleGraphics.getTurtle().location().toString();
        int actualDirection = turtleGraphics.getTurtle().getDirection();

        assertEquals("23 28", actualLocation);
        assertEquals(30, actualDirection);
    }

    @Test
    void testTurtle_With_BearingTO_And_DistanceTo() throws Exception {
        TurtleGraphics turtleGraphics = new TurtleGraphics("FileWithBearingToAndDistanceTo.txt");

        while (turtleGraphics.hasMoreCommands()) {
            turtleGraphics.readFile();
        }

        String actualLocation = turtleGraphics.getTurtle().location().toString();
        int actualDirection = turtleGraphics.getTurtle().getDirection();

        assertEquals("10 18", actualLocation );
        assertEquals(135, actualDirection);
    }

    @Test
    void testTurtle_With_InvalidLanguage() throws Exception {
        TurtleGraphics turtleGraphics = new TurtleGraphics("FileWithWrongLanguage.txt");

        assertThrows(IllegalArgumentException.class, () -> {
           while (turtleGraphics.hasMoreCommands()) {
               turtleGraphics.readFile();
           }
        });
    }

    @Test
    void testTurtle_RetuningToSameLocation() throws Exception {
        TurtleGraphics turtleGraphics = new TurtleGraphics("TurtleRetuningToSamePosition.txt");

        while (turtleGraphics.hasMoreCommands()) {
            turtleGraphics.readFile();
        }

        String actualLocation = turtleGraphics.getTurtle().location().toString();
        int actualDirection = turtleGraphics.getTurtle().getDirection();

        assertEquals("0 0", actualLocation);
        assertEquals(180, actualDirection);
    }
}