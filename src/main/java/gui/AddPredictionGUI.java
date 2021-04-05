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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblQuestion = new JLabel("Question:");
		lblQuestion.setBounds(49, 77, 67, 14);
		contentPane.add(lblQuestion);

		JLabel lblAnswer = new JLabel("Answer:");
		lblAnswer.setBounds(49, 105, 67, 14);
		contentPane.add(lblAnswer);

		JLabel questionString = new JLabel("");
		questionString.setBounds(139, 77, 274, 14);
		questionString.setText(question.getQuestion());
		contentPane.add(questionString);

		answerField = new JTextField();
		answerField.setBounds(139, 102, 159, 20);
		contentPane.add(answerField);
		answerField.setColumns(10);

		JLabel lblMultiplier = new JLabel("Share:");
		lblMultiplier.setBounds(49, 137, 67, 14);
		contentPane.add(lblMultiplier);

		shareField = new JTextField();
		shareField.setBounds(139, 134, 89, 20);
		contentPane.add(shareField);
		shareField.setColumns(10);

		JLabel submitted = new JLabel("");
		submitted.setBounds(10, 202, 414, 14);
		contentPane.add(submitted);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(104, 227, 89, 23);
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
		btnCancel.setBounds(209, 227, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});
		contentPane.add(btnCancel);

		JLabel lblAddPrediction = new JLabel("Add Prediction");
		lblAddPrediction.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddPrediction.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddPrediction.setBounds(10, 11, 414, 23);
		contentPane.add(lblAddPrediction);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setBounds(49, 51, 46, 14);
		contentPane.add(lblEvent);
		
		JLabel eventDesc = new JLabel("");
		eventDesc.setBounds(139, 51, 159, 14);
		eventDesc.setText(question.getEvent().getDescription());
		contentPane.add(eventDesc);
	}

	public void close(ActionEvent e) {
		this.dispose();
	}
}
