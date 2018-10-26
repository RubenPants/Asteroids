package asteroids.part3.programs;

import java.util.List;

/**
 * A program factory is used by the parser ({@link ProgramParser}) to construct
 * an in-memory representation of your program. For example, when reading the
 * program source code
 * 
 * <pre>
 * print getx self
 * </pre>
 * 
 * the parser will create a Program object by (conceptually) executing the
 * following code:
 * 
 * <pre>
 * factory.createProgram(Collections.emptyList(),
 * 		factory.createPrintStatement(factory.createGetXExpression(factory.createSelfExpression())));
 * </pre>
 * 
 * on the returned factory object.
 * 
 * <p>
 * For testing, you may use the methods from {@link ProgramParser} yourself, as
 * demonstrated in the test file {@link Part3TestFull}.
 * 
 * <p>
 * You should declare your class as follows:<code><pre>
 * public class ProgramFactory implements IProgramFactory&lt;MyExpression, MyStatement, MyFunction, Program&gt;
 * </pre></code> where MyExpression, MyStatement and MyFunction are your classes
 * for representing expressions, statements and function definitions,
 * respectively.
 * 
 * <p>
 * The SourceLocation object in the methods defined by this factory refers to
 * the location (line and column) in the text file where the statement or
 * expression begins.
 * 
 * @param <E>
 *            Your class for representing an expression.
 * @param <S>
 *            Your class for representing a statement.
 * @param <F>
 *            Your class representing function definitions.
 * @param <P>
 *            Your class for representing a program (should be Program).
 * 
 * 
 */
public interface IProgramFactory<E, S, F, P> {

	/* PROGRAM */

	/**
	 * Create a program from the given arguments.
	 * 
	 * @param functions
	 *            The function definitions for the program.
	 * @param main
	 *            The main statement of the program. Most likely this is a
	 *            sequence statement.
	 * @return A new program.
	 */
	public P createProgram(List<F> functions, S main);

	/* FUNCTION DEFINITIONS */

	/**
	 * Create a function definition involving the given function name,
	 * parameters and body.
	 * 
	 * @param functionName
	 *            The name of the function
	 * @param body
	 *            The body of the function.
	 */
	public F createFunctionDefinition(String functionName, S body, SourceLocation sourceLocation);

	/* STATEMENTS */

	/**
	 * Create a statement that represents the assignment of a variable.
	 * 
	 * @param variableName
	 *            The name of the assigned variable
	 * @param value
	 *            An expression that evaluates to the assigned value
	 */
	public S createAssignmentStatement(String variableName, E value, SourceLocation sourceLocation);

	/**
	 * Create a statement that represents a while loop.
	 * 
	 * @param condition
	 *            The condition of the while loop
	 * @param body
	 *            The body of the loop (most likely this is a sequence
	 *            statement).
	 */
	public S createWhileStatement(E condition, S body, SourceLocation sourceLocation);

	/**
	 * Create a statement that represents a break statement.
	 */
	public S createBreakStatement(SourceLocation sourceLocation);

	/**
	 * Create a statement that represents a return statement.
	 * 
	 * @param value
	 *            An expression that evaluates to the value to be returned
	 */
	public S createReturnStatement(E value, SourceLocation sourceLocation);

	/**
	 * Create an if-then-else statement.
	 * 
	 * @param condition
	 *            The condition of the if statement
	 * @param ifBody
	 *            The body of the if-part, which must be executed when the
	 *            condition evaluates to true
	 * @param elseBody
	 *            The body of the else-part, which must be executed when the
	 *            condition evaluates to false. Can be null if no else clause is
	 *            specified.
	 */
	public S createIfStatement(E condition, S ifBody, S elseBody, SourceLocation sourceLocation);

	/**
	 * Create a print statement that prints the value obtained by evaluating the
	 * given expression.
	 * 
	 * @param value
	 *            The expression to evaluate and print
	 */
	public S createPrintStatement(E value, SourceLocation sourceLocation);

	/**
	 * Create a sequence of statements.
	 * 
	 * @param statements
	 *            The statements that must be executed in the given order.
	 */
	public S createSequenceStatement(List<S> statements, SourceLocation sourceLocation);

	/* EXPRESSIONS */

	/**
	 * Create an expression that evaluates to the current value of the given
	 * variable.
	 * 
	 * @param variableName
	 *            The name of the variable to read.
	 */
	public E createReadVariableExpression(String variableName, SourceLocation sourceLocation);

	/**
	 * Create an expression that evaluates to the current value of the given
	 * parameter.
	 * 
	 * @param parameterName
	 *            The name of the parameter to read.
	 */
	public E createReadParameterExpression(String parameterName, SourceLocation sourceLocation);

	/**
	 * Create an expression that evaluates to result of calling the given
	 * function with the given list of actual arguments.
	 * 
	 * @param functionName
	 *            The name of the function to call.
	 * @param actualArgs
	 *            A list of expressions that act as actual arguments.
	 */
	public E createFunctionCallExpression(String functionName, List<E> actualArgs, SourceLocation sourceLocation);

