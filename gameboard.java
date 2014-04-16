import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.Vector;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class gameboard extends JFrame{
	JPanel panelEast = new JPanel(new BorderLayout());
	JPanel panelWest = new JPanel(new BorderLayout());
	JPanel mainPanel = new JPanel(new BorderLayout());

	public static void main(String[] args){
		gameboard g1 = new gameboard();
		g1.createFrame();
	}

	public void createFrame(){
		panelEast.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		PlayerBoard board1 = new PlayerBoard();
		PlayerBoard board2 = new PlayerBoard();
		panelEast.add(board1);
		panelWest.add(board2);
		mainPanel.add(panelWest, BorderLayout.WEST);
		mainPanel.add(panelEast, BorderLayout.EAST);
		add(mainPanel);
		setVisible(true);
		setResizable(false);
		repaint();
	}
}


class PlayerBoard extends JPanel{
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setPreferredSize(new Dimension(200,200));
		GridLayout glayout = new GridLayout(10,10);
		setLayout(glayout);

		for (int i = 1; i < 101; i++){
			add(new JButton());
		}
	}
}
















