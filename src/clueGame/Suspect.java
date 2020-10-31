package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Suspect {
	private String name;
	private Color color;
	private int row,col;
	private ArrayList<Card> playerHand;
	
	
	public Suspect() {
		super();
		
	}
	public Suspect(String name, Color color, int row, int col) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
	}


	public abstract void updateHand(Card card);
	

}
