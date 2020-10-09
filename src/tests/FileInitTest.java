package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;
import clueGame.Room;

public class FileInitTest {

	public static final int numRows = 25;
	public static final int numCols = 24;
	private static Board board;
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
	}
	
	@Test
	public void testNumRooms() {
		//verify rooms ==7
		int numRooms =0;
		for (int row = 0; row < board.getNumRows(); row++)
			for (int col = 0; col < board.getNumColumns(); col++) {
				BoardCell cell = board.getCell(row, col);
				if (cell.isRoomCenter())
					numRooms++;
			}
		Assert.assertEquals(7, numRooms);
	}
	
	
	@Test
	public void testBoardSize() {
		//24 COLS & 25 ROWS
		assertEquals(numRows, board.getNumRows());
		assertEquals(numCols, board.getNumColumns());	
	}
	
	
			
@Test
public void testDoorTypes() {
		//check one of each doorway
		//check that non-doorways return false isDoorway
	BoardCell cell = board.getCell(8, 7);
	assertTrue(cell.isDoorway());
	assertEquals(DoorDirection.LEFT, cell.getDoorDirection());
	cell = board.getCell(6, 4);
	assertTrue(cell.isDoorway());
	assertEquals(DoorDirection.UP, cell.getDoorDirection());
	cell = board.getCell(4, 8);
	assertTrue(cell.isDoorway());
	assertEquals(DoorDirection.RIGHT, cell.getDoorDirection());
	cell = board.getCell(3, 6);
	assertTrue(cell.isDoorway());
	assertEquals(DoorDirection.DOWN, cell.getDoorDirection());
	// Test that walkways && rooms are not doors
	cell = board.getCell(0, 0);
	assertFalse(cell.isDoorway());
	cell = board.getCell(6, 10);
	assertFalse(cell.isDoorway());
	
	
	
}

@Test
public void testNumDoorways() {
	//check doors==18
	int numDoors = 0;
	for (int row = 0; row < board.getNumRows(); row++)
		for (int col = 0; col < board.getNumColumns(); col++) {
			BoardCell cell = board.getCell(row, col);
			if (cell.isDoorway())
				numDoors++;
		}
	Assert.assertEquals(18, numDoors);
}
		

		// verify several entries?
		@Test
		public void testRooms() {
			//check rooms have proper center cell and label
			assertTrue(board.roomExists('C'));
			assertTrue(board.roomExists('K'));
			assertTrue(board.roomExists('B'));
			assertTrue(board.roomExists('R'));
			assertTrue(board.roomExists('M'));
			assertTrue(board.roomExists('D'));
			assertTrue(board.roomExists('H'));
			assertEquals("Comfort Room", board.getRoom('C').getName() );
			assertEquals("Kitchen", board.getRoom('K').getName() );
			assertEquals("Bathroom", board.getRoom('B').getName() );
			assertEquals("Billiard Room", board.getRoom('R').getName() );
			assertEquals("Murder Room", board.getRoom('M').getName() );
			assertEquals("Dining Room", board.getRoom('D').getName() );
			assertEquals("Hall", board.getRoom('H').getName() );
		
		
		
		
		
			// this is a secret passage test --> we tweaked how these work, theyre not in rooms, but rather walkways near-ish rooms-->will have to do more tweaking in next step
			BoardCell cell;
			Room room;
			cell = board.getCell(0, 5);
			assertEquals(cell.getFirst(), 'M');
			assertTrue( cell.getSecretPassage() == 'K' );
		
			//test walkway
			cell = board.getCell(2, 5);
			room = board.getRoom( cell ) ;
			// Note for our purposes, walkways and closets are rooms
			assertTrue( room != null );
			assertEquals( room.getName(), "Walkway" ) ;
			assertFalse( cell.isRoomCenter() );
			assertFalse( cell.isLabel() );
			
			//test room center
			cell = board.getCell(2, 2);
			room = board.getRoom( cell ) ;
			assertTrue( room != null );
			assertEquals( room.getName(), "Murder Room" ) ;
			assertTrue( cell.isRoomCenter() );
			assertTrue( room.getCenterCell() == cell );
			//test not center
			cell = board.getCell(3, 3);
			room = board.getRoom(cell);
			assertEquals( room.getName(), "Murder Room" ) ;
			assertFalse( cell.isRoomCenter() );
		
		
		}
		

		

	}


