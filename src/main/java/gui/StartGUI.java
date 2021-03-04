package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;

import javax.swing.JButton;

public class StartGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	private JButton jButtonLogin;
	private JButton jButtonRegister;
	private JButton btnClose;
	protected JLabel jLabelSelectOption;


	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}

	/**
	 * Create the frame.
	 */
	public StartGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Start"));
		
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton1());
			jContentPane.add(getBoton2());
			jContentPane.add(getClose());
			
		}
		return jContentPane;
	}

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(0, 0, 481, 63);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	private JButton getClose() {
		if(btnClose==null) {
			btnClose = new JButton("Close");
			btnClose.setBounds(197, 222, 85, 21);
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close(e);
				}
			});
			
		}
		return btnClose;
	}

	private JButton getBoton1() {
		if (jButtonLogin == null) {
			jButtonLogin = new JButton();
			jButtonLogin.setBounds(0, 63, 481, 63);
			jButtonLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new LoginGUI();
					closeWindow(e);
					a.setVisible(true);
					
				}
			});
			this.dispose();
		}
		return jButtonLogin;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonRegister == null) {
			jButtonRegister = new JButton();
			jButtonRegister.setBounds(0, 126, 481, 63);
			jButtonRegister.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
			jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new RegisterGUI();
					closeWindow(e);
					a.setVisible(true);
				}
			});
		}
		return jButtonRegister;
	}
	
	private void closeWindow(ActionEvent e) {
		this.setVisible(false);
	}
	
	public void close(ActionEvent e) {
		this.dispose();
	}
}
