package clueGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
		//random setting of num rows and cols to create a grid for testing, will change later rn its just some arbitrary value 
		//get a testable board that will fail all tests
		numColumns = 25;
		numRows=25;
		grid = new BoardCell[numColumns][numRows];
		for (int i=0; i<numColumns; i++) {
			for (int j=0; j<numRows; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
	}

	

	public void setConfigFiles(String csvFile, String textFile) {
		this.csvFile = csvFile;
		this.textFile = textFile;
		
	}

	public void loadSetupConfig() {
		// TODO Auto-generated method stub
		
	}

	public void loadLayoutConfig() {
		// TODO Auto-generated method stub
		
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

	public BoardCell getCell(int column, int row) {
		// TODO Auto-generated method stub
		return grid[column][row];
	}

	public Room getRoom(BoardCell cell) {
		// TODO Auto-generated method stub
		return null;
	}

}
