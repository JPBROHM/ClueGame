package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public abstract class Suspect {
	private int offset;
	protected String name;
	private Color color;
	protected int row;
	protected int col;
	protected ArrayList<Card> playerHand;
	protected Set<String> roomsSeen;
	protected Set<String> weaponsSeen;
	protected Set<String> peopleSeen;
	private boolean hasMoved = false;
	public String target;
	Board board = Board.getInstance();
	
	
	public boolean isHasMoved() {
		return hasMoved;
	}
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	public Suspect() {
		super();
		
	}
	public Suspect(String name, Color color, int row, int col, int offset) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
		this.offset = offset;
		this.playerHand = new ArrayList<>();
		roomsSeen = new HashSet<>();
		peopleSeen = new HashSet<>();
		weaponsSeen = new HashSet<>();
		
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public ArrayList<Card> getPlayerHand() {
		return playerHand;
	}

	public abstract void updateHand(Card card);
	
	
	public Card disproveSuggestion(Solution suggestion) {

		Card suggestPerson = suggestion.getPerson();
		Card suggestRoom = suggestion.getRoom();
		Card suggestWeapon = suggestion.getWeapon();
		ArrayList<Card> possibleCards = new ArrayList<>();
		//check this players cards
		for (int i = 0; i < playerHand.size(); i++) {


			if (suggestPerson.getName().equals(playerHand.get(i).getName())){
				possibleCards.add(playerHand.get(i));
			}

			if (suggestWeapon.getName().equals(playerHand.get(i).getName())){
				possibleCards.add(playerHand.get(i));
			}

			if (suggestRoom.getName().equals(playerHand.get(i).getName())){
				possibleCards.add(playerHand.get(i));
			}
		}



		//if only has one, return it
		if(possibleCards.size()==1) {
			return possibleCards.get(0);
		}
		//else if 2, pick randomly 
		else if(possibleCards.size()>1) {
		//rand function	
			Random r = new Random();
			int randoGen = (r.nextInt(possibleCards.size()));
			return possibleCards.get(randoGen);
			}
		
		
		//else, return null
		else {return null;}

	}
	
	
	
	public void updateSeen(Card card) {
		if (card.getType().equals(CardType.PERSON)) {
			peopleSeen.add(card.getName());
		}
		if (card.getType().equals(CardType.WEAPON)) {
			weaponsSeen.add(card.getName());
		}
		if (card.getType().equals(CardType.ROOM)) {
			roomsSeen.add(card.getName());
		}
	}
	
	public void addSeenRoom(String name) {
		roomsSeen.add(name);
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
	
	public Set<String> getRoomsSeen() {
		return roomsSeen;
	}
	public Set<String> getWeaponsSeen() {
		return weaponsSeen;
	}
	public Set<String> getPeopleSeen() {
		return peopleSeen;
	}
	
	//used for testing purposes to set things to exactly what we want to test for specific scenarios
	public void setRoomCards(Set<String> roomsSeen) {
		this.roomsSeen = roomsSeen;
	}
	public void setWeaponCards(Set<String> weaponsSeen) {
		this.weaponsSeen = weaponsSeen;
	}
	public void setPeopleCards(Set<String> peopleSeen) {
		this.peopleSeen = peopleSeen;
	}
	public void draw(Graphics g, int rectWidth, int rectHeight) {
		int mainOffset = (rectWidth/6) * offset;
		if (board.getCell(row, col).isRoomCenter()) {
			g.setColor(this.color);
			g.fillOval(col * rectWidth + mainOffset, row * rectHeight, rectWidth, rectHeight);
			g.setColor(Color.BLACK);
			g.drawOval(col * rectWidth + mainOffset, row * rectHeight, rectWidth, rectHeight);
		}
		else {
			g.setColor(this.color);
			g.fillOval(col * rectWidth, row * rectHeight, rectWidth, rectHeight);
			g.setColor(Color.BLACK);
			g.drawOval(col * rectWidth, row * rectHeight, rectWidth, rectHeight);
		}


	}
	protected abstract String getTarget();

	public void setRow(int row) {
		this.row = row;
	}
	public void setCol(int col) {
		this.col = col;
	}
	protected abstract void setTarget(ArrayList<Card> playerHand2);
	
	protected abstract void makeAccusation(Set<Card> deck);
	}
