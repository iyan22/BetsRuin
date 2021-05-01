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
		setResizable(false);
		setTitle("Bets&Ruin - Add Funds");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 254);
		panelframe = new JPanel();
		panelframe.setBackground(Color.WHITE);
		panelframe.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelframe);
		panelframe.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¿Con cuánto dinero quieres jugar?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(32, 11, 392, 28);
		panelframe.add(lblNewLabel);
		
	
		
		JLabel result = new JLabel("");
		result.setHorizontalAlignment(SwingConstants.CENTER);
		result.setBounds(44, 177, 256, 28);
		panelframe.add(result);
		
		JButton closeButton1 = new JButton("Return");
		closeButton1.setForeground(new Color(255, 189, 89));
		closeButton1.setBackground(new Color(61, 45, 20));
		closeButton1.setFont(new Font("PT Sans", Font.BOLD, 16));
		closeButton1.setBorderPainted(false);
		closeButton1.setOpaque(true);
		closeButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e,u);
			}
		});
		closeButton1.setBounds(323, 177, 101, 34);
		panelframe.add(closeButton1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(82, 57, 284, 97);
		panelframe.add(panel);
		panel.setLayout(null);
		JButton addButton = new JButton("Ingresar");
		addButton.setBounds(88, 55, 104, 30);
		panel.add(addButton);
		addButton.setBackground(new Color(255, 189, 89));
		addButton.setForeground(new Color(61, 45, 20));
		addButton.setFont(new Font("PT Sans", Font.BOLD, 16));
		addButton.setBorderPainted(false);
		addButton.setOpaque(true);
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
		
		amountField = new JTextField();
		amountField.setFont(new Font("PT Sans", Font.PLAIN, 14));
		amountField.setBounds(122, 17, 113, 26);
		panel.add(amountField);
		amountField.setColumns(10);
		
		JLabel lblAmount = new JLabel("Cantidad:");
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setBounds(6, 19, 104, 22);
		panel.add(lblAmount);
		lblAmount.setFont(new Font("PT Sans", Font.BOLD, 16));
		
		JLabel lblCurrency = new JLabel("€");
		lblCurrency.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrency.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblCurrency.setBounds(247, 21, 31, 22);
		panel.add(lblCurrency);
	}
	private void jButton2_actionPerformed(ActionEvent e, User u) {
		UserZoneGUI f= new UserZoneGUI(u);
		f.setVisible(true);
		this.dispose();
	}
}
