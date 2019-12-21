/*P1GUI.java
 * Vamsi Veeramasu
 * 11/03/2019
 * This file contains the user interface class, as well as the main method to execute the program. It consists of 3 layouts in a GridLayout.
 * Each of the 3 layouts inside the GridLayout are their own panels containing 1-2 objects. That is how I decided to represent the GUI. 
 * 
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class P1GUI extends JFrame {

	private JLabel enter = new JLabel("Enter Infix Expression");
	private JTextField input = new JTextField("");
	private JButton evaluate = new JButton("Evaluate");
	private JLabel result = new JLabel("Result");
	private JTextField displayResult = new JTextField("");
	
	public P1GUI() {
		super("Infix Expression Calculator");
		setSize(300,200);
		setLayout(new GridLayout(3,1, 0, 5));
		
		displayResult.setEditable(false);
		displayResult.setBackground(getBackground());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(0, 2));
		topPanel.add(enter);
		topPanel.add(input);
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new FlowLayout());
		middlePanel.add(evaluate);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0, 2));
		bottomPanel.add(result);
		bottomPanel.add(displayResult);
		
		add(topPanel); // Adding the panels in the right order in order to mimic the GUI from the project description 
		add(middlePanel);
		add(bottomPanel);
		
		InfixEval eval = new InfixEval(); //Instantiating an object of InfixEval so that I can use that class' methods in this file. 
		
		
		evaluate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				try {
					
					result = eval.evaluate(input.getText()); // Storing the result in a variable so I can keep access to it to write to a file.
					displayResult.setText("" + eval.evaluate(input.getText()));
				} 
				catch (DivideByZero e1) { // Catching the two custom exceptions we were instructed to catch, with JOptionPanes for each one.
					JOptionPane.showMessageDialog(null, "Error: Can not divide by zero.");
					//e1.printStackTrace();
				} catch (IllegalToken e2) {
					JOptionPane.showMessageDialog(null, "Error: Invalid token. All characters must be 0-9 or valid operators.");
					//e2.printStackTrace();
				}
				try {
					File output = new File("Results.txt"); //Writing the input and output results to Results.txt
					PrintWriter infile = new PrintWriter(output);
					infile.write("Input: " + input.getText() + " Output: " + result + "\n");
					infile.flush();
					infile.close();
				//	System.out.println(output.filePath());
					//infile.write("" + result);
				}
				catch(FileNotFoundException e5){ // In case the file isn't found. 
					
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void display() {
		setVisible(true);
	}
	public static void main(String[] args) { // All that needs to happen for the program to run is that the GUI loads on the user's screen. 
		// TODO Auto-generated method stub
		P1GUI gui = new P1GUI();
		gui.display();
	}
}
