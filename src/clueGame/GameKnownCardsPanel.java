package clueGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameKnownCardsPanel extends JPanel{

	
	
	public GameKnownCardsPanel setPeople(HumanPlayer player) {
		//get hand
		GameKnownCardsPanel peoplePanel = new GameKnownCardsPanel();
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		ArrayList<Card> peopleCards = new ArrayList<>();
		for (int i = 0; i < player.getPlayerHand().size(); i++) {
			if (player.getPlayerHand().get(i).getType() == CardType.PERSON) {
				peopleCards.add(player.getPlayerHand().get(i));
			}
		}
		
		//setting things to the right size dependant on if they have/dont have cards in hand/seen
		if (peopleCards.isEmpty() && player.getPeopleSeen().isEmpty()) {
			peoplePanel.setLayout(new GridLayout(4, 0));
		}
		else if(peopleCards.isEmpty()) {
			peoplePanel.setLayout(new GridLayout(player.getPeopleSeen().size() + 3, 0));
		}
		else if( player.getPeopleSeen().isEmpty()) {
			peoplePanel.setLayout(new GridLayout(peopleCards.size() + 3, 0));
		}
		else {
			peoplePanel.setLayout(new GridLayout(peopleCards.size() + player.getPeopleSeen().size() + 2, 0));
		}
		JLabel hand = new JLabel("In Hand:");
		peoplePanel.add(hand);


		if (peopleCards.size() == 0) {
			peoplePanel.add(new JTextField("None"));
		}

        for (int i = 0; i < peopleCards.size(); i++) {
        	peoplePanel.add(new JTextField(peopleCards.get(i).getName()));
        }
        
        
		//set seen & display
        peoplePanel.add(new JLabel("Seen:"));
        if (player.getPeopleSeen().size() == 0) {
			peoplePanel.add(new JTextField("None"));
		}
        for (String name : player.getPeopleSeen()) {
        	peoplePanel.add(new JTextField(name));
        	
        }
        
        return peoplePanel;
		
	}
	
	public GameKnownCardsPanel setWeapons(HumanPlayer player) {
		//get hand
		GameKnownCardsPanel weaponPanel = new GameKnownCardsPanel();
		weaponPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		ArrayList<Card> weaponCards = new ArrayList<>();
		for (int i = 0; i < player.getPlayerHand().size(); i++) {
			if (player.getPlayerHand().get(i).getType() == CardType.WEAPON) {
				weaponCards.add(player.getPlayerHand().get(i));
			}
		}

		//setting things to the right size dependant on if they have/dont have cards in hand/seen
		if (weaponCards.isEmpty() && player.getWeaponsSeen().isEmpty()) {
			weaponPanel.setLayout(new GridLayout(4, 0));
		}
		else if(weaponCards.isEmpty()) {
			weaponPanel.setLayout(new GridLayout(player.getWeaponsSeen().size() + 3, 0));
		}
		else if( player.getWeaponsSeen().isEmpty()) {
			weaponPanel.setLayout(new GridLayout(weaponCards.size() + 3, 0));
		}
		else {
			weaponPanel.setLayout(new GridLayout(weaponCards.size() + player.getWeaponsSeen().size() + 2, 0));
		}
		JLabel hand = new JLabel("In Hand:");
		weaponPanel.add(hand);


		if (weaponCards.size() == 0) {
			weaponPanel.add(new JTextField("None"));
		}

		for (int i = 0; i < weaponCards.size(); i++) {
			weaponPanel.add(new JTextField(weaponCards.get(i).getName()));
		}


		//set seen & display
		weaponPanel.add(new JLabel("Seen:"));
		if (player.getWeaponsSeen().size() == 0) {
			weaponPanel.add(new JTextField("None"));
		}
		for (String name : player.getWeaponsSeen()) {
			weaponPanel.add(new JTextField(name));

		}

		return weaponPanel;
	}

	
	public GameKnownCardsPanel setRooms(HumanPlayer player) {
		//get hand
		GameKnownCardsPanel roomPanel = new GameKnownCardsPanel();
		roomPanel.setBorder(new TitledBorder (new EtchedBorder(), "Room"));
		ArrayList<Card> roomCards = new ArrayList<>();
		for (int i = 0; i < player.getPlayerHand().size(); i++) {
			if (player.getPlayerHand().get(i).getType() == CardType.ROOM) {
				roomCards.add(player.getPlayerHand().get(i));
			}
		}
		
		//setting things to the right size dependant on if they have/dont have cards in hand/seen
		if (roomCards.isEmpty() && player.getRoomsSeen().isEmpty()) {
			roomPanel.setLayout(new GridLayout(4, 0));
		}
		else if(roomCards.isEmpty()) {
			roomPanel.setLayout(new GridLayout(player.getRoomsSeen().size() + 3, 0));
		}
		else if( player.getRoomsSeen().isEmpty()) {
			roomPanel.setLayout(new GridLayout(roomCards.size() + 3, 0));
		}
		else {
			roomPanel.setLayout(new GridLayout(roomCards.size() + player.getPeopleSeen().size() + 2, 0));
		}
		JLabel hand = new JLabel("In Hand:");
		roomPanel.add(hand);


		if (roomCards.size() == 0) {
			roomPanel.add(new JTextField("None"));
		}

        for (int i = 0; i < roomCards.size(); i++) {
        	roomPanel.add(new JTextField(roomCards.get(i).getName()));
        }
        
        
		//set seen & display
        roomPanel.add(new JLabel("Seen:"));
        if (player.getRoomsSeen().size() == 0) {
			roomPanel.add(new JTextField("None"));
		}
        for (String name : player.getRoomsSeen()) {
        	roomPanel.add(new JTextField(name));
        	
        }
        
        return roomPanel;
	}
	
	

    public static void main(String[] args) {
           GameKnownCardsPanel panel = new GameKnownCardsPanel();  // create the panel
           JFrame frame = new JFrame();  // create the frame
           frame.setContentPane(panel); // put the panel in the frame
           frame.setSize(180, 750);  // size the frame
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
           frame.setVisible(true); // make it visible
           panel.setBorder(new TitledBorder (new EtchedBorder(), "Known Cards"));
           panel.setLayout(new GridLayout(3, 0));
           Color color = new Color(0,0,0);
           color = Color.YELLOW;
           HumanPlayer player = new HumanPlayer("Col. Mustard", color, 0, 0);

           panel.add(panel.setPeople(player));
           panel.add(panel.setRooms(player));
           panel.add(panel.setWeapons(player));

    }





}
