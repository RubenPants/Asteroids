package asteroids.part3.programs.internal;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.internal.generated.AsteroidsProgramBaseVisitor;
import asteroids.part3.programs.internal.generated.AsteroidsProgramLexer;
import asteroids.part3.programs.internal.generated.AsteroidsProgramParser;
import asteroids.part3.programs.internal.generated.AsteroidsProgramParser.*;

public class ParserVisitor<E, S, F, P> extends AsteroidsProgramBaseVisitor<Void> {

    @Override
    public Void visitProgram(ProgramContext ctx) {
      FunctionDefinitionVisitor functionDefVisitor = new FunctionDefinitionVisitor();
      functions = ctx.funcdef.stream().map(funcdef -> functionDefVisitor.visit(funcdef))
          .collect(Collectors.toList());
      main = statementVisitor.visit(ctx.programBody);
      assert main != null;
      return null;
    }
    

  private class FunctionDefinitionVisitor extends AsteroidsProgramBaseVisitor<F> {

    @Override
    public F visitFunctiondef(AsteroidsProgramParser.FunctiondefContext ctx) {
      return getFactory().createFunctionDefinition(ctx.funcname.getText(),
          statementVisitor.visit(ctx.body), toSourceLocation(ctx));
    }
  }

  private class StatementVisitor extends AsteroidsProgramBaseVisitor<S> {

    @Override
    public S visitAssignmentStatement(AssignmentStatementContext ctx) {
      return getFactory().createAssignmentStatement(ctx.variableName.getText(), expressionVisitor.visit(ctx.value),
          toSourceLocation(ctx));
    }

    @Override
    public S visitWhileStatement(WhileStatementContext ctx) {
      return getFactory().createWhileStatement(expressionVisitor.visit(ctx.condition), statementVisitor.visit(ctx.body),
          toSourceLocation(ctx));
    }

    @Override
    public S visitBreakStatement(AsteroidsProgramParser.BreakStatementContext ctx) {
      return getFactory().createBreakStatement(toSourceLocation(ctx));
    }

    @Override
    public S visitReturnStatement(AsteroidsProgramParser.ReturnStatementContext ctx) {
      return getFactory().createReturnStatement(expressionVisitor.visit(ctx.value), toSourceLocation(ctx));
    }

    @Override
    public S visitIfStatement(IfStatementContext ctx) {
      S ifBody = statementVisitor.visit(ctx.ifbody);
      S elseBody = null;
      if (ctx.elsebody != null)
        elseBody = statementVisitor.visit(ctx.elsebody);
      return getFactory().createIfStatement(expressionVisitor.visit(ctx.condition), ifBody, elseBody, toSourceLocation(ctx));
    }

    @Override
    public S visitPrintStatement(PrintStatementContext ctx) {
      return getFactory().createPrintStatement(expressionVisitor.visit(ctx.value), toSourceLocation(ctx));
    }

    @Override
    public S visitStatementSequence(StatementSequenceContext ctx) {
      if (ctx.stmts.size() != 1) {
        return getFactory().createSequenceStatement(ctx.stmts.stream().map(this::visit).collect(Collectors.toList()),
            toSourceLocation(ctx));
      } else {
        return visit(ctx.stmts.get(0));
      }
    }

    @Override
    public S visitThrustOnAction(ThrustOnActionContext ctx) {
      return getFactory().createThrustOnStatement(toSourceLocation(ctx));
    }

    @Override
    public S visitThrustOffAction(ThrustOffActionContext ctx) {
      return getFactory().createThrustOffStatement(toSourceLocation(ctx));
    }

    @Override
    public S visitTurnAction(TurnActionContext ctx) {
      return getFactory().createTurnStatement(expressionVisitor.visit(ctx.angle), toSourceLocation(ctx));
    }

    @Override
    public S visitFireAction(FireActionContext ctx) {
      return getFactory().createFireStatement(toSourceLocation(ctx));
    }

    @Override
    public S visitSkipAction(SkipActionContext ctx) {
      return getFactory().createSkipStatement(toSourceLocation(ctx));
    }

  }

  private class ExpressionVisitor extends AsteroidsProgramBaseVisitor<E> {

    @Override
    public E visitNumberExpression(NumberExpressionContext ctx) {
      return getFactory().createDoubleLiteralExpression(Double.parseDouble(ctx.number.getText()), toSourceLocation(ctx));
    }

    @Override
    public E visitReadVariableExpression(ReadVariableExpressionContext ctx) {
      return getFactory().createReadVariableExpression(ctx.variable.getText(), toSourceLocation(ctx));
    }

    @Override
    public E visitReadParameterExpression(ReadParameterExpressionContext ctx) {
      return getFactory().createReadParameterExpression(ctx.parameter.getText(), toSourceLocation(ctx));
    }

