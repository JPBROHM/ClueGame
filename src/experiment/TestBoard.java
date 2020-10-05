/*
 * Authors: Joshua Brohm and Sam Pearson
 * C12A-2 Clue Paths
 */


package experiment;

import java.util.HashSet;
import java.util.Set;


public class TestBoard {
	
	
	private Set<TestBoardCell> legalTargets;

	public TestBoard() {
		
	}
	

	public void calcTargets(TestBoardCell startCell, int pathlength) {
		legalTargets = new HashSet<TestBoardCell>();
	}

	public Set<TestBoardCell> getTargets(){
		return legalTargets;
	}


	public TestBoardCell getCell(int row, int col) {
		return new TestBoardCell(-1,-1);
	}




}
