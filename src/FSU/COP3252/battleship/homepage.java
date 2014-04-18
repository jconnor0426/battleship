package FSU.COP3252.battleship;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.Vector;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.SwingUtilities; 
import java.io.File;

public class homepage{
	private JFrame frame = new JFrame("Home Screen");
	private JButton b1, b2, b3, b4;
	private URL url;
	private JPanel panel = new JPanel();
	private JLabel title = new JLabel("BattleShip", JLabel.CENTER);
	private Font font = new Font("Verdana", Font.BOLD, 40);
	private String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

	private JMenuBar menuBar;
	private JMenu gameMenu, helpMenu;
	private JMenuItem pvp, pvc, cvc, red, blue, green, yellow;

	private GameBoard board;

	private String[] shipNames = {"carrier", "battleship", "submarine", "destroyer", "patrol boat"};
	private int[] shipSizes = {5, 4, 3, 3, 2};
	private String[] directionNames = {"Horizontal", "Vertical"};

	public JComboBox<String> shipsList;
	public JComboBox<String> directionsList;
	private TitledBorder ships, directions;
	JPanel shipOptions;

	private MainPage new_frame;

	private String direction = "";
	private String ship = "";

	public static void main(String[] args){
		homepage page = new homepage();
		page.create();
	}

	public void create(){
		try{
	    	frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read
	    		(new File("battleship.jpg")))));
	   	} catch (Exception e){
			e.printStackTrace();
		}

		frame.setLayout(new BorderLayout());

	    b1 = new JButton("Player vs. Player");
	    b2 = new JButton("Player vs. Computer");
	    b3 = new JButton("Computer vs. Computer");
	    b4 = new JButton("Rules of Battleship");

	    panel.setPreferredSize(new Dimension(20,75));
	    panel.setLayout(new GridLayout(4,1));
	    panel.add(b1);
	    panel.add(b2);
	    panel.add(b3);
	    panel.add(b4);

	    title.setFont(font);
		title.setForeground(new Color(10,150,250));

	    frame.add(panel, BorderLayout.SOUTH);
	    frame.add(title, BorderLayout.NORTH);

	   	frame.setSize(500,500);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.setResizable(false);

	    // Player vs. Player
	    b1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
				frame.remove(title);
				frame.remove(panel);
				frame.setVisible(false);
				frame.dispose();

				boardFrame();
			}
		});


	    // Player vs. Computer
	   	b2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){

	    	}
	    });

	   	// Computer vs. Computer
	    b3.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){

	    	}
	    });

	    // Rules
	    b4.addActionListener(new HelpListener());
	}


	// --------------- Now in new frame ---------------------------------
	public void boardFrame(){
		board = new GameBoard();
		frame = new MainPage();
		new_frame = (MainPage) frame;
		shipsList = new_frame.getShipList();
		directionsList = new_frame.getDirectionList();

		new_frame.add(board, BorderLayout.CENTER);
	   	new_frame.setSize(500,500);
	    new_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    new_frame.setVisible(true);
	    new_frame.setResizable(false);
		new_frame.repaint();
		addButtonListeners(board);
	}

	private class HelpListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String message = "Players take turns firing a shot to attack enemy ships.\n" +
    		"On your turn, call out a letter and a number of a row and column on the \n" + 
    		"grid. Your opponent checks that space on their lower grid, and says \n" + 
    		"\"miss\" if there are no ships there, or boat are hit, the player loses\n" + 
    		"that boat. Once a player loses all thier boats, they have lost and the \n" + 
    		"other player has won.";
    		JOptionPane.showMessageDialog(null, message, "Rules of Battleship",
    		JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void paintComponent(Graphics g){
		frame.repaint();
		new_frame.repaint();
	}

	public void addButtonListeners(GameBoard board){
		MyButton[][] board2 = board.getBoard2();
		MyButton[][] board1 = board.getBoard1();
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				board2[i][j].addActionListener(new ButtonListener());
				board1[i][j].addActionListener(new ButtonListener());
			}
		}
	}

	public void checkBounds(int i, int j, String dir, int size){
		if (dir.equals("Horizontal")){
			if ((i + size) > 10){
				JOptionPane.showMessageDialog(null, "Bad Placement", "Bad", 
					JOptionPane.ERROR_MESSAGE);
			}
		} else if (dir.equals("Vertical")){
			if ((j+ size) > 10){
				JOptionPane.showMessageDialog(null, "Bad Placement", "Bad", 
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			int size = 0;
			boolean enabled = new_frame.getDeploy();
			direction = (String)directionsList.getSelectedItem();
			ship = (String)shipsList.getSelectedItem();

			MyButton button = (MyButton) ev.getSource();
			if (enabled){
				System.out.println("Board - " + button.getColumn() + ":" + letters[button.getRow()]);
				button.setBackground(Color.BLACK);
			}
			else{
				for (int i = 0; i < shipNames.length; i++){
					if (ship == shipNames[i]){
						size = shipSizes[i];
					}
				}
				if (direction.equals("Horizontal")){
					button.setBackground(Color.BLACK);
					checkBounds(button.getRow(), button.getColumn(), direction, size);
				}
				else{
					System.out.println("Vertical");
					button.setBackground(Color.BLACK);
					checkBounds(button.getRow(), button.getColumn(), direction, size);
				}
			}
		}
	}
}





















