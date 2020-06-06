import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private ImageIcon titleImage;
	private ImageIcon leftMouth;
	private ImageIcon rightMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	private ImageIcon snake;
	
	private int length = 3;
	private int moves = 0;
	
	private int[] xPos = new int[750];
	private int[] yPos = new int[750];
	
	private int[] eatXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
	                         350,375,400,425,450,475,500,525,550,575,600,625,650,
	                         675,700,725,750,775,800,825,850};
	
	private int[] eatYpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
            				350,375,400,425,450,475,500,525,550,575,600,625,650,
            				675,700,725,750,775,800,825,850};
	
	private ImageIcon eatImage;
	
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private Timer timer;
	private int delay = 100;
	
	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint (Graphics g) {
		
		
		if(moves == 0) {
			xPos[2] = 50;
			xPos[1] = 75;
			xPos[0] = 100;
			
			yPos[2] = 100;
			yPos[1] = 100;
			yPos[0] = 100;
		}
		
		// for title
		g.setColor(Color.WHITE);
		g.drawRect(24,10,851,55);
		
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		// board area
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		
		// snake position
		rightMouth = new ImageIcon("rightmouth.png");
		rightMouth.paintIcon(this, g, xPos[0], yPos[0]);
		
		for(int x = 0; x < length; x++) {
			if(x==0 && right) {
				rightMouth = new ImageIcon("rightmouth.png");
				rightMouth.paintIcon(this, g, xPos[x], yPos[x]);
			}
			
			if(x==0 && left) {
				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this, g, xPos[x], yPos[x]);
			}
			
			if(x==0 && up) {
				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this, g, xPos[x], yPos[x]);
			}
			
			if(x==0 && down) {
				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this, g, xPos[x], yPos[x]);
			}
			
			if(x!=0) {
				snake = new ImageIcon("snakeimage.png");
				snake.paintIcon(this, g, xPos[x], yPos[x]);
			}
			
			
		}
		
		eatImage = new ImageIcon("enemy.png");
		eatImage.paintIcon(this, g, eatXpos[xpos], eatYpos[ypos]);
		
		if((eatXpos[xpos] == xPos[0]) && (eatYpos[ypos] == yPos[0])) {
			length++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(right) {
			for(int i = length -1; i >= 0 ; i--) {
				yPos[i+1] = yPos[i];
			}
			
			for(int i = length; i>=0; i--) {
				if (i==0) {
					xPos[i] = xPos[i] + 25;
				}
				else {
					xPos[i] = xPos[i-1];
				}
				if(xPos[i] > 850) {
					xPos[i] = 25;
				}
			}
			repaint();
		}
		
		if(left) {
			for(int i = length -1; i >= 0 ; i--) {
				yPos[i+1] = yPos[i];
			}
			
			for(int i = length; i>=0; i--) {
				if (i==0) {
					xPos[i] = xPos[i] - 25;
				}
				else {
					xPos[i] = xPos[i-1];
				}
				if(xPos[i] < 25) {
					xPos[i] = 850;
				}
			}
			repaint();
		}
		
		
		if(down) {
			for(int i = length -1; i >= 0 ; i--) {
				xPos[i+1] = xPos[i];
			}
			
			for(int i = length; i>=0; i--) {
				if (i==0) {
					yPos[i] = yPos[i] + 25;
				}
				else {
					yPos[i] = yPos[i-1];
				}
				if(yPos[i] > 625) {
					yPos[i] = 75;
				}
			}
			repaint();
		}
		
		if(up) {
			for(int i = length -1; i >= 0 ; i--) {
				xPos[i+1] = xPos[i];
			}
			
			for(int i = length; i>=0; i--) {
				if (i==0) {
					yPos[i] = yPos[i] - 25;
				}
				else {
					yPos[i] = yPos[i-1];
				}
				if(yPos[i] < 75) {
					yPos[i] = 625;
				}
			}
			repaint();
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			left = false;
			up = false;
			down = false;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			right = false;
			left = true;
			up = false;
			down = false;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			right = false;
			left = false;
			up = true;
			down = false;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			right = false;
			left = false;
			up = false;
			down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
