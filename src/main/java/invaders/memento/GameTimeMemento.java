package invaders.memento;

import javafx.util.Duration;

public class GameTimeMemento {
    private final Duration gameTime;

    public GameTimeMemento(Duration gameTime){
        this.gameTime = gameTime;
    }

    public Duration getGameTime() {
        return gameTime;
    }
}
