package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Suspect;

class ComputerAITests {

	@Test
	void testSelectTargets() {
		//code for actual game vv
		//if target is in a room, check if room is in list
		//if room in list, find new target randomly
		//else create suggestion
		
		
		
		//code for testvv
		
		//give location(Murder Room) && target Murder Room
		//Dont put room in Set
		//calculate Target
		//Check that target remains Murder Room
		
		//give location (MurderRoom) && target Murder Room
		//Put room in Set
		//Calculate Targets a bunch of time
		//Check that all the other rooms get targeted a few times
		
		fail("Not yet implemented");
	}
	
	
	
	
	
	
	
	
	
	@Test
	void testCreateSuggestion() {
		//Suggestion has one of each type
		Color color = new Color(255,0,0);
		ComputerPlayer player = new ComputerPlayer("Player", color, 12, 20);
		ArrayList<Card> suggestion =  player.createSuggestion();
		Assert.assertEquals(CardType.PERSON, suggestion.get(0).getType());
		Assert.assertEquals(CardType.WEAPON, suggestion.get(1).getType());
		Assert.assertEquals(CardType.ROOM, suggestion.get(2).getType());

		//testing that player room is suggestion room
		Assert.assertEquals("Dining Room", suggestion.get(2).getName());

		//test random person selected
		Set<String> setPeopleCards = new HashSet<>();
		setPeopleCards.add("Mrs. Peacock");
		setPeopleCards.add("Mr. Green");
		setPeopleCards.add("Professor Plum");
		int countWhite =0;
		int countMustard=0;
		int countScarlet=0;
		int countPlum=0;
		for (int i=0; i<200; i++) {
			suggestion = player.createSuggestion();
			if(suggestion.get(0).getName().equals("Colonel Mustard")) {countMustard++;}
			if(suggestion.get(0).getName().equals("Mrs. White")) {countWhite++;}
			if(suggestion.get(0).getName().equals("Miss Scarlet")) {countScarlet++;}
			if(suggestion.get(0).getName().equals("Professor Plum")) {countPlum++;}}
		assert(countMustard>1);
		assert(countWhite>1);
		assert(countScarlet>1);
		assert(countPlum==0);



		//test weapon selected

		Set<String> setWeaponCards = new HashSet<>();
		setWeaponCards.add("Pistol");
		setWeaponCards.add("Knife");
		setWeaponCards.add("Chainsaw");
		int countPistol =0;
		int countKnife=0;
		int countChainsaw=0;
		int countPoison=0;
		for (int i=0; i<200; i++) {
			suggestion = player.createSuggestion();
			if(suggestion.get(1).getName().equals("Pistol")) {countPistol++;}
			if(suggestion.get(1).getName().equals("Poison")) {countPoison++;}
			if(suggestion.get(1).getName().equals("Chainsaw")) {countChainsaw++;}
			if(suggestion.get(1).getName().equals("Knife")) {countKnife++;}
		}
		assert(countPoison>1&&countPoison<200);
		assert(countKnife==0);
		assert(countChainsaw==0);
		assert(countPistol==0);



		//test only one weapon not seen
		setWeaponCards.clear();
		setWeaponCards.add("Poison");
		setWeaponCards.add("Knife");
		setWeaponCards.add("Chainsaw");
		setWeaponCards.add("Piano Wire");
		setWeaponCards.add("Baseball Bat");
		countPistol =0;
		for (int i=0; i<200; i++) {
			suggestion = player.createSuggestion();
			if(suggestion.get(1).getName().equals("Pistol")) {countPistol++;}}
		assert(countPistol==200);



		//test only one person not seen
		setPeopleCards.clear();
		setPeopleCards.add("Mrs. Peacock");
		setPeopleCards.add("Colonel Mustard");
		setPeopleCards.add("Miss Scarlet");
		setPeopleCards.add("Mr. Green");
		setPeopleCards.add("Professor Plum");
		countWhite =0;
		for (int i=0; i<200; i++) {
			suggestion = player.createSuggestion();
			if(suggestion.get(0).getName().equals("Mrs. White")) {countWhite++;}}
		assert(countWhite==200);
	}
}





	


