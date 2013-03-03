/**
 * @author Mike Kucharski
 * Fall 2012 | COMP 285 Object Oriented Programming
 */

package towersofhanoi;

import java.awt.Color;
import java.util.ArrayList;

public class GameState {
	
	//create an instance of the GUI here so it doesn't have to be static
	DrawTowers GUI;
	
	//number of blocks to start with
	int numberOfBlocks, from, to;
	final int MAX_BLOCKS = 6;
	
	//keeps track of the number of blocks on each pole
	ArrayList<Block> pole1;
	ArrayList<Block> pole2;
	ArrayList<Block> pole3;
	
	//keeps track of the algorithm for the simplest solution
	ArrayList<String> solutions;
	//keeps track of all the possible moves at any given block positions
	ArrayList<String> possibleMoves;
	
	//create max number of blocks
	Block [] block = new Block[MAX_BLOCKS];
	
	//constructor == initializes all the variables
	public GameState(DrawTowers gui)
	{
		GUI = gui;
		//simplest tower has one block
		numberOfBlocks = 1;
		
		pole1 = new ArrayList<Block>();
		pole2 = new ArrayList<Block>();
		pole3 = new ArrayList<Block>();
		solutions = new ArrayList<String>();
		possibleMoves = new ArrayList<String>();
		
		//initialze every block to have a specific width and color
		block[5] = new Block(160, new Color(233,104,134));
		block[4] = new Block(140, new Color(90,169,222));
		block[3] = new Block(120, new Color(165,250,245));
		block[2] = new Block(100, new Color(217,139,255));
		block[1] = new Block(80, new Color(255,230,168));
		block[0] = new Block(60, new Color(246,215,62));
	}
	
	//takes care of what happens logically to the game when each button is pressed
	public void buttonClick(String buttonText)
	{
		//sets up all blocks in order on pole 1
		if(buttonText == "Start")
		{
			int pole1Blocks = GUI.getNumberOfDisks();
			for(int i = pole1Blocks-1; i >= 0; i--)
				pole1.add(block[i]);
			
			//fill array list with possible moves
			getPossibleMoves();
		}
		//get the start and end pole numbers, call move function to move them, update possible moves
		else if(buttonText.contains("Move"))
		{
			if(buttonText.contains("pole"))
			{
				from = Character.getNumericValue(buttonText.charAt(10));
				to = Character.getNumericValue(buttonText.charAt(20));
			}
			else{
				from = Character.getNumericValue(buttonText.charAt(5));
				to = Character.getNumericValue(buttonText.charAt(10));
			}
			moveDisk(from, to);
			//set up possible moves
			getPossibleMoves();
		}
		//clears every pole and "starts" again
		else if(buttonText == "Reset")
		{
			buttonClick("New Disks");
			buttonClick("Start");
		}
		//clears every pole
		else if(buttonText == "New Disks")
		{
			pole1.clear();
			pole2.clear();
			pole3.clear();
		}
		//reset the board
		else if(buttonText == "Show me how to solve")
		{
			buttonClick("Reset");
		}
		
	}

	//this will add all the possible moves to the possibleMoves arraylist
	public void getPossibleMoves() {
		//clear list to start, prevents duplicate moves
		possibleMoves.clear();
		
		//first check 1- (2,3)
		if(!pole1.isEmpty())
		{
			//pole 2 check
			if(pole2.isEmpty() || pole1.get(pole1.size()-1).getWidth()  <  pole2.get(pole2.size()-1).getWidth())
				possibleMoves.add("Move 1 to 2");	
			//pole 3 check
			if(pole3.isEmpty() || pole1.get(pole1.size()-1).getWidth()  <  pole3.get(pole3.size()-1).getWidth())
				possibleMoves.add("Move 1 to 3");
		}

		//then check 2- (1,3)
		if(!pole2.isEmpty())
		{
			//pole 2 check
			if(pole1.isEmpty() || pole2.get(pole2.size()-1).getWidth()  <  pole1.get(pole1.size()-1).getWidth())
				possibleMoves.add("Move 2 to 1");	
			//pole 3 check
			if(pole3.isEmpty() || pole2.get(pole2.size()-1).getWidth()  <  pole3.get(pole3.size()-1).getWidth())
				possibleMoves.add("Move 2 to 3");
		}
		//then check 3- (1,2)
		if(!pole3.isEmpty())
		{
			//pole 2 check
			if(pole1.isEmpty() || pole3.get(pole3.size()-1).getWidth()  <  pole1.get(pole1.size()-1).getWidth())
				possibleMoves.add("Move 3 to 1");	
			//pole 3 check
			if(pole2.isEmpty() || pole3.get(pole3.size()-1).getWidth()  <  pole2.get(pole2.size()-1).getWidth())
				possibleMoves.add("Move 3 to 2");
		}
	}
	
	//removes a block from the start pole and adds a block to the end pole
	public void moveDisk(int p1, int p2){
		if(p1 == 1 && p2 == 2){
			pole2.add(pole1.get(pole1.size()-1));
			pole1.remove(pole1.size()-1);
		}
		else if(p1 == 1 && p2 == 3){
			pole3.add(pole1.get(pole1.size()-1));
			pole1.remove(pole1.size()-1);
		}
		else if(p1 == 2 && p2 == 1){
			pole1.add(pole2.get(pole2.size()-1));
			pole2.remove(pole2.size()-1);
		}
		else if(p1 == 2 && p2 == 3){
			pole3.add(pole2.get(pole2.size()-1));
			pole2.remove(pole2.size()-1);
		}
		if(p1 == 3 && p2 == 1){
			pole1.add(pole3.get(pole3.size()-1));
			pole3.remove(pole3.size()-1);
		}
		if(p1 == 3 && p2 == 2){
			pole2.add(pole3.get(pole3.size()-1));
			pole3.remove(pole3.size()-1);
		}
	}
	
	//fills the array list of possible moves with a recursive function
	public ArrayList<String> getSolutions()
	{
		solutions.clear();
		move( GUI.getNumberOfDisks(), 1, 3, 2);
		return solutions;
	}
	
	//recursive function, keeps calling move until number of disks == 1, then adds that move
	public void move(int numberOfDisks, int startPole, int endPole, int usingPole) {
		if (numberOfDisks == 1) {
		  solutions.add("Move pole " + startPole + " to pole " + endPole);
	    } else {
		  move(numberOfDisks - 1, startPole, usingPole, endPole);
		  move(1, startPole, endPole, usingPole);
		  move(numberOfDisks - 1, usingPole, endPole, startPole);
		 }
	}
	
	//returns if the game is logically over (all blocks are on pole 3)
	public boolean solvedCheck()
	{
		if(pole1.isEmpty() && pole2.isEmpty())
			return true;
		return false;
	}
}
