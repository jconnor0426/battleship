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


class gameboard extends JPanel{
	JButton[][] button_array1 = new JButton[10][10];
	JButton[][] button_array2 = new JButton[10][10];
	String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	JPanel panelEast = new JPanel(new BorderLayout());
	JPanel panelWest = new JPanel(new BorderLayout());
	JPanel mainPanel = new JPanel(new BorderLayout());

	public gameboard(){
		createPanel();
	}

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
	class MyButton extends JButton{
		private int row, column;
		private JButton button;

		public MyButton(JButton bn, int x, int y){
			button = bn;
			column = x;
			row = y;
		}

		public int getRow(){
			return row;
		}

		public int getColumn(){
			return column;
		}

		public JButton getButton(){
			return button;
		}
	}

	// Left Board
	class BoardOne extends JPanel implements ActionListener{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setPreferredSize(new Dimension(200,200));
			GridLayout glayout = new GridLayout(10,10);
			setLayout(glayout);

			for (int i = 0; i < 10; i++){
				for (int j = 0; j < 10; j++){
					// New instance of MyButton
					button_array1[i][j] = new MyButton(new JButton(), i, j);
					button_array1[i][j].addActionListener(this);
					add(button_array1[i][j]);
				}
			}
		}

		public void actionPerformed(ActionEvent e){
			MyButton button = (MyButton) e.getSource();
			System.out.println(button.getColumn() + ":" + letters[button.getRow()]);
		}
	}

	// Right board
	class BoardTwo extends JPanel implements ActionListener{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setPreferredSize(new Dimension(200,200));
			GridLayout glayout = new GridLayout(10,10);
			setLayout(glayout);

			for (int i = 0; i < 10; i++){
				for (int j = 0; j < 10; j++){
					// New instance of MyButton
					button_array2[i][j] = new MyButton(new JButton(), i, j);
					button_array2[i][j].addActionListener(this);
					add(button_array2[i][j]);
				}
			}
		}

		public void actionPerformed(ActionEvent e){
			MyButton button = (MyButton) e.getSource();
			System.out.println(button.getColumn() + ":" + letters[button.getRow()]);
		}
	}
}

