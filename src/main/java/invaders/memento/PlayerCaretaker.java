package invaders.memento;

import invaders.entities.Player;
import invaders.rendering.Renderable;

import java.util.ArrayDeque;
import java.util.Deque;

public class PlayerCaretaker implements Caretaker{
    private final Deque<PlayerMemento> mementos = new ArrayDeque<>();
    public  void saveState(Player player){
        PlayerMemento memento = player.save();
        mementos.push(memento);
    }

    public void reloadState(Player player){
        if (mementos == null || mementos.isEmpty()) {
            System.out.println("No previous state to revert to.");
            return;
        }

        player.restore(mementos.pop());
    }
}
