package asteroids.program;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class ProgramFactory
		implements asteroids.part3.programs.IProgramFactory<MyExpression, MyStatement, MyFunction, Program> {

	public ProgramFactory() {

	}

	@Override
	public Program createProgram(List<MyFunction> functions, MyStatement main) {
		return new Program(functions, main);
	}

	@Override
	public MyFunction createFunctionDefinition(String functionName, MyStatement body, SourceLocation sourceLocation) {
		MyFunction result = new MyFunction(functionName, body);
		return result;
	}

	@Override
	public MyStatement createAssignmentStatement(String variableName, MyExpression expression,
			SourceLocation sourceLocation) {
		MyStatement result = new AssignmentStatement(variableName, expression);
		return result;
	}

	@Override
	public MyStatement createWhileStatement(MyExpression condition, MyStatement body, SourceLocation sourceLocation) {
		try {
			MyStatement result = new WhileStatement((BooleanExpression) condition, body);
			return result;
		} catch (ClassCastException error) {
			throw new IllegalArgumentException("You've made a while statement with a non-boolean condition");
		}
	}

	@Override
	public MyStatement createBreakStatement(SourceLocation sourceLocation) {
		MyStatement result = new BreakStatement();
		return result;
	}

	@Override
	public MyStatement createReturnStatement(MyExpression value, SourceLocation sourceLocation) {
		MyStatement result = new ReturnStatement(value);
		return result;
	}

	@Override
	public MyStatement createIfStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation sourceLocation) {
		try {
			MyStatement result = new IfElseStatement((BooleanExpression) condition, ifBody, elseBody);
			return result;
		} catch (ClassCastException error) {
			throw new IllegalArgumentException("You've made an if statement with a non-boolean condition");
		}
	}

	@Override
	public MyStatement createPrintStatement(MyExpression value, SourceLocation sourceLocation) {
		MyStatement result = new PrintStatement(value);
		return result;
	}

	@Override
	public MyStatement createSequenceStatement(List<MyStatement> statements, SourceLocation sourceLocation) {
		MyStatement result = new SequenceStatement(statements);
		return result;
	}

	public MyExpression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		MyExpression result = new VariableExpression(variableName);
		return result;
	}

	public MyExpression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		MyExpression result = new ParameterExpression(parameterName);
		return result;
	}

	public MyExpression createFunctionCallExpression(String functionName, List<MyExpression> actualArgs,
			SourceLocation sourceLocation) {
		MyExpression result = new FunctionExpression(functionName, actualArgs);
		return result;
	}

	public MyExpression createChangeSignExpression(MyExpression expression, SourceLocation sourceLocation) {

		try {
			MyExpression result = new NegationExpression((ArithmeticExpression) expression);
			return result;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("You've made a negation with a non-arithmetic expression");
		}
	}

	public MyExpression createNotExpression(MyExpression expression, SourceLocation sourceLocation) {
		try {
			MyExpression result = new LogicalNegationExpression((BooleanExpression) expression);
			return result;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("You've made a logical negation with a non-boolean expression");
		}

	}

	public MyExpression createDoubleLiteralExpression(double value, SourceLocation location) {
		MyExpression result = new DoubleLiteralExpression(value);
		return result;
	}

	public MyExpression createNullExpression(SourceLocation location) {
		MyExpression result = new NullEntity();
		return result;
	}

	public MyExpression createSelfExpression(SourceLocation location) {
		MyExpression result = new SelfEntity();
		return result;
	}

	public MyExpression createShipExpression(SourceLocation location) {
		MyExpression result = new ShipEntity();
		return result;
	}

	public MyExpression createAsteroidExpression(SourceLocation location) {
		MyExpression result = new AsteroidEntity();
		return result;
	}

	public MyExpression createPlanetoidExpression(SourceLocation location) {
		MyExpression result = new PlanetoidEntity();
		return result;
	}

	public MyExpression createBulletExpression(SourceLocation location) {
		MyExpression result = new BulletEntity();
		return result;
	}

	public MyExpression createPlanetExpression(SourceLocation location) {
		MyExpression result = new PlanetEntity();
		return result;
	}

	public MyExpression createAnyExpression(SourceLocation location) {
		MyExpression result = new AnyEntity();
		return result;
	}

	public MyExpression createGetXExpression(MyExpression e, SourceLocation location) {
		try {
			MyExpression result = new XPositionExpression(e);
			return result;
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException("You've made a create get X expression on a non-entity");
		}
	}

	public MyExpression createGetYExpression(MyExpression e, SourceLocation location) {
		try {
			MyExpression result = new YPositionExpression(e);
			return result;
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException("You've made a create get X expression on a non-entity");
		}
	}

	public MyExpression createGetVXExpression(MyExpression e, SourceLocation location) {
		try {
			MyExpression result = new XVelocityExpression(e);
			return result;
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException("You've made a create get velocityX expression on a non-entity");
		}
	}

	public MyExpression createGetVYExpression(MyExpression e, SourceLocation location) {
		try {
			MyExpression result = new YVelocityExpression(e);
			return result;
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException("You've made a create get velocityY expression on a non-entity");
		}
	}

	public MyExpression createGetRadiusExpression(MyExpression e, SourceLocation location) {
		try {
			MyExpression result = new RadiusExpression(e);
			return result;
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException("You've made a create get radius expression on a non-entity");
		}
	}

	public MyExpression createLessThanExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		try {
			MyExpression result = new LessThanExpression((ArithmeticExpression) e1, (ArithmeticExpression) e2);
			return result;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("You've made a less than expression with a non-arithmetic operand");
		}
	}

	public MyExpression createEqualityExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		MyExpression result = new EqualsToExpression(e1, e2);
		return result;
	}

	public MyExpression createAdditionExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		try {
			MyExpression result = new AdditionExpression((ArithmeticExpression) e1, (ArithmeticExpression) e2);
			return result;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("You've made a addition expression with a non-arithmetic operand");
		}
	}

	public MyExpression createMultiplicationExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		try {
			MyExpression result = new MultiplicationExpression((ArithmeticExpression) e1, (ArithmeticExpression) e2);
			return result;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("You've made a multiplication expression with a non-arithmetic operand");
		}
	}

	public MyExpression createSqrtExpression(MyExpression expression, SourceLocation location) {
		try {
			MyExpression result = new SquareRootExpression((ArithmeticExpression) expression);
			return result;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("You've made a sqrt expression with a non-arithmetic operand");
		}
	}

	public MyExpression createGetDirectionExpression(SourceLocation location) {
		MyExpression result = new DirectionExpression();
		return result;
	}

	@Override
	public MyStatement createThrustOnStatement(SourceLocation location) {
		MyStatement result = new ThrustOnAction(location);
		return result;
	}

	@Override
	public MyStatement createThrustOffStatement(SourceLocation location) {
		MyStatement result = new ThrustOffAction(location);
		return result;
	}

	@Override
	public MyStatement createFireStatement(SourceLocation location) {
		MyStatement result = new FireAction(location);
		return result;
	}

	@Override
	public MyStatement createTurnStatement(MyExpression angle, SourceLocation location) {
		MyStatement result = new TurnAction(angle, location);
		return result;
	}

	@Override
	public MyStatement createSkipStatement(SourceLocation location) {
		MyStatement result = new SkipAction(location);
		return result;
	}

}