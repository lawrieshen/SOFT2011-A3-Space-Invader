package invaders.memento;

public class GameScoreMemento {
    private final int gameScore;

    public GameScoreMemento(int gameScore){
        this.gameScore = gameScore;
    }

    public int getGameScore() {
        return gameScore;
    }
}
