package gui;

import javax.swing.*;

import domain.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainUserGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;


	protected JLabel jLabelSelectOption;
	private JPanel panel;
	private JButton btnReturn;
	private User u;
	private JButton UserZone;

	/**
	 * This is the default constructor
	 */
	public MainUserGUI(User u) {
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 311);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getPanel());
			jContentPane.add(getBtnReturn());
			jContentPane.add(getUserZone());
			jContentPane.add(getBoton3());
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 66, 481, 49);
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


	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(0, 1, 481, 68);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 164, 481, 68);
		}
		return panel;
	}

	@SuppressWarnings("unused")
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	private JButton getBtnReturn() {
		if (btnReturn == null) {
			btnReturn = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnReturn.setBounds(201, 243, 85, 21);
			btnReturn.setText("Return");
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
	private JButton getUserZone() {
		if (UserZone == null) {
			UserZone = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			UserZone.setText(u.getName()+"'s Zone");
			UserZone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					UserZoneGUI usZ= new UserZoneGUI(u);
					usZ.setVisible(true);
					
				}
			});
			UserZone.setBounds(0, 114, 481, 49);
		}
		return UserZone;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

