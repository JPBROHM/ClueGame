package clueGame;

public class Room {
	
	private String name;
	private BoardCell labelCell;
	private BoardCell centerCell;
	
	public Room(String name) {
		this.name = name;
	}

	public Room(String string, BoardCell labelCell, BoardCell centerCell) {
		name = string;
		this.labelCell = labelCell;
		this.centerCell = centerCell;
	}

	public void setLabel(BoardCell cell) {
		labelCell = cell;
	}
	public void setCenter(BoardCell cell) {
		centerCell = cell;
	}
	public String getName() {
		return name;
	}

	public BoardCell getLabelCell() {
		// TODO Auto-generated method stub
		return labelCell;
	}

	public BoardCell getCenterCell() {
		// TODO Auto-generated method stub
		return centerCell;
	}

}
