package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	/**
	 * Create the frame.
	 */
	public PlaceBetGUI(Prediction prediction, User user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 506, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(45, 161, 80, 13);
		contentPane.add(lblAmount);

		amountBet = new JTextField();
		amountBet.setBounds(135, 157, 96, 19);
		contentPane.add(amountBet);
		amountBet.setColumns(10);

		JLabel submitted = new JLabel("");
		submitted.setHorizontalAlignment(SwingConstants.CENTER);
		submitted.setBounds(10, 214, 472, 13);
		contentPane.add(submitted);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(128, 242, 85, 21);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!amountBet.getText().equals("")) {
					try
					{
						float amount = Float.parseFloat(amountBet.getText());
						BLFacade facade = StartGUI.getBusinessLogic();
						float userFunds=user.getFunds();
						if(userFunds<prediction.getQuestion().getBetMinimum() || amount>userFunds) {
							submitted.setText("Not enough funds!");
							submitted.setForeground(Color.red);
						}else {
							if(amount>=prediction.getQuestion().getBetMinimum()) {
								facade.addBet(user,  amount, prediction);
								user.betMade(amount);
								submitted.setText("Bet submitted successfully!");
								submitted.setForeground(Color.green);
								
						
							}else{
								submitted.setText("Error!");
								submitted.setForeground(Color.red);
							}
					}
				
					} catch(NumberFormatException ex) {
						submitted.setText("Not valid format for the amount!");
						submitted.setForeground(Color.red);
					}
					
				}
			}
		});

		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(233, 242, 85, 21);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClose_actionPerformed(e,user);
			}
		});
		contentPane.add(btnReturn);


		JLabel lblQuestion = new JLabel("Question:");
		lblQuestion.setBounds(45, 85, 80, 13);
		contentPane.add(lblQuestion);

		JLabel questionDesc = new JLabel("");
		questionDesc.setBounds(135, 85, 347, 13);
		questionDesc.setText(prediction.getQuestion().getQuestion());
		contentPane.add(questionDesc);

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		textPane.setBounds(0, 185, 502, 19);
		textPane.setText("Be careful! The amount you want to bet should be at least "+prediction.getQuestion().getBetMinimum());
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		textPane.setParagraphAttributes(attribs, true);
		textPane.setForeground(Color.red);
		contentPane.add(textPane);
		
		JLabel lblPrediction = new JLabel("Prediction:");
		lblPrediction.setBounds(45, 109, 80, 14);
		contentPane.add(lblPrediction);
		
		JLabel predictionString = new JLabel("");
		predictionString.setBounds(135, 109, 302, 14);
		predictionString.setText(prediction.getAnswer());
		contentPane.add(predictionString);
		
		JLabel lblMultiplier = new JLabel("Multiplier:");
		lblMultiplier.setBounds(45, 134, 80, 14);
		contentPane.add(lblMultiplier);
		
		JLabel multiplierField = new JLabel("");
		multiplierField.setBounds(135, 132, 216, 14);
		multiplierField.setText(Float.toString(prediction.getShare()));
		contentPane.add(multiplierField);
	}

	public void btnClose_actionPerformed(ActionEvent e, User u) {
		this.dispose();
	}
}
