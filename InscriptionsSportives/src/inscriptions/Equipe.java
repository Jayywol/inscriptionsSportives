package inscriptions;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/*
 * Represente une equipe. C'est a dire une ensemble de personnes pouvant
 * s'inscrire a une competition
 */

public class Equipe extends Candidat
{
	private static final long serialVersionUID = 4147819927233466035L;
	private SortedSet<Personne> membres = new TreeSet<>();
	
	Equipe(Inscriptions inscriptions, String nom)
	{
		super(inscriptions, nom);
	}

	/*
	 * Retourne l'ensemble des personnes formant l'equipe
	 */
	
	public SortedSet<Personne> getMembres()
	{
		return Collections.unmodifiableSortedSet(membres);
	}
	
	/*
	 * Ajoute une personne l'equipe
	 * @param membre
	 * @return
	 */

	public boolean add(Personne membre)
	{
		membre.add(this);
		return membres.add(membre);
	}

	/*
	 * Supprime une personne de l'equipe
	 * @param membre
	 * @return
	 */
	
	public boolean remove(Personne membre)
	{
		membre.remove(this);
		return membres.remove(membre);
	}

	/*
	 * Retourn les personnes que l'on peut ajouter dans cette equipe
	 * @return
	 */
	
	public Set<Personne> getPersonnesAAjouter()
	{
		// TODO Retourner les personnes que l'on peut ajouter dans cette equipe
		return null;
	}
	
	@Override
	public void delete()
	{
		super.delete();
	}
	
	@Override
	public String toString()
	{
		return "Equipe " + super.toString();
	}
}
