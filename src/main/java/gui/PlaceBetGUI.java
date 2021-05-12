package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import businessLogic.BLFacade;
import domain.Prediction;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Font;

public class PlaceBetGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amountBet;
	@SuppressWarnings("unused")
	private Prediction p;
	@SuppressWarnings("unused")
	private User u;

	/**
	 * Create the frame.
	 */
	public PlaceBetGUI(Prediction prediction, User user) {
		setTitle("Bets&Ruin - Place Bet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 362);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.p = prediction;
		this.u = user;

		JLabel submitted = new JLabel("");
		submitted.setBackground(Color.WHITE);
		submitted.setHorizontalAlignment(SwingConstants.CENTER);
		submitted.setBounds(42, 249, 409, 20);
		contentPane.add(submitted);

		JButton btnSubmit = new JButton(ResourceBundle.getBundle("Etiquetas").getString("PlaceBet"));
		btnSubmit.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnSubmit.setBounds(108, 281, 126, 30);
		btnSubmit.setBackground(new Color(255, 189, 89));
		btnSubmit.setForeground(new Color(61, 45, 20));
		btnSubmit.setOpaque(true);
		btnSubmit.setBorderPainted(false);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!amountBet.getText().equals("")) {
					try {
						float amount = Float.parseFloat(amountBet.getText());
						BLFacade facade = StartGUI.getBusinessLogic();
						float userFunds = user.getFunds();
						if (userFunds < prediction.getQuestion().getBetMinimum() || amount > userFunds) {
							submitted.setText(ResourceBundle.getBundle("Etiquetas").getString("NotEnought"));
							submitted.setForeground(Color.red);
						} else {
							if (amount >= prediction.getQuestion().getBetMinimum()) {
								facade.addBet(user, amount, prediction);
								user.betMade(amount);
								submitted.setText(ResourceBundle.getBundle("Etiquetas").getString("BetSummit"));
								submitted.setForeground(Color.green);

							} else {
								submitted.setText(ResourceBundle.getBundle("Etiquetas").getString("Error"));
								submitted.setForeground(Color.red);
							}
						}

					} catch (NumberFormatException ex) {
						submitted.setText(ResourceBundle.getBundle("Etiquetas").getString("NotValid"));
						submitted.setForeground(Color.red);
					}

				}
			}
		});

		JButton btnReturn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btnReturn.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnReturn.setForeground(new Color(255, 189, 89));
		btnReturn.setBackground(new Color(61, 45, 20));
		btnReturn.setOpaque(true);
		btnReturn.setBorderPainted(false);
		btnReturn.setBounds(246, 281, 126, 30);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClose_actionPerformed(e, user);
			}
		});
		contentPane.add(btnReturn);
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(42, 52, 409, 185);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblQuestion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Query") + ":");
		lblQuestion.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblQuestion.setBounds(16, 21, 80, 19);
		panel.add(lblQuestion);

		JLabel questionDesc = new JLabel("");
		questionDesc.setFont(new Font("PT Sans", Font.PLAIN, 16));
		questionDesc.setBounds(108, 21, 278, 19);
		panel.add(questionDesc);
		questionDesc.setText(prediction.getQuestion().getQuestion());

		JLabel lblPrediction = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Prediction") + ":");
		lblPrediction.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblPrediction.setBounds(16, 51, 80, 19);
		panel.add(lblPrediction);

		JLabel predictionString = new JLabel("");
		predictionString.setFont(new Font("PT Sans", Font.PLAIN, 16));
		predictionString.setBounds(108, 52, 278, 18);
		panel.add(predictionString);
		predictionString.setText(prediction.getAnswer());

		JLabel lblMultiplier = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Share") + ":");
		lblMultiplier.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblMultiplier.setBounds(16, 82, 80, 14);
		panel.add(lblMultiplier);

		JLabel multiplierField = new JLabel("");
		multiplierField.setBounds(108, 81, 216, 14);
		panel.add(multiplierField);
		multiplierField.setText(Float.toString(prediction.getShare()));

		JLabel lblAmount = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Amount") + ":");
		lblAmount.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblAmount.setBounds(16, 109, 80, 17);
		panel.add(lblAmount);

		amountBet = new JTextField();
		amountBet.setFont(new Font("PT Sans", Font.PLAIN, 14));
		amountBet.setBounds(108, 107, 96, 20);
		panel.add(amountBet);
		amountBet.setColumns(10);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(13, 150, 387, 19);
		panel.add(textPane);
		textPane.setEditable(false);
		textPane.setBackground(new Color(227, 227, 227));
		textPane.setFont(new Font("PT Sans", Font.BOLD, 14));
		textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("AmountError")
				+ prediction.getQuestion().getBetMinimum());
		textPane.setParagraphAttributes(attribs, true);
		textPane.setForeground(Color.red);

		JLabel currency = new JLabel("€");
		currency.setFont(new Font("PT Sans", Font.PLAIN, 16));
		currency.setBounds(216, 108, 37, 19);
		panel.add(currency);

		JLabel lblHoraDeApostar = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
		lblHoraDeApostar.setForeground(new Color(61, 45, 20));
		lblHoraDeApostar.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraDeApostar.setFont(new Font("PT Sans", Font.BOLD, 20));
		lblHoraDeApostar.setBounds(128, 10, 204, 30);
		contentPane.add(lblHoraDeApostar);
	}

	public void btnClose_actionPerformed(ActionEvent e, User u) {
		this.dispose();
	}
}
