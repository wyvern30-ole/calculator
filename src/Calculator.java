import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class for making a calculator.
 * Any variable ending with B means it's a button
 */
public class Calculator implements ActionListener {

	// ui
	JFrame frame;
	JPanel panel;

	// textfield
	JTextField tf;

	// button groups
	JButton numB[] = new JButton[10];

	JButton operatorB[] = new JButton[6];
	JButton addB, subtractB, multiplyB, divideB, powerB, rootB;

	JButton extraArithB[] = new JButton[4];
	JButton decimalB, openParenB, closeParenB, equalsB;

	JButton extraFunctionB[] = new JButton[3];
	JButton prevAnsB, delB, clearB;

	// extra variables
	private int prevAns = 0; // previous answer so user doesn't have to manually type it again
	Font font = new Font("Monospaced", Font.BOLD, 30); // font for all text

	public Calculator() {
		// creating frame
		frame = new JFrame("Calculator");

		// setting up panel
		// panel = new JPanel();
		// panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		// panel.setLayout(new GridLayout(0,1));

		// setting up frame
		frame.setSize(400, 550);
		// frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);

		// making textfield
		tf = new JTextField();
		tf.setBounds(20, 20, 350, 50);
		tf.setFont(font);

		// making buttons

		// numbers
		for (int i = 0; i < 10; i++) {
			numB[i] = new JButton(String.valueOf(i));
			numB[i].addActionListener(this);
			numB[i].setFont(font);
			numB[i].setFocusable(false);
		}

		// operators
		addB = new JButton("+");
		subtractB = new JButton("-");
		multiplyB = new JButton("x");
		divideB = new JButton("/");
		powerB = new JButton("^");
		rootB = new JButton("√‾");

		// extra arithmetic
		decimalB = new JButton(".");
		openParenB = new JButton("(");
		closeParenB = new JButton(")");
		equalsB = new JButton("=");

		// extra function
		prevAnsB = new JButton("Previous");
		delB = new JButton("Delete");
		clearB = new JButton("Clear");

		// adding buttons to arrays
		operatorB[0] = addB;
		operatorB[1] = subtractB;
		operatorB[2] = multiplyB;
		operatorB[3] = divideB;
		operatorB[4] = powerB;
		operatorB[5] = rootB;

		extraArithB[0] = decimalB;
		extraArithB[1] = openParenB;
		extraArithB[2] = closeParenB;
		extraArithB[3] = equalsB;

		extraFunctionB[0] = prevAnsB;
		extraFunctionB[1] = delB;
		extraFunctionB[2] = clearB;

		// setting up buttons functionality/look
		for (int i = 0; i < 6; i++) {
			operatorB[i].addActionListener(this);
			operatorB[i].setFont(font);
			operatorB[i].setFocusable(false);
		}
		for (int i = 0; i < 4; i++) {
			extraArithB[i].addActionListener(this);
			extraArithB[i].setFont(font);
			extraArithB[i].setFocusable(false);
		}
		for (int i = 0; i < 3; i++) {
			extraFunctionB[i].addActionListener(this);
			extraFunctionB[i].setFont(font);
			extraFunctionB[i].setFocusable(false);
		}

		// end of setup
		frame.add(tf);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		System.out.println("Running...");
		// System.out.println(Math.pow(9, 1.0 / 2));
		new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
