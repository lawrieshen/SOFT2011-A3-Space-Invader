package invaders.factory;

import invaders.engine.GameEngine;
import invaders.memento.EnemyProjectileMemento;
import invaders.physics.Vector2D;
import invaders.strategy.ProjectileStrategy;
import javafx.scene.image.Image;

public class EnemyProjectile extends Projectile{
    private ProjectileStrategy strategy;

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
}
