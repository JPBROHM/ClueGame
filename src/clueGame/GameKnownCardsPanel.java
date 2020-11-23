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
	JTextField personCard1 = new JTextField("Not Seen            ");
	JTextField personCard2 = new JTextField("Not Seen            ");
	JTextField personCard3 = new JTextField("Not Seen            ");
	JTextField personCard4 = new JTextField("Not Seen            ");
	JTextField personCard5 = new JTextField("Not Seen            ");
	JTextField personCard6 = new JTextField("Not Seen            ");

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
		
		peoplePanel.setLayout(new GridLayout(9, 0));
		
		
		JLabel hand = new JLabel("In Hand:");
		peoplePanel.add(hand);


		if (peopleCards.size() == 0) {
			peoplePanel.add(new JTextField("None"));
		}

        for (int i = 0; i < peopleCards.size(); i++) {
        	peoplePanel.add(new JTextField(peopleCards.get(i).getName()));
        	count -= 1;
        }
        
        
		//set seen & display
        peoplePanel.add(new JLabel("Seen:"));
        
        peopleCardsSeen = player.getPeopleSeen().size();
        for (String name : player.getPeopleSeen()) {
        	peoplePanel.add(new JTextField(name));
        	count -= 1;
        }
        for (int i = 0; i < count; i++) {
        	switch (i) {
        	case 0: 
        		peoplePanel.add(personCard1);
        		break;
        	case 1: 
        		peoplePanel.add(personCard2);
        		break;
        	case 2: 
        		peoplePanel.add(personCard3);
        		break;
        	case 3: 
        		peoplePanel.add(personCard4);
        		break;
        	case 4: 
        		peoplePanel.add(personCard5);
        		break;   
        	case 5:
        		peoplePanel.add(personCard6);
        		break;
        	}
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
		weaponCardsSeen = player.getWeaponsSeen().size();
		for (String name : player.getWeaponsSeen()) {
			weaponPanel.add(new JTextField(name));
			count -= 1;
		}
		for (int i = 0; i < count; i++) {
			switch (i) {
        	case 0: 
        		weaponPanel.add(weaponCard1);
        		break;
        	case 1: 
        		weaponPanel.add(weaponCard2);
        		break;
        	case 2: 
        		weaponPanel.add(weaponCard3);
        		break;
        	case 3: 
        		weaponPanel.add(weaponCard4);
        		break;
        	case 4: 
        		weaponPanel.add(weaponCard5);
        		break;        	       	        	
        	}
		}

		return weaponPanel;
	}

	
	public GameKnownCardsPanel setRooms(Suspect player) {
		int count = 5;
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
		
		roomPanel.setLayout(new GridLayout(8, 0));
		
		
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
        roomCardsSeen = player.getRoomsSeen().size();
        for (String name : player.getRoomsSeen()) {
        	roomPanel.add(new JTextField(name));
        	count -= 1;
        }
        for (int i = 0; i < count; i++) {
        	switch (i) {
        	case 0: 
        		roomPanel.add(roomCard1);
        		break;
        	case 1: 
        		roomPanel.add(roomCard2);
        		break;
        	case 2: 
        		roomPanel.add(roomCard3);
        		break;
        	case 3: 
        		roomPanel.add(roomCard4);
        		break;
        	case 4: 
        		roomPanel.add(roomCard5);
        		break;        	       	        	
        	}
		}
        
        return roomPanel;
	}
	
	


		public void update() {
			int numPeopleSeen = board.getHuman().getPeopleSeen().size();
			int numWeaponsSeen = board.getHuman().getWeaponsSeen().size();
			int numRoomsSeen = board.getHuman().getRoomsSeen().size();
			
			
			
			if (numPeopleSeen != peopleCardsSeen) {
				int nameCount = 0;
				switch (numPeopleSeen) {
				case 1:
					for (String name : board.getHuman().getPeopleSeen()) {
						personCard1.setText(name);
					}
				case 2:
					nameCount = 0;
					for (String name : board.getHuman().getPeopleSeen()) {
						if (nameCount == 0) {
							personCard1.setText(name);
							
						}
						if (nameCount == 1) {
							personCard2.setText(name);
						}
						nameCount++;
					}
				case 3:
					nameCount = 0;
					for (String name : board.getHuman().getPeopleSeen()) {
						if (nameCount == 0) {
							personCard1.setText(name);
						
						}
						if (nameCount == 1) {
							personCard2.setText(name);
							
						}
						
						if (nameCount == 2) {
							personCard3.setText(name);
						}
						nameCount++;
					}
				case 4:
					nameCount = 0;
					for (String name : board.getHuman().getPeopleSeen()) {
						if (nameCount == 0) {
							personCard1.setText(name);
							
						}
						if (nameCount == 1) {
							personCard2.setText(name);
							
						}
						if (nameCount == 2) {
							personCard3.setText(name);
							
						}
						if (nameCount == 3) {
							personCard4.setText(name);
						}
						nameCount++;
					}
				case 5:
					nameCount = 0;
					for (String name : board.getHuman().getPeopleSeen()) {
						if (nameCount == 0) {
							personCard1.setText(name);
						
						}
						if (nameCount == 1) {
							personCard2.setText(name);
							
						}
						if (nameCount == 2) {
							personCard3.setText(name);
							
						}
						if (nameCount == 3) {
							personCard4.setText(name);
						
						}
						if (nameCount == 4) {
							personCard5.setText(name);
						}
						nameCount++;
					}
				case 6:
					nameCount = 0;
					for (String name : board.getHuman().getPeopleSeen()) {
						if (nameCount == 0) {
							personCard1.setText(name);
						
						}
						if (nameCount == 1) {
							personCard2.setText(name);
							
						}
						if (nameCount == 2) {
							personCard3.setText(name);
							
						}
						if (nameCount == 3) {
							personCard4.setText(name);
						
						}
						if (nameCount == 4) {
							personCard5.setText(name);
						}
						if (nameCount == 5) {
							personCard6.setText(name);
						}
						nameCount++;
					}
				}
					
			}
			
			
			
			
			
			
			
			
			
			if (numWeaponsSeen != weaponCardsSeen) {
				int nameCount = 0;
				switch (numWeaponsSeen) {
				case 1:
					for (String name : board.getHuman().getWeaponsSeen()) {
						weaponCard1.setText(name);
					}
				case 2:
					nameCount = 0;
					for (String name : board.getHuman().getWeaponsSeen()) {
						if (nameCount == 0) {
							weaponCard1.setText(name);
						}
						if (nameCount == 1) {
							weaponCard2.setText(name);
						}
						nameCount++;
					}
				case 3:
					nameCount = 0;
					for (String name : board.getHuman().getWeaponsSeen()) {
						if (nameCount == 0) {
							weaponCard1.setText(name);
							
						}
						if (nameCount == 1) {
							weaponCard2.setText(name);
							
						}
						if (nameCount == 2) {
							weaponCard3.setText(name);
						}
						nameCount++;
					}
				case 4:
					nameCount = 0;
					for (String name : board.getHuman().getWeaponsSeen()) {
						if (nameCount == 0) {
							weaponCard1.setText(name);

						}
						if (nameCount == 1) {
							weaponCard2.setText(name);
							
						}
						if (nameCount == 2) {
							weaponCard3.setText(name);
							
						}
						if (nameCount == 3) {
							weaponCard4.setText(name);
						}
						nameCount++;
					}
				case 5:
					nameCount = 0;
					for (String name : board.getHuman().getWeaponsSeen()) {
						if (nameCount == 0) {
							weaponCard1.setText(name);
							
						}
						if (nameCount == 1) {
							weaponCard2.setText(name);
							
						}
						if (nameCount == 2) {
							weaponCard3.setText(name);
							
						}
						if (nameCount == 3) {
							weaponCard4.setText(name);
							
						}
						if (nameCount == 4) {
							weaponCard5.setText(name);
						}
						nameCount++;
					}
				}
					
			}
			
			
			
			
			
			
			
			if (numRoomsSeen != roomCardsSeen) {
				int nameCount = 0;
				switch (numRoomsSeen) {
				case 1:
					for (String name : board.getHuman().getRoomsSeen()) {
						roomCard1.setText(name);
					}
				case 2:
					nameCount = 0;
					for (String name : board.getHuman().getRoomsSeen()) {
						if (nameCount == 0) {
							roomCard1.setText(name);
							
						}
						if (nameCount == 1) {
							roomCard2.setText(name);
						}
						nameCount++;
					}
				case 3:
					nameCount = 0;
					for (String name : board.getHuman().getRoomsSeen()) {
						if (nameCount == 0) {
							roomCard1.setText(name);
						
						}
						if (nameCount == 1) {
							roomCard2.setText(name);
						
						}
						if (nameCount == 2) {
							roomCard3.setText(name);
						}
						nameCount++;
					}
				case 4:
					nameCount = 0;
					for (String name : board.getHuman().getRoomsSeen()) {
						if (nameCount == 0) {
							roomCard1.setText(name);
						
						}
						if (nameCount == 1) {
							roomCard2.setText(name);
							
						}
						if (nameCount == 2) {
							roomCard3.setText(name);
							
						}
						if (nameCount == 3) {
							roomCard4.setText(name);
						}
						nameCount++;
					}
				case 5:
					nameCount = 0;
					for (String name : board.getHuman().getRoomsSeen()) {
						if (nameCount == 0) {
							roomCard1.setText(name);
							
						}
						if (nameCount == 1) {
							roomCard2.setText(name);
							
						}
						if (nameCount == 2) {
							roomCard3.setText(name);
							
						}
						if (nameCount == 3) {
							roomCard4.setText(name);
							
						}
						if (nameCount == 4) {
							roomCard5.setText(name);
						}
						nameCount++;
					}
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
           HumanPlayer player = new HumanPlayer("Col. Mustard", color, 0, 0);
           
           Set<String> weaponsSeen = new HashSet<>();
           Set<String> peopleSeen = new HashSet<>();
          
           
           weaponsSeen.add("Pistol");
           weaponsSeen.add("Chainsaw");
           
           peopleSeen.add( "Colonel Mustard");
           player.updateHand(new Card(CardType.ROOM, "Hall"));
           player.updateHand(new Card(CardType.WEAPON, "Piano Wire"));
           player.updateHand(new Card(CardType.PERSON, "Mrs. White"));
           
           player.setWeaponCards(weaponsSeen);
           player.setPeopleCards(peopleSeen);
           

           panel.add(panel.setPeople(player));
           panel.add(panel.setRooms(player));
           panel.add(panel.setWeapons(player));

    }



}
