package invaders.memento;

import invaders.physics.Vector2D;

public class EnemyMemento {
    private Vector2D position;
    private double health;

    public EnemyMemento(
            Vector2D position,
            double health
    ){
        this.position = position;
        this.health = health;
    }

    public Vector2D getPosition(){return position;}

    public double getHealth(){return health;}
}
