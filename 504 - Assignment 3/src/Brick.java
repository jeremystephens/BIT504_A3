/**
 * Brick class represents individual breakable blocks in Breakout.
 * Manages the state (broken or intact) and drawing of each brick.
 * 
 * Jeremy Stephens 
 * Student ID: 5071232
 * Course: BIT 504 
 * Assignment: 3
 */

import java.awt.Graphics;

public class Brick extends Sprite {
	
	private boolean broken = false;
	
	public Brick(int x, int y) {
		// TODO: Set x using the parameter
		setX(x);
		// TODO: Set y using the parameter
		setY(y);
		// TODO: Set the width and height of the brick using Settings.BRICK_WIDTH/HEIGHT
		setWidth(Settings.BRICK_WIDTH);
		setHeight(Settings.BRICK_HEIGHT);
	}

	public boolean isBroken() {
		// TODO: Return the correct variable
		return broken; 
	}
	public void setBroken(boolean b) {
		// TODO: Set the broken variable using the parameter given
		broken = b;
	}
	
	public void paint(Graphics g) {
		if(!broken) {
			g.fillRect(x, y, Settings.BRICK_WIDTH, Settings.BRICK_HEIGHT);
		}
	}
}
