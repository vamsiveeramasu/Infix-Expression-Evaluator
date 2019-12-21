/* InfixEval.java
 * Vamsi Veeramasu
 * 10/03/2019
 * This class contains the methods to actually evaluate expressions. I followed the pseudocode provided in the project description.
 * I used a for loop to iterate through the whole string, and I tokenized every character as I iterated through the string. I simply skipped 
 * empty spaces, to account for inputs with and without spaces. I also had a few helper methods to avoid code reuse. 
 * 
 */
import java.util.Stack;
public class InfixEval {

	private Stack<Integer> operands = new Stack<Integer>(); // The two stacks required to evaluate infix expressions
	private Stack<Character> operators = new Stack<Character>();
	
	public InfixEval() {
		
	}
	
	public int evaluate(String s) throws DivideByZero, IllegalToken {
		for(int k = 0; k < s.length(); k++) { // For loop to iterate through the whole string input
			
			if(s.charAt(k) == ' ')
				continue;  // skips spaces in the string to continue tokenization of the string
			
			if(isOperand(s.charAt(k))) { //In case the number is greater than 9, so it has more than one digit. This allows me to tokenize the full number.
				StringBuffer str = new StringBuffer(); //Using StringBuffer since StringBuffer isn't immutable like String. 
				while(k < s.length() && isOperand(s.charAt(k))) {
					str.append(s.charAt(k));
					k++; //incrementing k since I'm iterating through the string inside this while loop.
				}
				k--; //Decrementing k by one to keep the current value of k accurate. 
				operands.push(Integer.parseInt(str.toString())); //Using substring instead of charAt to obtain a string object to parse into an integer, so that I can push it onto the integer stack
			}
			
			else if(s.charAt(k) == '(')
				operators.push(s.charAt(k));
			
			else if(s.charAt(k) == ')') { 
				while(operators.peek() != '(') { 
					calculation(operators.pop(), operands.pop(), operands.pop());
				}
				operators.pop();
			}
			
			else if(isOperator(s.charAt(k))) { 
				while(!operators.isEmpty() && precedence(operators.peek(), s.charAt(k))) { 
					if(operators.peek() == '(')
						break;
					calculation(operators.pop(), operands.pop(), operands.pop());
				}
				operators.push(s.charAt(k));
			}
			else if(!isOperand(s.charAt(k)) && !isOperator(s.charAt(k))) { //If I run into a character that isn't 0-9, a mathematical operator, or an open/closed parantheses.//Throwing the exception here to be caught in the P1GUI.java.
				throw new IllegalToken("Invalid Token");
			}
		}
		
		while(operators.isEmpty() == false) { 
			calculation(operators.pop(), operands.pop(), operands.pop());
		}
		return operands.pop(); //The final answer. 
		
	}
	public boolean isOperand(Character c) {	//Simple way to determine if a character is a numeric digit or not. 
		if(c >= '0' && c <= '9') {
			return true;
		}	
		else {
			return false;
		}
	}
	public boolean isOperator(Character c) { // Only testing for the four mathematical operators as I already have an if statement for parantheses.
		if( c == '+' || c == '-' || c == '/' || c == '*') 
			return true;
		else	
			return false;
	}
	public void calculation(char operator, int x, int y) throws DivideByZero{ //Since calling pop() removes a value, and I need to remember the first value since that is what I have first before performing an operation
		if(operator == '*')												      //I constructed this method to accept the two int values as parameters so that I could keep both of them for the calculations. 
			operands.push(y * x);
		if(operator == '/') {
			if(x == 0) 
				throw new DivideByZero("Can not divide by 0"); //Throwing the exception here to be caught in the P1GUI.java.
			operands.push(y / x);
		}
		if(operator == '+')
			operands.push(y + x);
		if(operator == '-')
			operands.push(y - x);
	}
	public boolean precedence(Character x, Character y) { //Determines if the first perameter has equal or higher precedence than the second parameter
		if(x == '*' || x == '/')
			return true;
		else if(x == '(')
			return false;
		else { 
			if(y == '*' || y == '/')
				return false;
			else
				return true;
		}
	}
}
