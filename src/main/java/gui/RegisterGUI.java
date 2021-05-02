package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import exceptions.UserAlreadyExists;
import exceptions.NoReferralCodeFound;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField email;
	private JPasswordField pw1;
	private JPasswordField pw2;
	private JLabel submitText;
	private JButton btnClose;
	private JLabel lblLogo;
	private JTextField surname;
	private JTextField name;
	private JPanel panel;
	private JLabel lblReferralCode;
	private JTextField referralField;

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setTitle("Bets&Ruin - Register");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 543);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreateAccount = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(pw1.getPassword()).contentEquals(String.copyValueOf(pw2.getPassword()))) {
					BLFacade facade = StartGUI.getBusinessLogic();
					try {
						facade.createUser(username.getText(),name.getText(), surname.getText(), String.valueOf(pw1.getPassword()), email.getText(), referralField.getText());
						submitText.setText(ResourceBundle.getBundle("Etiquetas").getString("AccountCreated"));
						submitText.setForeground(Color.green);
					} catch (UserAlreadyExists e1) {
						submitText.setText(ResourceBundle.getBundle("Etiquetas").getString("UserExists"));
						submitText.setForeground(Color.RED);
					}
					catch (NoReferralCodeFound e2) {
						submitText.setText(ResourceBundle.getBundle("Etiquetas").getString("ReferralError"));
					}
					
				} else {
					submitText.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorUser"));
					submitText.setForeground(Color.RED);
				}
			}
		});
		btnCreateAccount.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnCreateAccount.setBackground(new Color(61, 45, 20));
		btnCreateAccount.setForeground(new Color(255, 189, 89));
		btnCreateAccount.setOpaque(true);
		btnCreateAccount.setBorderPainted(false);
		btnCreateAccount.setBounds(83, 453, 123, 35);
		contentPane.add(btnCreateAccount);
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btnClose.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnClose.setForeground(new Color(61, 45, 20));
		btnClose.setBackground(new Color(255, 189, 89));
		btnClose.setOpaque(true);
		btnClose.setBorderPainted(false);
		btnClose.setBounds(231, 453, 123, 35);
		contentPane.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClose_actionPerformed(e);
			}
		});
		
		submitText = new JLabel("");
		submitText.setFont(new Font("PT Sans", Font.BOLD, 16));
		submitText.setHorizontalAlignment(SwingConstants.CENTER);
		submitText.setBounds(26, 411, 382, 19);
		contentPane.add(submitText);
		
		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(RegisterGUI.class.getResource("/img/LogoBetsRuinL.png")));
		lblLogo.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(133, 20, 150, 138);
		contentPane.add(lblLogo);
		
		panel = new JPanel();
		panel.setBounds(26, 190, 382, 209);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name") + ":");
		lblName.setBounds(15, 18, 136, 15);
		panel.add(lblName);
		lblName.setFont(new Font("PT Sans", Font.BOLD, 16));
		
		JLabel lblSurname = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Surname") + ":");
		lblSurname.setBounds(15, 45, 136, 15);
		panel.add(lblSurname);
		lblSurname.setFont(new Font("PT Sans", Font.BOLD, 16));
		
		name = new JTextField();
		name.setFont(new Font("PT Sans", Font.PLAIN, 14));
		name.setBounds(163, 11, 202, 26);
		panel.add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setFont(new Font("PT Sans", Font.PLAIN, 14));
		surname.setBounds(163, 39, 202, 26);
		panel.add(surname);
		surname.setColumns(10);
		
		
		pw1 = new JPasswordField();
		pw1.setFont(new Font("PT Sans", Font.PLAIN, 14));
		pw1.setBounds(163, 92, 202, 26);
		panel.add(pw1);
		
		pw2 = new JPasswordField();
		pw2.setFont(new Font("PT Sans", Font.PLAIN, 14));
		pw2.setBounds(163, 120, 202, 26);
		panel.add(pw2);
		
		email = new JTextField();
		email.setFont(new Font("PT Sans", Font.PLAIN, 14));
		email.setBounds(163, 147, 202, 26);
		panel.add(email);
		email.setColumns(10);
		
		JLabel lblEmail = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Email") + ":");
		lblEmail.setBounds(15, 153, 136, 15);
		panel.add(lblEmail);
		lblEmail.setFont(new Font("PT Sans", Font.BOLD, 16));
		
		JLabel lblConfirmPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ConfirmPassword") + ":");
		lblConfirmPassword.setBounds(15, 126, 136, 15);
		panel.add(lblConfirmPassword);
		lblConfirmPassword.setFont(new Font("PT Sans", Font.BOLD, 16));
		
		JLabel lblPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password") + ":");
		lblPassword.setBounds(15, 99, 136, 15);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("PT Sans", Font.BOLD, 16));
		
		JLabel lblInsert = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User") + ":");
		lblInsert.setBounds(15, 72, 136, 15);
		panel.add(lblInsert);
		lblInsert.setFont(new Font("PT Sans", Font.BOLD, 16));
		
		username = new JTextField();
		username.setFont(new Font("PT Sans", Font.PLAIN, 14));
		username.setBounds(163, 66, 202, 26);
		panel.add(username);
		username.setColumns(10);
		
				referralField = new JTextField();
				referralField.setFont(new Font("PT Sans", Font.PLAIN, 14));
				referralField.setBounds(163, 176, 202, 25);
				panel.add(referralField);
				referralField.setColumns(10);
		
		lblReferralCode = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ReferralCode") + ":");
		lblReferralCode.setBounds(15, 183, 116, 15);
		panel.add(lblReferralCode);
		lblReferralCode.setFont(new Font("PT Sans", Font.BOLD, 16));
		
	}
	
	public void btnClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		JFrame a = new StartGUI();
		a.setVisible(true);
	}
}
