// Generated from AsteroidsProgram.g4 by ANTLR 4.7

    package asteroids.part3.programs.internal.generated;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AsteroidsProgramParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AsteroidsProgramVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(AsteroidsProgramParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#functiondef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctiondef(AsteroidsProgramParser.FunctiondefContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(AsteroidsProgramParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(AsteroidsProgramParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(AsteroidsProgramParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(AsteroidsProgramParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(AsteroidsProgramParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(AsteroidsProgramParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(AsteroidsProgramParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#statementSequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementSequence(AsteroidsProgramParser.StatementSequenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thrustOnAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrustOnAction(AsteroidsProgramParser.ThrustOnActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thrustOffAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrustOffAction(AsteroidsProgramParser.ThrustOffActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code turnAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTurnAction(AsteroidsProgramParser.TurnActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fireAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFireAction(AsteroidsProgramParser.FireActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code skipAction}
	 * labeled alternative in {@link AsteroidsProgramParser#actionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkipAction(AsteroidsProgramParser.SkipActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code changeSignExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChangeSignExpression(AsteroidsProgramParser.ChangeSignExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functCall}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctCall(AsteroidsProgramParser.FunctCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpression(AsteroidsProgramParser.NumberExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getYExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetYExpression(AsteroidsProgramParser.GetYExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(AsteroidsProgramParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpression(AsteroidsProgramParser.ParenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getRadiusExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetRadiusExpression(AsteroidsProgramParser.GetRadiusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code entityExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityExpression(AsteroidsProgramParser.EntityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getXExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetXExpression(AsteroidsProgramParser.GetXExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code readVariableExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadVariableExpression(AsteroidsProgramParser.ReadVariableExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getVXExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetVXExpression(AsteroidsProgramParser.GetVXExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code readParameterExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadParameterExpression(AsteroidsProgramParser.ReadParameterExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getDirectionExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetDirectionExpression(AsteroidsProgramParser.GetDirectionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpression(AsteroidsProgramParser.AddExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getVYExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetVYExpression(AsteroidsProgramParser.GetVYExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lessThanExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThanExpression(AsteroidsProgramParser.LessThanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sqrtExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqrtExpression(AsteroidsProgramParser.SqrtExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualExpression(AsteroidsProgramParser.EqualExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link AsteroidsProgramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExpression(AsteroidsProgramParser.MulExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntity(AsteroidsProgramParser.EntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link AsteroidsProgramParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(AsteroidsProgramParser.FunctionCallContext ctx);
}
