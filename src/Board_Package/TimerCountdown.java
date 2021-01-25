package Board_Package;

import java.util.Timer;
import java.util.TimerTask;

import Score_Package.GameScore;

public class TimerCountdown  {
	 
    public Timer timerCountDown1() {
    	//GameScore gameScore = new GameScore();
        Timer timer = new Timer(); 
        TimerTask task = new HelperForTimer();
        timer.schedule(task, 1000, 1000);
        //gameScore.setTimer(timer);
        return timer;
        
    }

    
 
 
}

