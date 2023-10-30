package invaders.memento;

import invaders.factory.EnemyProjectile;
import invaders.physics.Vector2D;
import invaders.strategy.ProjectileStrategy;

import java.util.Deque;

public class EnemyProjectileMemento {
    private Vector2D position;
    private double health;

    public EnemyProjectileMemento(
        Vector2D position,
        double health
    ){
        this.position = position;
        this.health = health;
    }

    public Vector2D getPosition(){return position;}

    public double getHealth(){return health;}
}
