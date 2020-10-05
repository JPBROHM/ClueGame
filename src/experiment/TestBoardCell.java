package experiment;

import java.util.Set;

public class TestBoardCell {
	int row;
	int column;
	Set <TestBoardCell> adjList;
	boolean room;
	boolean occupied;
	
	public TestBoardCell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public Set<TestBoardCell> getAdjList(){
		return adjList;
	}
	
	public void setRoom(boolean b) {
		room = b;
	}
	
	public boolean isRoom() {
		return room;
	}
	
	public void setOccupied(boolean b) {
		occupied = b;
	}
	
	public boolean getOccupied() {
		return occupied;
	}
}
