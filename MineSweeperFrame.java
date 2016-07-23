import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class MineSweeperFrame extends JFrame implements ActionListener, ItemListener{
	
	//FIELDS
	private final int DRAWING_SIZE = 920;
	private JPanel panel;
	private JPanel menu;
	private JPanel cards;
	private JMenuBar menuBar;
	JPopupMenu popup;
	JMenuItem menuItem;
	JDialog dialog;
	int numFlags;
	MineSweeper game;
	
	public MineSweeperFrame()
	{
		super ("MineSweeper!");
		//setSize(DRAWING_SIZE, DRAWING_SIZE+23);
		setSize(DRAWING_SIZE+5, DRAWING_SIZE+83);
		setLocation(100, 20);
		setResizable(false);
		game = new MineSweeper(40);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new MineSweeperPanel(game, 40);
		menu = new MenuPanel();
		
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Options");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("New Game",
		                         KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Set Bombs");
		menuItem.setMnemonic(KeyEvent.VK_B);
		menuItem.addActionListener(this);
		//MouseListener popupListener = new PopupListener();
	    //menuItem.addMouseListener(popupListener);
		
	   //slider
		JOptionPane optionPane = new JOptionPane();
	    
	    JOptionPane optionPane0 = new JOptionPane();
	    JSlider slider0 = getSlider0(optionPane0);
	    /*
	    optionPane.setMessage(new Object[] {"How big of a square?", slider0});
	    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
	    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
	    dialog = optionPane.createDialog(slider0, "My Slider");
	    dialog.setVisible(true);*/
	    
	    
	    
		
		
		
		
		
		/*
		popup = new JPopupMenu();
	    menuItem = new JMenuItem("A popup menu item");
	    menuItem.addActionListener(this);
	    popup.add(menuItem);
	    menuItem = new JMenuItem("Another popup menu item");
	    menuItem.addActionListener(this);
	    popup.add(menuItem); 
	    menu.add(popup);
	    */
	    //menuBar.addMouseListener(popupListener);
	    
		/*
		menuItem = new JMenuItem("pics2");
		menuItem.setMnemonic(KeyEvent.VK_D);
		menuItem.addActionListener(this);
		menu.add(menuItem);

		//a group of radio button menu items
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		rbMenuItem.addActionListener(this);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Another one");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		rbMenuItem.addActionListener(this);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		//a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		cbMenuItem.addItemListener(this);
		menu.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Another one");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		cbMenuItem.addItemListener(this);
		menu.add(cbMenuItem);

		//a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");
		menuItem.addActionListener(this);
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		menuItem.addActionListener(this);
		submenu.add(menuItem);
		
		menu.add(submenu);
		*/
		//Build second menu in the menu bar.
		menuItem = new JMenuItem("Exit");
		menuItem.setMnemonic(KeyEvent.VK_E);
		menuItem.addActionListener(this);
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Exit");
		menuBar.add(menuItem);
		
		this.setJMenuBar(menuBar);
		
	   
	    
	    
	    //change number of possible bombs depending on size of square.
	    JSlider slider = getSlider(optionPane);
	    optionPane.setMessage(new Object[] {"How big of a square?", slider0, "How many bombs?", slider});
	    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
	    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
	    dialog = optionPane.createDialog(slider, "My Slider");
	    dialog.setVisible(true);
	    menu.add(menuItem);
	    int numDivisions;
	    try {
	    	//System.out.println("option pane value is " + optionPane0.getInputValue());
			numDivisions = (int) optionPane0.getInputValue();
		}
		catch (ClassCastException ex)
		{
			numDivisions = 40;
			System.out.println("Setting default size");
		}
	    game.setNumDivisions(numDivisions);
	    ((MineSweeperPanel) panel).setNumDivisions(numDivisions);
	    if (numDivisions == 35)
	    {
	    	setSize(DRAWING_SIZE+5-10, DRAWING_SIZE+83);
	    	((MineSweeperPanel) panel).higher(35);
	    }
	    else if (numDivisions == 25)
	    {
	    	setSize(DRAWING_SIZE+5-20, DRAWING_SIZE+83);
	    	((MineSweeperPanel) panel).higher(25);
	    }
	    else if (numDivisions == 30)
	    {
	    	setSize(DRAWING_SIZE+5-20, DRAWING_SIZE+83);
	    	((MineSweeperPanel) panel).higher(30);
	    }
	    this.add(panel);
		try {
			numFlags = (int) optionPane.getInputValue();
		}
		catch (ClassCastException ex)
		{
			numFlags = 350;
		}
	    game.setNumFlags(numFlags);
	}
	int getNumFlags()
	{
		return numFlags;
	}
	JSlider getSlider(final JOptionPane optionPane) {
	    JSlider slider = new JSlider();
	    slider.setMinimum(50);
	    slider.setMaximum(350);
	    slider.setMajorTickSpacing(50);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    slider.setSnapToTicks(true);
	    slider.setValue(350);
	    ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	        JSlider theSlider = (JSlider) changeEvent.getSource();
	        if (!theSlider.getValueIsAdjusting()) {
	          optionPane.setInputValue(new Integer(theSlider.getValue()));
	        }
	      }
	    };
	    slider.addChangeListener(changeListener);
	    return slider;
	 }
	JSlider getSlider0(final JOptionPane optionPane) {
	    JSlider slider = new JSlider();
	    slider.setMinimum(20);
	    slider.setMaximum(40);
	    slider.setMajorTickSpacing(5);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    slider.setSnapToTicks(true);
	    slider.setValue(40);
	    ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	        JSlider theSlider = (JSlider) changeEvent.getSource();
	        if (!theSlider.getValueIsAdjusting()) {
	          optionPane.setInputValue(new Integer(theSlider.getValue()));
	        }
	      }
	    };
	    slider.addChangeListener(changeListener);
	    return slider;
	  }
	/*class PopupListener extends MouseAdapter implements MouseListener{
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
        	System.out.println("hit the oppup");
            if (e.isPopupTrigger()||true) {
                popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
        }
    }*/
	private JPanel getPanel()
	{
		return panel;
	}
	public static void main(String[] args)
	{
		new MineSweeperFrame().setVisible(true);
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("hit");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("New Game"))
		{
			game.clear();
			panel.repaint();
		}
		else if (e.getActionCommand().equals("Set Bombs"))
		{
			JOptionPane optionPane = new JOptionPane();
		    JSlider slider = getSlider(optionPane);
		    optionPane.setMessage(new Object[] {"How many bombs?", slider});
		    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		    dialog = optionPane.createDialog(slider, "My Slider");
		    dialog.setVisible(true);
		    try {
				numFlags = (int) optionPane.getInputValue();
			}
			catch (Exception ex)
			{
				numFlags = 350;
			}
		    game.setNumFlags(numFlags);
		    game.clear();
		    ((MineSweeperPanel) panel).reset();
		    panel.repaint();
		}
		else if (e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
	}
}
