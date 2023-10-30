package invaders.observer;

import invaders.factory.Projectile;
import invaders.gameobject.Enemy;
import invaders.memento.GameScoreMemento;
import invaders.rendering.Renderable;
import invaders.strategy.FastProjectileStrategy;
import invaders.strategy.SlowProjectileStrategy;

import java.util.ArrayList;
import java.util.List;

public class GameScore implements Subject{
    //observer register
    private List<Observer> observers = new ArrayList<>();
    // subject state
    private int gameScore = 0;

    public GameScore(){};

    public int getGameScore(){return gameScore;}

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    //    public void gainScore(Projectile projectile, Enemy enemy){
//        if (projectile.getClass().equals(SlowProjectileStrategy.class)){
//            gameScore = gameScore + 1;
//        } else if (projectile.getClass().equals(FastProjectileStrategy.class)) {
//            gameScore = gameScore + 2;
//        } else if (enemy.get){
//
//        }
//
//    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update();
        }
    }

    public GameScoreMemento save(){
        return new GameScoreMemento(gameScore);
    }

    public void restore(GameScoreMemento gameScoreMemento){
        this.setGameScore(gameScoreMemento.getGameScore());
    }
}
