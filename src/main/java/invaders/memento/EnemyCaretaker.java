package invaders.memento;

import invaders.entities.Player;
import invaders.gameobject.Enemy;
import invaders.rendering.Renderable;

import java.util.ArrayDeque;
import java.util.Deque;

public class EnemyCaretaker implements Caretaker{
    private final Deque<EnemyMemento> mementos = new ArrayDeque<>();
    public void saveState(Enemy enemy){
        EnemyMemento memento = enemy.save();
        mementos.push(memento);
    }

    public void reloadState(Enemy enemy){
        if (mementos == null || mementos.isEmpty()) {
            System.out.println("No previous state to revert to.");
            return;
        }

        enemy.restore(mementos.pop());
    }
}
