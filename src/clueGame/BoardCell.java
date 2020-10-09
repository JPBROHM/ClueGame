package clueGame;

import java.util.HashSet;
import java.util.Set;

import experiment.TestBoardCell;

public class BoardCell {
	private int row;
	private int column;
	private Set<BoardCell> adjList;
	private boolean doorway;
	private boolean label;
	private boolean roomCenter;
	private char secretPassage;
	private DoorDirection doorDirection;
	private char First;

	public BoardCell() {
		// TODO Auto-generated constructor stub
	}

	public BoardCell(int column, int row) {
		adjList = new HashSet<BoardCell>();
		this.row = row;
		this.column = column;
	}

	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return doorDirection;
	}

	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return doorway;
	}

	public boolean isLabel() {
		// TODO Auto-generated method stub
		return label;
	}

	public boolean isRoomCenter() {
		// TODO Auto-generated method stub
		return roomCenter;
	}

	public char getSecretPassage() {
		// TODO Auto-generated method stub
		return secretPassage;
	}
	public char getFirst() {
		return First;
	}


}
