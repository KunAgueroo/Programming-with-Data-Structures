package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple
 * arithmetic expressions.
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

  private final StackInterface<Operand<Integer>> stack;

  /** Constructs an {@link ArithPostfixEvaluator} */
  public ArithPostfixEvaluator() {
    // TODO Initialize to your LinkedStack
    stack = new LinkedStack<>();
  }

  /** {@inheritDoc} */
  @Override
  public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
    ArithPostfixParser parser = new ArithPostfixParser(expr);
    while (!stack.isEmpty()) {
      stack.pop();
    }
    for (Token<Integer> token : parser) {
      Type type = token.getType();
      switch (type) {
      case OPERAND:
        // TODO What do we do when we see an operand?
        Operand<Integer> oper = token.getOperand();
        stack.push(oper);
        break;
      case OPERATOR:
        // TODO What do we do when we see an operator?
        Operator<Integer> opera = token.getOperator();
        int j = opera.getNumberOfArguments();
        if (j > stack.size()) {
          throw new IllegalPostfixExpressionException("Too less");
        }
        else{
        for (int i = j - 1; i >= 0; i--) {
          opera.setOperand(i, stack.pop());
        }
      }
        stack.push(opera.performOperation());
        break;
      default:
        throw new IllegalStateException("Parser returned an invalid Type: " + type);
      }
    }
    // TODO What do we return?
    if (stack.size() != 1) {
      throw new IllegalPostfixExpressionException("Too less");
    }
    return stack.top().getValue();
  }
}
