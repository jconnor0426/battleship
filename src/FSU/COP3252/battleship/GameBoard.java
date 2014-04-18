package FSU.COP3252.battleship;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.Vector;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Insets;


class GameBoard extends JPanel{
	public MyButton[][] button_array1 = new MyButton[10][10];
	public MyButton[][] button_array2 = new MyButton[10][10];
	public String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	public JPanel panelEast = new JPanel(new BorderLayout());
	public JPanel panelWest = new JPanel(new BorderLayout());
	public JPanel mainPanel = new JPanel(new BorderLayout());
	public static int players = 2;
	public static int boardSize = 10;
	private ArrayList< Ship > playerShips1 ;
    private ArrayList<Integer> hits1 ;
    private ArrayList<Integer> misses1 ;
	private ArrayList< Ship > playerShips2 ;
    private ArrayList<Integer> hits2 ;
    private ArrayList<Integer> misses2 ;
	

	public GameBoard()
	{
	    for( int i = 0; i < players; i++ ) {
			playerShips1 = new ArrayList< Ship >(5);
            hits1 = new ArrayList<Integer>(100);
            misses1 = new ArrayList<Integer>(100);
            playerShips2 = new ArrayList< Ship >(5);
            hits2 = new ArrayList<Integer>(100);
            misses2 = new ArrayList<Integer>(100);
	    }
	    createPanel();
	}

    /*public boolean placeShip( int team, Ship toPlace  )
    {
        if( validShipLocation( team, toPlace ) )
                playerShips[team].add(toPlace);
        
        return true;
    }

	public boolean makeMove( int x, int y, char team)
	{
		if( x > boardSize || x < 0 )
			return false;
		if( y > boardSize || y < 0 )
			return false;
		if( board[x][y] != ' ') 
			return false;
			
		board[x][y] = team;
		return true;
	}

    private boolean validShipLocation(int team, Ship toPlace) {
        
        return true;
    }*/

	// Sets up frame and adds panels
	public void createPanel(){
		panelEast.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		setSize(500,500);
		BoardOne board1 = new BoardOne();
		BoardTwo board2 = new BoardTwo();
		panelEast.add(board1);
		panelWest.add(board2);
		mainPanel.add(panelEast, BorderLayout.EAST);
		mainPanel.add(panelWest, BorderLayout.WEST);
		add(mainPanel);
	}

	// Stores button and x,y coordinates

	// Left Board
	class BoardOne extends JPanel implements ActionListener{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setPreferredSize(new Dimension(200,200));
			GridLayout glayout = new GridLayout(11,10);
			setLayout(glayout);

			for (int i = 0; i < 11; i++){
				if (i == 0){
					JButton default_button = new JButton("");
					default_button.setEnabled(false);
					default_button.setMargin(new Insets(0, 0, 0, 0));
					add(default_button);
				}
				else{
					JButton letter_button = new JButton(letters[i-1]);
					letter_button.setEnabled(false);
					letter_button.setMargin(new Insets(0, 0, 0, 0));
					add(letter_button);
				}
				for (int j = 0; j < 10; j++){
					if (i > 0){
						// New instance of MyButton
						button_array1[i-1][j] = new MyButton(i-1, j);
						button_array1[i-1][j].addActionListener(this);
						add(button_array1[i-1][j]);
					}
					else{
						String temp = Integer.toString(j);
						JButton number_button = new JButton(temp);
						number_button.setEnabled(false);
						number_button.setMargin(new Insets(0, 0, 0, 0));
						add(number_button);
					}
				}

			}
		}

		public void actionPerformed(ActionEvent e){
			MyButton button = (MyButton) e.getSource();
			System.out.println("Board1 - " + button.getColumn() + ":" + letters[button.getRow()]);
			button.setBackground(Color.BLACK);
		}
	}

	// Right board
	class BoardTwo extends JPanel implements ActionListener{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setPreferredSize(new Dimension(200,200));
			GridLayout glayout = new GridLayout(11,11);
			setLayout(glayout);

			for (int i = 0; i < 11; i++){
				if (i == 0){
					JButton default_button = new JButton("");
					default_button.setEnabled(false);
					default_button.setMargin(new Insets(0, 0, 0, 0));
					add(default_button);
				}
				else{
					JButton letter_button = new JButton(letters[i-1]);
					letter_button.setEnabled(false);
					letter_button.setMargin(new Insets(0, 0, 0, 0));
					add(letter_button);
				}
				for (int j = 0; j < 10; j++){
					if (i > 0){
						// New instance of MyButton
						button_array2[i-1][j] = new MyButton(i-1, j);
						button_array2[i-1][j].addActionListener(this);
						add(button_array2[i-1][j]);
					}
					else{
						String temp = Integer.toString(j);
						JButton number_button = new JButton(temp);
						number_button.setEnabled(false);
						number_button.setMargin(new Insets(0, 0, 0, 0));
						add(number_button);
					}
				}
			}
		}

		public void actionPerformed(ActionEvent e){
			MyButton button = (MyButton) e.getSource();
			System.out.println("Board2 - " + button.getColumn() + ":" + letters[button.getRow()]);
			button.setBackground(Color.BLACK);
		}
	}

	public MyButton[][] getBoard1(){
		return button_array1;
	}

	public MyButton[][] getBoard2(){
		return button_array2;
	}
}

class MyButton extends JButton{
	private int row, column;

	public MyButton(int x, int y){
		column = x;
		row = y;
	}

	public int getRow(){
		return row;
	}

	public int getColumn(){
		return column;
	}
}