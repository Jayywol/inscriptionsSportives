package testjUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;
import inscriptions.Equipe;

public class testInscriptions
{
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Competition flechettes = inscriptions.createCompetition("Mondial de flechettes", null, false);
	Personne boris = inscriptions.createPersonne("Boris", "le Hachoir", "ytreza");
	Equipe paris = inscriptions.createEquipe("Paris");
	
	@Test
	public void testGetCompetitions()
	{
		assertTrue(inscriptions.getCompetitions().contains(flechettes));
	}
	
	@Test
	public void testGetCandidat()
	{
		assertTrue(inscriptions.getCandidats().contains(boris));
	}
	
	@Test
	public void testGetPersonnes()
	{
		assertTrue(inscriptions.getPersonnes().contains(boris));
	}
	
	@Test
	public void testGetEquipes()
	{
		assertTrue(inscriptions.getEquipes().contains(paris));
	}
	
	@Test
	public void testCreateCompetition()
	{	
		Competition foot = inscriptions.createCompetition("Mondial de football", null, false);
		assertTrue(inscriptions.getCompetitions().contains(foot));
	}
	
	@Test
	public void testCreatePersonne()
	{
		Personne alex = inscriptions.createPersonne("Alex", "Andre", "outlook");
		assertTrue(inscriptions.getPersonnes().contains(alex));
	}
	
	@Test
	public void testCreateEquipe()
	{
		Equipe marseille = inscriptions.createEquipe("Marseille");
		assertTrue(inscriptions.getEquipes().contains(marseille));
	}

}
