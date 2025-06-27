import javax.swing.*;
import javax.swing.border.LineBorder;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
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
	GridBagConstraints constraints;

	// textfield
	JTextField textfield;
	JTextField prevText;

	// button groups
	JButton numB[] = new JButton[10];

	JButton operatorB[] = new JButton[6];
	JButton addB, subtractB, multiplyB, divideB, powerB, rootB;

	JButton extraArithB[] = new JButton[4];
	JButton decimalB, openParenB, closeParenB, equalsB;

	JButton extraFunctionB[] = new JButton[3];
	JButton prevAnsB, delB, clearB;

	// extra variables
	private String currentText = "";
	private String prevAns = "0"; // previous answer so user doesn't have to manually type it again
	Font font = new Font("Monospaced", Font.BOLD, 30); // font for all text
	boolean justAnswered = false; // used for updating the prevAns field once user begins new equation

	/**
	 * Creates Calculator object, which will look/act like a handheld calculator
	 */
	public Calculator() {
		// creating frame
		frame = new JFrame("~ Calculator ~");

		// setting up frame
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		// setting up panel and grid
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(178, 252, 229));
		// setting up constraints
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); // space around components
		constraints.fill = GridBagConstraints.BOTH; // stretch to fill cell
		constraints.weightx = 1; // extra space
		constraints.weighty = 1; // extra space

		// making main textfield
		textfield = new JTextField();
		textfield.setFont(font);

		// making textfield which displays previous answer
		prevText = new JTextField();
		prevText.setFont(font);
		prevText.setEditable(false);

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
		multiplyB = new JButton("*");
		divideB = new JButton("/");
		powerB = new JButton("^");
		rootB = new JButton("√");

		// extra arithmetic
		decimalB = new JButton(".");
		openParenB = new JButton("(");
		closeParenB = new JButton(")");
		equalsB = new JButton("=");

		// extra function
		prevAnsB = new JButton("Prev Ans");
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

		extraFunctionB[0] = clearB;
		extraFunctionB[1] = prevAnsB;
		extraFunctionB[2] = delB;

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

		// adding textfields to panel
		// main textfield
		constraints.gridwidth = 5;
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(textfield, constraints);
		// previous answer textfield
		constraints.gridwidth = 3;
		constraints.gridy = 1;
		panel.add(prevText, constraints);

		// adding extra function buttons
		constraints.gridwidth = 2;
		addItem(3, 1, extraFunctionB[0]);
		addItem(3, 2, extraFunctionB[2]);
		constraints.gridwidth = 3;
		addItem(0, 2, extraFunctionB[1]);
		constraints.gridwidth = 1;

		// adding number buttons
		addItem(0, 6, numB[0]);
		addItem(0, 5, numB[1]);
		addItem(1, 5, numB[2]);
		addItem(2, 5, numB[3]);
		addItem(0, 4, numB[4]);
		addItem(1, 4, numB[5]);
		addItem(2, 4, numB[6]);
		addItem(0, 3, numB[7]);
		addItem(1, 3, numB[8]);
		addItem(2, 3, numB[9]);

		// adding operator buttons
		addItem(3, 5, operatorB[0]);
		addItem(4, 5, operatorB[1]);
		addItem(3, 4, operatorB[2]);
		addItem(4, 4, operatorB[3]);
		addItem(3, 3, operatorB[4]);
		addItem(4, 3, operatorB[5]);

		// adding extra arithmetic buttons
		addItem(1, 6, extraArithB[0]);
		addItem(2, 6, extraArithB[1]);
		addItem(3, 6, extraArithB[2]);
		addItem(4, 6, extraArithB[3]);

		// end of setup
		frame.add(panel);
		frame.setVisible(true);
	}

	/**
	 * Begins the program
	 * 
	 * @param args - automatically passed argument
	 */
	public static void main(String[] args) {
		System.out.println("Running...");
		new Calculator();
	}

	/**
	 * Adds a JButton to a specified x and y and given width inside the frame
	 * 
	 * @param width - number of spaces horizontally button takes up
	 * @param x     - horizontal position of item
	 * @param y     - vertical position of item
	 * @param item  - button to add
	 */
	public void addItem(int x, int y, JButton item) {
		// design
		item.setContentAreaFilled(false);
		item.setFocusPainted(false);
		item.setOpaque(true);

		item.setForeground(new Color(122, 244, 253)); // text
		item.setBackground(new Color(21, 134, 138)); // button color
		item.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				item.setBackground(new Color(9, 77, 80)); // button color when pressed
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				item.setBackground(new Color(21, 134, 138)); // button color when released
			}
		});
		item.setBorder(new LineBorder(new Color(15, 76, 78), 3, true));

		// placement
		constraints.gridx = x;
		constraints.gridy = y;
		panel.add(item, constraints);
	}

	/**
	 * Handles action events triggered by user interaction. Reacts accordingly when
	 * buttons are clicked
	 * 
	 * @param e - ActionEvent triggered by a user interaction
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// update previous answer once user begins new equation
		if (justAnswered) {
			prevAns = currentText;
			prevText.setText(prevAns);
			textfield.setText("");
			justAnswered = false;
		}

		// clear button is pressed
		if (e.getSource() == extraFunctionB[0]) {
			textfield.setText("");
		}
		// previous answer button is pressed
		else if (e.getSource() == extraFunctionB[1]) {
			textfield.replaceSelection(prevAns);
		}
		// delete button is pressed
		else if (e.getSource() == extraFunctionB[2]) {
			if (!textfield.getText().equals("")) { // throws error if not checked
				textfield.setText(currentText.substring(0, currentText.length() - 1));
			}
		}

		// arithmetic/symbol button is pressed
		else if (e.getSource() != extraArithB[3]) {
			textfield.replaceSelection(((JButton) e.getSource()).getText());
		}
		// equals button is pressed
		else {
			try {
				// evaluate expression
				Expression equation = new ExpressionBuilder((textfield.getText()).replace("√", "sqrt")).build();
				String answer = String.valueOf(equation.evaluate());
				// update fields
				textfield.setText(answer);
				justAnswered = true;
			} catch (Exception ex) {
				textfield.setText("ERROR");
			}
		}

		currentText = textfield.getText();
	}

}
