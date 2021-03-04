package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import businessLogic.BLFacade;
import domain.Question;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.ButtonGroup;

public class AddBetGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amountBet;
	private final ButtonGroup buttonGroup = new ButtonGroup();


	/**
	 * Create the frame.
	 */
	public AddBetGUI(Question q) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 506, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(45, 108, 80, 13);
		contentPane.add(lblAmount);

		amountBet = new JTextField();
		amountBet.setBounds(135, 105, 96, 19);
		contentPane.add(amountBet);
		amountBet.setColumns(10);

		JLabel lblBet = new JLabel("Bet:");
		lblBet.setBounds(45, 151, 45, 13);
		contentPane.add(lblBet);

		JRadioButton firstButton = new JRadioButton("1");
		buttonGroup.add(firstButton);
		firstButton.setBounds(135, 147, 103, 21);
		contentPane.add(firstButton);

		JRadioButton tieButton = new JRadioButton("X");
		buttonGroup.add(tieButton);
		tieButton.setBounds(240, 147, 103, 21);
		contentPane.add(tieButton);

		JRadioButton secondButton = new JRadioButton("2");
		buttonGroup.add(secondButton);
		secondButton.setBounds(345, 147, 103, 21);
		contentPane.add(secondButton);

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
						if(amount>=q.getBetMinimum()) {
							if(firstButton.isSelected()) facade.addBet("first", amount, q);
							else if(secondButton.isSelected()) facade.addBet("second", amount, q);
							else if(tieButton.isSelected()) facade.addBet("tie", amount, q);
							submitted.setText("Bet submitted successfully!");
							submitted.setForeground(Color.green);
						}
						else {
							submitted.setText("Error!");
							submitted.setForeground(Color.red);
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
				btnClose_actionPerformed(e);
			}
		});
		contentPane.add(btnReturn);


		JLabel lblQuestion = new JLabel("Question:");
		lblQuestion.setBounds(45, 85, 80, 13);
		contentPane.add(lblQuestion);

		JLabel questionDesc = new JLabel("");
		questionDesc.setBounds(135, 85, 347, 13);
		questionDesc.setText(q.getQuestion());
		contentPane.add(questionDesc);

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		textPane.setBounds(0, 185, 502, 19);
		textPane.setText("Be careful! The amount you want to bet should be at least "+q.getBetMinimum());
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		textPane.setParagraphAttributes(attribs, true);
		textPane.setForeground(Color.red);
		contentPane.add(textPane);
	}

	public void btnClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
