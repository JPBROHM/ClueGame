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
		//set hand
		GameKnownCardsPanel peoplePanel = new GameKnownCardsPanel();
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		ArrayList<Card> peopleCards = new ArrayList<>();
		for (int i = 0; i < player.getPlayerHand().size(); i++) {
			if (player.getPlayerHand().get(i).getType() == CardType.PERSON) {
				peopleCards.add(player.getPlayerHand().get(i));
			}
		}
        peoplePanel.setLayout(new GridLayout(peopleCards.size() + player.getPeopleSeen().size() + 2, 0));
		
        JLabel hand = new JLabel("In Hand:");
        peoplePanel.add(hand);
        
        for (int i = 0; i < peopleCards.size(); i++) {
        	peoplePanel.add(new JLabel(peopleCards.get(i).getName()));
        }
        
        
		//set seen
        peoplePanel.add(new JLabel("Seen:"));
        for (String name : player.getPeopleSeen()) {
        	peoplePanel.add(new JLabel(name));
        	
        }
        
        return peoplePanel;
		
	}
	
	public GameKnownCardsPanel setWeapons(HumanPlayer player) {
		GameKnownCardsPanel weaponPanel = new GameKnownCardsPanel();
		weaponPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		ArrayList<Card>weaponCards = new ArrayList<>();
		for (int i = 0; i < player.getPlayerHand().size(); i++) {
			if (player.getPlayerHand().get(i).getType() == CardType.WEAPON) {
				weaponCards.add(player.getPlayerHand().get(i));
			}
		}
        weaponPanel.setLayout(new GridLayout( weaponCards.size() + player.getWeaponsSeen().size() + 2,0));
		
        JLabel hand = new JLabel("In Hand:");
       weaponPanel.add(hand);
        
        for (int i = 0; i < weaponCards.size(); i++) {
        	weaponPanel.add(new JLabel(weaponCards.get(i).getName()));
        }
        
        
		//set seen
      weaponPanel.add(new JLabel("Seen:"));
        for (String name : player.getPeopleSeen()) {
        	weaponPanel.add(new JLabel(name));
        	
        }
        
        return weaponPanel;}
	
	
	public GameKnownCardsPanel setRooms(HumanPlayer player) {
		GameKnownCardsPanel roomPanel = new GameKnownCardsPanel();
		roomPanel.setBorder(new TitledBorder (new EtchedBorder(), "Room"));
		ArrayList<Card> roomCards = new ArrayList<>();
		for (int i = 0; i < player.getPlayerHand().size(); i++) {
			if (player.getPlayerHand().get(i).getType() == CardType.ROOM) {
				roomCards.add(player.getPlayerHand().get(i));
			}
		}
        roomPanel.setLayout(new GridLayout(roomCards.size() + player.getRoomsSeen().size() + 2, 0));
		
        JLabel hand = new JLabel("In Hand:");
        roomPanel.add(hand);
        
        for (int i = 0; i < roomCards.size(); i++) {
        	roomPanel.add(new JLabel(roomCards.get(i).getName()));
        }
        
        
		//set seen
        roomPanel.add(new JLabel("Seen:"));
        for (String name : player.getPeopleSeen()) {
        	roomPanel.add(new JLabel(name));
        	
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
