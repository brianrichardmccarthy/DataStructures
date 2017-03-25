/**
 * The main part of the calculator doing the calculations.
 * 
 * @author M. Kolling
 * @version 0.1 (incomplete)
 */
public class CalcEngine {
	private Double displayValue;
	private String postfix;

	/**
	 * Create a CalcEngine instance. Initialise its state so that it is ready
	 * for use.
	 */
	public CalcEngine() {
		displayValue = 0.0;
		postfix = "";
	}

	/**
	 * The 'C' (clear) button was pressed.
	 */
	public void clear() {
		// reset instance variables
		displayValue = 0.0;
		postfix = "";
	}

	/**
	 * Return the title of this calculation engine.
	 */
	public String getTitle() {
		return ("My Calculator");
	}

	/**
	 * Return the author of this engine. This string is displayed as it is, so
	 * it should say something like "Written by H. Simpson".
	 */
	public String getAuthor() {
		return ("Joe Daly");
	}

	/**
	 * Return the version number of this engine. This string is displayed as it
	 * is, so it should say something like "Version 1.1".
	 */
	public String getVersion() {
		return ("Ver. 1.0");
	}

	/**
	 * Return the value that should currently be displayed on the calculator
	 * display.
	 */
	public String getDisplayValue() {
		return (displayValue.toString());
	}

	/**
	 * Accessor for postfix
	 * 
	 * @return (String)
	 */
	public String getPostfix() {
		return postfix;
	}

	/**
	 * A number button was pressed. Do whatever you have to do to handle it. The
	 * number value of the button is given as a parameter.
	 */
	public void numberPressed(int number) {
		displayValue = displayValue * 10 + number;
	}

	/**
	 * The '=' button was pressed.
	 */
	public void equals(String infix) {

		// don't go furter with the calculations if brackets don't match
		if (!bracesMatch(infix)) {
			postfix = "Brackets don't match";
			displayValue = 0.0;
			return;
		}

		// infix to postfix algorithm
		shuntingYard(infix);

		// calculate the expression
		evaluateExpression();
	}

	private void evaluateExpression() {
		CustomStack<Double> operands = new CustomStack<>();
		String[] postfixSplit = postfix.split("\\s+");
		Double operand1, operand2;

		for (int x = 0; x < postfixSplit.length; x++) {
			if (Character.isDigit(postfixSplit[x].charAt(0))
					|| (postfixSplit[x].length() > 1 && Character.isDigit(postfixSplit[x].charAt(1)))) {
				operands.push(Double.parseDouble(postfixSplit[x]));
			} else {
				switch (postfixSplit[x]) {
				case "sin":
					operands.push(Math.sin(operands.pop()));
					break;
				case "cos":
					operands.push(Math.cos(operands.pop()));
					break;
				case "tan":
					operands.push(Math.tan(operands.pop()));
					break;
				case "sqrt":
					operands.push(Math.sqrt(operands.pop()));
					break;
				case "^":
					operand1 = operands.pop();
					operand2 = operands.pop();
					operands.push(Math.pow(operand2, operand1));
					break;
				case "*":
					operands.push((operands.pop() * operands.pop()));
					break;
				case "/":
					operands.push((operands.pop() / operands.pop()));
					break;
				case "+":
					operands.push((operands.pop() + operands.pop()));
					break;
				default:
					operand1 = operands.pop();
					operand2 = operands.pop();
					operands.push((operand2 - operand1));
					break;
				}
			}
		}

		if (operands.size() == 1) {
			displayValue = operands.pop();
		} else {
			postfix = "There wasn't enough operators";
			displayValue = 0.0;
		}

	}

	/**
	 * Sets the postfix string to the postfix value of the given expression
	 * 
	 * @param infix
	 *            (String)
	 */
	public void shuntingYard(String infix) {

		// split infix by spaces
		String[] process = infix.split("\\s+");
		CustomStack<String> operator = new CustomStack<>();

		for (int x = 0; x < process.length; x++) {
			String nextString = process[x];

			if (nextString.equals("PI")) {
				postfix += Math.PI + " ";
			} else if (Character.isDigit(nextString.charAt(0))
					|| (nextString.length() > 1 && Character.isDigit(nextString.charAt(1)))) {
				postfix += nextString + " ";
			} else {
				// else next string is operator
				switch (nextString) {
				case "^":
					operator.push(nextString);
					break;
				case "+":
				case "-":
				case "*":
				case "/":
				case "sin":
				case "cos":
				case "tan":
				case "sqrt":
					while (!operator.isEmpty() && hasPrecedenced(operator.peek(), nextString)) {
						if (!operator.peek().equals("(")) {
							postfix += operator.pop() + " ";
						} else {
							operator.pop();
							break;
						}
					}
					operator.push(nextString);
					break;
				case "(":
					operator.push(nextString);
					break;
				case ")":
					while (!operator.isEmpty() && !operator.peek().equals("(")) {
						postfix += operator.pop() + " ";
					}
					if (!operator.isEmpty() && operator.peek().equals("(")) {
						operator.pop();
					}
					break;
				}
			}

		}

		while (!operator.isEmpty()) {
			postfix += operator.pop() + " ";
		}

	}

	/**
	 * CHecks which of the two given operators has precedenced
	 * 
	 * @param topOfStackCharacter
	 *            (char)
	 * @param nextCharacter
	 *            (char)
	 * @return (boolean)
	 */
	private boolean hasPrecedenced(String topOfStackCharacter, String nextCharacter) {
		return getPrecedenced(topOfStackCharacter) >= getPrecedenced(nextCharacter);
	}

	/**
	 * 
	 * Returns a number based on the operator in a given string
	 * 
	 * @param operator
	 *            (String)
	 * @return (int)
	 */
	private int getPrecedenced(String operator) {
		if (operator == null) {
			return 0;
		}

		switch (operator) {
		case "(":
		case ")":
			return 5;
		case "sin":
		case "cos":
		case "tan":
		case "sqrt":
			return 4;
		case "^":
			return 3;
		case "*":
		case "/":
			return 2;
		default:
			return 1;
		}
	}

	/**
	 * 
	 * returns if the open bracket is the same type as the closing one.
	 * 
	 * @param open
	 *            (char)
	 * @param close
	 *            (char)
	 * @return (boolean)
	 */
	private boolean isMatch(char open, char close) {
		return (open == '(' && close == ')') || (open == '{' && close == '}') || (open == '[' && close == ']');
	}

	/**
	 * Checks if all the brackets match.
	 * 
	 * @param line
	 *            (String)
	 * @return boolean
	 */
	private boolean bracesMatch(String line) {
		boolean isBalanced = true;
		CustomStack<Character> balance = new CustomStack<>();

		/*
		 * loop through all the characters of the string if it's an open bracket
		 * push it onto the stack else if it's a closing bracket check if the
		 * brackets match
		 */
		for (int x = 0; x < line.length() && isBalanced; x++) {
			switch (line.charAt(x)) {
			case '(':
			case '{':
			case '[':
				balance.push(line.charAt(x));
				break;
			case ']':
			case '}':
			case ')':
				isBalanced = isMatch(balance.pop(), line.charAt(x));
				break;
			default:
				continue;
			}
		}

		// if there is brackets left on the stack
		if (!balance.isEmpty()) {
			isBalanced = false;
		}

		return isBalanced;
	}

}