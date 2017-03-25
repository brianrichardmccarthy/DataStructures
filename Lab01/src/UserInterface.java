import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * A graphical user interface for the calculator. No calculation is being done
 * here. This class is responsible just for putting up the display on screen. It
 * then refers to the "CalcEngine" to do all the real work.
 * 
 * @author Michael Kolling
 * @version 31 July 2000
 */
public class UserInterface implements ActionListener {
	private CalcEngine calc;
	private boolean showingAuthor;

	private JFrame frame;
	private JTextField displayAns, displayPostfix, displayInfix;
	private JLabel status;

	// infix is the calculation in a + b form
	// previousButtonPressed is used for making a number negative
	// see actionPerformed menthod
	private String infix = "", previousButtonPressed = "";

	/**
	 * Create a user interface for a given calcEngine.
	 */
	public UserInterface(CalcEngine engine) {
		calc = engine;
		showingAuthor = true;
		makeFrame();
		frame.setVisible(true);
	}

	/**
	 * Make this interface visible again. (Has no effect if it is already
	 * visible.)
	 */
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	/**
	 * Make the frame for the user interface.
	 */
	private void makeFrame() {
		frame = new JFrame(calc.getTitle());

		// clean up resources
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.setLayout(new BorderLayout(8, 8));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		// used to show infix, postfix and ans
		JPanel outputPanel = new JPanel(new GridLayout(3, 1));

		// infix, postfix and ans display
		displayAns = new JTextField();
		displayPostfix = new JTextField();
		displayInfix = new JTextField();

		// prevent the user from editing text fields
		displayInfix.setEditable(false);
		displayPostfix.setEditable(false);
		displayAns.setEditable(false);

		// add text field
		outputPanel.add(displayAns);
		outputPanel.add(displayInfix);
		outputPanel.add(displayPostfix);

		contentPane.add(outputPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel(new GridLayout(7, 4));

		// create buttons
		addButton(buttonPanel, "C");
		addButton(buttonPanel, "(");
		addButton(buttonPanel, ")");
		addButton(buttonPanel, "/");

		addButton(buttonPanel, "Sin");
		addButton(buttonPanel, "Cos");
		addButton(buttonPanel, "Tan");
		addButton(buttonPanel, "− / +");

		addButton(buttonPanel, "7");
		addButton(buttonPanel, "8");
		addButton(buttonPanel, "9");
		addButton(buttonPanel, "*");

		addButton(buttonPanel, "4");
		addButton(buttonPanel, "5");
		addButton(buttonPanel, "6");
		addButton(buttonPanel, "+");

		addButton(buttonPanel, "1");
		addButton(buttonPanel, "2");
		addButton(buttonPanel, "3");
		addButton(buttonPanel, "-");

		addButton(buttonPanel, "0");
		addButton(buttonPanel, ".");
		addButton(buttonPanel, "^");
		addButton(buttonPanel, "=");

		addButton(buttonPanel, "sqrt");
		addButton(buttonPanel, "PI");

		contentPane.add(buttonPanel, BorderLayout.CENTER);

		status = new JLabel(calc.getAuthor());
		contentPane.add(status, BorderLayout.SOUTH);

		frame.pack();
	}

	/**
	 * Add a button to the button panel.
	 */
	private void addButton(Container panel, String buttonText) {
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		panel.add(button);
	}

	/**
	 * Update the interface display to show the current value of the calculator.
	 */
	private void redisplay() {
		// update calcs' infix and redisplay ans, infix, post
		// ans and postfix aren't done until user hits ans
		displayAns.setText("Answer: " + calc.getDisplayValue());
		displayInfix.setText("Infix: " + infix);
		displayPostfix.setText("Postfix: " + calc.getPostfix());
	}

	/**
	 * Toggle the info display in the calculator's status area between the
	 * author and version information.
	 */
	@SuppressWarnings("unused")
	private void showInfo() {
		if (showingAuthor) {
			status.setText(calc.getVersion());
		} else {
			status.setText(calc.getAuthor());
		}
		showingAuthor = !showingAuthor;
	}

	/**
	 * An interface action has been performed. Find out what it was and handle
	 * it.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		// calculate expression
		if (command.equals("=")) {
			calc.equals(infix);
		} else if (command.equals("Sin") || command.equals("Cos") || command.equals("Tan") || command.equals("sqrt")) {
			// else if command is a meth function add to infix
			infix += command.toLowerCase() + " ( ";
		} else if (command.equals("C")) {
			// else if Clear button was pressed reset infix, postfix, ans
			infix = "";
			calc.clear();
		} else if (command.equals("− / +")) {
			// else if negative button was pressed

			/*
			 * if previousButtonPressed length is one or more and its first
			 * character is a number or previousButtonPressed was PI to negatate
			 * this added multiplication first then minus one to infix. else
			 * negate the next number by adding -1 then multiplication to infix.
			 */
			infix += (previousButtonPressed.length() >= 1 && Character.isDigit(previousButtonPressed.charAt(0))
					|| previousButtonPressed.equals("PI")) ? " * -1" : "-1 * ";
		} else {
			if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
				// add number or decimal point with no spaces to infix
				infix += command;
			} else {
				// else add operator or PI to infix with spaces at the end and
				// if its not the first character add space before as weel
				infix += (infix.length() >= 1) ? " " + command + " " : command + " ";
			}
		}

		previousButtonPressed = command;

		redisplay();
	}

}
