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
    	
    	GameControlPanel panel = new GameControlPanel();  // create the panel
    	panel.setLayout(new GridLayout(2, 0));
    	JFrame frame = new JFrame();  // create the frame
    	frame.setContentPane(panel); // put the panel in the frame
    	frame.setSize(750, 180);  // size the frame
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close

    	// test filling in the data
    	Color color = new Color(0,0,0);
    	color = Color.YELLOW;
    	panel.setTurn(new ComputerPlayer( "Col. Mustard", color, 0, 0), 5);
    	panel.setGuess( "I have no guess!");
    	panel.setGuessResult( "So you have nothing?");



    	//creating the panels and grids before adding them to eachother,
    	//they follow the same layout as the example layout from the assignment
    	//each one is named by that panels corresponding place in the frame
        GameControlPanel top = new GameControlPanel();
        top.setLayout(new GridLayout(1, 4));
        
        
        
        GameControlPanel topLeft = new GameControlPanel();
        topLeft.setLayout(new GridLayout(2, 0));
        

        
        
        GameControlPanel topMiddleLeft = new GameControlPanel();
        topMiddleLeft.setLayout(new GridLayout(0, 2));
       
        
        
        
        
        
               
        GameControlPanel bottom = new GameControlPanel();
        bottom.setLayout(new GridLayout(0, 2));
        
        
        
        //some good ole hard coded button/label text fields
        GameControlPanel bottomLeft = new GameControlPanel();
        bottomLeft.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
        bottom.setLayout(new GridLayout(1, 0));
        
        
        GameControlPanel bottomRight = new GameControlPanel();
        bottomRight.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
        bottom.setLayout(new GridLayout(1, 0));
        
        //the extra spaces are to get the roll closer to the text field for it
        //, makes it prettier
        //A1 coding practice ^^
    	JLabel roll = new JLabel("               Roll:");
        JLabel whosTurn = new JLabel("Who's turn?");
        JButton accuseButton = new JButton("Make Accusation");
        JButton nextButton = new JButton("NEXT!");

        
        
        //adding the stuff to the display, starting from the innermost fields and working its way out
        bottomRight.add(panel.getTheGuessResult());
        bottomLeft.add(panel.getTheGuess());

        bottom.add(bottomLeft);
        bottom.add(bottomRight);

        topLeft.add(whosTurn);
        topLeft.add(panel.playerName);

        topMiddleLeft.add(roll);
        topMiddleLeft.add(panel.getRollNumber());
        
        top.add(topLeft);
        top.add(topMiddleLeft);
        top.add(accuseButton);
        top.add(nextButton);
        
        
        //add the displays to the panel
        panel.add(top);
        panel.add(bottom);
        
        frame.setVisible(true);
        




 }

	

	

}
