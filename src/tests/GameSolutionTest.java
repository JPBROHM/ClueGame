package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Solution;
import clueGame.Suspect;

class GameSolutionTest {
	private static Board board;
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
	public void checkAccusation() {


		//solution is:
		Solution answer = new Solution(new Card(CardType.PERSON, "Mrs. White"), new Card(CardType.ROOM, "Comfort Room"), new Card(CardType.WEAPON, "Pistol"));
		Solution accusationRight = new Solution(new Card(CardType.PERSON, "Mrs. White"), new Card(CardType.ROOM, "Comfort Room"), new Card(CardType.WEAPON, "Pistol"));
		Solution accusationRoom = new Solution(new Card(CardType.PERSON, "Mrs. White"), new Card(CardType.ROOM, "Hall"), new Card(CardType.WEAPON, "Pistol"));
		Solution accusationWeapon = new Solution(new Card(CardType.PERSON, "Mrs. White"), new Card(CardType.ROOM, "Comfort Room"), new Card(CardType.WEAPON, "Baseball Bat"));
		Solution accusationPerson = new Solution(new Card(CardType.PERSON, "Colonel Mustard"), new Card(CardType.ROOM, "Comfort Room"), new Card(CardType.WEAPON, "Pistol"));

		board.setSolution(answer);
		//guess solution

		//accusation is right
		assertTrue(board.checkAccusation(accusationRight));

		//room is wrong
		assertFalse(board.checkAccusation(accusationRoom));

		//weapon wrong
		assertFalse( board.checkAccusation(accusationWeapon));

		//person wrong
		assertFalse(board.checkAccusation(accusationPerson));


	}

	@Test
	void disproveSuggestion() {
		Color color = new Color(255,0,0);
		ComputerPlayer player = new ComputerPlayer("Player", color, 12, 20);
		Solution suggestion =  player.createSuggestion(board.getDeckSet(), board);
			
		
		ComputerPlayer player2 = new ComputerPlayer("Player2", color, 15,18);
		//give player no cards
		//check that returns null
		assertEquals(player2.disproveSuggestion(suggestion),null);
		
		
		
		
		
		
		//give a player one card
		//check card is returned
		
		player2.updateHand(suggestion.getWeapon());
		assertEquals(player2.disproveSuggestion(suggestion),suggestion.getWeapon());
		
		
		
		//give player 2 cards
		//check that each card is returned some of the time
		//check that total of returns of those 2 cards = total disprove suggestion calls
		player2.updateHand(suggestion.getPerson());
		int countWeapon=0;
		int countPerson=0;
		for (int i=0; i<200; i++) {
			Card disproveCard=player2.disproveSuggestion(suggestion);
			if(disproveCard.equals(suggestion.getWeapon())) {
				countWeapon++;
			}
			if(disproveCard.equals(suggestion.getPerson())) {
				countPerson++;
			}
		}
		assert(countWeapon>1);

		assert(countPerson>1);
		assertEquals(200, countWeapon+countPerson);
		
		
		
	}
	
	@Test
	void testHandleSuggestion() {
		Color color = new Color(255,0,0);
		ComputerPlayer player1 = new ComputerPlayer("Player1", color, 12, 20);
		ComputerPlayer player2 = new ComputerPlayer("Player2", color, 15, 18);
		ComputerPlayer player3 = new ComputerPlayer("Player3", color, 14, 18);
		ComputerPlayer player4 = new ComputerPlayer("Player4", color, 13, 18);
		ComputerPlayer player5 = new ComputerPlayer("Player5", color, 12, 18);
		ComputerPlayer player6 = new ComputerPlayer("Player6", color, 11, 18);
		ArrayList<Suspect> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		
		board.setAllCharacters(players);
		//player1 will be suggester
		Solution suggestion =  player1.createSuggestion(board.getDeckSet(), board);
		
		
		//if no one can disprove, return null
		assertEquals(null, board.handleSuggestion(suggestion, player1));
		
		
		
		//if only suggesting player can disprove, return null
		//add card to player1 hand
		player1.updateHand(suggestion.getWeapon());
		assertEquals(null,board.handleSuggestion(suggestion, player1));
		
		
		//if only one player can disprove, they do so
		//add card to player2 hand
		player2.updateHand(suggestion.getWeapon());
		assertEquals(suggestion.getWeapon(),board.handleSuggestion(suggestion, player1));
		
		
		//if two or more players can disprove, go in order of suspect list for who disproves
		//cards in player 2 & 3 hand
		player3.updateHand(suggestion.getPerson());
		assertEquals(suggestion.getWeapon(),board.handleSuggestion(suggestion, player1));
		
		
		
		
	}

}