	/**
	 * Create an expression that evaluates to the given expression with changed
	 * sign (i.e., negated).
	 * 
	 * @param expression
	 */
	public E createChangeSignExpression(E expression, SourceLocation sourceLocation);

	/**
	 * Create an expression that evaluates to true when the given expression
	 * evaluates to false, and vice versa.
	 * 
	 * @param expression
	 */
	public E createNotExpression(E expression, SourceLocation sourceLocation);

	/**
	 * Creates an expression that represents a literal double value.
	 */
	public E createDoubleLiteralExpression(double value, SourceLocation location);

	/**
	 * Creates an expression that represents the null value.
	 */
	public E createNullExpression(SourceLocation location);

	/**
	 * Creates an expression that represents the self value, evaluating to the
	 * ship that executes the program.
	 */
	public E createSelfExpression(SourceLocation location);

	/**
	 * Creates an expression that evaluates to the ship that is closest to the
	 * ship that is executing the program.
	 */
	public E createShipExpression(SourceLocation location);

	/**
	 * Creates an expression that evaluates to the asteroid that is closest to
	 * the ship that is executing the program.
	 */
	public E createAsteroidExpression(SourceLocation location);

	/**
	 * Creates an expression that evaluates to the planetoid that is closest to
	 * the ship that is executing the program.
	 */
	public E createPlanetoidExpression(SourceLocation location);

	/**
	 * Creates an expression that evaluates to one of the bullets fired by the
	 * ship that executes the program.
	 */
	public E createBulletExpression(SourceLocation location);

	/**
	 * Creates an expression that evaluates to the minor planet that is closest
	 * to the ship that is executing the program.
	 */
	public E createPlanetExpression(SourceLocation location);

	/**
	 * Creates an expression that evaluates to an arbitrary entity in the world
	 * of the ship that is executing the program.
	 */
	public E createAnyExpression(SourceLocation location);

	/**
	 * Returns an expression that evaluates to the position along the x-axis of
	 * the entity to which the given expression evaluates.
	 */
	public E createGetXExpression(E e, SourceLocation location);

	/**
	 * Returns an expression that evaluates to the position along the y-axis of
	 * the entity to which the given expression evaluates.
	 */
	public E createGetYExpression(E e, SourceLocation location);

	/**
	 * Returns an expression that evaluates to the velocity along the x-axis of
	 * the entity to which the given expression evaluates.
	 */
	public E createGetVXExpression(E e, SourceLocation location);

	/**
	 * Returns an expression that evaluates to the velocity along the y-axis of
	 * the entity to which the given expression evaluates.
	 */
	public E createGetVYExpression(E e, SourceLocation location);

	/**
	 * Returns an expression that evaluates to the radius of the entity to which
	 * the given expression evaluates.
	 */
	public E createGetRadiusExpression(E e, SourceLocation location);

	/**
	 * Returns an expression that evaluates to true if the evaluation of the
	 * first expression yields a value that is less than the value obtained by
	 * evaluating the second expression.
	 */
	public E createLessThanExpression(E e1, E e2, SourceLocation location);

	/**
	 * Returns an expression that evaluates to true if the evaluation of the
	 * first expression yields a value that is equal to the value obtained by
	 * evaluating the second expression.
	 */
	public E createEqualityExpression(E e1, E e2, SourceLocation location);

	/**
	 * Returns an expression that evaluates to the addition of the values
	 * obtained by evaluating the first and second given expressions.
	 */
	public E createAdditionExpression(E e1, E e2, SourceLocation location);

	/**
	 * Returns an expression that evaluates to the multiplication of the values
	 * obtained by evaluating the first and second given expressions.
	 */
	public E createMultiplicationExpression(E e1, E e2, SourceLocation location);

	/**
	 * Returns an expression that evaluates to the square root of the value
	 * obtained by evaluating the given expression.
	 */
	public E createSqrtExpression(E e, SourceLocation location);

	/**
	 * Returns an expression that evaluates to the direction (in radians) of the
	 * ship executing the program.
	 */
	public E createGetDirectionExpression(SourceLocation location);

	/**
	 * Returns a statement that turns the thruster of the ship executing the
	 * program on.
	 */
	public S createThrustOnStatement(SourceLocation location);

	/**
	 * Returns a statement that turns the thruster of the ship executing the
	 * program off.
	 */
	public S createThrustOffStatement(SourceLocation location);

	/**
	 * Returns a statement that fires a bullet from the ship that is executing
	 * the program.
	 */
	public S createFireStatement(SourceLocation location);

	/**
	 * Returns a statement that makes the ship that is executing the program
	 * turn by the given amount.
	 */
	public S createTurnStatement(E angle, SourceLocation location);

	/**
	 * Returns a statement that does nothing.
	 */
	public S createSkipStatement(SourceLocation location);

}
