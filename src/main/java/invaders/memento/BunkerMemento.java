package invaders.memento;

import invaders.state.BunkerState;

public class BunkerMemento {
    private final double health;
    private final BunkerState bunkerState;

    public BunkerMemento(
            double health,
            BunkerState bunkerState
    ){
        this.health = health;
        this.bunkerState = bunkerState;
    }

    public BunkerState getBunkerState() {
        return bunkerState;
    }

    public double getHealth() {
        return health;
    }
}
