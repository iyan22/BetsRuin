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
import javax.swing.SwingConstants;


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
		titleZONE.setHorizontalAlignment(SwingConstants.CENTER);
		titleZONE.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleZONE.setBounds(318, 20, 148, 14);
		titleZONE.setText(user.getUsername()+"'s zone");
		showFunds.add(titleZONE);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(54, 60, 80, 14);
		showFunds.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setBounds(54, 84, 80, 14);
		showFunds.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surname: ");
		lblNewLabel_2.setBounds(54, 110, 80, 14);
		showFunds.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(54, 136, 80, 14);
		showFunds.add(lblNewLabel_3);
		
		JLabel saldo = new JLabel("Funds:");
		saldo.setBounds(498, 85, 46, 14);
		showFunds.add(saldo);
		
		JLabel showName = new JLabel("");
		showName.setBounds(151, 85, 195, 14);
		showName.setText(user.getName());
		showFunds.add(showName);
		
		JLabel showUsername = new JLabel("");
		showUsername.setBounds(151, 60, 195, 14);
		showUsername.setText(user.getUsername());
		showFunds.add(showUsername);
		
		JLabel showSurname = new JLabel("");
		showSurname.setBounds(151, 110, 195, 14);
		showSurname.setText(user.getSurname());
		showFunds.add(showSurname);
		
		JLabel showEmail = new JLabel("");
		showEmail.setBounds(151, 135, 195, 14);
		showEmail.setText(user.getMail());
		showFunds.add(showEmail);
		
	
		
		JLabel NoCreditCard = new JLabel("");
		NoCreditCard.setBounds(459, 110, 301, 14);
		showFunds.add(NoCreditCard);
		
		JLabel lblNewLabel_4 = new JLabel("Current Bets");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(337, 210, 130, 14);
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
		addfundsbtn.setBounds(488, 132, 123, 23);
		showFunds.add(addfundsbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 234, 540, 174);
		showFunds.add(scrollPane);
		DefaultTableModel mod = new DefaultTableModel(
				new Object[][] {},
				new String[] {"Event", "Question", "Amount", "Result", "Share"}
			);
		table = new JTable();
		table.setModel(mod);
		List<Bet> usrbetlist= facade.getBets(u.getUsername());
		if (usrbetlist.size() > 0) {
			for(Bet b : usrbetlist) {
				Vector<Object> row = new Vector<Object>();
				row.add(b.getQuestion().getEvent().getDescription());
				row.add(b.getQuestion().getQuestion());
				row.add(b.getAmount());
				row.add(b.getResult());
				row.add(b.getQuestion().getBetShare());
				mod.addRow(row);
			}
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(256);
		scrollPane.setViewportView(table);
		
		JLabel showFundsv2 = new JLabel("");
		showFundsv2.setBounds(582, 85, 130, 14);
		String s = String.valueOf(user.getFunds());
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
		btnNewButton.setBounds(620, 132, 140, 23);
		showFunds.add(btnNewButton);
		
	
		
		
	}
	
	public void close(ActionEvent e) {
		this.dispose();
	}
}
