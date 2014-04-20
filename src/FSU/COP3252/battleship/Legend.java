import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.*;


public class Legend extends JPanel 
{
   Destroyer destroyer = new Destroyer();
   Carrier carrier = new Carrier();
   PatrolBoat patrolboat = new PatrolBoat();
   Submarine submarine = new Submarine();
   bs battlesh = new bs();

   public void paintComponent(Graphics g){
      this.setPreferredSize(new Dimension(150,150));
      setBackground( Color.WHITE );
      g.setColor( destroyer.getColor());
      g.fillRect( 10, 0, 10, 10 );
      g.setColor( carrier.getColor());
      g.fillRect( 10, 20, 10, 10 );
      g.setColor( patrolboat.getColor());
      g.fillRect( 10, 40, 10, 10 );
      g.setColor( submarine.getColor());
      g.fillRect( 10, 60, 10, 10 );
      g.setColor( battlesh.getColor());
      g.fillRect( 10, 80, 10, 10 );

      g.setColor(Color.BLACK);
      g.drawString( "Destroyer" , 40, 10 );
      g.drawString( "Carrier"   , 40, 30 );
      g.drawString( "PatrolBoat", 40, 50 );
      g.drawString( "Submarine" , 40, 70 );
      g.drawString( "BattleShip", 40, 90 );
   }
}