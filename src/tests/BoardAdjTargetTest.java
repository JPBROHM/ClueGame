package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import experiment.TestBoardCell;

class BoardAdjTargetTest {
	
	
	
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}
	
	
	
	
	@Test
	public void testAdjacenciesRooms()
	{
		// we want to test a couple of different rooms.
		// First, the Murder Room
		Set<BoardCell> testList = board.getAdjList(2, 2);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(4, 6)));
		
		
		// now test the Billiard Room 
		//test that four doors exist, and test one of them
		testList = board.getAdjList(12, 3);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(6, 3)));
		

	}
	@Test
	public void testAdjacentPassageWays()
	{
		//test MK -> KM
		Set<BoardCell> testList = board.getAdjList(5, 0);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(23, 16)));
		
		
	}
	
	@Test
	public void testAdjacencyDoor()
	{
		Set<BoardCell> testList = board.getAdjList(8, 7);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(12, 3)));
		assertTrue(testList.contains(board.getCell(7, 7)));
		assertTrue(testList.contains(board.getCell(9, 7)));
		assertTrue(testList.contains(board.getCell(8, 8)));
	}
	@Test
	public void testRoomCenterAdj() {
		Set<BoardCell> testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
	}
	
	@Test
	public void testAdjRoomNonDoor() {
		Set<BoardCell> testList = board.getAdjList(6, 4);
		assertEquals(3, testList.size());
		assertFalse(testList.contains(board.getCell(7, 4)));
	}
	
	public void testTargetsOccupied() {
		// test a roll of 1 blocked 1 down
		board.getCell(12, 7).setOccupied(true);
		board.calcTargets(board.getCell(11, 7), 1);
		board.getCell(15, 7).setOccupied(false);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(10, 7)));
		assertTrue(targets.contains(board.getCell(11, 8)));
		assertTrue(targets.contains(board.getCell(11, 6)));	
		assertFalse( targets.contains( board.getCell(12, 7))) ;
	
		// we want to make sure we can get into a room, even if flagged as occupied
		board.getCell(21, 2).setOccupied(true);
		board.calcTargets(board.getCell(21, 5), 1);
		board.getCell(21, 2).setOccupied(false);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(21, 2)));	
		
		// check leaving a room with a blocked doorway
		board.getCell(15, 6).setOccupied(true);
		board.calcTargets(board.getCell(12, 3), 2);
		board.getCell(12, 15).setOccupied(false);
		targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(18, 2)));
		assertTrue(targets.contains(board.getCell(8, 8)));	
		assertFalse(targets.contains(board.getCell(15, 7)));

	}
	
	
	
	@Test
	public void testSteps() {
		
		//create Start Cell
		BoardCell startCell = board.getCell(0, 7);
		
		//create 3 different path lengths (die rolls)
		
		board.calcTargets(startCell, 1);
		Set<BoardCell> testList1 = board.getTargets();
		
		board.calcTargets(startCell, 2);
		Set<BoardCell> testList2 = board.getTargets();
		
		board.calcTargets(startCell, 3);
		Set<BoardCell> testList3 = board.getTargets();
		
		board.calcTargets(startCell, 4);
		Set<BoardCell> testList4 = board.getTargets();
		
		board.calcTargets(startCell, 5);
		Set<BoardCell> testList5 = board.getTargets();
		
		board.calcTargets(startCell, 6);
		Set<BoardCell> testList6 = board.getTargets();
		//die roll of one
		Assert.assertTrue(testList1.contains(board.getCell(1,7)));
		Assert.assertEquals(1,testList1.size());
		//die roll of two
		Assert.assertTrue(testList2.contains(board.getCell(1,8)));
		Assert.assertTrue(testList2.contains(board.getCell(2,7)));
		Assert.assertEquals(2,testList2.size());
		//die roll of three
		Assert.assertTrue(testList3.contains(board.getCell(3,7)));
		Assert.assertTrue(testList3.contains(board.getCell(2,8)));
		Assert.assertEquals(2,testList3.size());
		//die roll of four
		Assert.assertTrue(testList4.contains(board.getCell(4,7)));
		Assert.assertTrue(testList4.contains(board.getCell(3,8)));
		Assert.assertTrue(testList4.contains(board.getCell(1,8)));
		Assert.assertTrue(testList4.contains(board.getCell(2,7)));
		Assert.assertEquals(4,testList4.size());
		//die roll of five
		Assert.assertTrue(testList5.contains(board.getCell(5,7)));
		Assert.assertTrue(testList5.contains(board.getCell(4,8)));
		Assert.assertTrue(testList5.contains(board.getCell(3,7)));
		Assert.assertTrue(testList5.contains(board.getCell(4,6)));
		Assert.assertTrue(testList5.contains(board.getCell(2,8)));
		Assert.assertEquals(5,testList5.size());
		//die roll of 6
		Assert.assertTrue(testList6.contains(board.getCell(6,7)));
		Assert.assertTrue(testList6.contains(board.getCell(5,6)));
		Assert.assertTrue(testList6.contains(board.getCell(5,8)));
		Assert.assertTrue(testList6.contains(board.getCell(2,2)));
		Assert.assertTrue(testList6.contains(board.getCell(4,11)));
		Assert.assertTrue(testList6.contains(board.getCell(4,7)));
		Assert.assertTrue(testList6.contains(board.getCell(3,8)));
		Assert.assertTrue(testList6.contains(board.getCell(1,8)));
		Assert.assertTrue(testList6.contains(board.getCell(4,5)));
		Assert.assertEquals(9,testList6.size());
		
	}

}
