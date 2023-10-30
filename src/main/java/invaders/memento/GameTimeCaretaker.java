package invaders.memento;

import invaders.gameobject.Enemy;
import invaders.observer.GameTime;

import java.util.ArrayDeque;
import java.util.Deque;

public class GameTimeCaretaker implements Caretaker{

    private final Deque<GameTimeMemento> mementos = new ArrayDeque<>();

    public void saveState(GameTime gameTime){
        GameTimeMemento memento = gameTime.save();
        mementos.push(memento);
    }

    public void reloadState(GameTime gameTime){
        if (mementos == null || mementos.isEmpty()) {
            System.out.println("No previous state to revert to.");
            return;
        }

        gameTime.restore(mementos.pop());
    }
}
