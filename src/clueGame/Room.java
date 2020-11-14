package clueGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

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

	public void drawRoom(Graphics g, int width, int height) {
		if( labelCell == null) return ;
		g.setColor(Color.BLUE);
		Font font = new Font("font", Font.BOLD, 13);
		g.setFont(font);
		g.drawString(name, labelCell.getColumn() * width, labelCell.getRow() * height);

	}

}
