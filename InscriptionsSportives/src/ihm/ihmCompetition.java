package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; 
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ihmCompetition extends JFrame {

	private JPanel contentPane;
	private JTextField saisieNom;
	private JTextField saisieDate;
	private static ihmCompetition frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ihmCompetition();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ihmCompetition() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setTitle("Application");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JFrame.setDefaultLookAndFeelDecorated(true);

		TitledBorder border = new TitledBorder("Création d'une compétition");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);

		contentPane.setBorder(border);

		JLabel Compétition = new JLabel("Comp\u00E9tition");
		Compétition.setBounds(78, 49, 87, 20);
		contentPane.add(Compétition);

		saisieNom = new JTextField();
		saisieNom.setBounds(195, 49, 98, 20);
		contentPane.add(saisieNom);
		saisieNom.setColumns(10);

		JLabel dateCloture = new JLabel("Date cloture");
		dateCloture.setBounds(78, 81, 70, 14);
		contentPane.add(dateCloture);

		saisieDate = new JTextField();
		saisieDate.setBounds(195, 78, 98, 20);
		contentPane.add(saisieDate);
		saisieDate.setColumns(10);

		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/inscriptionsportive","root",null);  
					Statement stmt=con.createStatement();
					// the mysql insert statement
					String query = "INSERT INTO `competition`(`Nom_Competition`, `DateCloture_Comp`)"
							+ " values (?, ? )";

					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					preparedStmt.setString(1, saisieNom.getText());
					preparedStmt.setString(2, saisieDate.getText());

					// execute the preparedstatement
					preparedStmt.execute();
					
					frame.setVisible(false);

					con.close();  

				} catch (Exception e) {
					e.printStackTrace();
				}  
			}
		});
		valider.setEnabled(true);
		valider.setBounds(167, 192, 89, 23);
		contentPane.add(valider);
	}
}
