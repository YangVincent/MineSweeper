import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class MineSweeperPanel extends JPanel implements MouseListener{
	//fields
	private static int SUBDIVISIONS;//40
	
	boolean higher, higher25, higher30;
	private static final int DRAWING_SIZE = 920;
	private static int SUBDIVISION_SIZE;
	MineSweeper game;
	private Image blank;
	private Image bomb;
	private Image flag;
	private Image lastBomb;
	private int lastBombX, lastBombY;
	private int numBombs;
	
	//constructors
	public MineSweeperPanel(MineSweeper g, int s)
	{
		higher25 = false;
		higher = false;
		SUBDIVISIONS = s;
		SUBDIVISION_SIZE  = DRAWING_SIZE/SUBDIVISIONS;
		numBombs = 350;
		setBackground(Color.BLACK);
		addMouseListener(this);
		game = g;
		game.setNumFlags(numBombs);
		lastBomb = Toolkit.getDefaultToolkit().getImage("lastBomb.png");
		blank = Toolkit.getDefaultToolkit().getImage("MineSweeperUnopenedSquare.png");
		bomb = Toolkit.getDefaultToolkit().getImage("MineSweeperBomb.png");
		flag = Toolkit.getDefaultToolkit().getImage("flag.png");
		//game.generateBombs(350);
		//game.setNumbers();
		//background is white, draw the lines. 
	}
	//methods
	public void setNumDivisions(int d)
	{
		SUBDIVISIONS = d;
		SUBDIVISION_SIZE = DRAWING_SIZE/SUBDIVISIONS;
	}
	public void setLastBombX(int x)
	{
		lastBombX = x;
	}
	public void setLastBombY(int y)
	{
		lastBombY = y;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D) g;
		lastBombX = game.getEndGameX();
		lastBombY = game.getEndGameY();
		//paint the other colors
		/*
		for (int i = 0; i < SUBDIVISIONS; i++)
		{
			for (int j = 0; j < SUBDIVISIONS; j++)
			{
				int num = game.getNum(i, j);
				g2.setPaint(Color.BLUE);
				if (num == 9)
					g.fillRect(j*SUBDIVISION_SIZE, i*SUBDIVISION_SIZE, SUBDIVISION_SIZE, SUBDIVISION_SIZE);
				g2.setPaint(Color.BLACK);
			}
		}*/
		for (int i = 0; i < SUBDIVISIONS; i++)
		{
			for (int j = 0; j < SUBDIVISIONS; j++)
			{
				int num = game.getNum(i, j);
				g2.setPaint(Color.GRAY);
				if (num !=9 && num<0)
					g.fillRect(j*SUBDIVISION_SIZE, i*SUBDIVISION_SIZE, SUBDIVISION_SIZE, SUBDIVISION_SIZE);
				g2.setPaint(Color.BLACK);
			}
		}
		for (int i = 0; i < SUBDIVISIONS; i++)
		{
			for (int j = 0; j < SUBDIVISIONS; j++)
			{
				int num = game.getNum(i, j);
				g2.setPaint(Color.GRAY);
				if (num ==-9)
					g.fillRect(j*SUBDIVISION_SIZE, i*SUBDIVISION_SIZE, SUBDIVISION_SIZE, SUBDIVISION_SIZE);
				g2.setPaint(Color.BLACK);
			}
		}
		
		for (int i = 0; i < SUBDIVISIONS; i++)
		{
			for (int j = 0; j < SUBDIVISIONS; j++)
			{
				int num = game.getNum(i, j);
				if (num >=0)
				{
					g.drawImage(blank, j*SUBDIVISION_SIZE, i*SUBDIVISION_SIZE, SUBDIVISION_SIZE, SUBDIVISION_SIZE, null);
				//g.drawImage(blank, i*SUBDIVISION_SIZE, j*SUBDIVISION_SIZE, SUBDIVISION_SIZE, null);//fix here
				}
				int numFlag = game.getFlagGrid()[i][j];
				if (numFlag == 1)
				{
					g.drawImage(flag, j*SUBDIVISION_SIZE, i*SUBDIVISION_SIZE, SUBDIVISION_SIZE, SUBDIVISION_SIZE, null);
				}
			}
		}
		//draw the numbers
		g.setFont(new Font("default", Font.BOLD, (int)(640/SUBDIVISIONS)));
		if (game.isGameOver())
		{
			for (int i = 0; i < SUBDIVISIONS; i++)
			{
				for (int j = 0; j < SUBDIVISIONS; j++)
				{
					int num = game.getNum(i, j);
					g2.setPaint(Color.WHITE);
				//	if (num !=9 && num < 0 && num!=-9) //uncomment this to hide numbers
					if (num < 0 && num != -9)
					{
						if (num == -1)
						{
							g2.setPaint(Color.BLUE);
						}
						else if (num == -2)
						{
							g2.setPaint(Color.GREEN);
						}
						else if (num == -3)
						{
							g2.setPaint(Color.RED);
						}
						else if (num == -4)
						{
							g2.setPaint(Color.BLUE.darker().darker());
						}
						else if (num == -5)
						{
							g2.setPaint(Color.RED.darker().darker());
						}  
						else if (num == -6)
						{
							g2.setPaint(Color.GREEN.darker().darker());
						}
						else if (num == -7)
						{
							g2.setPaint(Color.YELLOW);
						}
						else if (num == -8)
						{
							g2.setPaint(Color.CYAN);
						}
						g.setFont(new Font("default", Font.BOLD, (int)(640/SUBDIVISIONS)));
						g.drawString(""+num*-1, j*SUBDIVISION_SIZE-g.getFontMetrics().stringWidth(num*-1+"")/2+SUBDIVISION_SIZE/2, (i+1)*SUBDIVISION_SIZE-g.getFontMetrics().getHeight()/4);
					}
					g2.setPaint(Color.BLACK);
					if (num == 9)
					{
						g.drawImage(bomb, j*SUBDIVISION_SIZE, i*SUBDIVISION_SIZE, SUBDIVISION_SIZE, SUBDIVISION_SIZE, this);
					}
				}
			}
			if (!(lastBombX == 0 && lastBombY == 0))
			{
				g.drawImage(lastBomb, lastBombY*SUBDIVISION_SIZE, lastBombX*SUBDIVISION_SIZE, SUBDIVISION_SIZE, SUBDIVISION_SIZE, this);
				//System.out.println("lastbombx and y are " + lastBombX + " " + lastBombY);
			}
			
		}
		else
		{
			for (int i = 0; i < SUBDIVISIONS; i++)
			{
				for (int j = 0; j < SUBDIVISIONS; j++)
				{
					int num = game.getNum(i, j);
					if (num == -1)
					{
						g2.setPaint(Color.BLUE);
					}
					else if (num == -2)
					{
						g2.setPaint(Color.GREEN);
					}
					else if (num == -3)
					{
						g2.setPaint(Color.RED);
					}
					else if (num == -4)
					{
						g2.setPaint(Color.BLUE.darker().darker());
					}
					else if (num == -5)
					{
						g2.setPaint(Color.RED.darker().darker());
					}
					else if (num == -6)
					{
						g2.setPaint(Color.GREEN.darker().darker());
					}
					else if (num == -7)
					{
						g2.setPaint(Color.YELLOW);
					}
					else if (num == -8)
					{
						g2.setPaint(Color.CYAN);
					}
					g.setFont(new Font("default", Font.BOLD, (int)(640/SUBDIVISIONS)));
					if (num < 0 && num!=-9) //uncomment this to hide numbers
						g.drawString(""+num*-1, j*SUBDIVISION_SIZE-g.getFontMetrics().stringWidth((num*-1)+"")/2+SUBDIVISION_SIZE/2, (i+1)*SUBDIVISION_SIZE-g.getFontMetrics().getHeight()/4);
					g2.setPaint(Color.BLACK);
				}
			}
			
		}
		//draw the grid
		g.setColor(Color.WHITE);
		for (int i = 1; i <= SUBDIVISIONS; i++)
		{
			int x = i*SUBDIVISION_SIZE;
			g2.drawLine(x, 0, x, getSize().height);
			
		}
		for (int i = 0; i <= SUBDIVISIONS; i++)
		{
			int y  = i*SUBDIVISION_SIZE;
			g2.drawLine(0, y, getSize().width, y);
		}
		//g.drawImage(bomb, 0, 0, 500, 500, this);

		g.setFont(new Font("default", Font.BOLD, 26));
		g.setColor(Color.BLACK);
		g.fillRect(0, DRAWING_SIZE, DRAWING_SIZE, 50);
		if (higher)
		{
			g.fillRect(0, DRAWING_SIZE-10, DRAWING_SIZE, 83);
			g.setColor(Color.WHITE);
			g.drawString("Flags: " + game.getFlags(), 20, DRAWING_SIZE+2*g.getFontMetrics().getHeight()/3);
			
		}
		else if (higher25)
		{
			g.fillRect(0,  DRAWING_SIZE-20,  DRAWING_SIZE,  83);
			g.setColor(Color.WHITE);
			g.drawString("Flags: " + game.getFlags(), 20, DRAWING_SIZE+g.getFontMetrics().getHeight()/2);
		}
		else if (higher30)
		{
			g.fillRect(0,  DRAWING_SIZE-20,  DRAWING_SIZE, 83);
			g.setColor(Color.WHITE);
			g.drawString("Flags: " + game.getFlags(), 20, DRAWING_SIZE+g.getFontMetrics().getHeight()/2);
		}
		g.setColor(Color.GRAY.brighter());
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.BOLD, 26));
		if (!higher && !higher25 && !higher30)
		{
			g.drawString("Flags: " + game.getFlags(), 20, DRAWING_SIZE+3*g.getFontMetrics().getHeight()/4);
		}
		if (game.gameWon())
		{
			//draw game over haze
			g.setFont(new Font("default", Font.BOLD, 64));
			g.setColor(new Color(128, 128, 128, 128));
			g.fillRect(0, 0, DRAWING_SIZE, DRAWING_SIZE);
			g.setColor(Color.BLACK);
			g.drawString("Congratulations!", DRAWING_SIZE/2-g.getFontMetrics().stringWidth("Congratulations!")/2, DRAWING_SIZE/2);
		}
		repaint();
	}
	public void higher(int subdivisions)
	{
		if (subdivisions == 35)
		{
			higher = true;
		}
		else if (subdivisions == 25)
			higher25 = true;
		else if (subdivisions == 30)
		{
			higher30 = true;
			higher25 = false;
			higher = false;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		//System.out.println("clicked on " + x + " " + y);
		if (SwingUtilities.isRightMouseButton(e))
		{
			game.clickSquare(x, y, true);
		}
		else
			game.clickSquare(x, y, false);
	}
	public void reset()
	{
		higher = false;
		higher25 = false;
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
