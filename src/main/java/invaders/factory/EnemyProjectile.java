package invaders.factory;

import invaders.engine.GameEngine;
import invaders.memento.EnemyProjectileMemento;
import invaders.observer2.Observer;
import invaders.observer2.Subject;
import invaders.physics.Vector2D;
import invaders.strategy.ProjectileStrategy;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class EnemyProjectile extends Projectile implements Subject {
    private ProjectileStrategy strategy;

    private List<Observer> observers = new ArrayList<>();

    public EnemyProjectile(Vector2D position, ProjectileStrategy strategy, Image image) {
        super(position,image);
        this.strategy = strategy;
    }

    public ProjectileStrategy getStrategy(){return strategy;}

    @Override
    public void update(GameEngine model) {
        strategy.update(this);

        if(this.getPosition().getY()>= model.getGameHeight() - this.getImage().getHeight()){
            this.takeDamage(1);
        }

    }
    @Override
    public String getRenderableObjectName() {
        return "EnemyProjectile";
    }

    public EnemyProjectileMemento save(){
        return new EnemyProjectileMemento(
               getPosition(),
               getHealth()
        );
    }

    public void restore(EnemyProjectileMemento enemyProjectileMemento){
        super.setPosition(enemyProjectileMemento.getPosition());
        super.setLives((int) enemyProjectileMemento.getHealth());
    }

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
        for (Observer observer : observers){
            observer.update(this);
        }
    }
}
