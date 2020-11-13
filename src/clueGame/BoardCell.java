package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import experiment.TestBoard;
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
	private boolean secretPassageBool;
	private boolean isRoom;
	private boolean isOccupied;
	private boolean isWalkway;
	private int width;
	private int height;


	public BoardCell(){
		// TODO Auto-generated constructor stub
	}
	
	public boolean isRoom() {
		return isRoom;
	}


	public BoardCell(int row, int column, String cellLabel) {
		this.cellLabel = cellLabel;
		adjList = new HashSet<BoardCell>();
		this.row = row;
		this.column = column;
		//if cellLabel from csv file is 1 character, we know its not a room center, label, doorway, or secret passage
		if(cellLabel.length() == 1) {
			if(!cellLabel.equals("W") && !cellLabel.equals("X")) {
				isRoom = true;
			} else {
				isRoom = false;
			}
			doorway = false;
			roomCenter = false;
			label = false;
			secretPassageBool=false;
			//if cellLabel is 2 characters long determine if it is a label, center, secret passage, or doorway, and set variables accordingly

			if(cellLabel.equals("W")) {isWalkway=true;}
			else {isWalkway=false;}


		} else if(cellLabel.length() == 2) {
			if (cellLabel.charAt(1) == '^' || cellLabel.charAt(1) == 'v' || cellLabel.charAt(1) == '<' || cellLabel.charAt(1) == '>') {
				isWalkway = true;
				doorway = true;
				isRoom = false;
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
				isRoom = true;
				isWalkway = false;
			}
			else if(cellLabel.charAt(1) == '#') {
				label=true;
				isRoom = true;
				isWalkway = false;
				}
			else {
				first=cellLabel.charAt(0);
				secretPassage=cellLabel.charAt(1);
				secretPassageBool=true;
				isRoom = true;
				isWalkway = false;
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
	public boolean isWalkway() {
		return isWalkway;
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
	public boolean isSecretPassage() {
		// return the second character
		return secretPassageBool;
	}
	public char getSecretPassageStart() {
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

	public void setOccupied(boolean b) {
		isOccupied = b;
		
	}

	public Set<BoardCell> getAdjList() {
		return adjList;
	}
	

	public void addToAdjList(BoardCell cell) {	
		this.adjList.add(cell);
	}

	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}

	public int getColumn() {
		// TODO Auto-generated method stub
		return column;
	}

	public boolean getOccupied() {
		// TODO Auto-generated method stub
		return isOccupied;
	}

	public void draw(Graphics g, int row, int col, int width, int height) {
		
		 if(secretPassageBool) {
			 
			 if(getSecretPassageStart()=='W'||getSecretPassage()=='W') {
				 g.setColor(Color.RED);
				 g.fillRect(col * width,row * height, width, height);
				 g.setColor(Color.BLACK);
				 g.drawRect(col * width,row * height, width, height);
			 }
			 else {
				 g.setColor(Color.GREEN);
				 g.fillRect(col * width,row * height, width, height);
				 g.setColor(Color.BLACK);
				 g.drawRect(col * width,row * height, width, height);
			 }
			 //color (make other end same color)
		 }
		 else if (isRoom) {
			 g.setColor(Color.LIGHT_GRAY);
			 g.fillRect(col * width,row * height, width, height);
			 //gray
		}
		else  if (isWalkway) {
			g.setColor(new Color(93,75,59));
			g.fillRect(col * width,row * height, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(col * width,row * height, width, height);
		//light brown
		}
		else if (cellLabel.equals("X")) {
			g.setColor(Color.BLACK);
			g.fillRect(col * width,row * height, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(col * width,row * height, width, height);
		//black
		}
		if(doorway) {
			g.setColor(new Color(93,75,59));
			g.fillRect(col * width,row * height, width, height);
			g.setColor(Color.BLUE);
		
		//color
		//line on side of door direction
			DoorDirection dDir = doorDirection;
			switch (dDir) {
			//each switch statement is the same, just the direction differs
			case UP:
				g.fillRect(col * width,row * height, width, 3);
				break;
			case DOWN:
				g.fillRect(col * width,(row * height) + (height - 3), width, 3);
				break;
			case LEFT:
				g.fillRect(col * width,row * height, 3, height);
				break;
			case RIGHT:
				g.fillRect((col * width) + (width - 3),row * height, 3, height);
				break;
			default:
				break;
			
			}
			g.setColor(Color.BLACK);
			g.drawRect(col * width,row * height, width, height);
		}
		
		
		
		
	}


}
