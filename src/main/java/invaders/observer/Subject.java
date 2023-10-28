package invaders.observer;

public interface Subject {

    public double getTime();

    public int getScore();

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();

    void increaseTime();

    void increaseScore();

}
