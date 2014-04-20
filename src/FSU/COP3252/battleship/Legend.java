import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;

public class Legend extends JPanel 
{
   public void paintComponent(Graphics g){
      this.setPreferredSize(new Dimension(100,100));
      this.setBackground( Color.WHITE );
      g.setColor( new Color( 255, 0, 0 ) );
      g.fillRect( 0, 0, 10, 10 );
      g.setColor( new Color( 255, 0, 0 ) );
      g.fillRect( 0, 20, 10, 10 );
      g.setColor( new Color( 255, 0, 0 ) );
      g.fillRect( 0, 40, 10, 10 );
      g.setColor( new Color( 255, 0, 0 ) );
      g.fillRect( 0, 60, 10, 10 );
      g.setColor( new Color( 255, 0, 0 ) );
      g.fillRect( 0, 80, 10, 10 );
      g.drawString( "Current RGB: " + g.getColor(), 30, 10 );
      g.drawString( "Current RGB: " + g.getColor(), 30, 30 );
      g.drawString( "Current RGB: " + g.getColor(), 30, 50 );
      g.drawString( "Current RGB: " + g.getColor(), 30, 70 );
   }
}