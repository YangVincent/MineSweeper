import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;


public class MenuPanel extends JPanel 
{
	//FIELDS
	private static final int SUBDIVISIONS = 40;
	private static final int DRAWING_SIZE = 920;
	private static final int SUBDIVISION_SIZE = DRAWING_SIZE/SUBDIVISIONS;
	private Image background;
	//CONSTRUCTORS
	public MenuPanel()
	{
		//background = 
		setBackground(Color.WHITE);
		
	}
	//METHODS
	public void paintComponent(Graphics g)
	{
		//image, 
		//buttons with dropdown
		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(0, 0, DRAWING_SIZE, 100);
	}
}
