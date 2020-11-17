package clueGame;

import java.awt.BorderLayout;
import java.awt.Component;

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
	}
	
	public static void main(String[] args) {
		ClueGame game = new ClueGame();
		Component frame = null;
		//create splash screen
		JOptionPane.showMessageDialog(null, "You are "+board.getHuman().getName()+"."+ "\n" +"Can you find the solution \nbefore the Computer players?", 
																					"Welcome to Clue", JOptionPane.PLAIN_MESSAGE);
		
		
		
		 ClueGame theBoard = new ClueGame();  // create the frame
         theBoard.setVisible(true);
      
   

	}

}
