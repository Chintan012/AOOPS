package visitors;

import interpreters.Expression;
import org.junit.jupiter.api.Test;
import program.Context;
import program.TurtleGraphics;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chintan B
 * Tests to verify the total distance travelled by the turtle object
 */
class TotalDistanceTravelledVisitorTest {

    private void readTextFile_And_CalculateDistance(String fileName, int totalDistanceTravelled) throws Exception {

        TurtleGraphics turtleGraphics = new TurtleGraphics(fileName);
        Context context = turtleGraphics.getContext();
        TotalDistanceTravelledVisitor distanceVisitor = new TotalDistanceTravelledVisitor(context);
        List<Expression> expressions = turtleGraphics.getExpressions();
        for(Expression expression : expressions) {
            expression.accept(distanceVisitor);
        }

        assertEquals(totalDistanceTravelled, distanceVisitor.getDistance());
    }

    @Test
    public void totalDistance_BasicFile() throws Exception {
        this.readTextFile_And_CalculateDistance("BasicOperations.txt", 45);
    }

    @Test
    public void totalDistance_File_With_Repeat_One() throws Exception {
        this.readTextFile_And_CalculateDistance("RepeatOperations1.txt", 20);
    }

    @Test
    public void totalDistance_File_With_Repeat_Two() throws Exception {
        this.readTextFile_And_CalculateDistance("RepeatOperations2.txt", 40);
    }

    @Test
    public void totalDistance_File_WithBearingTo_And_DistanceTo() throws Exception {
        this.readTextFile_And_CalculateDistance("FileWithBearingToAndDistanceTo.txt", 64);
    }

}