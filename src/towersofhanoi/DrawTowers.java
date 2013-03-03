/**
 * @author Mike Kucharski
 * Fall 2012 | COMP 285 Object Oriented Programming
 */

package towersofhanoi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class DrawTowers extends JFrame implements ActionListener {

	// create an instance of gamestate here so its not static
	GameState gameState;

	int diskNumber, movesCount, solutionIndex, fewestMoves;
	ArrayList<String> solutions;

	// all paint components necessary
	JPanel pnlMain;
	JLabel lblDisks, lblTitle, lblWinner, lblTryAgain, lblMoves, lblMovesCount;
	JButton btnReset, btnPlus, btnMinus, btnStart, btnNewDisks, btnSolve,
			btnRules;
	JButton[] btnMove = new JButton[3];
	MyPanel myPanel;

	public DrawTowers() {
		gameState = null;
		initUI();
	}

	// initialize all components in the right location
	private void initUI() {
		myPanel = new MyPanel();
		getContentPane().add(myPanel);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		btnReset.setEnabled(false);
		btnReset.setBounds(480, 11, 89, 23);
		myPanel.add(btnReset);

		btnMove[2] = new JButton("Move 3");
		btnMove[2].addActionListener(this);
		btnMove[2].setEnabled(false);
		btnMove[2].setBounds(440, 288, 115, 23);
		myPanel.add(btnMove[2]);

		btnMove[1] = new JButton("Move 2");
		btnMove[1].addActionListener(this);
		btnMove[1].setEnabled(false);
		btnMove[1].setBounds(240, 288, 115, 23);
		myPanel.add(btnMove[1]);

		btnMove[0] = new JButton("Move 1");
		btnMove[0].addActionListener(this);
		btnMove[0].setEnabled(false);
		btnMove[0].setBounds(40, 288, 115, 23);
		myPanel.add(btnMove[0]);

		lblDisks = new JLabel("Disks: 1");
		lblDisks.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDisks.setBounds(28, 364, 60, 14);
		myPanel.add(lblDisks);

		btnPlus = new JButton("+");
		btnPlus.addActionListener(this);
		btnPlus.setBounds(98, 350, 41, 20);
		myPanel.add(btnPlus);

		btnMinus = new JButton("-");
		btnMinus.addActionListener(this);
		btnMinus.setBounds(98, 375, 41, 20);
		myPanel.add(btnMinus);

		lblTitle = new JLabel("Towers of Hanoi");
		lblTitle.setForeground(new Color(255, 255, 153));
		lblTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lblTitle.setBounds(10, 11, 240, 31);
		myPanel.add(lblTitle);

		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		btnStart.setBounds(381, 11, 89, 23);
		myPanel.add(btnStart);

		lblWinner = new JLabel(
				"You solved it in n moves! The quickest solution is n moves.");
		lblWinner.setForeground(new Color(153, 0, 102));
		lblWinner.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblWinner.setVisible(false);
		lblWinner.setBounds(87, 45, 497, 23);
		myPanel.add(lblWinner);

		btnNewDisks = new JButton("New Disks");
		btnNewDisks.addActionListener(this);
		btnNewDisks.setEnabled(false);
		btnNewDisks.setBounds(260, 11, 108, 23);
		myPanel.add(btnNewDisks);

		lblTryAgain = new JLabel("Try again for fewer moves!");
		lblTryAgain.setForeground(new Color(153, 0, 102));
		lblTryAgain.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblTryAgain.setBounds(202, 63, 211, 23);
		lblTryAgain.setVisible(false);
		myPanel.add(lblTryAgain);

		lblMoves = new JLabel("Moves: ");
		lblMoves.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMoves.setBounds(175, 353, 65, 23);
		myPanel.add(lblMoves);

		movesCount = 0;
		fewestMoves = 0;
		lblMovesCount = new JLabel("" + movesCount);
		lblMovesCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMovesCount.setBounds(232, 353, 29, 23);
		myPanel.add(lblMovesCount);

		btnSolve = new JButton("Show me how to solve");
		btnSolve.addActionListener(this);
		btnSolve.setBounds(342, 329, 175, 37);
		btnSolve.setEnabled(false);
		myPanel.add(btnSolve);

		btnRules = new JButton("How To Play");
		btnRules.addActionListener(this);
		btnRules.setBounds(342, 375, 175, 37);
		myPanel.add(btnRules);

		setTitle("Towers of Hanoi");
		setSize(600, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// every time an action is performed (buttons in this case)
	// get the button text, update gui if needed, call into the gamestate
	// to update changes there
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton temp = (JButton) e.getSource();
		String buttonText = temp.getText();

		gameState.buttonClick(buttonText);
		diskNumber = getNumberOfDisks();
		if (buttonText == "+") {
			if (diskNumber < 6)
				diskNumber++;
			lblDisks.setText("Disks: " + diskNumber);
		} else if (buttonText == "-") {
			if (diskNumber > 1)
				diskNumber--;
			lblDisks.setText("Disks: " + diskNumber);
		} else if (buttonText == "Start") {
			movesCount = 0;
			lblMovesCount.setText("0");
			btnSolve.setEnabled(true);
			btnSolve.setText("Show me how to solve");
			btnNewDisks.setEnabled(true);
			btnStart.setEnabled(false);
			btnPlus.setEnabled(false);
			btnMinus.setEnabled(false);
			btnMove[0].setEnabled(true);
			btnMove[1].setEnabled(true);
			btnMove[2].setEnabled(true);
			btnReset.setEnabled(true);

			// set up button texts
			addMovesToButtons(gameState.possibleMoves);
			// draw poles
			myPanel.setPole(1, gameState.pole1);
			myPanel.setPole(2, gameState.pole2);
			myPanel.setPole(3, gameState.pole3);
			myPanel.repaint();
		} else if (buttonText.contains("Move")) {
			movesCount++;
			updateMovesCount();

			// set the solutions button to next move
			if (buttonText.contains("pole")) {
				solutionIndex++;
				if (solutionIndex < solutions.size())
					btnSolve.setText(solutions.get(solutionIndex));
			} else {
				// set up move button texts to next moves
				addMovesToButtons(gameState.possibleMoves);
			}

			// draw poles
			myPanel.setPole(1, gameState.pole1);
			myPanel.setPole(2, gameState.pole2);
			myPanel.setPole(3, gameState.pole3);
			myPanel.repaint();

			// if the game is over
			if (gameState.solvedCheck()) {
				// disable move buttons
				resetAllMoveButtons(false);

				setlblWinner();
				lblWinner.setVisible(true);
				if (movesCount != fewestMoves)
					lblTryAgain.setVisible(true);
				btnSolve.setEnabled(false);
				btnSolve.setText("Show me how to solve");
				movesCount = 0;
				lblMovesCount.setText("0");
			}
		} else if (buttonText == "Reset") {
			movesCount = 0;
			lblMovesCount.setText("0");
			btnSolve.setText("Show me how to solve");
			btnSolve.setEnabled(true);
			lblWinner.setVisible(false);
			lblTryAgain.setVisible(false);

			// set up button texts
			addMovesToButtons(gameState.possibleMoves);
			// draw poles
			myPanel.setPole(1, gameState.pole1);
			myPanel.setPole(2, gameState.pole2);
			myPanel.setPole(3, gameState.pole3);
			myPanel.repaint();
		} else if (buttonText == "New Disks") {
			lblWinner.setVisible(false);
			lblTryAgain.setVisible(false);
			btnSolve.setText("Show me how to solve");
			btnSolve.setEnabled(false);
			btnStart.setEnabled(true);
			btnReset.setEnabled(false);
			resetAllMoveButtons(false);
			btnPlus.setEnabled(true);
			btnMinus.setEnabled(true);

			// draw poles
			myPanel.setPole(1, gameState.pole1);
			myPanel.setPole(2, gameState.pole2);
			myPanel.setPole(3, gameState.pole3);
			myPanel.repaint();
		} else if (buttonText == "Show me how to solve") {
			// reset
			movesCount = 0;
			lblMovesCount.setText("0");

			myPanel.setPole(1, gameState.pole1);
			myPanel.setPole(2, gameState.pole2);
			myPanel.setPole(3, gameState.pole3);
			myPanel.repaint();

			solutionIndex = 0;
			// disable any unused buttons
			setAllMoveButtons(false, "-");

			// get all necessary moves in an array
			solutions = gameState.getSolutions();
			btnSolve.setText(solutions.get(solutionIndex));
		} else if (buttonText == "How To Play") {
			HowToPlay htp = new HowToPlay();
			htp.setVisible(true);
		}

	}

	public void resetAllMoveButtons(boolean state) {
		for (int i = 0; i < btnMove.length; i++) {
			btnMove[i].setText("Move " + (i + 1));
			btnMove[i].setEnabled(state);
		}
	}

	public void setAllMoveButtons(boolean state, String text) {
		for (int i = 0; i < btnMove.length; i++) {
			btnMove[i].setText(text);
			btnMove[i].setEnabled(state);
		}
	}

	public void updateMovesCount() {
		lblMovesCount.setText("" + movesCount);
	}

	public void setlblWinner() {
		fewestMoves = (int) (Math.pow(2, diskNumber) - 1);
		lblWinner
				.setText("You solved it in " + lblMovesCount.getText()
						+ " moves! The quickest solution is " + fewestMoves
						+ " moves.");
	}

	// adds the possible moves from the gamestate to the buttons
	public void addMovesToButtons(ArrayList<String> moves) {
		// reset buttons before assigning values
		resetAllMoveButtons(true);

		if (moves.isEmpty() || moves.size() > 3) {
			// this case should NEVER be true
			System.out
					.println("There is either no possible moves or too many moves, not enough buttons.");
		} else if (moves.size() == 3) {
			for (int i = 0; i < moves.size(); i++)
				btnMove[i].setText(moves.get(i));
		} else {
			// set as many buttons you can
			for (int i = 0; i < moves.size(); i++)
				btnMove[i].setText(moves.get(i));

			// disable any unused buttons
			for (int i = moves.size(); i < 3; i++) {
				btnMove[i].setText("-");
				btnMove[i].setEnabled(false);
			}
		}
	}

	// returns number of disks in the current game
	public int getNumberOfDisks() {
		char diskNumChar = lblDisks.getText().charAt(
				lblDisks.getText().length() - 1);
		return Character.getNumericValue(diskNumChar);
	}

	// initialize gamestate so there is a refernce in here
	public void setGameState(GameState gs) {
		gameState = gs;
	}
}
