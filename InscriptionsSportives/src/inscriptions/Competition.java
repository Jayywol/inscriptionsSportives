package inscriptions;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;

import java.util.Set;
import java.util.TreeSet;

/*
 * Represente une competition, c'est a dire un ensemble de candidat
 * inscrit a un evenement, les inscritpions sont closes a la date dateCloture
 * 
 */

public class Competition implements Comparable<Competition>, Serializable
{
	private static final long serialVersionUID = -2882150118573759729L;
	private Inscriptions inscriptions;
	private String nom;
	private Set<Candidat> candidats;
	private Date dateCloture;
	private boolean enEquipe = false;

	Competition(Inscriptions inscriptions, String nom, Date dateCloture, boolean enEquipe)
	{
		this.enEquipe = enEquipe;
		this.inscriptions = inscriptions;
		this.nom = nom;
		this.dateCloture = dateCloture;
		candidats = new TreeSet<>();
	}
	
	/*
	 * Retourne le nom de la competition
	 * @return
	 */
	
	public String getNom()
	{
		return nom;
	}
	
	/*
	 * Modifie le nom de la competition
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom ;
	}
	
	/*
	 * Retourne vrai si les inscriptions sont encore ouvertes, 
	 * faux si les inscriptions sont closes
	 * @return
	 */
	
	public boolean inscriptionsOuvertes() {
	Date date = new Date();
	try {
	if(date.after(getDateCloture()))
		return false;
	}
	catch(Exception e)
	{
		System.out.println("La date n'est pas donn�e !");
	}
	return true;
}
	
	/*
	 * Retourne la date de cloture des inscriptions
	 * @return
	 */
	
	public Date getDateCloture()
	{
		return dateCloture;
	}
	
	/*
	 * Retourne vrai si et si seulement les inscriptions sont reservers aux equipes
	 * @return
	 */
	
	public boolean estEnEquipe()
	{
		return enEquipe;
	}
	
	/*
	 * Modifie la date de cloture des inscriptions. Il est possible de la reculer 
	 * mais pas de l'avancer
	 * @param dateCloture
	 */
	
	public void setDateCloture(Date dateCloture)
	{
		// TODO Verifier que l'on avance pas la date
		if(dateCloture.compareTo(this.dateCloture) != 1)
		{
			System.out.println("Il est impossible d avancer la date de cloture");
		}
		else
			this.dateCloture = dateCloture;
	}
	
	/*
	 * Retourne l'ensemble des candidats inscrits
	 * @return
	 */
	
	public Set<Candidat> getCandidats()
	{
		return Collections.unmodifiableSet(candidats);
	}
	
	/*
	 * Inscrit un candidat de type Personne a la competition 
	 * Provoque une exception si la competition est reservee aux equipes
	 * ou que les inscriptions sont closes
	 * @param personne
	 * @return
	 */
	
	public boolean add(Personne personne)
	{
		// TODO Verifier que la date de cloture n'est pas passee
			if (enEquipe)
				throw new RuntimeException();
			personne.add(this);		
			return candidats.add(personne);
	}

	/*
	 * Inscrit un candidat de type Equipe à la compétition. Provoque une
	 * exception si la compétition est réservée aux personnes ou que 
	 * les inscriptions sont closes
	 * @param personne
	 * @return
	 */

	public boolean add(Equipe equipe)
	{
		// TODO Verifier que la date de cloture n'est pas passee
			if (!enEquipe)
				throw new RuntimeException();
			equipe.add(this);
			return candidats.add(equipe);
	}
	
	/*
	 * Retourne les personnes que 'lon peut inscrire a cette competition
	 * @return les personnes que l'on peut inscrire a cette competition
	 */
	
	public Set<Personne> getPersonnesAInscrire()
	{
		// TODO retourner les personnes que l'on peut inscrire a cette competition
		return null;
	}

	/*
	 * Desinscrit un candidat
	 * @param candidat
	 * @return
	 */
	
	public boolean remove(Candidat candidat)
	{
		candidat.remove(this);
		return candidats.remove(candidat);
	}
	
	/*
	 * Supprime la competition de l'application
	 */
	
	public void delete()
	{
		for (Candidat candidat : candidats)
			remove(candidat);
		inscriptions.delete(this);
	}
	
	@Override
	public int compareTo(Competition o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return getNom();
	}
}
