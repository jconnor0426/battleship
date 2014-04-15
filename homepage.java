import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
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


public class homepage extends JFrame{
	JButton b1;
	JLabel l1;	
	URL url;
	JLabel picture;
	public static void main(String[] args){
		homepage page = new homepage();
		page.create();
	}

	public void create(){
		try{
			url = new URL("https://i.imgur.com/62hTev9.gif");
		} catch (Exception e){
			e.printStackTrace();
		}
		setLayout(new BorderLayout());
		try{
	    	Image image = new Image(new ImageIcon(ImageIO.read(url)));
	   	} catch (Exception e){
			e.printStackTrace();
		}

	    setLayout(new FlowLayout());
	    l1=new JLabel("Here is a button");
	    b1=new JButton("I am a button");
	    add(l1);
	    add(b1);
	    // Just for refresh :) Not optional!
	    setSize(399,399);
	    setSize(400,400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    repaint();
	}

	public void paintComponent(Graphics g){
		add(pictue);
	}
}