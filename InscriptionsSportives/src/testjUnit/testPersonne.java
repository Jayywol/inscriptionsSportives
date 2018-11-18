package testjUnit;

import static org.junit.Assert.*;

import org.junit.Test;
 
import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class testPersonne
{
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Competition flechettes = inscriptions.createCompetition("Mondial de flechettes", null, false);
	Personne boris = inscriptions.createPersonne("Boris", "le Hachoir", "azerty");
	
    @Test
    public void testGetMail() {
        String mail = boris.getMail();

        assertEquals("GetMail : " + mail + " , " + boris.getMail(), mail, "azerty");
    }

    @Test
    public void testSetMail() {
        String mail = "1234";

        Personne boris = inscriptions.createPersonne("Boris", "le Hachoir", mail);

        assertEquals("SetMail : " + mail + " , " + boris.getMail(), boris.getMail(), mail);
    }
}
