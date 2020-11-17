package clueGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameControlPanel extends JPanel{

	private JTextField theGuess;
	private JTextField theGuessResult;
	private JTextField rollNumber;
	private JTextField playerName;



	public GameControlPanel() {
		theGuess = new JTextField();
		theGuessResult = new JTextField();
		rollNumber = new JTextField();
		playerName = new JTextField(15);
		
		Color color = new Color(0,0,0);
    	color = Color.YELLOW;
    	theGuess.setText("I have no guess!");
    	theGuessResult.setText( "So you have nothing?");
    	
    	setLayout(new GridLayout(2, 0));
		GameControlPanel top = new GameControlPanel(1,4);
		
		GameControlPanel topLeft = new GameControlPanel(2,0);
		GameControlPanel topMiddleLeft = new GameControlPanel(0, 2);
	

		GameControlPanel bottom = new GameControlPanel(0, 2);
		
		//some good ole hard coded button/label text fields
		GameControlPanel bottomLeft = new GameControlPanel(1,0);
		bottomLeft.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));


		GameControlPanel bottomRight = new GameControlPanel(1,0);
		bottomRight.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));

		//the extra spaces are to get the roll closer to the text field for it
		//, makes it prettier
		//A1 coding practice ^^
		JLabel roll = new JLabel("               Roll:");
		JLabel whosTurn = new JLabel("Who's turn?");
		JButton accuseButton = new JButton("Make Accusation");
		JButton nextButton = new JButton("NEXT!");
		nextButton.addActionListener(new NextButtonListener());



		//adding the stuff to the display, starting from the innermost fields and working its way out
		bottomRight.add(theGuessResult);
		bottomLeft.add(theGuess);

		bottom.add(bottomLeft);
		bottom.add(bottomRight);

		topLeft.add(whosTurn);
		topLeft.add(playerName);

		topMiddleLeft.add(roll);
		topMiddleLeft.add(rollNumber);

		top.add(topLeft);
		top.add(topMiddleLeft);
		top.add(accuseButton);
		top.add(nextButton);


		//add the displays to the panel
		add(top);
		add(bottom);


		setVisible(true);
		
		
		
		
	}
	

	
	
	class NextButtonListener implements ActionListener {
		private ArrayList<Suspect> players;
		Board board = Board.getInstance();
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Worky worky worky");
			players = board.getAllCharacters();
			//check if human turn over (have they moved)
			//if no --> error
			//if yes --> update current player
			if (board.getHuman().isHasMoved()) {
				board.nextTurn();
				Suspect sus = players.get(board.getTurnCount());


				//roll dice
				int roll = board.rollDice();
				//set turn
				setTurn(sus, roll);
				board.repaint();

				//calctargets
				BoardCell cell = board.getCell(sus.getRow(), sus.getCol());

				board.calcTargets(cell,roll);

				Set<BoardCell> targets = board.getTargets();



				//we have a target room--> we need to set the target cell to the closest door for that room

			


				//if they can get to the door they want (in die roll-1) -------> then go in that room center
				if(sus instanceof ComputerPlayer) {
					int min = 999;
					boolean moveBool = false;
					BoardCell cellToMoveTo = null;
					ArrayList<Card> seenRooms = new ArrayList<>();
					for (String name : sus.getRoomsSeen()) {
						seenRooms.add(new Card(CardType.ROOM, name));
					}
					sus.setTarget(seenRooms);
					BoardCell targetRoomCell = board.getCell(board.getRoom(sus.getTarget()).getCenterCell().getRow(),board.getRoom(sus.getTarget()).getCenterCell().getColumn());
					for (BoardCell targetCell : targets) {
						if (targetCell.isRoomCenter() && sus.getTarget().equals(board.getRoom(targetCell.getCellLabel().charAt(0)).getName())) {
							sus.setRow(targetCell.getRow());
							sus.setCol(targetCell.getColumn());
							moveBool = true;
							break;
						}
						else if (targetCell.isDoorway()) {
							for (BoardCell roomCell : targetCell.getAdjList()) {
								if (roomCell.isRoomCenter() && sus.getTarget().equals(board.getRoom(roomCell.getCellLabel().charAt(0)).getName())) {
									sus.setRow(targetCell.getRow());
									sus.setCol(targetCell.getColumn());
									moveBool = true;
									break;
								}
								break;
							}
						} else if (Math.abs(targetCell.getRow() + targetCell.getColumn() - targetRoomCell.getRow() - targetRoomCell.getColumn()) < min){
							min = Math.abs(targetCell.getRow() + targetCell.getColumn() - targetRoomCell.getRow() - targetRoomCell.getColumn());
							cellToMoveTo = targetCell;
						}
					}
					if (!moveBool) {
						sus.setRow(cellToMoveTo.getRow());
						sus.setCol(cellToMoveTo.getColumn());
					}


					board.repaint();
				}
				

			
			//is new player human? 
				else{
				//if yes flag unfinished, end
					sus.setHasMoved(false);}
		
			
			
			
			
			
			
			
			
			
		} else {
			JOptionPane.showMessageDialog(null,"Error: Your move is not over. Please move your player before continuing", 
					"Turn Not Over", JOptionPane.PLAIN_MESSAGE);
		}
	
			
	
		
		
		
		
		
		
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public GameControlPanel(int rows, int cols) {
		theGuess = new JTextField();
		theGuessResult = new JTextField();
		rollNumber = new JTextField();
		playerName = new JTextField(15);
		setLayout(new GridLayout(rows, cols));
	}

	public JTextField getTheGuess() {
		return theGuess;
	}

	public JTextField getTheGuessResult() {
		return theGuessResult;
	}

	public JTextField getRollNumber() {
		return rollNumber;
	}

	public JTextField getPlayerName() {
		return playerName;
	}

	public void setGuess(String guess) {
	    theGuess.setText(guess);
	}

	private void setGuessResult(String string) {
		theGuessResult.setText(string);
	}
	
	
	private void setTurn(Suspect computerPlayer, int roll) {
		rollNumber.setText(Integer.toString(roll));
		playerName.setText(computerPlayer.getName());
		playerName.setBackground(computerPlayer.getColor());

		
	}
	

    public static void main(String[] args) {
    	
    	GameControlPanel panel = new GameControlPanel();  // create the panel
    	panel.setLayout(new GridLayout(2, 0));
    	JFrame frame = new JFrame();  // create the frame
    	frame.setContentPane(panel); // put the panel in the frame
    	frame.setSize(750, 180);  // size the frame
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close

    	// test filling in the data
    	Color color = new Color(0,0,0);
    	color = Color.YELLOW;
    	panel.setTurn(new ComputerPlayer( "Col. Mustard", color, 0, 0), 5);
    	panel.setGuess( "I have no guess!");
    	panel.setGuessResult( "So you have nothing?");



    	//creating the panels and grids before adding them to eachother,
    	//they follow the same layout as the example layout from the assignment
    	//each one is named by that panels corresponding place in the frame
       




 }

	

	

}
