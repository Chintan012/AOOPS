package program;

import interpreters.*;
import models.Turtle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Acts as a starting point of the program
 *
 * @author Chintan B
 */

public class TurtleGraphics {

    private static final String VARIABLE_REGEX = "[a-zA-Z]+";
    private Turtle turtle = new Turtle();
    private BufferedReader bufferedReader;
    private Context context = new Context(turtle);
    private List<String> tokenList;
    private boolean moreCommandsPresent = true;

    public TurtleGraphics(String fileName) throws Exception {

        URL fileURL = ClassLoader.getSystemResource(fileName);
        if (fileURL == null) {
            throw new FileNotFoundException("File does not exist");
        }

        Path filePath = Paths.get(fileURL.toURI());
        bufferedReader = Files.newBufferedReader(filePath);
    }

    public Turtle getTurtle() {
        return turtle;
    }

    boolean hasMoreCommands() {
        return moreCommandsPresent;
    }

    public Context getContext() {
        return context;
    }

    /**
     * @return list of expressions which will be useful when visiting each expression
     * @throws IOException
     */
    public List<Expression> getExpressions()
            throws IOException {

        List<Expression> expressions = new ArrayList<>();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            List<String> listOfTokens = generateListOfTokens(line);
            expressions.add(parseListOfTokens(listOfTokens));
        }

        return expressions;
    }

    void readFile() throws Exception {

        if (!hasMoreCommands()) {
            throw new Exception("Reached end of file");
        }
        String line = bufferedReader.readLine();

        if (line == null) {
            moreCommandsPresent = false;
        } else {
            tokenList = generateListOfTokens(line);
            parseListOfTokens(tokenList).interpret(context);
        }
    }

    /**
     * Method to convert string to a list of tokens for each line
     *
     * @param line : each line inside the file
     * @return list of tokens
     */
    private List<String> generateListOfTokens(String line) {
        StringTokenizer tokens = new StringTokenizer(line.replaceAll("[=,]", "").trim());
        return Collections.list(tokens)
                .stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }

    /**
     * @param tokenList
     * @return Expression for each token that we want to interpret
     * @throws IOException
     */
    public Expression parseListOfTokens(List<String> tokenList) throws IOException {

        String firstCommand = tokenList.get(0);
        String secondCommand = tokenList.get(1);
        Expression firstExpression = null;

        if (firstCommand.startsWith("#P") && firstCommand.substring(2).matches(VARIABLE_REGEX)) {
            firstExpression = new PointExpression(tokenList);
        } else if (firstCommand.startsWith("#") && tokenList.size() < 3) {
            firstExpression = generateAssignmentParameters(tokenList);
        } else if (firstCommand.equalsIgnoreCase("MOVE")) {
            TerminalExpression parameters = generateParameters(tokenList);
            firstExpression = new MoveExpression(parameters);
        } else if (firstCommand.equalsIgnoreCase("TURN")) {
            TerminalExpression parameters = generateParameters(tokenList);
            firstExpression = new TurnExpression(parameters);
        } else if (firstCommand.equalsIgnoreCase("REPEAT")) {
            return generateRepeatParameters(tokenList);
        } else if (secondCommand.equalsIgnoreCase("BEARINGTO")) {
            firstExpression = new BearingToExpression(tokenList);
        } else if (secondCommand.equalsIgnoreCase("DISTANCETO")) {
            firstExpression = new DistanceToExpression(tokenList);
        } else {
            throw new IllegalArgumentException("Cannot read file");
        }
        return firstExpression;
    }


    private TerminalExpression generateParameters(List<String> tokenList) {
        TerminalExpression parameters;
        String secondExpression = tokenList.get(tokenList.size() - 1);
        if (secondExpression.startsWith(("#"))) {
            parameters = new VariableExpression(secondExpression);
        } else {
            parameters = new ConstantExpression(Integer.valueOf(secondExpression));
        }

        checkExpressionIsValid(tokenList);

        return parameters;
    }


    private Expression generatePointParameters(List<String> tokenList) {
        Expression parameters;
        String secondExpression = tokenList.get(tokenList.size() - 1);
        parameters = new VariableExpression(secondExpression);

        return parameters;
    }

    private Expression generateAssignmentParameters(List<String> tokenList) {

        Expression parameters = null;
        int value;
        String firstExpression = tokenList.get(0);
        String secondExpression = tokenList.get(1);

        if (firstExpression.startsWith("#") && tokenList.size() != 3) {
            value = Integer.valueOf(secondExpression);
            parameters = new AssignmentExpression(tokenList, value);
        } else {
            throw new IllegalArgumentException("Cannot identify command");
        }

        return parameters;
    }

    private Expression generateRepeatParameters(List<String> tokenList) throws IOException {
        int repetitions = Integer.valueOf(tokenList.get(1));
        RepeatExpression repeatExpression = new RepeatExpression(repetitions, context);

        String linesInsideRepeat;
        while ((linesInsideRepeat = bufferedReader.readLine()) != null && !linesInsideRepeat.equalsIgnoreCase("END")) {
            List<String> listOfRepeatTokens = generateListOfTokens(linesInsideRepeat);
            repeatExpression.add(parseListOfTokens(listOfRepeatTokens));
        }

        return repeatExpression;
    }

    private void checkExpressionIsValid(List<String> tokenList) {
        if (tokenList.isEmpty()) {
            throw new IllegalArgumentException("Not a valid statement");
        }
    }

}
