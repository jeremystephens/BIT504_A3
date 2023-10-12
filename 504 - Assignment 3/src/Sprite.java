/**
 * Sprite class defines basic game elements with attributes like position and size.
 * 
 * Jeremy Stephens 
 * Student ID: 5071232
 * Course: BIT 504 
 * Assignment: 3
 */

import java.awt.Rectangle;

public class Sprite {
	
	protected int x,y,width,height;
	
	// Note: This should only set a single value, they can be done in a single statement
	
	public void setX(int x) {
		// TODO
		this.x = x;
	}
	public void setY(int y) {
		// TODO
		this.y = y;
	}
	public void setWidth(int width) {
		// TODO
		this.width = width;
	}
	public void setHeight(int height) {
		// TODO
		this.height = height;
	}
	
	// Note: Change the "0" to the correct variable
	public int getX() { 
		// TODO: Return correct value
		return x;
	}
	public int getY() { 
		// TODO: Return correct value
		return y;
	}
	public int getWidth() { 
		// TODO: Return correct value
		return width;
	}
	public int getHeight() { 
		// TODO: Return correct value
		return height;
	}
	
	Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
