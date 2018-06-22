/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class Playfield extends HComponent implements UserEventListener {

    HelloTVXlet master;
    Laser laser;
    ArrayList slang=new ArrayList();
    private double playerSpeed = 4.5;
    private double rotationSpeed = 15;
    private int playerX = 360;
    private int playerY = 268;
    double hx=32;
    double hy=0;
    double hoek=0;
    double gr=25;
    public ArrayList asteroids;
    public GameOverScreen gos;

    Random r=new Random();
    
    private boolean isMovingForwards = true;
    
    public Playfield(Laser pLaser, HelloTVXlet pMaster)
    {
        master = pMaster;
        laser = pLaser;
        this.setBounds(playerX,playerY,64,64);
    }
   
    public void paint(Graphics g)
    {
        if(playerX >= 710)
        {
            playerX -= 710;
        }
        if(playerX <= 5)
        {
            playerX += 710;
        }
        
        if(playerY >= 565)
        {
            playerY -= 565;
        }
        if(playerY <= 5)
        {
            playerY += 565;
        }
        
        laser.asteroids = asteroids;
        
        for(int i = 0; i < asteroids.size(); i++)
        {
            Enemy e = (Enemy)asteroids.get(i);
            if(this.getBounds().intersects(e.getBounds()))
            {
                //we have a collision
                //show the gameoverscreen
                gos.mayDraw = true;
                master.ResetAsteroids();
            }
        }
        
        //System.out.println(playerX + "," + playerY);
        this.setBounds(playerX,playerY,64,64);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,64,64);
     
        g.setColor(Color.WHITE);
        double radhoek=(hoek/360.0)*2*Math.PI;
        double hoek2=hoek+200;
        double radhoek2=(hoek2/360.0)*2*Math.PI;
        double hoek3=hoek-200;
        double radhoek3=(hoek3/360.0)*2*Math.PI;
                
        int x[]=new int[3];
        int y[]=new int[3];
        x[0]=(int)(hx+gr*Math.cos(radhoek));
        x[1]=(int)(hx+gr*Math.cos(radhoek2));
        x[2]=(int)(hx+gr*Math.cos(radhoek3));
                
        y[0]=(int)(hx+gr*Math.sin(radhoek));
        y[1]=(int)(hx+gr*Math.sin(radhoek2));
        y[2]=(int)(hx+gr*Math.sin(radhoek3));
    
                
        g.drawPolygon(x, y, 3);
    }
    
    public void run()
    {
        this.repaint();
        laser.FeedPlayerPosition(playerX, playerY, hoek);
    }
    public void userEventReceived(UserEvent e) {
      if (e.getType()==HRcEvent.KEY_PRESSED)
      {
          if(e.getCode() == HRcEvent.VK_NUMPAD0)
          {
              laser.mayDraw = true;
          }
          
          if(e.getCode() == HRcEvent.VK_UP)
          {/*
              if(!isMovingForwards)
              {
                  hoek += 180;
              }*/
              double radHoek = (hoek / 360.0) * 2 * Math.PI;
              playerX += playerSpeed * (Math.cos(radHoek));
              playerY += playerSpeed * (Math.sin(radHoek));
              isMovingForwards = true;
          }
          /*
          if(e.getCode() == HRcEvent.VK_DOWN)
          {
              if(isMovingForwards)
              {
                  hoek -= 180;
              }
              double radHoek = (hoek / 360.0) * 2 * Math.PI;
              playerX -= playerSpeed * (Math.cos(radHoek));
              playerY -= playerSpeed * (Math.sin(radHoek));
              isMovingForwards = false;
          }*/
          
          if (e.getCode()==HRcEvent.VK_LEFT)
          {
              hoek -= rotationSpeed;
              this.repaint();
          }

          if (e.getCode()==HRcEvent.VK_RIGHT)
          {
              hoek += rotationSpeed;
              this.repaint();
          }
      }
    }
}

