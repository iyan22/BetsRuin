package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import exceptions.UserAlreadyExists;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField email;
	private JPasswordField pw1;
	private JPasswordField pw2;
	private JLabel submitText;
	private JButton btnClose;
	private JLabel lblCreateYourAccount;

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setTitle("Register");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInsert = new JLabel("Username:");
		lblInsert.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblInsert.setBounds(27, 65, 116, 13);
		contentPane.add(lblInsert);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPassword.setBounds(27, 88, 116, 13);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblConfirmPassword.setBounds(27, 111, 116, 13);
		contentPane.add(lblConfirmPassword);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmail.setBounds(27, 134, 116, 13);
		contentPane.add(lblEmail);
		
		username = new JTextField();
		username.setBounds(153, 63, 161, 19);
		contentPane.add(username);
		username.setColumns(10);
		
		email = new JTextField();
		email.setBounds(153, 132, 161, 19);
		contentPane.add(email);
		email.setColumns(10);
		
		
		pw1 = new JPasswordField();
		pw1.setBounds(153, 86, 161, 19);
		contentPane.add(pw1);
		
		pw2 = new JPasswordField();
		pw2.setBounds(153, 109, 161, 19);
		contentPane.add(pw2);
		
		submitText = new JLabel("");
		submitText.setHorizontalAlignment(SwingConstants.CENTER);
		submitText.setBounds(0, 212, 436, 13);
		contentPane.add(submitText);
		
		JButton btnCreateAccount = new JButton("Submit");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(pw1.getPassword()).contentEquals(String.copyValueOf(pw2.getPassword()))) {
					BLFacade facade = StartGUI.getBusinessLogic();
					try {
						facade.createUser(username.getText(), String.valueOf(pw1.getPassword()), email.getText());
						submitText.setText("Account created successfully!");
						submitText.setForeground(Color.green);
					} catch (UserAlreadyExists e1) {
						submitText.setText("This user already exists! Try again!");
						submitText.setForeground(Color.RED);
					}
					
				} else {
					submitText.setText("Unable to create the user. Try again!");
					submitText.setForeground(Color.RED);
				}
			}
		});
		btnCreateAccount.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCreateAccount.setBounds(175, 181, 85, 21);
		contentPane.add(btnCreateAccount);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClose_actionPerformed(e);
			}
		});
		btnClose.setBounds(175, 232, 85, 21);
		contentPane.add(btnClose);
		
		lblCreateYourAccount = new JLabel("Create your account");
		lblCreateYourAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCreateYourAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateYourAccount.setBounds(0, 25, 436, 13);
		contentPane.add(lblCreateYourAccount);
		
	}
	
	public void btnClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		JFrame a = new LoginGUI();
		a.setVisible(true);
	}
}
