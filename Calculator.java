

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
	
	public Calculator() {
		var panel = new JPanel(new GridBagLayout());
		add(panel);
		var c = new GridBagConstraints();
		c.weightx=1.0; c.weighty=1.0; c.fill=GridBagConstraints.BOTH;
		
		var textField = new JTextField();
		c.gridwidth=4;
		panel.add(textField,c);
		
		c.gridwidth=1;c.gridy=1; addButtonEvent(panel, new JButton("7"),textField,c);
		addButtonEvent(panel, new JButton("8"),textField,c);
		addButtonEvent(panel, new JButton("9"),textField,c);
		addButtonEvent(panel, new JButton("/"),textField,c);
		
		c.gridy=2;addButtonEvent(panel, new JButton("4"),textField,c);
		addButtonEvent(panel, new JButton("5"),textField,c);
		addButtonEvent(panel, new JButton("6"),textField,c);
		addButtonEvent(panel, new JButton("*"),textField,c);
		
		c.gridy=3;addButtonEvent(panel, new JButton("1"),textField,c);
		addButtonEvent(panel, new JButton("2"),textField,c); 
		addButtonEvent(panel, new JButton("3"),textField,c);
		addButtonEvent(panel, new JButton("-"),textField,c);
		
		c.gridy=4; addButtonEvent(panel, new JButton("0"),textField,c);
		

		var buttonResult = new JButton("=");
		buttonResult.addActionListener(new AbstractAction() {
			@Override public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				double result = Postfix.evaluatePostfixString(Infix.toPostfixString(text));
				textField.setText(textField.getText()+" = "+result);
			}
		});
		c.gridwidth=2;panel.add(buttonResult,c);
		
		c.gridwidth=1;addButtonEvent(panel, new JButton("+"),textField,c);
		
		setSize(400,250);
		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		}
	
	private void addButtonEvent (JPanel panel, JButton button, JTextField textField, 
			GridBagConstraints c){
		button.addActionListener(new AbstractAction() {
			@Override public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+((JButton) e.getSource()).getText());
			}});
		panel.add(button,c);
	}

	public static void main(String[] args) {
		new Calculator().setTitle("Calculator");
	}
}
