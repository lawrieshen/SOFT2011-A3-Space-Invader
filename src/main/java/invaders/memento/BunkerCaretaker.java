package invaders.memento;

import invaders.gameobject.Bunker;

import java.util.ArrayDeque;
import java.util.Deque;

public class BunkerCaretaker {
    private final Deque<BunkerMemento> mementos = new ArrayDeque<>();

    public void saveState(Bunker bunker){
        BunkerMemento memento = bunker.save();
        mementos.push(memento);
    }

    public void reloadState(Bunker bunker){
        if (mementos == null || mementos.isEmpty()) {
            System.out.println("No previous state to revert to.");
            return;
        }

        bunker.restore(mementos.pop());
    }
}
