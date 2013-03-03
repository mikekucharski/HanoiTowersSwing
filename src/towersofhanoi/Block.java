/**
 * @author Mike Kucharski
 * Fall 2012 | COMP 285 Object Oriented Programming
 */

package towersofhanoi;

import java.awt.Color;

//this class will define the objects that are moving around the poles
public class Block {

	// every block will be the same height
	final int HEIGHT = 25;

	// each block will be defined by its width and color
	int width;
	Color color;

	// default constructor
	public Block() {
		this(0, null);
	}

	// constructor to initialize blokc stats
	public Block(int w, Color c) {
		width = w;
		color = c;
	}

	// returns block height
	public int getHeight() {
		return HEIGHT;
	}

	// returns block width
	public int getWidth() {
		return width;
	}

	// returns block color
	public Color getColor() {
		return color;
	}
}
