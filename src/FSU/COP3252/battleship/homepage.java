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

public class homepage{
	private JFrame frame = new JFrame("Home Screen");
	private JButton b1, b2, b3, b4;
	private URL url;
	private JPanel panel = new JPanel();
	private JLabel title = new JLabel("BattleShip", JLabel.CENTER);
	private Font font = new Font("Verdana", Font.BOLD, 40);

	private JMenuBar menuBar;
	private JMenu gameMenu, helpMenu;
	private JMenuItem pvp, pvc, cvc, red, blue, green, yellow;

	private gameboard board;

	private String[] shipNames = {"carrier", "battleship", "submarine", "destroyer", "patrol boat"};
	private String[] directionNames = {"Horizontal", "Vertical"};
	private JButton deploy;
	private JComboBox<String> shipsList = new JComboBox<String>(shipNames);
	private JComboBox<String> directionsList = new JComboBox<String>(directionNames);
	private TitledBorder ships, directions;
	JPanel shipOptions;

	public static void main(String[] args){
		homepage page = new homepage();
		page.create();
	}

	public void create(){
		try{
			url = new URL("http://hdwallpaper.freehdw.com/0004/3d-abstract" + 
			"_hdwallpaper_battleship_36265.jpg");
		} catch (Exception e){
			e.printStackTrace();
		}

		try{
	    	frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(url))));
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
		board = new gameboard();
		frame = new MainPage();

		frame.add(board, BorderLayout.CENTER);
	   	frame.setSize(500,500);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.setResizable(false);
		frame.repaint();
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
	}

	public void addButtonListeners(MyButton[][] array){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				array[i][j].addActionListener(new ButtonListener());
			}
		}
	}

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			
		}
	}
}





















