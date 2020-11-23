package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class AccuseButtonListener implements ActionListener{
	
	private ArrayList<Suspect> players;
	Board board = Board.getInstance();
	private JTextField room = new JTextField("Room");
	private JTextField weapon = new JTextField("Weapon");
	private JTextField person = new JTextField("Person");
	private JButton submitButton = new JButton("Submit");
	private JButton cancelButton = new JButton("Cancel");
	private JComboBox<String> roomBox, weaponBox, personBox;
	private String roomString, personString, weaponString;
	private Solution accusation;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		players = board.getAllCharacters();
		//check to see if its the players turn
		Suspect sus = players.get(board.getTurnCount());
		//if no --> Display error message
		if(sus instanceof ComputerPlayer) {
			JOptionPane.showMessageDialog(null,"Error: It is not your turn. Wait until your turn to make an accusation", 
					"Accusation Error", JOptionPane.PLAIN_MESSAGE);
		}
		if (board.isSuggestionMade()) {
			JOptionPane.showMessageDialog(null,"Error: You made a suggestion this turn, wait until the beggining of your next turn to make an accusation", 
					"Accusation Error", JOptionPane.PLAIN_MESSAGE);
		}

		//if yes --> do following
		else {
			//make accusation choices
			//create panel to select accusation cards
			JFrame frame = new JFrame();  // create the frame
			GameControlPanel accuseMenu = new GameControlPanel(4,2);
	    	frame.setContentPane(accuseMenu); // put the panel in the frame
	    	frame.setTitle("Make an Accusation");
	    	frame.setSize(250, 150);  // size the frame
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
	    	frame.setVisible(true);
			
	    	
	    	//create the combo boxes and give the first option to be select whatever
	    	//this forces the player to actually activate the listener for the combo boxes in case the accusation they wanted to make was already 
	    	//the first option in one of the combo boxes
			roomBox = new JComboBox();
			roomBox.addItem("Select Room");
			
			weaponBox = new JComboBox();
			weaponBox.addItem("Select Weapon");
			
			personBox = new JComboBox();
			personBox.addItem("Select Person");
			
			Set<Card> deck = board.getDeckSet();
			
			//iterate through the deck, adding each card to corresponding combo boxes
			for (Card card : deck) {
				if (card.getType() == CardType.ROOM) {
					roomBox.addItem(card.getName());
				}
				if (card.getType() == CardType.WEAPON) {
					weaponBox.addItem(card.getName());
				}
				if (card.getType() == CardType.PERSON) {
					personBox.addItem(card.getName());
				}
			}
			accuseMenu.add(room);
			accuseMenu.add(roomBox);
			accuseMenu.add(person);
			accuseMenu.add(personBox);
			accuseMenu.add(weapon);
			accuseMenu.add(weaponBox);
			accuseMenu.add(submitButton);
			accuseMenu.add(cancelButton);
			
			
			//create a listener to set the strings for each card the player selected
			class ComboListener implements ActionListener{
				//set the accusation strings based on what was selected 
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == roomBox) {
						roomString = roomBox.getSelectedItem().toString();
					}
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
			roomBox.addActionListener(listener);

			class SubmitButtonListener implements ActionListener{
				//if submit is clicked
				@Override
				public void actionPerformed(ActionEvent e) {
					//create accusation with the players choices
					accusation = new Solution(new Card(CardType.PERSON, personString), new Card(CardType.ROOM, roomString), new Card(CardType.WEAPON, weaponString));

					//check accusation against the boards solution and give the corresponding response if they win or lose
					if(board.checkAccusation(accusation)){
						//display the win message
						JOptionPane.showMessageDialog(null,"Congratulations, you have won the game ", 
								"Victory", JOptionPane.PLAIN_MESSAGE);
						//close the game
						System.exit(0);
					}
					else{
						//display the lose message
						JOptionPane.showMessageDialog(null,"Too bad, your guess was incorrect. Better luck next time", 
								"You lose", JOptionPane.PLAIN_MESSAGE);
						//close the game
						System.exit(0);
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
			
			//addd the listeners to the cancel and submit buttons
			submitButton.addActionListener(new SubmitButtonListener());
			cancelButton.addActionListener(new CancelButtonListener());

		
		}

	}


}

