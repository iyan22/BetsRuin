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

public class DirectAccessEventGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Event event;
	private User user;
	private Prediction prediction;
	private JFrame bet;

	private JLabel lbltuEquipoFavorito = new JLabel();
	private JLabel lblhazUnaApuesta = new JLabel("\u00A1Haz una apuesta!");
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


	/**
	 * Create the frame.
	 */
	public DirectAccessEventGUI(Event e, User u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 628, 481);
		contentPane = new JPanel();
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

		lbltuEquipoFavorito.setText("Tu equipo favorito " + fol + " compite contra "+alt);

		tableModelQuestions = new DefaultTableModel(null, columnNamesQuestions);
		scrollPaneQuestions.setViewportView(tableQuestions);
		tableQuestions.setModel(tableModelQuestions);
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
			tableQuestions.getColumnModel().getColumn(0).setPreferredWidth(25);
			tableQuestions.getColumnModel().getColumn(1).setPreferredWidth(268);
			tableQuestions.getColumnModel().removeColumn(tableQuestions.getColumnModel().getColumn(2)); // not shown in JTable
		} catch (Exception e1) {

		}

		lbltuEquipoFavorito.setHorizontalAlignment(SwingConstants.CENTER);
		lbltuEquipoFavorito.setBounds(10, 34, 592, 14);
		contentPane.add(lbltuEquipoFavorito);

		lblhazUnaApuesta.setHorizontalAlignment(SwingConstants.CENTER);
		lblhazUnaApuesta.setBounds(10, 59, 592, 14);
		contentPane.add(lblhazUnaApuesta);

		lblQuestions.setBounds(21, 103, 120, 14);
		contentPane.add(lblQuestions);

		scrollPaneQuestions.setBounds(20, 125, 582, 105);
		contentPane.add(scrollPaneQuestions);

		lblPredictions.setBounds(21, 265, 284, 14);
		contentPane.add(lblPredictions);

		scrollPanePredictions.setBounds(21, 290, 581, 105);
		contentPane.add(scrollPanePredictions);



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

		btnApostar.setBounds(200, 408, 89, 23);
		contentPane.add(btnApostar);
		btnApostar.setEnabled(false);
		btnApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bet = new PlaceBetGUI(prediction, user);
				bet.setVisible(true);
			}
		});

		btnVolver.setBounds(299, 408, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});
		contentPane.add(btnVolver);

		scrollPanePredictions.setViewportView(tablePredictions);
		tableModelPredictions = new DefaultTableModel(null, columnNamePrediction);
		tablePredictions.setModel(tableModelPredictions);

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
