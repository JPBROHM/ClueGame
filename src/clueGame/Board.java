package clueGame;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import experiment.TestBoardCell;


public class Board {
	

	private int numRows;
	private int numColumns;
	private BoardCell[][] grid;
	private String csvFile;
	private String textFile;
	private Set<BoardCell> visited;
	private Set<BoardCell> legalTargets;
	private Map<Character, Room> rooms;
	private Map<String, String> roomNames;


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
	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize(){
		rooms = new HashMap<Character, Room>();
		roomNames = new HashMap<String, String>();
		try {
			loadSetupConfig();
			loadLayoutConfig();
		} catch (FileNotFoundException | BadConfigFormatException e) {
			//this catch is empty as both loadSetupConfig and loadLayoutConfig throw BadConfig exceptions with messages
			//as to what exactly caused the exception to be thrown that are more specific than this catch can be
		}
		
		
	}
		
	

	


	
	

	public void loadSetupConfig() throws BadConfigFormatException, FileNotFoundException {

		roomNames = new HashMap<String, String>();
		FileReader f;
		f = new FileReader(textFile);
		//read in all the rooms/spaces skipping comments
		Scanner sc = new Scanner(f);
		String room;
		while(sc.hasNext()) {
			room = sc.nextLine();
			if(room.charAt(0) == '/') {
				continue;
			} else {
				String[] currRoom = room.split(",");
				//if room or space is misspelled, or if the setup file contains a classification that isnt a room or a space throw an error 
				//specifying that its the setup file causing the issue
				if (!currRoom[0].equals("Room") && !currRoom[0].equals("Space")) {
					throw new BadConfigFormatException("Bad setup file, contains area that is not a room or space");
				}
				String name = currRoom[1];
				String letter = currRoom[2];
				roomNames.put(letter, name);					
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
				String key = " " + currRow[j].charAt(0);

				//if they key is in the roomNames map, its a valid room/space so it can be added to the rooms map
				if (roomNames.containsKey(key)) {
					Room room1 = new Room(roomNames.get(key).substring(1));
					rooms.putIfAbsent(currRow[j].charAt(0), room1);

					//if the current space is a label space or room center space, add the cell location to the the rooms information
					//in the map, allowing the center and label cells for each room to be accessed later
					if(currRow[j].length() > 1) {

						if (currRow[j].charAt(1) == '#') {
							room1 = rooms.get(currRow[j].charAt(0));
							room1.setLabel(grid[i][j]);
							rooms.replace(currRow[j].charAt(0), room1);
						}

						if (currRow[j].charAt(1) == '*') {
							room1 = rooms.get(currRow[j].charAt(0));
							room1.setCenter(grid[i][j]);
							rooms.replace(currRow[j].charAt(0), room1);
						}
					}
				//if the key was not in roomNames map, then it is an invalid space, so throw an exception giving specific values
				//as to what grid caused the problem along with the room it was trying to create
				} else {
					throw new BadConfigFormatException("Error: Room/space " + key + "at row " + i + "column " + j + " does not exist");
				}
			}
		}
		for (int i = 0; i < numRows; i++ ) {
			for (int j = 0; j < numColumns; j++) {
				calcAdjList(i, j);
			}
		}
	}



	
	
	public void setConfigFiles(String csvFile, String textFile) {
		this.csvFile = "data/" + csvFile;
		this.textFile = "data/" + textFile;
		
	}
	
	
	public Room getRoom(char c) {
		Room room = rooms.get(c);
		return room;
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
	
	
	public Set<BoardCell> getAdjList(int row, int column){
		return grid[row][column].getAdjList();
	}
	
	
	
	public void calcAdjList(int row, int column) {
		Set<BoardCell> adjList = new HashSet<BoardCell>();
		if(column != 0 ) {
			if (grid[row][column - 1].getCellLabel().charAt(0) == 'W') {
				grid[row][column].addToAdjList(grid[row][column - 1]);
			}
		} 
		if(column != grid[0].length - 1 ) {
			if (grid[row][column + 1].getCellLabel().charAt(0) == 'W') {
				grid[row][column].addToAdjList(grid[row][column + 1]);
			}
		}
		if(row != 0 ) {
			if (grid[row - 1][column].getCellLabel().charAt(0) == 'W') {
				grid[row][column].addToAdjList(grid[row - 1][column]);
			}
		}
		if(row != grid.length - 1 ) {
			if (grid[row + 1][column].getCellLabel().charAt(0) == 'W') {
				grid[row][column].addToAdjList(grid[row + 1][column]);
			}
		}
		if (grid[row][column].isDoorway()) {
			DoorDirection dDir = grid[row][column].getDoorDirection();
			BoardCell roomCenter;
			char labelChar;
			String roomCellLabel;
			switch (dDir) {
			case UP:
				roomCellLabel = grid[row - 1][column].getCellLabel();
				labelChar = roomCellLabel.charAt(0);
				roomCenter = rooms.get(labelChar).getCenterCell();
				grid[row][column].addToAdjList(roomCenter);
				grid[roomCenter.getRow()][roomCenter.getColumn()].addToAdjList(grid[row][column]);
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
			char first=grid[row][column].getFirst();
			char secretPassage=grid[row][column].getSecretPassage();

			int numRoom=0;
			if (row != grid.length - 1 && grid[row+1][column].isRoom()) {numRoom++;}
			if (column != grid[0].length - 1 && grid[row][column+1].isRoom()) {numRoom++;}
			if (row != 0 && grid[row-1][column].isRoom()) {numRoom++;}
			if (column != 0 && grid[row][column-1].isRoom() ) {numRoom++;}

			if (numRoom<2) {
				//scan through to find inverse order of ^^, add to adj list.
				String passSec = ""+secretPassage+first;
				for(int i=0;i<numRows;i++) {
					for (int j=0; j<numColumns;j++) {
						if (grid[i][j].getCellLabel().equals(passSec)) {
							grid[row][column].addToAdjList(grid[i][j]);
						}
					}
				}
			}
			else {

				//find room == char secretPassage
				//find that room's center
				//add to adj list
				int room1Row=0;
				int room1Col=0;
				int room2Row=0;
				int room2Col=0;
				String passSec = ""+secretPassage+'*';
				String secPass=""+first+'*';
				for(int i=0;i<numRows;i++) {
					for (int j=0; j<numColumns;j++) {
						if (grid[i][j].getCellLabel().equals(passSec)) {
							room1Row=i;
							room1Col=j;
						}
					}
				}
				for(int i=0;i<numRows;i++) {
					for (int j=0; j<numColumns;j++) {
						if (grid[i][j].getCellLabel().equals(secPass)) {
							room2Row=i;
							room2Col=j;
						}
					}
				}
				//add room1 and room2 to eachother adj lists
				grid[room2Row][room2Col].addToAdjList(grid[room1Row][room1Col]);

			}

		}


	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		legalTargets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		visited.add(cell);
		findAllTargets(cell, pathLength);
		
	}
	
	//find all the targets, but if an adjCell is occupied do not visit it
	public void findAllTargets(BoardCell cell, int numSteps) {
		for (BoardCell adjCell : cell.getAdjList()) {
			if (adjCell.isRoom() && (!visited.contains(adjCell))) {
				legalTargets.add(adjCell);
				visited.add(adjCell);
			}
			else if ((!visited.contains(adjCell)) && (!adjCell.getOccupied()) && !adjCell.isRoom()) {
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
	
	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return legalTargets;
	}

}
