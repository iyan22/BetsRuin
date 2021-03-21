package gui;


import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import businessLogic.BLFacade;
import domain.User;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class AddCardGUI extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField yearc;
	private JTextField monthc;
	private JTextField cvV;
	private JTextField cdN1;
	private JLabel result;
	private JTextField cdN2;
	private JTextField cdN3;
	private JTextField cdN4;
	private JButton closeButton1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AddCardGUI(User u) {
		setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jlbl1 = new JLabel("Card number:");
		jlbl1.setBounds(10, 71, 82, 14);
		contentPane.add(jlbl1);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setBounds(10, 96, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("/");
		lblNewLabel_1.setBounds(164, 96, 46, 20);
		contentPane.add(lblNewLabel_1);
		
		yearc = new JTextField();
		yearc.setBounds(174, 96, 57, 20);
		contentPane.add(yearc);
		yearc.setColumns(10);
		
		monthc = new JTextField();
		monthc.setColumns(10);
		monthc.setBounds(113, 96, 41, 20);
		contentPane.add(monthc);
		
		JLabel lblNewLabel_2 = new JLabel("CVV:");
		lblNewLabel_2.setBounds(10, 130, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		cvV = new JTextField();
		cvV.setBounds(113, 127, 41, 20);
		contentPane.add(cvV);
		cvV.setColumns(10);
		
		cdN1 = new JTextField();
		cdN1.setBounds(112, 68, 57, 20);
		contentPane.add(cdN1);
		cdN1.setColumns(10);
		
		JButton button = new JButton("AddCard");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BLFacade facade = StartGUI.getBusinessLogic();
					int cd1=Integer.parseInt(cdN1.getText());
					int cd2=Integer.parseInt(cdN2.getText());
					int cd3=Integer.parseInt(cdN3.getText());
					int cd4=Integer.parseInt(cdN4.getText());
					int month=Integer.parseInt(monthc.getText());
					int year=Integer.parseInt(yearc.getText());
					int cvv=Integer.parseInt(cvV.getText());
					if(cd1>9999 || cd2>9999 || cd3>9999 || cd4>9999) {
						result.setText("Not valid format for the card number!");
						result.setForeground(Color.red);
					}else if(month <1 || month>12 || year<2021||year>2100) {
						result.setText("Not valid format for the month or year!");
						result.setForeground(Color.red);
					}else if(cvv>999 || cvv <100) {
						result.setText("Not valid format for the cvv!");
						result.setForeground(Color.red);
					}else if(cd1<1000 || cd2<1000 || cd3<1000|| cd4<1000){
						result.setText("Not valid format for the card number!");
						result.setForeground(Color.red);
					}else {
						int[] card= {cd1,cd2,cd3,cd4,month,year,cvv};
						boolean res=facade.addCard(u,card);
						if(res) {
							result.setText("Card added!");
							result.setForeground(Color.green);
							u.setCard(card);
						}else {
							result.setText("Something went wrong, try again!");
							result.setForeground(Color.red);
						}
					}
					
					
					
				}catch(NumberFormatException ex) {
					result.setText("Not valid format, only numbers accepted!");
					result.setForeground(Color.red);
				}
				
			}
		});
		button.setBounds(98, 172, 89, 23);
		contentPane.add(button);
		
		result = new JLabel("");
		result.setBounds(113, 206, 219, 14);
		contentPane.add(result);
		
		cdN2 = new JTextField();
		cdN2.setColumns(10);
		cdN2.setBounds(179, 68, 57, 20);
		contentPane.add(cdN2);
		
		cdN3 = new JTextField();
		cdN3.setColumns(10);
		cdN3.setBounds(246, 68, 57, 20);
		contentPane.add(cdN3);
		
		cdN4 = new JTextField();
		cdN4.setColumns(10);
		cdN4.setBounds(313, 68, 57, 20);
		contentPane.add(cdN4);
		
		closeButton1 = new JButton("Close");
		closeButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e,u);
			}
		});
		closeButton1.setBounds(313, 227, 89, 23);
		contentPane.add(closeButton1);
	}
	private void jButton2_actionPerformed(ActionEvent e,User u) {
		UserZoneGUI f= new UserZoneGUI(u);
		f.setVisible(true);
		this.dispose();
	}

}
