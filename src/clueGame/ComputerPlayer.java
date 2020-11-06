package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

public class ComputerPlayer extends Suspect {

	public ComputerPlayer(String name, Color color, int row, int col) {
		super(name, color, row, col);
	}

	@Override
	public void updateHand(Card card) {
		playerHand.add(card);
		
	}

	public ArrayList<Card> createSuggestion() {
		ArrayList<Card> suggestion = new ArrayList<>();
		suggestion.add(new Card(CardType.WEAPON, "WEAPON"));
		suggestion.add(new Card(CardType.WEAPON, "WEAPON"));
		suggestion.add(new Card(CardType.WEAPON, "WEAPON"));
		return suggestion;
	}
	
	
	//used for testing
	public Set<String> getRoomCards() {
		return roomsSeen;
	}
	public void setRoomCards(Set<String> roomsSeen) {
		this.roomsSeen = roomsSeen;
	}
	public Set<String> getWeaponCards() {
		return weaponsSeen;
	}
	public void setWeaponCards(Set<String> weaponsSeen) {
		this.weaponsSeen = weaponsSeen;
	}
	public Set<String> getPeopleCards() {
		return peopleSeen;
	}
	public void setPeopleCards(Set<String> peopleSeen) {
		this.peopleSeen = peopleSeen;
	}

	

}
