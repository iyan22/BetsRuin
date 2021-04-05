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
	private JButton jButtonManageEvents = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	protected JLabel jLabelSelectOption;
	private JButton btnReturn;
	private User u;
	
	/**
	 * Create the frame.
	 */
	public MainAdminGUI(User u) {
		
		super();
		this.u=u;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(0);
			}
		});

		initialize();
	}

	public void initialize() {
		
		this.setSize(553, 391);
		this.setContentPane(getJContentPane());
		this.setTitle("Main Admin");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public JPanel getJContentPane() {
		if(contentPane==null) {
			contentPane = new JPanel();
			contentPane.setLayout(null);
			contentPane.add(getLblNewLabel());
			contentPane.add(getBoton2());
			contentPane.add(getBoton4());
			contentPane.add(getBoton3());
			contentPane.add(getBtnReturn());
			
			JButton jButtonCloseQuestion = new JButton();
			jButtonCloseQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new ManageQuestionsGUI(u);
					a.setVisible(true);
				}
			});
			jButtonCloseQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.jButtonCloseQuestion.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonCloseQuestion.setBounds(31, 250, 481, 49);
			contentPane.add(jButtonCloseQuestion);
		}
		return contentPane;
	}
	
	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(6, 6, 539, 68);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(31, 70, 481, 49);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI(u);

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setBounds(31, 190, 481, 49);
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	
	private JButton getBoton4() {
		if(jButtonManageEvents == null) {
			jButtonManageEvents = new JButton();
			jButtonManageEvents.setBounds(31, 129, 481, 49);
			jButtonManageEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.jButtonManageEvents.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonManageEvents.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ManageEventGUI();
					a.setVisible(true);
				}
			});
		}
		return jButtonManageEvents;
	}
	
	private JButton getBtnReturn() {
		if (btnReturn == null) {
			btnReturn = new JButton();
			btnReturn.setBackground(Color.WHITE);
			btnReturn.setText("Return");
			btnReturn.setBounds(236, 322, 85, 21);
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
