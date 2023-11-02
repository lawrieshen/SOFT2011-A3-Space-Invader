package invaders.memento;

import invaders.factory.EnemyProjectile;

import java.util.ArrayDeque;
import java.util.Deque;

public class EnemyProjectileCaretaker {

    private final Deque<EnemyProjectileMemento> mementos = new ArrayDeque<>();
    public void saveState(EnemyProjectile enemyProjectile){
        EnemyProjectileMemento memento = enemyProjectile.save();
        mementos.push(memento);
    }

    public void reloadState(EnemyProjectile enemyProjectile){
        if (mementos == null || mementos.isEmpty()) {
            System.out.println("No previous state to revert to.");
            return;
        }

        enemyProjectile.restore(mementos.pop());
    }
}
