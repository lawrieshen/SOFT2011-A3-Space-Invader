package invaders.observer;


import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class GameTime implements Subject{
    //observer register
    private List<Observer> observers = new ArrayList<>();
    //Subject state
    private Duration gameTime  = Duration.ZERO;

    public GameTime(){};

    public Duration getGameTime(){return gameTime;}
    public void setTime(Duration time){this.gameTime = time;}

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
}
