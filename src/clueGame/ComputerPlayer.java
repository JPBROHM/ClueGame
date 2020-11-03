package clueGame;

import java.awt.Color;

public class ComputerPlayer extends Suspect {

	public ComputerPlayer(String name, Color color, int row, int col) {
		super(name, color, row, col);
	}

	@Override
	public void updateHand(Card card) {
		playerHand.add(card);
		
	}

	

}
