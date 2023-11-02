package invaders.memento;

import javafx.util.Duration;

public class SystemStatsMemento {
    private Duration gameTime;
    private int gameScore;

    public SystemStatsMemento(Duration gameTime, int gameScore){
        this.gameTime = gameTime;
        this.gameScore =gameScore;
    }

    public Duration getGameTime() {
        return gameTime;
    }

    public int getGameScore() {
        return gameScore;
    }
}
