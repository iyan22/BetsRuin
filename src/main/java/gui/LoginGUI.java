package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnClose;
	private JLabel lblLogin;
	private JLabel lblMode;
	private JLabel submittedBtn;
	private JRadioButton userMode;
	private JRadioButton adminMode;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {

		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(104, 59, 48, 29);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(104, 98, 82, 13);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lblPassword);

		textField = new JTextField();
		textField.setBounds(186, 64, 132, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(186, 92, 132, 19);
		contentPane.add(passwordField);

		JButton btnLogin = new JButton("Log in");
		btnLogin.setBounds(196, 194, 85, 21);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = StartGUI.getBusinessLogic();
				User usr=facade.login(textField.getText(), String.copyValueOf(passwordField.getPassword()));
				if(usr!=null){
					if(userMode.isSelected()) {
						
						JFrame a = new MainUserGUI(usr);
						closeWindow(e);
						a.setVisible(true);
					}
					else if(adminMode.isSelected()) {
						if(facade.isAdmin(textField.getText())) {
							JFrame a = new MainAdminGUI(usr);
							closeWindow(e);
							a.setVisible(true);
						} else {
							submittedBtn.setText("You don't have permission to access as admin!");
							submittedBtn.setForeground(Color.red);
						}
					}
				}else{
					submittedBtn.setText("Username or password are incorrect!");
					submittedBtn.setForeground(Color.red);
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(btnLogin);

		btnClose = new JButton("Close");
		btnClose.setBounds(196, 250, 85, 21);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});
		contentPane.add(btnClose);

		lblLogin = new JLabel("Login");
		lblLogin.setBounds(217, 24, 45, 13);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblLogin);

		lblMode = new JLabel("Mode:");
		lblMode.setBounds(104, 137, 45, 13);
		contentPane.add(lblMode);

		userMode = new JRadioButton("User");
		userMode.setSelected(true);
		userMode.setBounds(186, 133, 76, 21);
		buttonGroup.add(userMode);
		contentPane.add(userMode);

		adminMode = new JRadioButton("Admin");
		adminMode.setBounds(264, 133, 103, 21);
		buttonGroup.add(adminMode);
		contentPane.add(adminMode);
	
		submittedBtn = new JLabel("");
		submittedBtn.setHorizontalAlignment(SwingConstants.CENTER);
		submittedBtn.setBounds(0, 227, 501, 13);
		contentPane.add(submittedBtn);
	}

	public void close(ActionEvent e) {
		this.dispose();
		JFrame a = new StartGUI();
		a.setVisible(true);
	}

	private void closeWindow(ActionEvent e) {
		this.setVisible(false);
	}
}
