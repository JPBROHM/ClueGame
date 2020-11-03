package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Suspect {
	private String name;
	private Color color;
	private int row,col;
	protected ArrayList<Card> playerHand;
	
	
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
