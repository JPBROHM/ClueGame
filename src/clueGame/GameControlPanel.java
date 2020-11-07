package clueGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameControlPanel extends JPanel{

	private JTextField theGuess;
	private JTextField theGuessResult;
	private JTextField rollNumber;
	private JTextField playerName;
    
	
	public GameControlPanel() {
		theGuess = new JTextField();
		theGuessResult = new JTextField();
		rollNumber = new JTextField();
		playerName = new JTextField(15);
	}

	public JTextField getTheGuess() {
		return theGuess;
	}

	public JTextField getTheGuessResult() {
		return theGuessResult;
	}

	public JTextField getRollNumber() {
		return rollNumber;
	}

	public JTextField getPlayerName() {
		return playerName;
	}

	public void setGuess(String guess) {
	    theGuess.setText(guess);
	}

	private void setGuessResult(String string) {
		theGuessResult.setText(string);
	}
	
	
	private void setTurn(ComputerPlayer computerPlayer, int roll) {
		rollNumber.setText(Integer.toString(roll));
		playerName.setText(computerPlayer.getName());

		
	}

    public static void main(String[] args) {
    	
    	
 }

	

	

}
