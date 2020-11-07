package clueGame;

import java.awt.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public abstract class Suspect {
	private String name;
	private Color color;
	private int row,col;
	protected ArrayList<Card> playerHand;
	protected Set<String> roomsSeen;
	protected Set<String> weaponsSeen;
	protected Set<String> peopleSeen;
	
	
	
	public Suspect() {
		super();
		
	}
	public Suspect(String name, Color color, int row, int col) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
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
	}
