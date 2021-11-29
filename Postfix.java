package einsendeaufgaben;
import java.util.Stack;

public class Postfix {
	public static double evaluatePostfixString(String postfixString) {
		var stack = new Stack<Double>();
		for (String token : postfixString.split(" ")) {
			switch(token) {
			case "+":
				stack.push(stack.pop() + stack.pop());
				break;
			case "-":
				double subtrahend = stack.pop();
				stack.push(stack.pop() - subtrahend);
				break;
			case "*":
				stack.push(stack.pop() * stack.pop());
				break;
			case "/":
				double divisor = stack.pop();
				stack.push(stack.pop() / divisor);
				break;
			default:
				stack.push(Double.parseDouble(token));
				break;
			}
		}
		return stack.pop();
	}
	
	public static void main(String[] args) {
		System.out.println(evaluatePostfixString("1 2 + 3 4 + *"));
		System.out.println(evaluatePostfixString("55 3 2 * + 6 3 / - 123 2 * + "));
		System.out.println(evaluatePostfixString("55 3 2 *+6 3 /-123 2 * + "));
	}

}
