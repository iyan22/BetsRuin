package gui;


import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddFundsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelframe;
	private JTextField amountField;
	/**
	 * Create the frame.
	 */
	public AddFundsGUI(User u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelframe = new JPanel();
		panelframe.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelframe);
		panelframe.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insert how much money you want to add to your account");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(32, 11, 392, 14);
		panelframe.add(lblNewLabel);
		
		amountField = new JTextField();
		amountField.setBounds(230, 108, 86, 20);
		panelframe.add(amountField);
		amountField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Amount:");
		lblNewLabel_1.setBounds(148, 111, 46, 14);
		panelframe.add(lblNewLabel_1);
		
	
		
		JLabel result = new JLabel("");
		result.setHorizontalAlignment(SwingConstants.CENTER);
		result.setBounds(32, 191, 392, 14);
		panelframe.add(result);
		
		JButton closeButton1 = new JButton("Return");
		closeButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e,u);
			}
		});
		closeButton1.setBounds(323, 216, 89, 23);
		panelframe.add(closeButton1);
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				float amount=Float.parseFloat(amountField.getText());
				BLFacade facade = StartGUI.getBusinessLogic();
				
				boolean res=facade.addFunds(u, amount);
				if(res) {
					result.setText("Funds added successfully!");
					result.setForeground(Color.green);
					u.addFunds(amount);
				
				}else {
					result.setText("Something went wrong, try again!");
					result.setForeground(Color.red);
				}
				
				}catch(NumberFormatException ex) {
					result.setText("Not valid format for the amount!");
					result.setForeground(Color.red);
				}
				
			}
		});
		addButton.setBounds(176, 157, 89, 23);
		panelframe.add(addButton);
	}
	private void jButton2_actionPerformed(ActionEvent e, User u) {
		UserZoneGUI f= new UserZoneGUI(u);
		f.setVisible(true);
		this.dispose();
	}
}
