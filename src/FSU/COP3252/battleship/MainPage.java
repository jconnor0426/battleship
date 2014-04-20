
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
import java.awt.TextField;

class MainPage extends JFrame{
	private JMenuBar menuBar;
	private JMenu gameMenu, helpMenu;
	private JMenuItem pvp, pvc, cvc;

	private Ship[] shipNames = {new Carrier(),
                                    new bs(),
                                    new Submarine(),
                                    new Destroyer(),
                                    new PatrolBoat() };
	private String[] directionNames = {"Horizontal", "Vertical"};
	private JButton deploy;
	private JComboBox<Ship> shipsList = new JComboBox<Ship>(shipNames);
	private JComboBox<String> directionsList = new JComboBox<String>(directionNames);
	private TitledBorder ships, directions;
	JPanel shipOptions;
	JPanel southPanel;
	Legend legend;
	JPanel statsPanel, statsTracker;
	JLabel hits, misses, shipsSunk;
	TextField numberHits, numberMisses, numberShipsSunk;



	public MainPage(){
		addMenu();
		optionMenus();
		addsouthPanel();
	}

	public void addMenu(){
		menuBar = new JMenuBar();
		gameMenu = new JMenu("Game");
		JMenu newGame = new JMenu("New Game");
		pvp = new JMenuItem("Player vs. Player");
		pvc = new JMenuItem("Player vs. Computer");
		cvc = new JMenuItem("Computer vs. Computer");

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				dispose();
			}
		});

		helpMenu = new JMenu("Help");
		JMenuItem help = new JMenuItem("View Help");
		help.addActionListener(new HelpListener());

		newGame.add(pvp);
		newGame.add(pvc);
		newGame.add(cvc);

		gameMenu.add(newGame);
		gameMenu.add(exit);

		helpMenu.add(help);

		menuBar.add(gameMenu);
		menuBar.add(helpMenu);

		setJMenuBar(menuBar);
	}

	public void optionMenus(){
		deploy = new JButton("Place Ships!");
		deploy.setEnabled(false);

		ships = BorderFactory.createTitledBorder("Ships");
		shipsList.setBorder(ships);	
		directions = BorderFactory.createTitledBorder("Directions");
		directionsList.setBorder(directions);

		shipOptions = new JPanel();
		shipOptions.setLayout(new FlowLayout());
		shipOptions.add(shipsList);
		shipOptions.add(directionsList);
		shipOptions.add(deploy);

		add(shipOptions, BorderLayout.NORTH);
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

	public void addStatistics(){
		statsPanel = new JPanel(new GridLayout(3,2));
		hits            = new JLabel("Hits");
		misses          = new JLabel("Misses");
		shipsSunk       = new JLabel("Ships Sunk   ");
		numberHits      = new TextField("0", 2);
		numberMisses    = new TextField("0", 2);
		numberShipsSunk = new TextField("0", 2);
		numberHits.setEditable(false);
		numberMisses.setEditable(false);
		numberShipsSunk.setEditable(false);
		statsTracker = new JPanel(new GridLayout(3, 2));
		statsTracker.add(hits);
		statsTracker.add(numberHits);
		statsTracker.add(misses);
		statsTracker.add(numberMisses);
		statsTracker.add(shipsSunk);
		statsTracker.add(numberShipsSunk);
		southPanel.add(statsTracker, BorderLayout.EAST);
	}

	public void addsouthPanel(){
		legend = new Legend();
		southPanel = new JPanel(new BorderLayout());
		southPanel.setPreferredSize(new Dimension(100,100));
		addStatistics();
		southPanel.add(legend, BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);
	}

	public JButton getDeploy(){
		return deploy;
	}

	public JComboBox<Ship> getShipList(){
		return shipsList;
	}

	public JComboBox<String> getDirectionList(){
		return directionsList;
	}

	public void removeOptions(){
		getContentPane().remove(shipOptions);
	}
}