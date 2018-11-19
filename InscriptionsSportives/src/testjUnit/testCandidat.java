package testjUnit;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class testCandidat
{
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Competition flechettes = inscriptions.createCompetition("Mondial de flechettes", null, false);
	Personne boris = inscriptions.createPersonne("Boris", "le Hachoir", "ytreza");
	
	@Test
	public void testGetNom()
	{
		assertEquals("Boris", boris.getNom());
	}
	
	@Test
	public void testSetNom()
	{
		boris.setNom("Test");
		assertEquals("Test", boris.getNom());
	}
}
