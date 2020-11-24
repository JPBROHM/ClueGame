package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public class HumanPlayer extends Suspect {
	
	public HumanPlayer() {
		super();
	}

	public HumanPlayer(String name, Color color, int row, int col, int offset) {
		super(name, color, row, col, offset);
	}

	@Override
	public void updateHand(Card card) {
		playerHand.add(card);
		
	}
	
	@Override
	protected String getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setTarget(ArrayList<Card> playerHand2) {
		// TODO Auto-generated method stub
		
	}

}
