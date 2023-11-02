package invaders.singleton;

import invaders.engine.GameEngine;
import invaders.engine.GameWindow;

public class LevelManager {
    /** Singleton instance -- Lazy Initialization Thread-Safe **/
    private static LevelManager instance;
    private GameEngine model;
    private GameWindow window;

    public LevelManager(){};

    public static LevelManager getInstance(){
        return new LevelManager();
    }

    public void easyConfiguration(){
        this.model = new GameEngine("src/main/resources/config_easy.json");
        this.window = new GameWindow(model);
    }

    public void mediumConfiguration(){
        this.model = new GameEngine("src/main/resources/config_medium.json");
        this.window = new GameWindow(model);
    }

    public void hardConfiguration(){
        this.model = new GameEngine("src/main/resources/config_medium.json");
        this.window = new GameWindow(model);
    }

    public GameEngine getModel() {
        return model;
    }

    public GameWindow getWindow() {
        return window;
    }
}
