package clueGame;

public class Solution {
	
	private Card person;
	private Card room;
	private Card weapon;
	
	public Card getPerson() {
		return person;
	}

	public Card getRoom() {
		return room;
	}

	public Card getWeapon() {
		return weapon;
	}

	public Solution(Card person, Card room, Card weapon) {
		super();
		this.person = person;
		this.room = room;
		this.weapon = weapon;
	}
	
	//constructor used only for testing purposes
	public Solution() {
		super();
		person = new Card();
		room = new Card();
		weapon = new Card();
	}
	
	
}
