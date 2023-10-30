package invaders.memento;

import invaders.engine.GameEngine;

import java.util.ArrayDeque;
import java.util.Deque;

public class GameEngineCaretaker {
    private final Deque<GameEngineMemento> mementos = new ArrayDeque<>();

    public void saveState(GameEngine gameEngine){
        GameEngineMemento memento = gameEngine.save();
        mementos.push(memento);
        System.out.println("Triggered");
    }

    public void revertGameEngine(GameEngine gameEngine){
        if (mementos == null || mementos.isEmpty()) {
            System.out.println("No previous state to revert to.");
            return;
        }

        gameEngine.restore(mementos.pop());
    }
}
