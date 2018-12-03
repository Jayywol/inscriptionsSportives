package utilisateur;

import java.io.IOException;
import commandLineMenus.*;
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
		Menu menu = new Menu("Gérer les compétitions", "c");
	//	menu.add(afficherCompetitions());
		//menu.add(ajouterCompetition());
		//menu.add(selectionnerCompetition());
		menu.addBack("q");
		return menu;
	}
	
	private Menu menuEquipes()
	{
		Menu menu = new Menu("Gérer les équipes", "e");
		//menu.add(afficherEquipes());
		//menu.add(ajouterEquipe());
		//menu.add(selectionnerEquipe());
		menu.addBack("q");
		return menu;
	}
	
	private Menu menuPersonnes()
	{
		Menu menu = new Menu("Gérer les personnes","p");
		//menu.add(afficherPersonnes());
		//menu.add(ajouterPersonne());
		//menu.add(selectionnerPersonne());
		menu.addBack("q");
		return menu;
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
