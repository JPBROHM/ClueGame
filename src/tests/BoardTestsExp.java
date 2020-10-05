/*
 * Authors: Joshua Brohm and Sam Pearson
 * C12A-2 Clue Paths
 */


package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.Assert;
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
	public void testAdjacency() {
		/* TEST FOR TOP RIGHT ADJACENCY LIST */
		//test to make sure that the adjacency list is the correct size, and contains the correct cells
		TestBoardCell cell = board.getCell(0,0);
		Set<TestBoardCell> testList = cell.getAdjList();
		//fail if the adjacency list does not contain the adjacent cells for (0,0) or is the wrong size
		Assert.assertTrue(testList.contains(board.getCell(1,0)));
		Assert.assertTrue(testList.contains(board.getCell(1,0)));
		Assert.assertEquals(2, testList.size());
		
		/* TEST FOR BOTTOM RIGHT ADJACENCY LIST */
		//test to make sure that the adjacency list is the correct size, and contains the correct cells
		cell = board.getCell(3,3);
		testList = cell.getAdjList();
		//fail if the adjacency list does not contain the adjacent cells for (0,0) or is the wrong size
		Assert.assertTrue(testList.contains(board.getCell(3,2)));
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertEquals(2, testList.size());
		
		/* TEST FOR A RIGHT EDGE ADJACENCY LIST */
		//test to make sure that the adjacency list is the correct size, and contains the correct cells
		cell = board.getCell(1,3);
		testList = cell.getAdjList();
		//fail if the adjacency list does not contain the adjacent cells for (0,0) or is the wrong size
		Assert.assertTrue(testList.contains(board.getCell(0,3)));
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertTrue(testList.contains(board.getCell(1,2)));
		Assert.assertEquals(3, testList.size());
		
		/* TEST FOR LEFT EDGE ADJACENCY LIST */
		//test to make sure that the adjacency list is the correct size, and contains the correct cells
		cell = board.getCell(2,0);
		testList = cell.getAdjList();
		//fail if the adjacency list does not contain the adjacent cells for (0,0) or is the wrong size
		Assert.assertTrue(testList.contains(board.getCell(1,0)));
		Assert.assertTrue(testList.contains(board.getCell(3,0)));
		Assert.assertTrue(testList.contains(board.getCell(2,1)));
		Assert.assertEquals(3, testList.size());
		
		/* TEST FOR MIDDLE ADJACENCY LIST */
		//test to make sure that the adjacency list is the correct size, and contains the correct cells
		cell = board.getCell(2,2);
		testList = cell.getAdjList();
		//fail if the adjacency list does not contain the adjacent cells for (0,0) or is the wrong size
		Assert.assertTrue(testList.contains(board.getCell(1,2)));
		Assert.assertTrue(testList.contains(board.getCell(3,2)));
		Assert.assertTrue(testList.contains(board.getCell(2,1)));
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertEquals(4, testList.size());
		
	
	}
	
	
	
	@Test
	public void testSteps() {
		
		//create Start Cell
		TestBoardCell startCell = board.getCell(0, 0);
		
		//create 3 different path lengths (die rolls)
		
		board.calcTargets(startCell, 1);
		Set<TestBoardCell> testList1 = board.getTargets();
		
		board.calcTargets(startCell, 2);
		Set<TestBoardCell> testList2 = board.getTargets();
		
		board.calcTargets(startCell, 3);
		Set<TestBoardCell> testList3 = board.getTargets();
		//die roll of one
		Assert.assertTrue(testList1.contains(board.getCell(1,0)));
		Assert.assertTrue(testList1.contains(board.getCell(0,1)));
		Assert.assertEquals(2,testList1.size());
		//die roll of two
		Assert.assertTrue(testList2.contains(board.getCell(2,0)));
		Assert.assertTrue(testList2.contains(board.getCell(0,2)));
		Assert.assertTrue(testList2.contains(board.getCell(1,1)));
		Assert.assertEquals(3,testList2.size());
		//die roll of three
		Assert.assertTrue(testList3.contains(board.getCell(3,0)));
		Assert.assertTrue(testList3.contains(board.getCell(0,3)));
		Assert.assertTrue(testList3.contains(board.getCell(2,1)));
		Assert.assertTrue(testList3.contains(board.getCell(1,2)));
		Assert.assertTrue(testList3.contains(board.getCell(1,0)));
		Assert.assertTrue(testList3.contains(board.getCell(0,1)));
		Assert.assertEquals(6,testList3.size());
		
	}
	
	
	
	@Test
	public void testStartLoc() {
		
		//create Start Cell 1
		TestBoardCell startCell1 = board.getCell(0, 0);
		board.calcTargets(startCell1, 2);
		Set<TestBoardCell> testList = board.getTargets();
		//pathLength from 0,0?
		Assert.assertTrue(testList.contains(board.getCell(2,0)));
		Assert.assertTrue(testList.contains(board.getCell(1,1)));
		Assert.assertTrue(testList.contains(board.getCell(0,2)));
		
		
		
		//create Start Cell 2
		TestBoardCell startCell2 = board.getCell(1, 1);
		board.calcTargets(startCell2, 2);
		Set<TestBoardCell> testList2 = board.getTargets();
		//pathLength from 1,1?
		Assert.assertTrue(testList2.contains(board.getCell(2,2)));
		Assert.assertTrue(testList2.contains(board.getCell(0,0)));
		Assert.assertTrue(testList2.contains(board.getCell(3,1)));
		Assert.assertTrue(testList2.contains(board.getCell(1,3)));
		Assert.assertTrue(testList2.contains(board.getCell(2,0)));
		Assert.assertTrue(testList2.contains(board.getCell(0,2)));
	}
	
	
	
	@Test
	public void testTargetsNormal() {
		
		//tested that all cells are viable when there are no occupied spaces
	TestBoardCell startCell = board.getCell(0, 0);
	board.calcTargets(startCell, 3);
	Set<TestBoardCell> testList = board.getTargets();
	Assert.assertTrue(testList.contains(board.getCell(3,0)));
	Assert.assertTrue(testList.contains(board.getCell(0,3)));
	Assert.assertTrue(testList.contains(board.getCell(1,2)));
	Assert.assertTrue(testList.contains(board.getCell(2,1)));
	Assert.assertTrue(testList.contains(board.getCell(1,0)));
	Assert.assertTrue(testList.contains(board.getCell(0,1)));
	Assert.assertEquals(6, testList.size());}
	
	
	
	
	@Test
	public void testTargetsMixed() {
		//test targets with other spaces besides open walkways
		board.getCell(0, 2).setOccupied(true);
		board.getCell(1, 2).setRoom(true);
		//create adjacency list
		TestBoardCell cell = board.getCell(0, 3);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		//run tests
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1,2)));
		Assert.assertTrue(targets.contains(board.getCell(2,2)));
		Assert.assertTrue(targets.contains(board.getCell(3,3)));
		
	}
	
	@Test
	public void testTargetsDie6() {
		
	
		TestBoardCell startCell = board.getCell(0, 0);

	board.calcTargets(startCell, 6);
	Set<TestBoardCell> testList = board.getTargets();
	Assert.assertTrue(testList.contains(board.getCell(3,3)));
	
	//shouldn't be possible to get to these squares with a die roll of 6 (would req. a 5 or a 7 die roll)
	Assert.assertFalse(testList.contains(board.getCell(3,2)));
	Assert.assertFalse(testList.contains(board.getCell(2,3)));
	Assert.assertFalse(testList.contains(board.getCell(3,0)));
	Assert.assertFalse(testList.contains(board.getCell(2,1)));
	Assert.assertFalse(testList.contains(board.getCell(1,2)));
	Assert.assertFalse(testList.contains(board.getCell(0,3)));
	Assert.assertFalse(testList.contains(board.getCell(1,0)));
	Assert.assertFalse(testList.contains(board.getCell(0,1)));
	Assert.assertEquals(8, testList.size());
		
}

}
