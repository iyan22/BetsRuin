package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Question;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class AddPredictionGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField answerField;
	private JTextField shareField;


	/**
	 * Create the frame.
	 */
	public AddPredictionGUI(Question question) {
		setTitle("Bets&Ruin - Add prediction");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 269);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel submitted = new JLabel("");
		submitted.setBounds(49, 171, 355, 14);
		contentPane.add(submitted);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(93, 182, 113, 35);
		btnSubmit.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnSubmit.setForeground(new Color(61, 45, 20));
		btnSubmit.setBackground(new Color(255, 189, 89));
		btnSubmit.setOpaque(true);
		btnSubmit.setBorderPainted(false);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!answerField.getText().equals("")) {
					try {
						float multiplier = Float.parseFloat(shareField.getText());
						BLFacade facade = StartGUI.getBusinessLogic();

						facade.addPrediction(question, answerField.getText(), multiplier);
						
						submitted.setText("Prediction submitted successfully!");
						submitted.setForeground(Color.green);

					} catch(NumberFormatException ex) {
						submitted.setText("Not valid format for the amount!");
						submitted.setForeground(Color.red);
					}
				} else {
					submitted.setText("Answer cannot be empty. Try again!");
					submitted.setForeground(Color.red);
				}
			}});
		contentPane.add(btnSubmit);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 182, 113, 35);
		btnCancel.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnCancel.setBackground(new Color(61, 45, 20));
		btnCancel.setForeground(new Color(255, 189, 89));
		btnCancel.setOpaque(true);
		btnCancel.setBorderPainted(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});
		contentPane.add(btnCancel);

		JLabel lblAddPrediction = new JLabel("Add Prediction");
		lblAddPrediction.setForeground(new Color(61, 45, 20));
		lblAddPrediction.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddPrediction.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblAddPrediction.setBounds(33, 11, 391, 24);
		contentPane.add(lblAddPrediction);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(33, 47, 391, 123);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setForeground(new Color(61, 45, 20));
		lblEvent.setBounds(19, 17, 89, 14);
		panel.add(lblEvent);
		lblEvent.setFont(new Font("PT Sans", Font.BOLD, 16));
		
		JLabel lblQuestion = new JLabel("Question:");
		lblQuestion.setForeground(new Color(61, 45, 20));
		lblQuestion.setBounds(19, 43, 89, 14);
		panel.add(lblQuestion);
		lblQuestion.setFont(new Font("PT Sans", Font.BOLD, 16));
		
		JLabel lblAnswer = new JLabel("Answer:");
		lblAnswer.setForeground(new Color(61, 45, 20));
		lblAnswer.setBounds(19, 69, 89, 14);
		panel.add(lblAnswer);
		lblAnswer.setFont(new Font("PT Sans", Font.BOLD, 16));
		
		JLabel lblMultiplier = new JLabel("Share:");
		lblMultiplier.setForeground(new Color(61, 45, 20));
		lblMultiplier.setBounds(19, 92, 89, 14);
		panel.add(lblMultiplier);
		lblMultiplier.setFont(new Font("PT Sans", Font.BOLD, 16));
				
		shareField = new JTextField();
		shareField.setBounds(120, 89, 89, 20);
		panel.add(shareField);
		shareField.setFont(new Font("PT Sans", Font.PLAIN, 14));
		shareField.setColumns(10);
		
		answerField = new JTextField();
		answerField.setBounds(120, 66, 247, 20);
		panel.add(answerField);
		answerField.setFont(new Font("PT Sans", Font.PLAIN, 14));
		answerField.setColumns(10);
		
		JLabel questionString = new JLabel("");
		questionString.setBounds(120, 43, 247, 14);
		panel.add(questionString);
		questionString.setFont(new Font("PT Sans", Font.PLAIN, 16));
		questionString.setText(question.getQuestion());
		
		JLabel eventDesc = new JLabel("");
		eventDesc.setBounds(120, 19, 247, 14);
		panel.add(eventDesc);
		eventDesc.setFont(new Font("PT Sans", Font.PLAIN, 16));
		eventDesc.setText(question.getEvent().getDescription());
	}

	public void close(ActionEvent e) {
		this.dispose();
	}
}
