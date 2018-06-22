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
public class Enemy extends HComponent
{
    private Image image;
    private MediaTracker mtrack;
    private int Width;
    private int Height;
    private int X;
    private int Y;
    private int textureIndex = 0;
    private DoublePoint Richting;
    ArrayList asteroidTextures = new ArrayList();
    //Random r = new Random();
    
    public int GetUsedTexture()
    {
        return textureIndex;
    }
    
    public void SetPosition(int px, int py)
    {
        X = px;
        Y = py;
    }
    
    public Enemy(Random pr,int x, int y, int width, int height, DoublePoint richting)
    {
        Richting = richting;
        //Add all the different textures
        asteroidTextures.add(new String("Asteroid classic 1.png"));
        asteroidTextures.add(new String("Asteroid classic 2.png"));
        asteroidTextures.add(new String("Asteroid classic 3.png"));
        asteroidTextures.add(new String("Asteroid classic 4.png"));
        asteroidTextures.add(new String("Asteroid classic 5.png"));
        
        int usedTexture = pr.nextInt(5);
        String texture = (String)asteroidTextures.get(usedTexture);
        textureIndex = usedTexture;
        image = this.getToolkit().getImage(texture);
        mtrack = new MediaTracker(this);
        mtrack.addImage(image,0);
        Width = width;
        Height = height;
        X = x;
        Y = y;
        
        try
        {
            mtrack.waitForAll();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
        //this.setBounds(x,y,image.getWidth(null),image.getWidth(null));
        this.setBounds(x,y,Width,Height);
    }
    
    public void Update()
    {
        if(X >= 710)
        {
            X -= 710;
        }
        if(X <= 5)
        {
            X += 710;
        }
        
        if(Y >= 565)
        {
            Y -= 565;
        }
        if(Y <= 5)
        {
            Y += 565;
        }
        double radhoek=(Richting.x/360.0)*2*Math.PI;
        X +=(Richting.y*Math.cos(radhoek));
        Y +=(Richting.y*Math.sin(radhoek));
        
        this.setBounds(X,Y,Width,Height);
        this.repaint();
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(image,0,0,Width,Height,null);
    }
}
