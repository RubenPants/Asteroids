// Generated from AsteroidsProgram.g4 by ANTLR 4.7

    package asteroids.part3.programs.internal.generated;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AsteroidsProgramParser}.
 */
public interface AsteroidsProgramListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(AsteroidsProgramParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(AsteroidsProgramParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#functiondef}.
	 * @param ctx the parse tree
	 */
	void enterFunctiondef(AsteroidsProgramParser.FunctiondefContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#functiondef}.
	 * @param ctx the parse tree
	 */
	void exitFunctiondef(AsteroidsProgramParser.FunctiondefContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(AsteroidsProgramParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(AsteroidsProgramParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(AsteroidsProgramParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(AsteroidsProgramParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(AsteroidsProgramParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(AsteroidsProgramParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(AsteroidsProgramParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(AsteroidsProgramParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(AsteroidsProgramParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(AsteroidsProgramParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(AsteroidsProgramParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(AsteroidsProgramParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(AsteroidsProgramParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(AsteroidsProgramParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#statementSequence}.
	 * @param ctx the parse tree
	 */
	void enterStatementSequence(AsteroidsProgramParser.StatementSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#statementSequence}.
	 * @param ctx the parse tree
	 */
	void exitStatementSequence(AsteroidsProgramParser.StatementSequenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thrustOnAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void enterThrustOnAction(AsteroidsProgramParser.ThrustOnActionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thrustOnAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void exitThrustOnAction(AsteroidsProgramParser.ThrustOnActionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thrustOffAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void enterThrustOffAction(AsteroidsProgramParser.ThrustOffActionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thrustOffAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void exitThrustOffAction(AsteroidsProgramParser.ThrustOffActionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code turnAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void enterTurnAction(AsteroidsProgramParser.TurnActionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code turnAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void exitTurnAction(AsteroidsProgramParser.TurnActionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fireAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void enterFireAction(AsteroidsProgramParser.FireActionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fireAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void exitFireAction(AsteroidsProgramParser.FireActionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code skipAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSkipAction(AsteroidsProgramParser.SkipActionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code skipAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSkipAction(AsteroidsProgramParser.SkipActionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code changeSignExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterChangeSignExpression(AsteroidsProgramParser.ChangeSignExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code changeSignExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitChangeSignExpression(AsteroidsProgramParser.ChangeSignExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functCall}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctCall(AsteroidsProgramParser.FunctCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functCall}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctCall(AsteroidsProgramParser.FunctCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpression(AsteroidsProgramParser.NumberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpression(AsteroidsProgramParser.NumberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getYExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGetYExpression(AsteroidsProgramParser.GetYExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getYExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGetYExpression(AsteroidsProgramParser.GetYExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(AsteroidsProgramParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(AsteroidsProgramParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(AsteroidsProgramParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(AsteroidsProgramParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getRadiusExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGetRadiusExpression(AsteroidsProgramParser.GetRadiusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getRadiusExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGetRadiusExpression(AsteroidsProgramParser.GetRadiusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code entityExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEntityExpression(AsteroidsProgramParser.EntityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code entityExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEntityExpression(AsteroidsProgramParser.EntityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getXExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGetXExpression(AsteroidsProgramParser.GetXExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getXExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGetXExpression(AsteroidsProgramParser.GetXExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code readVariableExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterReadVariableExpression(AsteroidsProgramParser.ReadVariableExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code readVariableExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitReadVariableExpression(AsteroidsProgramParser.ReadVariableExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getVXExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGetVXExpression(AsteroidsProgramParser.GetVXExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getVXExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGetVXExpression(AsteroidsProgramParser.GetVXExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code readParameterExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterReadParameterExpression(AsteroidsProgramParser.ReadParameterExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code readParameterExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitReadParameterExpression(AsteroidsProgramParser.ReadParameterExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getDirectionExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGetDirectionExpression(AsteroidsProgramParser.GetDirectionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getDirectionExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGetDirectionExpression(AsteroidsProgramParser.GetDirectionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(AsteroidsProgramParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(AsteroidsProgramParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getVYExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGetVYExpression(AsteroidsProgramParser.GetVYExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getVYExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGetVYExpression(AsteroidsProgramParser.GetVYExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lessThanExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLessThanExpression(AsteroidsProgramParser.LessThanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lessThanExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLessThanExpression(AsteroidsProgramParser.LessThanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sqrtExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSqrtExpression(AsteroidsProgramParser.SqrtExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sqrtExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSqrtExpression(AsteroidsProgramParser.SqrtExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualExpression(AsteroidsProgramParser.EqualExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualExpression(AsteroidsProgramParser.EqualExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulExpression(AsteroidsProgramParser.MulExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulExpression(AsteroidsProgramParser.MulExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#entity}.
	 * @param ctx the parse tree
	 */
	void enterEntity(AsteroidsProgramParser.EntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#entity}.
	 * @param ctx the parse tree
	 */
	void exitEntity(AsteroidsProgramParser.EntityContext ctx);
	/**
	 * Enter a parse tree produced by {@link AsteroidsProgramParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(AsteroidsProgramParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link AsteroidsProgramParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(AsteroidsProgramParser.FunctionCallContext ctx);
}
