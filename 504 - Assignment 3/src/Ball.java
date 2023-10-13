/**
 * Ball class handles the movement and interactions 
 * for the primary game element in Breakout.
 * 
 * Jeremy Stephens 
 * Student ID: 5071232
 * Course: BIT 504 
 * Assignment: 3 
 */

import java.awt.Graphics;

public class Ball extends Sprite {

    private int xVelocity = 1, yVelocity = -1;
    
    // Constructor
    public Ball() {
        // TODO: Set width to Settings.BALL_WIDTH
        setWidth(Settings.BALL_WIDTH);
        // TODO: Set width to Settings.BALL_HEIGHT
        setHeight(Settings.BALL_HEIGHT);
        // TODO: Call resetPosition
        resetPosition();
    }
    
    /**
     * Resets the ball to the initial position
     * Uses Settings.INITIAL_BALL_X/Y to set the position of the ball
     */
    public void resetPosition() {
        setX(Settings.INITIAL_BALL_X);
        
        // TODO: Set the ball's y by using the INITIAL_BALL_Y (see above)
        setY(Settings.INITIAL_BALL_Y);
    }
    
    /**
     * Updates the ball's position based on its velocities.
     * Also handles boundary checks for bouncing.
     */
    public void update() {
        // Move the ball based on its velocities
        x += xVelocity;
        
        // TODO: Increase the y variable by yVelocity (see above)
        y += yVelocity;

        // Logging ball's position before boundary checks to diagnose potential out-of-bound issues.
        //System.out.println("Before checks: Ball X = " + x);
        //System.out.println("Before checks: Ball Y = " + y);
        
        // Bounce off the left side of the screen
        if (x <= 0) {
            // TODO: Set x to 0 so it does not leave the screen
            x = 0;
            // TODO: Change the x velocity to make the ball go right
            xVelocity = Math.abs(xVelocity);
        }

        // Bounce off the right side of the screen
        if (x + Settings.BALL_WIDTH >= Settings.WINDOW_WIDTH) {
            // TODO: Set x to the right edge of the screen (see the above if condition)
            x = Settings.WINDOW_WIDTH - Settings.BALL_WIDTH;
            // TODO: Change the x velocity to make the ball go left
            xVelocity = -Math.abs(xVelocity);
        }

        // Bounce off the top of the screen
        if (y <= 0) {
            // TODO: Change the x velocity to make the ball go left
            y = 0;
            // TODO: Change the y velocity to make the ball go downward
            yVelocity = Math.abs(yVelocity);
        }

        // Logging ball's position after boundary checks to confirm corrections.
        // System.out.println("After checks: Ball X = " + x);
        // System.out.println("After checks: Ball Y = " + y);
    }
    
    /**
     * Sets the x velocity of the ball.
     * @param x The new x velocity.
     */
    public void setXVelocity(int x) {
        // TODO: Set the x velocity
        xVelocity = x;
    }
    
    /**
     * Sets the y velocity of the ball.
     * @param y The new y velocity.
     */
    public void setYVelocity(int y) {
        // TODO: Set the y velocity
        yVelocity = y;
    }
    
    /**
     * Gets the current x velocity of the ball.
     * @return The x velocity.
     */
    public int getXVelocity() {
        // TODO: Return the x velocity
        return xVelocity;
    }
    
    /**
     * Gets the current y velocity of the ball.
     * @return The y velocity.
     */
    public int getYVelocity() {
        // TODO: Return the y velocity
        return yVelocity;
    }
    
    /**
     * Paints the ball on the given Graphics object.
     * @param g The Graphics object to paint on.
     */
    public void paint(Graphics g) {
        g.fillOval(x, y, Settings.BALL_WIDTH, Settings.BALL_HEIGHT);
    }
}
