package clueGame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClueGame extends JFrame {
	private static Board board;

	
	public ClueGame() {
		 //create the board
        board = Board.getInstance();
        board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
        board.initialize();
        //draw board
        setSize(750, 930);  // size the frame
        setTitle("Clue Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
        add(board, BorderLayout.CENTER);
        GameControlPanel controlPanel = new GameControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        GameKnownCardsPanel cardPanel = new GameKnownCardsPanel();
        add(cardPanel, BorderLayout.EAST);
        Random r = new Random();
        
        //manually making the first turn work (since the next button normally triggers it)
        int firstRoll = board.rollDice();
        controlPanel.setTurn(board.getHuman(),firstRoll);
        BoardCell cell = board.getCell(board.getHuman().getRow(), board.getHuman().getCol());

		board.calcTargets(cell,firstRoll);

		Set<BoardCell> targets = board.getTargets();
		board.repaint();
	}
	
	public static void main(String[] args) {
		ClueGame game = new ClueGame();
		Component frame = null;
		//create splash screen
		JOptionPane.showMessageDialog(null, "You are "+board.getHuman().getName()+"."+ "\n" +"Can you find the solution \nbefore the Computer players?", 
																					"Welcome to Clue", JOptionPane.PLAIN_MESSAGE);
		
		
		
		
		
		 ClueGame theBoard = new ClueGame();  // create the frame
		 //used for testing to make sure correct accusation works
		 board.setSolution( new Solution(new Card(CardType.PERSON, "Mrs. White"), new Card(CardType.ROOM, "Hall"), new Card(CardType.WEAPON, "Pistol")));
         theBoard.setVisible(true);
      
   

	}

}
