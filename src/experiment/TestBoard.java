package experiment;

import java.util.Set;


public class TestBoard {
	
	
	private Set<TestBoardCell> legalTargets;

	public TestBoard() {

	}
	

	public void calcTargets(TestBoardCell startCell, int pathlength) {

	}

	public Set<TestBoardCell> getTargets(){

		return legalTargets;
	}


	public TestBoardCell getCell(int row, int col) {
		return new TestBoardCell(-1,-1);
	}




}
