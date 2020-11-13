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
        setSize(500, 750);  // size the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
        add(board, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		 ClueGame frame = new ClueGame();  // create the frame
         frame.setVisible(true); // make it visible
         
        
         //double for loop for size of the board, with BoardCell cell.Draw()??
         
         
         //add known cards
         //add next turn thing
         

	}

}
