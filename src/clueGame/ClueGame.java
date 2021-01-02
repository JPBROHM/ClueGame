package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class ClueGame extends JFrame {
	private static Board board = Board.getInstance();;
	static GameKnownCardsPanel cardPanel;
	public static GameKnownCardsPanel getCardPanel() {
		return cardPanel;
	}

	GameControlPanel controlPanel;
	
	public ClueGame() {
		 //create the board
        board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
        board.initialize();
        //draw board
        
        setSize(750, 930);  // size the frame
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		add(Board.getInstance(), BorderLayout.CENTER);
        controlPanel = new GameControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        cardPanel = new GameKnownCardsPanel();
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
		//create splash screen
		JOptionPane.showMessageDialog(null, "You are "+board.getHuman().getName()+"."+ "\n" +"Can you find the solution \nbefore the Computer players?", 
																					"Welcome to Clue", JOptionPane.PLAIN_MESSAGE);
	
		 
         game.setVisible(true);
         
         System.out.println(board.getSolution().getPerson().getName() + board.getSolution().getWeapon().getName() + board.getSolution().getRoom().getName());
      
   

	}

}
