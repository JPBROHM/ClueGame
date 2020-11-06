package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
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
	
	

}
