package invaders.observer2;

import invaders.observer2.Observer;

public interface Subject {
    public void attach(Observer observer);
    public void detach(Observer observer);
    public void notifyObservers();
}
