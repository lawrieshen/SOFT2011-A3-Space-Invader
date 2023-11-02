package invaders.memento;

import invaders.engine.GameWindow;
import invaders.entities.Player;

import java.util.ArrayDeque;
import java.util.Deque;

public class SystemStatsCaretaker {
    private Deque<SystemStatsMemento> mementos = new ArrayDeque<>();

    public  void saveState(GameWindow window){
        SystemStatsMemento memento = window.save();
        mementos.push(memento);
    }

    public void reloadState(GameWindow window){
        if (mementos == null || mementos.isEmpty()) {
            System.out.println("No previous state to revert to.");
            return;
        }

        window.restore(mementos.pop());
    }
}
