package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Event;
import domain.Prediction;
import domain.Question;
import domain.User;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class DirectAccessEventGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Event event;
	private User user;
	private Prediction prediction;
	private JFrame bet;

	private JLabel lbltuEquipoFavorito = new JLabel();
	private JLabel lblhazUnaApuesta = new JLabel("¡Haz tu apuesta!");
	private JLabel lblQuestions = new JLabel("Questions");
	private	JLabel lblPredictions = new JLabel("Predictions");
	private JButton btnApostar = new JButton("Apostar");
	private JButton btnVolver = new JButton("Volver");

	private JScrollPane scrollPaneQuestions = new JScrollPane();
	private DefaultTableModel tableModelQuestions;
	private JTable tableQuestions = new JTable();

	private JScrollPane scrollPanePredictions = new JScrollPane();
	private DefaultTableModel tableModelPredictions;
	private JTable tablePredictions = new JTable();


	private String[] columnNamePrediction = new String[] {
			"Prediction"
	};
	private String[] columnNamesQuestions = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")
	};
	private final JPanel panel = new JPanel();


	/**
	 * Create the frame.
	 */
	public DirectAccessEventGUI(Event e, User u) {
		setTitle("Bets&Ruin - Place bet favourite team");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 628, 519);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		BLFacade facade = StartGUI.getBusinessLogic();

		this.event=e;
		this.user=u;

		String[] events = event.getDescription().split("-");
		String fol="";
		String alt="";

		Vector<String> teams = facade.getFollowedTeams(u);

		if(teams.contains(events[0])) {
			fol=events[0];
			alt=events[1];
		} else {
			fol=events[1];
			alt=events[0];
		}
		lbltuEquipoFavorito.setForeground(new Color(61, 45, 20));
		lbltuEquipoFavorito.setFont(new Font("PT Sans", Font.BOLD, 20));

		lbltuEquipoFavorito.setText("Tu equipo favorito compite contra ");

		tableModelQuestions = new DefaultTableModel(null, columnNamesQuestions);
		try {
			tableModelQuestions.setDataVector(null, columnNamesQuestions);
			tableModelQuestions.setColumnCount(3); // another column added to allocate question objects
			Vector<Question> questions = facade.getQuestions(event);
			for (Question q: questions){
				Vector<Object> row = new Vector<Object>();
				System.out.println("Question "+q);
				row.add(q.getEvent());
				row.add(q.getQuestion());
				row.add(q); // question object added in order to obtain it with tableModelQuestions.getValueAt(i,2)
				tableModelQuestions.addRow(row);		
			}
		} catch (Exception e1) {

		}

		lbltuEquipoFavorito.setHorizontalAlignment(SwingConstants.CENTER);
		lbltuEquipoFavorito.setBounds(10, 18, 592, 30);
		contentPane.add(lbltuEquipoFavorito);
		lblhazUnaApuesta.setForeground(new Color(255, 189, 89));
		lblhazUnaApuesta.setFont(new Font("PT Sans", Font.BOLD, 20));

		lblhazUnaApuesta.setHorizontalAlignment(SwingConstants.CENTER);
		lblhazUnaApuesta.setBounds(10, 49, 592, 29);
		contentPane.add(lblhazUnaApuesta);
		btnApostar.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnApostar.setForeground(new Color(61, 45, 20));
		btnApostar.setBackground(new Color(255, 189, 89));
		btnApostar.setOpaque(true);
		btnApostar.setBorderPainted(false);
		btnApostar.setBounds(155, 444, 135, 41);
		contentPane.add(btnApostar);
		btnApostar.setEnabled(false);
		btnApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bet = new PlaceBetGUI(prediction, user);
				bet.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnVolver.setBackground(new Color(61, 45, 20));
		btnVolver.setForeground(new Color(255, 189, 89));
		btnVolver.setOpaque(true);
		btnVolver.setBorderPainted(false);
		btnVolver.setBounds(300, 444, 135, 41);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});
		contentPane.add(btnVolver);
		tableModelPredictions = new DefaultTableModel(null, columnNamePrediction);
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(20, 90, 582, 342);
		
		contentPane.add(panel);
		panel.setLayout(null);
		lblQuestions.setForeground(new Color(61, 45, 20));
		lblQuestions.setBounds(16, 6, 560, 23);
		panel.add(lblQuestions);
		lblQuestions.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblPredictions.setForeground(new Color(61, 45, 20));
		lblPredictions.setBounds(16, 177, 560, 23);
		panel.add(lblPredictions);
		lblPredictions.setFont(new Font("PT Sans", Font.BOLD, 16));
		tablePredictions.setFont(new Font("PT Sans", Font.PLAIN, 14));
		
		
		
				tablePredictions.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int i = tablePredictions.getSelectedRow();
						prediction = (Prediction) tableModelPredictions.getValueAt(i,1); // obtain prediction object
						if(prediction!=null) {
							btnApostar.setEnabled(true);
						}
					}
				});
						scrollPanePredictions.setBounds(16, 204, 546, 121);
						panel.add(scrollPanePredictions);
				
						scrollPanePredictions.setViewportView(tablePredictions);
						tablePredictions.setModel(tableModelPredictions);
		tableQuestions.setFont(new Font("PT Sans", Font.PLAIN, 14));
		scrollPaneQuestions.setBounds(16, 36, 545, 129);
		panel.add(scrollPaneQuestions);
		scrollPaneQuestions.setViewportView(tableQuestions);
		tableQuestions.setModel(tableModelQuestions);
		tableQuestions.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQuestions.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQuestions.getColumnModel().removeColumn(tableQuestions.getColumnModel().getColumn(2));

		tableQuestions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableQuestions.getSelectedRow();
				Question q = (Question) tableModelQuestions.getValueAt(i,2); // obtain question object

				BLFacade facade = StartGUI.getBusinessLogic();
				Vector<Prediction> predictions = (Vector<Prediction>) facade.getPredictions(q);


				tableModelPredictions.setDataVector(null, columnNamePrediction);
				tableModelPredictions.setColumnCount(2);

				if(predictions.isEmpty()) {
					tableModelPredictions.setRowCount(0);

				} else {

					for (Prediction p: predictions){
						Vector<Object> row = new Vector<Object>();
						row.add(p.getAnswer());
						row.add(p);
						tableModelPredictions.addRow(row);
					}
				}
				tablePredictions.getColumnModel().removeColumn(tablePredictions.getColumnModel().getColumn(1)); // not shown in JTable
			}
		});

	}

	public void close(ActionEvent e) {
		this.dispose();
	}
}
