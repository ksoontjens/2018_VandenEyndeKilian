/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class Laser extends HComponent 
{
    public boolean mayDraw = false;
    int X, Y, X2, Y2;
    public int kills = 0;
    public ArrayList asteroids;
    
    public Laser()
    {
        this.setBounds(X,Y,150,150);
    }
    
    public void FeedPlayerPosition(int pX, int pY, double pHoek)
    {
        X = pX;
        Y = pY;
        double radHoek = (pHoek / 360.0) * 2 * Math.PI;
        //X2 = X + (int) (100 * (Math.cos(radHoek))); -- code voor de hoek enzo
        //Y2 = Y + (int) (100 * (Math.sin(radHoek)));
        X2 = X + 100;
        Y2 = Y + 100;
        this.repaint();
        //System.out.println(X + " " + Y + " " + X2 + " " + Y2);
        
    }
    
    public void Paint(Graphics g)
    {
        System.out.println("line draw");
        g.setColor(Color.WHITE);
        g.drawLine(X, Y, X2, Y2); // --> TEKENT NIETS
        
        for(int i = 0; i < asteroids.size(); i++)
        {
            Enemy e = (Enemy)asteroids.get(i);
            if(this.getBounds().intersects(e.getBounds()))
            {
                //we have a collision
                asteroids.remove(i);
                kills++;
            }
        }
    }
}


