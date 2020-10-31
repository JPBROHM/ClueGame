package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Suspect;

class GameSetupTests {
	
	private static Board board;
	private static int numWeapons = 6;
	private static int numPlayers = 6;
	private static int numComputers = 5;

	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
	}
	
	
	@Test
	public void testPeopleWeapons() {
		//test load weapons
		Set<String> weaponsList = board.getWeapons();
		assertEquals(numWeapons, weaponsList.size());
		assertFalse(weaponsList.contains("Colonel Mustard"));
		assertTrue(weaponsList.contains("Chainsaw"));
		assertTrue(weaponsList.contains("Piano Wire"));
		assertTrue(weaponsList.contains("Pistol"));
		
		
		//test load people
		Map<String, String> peopleList = board.getPlayers();
		assertEquals(numPlayers, peopleList.size());
		assertTrue(peopleList.containsKey("Mr. Green"));
		assertTrue(peopleList.containsKey("Colonel Mustard"));
		assertTrue(peopleList.containsKey("Mrs. White"));
		assertFalse(peopleList.containsKey("Pistol"));
		
	}
	
	@Test
	public void testPlayers() {
		//Havent added an option for player to choose character yet, so the human player is just going to be set to a random player
		Set<Suspect> allCharacters = board.getAllCharacters();
		Set<Suspect> computers = board.getComputers();
		Suspect human = board.getHuman();
		assertEquals(numComputers, computers.size());
		assertTrue(allCharacters.contains(human));
		assertFalse(computers.contains(human));
		assertEquals(numPlayers, allCharacters.size());
		assertEquals(numComputers, computers.size());
		
		
	}

}
