
package clueGame;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clueGame.GameControlPanel.NextButtonListener;
import experiment.TestBoardCell;


public class Board extends JPanel{
	

	private int numRows;
	private int numColumns;
	private int rectWidth;
	private int rectHeight;
	private BoardCell[][] grid;
	private String csvFile;
	private String textFile;
	private Set<BoardCell> visited;
	private Set<BoardCell> legalTargets;
	private Map<Character, Room> rooms;
	private Map<String, String> players;
	private Set<String> weapons;
	private ArrayList<Suspect> allCharacters;
	private Set<Suspect> computers;
	private Suspect human;
	private Solution solution;
	private ArrayList<Card> deck;
	private Set<Card> deckSet;
	private ArrayList<Card> roomCards;
	private int turnCount;
	private int eventCount;

	
	
	public int getEventCount() {
		return eventCount;
	}


	public void addEventCount(int eventCount) {
		this.eventCount += eventCount;
	}


	public int rollDice() {
		Random die = new Random();
		return die.nextInt(6) + 1;
	}
	
	
	public int getTurnCount() {
		return turnCount;
	}




	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}
	
	public void nextTurn() {
		turnCount++;
		eventCount = 0;
		if (turnCount == 6) {
			turnCount = 0;
			human.setHasMoved(false);
		}
	}




	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {
		super() ;
	}
	
	
	
	
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//calculate the size for one of the squares
		rectWidth = getWidth() / numColumns;
		rectHeight = getHeight()/ numRows;
		//iterate through the grid, making each boardCell draw itself
		for (int i = 0; i < numColumns; i++) {
			for (int j = 0; j < numRows; j++) {
				
				if (legalTargets.contains(grid[j][i]) && turnCount == 0) {
					grid[j][i].drawTarget(g, j, i, rectWidth, rectHeight);
				}
				else {grid[j][i].draw(g, j, i, rectWidth, rectHeight);}
			}
		}
		//iterate through the rooms, making the rooms display their names
		for (Entry<Character, Room> room : rooms.entrySet()) {
			Room roomDraw = room.getValue();
			roomDraw.drawRoom(g, rectWidth, rectHeight);
		}
		
	
		//iterate through the players, drawing a circle in their color for each player
		for(Suspect sus : allCharacters) {
			sus.draw(g, rectWidth, rectHeight);
		}
	}

	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize(){
		addMouseListener(new BoardListener());
		legalTargets = new HashSet<>();
		rooms = new HashMap<Character, Room>();
		weapons = new HashSet<String>();
		players = new HashMap<String, String>();
		allCharacters = new ArrayList<Suspect>();
		computers = new HashSet<Suspect>();
		deckSet = new HashSet<>();
		solution = new Solution();
		roomCards = new ArrayList<>();
		turnCount = 0;
		Random r = new Random();
		int count = 0;
		Color color = new Color(0,0,0);
		try {
			loadSetupConfig();
			loadLayoutConfig();
		} catch (FileNotFoundException | BadConfigFormatException e) {
			e.getMessage();
		}

		
		if(!players.isEmpty()) {
			for (Map.Entry<String, String> entry : players.entrySet()) {
				//each player has a corresponding color, it was easiest to then give each color a corresponding starting location
				//then use that to set the starting location of the player
				int row = 0;
				int col = 0;
				if (entry.getValue().equals("Blue")) {
					color = Color.BLUE;
					row = 0;
					col = 7;
				}
				if (entry.getValue().equals("Green")) {
					color = Color.GREEN;
					row = 6;
					col = 0;
				}
				if (entry.getValue().equals("Purple")) {
					color = Color.MAGENTA;
					row = 18;
					col = 0;
				}
				if (entry.getValue().equals("Red")) {
					color = Color.RED;
					row = 24;
					col = 7;
				}
				if (entry.getValue().equals("White")) {
					color = Color.WHITE;
					row = 17;
					col = 23;
				}
				if (entry.getValue().equals("Yellow")) {
					color = Color.YELLOW;
					row = 0;
					col = 18;
				}
				if (count == 0) {
					human = new HumanPlayer(entry.getKey(), color, row, col);
					allCharacters.add(human);
					count++;
				}
				else {
					Suspect computer = new ComputerPlayer(entry.getKey(), color, row, col);
					computers.add(computer);
					allCharacters.add(computer);
				}
			}

			//this next chunks purpose is to create three separate "decks" one of all room, weapons and players,
			//the point of this is to separate them first so the solution can be made, then they can be put into a deck and that large deck
			//dealt out to the players
			ArrayList<Card> playerCards = new ArrayList<>();
			ArrayList<Card> weaponCards = new ArrayList<>();
			deck = new ArrayList<>();

			for (Entry<Character, Room> entry : rooms.entrySet()) {
				if (!(entry.getValue().getName().equals("Walkway") || entry.getValue().getName().equals("Unused"))) {
				
					roomCards.add(new Card(CardType.ROOM, entry.getValue().getName()));
				}
			}

			for (Entry<String, String> entry : players.entrySet()) {
				
				playerCards.add(new Card(CardType.PERSON, entry.getKey()));
			}

			for (String entry : weapons) {
				
				weaponCards.add(new Card(CardType.WEAPON, entry));
			}
			//this is where the solution gets created
			
			int p = (r.nextInt(playerCards.size() - 1));
			int w = (r.nextInt(weaponCards.size() - 1));
			int ro = (r.nextInt(roomCards.size() - 1));
			solution = new Solution( playerCards.get(p), roomCards.get(ro), 
					weaponCards.get(w));
			
			//all the cards are now put into one large deck
			for (int i = 0; i < playerCards.size(); i++) {
				deck.add(playerCards.get(i));
			}
			for (int i = 0; i < weaponCards.size(); i++) {
				deck.add(weaponCards.get(i));
			}
			for (int i = 0; i < roomCards.size(); i++) {
				deck.add(roomCards.get(i));
			}
			
			for (int i = 0; i < deck.size(); i++) {
				deckSet.add(deck.get(i));
			}
			//remove the cards that were used for the solution
			deck.remove(p);
			deck.remove(p+w);
			deck.remove(p+w+ro);
			int num = 0;
			while(!deck.isEmpty()) {
				for (Suspect sus : computers) {
					if(deck.size()>1) {
						num = r.nextInt(deck.size() - 1);
						sus.updateHand(deck.get(num));
						deck.remove(num);
					}
					else if(deck.size()==1) {
						sus.updateHand(deck.get(0));
						deck.remove(0);
						}
				}
				if(deck.size()>1) {
					num = r.nextInt(deck.size() - 1);
					human.updateHand(deck.get(num));
					deck.remove(num);
				}
				else if (deck.size()==1) {
					human.updateHand(deck.get(0));
					deck.remove(0);
				}
			}
			
		}
		
	}
		
	

	


	
	





	public void loadSetupConfig() throws BadConfigFormatException, FileNotFoundException {
		
		FileReader f;
		f = new FileReader(textFile);
		//read in all the rooms/spaces skipping comments
		Scanner sc = new Scanner(f);
		String room;
		while(sc.hasNext()) {
			room = sc.nextLine();
			if(room.charAt(0) != '/') {
				String[] currRoom = room.split(",");
				//if room or space is misspelled, or if the setup file contains a classification that isnt a room or a space throw an error 
				//specifying that its the setup file causing the issue
				if (!currRoom[0].equals("Room") && !currRoom[0].equals("Space") && !currRoom[0].equals("Weapon") && !currRoom[0].equals("Player")) {
					throw new BadConfigFormatException("Bad setup file, contains area that is not a room or space");
				}
				if (currRoom[0].equals("Room") || currRoom[0].equals("Space") ) {
					String name = currRoom[1].substring(1);
					char letter = currRoom[2].charAt(1);
					Room roomToAdd = new Room(name);
					rooms.put(letter, roomToAdd);
				}
				else if (currRoom[0].equals("Weapon")) {
					weapons.add(currRoom[1].substring(1));
				}
				else if (currRoom[0].equals("Player")) {
					players.put(currRoom[1].substring(1), currRoom[2].substring(1));
				}
			} 
		}
		sc.close();

	}

	
	
	
	
	
	
	
	
	
	
	
	public void loadLayoutConfig() throws BadConfigFormatException, FileNotFoundException {
		ArrayList<String> rows = new ArrayList<String>();
		FileReader f;
		//Read each row into an ArrayList of strings
		f = new FileReader(csvFile);
		Scanner s = new Scanner(f);
		String row;
		while(s.hasNext()) {
			row = s.nextLine();
			rows.add(row);
		}
		s.close();

		//set number of rows and columns
		numRows = rows.size();
		numColumns = rows.get(0).split(",").length;
		grid = new BoardCell[numRows][numColumns];


		//now that rows have all been read in, split into columns to be able ot create the grid
		for (int i=0; i<numRows; i++) {
			row = rows.get(i);
			String[] currRow = row.split(",");


			/*the number of columns was gotten from the first row, the first row may be the wrong number of but it doesn't 
			* really matter which row has the wrong amount of columns, because they should all have the exact same amount
			* throw an exception saying that it was an issue with number of columns, with row i+1 being the number (Not index)
			* of the first time a row with a different amount of columns is encountered
			*/
			if (currRow.length != numColumns) {
				throw new BadConfigFormatException("Bad number of colums in row" + i+1);
			}


			//iterate through each row and get the columns to create the grid cell by cell
			for (int j = 0; j < numColumns; j++) {
				grid[i][j] = new BoardCell(i,j,currRow[j]);
				
				
				//when the row was split, the character representation of the row has a space before it, since there was a space after the comma
				//which is the delimiter that was used, so add a space for the sake of checking it with the roomNames map before getting rid of said space
				//and adding to the rooms map
				char key = currRow[j].charAt(0);
				
				//if they key is in the roomNames map, its a valid room/space so it can be added to the rooms map
				if (!rooms.containsKey(key)) {	
					//if the key was not in roomNames map, then it is an invalid space, so throw an exception giving specific values
					//as to what grid caused the problem along with the room it was trying to create
					throw new BadConfigFormatException("Error: Room/space " + key + "at row " + i + "column " + j + " does not exist");
				}
				//if the current space is a label space or room center space, add the cell location to the the rooms information
				//in the map, allowing the center and label cells for each room to be accessed later
				else if(currRow[j].length() > 1 && (currRow[j].charAt(1) == '#')) {
					rooms.get(currRow[j].charAt(0)).setLabel(grid[i][j]);
				}

				else if (currRow[j].length() > 1 && (currRow[j].charAt(1) == '*')) {
					rooms.get(currRow[j].charAt(0)).setCenter(grid[i][j]);

				}
			}
			
		}
		for (int i = 0; i < numRows; i++ ) {
			for (int j = 0; j < numColumns; j++) {
				calcAdjList(i, j);
			}
		}
	}


	
	
	
	

	
	
	
	
	
	
	
	//calculating the adj List is much more complex than in the testBoardCell class
	//this is because we have to account for only be able to enter rooms through doorways along with not being able to walk 
	//around in rooms
	public void calcAdjList(int row, int column) {
		
		//set of if statements is similar to testBoardCell, but it only adds the cell to the adj list 
		//if its a walkway, since you need to be on a doorway to enter a room
		if(column != 0 && (grid[row][column - 1].isWalkway())) {
			
				grid[row][column].addToAdjList(grid[row][column - 1]);
			
		} 
		if(column != grid[0].length - 1 && (grid[row][column + 1].isWalkway() )) {
				grid[row][column].addToAdjList(grid[row][column + 1]);
			
		}
		if(row != 0 && (grid[row - 1][column].isWalkway()) ) {
			
				grid[row][column].addToAdjList(grid[row - 1][column]);
		
		}
		if(row != grid.length - 1 && (grid[row + 1][column].isWalkway()) ) {
			
				grid[row][column].addToAdjList(grid[row + 1][column]);
			
		}
		
		//Switch statement for if the player is on a doorway
		if (grid[row][column].isDoorway()) {
			DoorDirection dDir = grid[row][column].getDoorDirection();
			BoardCell roomCenter;
			char labelChar;
			String roomCellLabel;
			switch (dDir) {
			//each switch statement is the same, just the direction differs
			case UP:
				roomCellLabel = grid[row - 1][column].getCellLabel();							//go one cell in the direction of the doorway to get the character of the room
				labelChar = roomCellLabel.charAt(0);
				roomCenter = rooms.get(labelChar).getCenterCell();									//find that rooms center cell from the rooms map
				grid[row][column].addToAdjList(roomCenter);											//add the center cell of the room to the current cells adj list
				grid[roomCenter.getRow()][roomCenter.getColumn()].addToAdjList(grid[row][column]);  //add the doorway to the adj list of the room center
				break;
			case DOWN:
				roomCellLabel = grid[row + 1][column].getCellLabel();
				labelChar = roomCellLabel.charAt(0);
				roomCenter = rooms.get(labelChar).getCenterCell();
				grid[row][column].addToAdjList(roomCenter);
				grid[roomCenter.getRow()][roomCenter.getColumn()].addToAdjList(grid[row][column]);
				break;
			case LEFT:
				roomCellLabel = grid[row][column - 1].getCellLabel();
				labelChar = roomCellLabel.charAt(0);
				roomCenter = rooms.get(labelChar).getCenterCell();
				grid[row][column].addToAdjList(roomCenter);
				grid[roomCenter.getRow()][roomCenter.getColumn()].addToAdjList(grid[row][column]);
				break;
			case RIGHT:
				roomCellLabel = grid[row][column + 1].getCellLabel();
				labelChar = roomCellLabel.charAt(0);
				roomCenter = rooms.get(labelChar).getCenterCell();
				grid[row][column].addToAdjList(roomCenter);
				grid[roomCenter.getRow()][roomCenter.getColumn()].addToAdjList(grid[row][column]);
				break;
			default:
				break;
			
			}
		}

		//secret passageways
		if (grid[row][column].isSecretPassage()) {
			char first=grid[row][column].getSecretPassageStart();
			char secretPassage=grid[row][column].getSecretPassage();

			//if a secret passage way is surrounded by more than 2 cells that are part of a room then it is in that room
			//calculated because the 306 board has all the secret passageways in rooms, so they need to teleport to the room center
			//but our board has secret passageways that dont go into rooms they just go to a specific cell
			int numRoom=0;
			if (row != grid.length - 1 && grid[row+1][column].isRoom()) {
				numRoom++;
				}
			if (column != grid[0].length - 1 && grid[row][column+1].isRoom()) {
				numRoom++;
				}
			if (row != 0 && grid[row-1][column].isRoom()) {
				numRoom++;
				}
			if (column != 0 && grid[row][column-1].isRoom() ) {
				numRoom++;
				}

			//if the passageway teleports the player to a cell outside of a room
			if (numRoom<2) {
				//scan through to find inverse order of ^^, add to adj list.
				String passSec = ""+secretPassage+first;
				for(int i=0;i<numRows;i++) {
					for (int j=0; j<numColumns;j++) {
 						if (grid[i][j].getCellLabel().equals(passSec)) {
							grid[row][column].addToAdjList(grid[i][j]);
							grid[i][j].addToAdjList(grid[row][column]);
						}
					}
				}
			}
			
			//if the passageway teleports the player into a room
			else {
				//add room2 to room1s adj lists
				rooms.get(first).getCenterCell().addToAdjList(rooms.get(secretPassage).getCenterCell());

			}

		}


	}
	
	
	
	
	
	
	
	
	
	
	
	//add the the cell the player is on to the visited list then call the find targets method
	public void calcTargets(BoardCell cell, int pathLength) {
		legalTargets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		visited.add(cell);
		findAllTargets(cell, pathLength);
		
	}
	
	
	
	
	
	
	
	
	//find all the targets, but if an adjCell is occupied do not visit it
	//this is basically the same as in testBoard class, except the first if statement was added because players cannot 
	//enter and exit a room in the same turn
	public void findAllTargets(BoardCell cell, int numSteps) {
		for (BoardCell adjCell : cell.getAdjList()) {
			if (adjCell.isRoom() && (!visited.contains(adjCell))) {
				legalTargets.add(adjCell);
				visited.add(adjCell);
			}
			else if ((!visited.contains(adjCell)) && !adjCell.getOccupied()) {
				visited.add(adjCell);
				if(numSteps == 1){
					legalTargets.add(adjCell);
				} else {
					findAllTargets(adjCell, numSteps - 1);

				}
				visited.remove(adjCell);
			}
			


		}
	}
	
	
	
	
	public boolean checkAccusation(Solution accusation) {
		//if any of the cards in an accusation dont match the cards in the boards solution, return false
		if (!accusation.getPerson().getName().equals(solution.getPerson().getName())) {
			return false;
		}
		if (!accusation.getWeapon().getName().equals(solution.getWeapon().getName())) {
			return false;
		}
		if (!accusation.getRoom().getName().equals(solution.getRoom().getName())) {
			return false;
		}
		//if none of the if statements triggered to return false, the accusation is right so return true
		return true;
	}




	public Card handleSuggestion(Solution suggestion, Suspect player) {
		//iterate through all players, seeing if any can disprove the suggestion, is they can return whatever card they 
		//use to disprove the suggestion
		for (Suspect playeri : allCharacters) {
			if (!playeri.getName().equals(player.getName())) {
				if (playeri.disproveSuggestion(suggestion) == null) {
					continue;
				} else {
					return playeri.disproveSuggestion(suggestion);
				}
			}
		}
		return null;
	}
	
	
	
	public Set<BoardCell> getTargets() {

		return legalTargets;
	}
	
	public void setConfigFiles(String csvFile, String textFile) {
		this.csvFile = "data/" + csvFile;
		this.textFile = "data/" + textFile;
		
	}
	
	
	public Room getRoom(char c) {
		Room room = rooms.get(c);
		return room;
	}
	
	public Room getRoom(String name) {
		for (Entry<Character, Room> room : rooms.entrySet()) {
			if (room.getValue().getName().equals(name)) {
				return room.getValue();
			}
		}
		return null;
	}
	
	public boolean roomExists(char c) {
		return rooms.containsKey(c);
	}

	
	
	public int getNumRows() {
		return numRows;
	}
	
	

	public int getNumColumns() {
		return numColumns;
	}
	
	

	public BoardCell getCell(int row, int column) {
		return grid[row][column];
	}
	
	

	public Room getRoom(BoardCell cell) {
		String label = cell.getCellLabel();
		Room room = rooms.get(label.charAt(0));
		return room;
	}
	
	
	public Map<Character, Room> getRooms() {
		return rooms;
	}




	public Set<BoardCell> getAdjList(int row, int column){
		return grid[row][column].getAdjList();
	}
	
	
	public Set<String> getWeapons(){
		return weapons;
	}
	
	public Map<String, String> getPlayers(){
		return players;
	}
	
	public Set<Suspect> getComputers(){
		return computers;
	}
	public ArrayList<Suspect> getAllCharacters(){
		return allCharacters;
	}
	public Suspect getHuman(){
		return human;
	}
	
	public Solution getSolution(){
		return solution;
	}
	public ArrayList<Card> getDeck(){
		return deck;
	}
	
	public Set<Card> getDeckSet(){
		
		return deckSet;
	}
	public ArrayList<Card> getRoomCards() {
		return roomCards;
	}

	
	
	
	public void setSolution(Solution solution) {
		this.solution = solution;
	}


	//These three setter are used for testing purposes, as the initialize method randomly selects a solution, along with randomly deals the deck
	//but for testing we need to know exactly what the solution is and what cards are in each players hand, setters allow us to create a custom solution/players
	//with custom hands so we know what to test
	public void setAllCharacters(ArrayList<Suspect> allCharacters) {
		this.allCharacters = allCharacters;
	}




	public void setComputers(Set<Suspect> computers) {
		this.computers = computers;
	}




	public void setHuman(Suspect human) {
		this.human = human;
	}
	

	class BoardListener implements MouseListener {
		

		@Override
		public void mouseClicked(MouseEvent e) {
			//get instance of board and set size or rectangle
			Board board = Board.getInstance();
			Set<BoardCell> targets = board.getTargets();
			//only tries to move if they have places they can move
			if(targets.size()!=0) {
				int rectWidth = board.getWidth() / (board.getNumColumns());
				int rectHeight = board.getHeight() / (board.getNumRows());

				if (turnCount == 0) {
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
							if(board.getCell(board.getHuman().getRow(), board.getHuman().getCol()).isRoom()) {
								//handle suggestion stuff
							} 
							else{}



						}

					}
					//mod 2 because there was an issue with it printing twice 
					if (!getHuman().isHasMoved() && board.getEventCount()% 2 == 0) {
						JOptionPane.showMessageDialog(null,"Error: You have selected an invalid tile. \n Please select one of the yellow spaces.", 
								"Invalid Tile", JOptionPane.PLAIN_MESSAGE);

					}
					board.addEventCount(1);
					//show updated board (continues to show possible tile markings until next is pressed so person isn't locked into their first click)
					board.repaint();



				}
			}
			//if human player has no moves skips their turn via setting has moved to true
			else {board.getHuman().setHasMoved(true);}
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




}
