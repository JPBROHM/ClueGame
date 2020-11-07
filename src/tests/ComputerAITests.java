package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Solution;
import clueGame.Suspect;

class ComputerAITests {
	
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
	void testSelectTargets() {
		//code for actual game vv
		//if target is in a room, check if room is in list
		//if room in list, find new target randomly
		//else create suggestion
		
		
		
		//code for testvv
		
		//give location(Murder Room) && target Murder Room
		Color color = new Color(255,0,0);
		ComputerPlayer player = new ComputerPlayer("Player", color, 2, 2);
	
		
		player.HARDSETTEST("Murder Room");
		player.setTarget(board.getRoomCards());
		//Dont put room in Set
		//calculate Target
		player.getTarget();
		
		//Check that target remains Murder Room
		Assert.assertEquals(player.getTarget(), "Murder Room");
		
		
		
		//leave location as MurderRoom && target as Murder Room
			
		//Put room in Set
		Set<String> setRoomCards = new HashSet<>();
		setRoomCards.add("Murder Room");
		player.setRoomCards(setRoomCards);
		//Calculate Targets a bunch of time
		int murderCount=0;
		int comfortCount=0;
		int diningCount=0;
		for (int i=0; i<200; i++) {
			player.HARDSETTEST("Murder Room");//must reset to murder room, or it will continue to pick wtv its first random pick was every time
			player.setTarget(board.getRoomCards());
			if(player.getTarget().equals("Murder Room")){murderCount++;}
			if(player.getTarget().equals("Comfort Room")){comfortCount++;}
			if(player.getTarget().equals("Dining Room")){diningCount++;}
		}
		
		//Check that all the other rooms get targeted a few times, but not Murder Room
		assertEquals(0, murderCount);
	}
	
	
	
	
	
	
	
	
	
	@Test
	void testCreateSuggestion() {
		//Cehck to see that a suggestion has one of each type of card
		Color color = new Color(255,0,0);
		ComputerPlayer player = new ComputerPlayer("Player", color, 12, 20);
		Solution suggestion =  player.createSuggestion(board.getDeckSet(), board);
		Assert.assertEquals(CardType.PERSON, suggestion.getPerson().getType());
		Assert.assertEquals(CardType.WEAPON, suggestion.getWeapon().getType());
		Assert.assertEquals(CardType.ROOM, suggestion.getRoom().getType());

		//testing that the room the player is in is the is suggestion room
		Assert.assertEquals("Dining Room", suggestion.getRoom().getName());

		//test random person selected with certain people already being "seen"
		Set<String> setPeopleCards = new HashSet<>();
		setPeopleCards.add("Mrs. Peacock");
		setPeopleCards.add("Mr. Green");
		setPeopleCards.add("Professor Plum");
		player.setPeopleCards(setPeopleCards);
		int countWhite =0;
		int countMustard=0;
		int countScarlet=0;
		int countPlum=0;
		for (int i=0; i<200; i++) {
			suggestion = player.createSuggestion(board.getDeckSet(), board);
			if(suggestion.getPerson().getName().equals("Colonel Mustard")) {countMustard++;}
			if(suggestion.getPerson().getName().equals("Mrs. White")) {countWhite++;}
			if(suggestion.getPerson().getName().equals("Miss Scarlet")) {countScarlet++;}
			if(suggestion.getPerson().getName().equals("Professor Plum")) {countPlum++;}}
		assert(countPlum==0);
		assert(countScarlet > 1);
		assert(countMustard > 1);
		assert(countWhite > 1);



		//test to see if a random weapon is selected given three weapon cards that have been "seen"
		Set<String> setWeaponCards = new HashSet<>();
		setWeaponCards.add("Pistol");
		setWeaponCards.add("Knife");
		setWeaponCards.add("Chainsaw");
		player.setWeaponCards(setWeaponCards);
		int countPistol =0;
		int countKnife=0;
		int countChainsaw=0;
		int countPoison=0;
		int countBat = 0;
		for (int i=0; i<200; i++) {
			suggestion = player.createSuggestion(board.getDeckSet(), board);
			if(suggestion.getWeapon().getName().equals("Pistol")) {countPistol++;}
			if(suggestion.getWeapon().getName().equals("Poison")) {countPoison++;}
			if(suggestion.getWeapon().getName().equals("Chainsaw")) {countChainsaw++;}
			if(suggestion.getWeapon().getName().equals("Knife")) {countKnife++;}
		}
		assert(countPoison>0);
		assert(countKnife==0);
		assert(countChainsaw==0);
		assert(countPistol==0);



		//test for when all but one weapon card has been seen, making sure it returns that one weapon every time
		setWeaponCards.clear();
		setWeaponCards.add("Poison");
		setWeaponCards.add("Knife");
		setWeaponCards.add("Chainsaw");
		setWeaponCards.add("Piano Wire");
		setWeaponCards.add("Baseball Bat");
		player.setWeaponCards(setWeaponCards);
		countPistol = 0;
		for (int i=0; i<200; i++) { 
			suggestion = player.createSuggestion(board.getDeckSet(), board);
			if(suggestion.getWeapon().getName().equals("Pistol")) {countPistol++;}}
		
		assert(countPistol== 200);



		//test for when all but one 
		setPeopleCards.clear();
		setPeopleCards.add("Mrs. Peacock");
		setPeopleCards.add("Colonel Mustard");
		setPeopleCards.add("Miss Scarlet");
		setPeopleCards.add("Mr. Green");
		setPeopleCards.add("Professor Plum");
		countWhite =0;
		for (int i=0; i<200; i++) {
			suggestion = player.createSuggestion(board.getDeckSet(), board);
			if(suggestion.getPerson().getName().equals("Mrs. White")) {countWhite++;}}
		assert(countWhite==200);
	}



	



}



	


