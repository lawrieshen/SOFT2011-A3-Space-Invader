package invaders.observer;

public class SystemStats implements Subject{
    private double time;
    private int score;

    public SystemStats(){
        time = 0;
        score = 0;
    }

    @Override
    public double getTime(){
        return time;
    }

    @Override
    public int getScore(){
        return score;
    }

    @Override
    public void attach(Observer observer) {

    }

    @Override
    public void detach(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void increaseTime() {

    }

    @Override
    public void increaseScore() {

    }
}
