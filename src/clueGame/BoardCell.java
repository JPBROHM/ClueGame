package clueGame;

import java.util.HashSet;
import java.util.Set;

import experiment.TestBoardCell;

public class BoardCell  implements Comparable<BoardCell> {
	private int row;
	private int column;
	private String cellLabel;
	private Set<BoardCell> adjList;
	private boolean doorway;
	private boolean label;
	private boolean roomCenter;
	private char secretPassage;
	private DoorDirection doorDirection;
	private char first;

	public BoardCell(){
		// TODO Auto-generated constructor stub
	}

	public BoardCell(int row, int column, String cellLabel) {
		this.cellLabel = cellLabel;
		adjList = new HashSet<BoardCell>();
		this.row = row;
		this.column = column;
		if(cellLabel.length() == 1) {
			doorway = false;
			roomCenter = false;
			label = false;
		} else if(cellLabel.length() == 2) {
			if (cellLabel.charAt(1) == '^' || cellLabel.charAt(1) == 'v' || cellLabel.charAt(1) == '<' || cellLabel.charAt(1) == '>') {
				doorway = true;
				if (cellLabel.charAt(1) == '^') {
					doorDirection = DoorDirection.UP;
				}
				if (cellLabel.charAt(1) == 'v') {
					doorDirection = DoorDirection.DOWN;
				}
				if (cellLabel.charAt(1) == '<') {
					doorDirection = DoorDirection.LEFT;
				}
				if (cellLabel.charAt(1) == '>') {
					doorDirection = DoorDirection.RIGHT;
				}
			}
			else if(cellLabel.charAt(1)=='*') {
				roomCenter=true;
			}
			else if(cellLabel.charAt(1) == '#') {
				label=true;
				}
			else {
				first=cellLabel.charAt(0);
				secretPassage=cellLabel.charAt(1);
			}
		}
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
		return roomCenter;
	}

	public char getSecretPassage() {
		// return the second character
		return secretPassage;
	}
	public char getFirst() {
		//get the first character (of the secret passage) and return it
		return first;
	}
	public String getCellLabel() {
		//get the first character (of the secret passage) and return it
		return cellLabel;
	}

	@Override
	public int compareTo(BoardCell o) {
		if(row == o.row && column == o.column) {
			return 0;
		}
		return 1;
	}


}
