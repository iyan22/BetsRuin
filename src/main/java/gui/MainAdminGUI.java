package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Event;
import domain.User;

import javax.swing.SwingConstants;

public class MainAdminGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane=null;
	private JButton jButtonCreateEvent = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQuestions = null;
	private JButton jButtonCreatePrediction = null;
	protected JLabel jLabelSelectOption;
	private JButton btnReturn;
	private User u;
	
	/**
	 * Create the frame.
	 */
	public MainAdminGUI(User u) {
		super();
		this.u = u;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(0);
			}
		});

		initialize();
	}

	public void initialize() {
		
		this.setSize(635, 337);
		this.setContentPane(getJContentPane());
		this.setTitle("Main Admin");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public JPanel getJContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setLayout(null);
			contentPane.add(getLblNewLabel());
			contentPane.add(getBoton2());
			contentPane.add(getBoton4());
			contentPane.add(getBoton3());
			contentPane.add(getBoton5());
			contentPane.add(getBtnReturn());
			
			JButton btnCloseEvent = new JButton();
			btnCloseEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new CloseEventsGUI();

					a.setVisible(true);
				}
			});
			btnCloseEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.btnCloseEvent.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCloseEvent.setBounds(317, 116, 269, 37);
			contentPane.add(btnCloseEvent);
			
			JButton btnCloseQuestion = new JButton();
			btnCloseQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new CloseQuestionsGUI();

					a.setVisible(true);
				}
			});
			btnCloseQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.btnCloseQuestion.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCloseQuestion.setBounds(317, 155, 269, 37);
			contentPane.add(btnCloseQuestion);
		}
		return contentPane;
	}
	
	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(75, 6, 481, 55);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	private JButton getBoton3() {
		if (jButtonQueryQuestions == null) {
			jButtonQueryQuestions = new JButton();
			jButtonQueryQuestions.setBounds(36, 70, 548, 37);
			jButtonQueryQuestions.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQuestions.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FindQuestionsGUI(u);

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQuestions;
	}
	
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setBounds(36, 155, 269, 37);
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	
	private JButton getBoton4() {
		if (jButtonCreateEvent == null) {
			jButtonCreateEvent = new JButton();
			jButtonCreateEvent.setBounds(36, 116, 269, 37);
			jButtonCreateEvent.setText("Create Event");
			jButtonCreateEvent.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new CreateEventGUI();
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateEvent;
	}
	
	private JButton getBoton5() {
		if(jButtonCreatePrediction == null) {
			jButtonCreatePrediction = new JButton();
			jButtonCreatePrediction.setBounds(36, 194, 269, 37);
			jButtonCreatePrediction.setText("Create Prediction");
			jButtonCreatePrediction.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new CreatePredictionGUI();
					a.setVisible(true);
				}
			});
			
		}
		return jButtonCreatePrediction;
	}
	
	private JButton getBtnReturn() {
		if (btnReturn == null) {
			btnReturn = new JButton();
			btnReturn.setText("Return");
			btnReturn.setBounds(264, 260, 85, 21);
			btnReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close(e);
				}
			});
		}
		return btnReturn;
	}
	
	public void close(ActionEvent e) {
		JFrame a = new StartGUI();
		a.setVisible(true);
		this.dispose();
	}
}
