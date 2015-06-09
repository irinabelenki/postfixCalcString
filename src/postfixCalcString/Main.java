package postfixCalcString;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static Stack<Integer> operandStack = new Stack<Integer>();

	public static void main(String[] args) {
		System.out.println("Enter line in postfix format");
		try {
			String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
			StringTokenizer st = new StringTokenizer(line);
			List<String> elementsList = new ArrayList<String>();
            while (st.hasMoreTokens()) {
            	elementsList.add(st.nextToken());
            }
            Iterator<String> it = elementsList.iterator();
			
			while (it.hasNext()) {
				String element = it.next();
				boolean operandFound = false;
				boolean operationFound = false;
				try {
					int operand = Integer.parseInt(element);
					operandStack.push(operand);
					operandFound = true;
					continue;
				} catch (NumberFormatException nfe) {
					operandFound = false;
				}
				
				String operation = element;
				if (containsOperation(operation)) {
					operationFound = true;
				} else {
					operationFound = false;
				}

				if (!operandFound && !operationFound) {
					System.out.println("Invalid character: " + element);
					break;
				}

				if (operandStack.size() < 2) {
					System.out.println("Number of operands less than two");
					break;
				}

				int result = 0;
				int secondOperand = 0;
				if (operation.equals("+")) {
					result = operandStack.pop() + operandStack.pop();
				}
				else if(operation.equals("*")) {
					result = operandStack.pop() * operandStack.pop();
				}
				else if(operation.equals("-")) {
					secondOperand = operandStack.pop();
					result = operandStack.pop() - secondOperand;
				}
				else if(operation.equals("/")) {
					secondOperand = operandStack.pop();
					if (secondOperand == 0) {
						System.err.println("Cannot divide by zero");
						break;
					}
					result = operandStack.pop() / secondOperand;
				}
				operandStack.push(result);
				System.out.println("Result: " + result);
				operationFound = true;

			}
		} catch (Exception e) {
			System.err.println("Exception:" + e.getMessage());
		}

	}

	public static boolean containsOperation(String operation) {
		if (operation.equals("+") || 
			operation.equals("-") || 
			operation.equals("*") || 
			operation.equals("/")) {
			return true;
		}
		return false;
	}

}
