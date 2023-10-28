package invaders.observer;

import javafx.util.Duration;

public class SystemStats implements Observer{
    //Observer States
    private Duration time;
    private int score;
    //declare subject
    private GameTime gameTime;

    public SystemStats(GameTime gameTime){
        this.gameTime = gameTime;
    }

    public Duration getGameTime(){return time;}

    @Override
    public void update() {
        time = gameTime.getGameTime();
        //System.out.println(time.toSeconds());
    }

}
