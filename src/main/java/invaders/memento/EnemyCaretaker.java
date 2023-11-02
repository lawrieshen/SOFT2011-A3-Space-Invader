package invaders.memento;

import invaders.gameobject.Enemy;

import java.util.ArrayDeque;
import java.util.Deque;

public class EnemyCaretaker {
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
