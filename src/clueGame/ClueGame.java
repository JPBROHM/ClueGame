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
	private static Board board;
	GameKnownCardsPanel cardPanel;
	GameControlPanel controlPanel;
	
	public ClueGame() {
		 //create the board
        board = Board.getInstance();
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
        controlPanel.addMouseListener(new FrameListener());
        board.addMouseListener(new FrameListener());
        
        
        Random r = new Random();
        
        //manually making the first turn work (since the next button normally triggers it)
        int firstRoll = board.rollDice();
        controlPanel.setTurn(board.getHuman(),firstRoll);
        BoardCell cell = board.getCell(board.getHuman().getRow(), board.getHuman().getCol());

		board.calcTargets(cell,firstRoll);

		Set<BoardCell> targets = board.getTargets();
		board.repaint();
	}
	
	
	
	class FrameListener implements MouseListener, ActionListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			cardPanel.update();
				
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void actionPerformed(ActionEvent e) {
			cardPanel.update();
			
		}
		
	}
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		ClueGame game = new ClueGame();
		//create splash screen
		JOptionPane.showMessageDialog(null, "You are "+board.getHuman().getName()+"."+ "\n" +"Can you find the solution \nbefore the Computer players?", 
																					"Welcome to Clue", JOptionPane.PLAIN_MESSAGE);
		
		
		
		
		
		 ClueGame theBoard = new ClueGame();  // create the frame
		 
	
         theBoard.setVisible(true);
         while (true) {
        	 theBoard.cardPanel.update();
        	 if (!theBoard.isVisible()) {
        		 break;
        	 }
         }
      
   

	}

}
