package clueGame;

import java.awt.Color;

public class HumanPlayer extends Suspect {
	
	public HumanPlayer() {
		super();
	}

	public HumanPlayer(String name, Color color, int row, int col) {
		super(name, color, row, col);
	}

	@Override
	public void updateHand(Card card) {
		playerHand.add(card);
		
	}

}
