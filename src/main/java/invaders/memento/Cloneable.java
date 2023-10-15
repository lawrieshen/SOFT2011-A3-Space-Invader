package invaders.memento;

public interface Cloneable {
    public Memento save();
    public void restore();
}
