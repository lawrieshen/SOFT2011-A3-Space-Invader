package invaders.memento;

import invaders.entities.Player;
import invaders.gameobject.GameObject;
import invaders.observer.GameScore;
import invaders.observer.GameTime;
import invaders.observer.SystemStats;
import invaders.rendering.Renderable;

import java.util.ArrayList;
import java.util.List;

public class GameEngineMemento {
    private List<GameObject> gameObjects;
    private List<GameObject> pendingToAddGameObject;
    private List<GameObject> pendingToRemoveGameObject;

    private List<Renderable> pendingToAddRenderable;
    private List<Renderable> pendingToRemoveRenderable;

    private List<Renderable> renderables;

    private Player player;

    private boolean left;
    private boolean right;
    private int gameWidth;
    private int gameHeight;
    private int timer;
    private GameTime gameTime;
    private GameScore gameScore;
    private SystemStats systemStats;

    public GameEngineMemento(
            List<GameObject> gameObjects,
            List<GameObject> pendingToAddGameObject,
            List<GameObject> pendingToRemoveGameObject,
            List<Renderable> pendingToAddRenderable,
            List<Renderable> pendingToRemoveRenderable,
            List<Renderable> renderables,
            Player player,
            boolean left,
            boolean right,
            int gameWidth,
            int gameHeight,
            int timer,
            GameTime gameTime,
            GameScore gameScore,
            SystemStats systemStats
    ){
        this.gameObjects = gameObjects;
        this.pendingToAddGameObject = pendingToAddGameObject;
        this.pendingToRemoveGameObject = pendingToRemoveGameObject;
        this.pendingToAddRenderable = pendingToAddRenderable;
        this.pendingToRemoveRenderable = pendingToRemoveRenderable;
        this.renderables = renderables;
        this.player = player;
        this.left = left;
        this.right = right;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.timer = timer;
        this.gameTime = gameTime;
        this.gameScore = gameScore;
        this.systemStats = systemStats;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public List<GameObject> getPendingToAddGameObject() {
        return pendingToAddGameObject;
    }

    public List<GameObject> getPendingToRemoveGameObject() {
        return pendingToRemoveGameObject;
    }

    public List<Renderable> getPendingToAddRenderable() {
        return pendingToAddRenderable;
    }

    public List<Renderable> getPendingToRemoveRenderable() {
        return pendingToRemoveRenderable;
    }

    public List<Renderable> getRenderables() {
        return renderables;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getTimer() {
        return timer;
    }

    public GameTime getGameTime() {
        return gameTime;
    }

    public GameScore getGameScore() {
        return gameScore;
    }

    public SystemStats getSystemStats() {
        return systemStats;
    }
}
