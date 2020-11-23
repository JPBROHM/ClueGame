package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class BoardListener implements MouseListener {
	Board board = Board.getInstance();
	private Set<BoardCell> targets;
	private JTextField room = new JTextField("Room");
	private JTextField roomName;
	private JTextField weapon = new JTextField("Weapon");
	private JTextField person = new JTextField("Person");
	private JButton submitButton = new JButton("Submit");
	private JButton cancelButton = new JButton("Cancel");
	private JComboBox<String> weaponBox, personBox;
	private String personString, weaponString;
	private Solution suggestion;
	private int handleCount = 0;
	

	@Override
	public void mouseClicked(MouseEvent e) {
		handleCount = 0;
		//get instance of board and set size or rectangle
		targets = board.getTargets();
		//only tries to move if they have places they can move
		if(targets.size()!=0 && !board.isSuggestionMade()) {
			int rectWidth = board.getWidth() / (board.getNumColumns());
			int rectHeight = board.getHeight() / (board.getNumRows());

			if (board.getTurnCount() == 0) {
				//get pixel location of mouse click on the game board
				int x = e.getX();
				int y = e.getY();

				for (BoardCell cell : targets ) {
					if ((x >= ((cell.getColumn()) * rectWidth)) && (x <= ((cell.getColumn() + 1) * rectWidth)) &&
							(y >= ((cell.getRow()) * rectHeight)) && (y <= ((cell.getRow() + 1) * rectHeight))) {

						//move player
						board.getCell(board.getHuman().getRow(), board.getHuman().getCol()).setOccupied(false);
						board.getHuman().setRow(cell.getRow());
						board.getHuman().setCol(cell.getColumn());
						board.getCell(board.getHuman().getRow(), board.getHuman().getCol()).setOccupied(true);
													
						
						//set have move to true
						board.getHuman().setHasMoved(true);
						
						//are they in a room? if yes --> suggestion stuff
						if(board.getCell(board.getHuman().getRow(), board.getHuman().getCol()).isRoom() && !board.isSuggestionMade()
												&& board.getEventCount()% 2 == 0) {
							
							
							roomName = new JTextField(board.getRoom(board.getCell(board.getHuman().getRow(), board.getHuman().getCol())).getName());
							//handle suggestion stuff
							
							//create panel to select suggestion cards
							JFrame frame = new JFrame();  // create the frame
							GameControlPanel suggestMenu = new GameControlPanel(4,2);
					    	frame.setContentPane(suggestMenu); // put the panel in the frame
					    	frame.setTitle("Make a Suggestion");
					    	frame.setSize(250, 150);  // size the frame
					    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
					    	frame.setVisible(true);
							
					    	
					    	//create the combo boxes and give the first option to be select whatever
					    	//this forces the player to actually activate the listener for the combo boxes in case the accusation they wanted to make was already 
					    	//the first option in one of the combo boxes
							
							weaponBox = new JComboBox();
							weaponBox.addItem("Select Weapon");
							
							personBox = new JComboBox();
							personBox.addItem("Select Person");
							
							Set<Card> deck = board.getDeckSet();
							
							//iterate through the deck, adding each card to corresponding combo boxes
							for (Card card : deck) {

								if (card.getType() == CardType.WEAPON) {
									weaponBox.addItem(card.getName());
								}
								if (card.getType() == CardType.PERSON) {
									personBox.addItem(card.getName());
								}
							}
							suggestMenu.add(room);
							suggestMenu.add(roomName);
							suggestMenu.add(person);
							suggestMenu.add(personBox);
							suggestMenu.add(weapon);
							suggestMenu.add(weaponBox);
							suggestMenu.add(submitButton);
							suggestMenu.add(cancelButton);
							//create a listener to set the strings for each card the player selected
							class ComboListener implements ActionListener{
								//set the accusation strings based on what was selected 
								@Override
								public void actionPerformed(ActionEvent e) {
									
									if (e.getSource() == personBox) {
										personString = personBox.getSelectedItem().toString();
									}
									if (e.getSource() == weaponBox) {
										weaponString = weaponBox.getSelectedItem().toString();
									}
									
								}
								
							}
							//add listeners to the combo boxes
							ComboListener listener = new ComboListener();
							personBox.addActionListener(listener);
							weaponBox.addActionListener(listener);

							class SubmitButtonListener implements ActionListener{
								//if submit is clicked
								@Override
								public void actionPerformed(ActionEvent e) {
									//create accusation with the players choices
									suggestion = new Solution(new Card(CardType.PERSON, personString), 
											new Card(CardType.ROOM, board.getRoom(board.getCell(board.getHuman().getRow(), board.getHuman().getCol())).getName()), 
											new Card(CardType.WEAPON, weaponString));

									//check suggestion to see if it is correct
									if (handleCount == 0) {
										handleCount++;
										Card disproveCard = board.handleSuggestion(suggestion, board.getHuman());
										if (disproveCard != null) {



											board.addEventCount(1);
											board.setSuggestionMade(true);
											frame.setVisible(false);
											board.getHuman().setHasMoved(true);
										} else {



											board.addEventCount(1);
											board.setSuggestionMade(true);
											frame.setVisible(false);
											board.getHuman().setHasMoved(true);
										}
									}
								}

							}
							//cancel button closes the accuse menu
							class CancelButtonListener implements ActionListener{
								//if cancel is clicked
								//close accuse menu
								@Override
								public void actionPerformed(ActionEvent e) {
									frame.setVisible(false);
									
								}
								
							}
							
							//add the listeners to the cancel and submit buttons
							submitButton.addActionListener(new SubmitButtonListener());
							cancelButton.addActionListener(new CancelButtonListener());

						} 

					}

				}
				//mod 2 because there was an issue with it printing twice 
				if (!board.getHuman().isHasMoved() && board.getEventCount()% 2 == 0) {
					JOptionPane.showMessageDialog(null,"Error: You have selected an invalid tile. \n Please select one of the yellow spaces.", 
							"Invalid Tile", JOptionPane.PLAIN_MESSAGE);

				}
				board.addEventCount(1);
				//show updated board (continues to show possible tile markings until next is pressed so person isn't locked into their first click)
				board.repaint();



			}
		}
		//if human player has no moves skips their turn via setting has moved to true
		else {
			if (board.isSuggestionMade()) {
				JOptionPane.showMessageDialog(null,"You have made a suggestion, this ended your turn \n Please Hit the Next Button", 
						"Turn Over", JOptionPane.PLAIN_MESSAGE);
			}
			board.getHuman().setHasMoved(true);
		}
	}


	//unused mouse functions
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
