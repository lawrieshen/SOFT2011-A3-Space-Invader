package invaders.memento;

import invaders.observer.GameScore;
import invaders.observer.GameTime;

import java.util.ArrayDeque;
import java.util.Deque;

public class GameScoreCaretaker implements Caretaker{
    private final Deque<GameScoreMemento> mementos = new ArrayDeque<>();

    public void saveState(GameScore gameScore){
        GameScoreMemento memento = gameScore.save();
        mementos.push(memento);
    }

    public void reloadState(GameScore gameScore){
        if (mementos == null || mementos.isEmpty()) {
            System.out.println("No previous state to revert to.");
            return;
        }

        gameScore.restore(mementos.pop());
    }
}
