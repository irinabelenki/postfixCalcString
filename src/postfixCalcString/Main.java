package postfixCalcString;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static Stack<Integer> operandStack = new Stack<Integer>();	
	
	public enum Operation {		 
		PLUS("+"), 
		MINUS("-"), 
		MULTIPLY("*"), 
		DIVIDE("/");
	 
		private String content;
	 
		private Operation(String content) {
			this.content = content;
		}
	 
		public String getContent() {
			return content;
		}	 
	}

	public static void main(String[] args) {
		System.out.println("Enter line in postfix format");
		
		try {
			String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
			StringTokenizer elementsList = new StringTokenizer(line);
			
			while (elementsList.hasMoreTokens()) {
				String element = elementsList.nextToken();
				try {
					int operand = Integer.parseInt(element);
					operandStack.push(operand);
					continue;
				} catch (NumberFormatException nfe) {
				}
				
				if (!containsOperation(element)) {
					System.out.println("Invalid character: " + element);
					break;
				}

				if (operandStack.size() < 2) {
					System.out.println("Number of operands less than two");
					break;
				}

				int result = 0;
				int secondOperand = 0;
				if (element.equals("+")) {
					result = operandStack.pop() + operandStack.pop();
				}
				else if(element.equals("*")) {
					result = operandStack.pop() * operandStack.pop();
				}
				else if(element.equals("-")) {
					secondOperand = operandStack.pop();
					result = operandStack.pop() - secondOperand;
				}
				else if(element.equals("/")) {
					secondOperand = operandStack.pop();
					if (secondOperand == 0) {
						System.err.println("Cannot divide by zero");
						break;
					}
					result = operandStack.pop() / secondOperand;
				}
				operandStack.push(result);
				System.out.println("Result: " + result);
			}
		} 
		catch (Exception e) {
			System.err.println("Exception:" + e.getMessage());
		}
	}

	public static boolean containsOperation(String operation) {
		for(Operation value : Operation.values()) {
			if (value.getContent().equals(operation)) {
				return true;
			}
		}
		return false;
	}

}
