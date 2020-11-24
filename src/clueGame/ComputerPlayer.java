package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;

public class ComputerPlayer extends Suspect {
	private String target;
	
	

	@Override
	public void setTarget(ArrayList<Card> roomCards) {
		if (roomsSeen.isEmpty()) {
			return;
		}
		if (board.getRoom(board.getCell(row, col)).getName().equals(target)) {
			ArrayList<String> possibleTargets = new ArrayList<>();
			boolean wasRoomSeen = false;
			//add unseen rooms to list of possible targets
			for (int i=0; i< roomCards.size();i++) {
				for (String room : roomsSeen) {
					if (roomCards.get(i).getName().equals(room)) {wasRoomSeen=true;}
				}
				if(!wasRoomSeen) {possibleTargets.add(roomCards.get(i).getName());}
				else {wasRoomSeen=false;}
			}

			//randomly select one card from the array list
			if (possibleTargets.size()==1) {
				this.target = possibleTargets.get(0);
				}
			else {
				Random r = new Random();
				int randoGen = (r.nextInt(possibleTargets.size()));
				this.target = possibleTargets.get(randoGen);}
		}
		
	}

	public ComputerPlayer(String name, Color color, int row, int col, int offset) {
		super(name, color, row, col, offset);
		target = "Kitchen";
		
	}

	@Override
	public void updateHand(Card card) {
		playerHand.add(card);
		if (card.getType() == CardType.ROOM) {
			roomsSeen.add(card.getName());
		}
		
	}


	
	
	
	//used for testing purposes to set things to exactly what we want to test for specific scenarios
	public Set<String> getRoomCards() {
		return roomsSeen;
	}
	public Set<String> getWeaponCards() {
		return weaponsSeen;
	}
	public Set<String> getPeopleCards() {
		return peopleSeen;
	}
	@Override
	public String getTarget() {
		return target;
		
	}
	
	public void HARDSETTEST(String target) {
		this.target = target;
	}


	

}
