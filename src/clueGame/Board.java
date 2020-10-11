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
	private Map<Character, Room> rooms = new HashMap<Character, Room>();


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
		rooms.clear();
		
		ArrayList<String> rows = new ArrayList<String>();
		FileReader f;
		Map<String, String> roomNames = new HashMap<String, String>();
		
		try {
			f = new FileReader(textFile);
			Scanner sc = new Scanner(f);
			String room;
			while(sc.hasNext()) {
				room = sc.nextLine();
				if(room.charAt(0) == '/') {
					continue;
				} else {
					String[] currRoom = room.split(",");
					String name = currRoom[1];
					String letter = currRoom[2];
					roomNames.put(letter, name);					
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			f = new FileReader(csvFile);
			Scanner s = new Scanner(f);
			String row;
			while(s.hasNext()) {
				row = s.nextLine();
				rows.add(row);
			}
			s.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		

		numRows = rows.size();
		numColumns = rows.get(0).split(",").length;
		grid = new BoardCell[numRows][numColumns];

		for (int i=0; i<numRows; i++) {

			String row = rows.get(i);
			String[] currRow = row.split(",");
			
			for (int j = 0; j < numColumns; j++) {
				grid[i][j] = new BoardCell(i,j,currRow[j]);
					String key = " " + currRow[j].charAt(0);
					if (roomNames.containsKey(key)) {
						Room room = new Room(roomNames.get(key).substring(1));
						rooms.putIfAbsent(currRow[j].charAt(0), room);
						if(currRow[j].length() > 1) {
							if (currRow[j].charAt(1) == '#') {
								room = rooms.get(currRow[j].charAt(0));
								room.setLabel(grid[i][j]);
								rooms.replace(currRow[j].charAt(0), room);
							}
							if (currRow[j].charAt(1) == '*') {
								room = rooms.get(currRow[j].charAt(0));
								room.setCenter(grid[i][j]);
								rooms.replace(currRow[j].charAt(0), room);
							}
						}
						
						
					} 
					
				
			}
		}
	}
		
	

	

	public void setConfigFiles(String csvFile, String textFile) {
		this.csvFile = "data/" + csvFile;
		this.textFile = "data/" + textFile;
		
	}

	public void loadSetupConfig() throws BadConfigFormatException, FileNotFoundException {
		FileReader f = new FileReader(textFile);
		Scanner sc = new Scanner(f);
		String room;
		while(sc.hasNext()) {
			room = sc.nextLine();
			if(room.charAt(0) == '/') {
				continue;
			} 
		}
		sc.close();
		
	}

	public void loadLayoutConfig() throws BadConfigFormatException, FileNotFoundException {
		
		
		ArrayList<String> rows = new ArrayList<String>();
		FileReader f;
		Map<String, String> roomNames = new HashMap<String, String>();
		
	
			f = new FileReader(textFile);
			Scanner sc = new Scanner(f);
			String room;
			while(sc.hasNext()) {
				room = sc.nextLine();
				if(room.charAt(0) == '/') {
					continue;
				} else {
					String[] currRoom = room.split(",");
					if (currRoom[0] != "Room" || currRoom[0] != "Space") {
						throw new BadConfigFormatException();
					}
					String name = currRoom[1];
					String letter = currRoom[2];
					roomNames.put(letter, name);					
				}
			}
			sc.close();
	



	
			f = new FileReader(csvFile);
			Scanner s = new Scanner(f);
			String row;
			while(s.hasNext()) {
				row = s.nextLine();
				rows.add(row);
			}
			s.close();
		




		numRows = rows.size();
		numColumns = rows.get(0).split(",").length;
		grid = new BoardCell[numRows][numColumns];

		for (int i=0; i<numRows; i++) {

			row = rows.get(i);
			
			String[] currRow = row.split(",");
			if (currRow.length != numColumns) {
				throw new BadConfigFormatException();
			}
			for (int j = 0; j < numColumns; j++) {
				grid[i][j] = new BoardCell(i,j,currRow[j]);
				
				String key = " " + currRow[j].charAt(0);
				
				if (roomNames.containsKey(key)) {
					Room room1 = new Room(roomNames.get(key).substring(1));
					rooms.put(currRow[j].charAt(0), room1);
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


				} else {
					throw new BadConfigFormatException();
				}


			}
		}
	}
	public Room getRoom(char c) {
		Room room = rooms.get(c);
		return room;
	}
	
	public boolean roomExists(char c) {
		return rooms.containsKey(c);
	}

	public int getNumRows() {
		// TODO Auto-generated method stub
		return numRows;
	}

	public int getNumColumns() {
		// TODO Auto-generated method stub
		return numColumns;
	}

	public BoardCell getCell(int row, int column) {
		// TODO Auto-generated method stub
		return grid[row][column];
	}

	public Room getRoom(BoardCell cell) {
		String label = cell.getCellLabel();
		Room room = rooms.get(label.charAt(0));
		return room;
	}

}
