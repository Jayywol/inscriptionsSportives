package utilisateur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import commandLineMenus.*;
import commandLineMenus.rendering.examples.util.InOut;
import static commandLineMenus.rendering.examples.util.InOut.getString;
import static commandLineMenus.rendering.examples.util.InOut.getInt;
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
		Menu menu = new Menu("Gérer les compétitions", "1");
		menu.add(ajouterCompetition());
		menu.add(afficherCompetitions());
		menu.add(selectionnerCompetition());
		menu.addBack("q");
		return menu;
	}
	
	private Option ajouterCompetition()
	
	{
		return new Option("Ajouter une compétition", "1", () -> {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateCloture = InOut.getString("Entrez la date de clôture 'yyyy-MM-dd' : ");
			Date localDate;
			try {
				localDate = formatter.parse(dateCloture);
				inscriptions.createCompetition(getString("nom : "),localDate,getInt("0 - Compétition de personnes \n1 - Compétition d'équipes : ")==0);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	private Option afficherCompetitions()
	{
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		return new Option("Afficher les compétitions", "2", () -> {System.out.println(inscriptions.getCompetitions());});
	}
	
	private List<Competition> selectionnerCompetition()
	{
		return new List<Competition>("Sélectionner une compétition", "3",() -> new ArrayList<>(inscriptions.getCompetitions()),(element) -> editerCompetition(element));
	}
	
	private Menu editerCompetition(Competition competition)
	{
		Menu menu = new Menu("Editer " + competition.getNom());
		menu.add(ajouterPersonneCompetition(competition));
		menu.add(ajouterEquipeCompetition(competition));
		menu.add(modifierCompetition(competition));
		menu.add(supprimer(competition));
		menu.addBack("q");
		return menu;
	}
	
	private Menu ajouterPersonneCompetition(Competition competition)
	{
		return new List<Personne>("Ajouter une personne", "1", 
				() -> new ArrayList<>(inscriptions.getPersonnes()),
				(index, element) -> {competition.add(element);}
				);
	}
	
	private Menu ajouterEquipeCompetition(Competition competition)
	{
		return new List<Equipe>("Ajouter une équipe", "2", 
				() -> new ArrayList<>(inscriptions.getEquipes()),
				(index, element) -> {competition.add(element);}
				);
	}
	
	private Option modifierCompetition(final Competition competition)
	{
		return new Option("Modifier compétition", "3", 
				() -> {competition.setNom(getString("Nouveau nom : "));});
	}
	
	private Option supprimer(Competition competition)
	{
		return new Option("Supprimer", "4", () -> {competition.delete();});
	}
	
	         
	private Menu menuEquipes()
	{
		Menu menu = new Menu("Gérer les équipes", "2");
		menu.add(ajouterEquipe());
		menu.add(afficherEquipes());
		menu.add(selectionnerEquipe());
		menu.addBack("q");
		return menu;
	}
	

	private Option ajouterEquipe()
	{
		return new Option("Ajouter une équipe", "1", () -> {inscriptions.createEquipe(getString("nom : "));});
	}
	
	private Option afficherEquipes()
	{
		return new Option("Afficher les équipes", "2", () -> {System.out.println(inscriptions.getEquipes());});
	}
	
	private List<Equipe> selectionnerEquipe()
	{
		return new List<Equipe>("Sélectionner une equipe", "3", () -> new ArrayList<>(inscriptions.getEquipes()),
				(element) -> editerEquipe(element));
	}
	
	private Menu editerEquipe(Equipe equipe)
	{
		Menu menu = new Menu("Editer " + equipe.getNom());
		menu.add(modifierEquipe(equipe));
		menu.add(supprimer(equipe));
		menu.add(ajouterMembre(equipe));
		menu.add(supprimerMembre(equipe));
		menu.addBack("q");
		return menu;
	}
	
	
	private Option modifierEquipe(final Equipe equipe)
	{
		return new Option("Renommer équipe", "1", 
				() -> {equipe.setNom(getString("Nouveau nom : "));});
	}
	

	private Option supprimer(Equipe equipe)
	{
		return new Option("Supprimer", "2", () -> {equipe.delete();});
	}

	private Menu ajouterMembre(Equipe equipe)
	{
		return new List<Personne>("Ajouter un membre", "3", 
				() -> new ArrayList<>(inscriptions.getPersonnes()),
				(index, element) -> {equipe.add(element);}
				);
	}
	
	private Menu supprimerMembre(Equipe equipe)
	{
		return new List<Personne>("Supprimer un membre", "4", 
				() -> new ArrayList<>(equipe.getMembres()),
				(index, element) -> {equipe.remove(element);}
				);
	}
	
	private Menu menuPersonnes()
	{
		Menu menu = new Menu("Gérer les personnes","3");
		menu.add(afficherPersonnes());
		menu.add(ajouterPersonne());
		menu.add(selectionnerPersonne());
		menu.addBack("q");
		return menu;
		
	}	
	
	private Option afficherPersonnes()
	{
		return new Option("Afficher les personnes", "1", () -> {System.out.println(inscriptions.getPersonnes());});
	}
	
	private Option ajouterPersonne()
	{	
		return new Option("Ajouter une personne", "2", () -> {inscriptions.createPersonne(getString("nom : "),getString("prenom : "),getString("mail : "));});
	}
	
	private List<Personne> selectionnerPersonne()
	{
		return new List<Personne>("Sélectionner une personne", "3", 
				() -> new ArrayList<>(inscriptions.getPersonnes()),
				(element) -> editerPersonne(element)
				);
	}
	
	private Menu editerPersonne(Personne personne)
	{
		Menu menu = new Menu("Editer " + personne.getNom());
		menu.add(modifierPersonne(personne));
		menu.add(supprimer(personne));
		menu.addBack("q");
		return menu;
	}
	
	private Option modifierPersonne(final Personne personne)
	{
		return new Option("Modifier personne", "1", 
				() -> {personne.setNom(getString("Nouveau nom : "));
					   personne.setPrenom(getString("Nouveau prenom : "));
					   personne.setMail(getString("Nouveau mail : "));});
	}
	
	private Option supprimer(Personne personne)
	{
		return new Option("Supprimer", "2", () -> {personne.delete();});
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
