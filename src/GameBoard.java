

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
	private String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	private JPanel panelEast = new JPanel(new BorderLayout());
	private JPanel panelWest = new JPanel(new BorderLayout());
	private JPanel mainPanel = new JPanel(new BorderLayout());
	private static int players = 2;
	private static int boardSize = 10;
    private ArrayList<Integer> hits1 ;
    private ArrayList<Integer> misses1 ;
    private ArrayList<Integer> hits2 ;
    private ArrayList<Integer> misses2 ;
	

	public GameBoard()
	{
	    createPanel();
	}

	// Sets up frame and adds panels
	public void createPanel(){
		panelEast.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		setSize(500,500);
		JPanel board1 = createBoardOne();
		JPanel board2 = createBoardTwo();
		panelEast.add(board1);
		panelWest.add(board2);
		mainPanel.add(panelEast, BorderLayout.EAST);
		mainPanel.add(panelWest, BorderLayout.WEST);
		add(mainPanel);
	}

	// Stores button and x,y coordinates

	// Left Board
	public JPanel createBoardOne(){
		JPanel panelOne = new JPanel();
		panelOne.setPreferredSize(new Dimension(200,200));
		GridLayout glayout = new GridLayout(11,11);
		panelOne.setLayout(glayout);

		for (int i = 0; i < 11; i++){
			if (i == 0){
				JButton default_button = new JButton("");
				default_button.setEnabled(false);
				default_button.setMargin(new Insets(0, 0, 0, 0));
				panelOne.add(default_button);
			}
			else{
				JButton letter_button = new JButton(letters[i-1]);
				letter_button.setEnabled(false);
				letter_button.setMargin(new Insets(0, 0, 0, 0));
				panelOne.add(letter_button);
			}
			for (int j = 0; j < 10; j++){
				if (i > 0){
					// New instance of MyButton
					button_array1[i-1][j] = new MyButton(i-1, j);
					//button_array1[i-1][j].addActionListener(this);
					panelOne.add(button_array1[i-1][j]);
				}
				else{
					String temp = Integer.toString(j);
					JButton number_button = new JButton(temp);
					number_button.setEnabled(false);
					number_button.setMargin(new Insets(0, 0, 0, 0));
					panelOne.add(number_button);
				}
			}
		}

		return panelOne;
	}

	// Right board
	public JPanel createBoardTwo(){
		JPanel panelTwo = new JPanel();
		panelTwo.setPreferredSize(new Dimension(200,200));
		GridLayout glayout = new GridLayout(11,11);
		panelTwo.setLayout(glayout);

		for (int i = 0; i < 11; i++){
			if (i == 0){
				JButton default_button = new JButton("");
				default_button.setEnabled(false);
				default_button.setMargin(new Insets(0, 0, 0, 0));
				panelTwo.add(default_button);
			}
			else{
				JButton letter_button = new JButton(letters[i-1]);
				letter_button.setEnabled(false);
				letter_button.setMargin(new Insets(0, 0, 0, 0));
				panelTwo.add(letter_button);
			}
			for (int j = 0; j < 10; j++){
				if (i > 0){
					// New instance of MyButton
					button_array2[i-1][j] = new MyButton(i-1, j);
					//button_array2[i-1][j].addActionListener(this);
					panelTwo.add(button_array2[i-1][j]);
				}
				else{
					String temp = Integer.toString(j);
					JButton number_button = new JButton(temp);
					number_button.setEnabled(false);
					number_button.setMargin(new Insets(0, 0, 0, 0));
					panelTwo.add(number_button);
				}
			}
		}

		return panelTwo;
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
	private boolean occupied, hit;

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

	public boolean getOccupied(){
		return occupied;
	}

	public boolean getHit(){
		return hit;
	}

	public void setOccupied(boolean b){
		occupied = b;
	}

	public void setHit(boolean b){
		hit = b;
	}
}