package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Bet;
import domain.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UserZoneGUI extends JFrame {

	private JPanel showFunds;
	private User user;
	private DefaultListModel<Bet> betlist;
	private DefaultTableModel tableModelBets;
	private String[] columnNamesBets= {"Question", "Amount"};
	/**
	 * Create the frame.
	 */
	public UserZoneGUI(User u) {
		this.user=u;
		BLFacade facade = StartGUI.getBusinessLogic();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 361);
		showFunds = new JPanel();
		showFunds.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(showFunds);
		showFunds.setLayout(null);
		
		JLabel ADSD = new JLabel("<User>'s zone");
		ADSD.setFont(new Font("Tahoma", Font.BOLD, 12));
		ADSD.setBounds(163, 11, 91, 14);
		showFunds.add(ADSD);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(10, 60, 61, 14);
		showFunds.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setBounds(10, 85, 46, 14);
		showFunds.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surname: ");
		lblNewLabel_2.setBounds(10, 110, 61, 14);
		showFunds.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(10, 135, 46, 14);
		showFunds.add(lblNewLabel_3);
		
		JLabel saldo = new JLabel("Funds");
		saldo.setBounds(10, 160, 46, 14);
		showFunds.add(saldo);
		
		JLabel showName = new JLabel("");
		showName.setBounds(76, 85, 130, 14);
		showName.setText(user.getName());
		showFunds.add(showName);
		
		JLabel showUsername = new JLabel("");
		showUsername.setBounds(76, 60, 130, 14);
		showUsername.setText(user.getUsername());
		showFunds.add(showUsername);
		
		JLabel showSurname = new JLabel("");
		showSurname.setBounds(76, 110, 130, 14);
		showSurname.setText(user.getSurname());
		showFunds.add(showSurname);
		
		JLabel showEmail = new JLabel("");
		showEmail.setBounds(76, 135, 130, 14);
		showEmail.setText(user.getMail());
		showFunds.add(showEmail);
		
		JLabel lblNewLabel_4_3 = new JLabel("");
		lblNewLabel_4_3.setBounds(76, 160, 130, 14);
		showFunds.add(lblNewLabel_4_3);
		
		JLabel NoCreditCard = new JLabel("");
		NoCreditCard.setBounds(10, 219, 148, 14);
		showFunds.add(NoCreditCard);
		
		JLabel lblNewLabel_4 = new JLabel("Active Bets");
		lblNewLabel_4.setBounds(285, 35, 61, 14);
		showFunds.add(lblNewLabel_4);
		
		JButton addfundsbtn = new JButton("Add funds");
		addfundsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getBank()) {
					AddFundsGUI f= new AddFundsGUI(user);
				}else {
					NoCreditCard.setText("Ninguna tarjeta de credito registrada");
					NoCreditCard.setForeground(Color.red);
				}
			}
		});
		addfundsbtn.setBounds(10, 185, 89, 23);
		showFunds.add(addfundsbtn);
		
	
		
		
	}
	
	public void close(ActionEvent e) {
		this.dispose();
	}
}
