/**
 * BreakoutPanel class handles the game mechanics and interactions for Breakout.
 * Manages game state, user input, collisions, and painting game elements.
 * 
 * Jeremy Stephens 
 * Student ID: 5071232
 * Course: BIT 504 
 * Assignment: 3
 */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BreakoutPanel extends JPanel implements ActionListener, KeyListener {
    
    static final long serialVersionUID = 2L;

    private boolean gameRunning = true;
    private int livesLeft = 3;
    private String screenMessage = "";
    private Ball ball;
    private Paddle paddle;
    private Brick bricks[];
    
    private int countdown = 10; // starts at 10 seconds
    private Timer restartTimer;
    private boolean isCountdownActive = false;


    
    public BreakoutPanel(Breakout game) {
        // Setting the preferred size ensures the content pane gets the desired size for our game.
        this.setPreferredSize(new Dimension(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT));
        
        addKeyListener(this);
        setFocusable(true);
        
        Timer timer = new Timer(5, this);
        timer.start();
        
        // TODO: Create a new ball object and assign it to the appropriate variable
        ball = new Ball();
        // TODO: Create a new paddle object and assign it to the appropriate variable
        paddle = new Paddle();
        // TODO: Create a new bricks array (Use Settings.TOTAL_BRICKS)
        bricks = new Brick[Settings.TOTAL_BRICKS];
        // TODO: Call the createBricks() method
        createBricks();
    }
    
    private void createBricks() {
        int counter = 0;
        int x_space = 0;
        int y_space = 0;
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 5; y++) {
            	// An offset of 8 pixels is added to ensure the bricks are centered in the content pane, compensating for changes in frame dimensions.
                bricks[counter] = new Brick(8 + (x * Settings.BRICK_WIDTH) + Settings.BRICK_HORI_PADDING + x_space, (y * Settings.BRICK_HEIGHT) + Settings.BRICK_VERT_PADDING + y_space);
                counter++;
                y_space++;
            }
            x_space++;
            y_space = 0;
        }
    }
    
    private void paintBricks(Graphics g) {
        // TODO: Loop through the bricks and call the paint() method
        for(Brick brick : bricks) {
            brick.paint(g);
        }
    }
    
    private void update() {
        if(gameRunning) {
            // TODO: Update the ball and paddle
            ball.update();
            paddle.update();
            collisions();
            repaint();
        }
    }
    
    private void gameOver() {
        // TODO: Set screen message
        screenMessage = "Game Over";
        stopGame();
        
        // Start the countdown timer to restart the game
        startRestartCountdown();
    }
    
    private void gameWon() {
        // TODO: Set screen message
        screenMessage = "Game Won";
        stopGame();
        
        // Start the countdown timer to restart the game
        startRestartCountdown();
    }
    
    private void stopGame() {
        gameRunning = false;
    }
    
    //Reset the game to its initial state.
    private void resetGame() {
        ball.resetPosition();
        paddle.resetPosition();
        createBricks();
        livesLeft = 3; 
        gameRunning = true;
        countdown = 10;
        screenMessage = "";
    }
    
    private void startRestartCountdown() {
        countdown = 10; // reset countdown

        if (restartTimer != null) {
            restartTimer.stop(); // stop any previous timer instance
        }

        // This timer will tick every second (1000 ms)
        restartTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isCountdownActive = true; // countdown is active now
                countdown--;

                if (countdown <= 0) {
                    // When countdown is over
                    ((Timer)e.getSource()).stop(); // Stop the timer
                    resetGame();  // Reset the game state
                    gameRunning = true; // Start the game again
                    isCountdownActive = false; // countdown is no longer active      
                }
                repaint(); // To refresh the displayed countdown
            }
        });
        
        restartTimer.start();
    }
    
    private void collisions() {
        // Check for loss
        if(ball.y > 450) {
            // Game over
            livesLeft--;
            if(livesLeft <= 0) {
                gameOver();
                return;
            } else {
                ball.resetPosition();
                ball.setYVelocity(-1);
            }
        }
        
        // Check for win
        boolean bricksLeft = false;
        for(int i = 0; i < bricks.length; i++) {
            // Check if there are any bricks left
            if(!bricks[i].isBroken()) {
                // Brick was found, close loop
                bricksLeft = true;
                break;
            }
        }
        if(!bricksLeft) {
            gameWon();
            return;
        }
        
        // Check collisions
        if(ball.getRectangle().intersects(paddle.getRectangle())) {
            // Simplified touching of paddle
            // Proper game would change angle of ball depending on where it hit the paddle
            ball.setYVelocity(-1);
        }
        
        // 	Iterate over all the bricks to check for collisions
        for(int i = 0; i < bricks.length; i++) {
        	
            // Only check collisions for bricks that are not already broken
            if (!bricks[i].isBroken() && ball.getRectangle().intersects(bricks[i].getRectangle())) {
                
                // Get the center points of the ball and the brick
                int ballCenterX = ball.getX() + ball.getWidth() / 2;
                int ballCenterY = ball.getY() + ball.getHeight() / 2;
                int brickCenterX = bricks[i].getX() + bricks[i].getWidth() / 2;
                int brickCenterY = bricks[i].getY() + bricks[i].getHeight() / 2;
                
                // Calculate the differences between the X and Y coordinates of the centers
                int deltaX = ballCenterX - brickCenterX;
                int deltaY = ballCenterY - brickCenterY;

                // Determine which side of the brick the ball hit based on the magnitude of the differences
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    // Ball hit the left or right of the brick, so reverse the horizontal velocity
                    ball.setXVelocity(-ball.getXVelocity()); 
                } else {
                    // Ball hit the top or bottom of the brick, so reverse the vertical velocity
                    ball.setYVelocity(-ball.getYVelocity());
                }
                // Mark the brick as broken
                bricks[i].setBroken(true);
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ball.paint(g);
        paddle.paint(g);
        paintBricks(g);
        
        // Draw lives left
        // TODO: Draw lives left in the top left-hand corner
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Lives: " + livesLeft, Settings.LIVES_POSITION_X, Settings.LIVES_POSITION_Y);
        
        // Draw screen message
        if(screenMessage != null) {
            g.setFont(new Font("Arial", Font.BOLD, 18));
            int messageWidth = g.getFontMetrics().stringWidth(screenMessage);
            g.drawString(screenMessage, (Settings.WINDOW_WIDTH / 2) - (messageWidth / 2), Settings.MESSAGE_POSITION);
        }

        // If the game is not currently running, display the countdown and exit instructions
        if (!gameRunning) {
            g.setFont(new Font("Arial", Font.BOLD, 18));
            
            // Display the countdown message for the next game start
            String countdownMessage = "Next game starts in " + countdown + " seconds...";
            int countdownMessageWidth = g.getFontMetrics().stringWidth(countdownMessage);
            g.drawString(countdownMessage, (Settings.WINDOW_WIDTH / 2) - (countdownMessageWidth / 2), Settings.MESSAGE_POSITION + 30);
            
            // Display the message for the user to exit the game by pressing 'X'
            String exitMessage = "Press 'X' to Exit";
            int exitMessageWidth = g.getFontMetrics().stringWidth(exitMessage);
            g.drawString(exitMessage, (Settings.WINDOW_WIDTH / 2) - (exitMessageWidth / 2), Settings.MESSAGE_POSITION + 60);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO: Set the velocity of the paddle depending on whether the player is pressing left or right
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            paddle.setXVelocity(-5);  // Assuming -5 is a suitable velocity to move left
        }
        if (key == KeyEvent.VK_RIGHT) {
            paddle.setXVelocity(5);   // Assuming 5 is a suitable velocity to move right
        }
        
        // If countdown is active and "X" key is pressed, exit the game.
        if (isCountdownActive && e.getKeyCode() == KeyEvent.VK_X) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO: Set the velocity of the paddle after the player has released the keys
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            paddle.setXVelocity(0);   // Stop the paddle when the key is released
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
	}
}