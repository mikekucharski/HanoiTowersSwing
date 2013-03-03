/**
 * @author Mike Kucharski
 * Fall 2012 | COMP 285 Object Oriented Programming
 */

package towersofhanoi;

public class TowersApp {
	
	//DrawTowers GUI;
	//static GameState gs;
	
	public static void main(String[] args)
	{	
		//create the GUI object
		DrawTowers GUI = new DrawTowers();
		
		//create the object that will control the state of the game
		GameState gs = new GameState(GUI);
		
		GUI.setGameState(gs);
		
		//show the GUI to the user
		GUI.setVisible(true);
	}
}
