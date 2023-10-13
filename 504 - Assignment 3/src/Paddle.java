/**
 * Paddle class represents the player-controlled game element in Breakout.
 * Handles the movement and drawing of the paddle, and responds to player input.
 * 
 * Jeremy Stephens 
 * Student ID: 5071232
 * Course: BIT 504 
 * Assignment: 3 
 */

import java.awt.Graphics;

public class Paddle extends Sprite {

    private int xVelocity;
    
    /**
     * Constructor for the Paddle class.
     * Initializes the paddle's width, height, and position, and calls resetPosition().
     */
    public Paddle() {
        // TODO: Set width to Settings.PADDLE_WIDTH
        setWidth(Settings.PADDLE_WIDTH);
        // TODO: Set width to Settings.PADDLE_HEIGHT
        setHeight(Settings.PADDLE_HEIGHT);
        // TODO: Call resetPosition
        resetPosition();
    }
    
    /**
     * Resets the position of the paddle to its initial position.
     * Uses Settings.INITIAL_PADDLE_X and Settings.INITIAL_PADDLE_Y.
     */
    public void resetPosition() {
        // TODO: Set initial position x and y (use INITIAL_PADDLE_X/Y)
        // Note: Check Ball.java for a hint
        int centeredXPosition = (Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH) / 2;
        setX(centeredXPosition);
        setY(Settings.INITIAL_PADDLE_Y);
    }
    
    /**
     * Updates the paddle's position based on its velocity.
     * Prevents the paddle from moving outside of the screen boundaries.
     */
    public void update() {
        x += xVelocity;
        
        // TODO: Prevent the paddle from moving outside of the screen
        // This can be done using two if statements (one for the left side of the screen and one for the right)

        // Prevent the paddle from moving outside of the left side of the screen
        if (x <= 0) {
            x = 0;
        }
        // Prevent the paddle from moving outside of the right side of the screen
        else if (x >= Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH) {
            x = Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH;
        }
    }
    
    /**
     * Paints the paddle on the given Graphics object.
     * @param g The Graphics object to paint on.
     */
    public void paint(Graphics g) {
        g.fillRect(x, y, Settings.PADDLE_WIDTH, Settings.PADDLE_HEIGHT);
    }
    
    /**
     * Sets the horizontal velocity of the paddle.
     * @param vel The new horizontal velocity.
     */
    public void setXVelocity(int vel) {
        xVelocity = vel;
    }
}