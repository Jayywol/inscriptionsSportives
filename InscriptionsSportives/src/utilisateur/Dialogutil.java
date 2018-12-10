package utilisateur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import commandLineMenus.*;
import commandLineMenus.rendering.examples.util.InOut;
import static commandLineMenus.rendering.examples.util.InOut.getString;
import inscriptions.*;


public class Dialogutil {

	private Inscriptions inscriptions;
	
	public Dialogutil(Inscriptions inscriptions)
	
	{
		this.inscriptions = inscriptions;
	}
	
	public void start()
	{
		menuPrincipal().start();
	}
	
	private Menu menuPrincipal()
	{
		Menu menu = new Menu("Menu Principal");
		menu.add(menuCompetitions());
		menu.add(menuEquipes());
		menu.add(menuPersonnes());
		menu.add(menuQuitter());
		return menu;
	}
	
	private Menu menuQuitter()
	{
		Menu menu = new Menu("Quitter", "q");
		menu.add(quitterEtEnregistrer());
		menu.add(quitterSansEnregistrer());
		menu.addBack("r");
		return menu;
	}
	
	private Menu menuCompetitions()
	{
		Menu menu = new Menu("G�rer les comp�titions", "1");
	    menu.add(afficherCompetitions());
		menu.add(ajouterCompetition());
		menu.add(selectionnerCompetition());
		menu.addBack("q");
		return menu;
	}
	
	private Option ajouterCompetition()
	
	{
		return new Option("Ajouter une comp�tition", "1", () -> {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateCloture = InOut.getString("Entrez la date de cl�ture 'yyyy-MM-dd' : ");
			Date localDate;
			try {
				localDate = formatter.parse(dateCloture);
				inscriptions.createCompetition(getString("nom : "),localDate,getInt("0 - Comp�tition de personnes \n1 - Comp�tition d'�quipes : ")==0);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	private int getInt(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	private Option afficherCompetitions()
	{
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		return new Option("Afficher les comp�titions", "2", () -> {System.out.println(inscriptions.getCompetitions());});
	}
	
	private List<Competition> selectionnerCompetition()
	{
		return new List<Competition>("S�lectionner une comp�tition", "3",() -> new ArrayList<>(inscriptions.getCompetitions()),(element) -> editerCompetition(element));
	}
	
	private Menu editerCompetition(Competition competition)
	{
		Menu menu = new Menu("Editer " + competition.getNom());
		//menu.add(afficherCandidats(competition));
		//menu.add(ajouterPersonneCompetition(competition));
		//menu.add(ajouterEquipeCompetition(competition));
		//menu.add(supprimerCandidat(competition));
		//menu.add(modifierCompetition(competition));
		//menu.add(supprimer(competition));
		menu.addBack("q");
		return menu;
	}
	         
	private Menu menuEquipes()
	{
		Menu menu = new Menu("G�rer les �quipes", "2");
		menu.add(afficherEquipes());
		menu.add(ajouterEquipe());
		//menu.add(selectionnerEquipe());
		menu.addBack("q");
		return menu;
	}
	
	private Option ajouterEquipe()
	{
		return new Option("Ajouter une �quipe", "a", () -> {inscriptions.createEquipe(getString("nom : "));});
	}
	
	private Option afficherEquipes()
	{
		return new Option("Afficher les �quipes", "l", () -> {System.out.println(inscriptions.getEquipes());});
	}
	
	private Menu menuPersonnes()
	{
		Menu menu = new Menu("G�rer les personnes","3");
		menu.add(afficherPersonnes());
		menu.add(ajouterPersonne());
		//menu.add(selectionnerPersonne());
		menu.addBack("q");
		return menu;
		
	}	
	
	private Option ajouterPersonne()
	{	
		return new Option("Ajouter une personne", "a", () -> {inscriptions.createPersonne(getString("nom : "),getString("prenom : "),getString("mail : "));});
	}
	
	private Option afficherPersonnes()
	{
		return new Option("Afficher les personnes", "l", () -> {System.out.println(inscriptions.getPersonnes());});
	}
	
	
	private Option quitterSansEnregistrer()
	{
		return new Option("Quitter sans enregistrer", "a", Action.QUIT);
	}

	private Option quitterEtEnregistrer()
    {
        return new Option("Quitter et enregistrer", "q", 
                () -> 
                {
                    try
                    {
                        inscriptions.sauvegarder();
                        Action.QUIT.optionSelected();
                    } 
                    catch (IOException e)
                    {
                        System.out.println("Impossible d'effectuer la sauvegarde");
                    }
                }
            );
    }
	
	public static void main(String[] args)
	{
Inscriptions inscriptions = Inscriptions.getInscriptions();
		Dialogutil personnelConsole = new Dialogutil(inscriptions);
			personnelConsole.start();
		
	}
	
	
}
