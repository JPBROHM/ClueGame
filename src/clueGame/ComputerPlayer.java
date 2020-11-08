package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;

public class ComputerPlayer extends Suspect {
	private String target;
	
	

	public void setTarget(ArrayList<Card> roomCards) {
		if (roomsSeen.contains(target)) {
			ArrayList<String> possibleTargets = new ArrayList<>();
			boolean wasRoomSeen = false;
			//add unseen rooms to list of possible targets
			for (int i=0; i< roomCards.size();i++) {
				for (String room : roomsSeen) {
					if (roomCards.get(i).getName().equals(room)) {wasRoomSeen=true;}
				}
				if(!wasRoomSeen) {possibleTargets.add(roomCards.get(i).getName());}
				else {wasRoomSeen=false;}
			}

			//randomly select one card from the array list
			if (possibleTargets.size()==1) {this.target = possibleTargets.get(0);}
			else {
				Random r = new Random();
				int randoGen = (r.nextInt(possibleTargets.size()));
				this.target = possibleTargets.get(randoGen);}
		}

		
	}

	public ComputerPlayer(String name, Color color, int row, int col) {
		super(name, color, row, col);
	}

	@Override
	public void updateHand(Card card) {
		playerHand.add(card);
		
	}

	public Solution createSuggestion(Set<Card> deck, Board board) {
		Card w = new Card();
		Card p = new Card();
		Card r = new Card();
		ArrayList<String> NOTseenWeapons = new ArrayList<>();
		ArrayList<String> NOTseenPeople = new ArrayList<>();
		//iterate through all cards in the deck, if the card has not been seen by that player add it to a list
		for (Card card : deck) {
			if (card.getType() == CardType.WEAPON && !weaponsSeen.contains(card.getName())) {
				NOTseenWeapons.add(card.getName());
			}
			if (card.getType() == CardType.PERSON && !peopleSeen.contains(card.getName())) {
				NOTseenPeople.add(card.getName());
			}
		}	
		
		//randomly select a player and a weapon that has not been seen
		Random rand = new Random();
		if (NOTseenPeople.size() > 1) {
			int Prand = rand.nextInt(NOTseenPeople.size());
			p = new Card(CardType.PERSON, NOTseenPeople.get(Prand));
		} else {
			p = new Card(CardType.PERSON, NOTseenPeople.get(0));
		}
		if (NOTseenWeapons.size() > 1) {
			int Wrand = rand.nextInt(NOTseenWeapons.size());
			w = new Card(CardType.WEAPON, NOTseenWeapons.get(Wrand));
		} else {
			w = new Card(CardType.WEAPON, NOTseenWeapons.get(0));
		}
		//get corresponding room of the location of the player
		int row = getRow();
		int col = getCol();
		String name = board.getRoom(board.getCell(row, col).getCellLabel().charAt(0)).getName();
		r = new Card(CardType.ROOM, name);
		
		//return a solution object to be the suggestion, containing the randomly chosen weapon and person and the room the player is currently in
		return new Solution(p,r,w);
	}
	
	
	//used for testing purposes to set things to exactly what we want to test for specific scenarios
	public Set<String> getRoomCards() {
		return roomsSeen;
	}
	public Set<String> getWeaponCards() {
		return weaponsSeen;
	}
	public Set<String> getPeopleCards() {
		return peopleSeen;
	}
	public String getTarget() {
		return target;
		
	}
	
	public void HARDSETTEST(String target) {
		this.target = target;
	}


	

}
