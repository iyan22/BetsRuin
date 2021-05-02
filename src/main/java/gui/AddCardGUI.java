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
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;

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
	private JLabel lblVinculaTuTarjeta;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AddCardGUI(User u) {
		setResizable(false);
		setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 332);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		closeButton1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
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
		closeButton1.setBounds(376, 249, 102, 35);
		contentPane.add(closeButton1);
		
		lblVinculaTuTarjeta = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LinkCard"));
		lblVinculaTuTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblVinculaTuTarjeta.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblVinculaTuTarjeta.setBounds(68, 16, 375, 23);
		contentPane.add(lblVinculaTuTarjeta);
		
		panel = new JPanel();
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(33, 51, 445, 185);
		contentPane.add(panel);
		panel.setLayout(null);
		
		cdN1 = new JTextField();
		cdN1.setFont(new Font("PT Sans", Font.PLAIN, 14));
		cdN1.setBounds(145, 32, 62, 26);
		panel.add(cdN1);
		cdN1.setColumns(10);
		
		cdN2 = new JTextField();
		cdN2.setFont(new Font("PT Sans", Font.PLAIN, 14));
		cdN2.setBounds(219, 32, 62, 26);
		panel.add(cdN2);
		cdN2.setColumns(10);
		
		cdN3 = new JTextField();
		cdN3.setFont(new Font("PT Sans", Font.PLAIN, 14));
		cdN3.setBounds(293, 32, 62, 26);
		panel.add(cdN3);
		cdN3.setColumns(10);
		
		JLabel jlbl1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CardNumber"));
		jlbl1.setFont(new Font("PT Sans", Font.BOLD, 16));
		jlbl1.setBounds(32, 36, 101, 16);
		panel.add(jlbl1);
		
		monthc = new JTextField();
		monthc.setFont(new Font("PT Sans", Font.PLAIN, 14));
		monthc.setBounds(145, 66, 41, 26);
		panel.add(monthc);
		monthc.setColumns(10);
		
		cdN4 = new JTextField();
		cdN4.setFont(new Font("PT Sans", Font.PLAIN, 14));
		cdN4.setBounds(367, 32, 62, 26);
		panel.add(cdN4);
		cdN4.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("/");
		lblNewLabel_1.setBounds(200, 70, 7, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Date"));
		lblNewLabel.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblNewLabel.setBounds(32, 69, 101, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CVV"));
		lblNewLabel_2.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblNewLabel_2.setBounds(32, 102, 102, 16);
		panel.add(lblNewLabel_2);
		
		cvV = new JTextField();
		cvV.setFont(new Font("PT Sans", Font.PLAIN, 14));
		cvV.setBounds(145, 98, 62, 26);
		panel.add(cvV);
		cvV.setColumns(10);
		
		JButton btnVincular = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Link"));
		btnVincular.setBackground(new Color(255, 189, 89));
		btnVincular.setForeground(new Color(61, 45, 20));
		btnVincular.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnVincular.setBorderPainted(false);
		btnVincular.setOpaque(true);
		btnVincular.setBounds(126, 140, 161, 30);
		panel.add(btnVincular);
		btnVincular.addActionListener(new ActionListener() {
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
					
					if(cdN1.getText().length()!=4 || cdN2.getText().length()!=4 || cdN3.getText().length()!=4 || cdN4.getText().length()!=4) {
						result.setText(ResourceBundle.getBundle("Etiquetas").getString("NoCardNum"));
						result.setForeground(Color.red);
					}else if(month <1 || month>12 || year<2021||year>2100) {
						result.setText(ResourceBundle.getBundle("Etiquetas").getString("NoDateFormat"));
						result.setForeground(Color.red);
					}else if(cvV.getText().length()!=3) {
						result.setText(ResourceBundle.getBundle("Etiquetas").getString("NoCvv"));
						result.setForeground(Color.red);
					}else {
						int[] card= {cd1,cd2,cd3,cd4,month,year,cvv};
						boolean res=facade.addCard(u,card);
						if(res) {
							result.setText(ResourceBundle.getBundle("Etiquetas").getString("CardAdded"));
							result.setForeground(Color.green);
							u.setCard(card);
						}else {
							result.setText(ResourceBundle.getBundle("Etiquetas").getString("SmthWrong"));
							result.setForeground(Color.red);
						}
					}
					
					
					
				}catch(NumberFormatException ex) {
					result.setText(ResourceBundle.getBundle("Etiquetas").getString("NotValid"));
					result.setForeground(Color.red);
				}
				
			}
		});
		
		yearc = new JTextField();
		yearc.setFont(new Font("PT Sans", Font.PLAIN, 14));
		yearc.setBounds(219, 64, 62, 26);
		panel.add(yearc);
		yearc.setColumns(10);
		
		result = new JLabel("");
		result.setBounds(210, 99, 219, 19);
		panel.add(result);
	}
	private void jButton2_actionPerformed(ActionEvent e,User u) {
		UserZoneGUI f= new UserZoneGUI(u);
		f.setVisible(true);
		this.dispose();
	}

}