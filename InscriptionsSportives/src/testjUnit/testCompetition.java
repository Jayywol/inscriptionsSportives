package testjUnit;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.TreeSet;

import org.junit.Test;
 
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class testCompetition
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
	
	@Test
	public void testInscriptionsOuvertes()
	{
		fail("Not yet implemented"); //TODO
	}
	
	@Test
	public void testGetDateCloture()
	{
		assertEquals("", LocalDate.getDateCloture());
	}
}