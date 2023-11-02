package invaders.observer;

import javafx.util.Duration;

public class SystemStats implements Observer{
    //Observer States
    private Duration time;
    private int score;
    //declare subject
    private GameTime gameTime;
    private GameScore gameScore;

    public SystemStats(GameTime gameTime, GameScore gameScore){
        this.gameTime = gameTime;
        this.gameScore = gameScore;
    }

    public Duration getGameTime(){return time;}
    public int getGameScore(){return  score;}

    @Override
    public void update() {
        time = gameTime.getGameTime();
        score = gameScore.getGameScore();
    }
}
