/*
 * Authors: Joshua Brohm and Sam Pearson
 * C12A-2 Clue Paths
 */


package experiment;

import java.util.HashSet;

import java.util.Set;

public class TestBoardCell {
	private int row;
	private int column;
	private Set<TestBoardCell> adjList;
	private boolean isRoom = false;
	private boolean isOccupied = false;
	
	public TestBoardCell(int row, int column) {
		adjList = new HashSet<TestBoardCell>();
		this.row = row;
		this.column = column;	
	}
	
	
	public TestBoardCell(int row, int column, boolean occupy, boolean room) {
		adjList = new HashSet<TestBoardCell>();
		this.row = row;
		this.column = column;
		this.isOccupied = occupy;
		this.isRoom = room;
	}
	
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	
	//the adj list cannot be made until the actual game board has been made, becuase it contains cells
	//putting the code to make an adj list in the constructor of a board cell ended up making it a recursive 
	//constructor,  which ended up damaging performance significantly and none of the tests passed
	public Set<TestBoardCell> getAdjList(TestBoardCell[][] grid){
		if(column != 0) {
			adjList.add(grid[column - 1][row]);
		}
		if(column != TestBoard.ROWS - 1) {
			adjList.add(grid[column + 1][row]);
		}
		if(row != 0) {
			adjList.add(grid[column][row - 1]);
		}
		if(row != TestBoard.COLS - 1) {
			adjList.add(grid[column][row + 1]);
		}
		return adjList;
	}
	
	public void setRoom(boolean b) {
		isRoom = b;
	}
	
	public boolean isRoom() {
		return isRoom;
	}
	
	public void setOccupied(boolean b) {
		isOccupied = b;
	}
	
	public boolean getOccupied() {
		return isOccupied;
	}
}
