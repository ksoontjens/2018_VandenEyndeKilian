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
public class GameOverScreen extends HComponent
{
    public boolean mayDraw = false;
    private Image image;
    private MediaTracker mtrack;
    
    public GameOverScreen()
    {
        this.setBounds(0,0,720,576);
        image = this.getToolkit().getImage("gameover.png");
        mtrack = new MediaTracker(this);
        mtrack.addImage(image,0);
    }
    public void paint(Graphics g)
    {
        if(mayDraw)
        {/*
            g.setColor(Color.RED);
            g.fillRect(0,0,720,576);
            g.drawRect(0, 0, 720, 576);
            g.drawChars(new char[] {'G','A','M','E','O','V','E','R'}, 100, 100, 150, 35);*/
            
            g.drawImage(image,0,0,720,576,null);
        }
    }
}
