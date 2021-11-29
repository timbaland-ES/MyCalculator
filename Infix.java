package einsendeaufgaben;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Infix {
	public static String toPostfixString(String infixString) {
		var operators = new HashSet<String>(Arrays.asList("+","-","*","/"));
		var precedences = new HashMap<String,Integer>();
		precedences.put("(",0);
		precedences.put("+",1);
		precedences.put("-",1);
		precedences.put("*",2);
		precedences.put("/",2);
		
		var operatorStack = new Stack<String>();
		String result="";
		String regex = "[0-9]+|\\+|\\-|\\*|/|\\)|\\(";
		var pattern = Pattern.compile(regex);
		var matcher = pattern.matcher(infixString);
		
		while(matcher.find()) {
			String token = matcher.group();
			if (operators.contains(token)) {
				while(!operatorStack.empty()&&precedences.get(operatorStack.peek()
						) >= precedences.get(token)) {
						result += operatorStack.pop()+" ";
				}
				operatorStack.push(token);
			} else if (token.equals("(")) {
			  operatorStack.push(token);
			} else if (token.equals(")")) {
			  if (!operatorStack.empty()) {
				  String operator;
				  while (!(operator=operatorStack.pop()).equals("(")) {
					  result += operator + " ";
				  }
			  }
			} else {
				result += token + " ";
			}
		}
		while (!operatorStack.empty()) {
			result += operatorStack.pop() + " ";
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(toPostfixString("(1+2)*(3+4)"));
		System.out.println(toPostfixString("55+3*2-6/3+123*2"));
		//System.out.println(Postfix.evaluatePostfixString(toPostfixString("55+3*2-6/3+123*2")));
	}

}
