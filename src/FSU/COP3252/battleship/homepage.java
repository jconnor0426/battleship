

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
import java.util.ArrayList;
import java.awt.TextField;

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
	private GameObject gameObject;

	private Ship[] shipNames = {new Carrier(),
                                    new bs(),
                                    new Submarine(),
                                    new Destroyer(),
                                    new PatrolBoat() };
	private int[] shipSizes = {5, 4, 3, 3, 2};
	private String[] directionNames = {"Horizontal", "Vertical"};

	public JComboBox<Ship> shipsList;
	public JComboBox<String> directionsList;
	private TitledBorder ships, directions;
	JPanel shipOptions;

	private MainPage new_frame;

	private String direction = "";
	private Ship ship;
	private int orientation = 0;
	private ArrayList<Ship> playersShips;

	private MyButton[][] board1;
	private MyButton[][] board2;

	//Deployed button
	private JButton deploy;

	//Table of contents for color of ships
	Legend legend;

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

        board1 = board.getBoard2();
		board2 = board.getBoard1();
                
		gameObject = new HumanVsComputerEasy(board1, board2);

                //Have the CPU player set their ships
                //CPU player is always team 1
                //Human Player is always team 0
                gameObject.cpuInitialize(1);

		deploy = new_frame.getDeploy();
		deploy.setEnabled(false);
		deploy.addActionListener(new DeployListener());

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

	public void addButtonListeners(GameBoard board){

		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				board1[i][j].addActionListener(new ButtonListener());
				board2[i][j].addActionListener(new ButtonListener());
			}
		}
	}

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			MyButton button = (MyButton) ev.getSource();
			if (!deploy.isEnabled()){
				int size = 0;
				direction = (String)directionsList.getSelectedItem();
				ship = (Ship) shipsList.getSelectedItem();

				// Check direction to assign int to orientation
				if (direction.equals("Horizontal")){
					orientation = 0;
				} else if (direction.equals("Vertical")){
					orientation = 1;
				}


				if (gameObject.placeShip(button.getRow(), button.getColumn(), orientation, ship, 0)){
					
					// Repainting the board to default before redrawing the ships
					for (int i = 0; i < 10; i++){
						for (int j = 0; j < 10; j++){
							board1[i][j].setBackground(null);
							board1[i][j].setOpaque(false);
							board1[i][j].setBorderPainted(true);
						}
					}

					playersShips = gameObject.getShipsToDraw(0);
	                                boolean DEBUG = false;
	                                if(DEBUG )
	                                {
	                                    ArrayList<Ship> cpuShips = gameObject.getShipsToDraw(1);

	                                    for (Ship cpuShip : cpuShips) {
	                                        colorButtons(cpuShip, 1);
	                                    }
	                                }
	                                
					if (playersShips.size() == 5){
						deploy.setEnabled(true);
					}

					for (int i = 0; i < playersShips.size(); i++){
						colorButtons(playersShips.get(i), 0);
					}
				}
			} else{
				System.out.println("true");
				gameObject.takeTurn(button.getRow(), button.getColumn(), 0, 1);
			}
			if (gameObject.checkGameOver() == 0){
				JOptionPane.showMessageDialog(null, "Player " + "1 " +
					"has won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void colorButtons(Ship shipToDraw, int team){
		if (team == 0){
			if (shipToDraw.getOrientation() == Ship.HORIZONTAL){
				int row = shipToDraw.getRow();
				int column = shipToDraw.getColumn();
				for (int i = row; i < row + shipToDraw.getSize(); i++){
					board1[column][i].setOccupied(true);
					board1[column][i].setBackground(shipToDraw.getColor());
                    board1[column][i].setOpaque(true);
                    board1[column][i].setBorderPainted(false);
				}
			} else if (shipToDraw.getOrientation() == Ship.VERTICAL){
				int row = shipToDraw.getRow();
				int column = shipToDraw.getColumn();
				for (int i = column; i < column + shipToDraw.getSize(); i++){
					board1[i][row].setOccupied(true);
					board1[i][row].setBackground(shipToDraw.getColor());
                    board1[i][row].setOpaque(true);
                    board1[i][row].setBorderPainted(false);
				}
			}
		} else if (team == 1){
                        if (shipToDraw.getOrientation() == Ship.HORIZONTAL){
				int row = shipToDraw.getRow();
				int column = shipToDraw.getColumn();
				for (int i = row; i < row + shipToDraw.getSize(); i++){
					board2[column][i].setOccupied(true);
					board2[column][i].setBackground(shipToDraw.getColor());
                                        board2[column][i].setOpaque(true);
                                        board2[column][i].setBorderPainted(false);
				}
			} else if (shipToDraw.getOrientation() == Ship.VERTICAL){
				int row = shipToDraw.getRow();
				int column = shipToDraw.getColumn();
				for (int i = column; i < column + shipToDraw.getSize(); i++){
					board2[i][row].setOccupied(true);
					board2[i][row].setBackground(shipToDraw.getColor());
                                        board2[i][row].setOpaque(true);
                                        board2[i][row].setBorderPainted(false);
				}
                        }
                }
	}

	private class DeployListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (deploy.isEnabled()){

				//Removing top labels of frame
				new_frame.removeOptions();
				new_frame.addStatistics();
				new_frame.repaint();

				//Set board to disabled
				for (int i = 0; i < 10; i++){
					for (int j = 0; j < 10; j++){
						board1[i][j].setEnabled(false);
					}
				}
			}
		}
	}
}





















