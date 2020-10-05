package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experiment.TestBoard;
import experiment.TestBoardCell;

class BoardTestsExp {


	TestBoard board;
	
	@BeforeEach // Run before each test
	public void setUp() {
		board = new TestBoard();
	}

	@Test
	public void testAdjacent() {
		TestBoardCell cell = board.getCell(0,0);
	}
	
	
	
	@Test
	public void testSteps() {
		
	}
	
	
	
	@Test
	public void testStartLoc() {
		TestBoardCell StartCell1 = board.getCell(0, 0);
		TestBoardCell StartCell2 = board.getCell(1, 1);
	}
	
	
	
	@Test
	public void testTargetsNormal() {}
	
	
	
	@Test
	public void testTargetsMixed() {}
	

}
