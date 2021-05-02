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
import java.util.ResourceBundle;
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
		setTitle("Bets&Ruin - User zone");
		this.user = u;
		BLFacade facade = StartGUI.getBusinessLogic();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 553);
		showFunds = new JPanel();
		showFunds.setBackground(Color.WHITE);
		showFunds.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(showFunds);
		showFunds.setLayout(null);

		JLabel NoCreditCard = new JLabel("");
		NoCreditCard.setHorizontalAlignment(SwingConstants.CENTER);
		NoCreditCard.setFont(new Font("PT Sans", Font.BOLD, 16));
		NoCreditCard.setBounds(48, 476, 577, 23);
		showFunds.add(NoCreditCard);

		JLabel lblCurrentBets = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CurrentBets"));
		lblCurrentBets.setForeground(new Color(61, 45, 20));
		lblCurrentBets.setFont(new Font("PT Sans", Font.BOLD, 18));
		lblCurrentBets.setBounds(48, 218, 148, 23);
		showFunds.add(lblCurrentBets);
		DefaultTableModel mod = new DefaultTableModel(new Object[][] {},
				new String[] { ResourceBundle.getBundle("Etiquetas").getString("Event"),
						ResourceBundle.getBundle("Etiquetas").getString("Query"),
						ResourceBundle.getBundle("Etiquetas").getString("Prediction"),
						ResourceBundle.getBundle("Etiquetas").getString("Amount"),
						ResourceBundle.getBundle("Etiquetas").getString("Share") });
		List<Bet> usrbetlist = facade.getBets(u.getUsername());
		for (Bet b : usrbetlist) {
			Vector<Object> row = new Vector<Object>();
			row.add(b.getPrediction().getQuestion().getEvent().getDescription());
			row.add(b.getPrediction().getQuestion().getQuestion());
			row.add(b.getPrediction().getAnswer());
			row.add(b.getAmount());
			row.add(b.getPrediction().getShare());
			mod.addRow(row);
		}
		String s = String.valueOf(user.getFunds());

		JPanel panelPersonal = new JPanel();
		panelPersonal.setBackground(new Color(227, 227, 227));
		panelPersonal.setBounds(48, 81, 287, 125);
		showFunds.add(panelPersonal);
		panelPersonal.setLayout(null);

		JLabel lblUsername = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserName") + ":");
		lblUsername.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblUsername.setBounds(12, 16, 96, 14);
		panelPersonal.add(lblUsername);

		JLabel lblName = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name") + ":");
		lblName.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblName.setBounds(12, 42, 96, 14);
		panelPersonal.add(lblName);

		JLabel lblSurname = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Surname") + ":");
		lblSurname.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblSurname.setBounds(12, 68, 96, 14);
		panelPersonal.add(lblSurname);

		JLabel lblEmail = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Email") + ":");
		lblEmail.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblEmail.setBounds(12, 94, 96, 14);
		panelPersonal.add(lblEmail);

		JLabel lblUsernameVar = new JLabel("");
		lblUsernameVar.setFont(new Font("PT Sans", Font.PLAIN, 16));
		lblUsernameVar.setBounds(120, 16, 150, 14);
		panelPersonal.add(lblUsernameVar);
		lblUsernameVar.setText(user.getUsername());

		JLabel lblNameVar = new JLabel("");
		lblNameVar.setFont(new Font("PT Sans", Font.PLAIN, 16));
		lblNameVar.setBounds(120, 42, 150, 14);
		panelPersonal.add(lblNameVar);
		lblNameVar.setText(user.getName());

		JLabel lblSurnameVar = new JLabel("");
		lblSurnameVar.setFont(new Font("PT Sans", Font.PLAIN, 16));
		lblSurnameVar.setBounds(120, 68, 150, 14);
		panelPersonal.add(lblSurnameVar);
		lblSurnameVar.setText(user.getSurname());

		JLabel lblEmailVar = new JLabel("");
		lblEmailVar.setFont(new Font("PT Sans", Font.PLAIN, 16));
		lblEmailVar.setBounds(120, 94, 150, 14);
		panelPersonal.add(lblEmailVar);
		lblEmailVar.setText(user.getMail());

		JLabel lblPersonalInfo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PersonalInfo"));
		lblPersonalInfo.setForeground(new Color(61, 45, 20));
		lblPersonalInfo.setFont(new Font("PT Sans", Font.BOLD, 18));
		lblPersonalInfo.setBounds(48, 46, 281, 23);
		showFunds.add(lblPersonalInfo);

		JLabel lblFinancialInformation = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FinalcialInfo"));
		lblFinancialInformation.setForeground(new Color(61, 45, 20));
		lblFinancialInformation.setFont(new Font("PT Sans", Font.BOLD, 18));
		lblFinancialInformation.setBounds(344, 46, 281, 23);
		showFunds.add(lblFinancialInformation);

		JPanel panelBets = new JPanel();
		panelBets.setBackground(new Color(227, 227, 227));
		panelBets.setBounds(48, 253, 577, 211);
		showFunds.add(panelBets);
		panelBets.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 19, 540, 174);
		panelBets.add(scrollPane);
		table = new JTable();
		table.setFont(new Font("PT Sans", Font.PLAIN, 14));
		table.setModel(mod);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(256);
		scrollPane.setViewportView(table);

		JPanel panelFinancial = new JPanel();
		panelFinancial.setLayout(null);
		panelFinancial.setBackground(new Color(227, 227, 227));
		panelFinancial.setBounds(347, 81, 271, 160);
		showFunds.add(panelFinancial);

		JButton btnAddCard = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddCreditCard"));
		btnAddCard.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnAddCard.setForeground(new Color(255, 189, 89));
		btnAddCard.setBackground(new Color(61, 45, 20));
		btnAddCard.setOpaque(true);
		btnAddCard.setBorderPainted(false);
		btnAddCard.setBounds(6, 124, 259, 30);
		panelFinancial.add(btnAddCard);

		JLabel lblReferralCode = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ReferralCode") + ": ");
		lblReferralCode.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblReferralCode.setBounds(16, 43, 106, 14);
		panelFinancial.add(lblReferralCode);

		JLabel lblFunds = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Founds") + ": ");
		lblFunds.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblFunds.setBounds(18, 17, 104, 14);
		panelFinancial.add(lblFunds);

		JLabel lblReferralCodeVar = new JLabel("");
		lblReferralCodeVar.setFont(new Font("PT Sans", Font.PLAIN, 16));
		lblReferralCodeVar.setBounds(134, 43, 117, 14);
		panelFinancial.add(lblReferralCodeVar);
		lblReferralCodeVar.setText(u.getRefCode());

		JLabel lblNumRefVar = new JLabel("");
		lblNumRefVar.setFont(new Font("PT Sans", Font.PLAIN, 16));
		lblNumRefVar.setBounds(134, 69, 89, 14);
		panelFinancial.add(lblNumRefVar);
		lblNumRefVar.setText(u.getNumberRef().toString());

		JLabel lblFundsVar = new JLabel("");
		lblFundsVar.setFont(new Font("PT Sans", Font.PLAIN, 16));
		lblFundsVar.setBounds(134, 17, 106, 14);
		panelFinancial.add(lblFundsVar);
		lblFundsVar.setText(s);

		JLabel lblNumRef = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NReferrals") + ": ");
		lblNumRef.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblNumRef.setBounds(16, 69, 106, 14);
		panelFinancial.add(lblNumRef);

		JButton btnAddFunds = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddFunds"));
		btnAddFunds.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnAddFunds.setBackground(new Color(255, 189, 89));
		btnAddFunds.setForeground(new Color(61, 45, 20));
		btnAddFunds.setOpaque(true);
		btnAddFunds.setBorderPainted(false);
		btnAddFunds.setBounds(6, 89, 259, 30);
		panelFinancial.add(btnAddFunds);

		JLabel lblFundsCurr = new JLabel("");
		lblFundsCurr.setFont(new Font("PT Sans", Font.PLAIN, 16));
		lblFundsCurr.setBounds(235, 17, 30, 14);
		panelFinancial.add(lblFundsCurr);
		btnAddFunds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.getBank()) {
					AddFundsGUI f = new AddFundsGUI(user);
					f.setVisible(true);
					close(e);
				} else {
					NoCreditCard.setText(ResourceBundle.getBundle("Etiquetas").getString("NoCard"));
					NoCreditCard.setForeground(Color.red);
				}
			}
		});
		btnAddCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCardGUI f = new AddCardGUI(u);
				f.setVisible(true);
				close(e);
			}
		});

	}

	public void close(ActionEvent e) {
		this.dispose();
	}
}
