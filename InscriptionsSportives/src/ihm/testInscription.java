package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class testInscription extends JFrame {

	private JPanel contentPane;
	private JTextField saisieSport;
	private JButton valider = new JButton("Valider");
	Map<String, Integer> competitions = new HashMap<String, Integer>();
	Map<String, Integer> candidats = new HashMap<String, Integer>();
	Map<String, Integer> equipes = new HashMap<String, Integer>();
	static testInscription frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new testInscription();
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
	public testInscription() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setTitle("Application");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TitledBorder border = new TitledBorder("Inscription");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		contentPane.setBorder(border);

		JLabel Nom = new JLabel("Votre nom");
		Nom.setBounds(78, 49, 87, 20);
		contentPane.add(Nom);
		

		String[] candidatsTab = getCandidatsFromDB();
		JComboBox<String> listeCandidats = new JComboBox<>(candidatsTab);
		listeCandidats.setBounds(195, 49, 98, 20);
		contentPane.add(listeCandidats);
		
		JLabel Sport = new JLabel("Compétition");
		Sport.setBounds(78, 81, 70, 14);
		contentPane.add(Sport);


		String[] competitionsTab = getCompetitionsFromDB();
		JComboBox<String> listeCompet = new JComboBox<>(competitionsTab);
		listeCompet.setBounds(195, 78, 98, 20);
		contentPane.add(listeCompet);

		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Object nom = listeCandidats.getSelectedObjects()[0];
				int idCandidat = candidats.get(nom);
				Object competition = listeCompet.getSelectedObjects()[0];
				int idCompetition = competitions.get(competition);
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/inscriptionsportive","root",null);  
					Statement stmt=con.createStatement();
					// the mysql insert statement
					String query = "INSERT INTO `constituer`(`Num_Candidat`, `Num_Competition`)"
							+ " values (?, ? )";

					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					preparedStmt.setInt(1, idCandidat);
					preparedStmt.setInt(2, idCompetition);

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

	
	private String[] getCandidatsFromDB()
	{
		ArrayList<String> result = new ArrayList<String>(); 

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/inscriptionsportive","root",null);  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from candidat");  
			while(rs.next()) {
				result.add(rs.getString(2));  
				candidats.put(rs.getString(2), rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  

		return result.toArray(new String[result.size()]); 
	}

	private String[] getCompetitionsFromDB()
	{
		ArrayList<String> result = new ArrayList<String>(); 

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/inscriptionsportive","root",null);  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from competition");  
			while(rs.next()) {
				result.add(rs.getString(2));  
				competitions.put(rs.getString(2), rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  

		return result.toArray(new String[result.size()]); 
	}
}
