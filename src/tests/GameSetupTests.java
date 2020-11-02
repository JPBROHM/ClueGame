package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Solution;
import clueGame.Suspect;

class GameSetupTests {
	
	private static Board board;
	private static int numWeapons = 6;
	private static int numPlayers = 6;
	private static int numComputers = 5;
	private static int numCards = 19;

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
		//Havent added an option for player to choose character yet, so the human player is just going to be set to the first character loaded in
		Set<Suspect> allCharacters = board.getAllCharacters();
		Set<Suspect> computers = board.getComputers();
		Suspect human = board.getHuman();
		assertEquals(numComputers, computers.size());
		assertTrue(allCharacters.contains(human));
		assertFalse(computers.contains(human));
		assertEquals(numPlayers, allCharacters.size());
		assertEquals(numComputers, computers.size());
		
	}
	
	@Test
	public void testSolution() {
		//test to make sure solution has a person, weapon, and room card
		Solution solution = board.getSolution();
		assertEquals(CardType.PERSON, solution.getPerson().getType());
		assertEquals(CardType.WEAPON, solution.getWeapon().getType());
		assertEquals(CardType.ROOM, solution.getRoom().getType());
	}
	
	
	@Test
	public void testDeck() {
		//test to make sure the deck is of the right size
		assertEquals(numCards, board.getDeck().size());
		//test to make sure no card was added twice
		Set<Card> deckCards = new HashSet<>();
		for (int i = 0; i < board.getDeck().size(); i++) {
			deckCards.add(board.getDeck().get(i));
		}
		assertEquals(deckCards.size(), board.getDeck().size());
	}
	
	
	@Test
	public void testDealer() {
		//test to see if all players have similar size hands
		//test to make sure no card was dealt to more than one player
		//test to make sure no player got cards that are also in the solution
	}
	

}
