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
import domain.User;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

public class CloseQuestionGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Create the frame.
	 */
	public CloseQuestionGUI(Question q, User user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBet = new JLabel("Result:");
		lblBet.setBounds(28, 88, 63, 13);
		contentPane.add(lblBet);

		JRadioButton firstButton = new JRadioButton("1");
		buttonGroup.add(firstButton);
		firstButton.setBounds(108, 83, 45, 21);
		contentPane.add(firstButton);

		JRadioButton tieButton = new JRadioButton("X");
		buttonGroup.add(tieButton);
		tieButton.setBounds(187, 83, 63, 21);
		contentPane.add(tieButton);

		JRadioButton secondButton = new JRadioButton("2");
		buttonGroup.add(secondButton);
		secondButton.setBounds(260, 83, 53, 21);
		contentPane.add(secondButton);

		JLabel submitted = new JLabel("");
		submitted.setHorizontalAlignment(SwingConstants.CENTER);
		submitted.setBounds(28, 117, 328, 13);
		contentPane.add(submitted);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(100, 152, 85, 21);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = StartGUI.getBusinessLogic();
				if (firstButton.isSelected()) { 
					facade.setQuestionResult(q, "1");
					submitted.setText("Result submitted successfully!");
					submitted.setForeground(Color.green);
				}
				else if (secondButton.isSelected()) { 
					facade.setQuestionResult(q, "2");
					submitted.setText("Result submitted successfully!");
					submitted.setForeground(Color.green);
				}
				else if (tieButton.isSelected()) { 
					facade.setQuestionResult(q, "X");
					submitted.setText("Result submitted successfully!");
					submitted.setForeground(Color.green);
				}
				else {
					submitted.setText("Error!");
					submitted.setForeground(Color.red);
				}
			}
		});

		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(228, 152, 85, 21);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClose_actionPerformed(e, user);
			}
		});
		contentPane.add(btnReturn);


		JLabel lblQuestion = new JLabel("Question:");
		lblQuestion.setBounds(28, 53, 80, 13);
		contentPane.add(lblQuestion);

		JLabel questionDesc = new JLabel("");
		questionDesc.setBounds(120, 53, 237, 13);
		questionDesc.setText(q.getQuestion());
		contentPane.add(questionDesc);
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
	}

	public void btnClose_actionPerformed(ActionEvent e, User u) {
		//FindQuestionsGUI f = new FindQuestionsGUI(u);
		//f.setVisible(true);
		this.dispose();
	}
}
