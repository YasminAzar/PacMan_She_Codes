package Board_Package;

import java.util.Timer; 
import java.util.TimerTask;

import Score_Package.GameScore; 
  
public class HelperForTimer extends TimerTask 
{ 
	//GameScore gameScore = new GameScore();
    public static int i = 15; 
    public void run() 
    { 
        System.out.println("Timer ran " + i--);
    	
        if(i == -1) {
        this.cancel();
        i = 15;
        }    
    } 
} 