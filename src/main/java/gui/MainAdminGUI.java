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
import javax.swing.ImageIcon;

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
	private JButton sendMail;
	
	/**
	 * Create the frame.
	 */
	public MainAdminGUI(User u) {
		super();
		setResizable(false);
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
		
		this.setSize(635, 557);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public JPanel getJContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setLayout(null);
			contentPane.add(getLblNewLabel());
			contentPane.add(getBoton2());
			contentPane.add(getBoton4());
			contentPane.add(getBoton3());
			contentPane.add(getBoton5());
			contentPane.add(getBtnReturn());
			
			JButton btnCloseEvent = new JButton();
			btnCloseEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new CloseEventGUI();
					a.setVisible(true);
				}
			});
			btnCloseEvent.setText("Close Event"); 
			btnCloseEvent.setBounds(36, 321, 548, 37);
			btnCloseEvent.setFont(new Font("PT Sans", Font.BOLD, 16));
			btnCloseEvent.setForeground(new Color(61, 45, 20));
			btnCloseEvent.setBackground(new Color(255, 189, 89));
			btnCloseEvent.setOpaque(true);
			btnCloseEvent.setBorderPainted(false);
			contentPane.add(btnCloseEvent);
			
			JLabel jLabelLogo = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
			jLabelLogo.setIcon(new ImageIcon(MainAdminGUI.class.getResource("/img/LogoBetsRuinL.png")));
			jLabelLogo.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelLogo.setForeground(Color.BLACK);
			jLabelLogo.setFont(new Font("PT Sans", Font.BOLD, 16));
			jLabelLogo.setBounds(36, 21, 194, 159);
			contentPane.add(jLabelLogo);
			contentPane.add(getSendMail());
		}
		return contentPane;
	}
	
	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel("<html>ADMIN ZONE</html>");
			jLabelSelectOption.setBounds(269, 21, 315, 159);
			jLabelSelectOption.setFont(new Font("PT Sans", Font.BOLD, 70));
			jLabelSelectOption.setForeground(new Color(61, 45, 20));
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	private JButton getBoton3() {
		if (jButtonQueryQuestions == null) {
			jButtonQueryQuestions = new JButton();
			jButtonQueryQuestions.setBounds(36, 225, 548, 37);
			jButtonQueryQuestions.setFont(new Font("PT Sans", Font.BOLD, 16));
			jButtonQueryQuestions.setForeground(new Color(61, 45, 20));
			jButtonQueryQuestions.setBackground(new Color(255, 189, 89));
			jButtonQueryQuestions.setOpaque(true);
			jButtonQueryQuestions.setBorderPainted(false);
			jButtonQueryQuestions.setText("Query Queries");
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
			jButtonCreateQuery.setBounds(36, 417, 269, 37);
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			jButtonCreateQuery.setFont(new Font("PT Sans", Font.BOLD, 16));
			jButtonCreateQuery.setForeground(new Color(61, 45, 20));
			jButtonCreateQuery.setBackground(new Color(255, 189, 89));
			jButtonCreateQuery.setOpaque(true);
			jButtonCreateQuery.setBorderPainted(false);
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
			jButtonCreateEvent.setBounds(36, 273, 548, 37);
			jButtonCreateEvent.setText("Create Event");
			jButtonCreateEvent.setFont(new Font("PT Sans", Font.BOLD, 16));
			jButtonCreateEvent.setForeground(new Color(61, 45, 20));
			jButtonCreateEvent.setBackground(new Color(255, 189, 89));
			jButtonCreateEvent.setOpaque(true);
			jButtonCreateEvent.setBorderPainted(false);
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
			jButtonCreatePrediction.setBounds(317, 417, 267, 37);
			jButtonCreatePrediction.setText("Create Prediction");
			jButtonCreatePrediction.setFont(new Font("PT Sans", Font.BOLD, 16));
			jButtonCreatePrediction.setForeground(new Color(61, 45, 20));
			jButtonCreatePrediction.setBackground(new Color(255, 189, 89));
			jButtonCreatePrediction.setOpaque(true);
			jButtonCreatePrediction.setBorderPainted(false);
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
			btnReturn.setFont(new Font("PT Sans", Font.BOLD, 16));
			btnReturn.setForeground(new Color(255, 189, 89));
			btnReturn.setBackground(new Color(61, 45, 20));
			btnReturn.setOpaque(true);
			btnReturn.setBorderPainted(false);
			btnReturn.setBounds(36, 465, 548, 42);
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
	private JButton getSendMail() {
		if (sendMail == null) {
			sendMail = new JButton();
			sendMail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SendMailOfferGUI of=new SendMailOfferGUI(u);
					of.setVisible(true);
				}
			});
			sendMail.setText(ResourceBundle.getBundle("Etiquetas").getString("btnSendMail")); //$NON-NLS-1$ //$NON-NLS-2$
			sendMail.setOpaque(true);
			sendMail.setForeground(new Color(61, 45, 20));
			sendMail.setFont(new Font("Dialog", Font.BOLD, 16));
			sendMail.setBorderPainted(false);
			sendMail.setBackground(new Color(255, 189, 89));
			sendMail.setBounds(36, 369, 548, 37);
		}
		return sendMail;
	}
}
