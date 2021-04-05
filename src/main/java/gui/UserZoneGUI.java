package gui;


import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import businessLogic.BLFacade;
import domain.Bet;
import domain.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class UserZoneGUI extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel showFunds;
	private User user;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public UserZoneGUI(User u) {
		this.user=u;
		BLFacade facade = StartGUI.getBusinessLogic();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 804, 484);
		showFunds = new JPanel();
		showFunds.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(showFunds);
		showFunds.setLayout(null);
		
		JLabel titleZONE = new JLabel("<User>'s zone");
		titleZONE.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleZONE.setBounds(344, 11, 91, 14);
		titleZONE.setText(user.getUsername()+"'s zone");
		showFunds.add(titleZONE);
		
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
		saldo.setBounds(490, 42, 46, 14);
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
		
	
		
		JLabel NoCreditCard = new JLabel("");
		NoCreditCard.setBounds(459, 110, 301, 14);
		showFunds.add(NoCreditCard);
		
		JLabel lblNewLabel_4 = new JLabel("Current Bets");
		lblNewLabel_4.setBounds(368, 237, 148, 14);
		showFunds.add(lblNewLabel_4);
		
		JButton addfundsbtn = new JButton("Add funds");
		addfundsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getBank()) {
					AddFundsGUI f= new AddFundsGUI(user);
					f.setVisible(true);
					close(e);
				}else {
					NoCreditCard.setText("Ninguna tarjeta de credito registrada");
					NoCreditCard.setForeground(Color.red);
				}
			}
		});
		addfundsbtn.setBounds(490, 81, 123, 23);
		showFunds.add(addfundsbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(133, 260, 540, 174);
		showFunds.add(scrollPane);
		DefaultTableModel mod= new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Event", "Question", "Prediction", "Amount"
				}
			);
		table = new JTable();
		table.setModel(mod);
		List<Bet> usrbetlist= facade.getBets(u.getUsername());
		for(Bet b:usrbetlist) {
			Vector<Object> row= new Vector<Object>();
			row.add(b.getPrediction().getQuestion().getEvent().getDescription());
			row.add(b.getPrediction().getQuestion().getQuestion());
			row.add(b.getPrediction().getAnswer());
			row.add(b.getAmount());
			mod.addRow(row);
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(256);
		scrollPane.setViewportView(table);
		
		JLabel showFundsv2 = new JLabel("");
		showFundsv2.setBounds(553, 42, 130, 14);
		String s= String.valueOf(user.getFunds());
		showFundsv2.setText(s);
		showFunds.add(showFundsv2);
		
		JButton btnNewButton = new JButton("Add Credit Card");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCardGUI f=new AddCardGUI(u);
				f.setVisible(true);
				close(e);
			}
		});
		btnNewButton.setBounds(620, 81, 140, 23);
		showFunds.add(btnNewButton);
		
	
		
		
	}
	
	public void close(ActionEvent e) {
		this.dispose();
	}
}
