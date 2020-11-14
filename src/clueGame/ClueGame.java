package clueGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClueGame extends JFrame {
	private static Board board;

	
	public ClueGame() {
		 //create the board
        board = Board.getInstance();
        board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
        board.initialize();
        //draw board
        setSize(750, 930);  // size the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
        add(board, BorderLayout.CENTER);
        GameControlPanel controlPanel = new GameControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        GameKnownCardsPanel cardPanel = new GameKnownCardsPanel();
        add(cardPanel, BorderLayout.EAST);
	}
	
	public static void main(String[] args) {
		 ClueGame theBoard = new ClueGame();  // create the frame
         theBoard.setVisible(true);
         
         
         /*GameControlPanel controlPanel = new GameControlPanel();
         JFrame control = controlPanel.main();
         GameKnownCardsPanel knownCards = new GameKnownCardsPanel();
         frame.add(control, BorderLayout.SOUTH);
         frame.add(knownCards, BorderLayout.EAST);
        */
    
         
         
         //add known cards
         //add next turn thing
         

	}

}
