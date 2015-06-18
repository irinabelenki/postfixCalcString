package postfixCalcString;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static Stack<Integer> operandStack = new Stack<Integer>();	
	
	public enum OPERATION { ADD, SUBTRACT, MULTIPLY, DIVIDE, ILLEGAL_OPERATION };

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
				
				OPERATION operation = getOperation(element);
				
				if (operandStack.size() < 2) {
					System.out.println("Number of operands less than two");
					break;
				}

				int result = 0;
				int secondOperand = 0;
				switch(operation) {
					case ADD:
						result = operandStack.pop() + operandStack.pop();
						break;
					case MULTIPLY:
						result = operandStack.pop() * operandStack.pop();
						break;
					case SUBTRACT:
						secondOperand = operandStack.pop();
						result = operandStack.pop() - secondOperand;
						break;
					case DIVIDE:
						secondOperand = operandStack.pop();
						if (secondOperand == 0) {
							throw new Exception("Cannot divide by zero");
						}
						result = operandStack.pop() / secondOperand;
						break;
					case ILLEGAL_OPERATION:
						throw new Exception("Illegal operaton: " + element);
						
				}

				operandStack.push(result);
				System.out.println("Result: " + result);
			}
		} 
		catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}

	public static OPERATION getOperation(String operation) {
		if(operation.equals("+")) {
			return OPERATION.ADD;
		}
		else if(operation.equals("-")) {
			return OPERATION.SUBTRACT;
		}
		else if(operation.equals("*")) {
			return OPERATION.MULTIPLY;
		}
		else if(operation.equals("/")) {
			return OPERATION.DIVIDE;
		}
		return OPERATION.ILLEGAL_OPERATION;
	}

}
