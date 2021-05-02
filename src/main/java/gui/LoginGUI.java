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
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnClose;
	private JLabel lblLogo;
	private JLabel lblMode;
	private JLabel submittedBtn;
	private JRadioButton userMode;
	private JRadioButton adminMode;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel;
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
		setResizable(false);

		setTitle("Bets&Ruin - Log in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 373, 435);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		btnLogin.setBounds(65, 353, 101, 30);
		btnLogin.setForeground(new Color(61, 45, 20));
		btnLogin.setBackground(new Color(255, 189, 89));
		btnLogin.setBorderPainted(false);
		btnLogin.setOpaque(true);
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
							submittedBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorAdmin"));
							submittedBtn.setForeground(Color.red);
						}
					}
				}
				else{
					submittedBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorLogin"));
					submittedBtn.setForeground(Color.red);
				}
			}
		});
		btnLogin.setFont(new Font("PT Sans", Font.BOLD, 16));
		contentPane.add(btnLogin);

		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnClose.setBackground(new Color(61, 45, 20));
		btnClose.setForeground(new Color(255, 189, 89));
		btnClose.setBounds(191, 353, 101, 30);
		btnClose.setOpaque(true);
		btnClose.setBorderPainted(false);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});
		contentPane.add(btnClose);

		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(LoginGUI.class.getResource("/img/LogoBetsRuinL.png")));
		lblLogo.setBounds(104, 26, 164, 143);
		lblLogo.setFont(new Font("PT Sans", Font.BOLD, 20));
		contentPane.add(lblLogo);
		
		panel = new JPanel();
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(36, 199, 301, 116);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password") + ":");
		lblPassword.setBounds(16, 47, 71, 22);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("PT Sans", Font.BOLD, 16));
			
		adminMode = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Admin"));
		adminMode.setFont(new Font("PT Sans", Font.BOLD, 14));
		adminMode.setBounds(179, 79, 73, 23);
		panel.add(adminMode);
		buttonGroup.add(adminMode);
						
		userMode = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("User"));
		userMode.setFont(new Font("PT Sans", Font.BOLD, 14));
		userMode.setBounds(99, 79, 60, 23);
		panel.add(userMode);
		userMode.setSelected(true);
		buttonGroup.add(userMode);
								
		lblMode = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Mode") + ":");
		lblMode.setBounds(16, 81, 43, 22);
		panel.add(lblMode);
		lblMode.setFont(new Font("PT Sans", Font.BOLD, 16));
										
		passwordField = new JPasswordField();
		passwordField.setBounds(99, 44, 185, 26);
		panel.add(passwordField);
												
		textField = new JTextField();
		textField.setBounds(99, 14, 185, 26);
		panel.add(textField);
		textField.setColumns(10);
														
		JLabel lblUser = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User") + ":");
		lblUser.setBounds(16, 17, 35, 22);
		panel.add(lblUser);
		lblUser.setFont(new Font("PT Sans", Font.BOLD, 16));
																	
		submittedBtn = new JLabel("");
		submittedBtn.setFont(new Font("PT Sans", Font.BOLD, 16));
		submittedBtn.setBounds(36, 328, 301, 17);
		contentPane.add(submittedBtn);
		submittedBtn.setHorizontalAlignment(SwingConstants.CENTER);
		
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
