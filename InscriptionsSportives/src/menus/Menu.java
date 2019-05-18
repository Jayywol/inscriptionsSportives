package menus;

import java.util.Scanner;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class Menu {

	public static void main(String[] args) {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		
		while(true) {
			System.out.println("Choisissez une action : ");
			System.out.println("1 : Créer une compétition");
			System.out.println("2 : Créer une équipe ");
			System.out.println("3 : Créer un joueur ");

			Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();
			sc.nextLine();
			
			Personne personne;
			Equipe equipe;
			Competition competition;
			
			switch(choix) {
				case 1 : 
					System.out.println("rentrer le nom de la compétition:");
					String nom = sc.nextLine();
					sc.nextLine();
					System.out.println("Compétion en équipe : oui/non");
					String enEquipe = sc.nextLine();
					System.out.println("Date cloture :");
					String dateCloture = sc.nextLine();
					
					
					if (enEquipe == "oui") {
						competition = inscriptions.createCompetition(nom, null, true); 
					} else {
						competition = inscriptions.createCompetition(nom, null, false); 
					}
					break;
				case 2 : 
					System.out.println("rentrer le nom de l'équipe:");
					String nomEquipe = sc.nextLine();
					equipe = inscriptions.createEquipe(nomEquipe);
					break;
				case 3 :
					System.out.println("rentrer le nom de la personne:");
					String nomPersonne = sc.nextLine();
					sc.nextLine();
					System.out.println("rentrer le prénom de la personne:");
					String prenomPersonne = sc.nextLine();
					sc.nextLine();
					System.out.println("rentrer l'email de la personne:");
					String emailPersonne = sc.nextLine();
					
					personne = inscriptions.createPersonne(nomPersonne,prenomPersonne,emailPersonne);
					break;
				default : 
					System.out.println("Option inconnue. Veuillez réessayer");
			}
		}
	}
}
