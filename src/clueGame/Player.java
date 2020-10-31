package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	private String name;
	private Color color;
	private int row,col;
	private ArrayList<Card> playerHand;
	
	public abstract void updateHand(Card card);
	

}
