package invaders.engine;

import invaders.entities.Player;
import invaders.factory.EnemyProjectile;
import invaders.factory.Projectile;
import invaders.gameobject.Bunker;
import invaders.gameobject.Enemy;
import invaders.gameobject.GameObject;
import invaders.memento.*;
import invaders.rendering.Renderable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.*;

class KeyboardInputHandler {
    private final GameEngine model;
    private boolean left = false;
    private boolean right = false;
    private Set<KeyCode> pressedKeys = new HashSet<>();

    private Map<String, MediaPlayer> sounds = new HashMap<>();
    /**Implement Memento**/
    private Map<String, Object> caretakers;
    private BunkerCaretaker bunkerCaretaker;
    private EnemyCaretaker enemyCaretaker;
    private EnemyProjectileCaretaker enemyProjectileCaretaker;
    private PlayerCaretaker playerCaretaker;
    private SystemStatsCaretaker systemStatsCaretaker;

    /**Extensions**/
    private GameWindow window;

    KeyboardInputHandler(GameWindow window) {
        this.window = window;
        this.model = window.getModel();
        this.caretakers = window.getCaretakers();

        this.bunkerCaretaker = (BunkerCaretaker) caretakers.get("BunkerCaretaker");
        this.enemyCaretaker = (EnemyCaretaker) caretakers.get("EnemyCaretaker");
        this.enemyProjectileCaretaker = (EnemyProjectileCaretaker) caretakers.get("EnemyProjectileCaretaker");
        this.playerCaretaker = (PlayerCaretaker) caretakers.get("PlayerCaretaker");
        this.systemStatsCaretaker = (SystemStatsCaretaker) caretakers.get("SystemStatsCaretaker");


        // TODO (longGoneUser): Is there a better place for this code?
        URL mediaUrl = getClass().getResource("/shoot.wav");
        String jumpURL = mediaUrl.toExternalForm();

        Media sound = new Media(jumpURL);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        sounds.put("shoot", mediaPlayer);
    }

    void handlePressed(KeyEvent keyEvent) {
        if (pressedKeys.contains(keyEvent.getCode())) {
            return;
        }
        pressedKeys.add(keyEvent.getCode());

        if (keyEvent.getCode().equals(KeyCode.SPACE)) {
            if (model.shootPressed()) {
                MediaPlayer shoot = sounds.get("shoot");
                shoot.stop();
                shoot.play();
                /**save state of the game objects when the player shoot**/
                for (Renderable ro : model.getRenderables()){
                    if (ro.getClass().equals(Bunker.class)){
                        bunkerCaretaker.saveState((Bunker) ro);
                    } else if (ro.getClass().equals((Enemy.class))) {
                        enemyCaretaker.saveState((Enemy) ro);
                    } else if (ro.getClass().equals(Projectile.class)) {
                        if (((Projectile) ro).getClass().equals(EnemyProjectile.class)){
                            enemyProjectileCaretaker.saveState((EnemyProjectile) ro);
                        }
                    } else if (ro.getClass().equals(Player.class)) {
                        playerCaretaker.saveState((Player) ro);
                    }
                }
                systemStatsCaretaker.saveState(window);
            }
        }

        if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            left = true;
        }
        if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            right = true;
        }

        if (left) {
            model.leftPressed();
        }

        if(right){
            model.rightPressed();
        }
    }

    void handleReleased(KeyEvent keyEvent) {
        pressedKeys.remove(keyEvent.getCode());

        if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            left = false;
            model.leftReleased();
        }
        if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            model.rightReleased();
            right = false;
        }
    }
}
