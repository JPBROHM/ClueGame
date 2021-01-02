package clueGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameKnownCardsPanel extends JPanel{
	Board board = Board.getInstance();
	int peopleCardsSeen = 0;
	ArrayList<JTextField> personCardsArray = new ArrayList<>();
	ArrayList<JTextField> weaponCardsArray = new ArrayList<>();
	ArrayList<JTextField> roomCardsArray = new ArrayList<>();
	JTextField personCard1 = new JTextField("Not Seen            ");
	JTextField personCard2 = new JTextField("Not Seen            ");
	JTextField personCard3 = new JTextField("Not Seen            ");
	JTextField personCard4 = new JTextField("Not Seen            ");
	JTextField personCard5 = new JTextField("Not Seen            ");
	

	int weaponCardsSeen = 0;
	JTextField weaponCard1 = new JTextField("Not Seen            ");	
	JTextField weaponCard2 = new JTextField("Not Seen            ");
	JTextField weaponCard3 = new JTextField("Not Seen            ");
	JTextField weaponCard4 = new JTextField("Not Seen            ");
	JTextField weaponCard5 = new JTextField("Not Seen            ");
	
	int roomCardsSeen = 0;
	JTextField roomCard1 = new JTextField("Not Seen            ");
	JTextField roomCard2 = new JTextField("Not Seen            ");
	JTextField roomCard3 = new JTextField("Not Seen            ");
	JTextField roomCard4 = new JTextField("Not Seen            ");
	JTextField roomCard5 = new JTextField("Not Seen            ");
	JTextField roomCard6 = new JTextField("Not Seen            ");
	
	
	
	public GameKnownCardsPanel(int i, int j) {
		super();
		setLayout(new GridLayout(i, j));
	}


	public GameKnownCardsPanel() {
		super();
        setBorder(new TitledBorder (new EtchedBorder(), "Known Cards"));
        setLayout(new GridLayout(3, 0));
        Suspect player = board.getHuman();
     
        add(setPeople(player));
        add(setRooms(player));
        add(setWeapons(player));
	}
	
	
	public GameKnownCardsPanel setPeople(Suspect player) {
		int count = 5;
		//get hand
		GameKnownCardsPanel peoplePanel = new GameKnownCardsPanel(1 , 0);
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		ArrayList<Card> peopleCards = new ArrayList<>();
		for (int i = 0; i < player.getPlayerHand().size(); i++) {
			if (player.getPlayerHand().get(i).getType() == CardType.PERSON) {
				peopleCards.add(player.getPlayerHand().get(i));
			}
		}
		
		//setting things to the right size dependant on if they have/dont have cards in hand/seen
		
		peoplePanel.setLayout(new GridLayout(8, 0));
		
		
		JLabel hand = new JLabel("In Hand:");
		peoplePanel.add(hand);


		if (peopleCards.isEmpty()) {
			peoplePanel.add(new JTextField("None"));
		}

        for (int i = 0; i < peopleCards.size(); i++) {
        	peoplePanel.add(new JTextField(peopleCards.get(i).getName()));
        	count -= 1;
        }
        
        
		//set seen & display
        peoplePanel.add(new JLabel("Seen:"));
        
        for (int i = 0; i < count; i++) {
        	personCardsArray.add(new JTextField("Not Seen            "));
        	peoplePanel.add(personCardsArray.get(i));
        }
        
        peopleCardsSeen = player.getPeopleSeen().size();
        int cardCount = 0;
        for (String name : player.getPeopleSeen()) {
        	personCardsArray.get(cardCount).setText(name);
        	cardCount++;
        }
    
        
        return peoplePanel;
		
	}
	
	public GameKnownCardsPanel setWeapons(Suspect player) {
		int count = 5;
		//get hand
		GameKnownCardsPanel weaponPanel = new GameKnownCardsPanel(1,0);
		weaponPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		ArrayList<Card> weaponCards = new ArrayList<>();
		for (int i = 0; i < player.getPlayerHand().size(); i++) {
			if (player.getPlayerHand().get(i).getType() == CardType.WEAPON) {
				weaponCards.add(player.getPlayerHand().get(i));
			}
		}

		//setting things to the right size dependant on if they have/dont have cards in hand/seen

		weaponPanel.setLayout(new GridLayout(8, 0));
		
		JLabel hand = new JLabel("In Hand:");
		weaponPanel.add(hand);


		if (weaponCards.size() == 0) {
			weaponPanel.add(new JTextField("None"));
		}

		for (int i = 0; i < weaponCards.size(); i++) {
			weaponPanel.add(new JTextField(weaponCards.get(i).getName()));
			count -= 1;
		}


		//set seen & display
		weaponPanel.add(new JLabel("Seen:"));
		int cardCount = 0;
		for (int i = 0; i < count; i++) {
        	weaponCardsArray.add(new JTextField("Not Seen            "));
        	weaponPanel.add(weaponCardsArray.get(i));
        }
		
		weaponCardsSeen = player.getWeaponsSeen().size();
		for (String name : player.getWeaponsSeen()) {
			weaponCardsArray.get(cardCount).setText(name);
        	cardCount++;
		}

		return weaponPanel;
	}

	
	public GameKnownCardsPanel setRooms(Suspect player) {
		int count = 6;
		//get hand
		GameKnownCardsPanel roomPanel = new GameKnownCardsPanel(1,0);
		roomPanel.setBorder(new TitledBorder (new EtchedBorder(), "Room"));
		ArrayList<Card> roomCards = new ArrayList<>();
		for (int i = 0; i < player.getPlayerHand().size(); i++) {
			if (player.getPlayerHand().get(i).getType() == CardType.ROOM) {
				roomCards.add(player.getPlayerHand().get(i));
			}
		}
		
		//setting things to the right size dependant on if they have/dont have cards in hand/seen
		
		roomPanel.setLayout(new GridLayout(9, 0));
		
		
		JLabel hand = new JLabel("In Hand:");
		roomPanel.add(hand);


		if (roomCards.size() == 0) {
			roomPanel.add(new JTextField("None"));
		}

        for (int i = 0; i < roomCards.size(); i++) {
        	roomPanel.add(new JTextField(roomCards.get(i).getName()));
        	count -= 1;
        }
        
        
		//set seen & display
        roomPanel.add(new JLabel("Seen:"));
		int cardCount = 0;
		for (int i = 0; i < count; i++) {
        	roomCardsArray.add(new JTextField("Not Seen            "));
        	roomPanel.add(roomCardsArray.get(i));
        }
		
		roomCardsSeen = player.getRoomsSeen().size();
		for (String name : player.getRoomsSeen()) {
			roomCardsArray.get(cardCount).setText(name);
        	cardCount++;
		}
       
        return roomPanel;
	}
	
	


		public  void update() {
			int numPeopleSeen = board.getHuman().getPeopleSeen().size();
			int numWeaponsSeen = board.getHuman().getWeaponsSeen().size();
			int numRoomsSeen = board.getHuman().getRoomsSeen().size();
			
			//doing these switch cases depending on how many cards has been seen is clearly alot of code and definitly not the most ideal
			//did this because it was the only way i could think of having a text field for every card from the start then updating those text field, as 
			//otherwise we would have to completely remake the entire panel and re-add it to the clueGame instance, and i couldn't figure out how to do that
			//for now this gets the job done and works, although if time allows for it after getting the game fully functional and do work that needs to be 
			//done for other classes
			//this method is going to be kept in mind for next major refactoring session we do
			
			if (numPeopleSeen != peopleCardsSeen) {
				int peopleCount = 0;
				for (String name : board.getHuman().getPeopleSeen()) {
					personCardsArray.get(peopleCount).setText(name);
					peopleCount++;
					repaint();
				}	
			}
			
		
			
			if (numWeaponsSeen != weaponCardsSeen) {
				
				int weaponCount = 0;
				for (String name : board.getHuman().getWeaponsSeen()) {
					weaponCardsArray.get(weaponCount).setText(name);
					weaponCount++;
					repaint();
				}		
			}
			
			
	
			
			
			if (numRoomsSeen != roomCardsSeen) {
				
				int roomCount = 0;
				for (String name : board.getHuman().getRoomsSeen()) {
					roomCardsArray.get(roomCount).setText(name);
					roomCount++;
					repaint();
				}		
			}	
		}

		
	
	

    public static void main(String[] args) {
           GameKnownCardsPanel panel = new GameKnownCardsPanel();  // create the panel
           JFrame frame = new JFrame();  // create the frame
           frame.setContentPane(panel); // put the panel in the frame
           frame.setSize(250, 750);  // size the frame
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
           frame.setVisible(true); // make it visible
           panel.setBorder(new TitledBorder (new EtchedBorder(), "Known Cards"));
           panel.setLayout(new GridLayout(3, 0));
           Color color = new Color(0,0,0);
           color = Color.YELLOW;
           
           
           Set<String> weaponsSeen = new HashSet<>();
           Set<String> peopleSeen = new HashSet<>();
          
           
           weaponsSeen.add("Pistol");
           weaponsSeen.add("Chainsaw");
           
           peopleSeen.add( "Colonel Mustard");
          
    }



}
