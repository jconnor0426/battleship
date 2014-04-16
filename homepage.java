import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
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

public class homepage{
	JFrame frame = new JFrame("Home Screen");
	JButton b1, b2, b3, b4;
	URL url;
	JPanel panel = new JPanel();
	JLabel title = new JLabel("BattleShip", JLabel.CENTER);
	Font font = new Font("Verdana", Font.BOLD, 40);

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
				gameboard board = new gameboard();
				frame.setVisible(false);
				frame.dispose();

				frame = new JFrame("Game");
				frame.add(board, BorderLayout.NORTH);
			   	frame.setSize(500,500);
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setVisible(true);
			    frame.setResizable(false);
				frame.repaint();
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
	    b4.addActionListener(new ActionListener(){
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
	    });
	}

	public void paintComponent(Graphics g){
		frame.repaint();
	}
}