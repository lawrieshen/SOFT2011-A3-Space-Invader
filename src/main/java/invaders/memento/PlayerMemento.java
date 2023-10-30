package invaders.memento;

import invaders.physics.Vector2D;

public class PlayerMemento {
    private final Vector2D position;
    private final double health;

    public PlayerMemento(
            Vector2D position,
            double health
    ){
        this.position = position;
        this.health = health;
    }

    public Vector2D getPosition(){return position;}

    public double getHealth(){return health;}
}
