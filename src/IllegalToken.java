/* IllegalToken.java
 * Vamsi Veeramasu
 * 10/03/2019
 * This class exists solely so that I can throw this custom exception in the event I run into invalid tokens.
 * 
 */
public class IllegalToken extends Exception{ //How to tie in exceptions with main and throw exceptions to throw up the right JPanes in main?
	
	IllegalToken(String s){
		super(s);
	}
	
	
}