    @Override
    public E visitEntity(AsteroidsProgramParser.EntityContext ctx) {
      SourceLocation loc = toSourceLocation(ctx);
      switch (ctx.start.getType()) {
      case AsteroidsProgramLexer.NULL:
        return getFactory().createNullExpression(loc);
      case AsteroidsProgramLexer.SELF:
        return getFactory().createSelfExpression(loc);
      case AsteroidsProgramLexer.SHIP:
        return getFactory().createShipExpression(loc);
      case AsteroidsProgramLexer.ASTEROID:
        return getFactory().createAsteroidExpression(loc);
      case AsteroidsProgramLexer.PLANETOID:
        return getFactory().createPlanetoidExpression(loc);
      case AsteroidsProgramLexer.BULLET:
        return getFactory().createBulletExpression(loc);
      case AsteroidsProgramLexer.PLANET:
        return getFactory().createPlanetExpression(loc);
      case AsteroidsProgramLexer.ANY:
        return getFactory().createAnyExpression(loc);
      default:
        throw new IllegalArgumentException("Unknown entity: " + ctx.getText());
      }
    }

    @Override
    public E visitParenExpression(ParenExpressionContext ctx) {
      return expressionVisitor.visit(ctx.subExpr);
    }

    @Override
    public E visitChangeSignExpression(ChangeSignExpressionContext ctx) {
      return getFactory().createChangeSignExpression(expressionVisitor.visit(ctx.operand), toSourceLocation(ctx));
    }

    @Override
    public E visitAddExpression(AsteroidsProgramParser.AddExpressionContext ctx) {
      return getFactory().createAdditionExpression(expressionVisitor.visit(ctx.leftAdd), expressionVisitor.visit(ctx.rightAdd),
          toSourceLocation(ctx));
    }

    @Override
    public E visitMulExpression(AsteroidsProgramParser.MulExpressionContext ctx) {
      return getFactory().createMultiplicationExpression(expressionVisitor.visit(ctx.leftMul), expressionVisitor.visit(ctx.rightMult),
          toSourceLocation(ctx));
    }

    @Override
    public E visitFunctionCall(AsteroidsProgramParser.FunctionCallContext ctx) {
      return getFactory().createFunctionCallExpression(ctx.funcName.getText(),
          ctx.actualArgs.stream().map(this::visit).collect(Collectors.toList()), toSourceLocation(ctx));
    }

    @Override
    public E visitNotExpression(NotExpressionContext ctx) {
      return getFactory().createNotExpression(expressionVisitor.visit(ctx.expr), toSourceLocation(ctx));
    }

    @Override
    public E visitSqrtExpression(SqrtExpressionContext ctx) {
      return getFactory().createSqrtExpression(expressionVisitor.visit(ctx.expr), toSourceLocation(ctx));
    }

    @Override
    public E visitGetXExpression(GetXExpressionContext ctx) {
      return getFactory().createGetXExpression(expressionVisitor.visit(ctx.expr), toSourceLocation(ctx));
    }

    @Override
    public E visitGetYExpression(GetYExpressionContext ctx) {
      return getFactory().createGetYExpression(expressionVisitor.visit(ctx.expr), toSourceLocation(ctx));
    }

    @Override
    public E visitGetVXExpression(GetVXExpressionContext ctx) {
      return getFactory().createGetVXExpression(expressionVisitor.visit(ctx.expr), toSourceLocation(ctx));
    }

    @Override
    public E visitGetVYExpression(GetVYExpressionContext ctx) {
      return getFactory().createGetVYExpression(expressionVisitor.visit(ctx.expr), toSourceLocation(ctx));
    }

    @Override
    public E visitGetRadiusExpression(GetRadiusExpressionContext ctx) {
      return getFactory().createGetRadiusExpression(expressionVisitor.visit(ctx.expr), toSourceLocation(ctx));
    }

    @Override
    public E visitGetDirectionExpression(GetDirectionExpressionContext ctx) {
      return getFactory().createGetDirectionExpression(toSourceLocation(ctx));
    }

    @Override
    public E visitEqualExpression(AsteroidsProgramParser.EqualExpressionContext ctx) {
      return getFactory().createEqualityExpression(expressionVisitor.visit(ctx.leftEq), expressionVisitor.visit(ctx.rightEq),
          toSourceLocation(ctx));
    }

    @Override
    public E visitLessThanExpression(AsteroidsProgramParser.LessThanExpressionContext ctx) {
      return getFactory().createLessThanExpression(expressionVisitor.visit(ctx.leftLt), expressionVisitor.visit(ctx.rightLt),
          toSourceLocation(ctx));
    }

  }

  private final IProgramFactory<E, S, F, P> factory;

  private final StatementVisitor statementVisitor = new StatementVisitor();
  private final ExpressionVisitor expressionVisitor = new ExpressionVisitor();

  private S main;
  private List<F> functions;

  public ParserVisitor(IProgramFactory<E, S, F, P> factory) {
    this.factory = factory;
  }

  private SourceLocation toSourceLocation(ParserRuleContext ctx) {
    int line = ctx.getStart().getLine();
    int column = ctx.getStart().getCharPositionInLine();
    return new SourceLocation(line, column);
  }

  public IProgramFactory<E, S, F, P> getFactory() {
    return factory;
  }

  public int toInt(Token z) {
    return Integer.parseInt(z.getText());
  }

  public S getMain() {
    return main;
  }

  public List<F> getFunctions() {
    return functions;
  }

}
