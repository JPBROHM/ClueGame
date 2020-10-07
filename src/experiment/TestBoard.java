/*
 * Authors: Joshua Brohm and Sam Pearson
 * C12A-2 Clue Paths
 */


package experiment;

import java.util.HashSet;
import java.util.Set;


public class TestBoard {
	final static int COLS=4;
	final static int ROWS =4;
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> visited;
	private Set<TestBoardCell> legalTargets;

	public TestBoard() {
		grid = new TestBoardCell[COLS][ROWS];
		for (int i=0; i<COLS; i++) {
			for (int j=0; j<ROWS; j++) {
				grid[i][j] = new TestBoardCell(i,j);
			}
		}
	}


	public TestBoardCell[][] getGrid() {
		return grid;
	}
	
	
	
	//method to actual changing the value of a cell, instead of making a copy then changing the value of the copy
	public void occupyCell(TestBoardCell cell, boolean occupy) {
		grid[cell.getColumn()][cell.getRow()].setOccupied(occupy);
	}
	
	
 

	public void calcTargets(TestBoardCell startCell, int pathLength) {
		legalTargets = new HashSet<TestBoardCell>();
		visited = new HashSet<TestBoardCell>();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}

	//find all the targets, but if an adjCell is occupied do not visit it
	public void findAllTargets(TestBoardCell cell, int numSteps) {
		for (TestBoardCell adjCell : cell.getAdjList(grid)) {
			if ((!visited.contains(adjCell)) && (!adjCell.getOccupied())) {
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

	public Set<TestBoardCell> getTargets(){
		return legalTargets;
	}


	public TestBoardCell getCell(int col, int row) {
		return grid[col][row];
	}




}
