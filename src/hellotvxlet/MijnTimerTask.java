/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;

/**
 *
 * @author student
 */
public class MijnTimerTask extends TimerTask {

    HelloTVXlet xl;
    public void setCB(HelloTVXlet xl)
    
    {
        this.xl=xl;
    }
    public void run() {
       xl.callback();
    }

}
