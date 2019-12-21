/* DivideByZero.java
 * Vamsi Veeramasu
 * 10/03/2019
 * This class exists solely so that I can throw this custom exception in the event that the program attempts
 * to divide by zero.
 */
public class DivideByZero extends Exception{
	DivideByZero(String s){
		super(s);
	}
	
	
}
