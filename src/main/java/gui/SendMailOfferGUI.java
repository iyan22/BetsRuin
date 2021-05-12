package gui;


import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Email;
import domain.User;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class SendMailOfferGUI extends JFrame {

	private JPanel contentPane;
	private JTextField subject;
	private BLFacade facade = StartGUI.getBusinessLogic();
	@SuppressWarnings("unused")
	private User u;
	JLabel result;
	/**
	 * Create the frame.
	 */
	public SendMailOfferGUI(User u) {
		this.u=u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane message = new JTextPane();
		message.setBounds(143, 75, 455, 207);
		contentPane.add(message);
		
		subject = new JTextField();
		subject.setBounds(143, 44, 266, 20);
		contentPane.add(subject);
		subject.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Subject"));
		lblNewLabel.setBounds(10, 37, 70, 29);
		lblNewLabel.setFont(new Font("PT Sans", Font.BOLD, 16));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Message"));
		lblNewLabel_1.setBounds(10, 76, 100, 23);
		lblNewLabel_1.setFont(new Font("PT Sans", Font.BOLD, 16));
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendMail"));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBackground(new Color(255, 189, 89));
		btnNewButton.setForeground(new Color(61, 45, 20));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(true);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(subject.getText().isBlank()) {
					result.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorSubject"));
					result.setForeground(Color.RED);
				}else if(message.getText().isBlank()) {
					result.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorMessage"));
					result.setForeground(Color.RED);
				}else {
					String sub=subject.getText();
					String mess=message.getText();
					Email mail=new Email(u.getMail(), sub, mess);
					facade.sendMailOffer(mail);
					result.setText(ResourceBundle.getBundle("Etiquetas").getString("MailSent"));
					result.setForeground(Color.GREEN);
					System.out.println(message.getText().length());
				}
			}
		});
		btnNewButton.setBounds(312, 302, 151, 23);
		contentPane.add(btnNewButton);
		
		JButton btn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btn.setFont(new Font("Dialog", Font.BOLD, 16));
		btn.setBackground(new Color(61, 45, 20));
		btn.setForeground(new Color(255, 189, 89));
		btn.setBorderPainted(false);
		btn.setOpaque(true);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeGUI(e);
			}
		});
		btn.setBounds(509, 302, 89, 23);
		contentPane.add(btn);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MailEditor"));
		lblNewLabel_2.setBounds(230, 11, 198, 14);
		lblNewLabel_2.setFont(new Font("PT Sans", Font.BOLD, 16));
		contentPane.add(lblNewLabel_2);
		
		
		result = new JLabel("");
		result.setBounds(10, 302, 258, 23);
		result.setFont(new Font("PT Sans", Font.BOLD, 16));
		contentPane.add(result);
	}
	public void closeGUI(ActionEvent e) {
		this.dispose();
	}
}
