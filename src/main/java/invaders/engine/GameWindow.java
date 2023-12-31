package invaders.engine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import invaders.entities.EntityViewImpl;
import invaders.entities.Player;
import invaders.entities.SpaceBackground;
import invaders.factory.EnemyProjectile;
import invaders.factory.PlayerProjectile;
import invaders.gameobject.Bunker;
import invaders.gameobject.Enemy;
import invaders.gameobject.GameObject;
import invaders.memento.*;
import invaders.observer2.Observer;
import invaders.observer2.Subject;
import invaders.strategy.FastProjectileStrategy;
import invaders.strategy.SlowProjectileStrategy;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import invaders.entities.EntityView;
import invaders.rendering.Renderable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameWindow implements Serializable, Observer {
	private final int width;
    private final int height;
	private Scene scene;
    private Pane pane;
    private GameEngine model;
    private List<EntityView> entityViews =  new ArrayList<EntityView>();
    private Renderable background;

    private double xViewportOffset = 0.0;
    private double yViewportOffset = 0.0;
    // private static final double VIEWPORT_MARGIN = 280.0;

    /**Set up Game Time Label**/
    private Label gameTimeLabel;
    /**Set Up Game Score Label**/
    private Label gameScoreLabel;
    /**Set Up Undo Button and Cheat Menu Bar**/
    private Button undoButton;
    private MenuBar cheatMenuBar;
    /**Memento**/
    private Map<String, Object> caretakers= new HashMap();


    private int gameScore = 0;
    private Duration gameTime = Duration.ZERO;

	public GameWindow(GameEngine model){
        this.model = model;
		this.width =  model.getGameWidth();
        this.height = model.getGameHeight();

        /**Register all caretakers**/
        caretakers.put("BunkerCaretaker", new BunkerCaretaker());
        caretakers.put("EnemyCaretaker", new EnemyCaretaker());
        caretakers.put("EnemyProjectileCaretaker", new EnemyProjectileCaretaker());
        caretakers.put("PlayerCaretaker", new PlayerCaretaker());
        caretakers.put("SystemStatsCaretaker", new SystemStatsCaretaker());

        pane = new Pane();
        scene = new Scene(pane, width, height);
        this.background = new SpaceBackground(model, pane);

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(this);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

        /**Set Up Game Time Label**/
        gameTimeLabel = new Label("Time: 0:00");
        gameTimeLabel.setLayoutX(10);
        gameTimeLabel.setLayoutY(40);
        gameTimeLabel.setTextFill(Paint.valueOf("WHITE"));
        pane.getChildren().add(gameTimeLabel);

        /**Set Up Game Score Label**/
        gameScoreLabel = new Label("Score: 0");
        gameScoreLabel.setLayoutX(10);
        gameScoreLabel.setLayoutY(60);
        gameScoreLabel.setTextFill(Paint.valueOf("WHITE"));
        pane.getChildren().add(gameScoreLabel);

        /**Set Up Undo and Cheat Menu**/
        cheatMenuBar = new MenuBar();
        undoButton = new Button("Undo");
        Menu cheatMenu = new Menu("Cheat");
        MenuItem removeAllSlowAliens = new MenuItem("Remove All Slow Aliens");
        MenuItem removeAllFastAliens = new MenuItem("Remove All Fast Aliens");
        MenuItem removeALlSlowProjectiles = new MenuItem("Remove All Slow Projectiles");
        MenuItem removeALlFastProjectiles = new MenuItem("Remove All Fast Projectiles");
        cheatMenu.getItems().addAll(removeAllSlowAliens,removeAllFastAliens,removeALlSlowProjectiles,removeALlFastProjectiles);
        cheatMenuBar.getMenus().add(cheatMenu);

        removeAllSlowAliens.setOnAction(e -> {
            for (Renderable ro : model.getRenderables()){
                if (ro.getClass().equals(Enemy.class)){
                    if (((Enemy) ro).getProjectileStrategy().getClass().equals(SlowProjectileStrategy.class)){
                        ro.takeDamage(((int)ro.getHealth())+1);
                    }
                }
            }
        });
        removeAllFastAliens.setOnAction(e -> {
            for (Renderable ro : model.getRenderables()){
                if (ro.getClass().equals(Enemy.class)){
                    if (((Enemy) ro).getProjectileStrategy().getClass().equals(FastProjectileStrategy.class)){
                        ro.takeDamage(((int)ro.getHealth())+1);
                    }
                }
            }
        });
        removeALlSlowProjectiles.setOnAction(e -> {
            for (Renderable ro : model.getRenderables()){
                if (ro.getClass().equals(EnemyProjectile.class)){
                    if (((EnemyProjectile) ro).getStrategy().getClass().equals(SlowProjectileStrategy.class)){
                        ro.takeDamage(((int)ro.getHealth())+1);
                    }
                }
            }
        });
        removeALlFastProjectiles.setOnAction(e -> {
            for (Renderable ro : model.getRenderables()){
                if (ro.getClass().equals(EnemyProjectile.class)){
                    if (((EnemyProjectile) ro).getStrategy().getClass().equals(FastProjectileStrategy.class)){
                        ro.takeDamage(((int)ro.getHealth())+1);
                    }
                }
            }
        });

        undoButton.setOnAction(e -> {
            //to do
            List<Renderable> renderablesToBeRemoved = new ArrayList<>();
            for (Renderable ro : model.getRenderables()){
                if (ro.getClass().equals(Bunker.class)){
                    renderablesToBeRemoved.add(ro);
                    ((BunkerCaretaker) caretakers.get("BunkerCaretaker")).reloadState((Bunker) ro);
                } else if (ro.getClass().equals(Enemy.class)) {
                    renderablesToBeRemoved.add(ro);
                    ((EnemyCaretaker) caretakers.get("EnemyCaretaker")).reloadState((Enemy) ro);
                } else if (ro.getClass().equals(EnemyProjectile.class)){
                    renderablesToBeRemoved.add(ro);
                    ((EnemyProjectileCaretaker) caretakers.get("EnemyProjectileCaretaker")).reloadState((EnemyProjectile) ro);
                } else if (ro.getClass().equals(Player.class)) {
                    renderablesToBeRemoved.add(ro);
                    ((PlayerCaretaker) caretakers.get("PlayerCaretaker")).reloadState((Player) ro);
                } else if (ro.getClass().equals(PlayerProjectile.class)) {
                    ro.takeDamage(1);
                }
                ((SystemStatsCaretaker) caretakers.get("SystemStatsCaretaker")).reloadState(this);
            }

            for (Renderable entity : renderablesToBeRemoved){
                for (EntityView entityView : entityViews){
                    if (entityView.matchesEntity(entity)){
                        entityView.markForDelete();
                    }
                }
            }

            for (EntityView entityView : entityViews) {
                if (entityView.isMarkedForDelete()) {
                    pane.getChildren().remove(entityView.getNode());
                }
            }

            entityViews.removeIf(EntityView::isMarkedForDelete);
        });


        undoButton.setFocusTraversable(false);
        cheatMenuBar.setFocusTraversable(false);

        HBox hbox = new HBox(undoButton, cheatMenuBar);

        HBox.setHgrow(cheatMenuBar, Priority.NEVER);
        HBox.setHgrow(undoButton, Priority.NEVER);
        hbox.setSpacing(10);
        hbox.setLayoutX(10);
        hbox.setLayoutY(10);
        hbox.setFocusTraversable(false);

        pane.getChildren().add(hbox);


        // Observer - register observer to subject
        for (Renderable ro : model.getRenderables()){
            if (ro.getClass().equals(Enemy.class)){
                ((Enemy) ro).attach(this);
            }
        }


    }

	public void run() {
         Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17), t -> this.draw()));

         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.play();
    }


    private void draw(){
        gameTime = gameTime.add(Duration.millis(17));

        model.update();

        List<Renderable> renderables = model.getRenderables();
        for (Renderable entity : renderables) {
            boolean notFound = true;
            for (EntityView view : entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    view.update(xViewportOffset, yViewportOffset);
                    break;
                }
            }
            if (notFound) {
                EntityView entityView = new EntityViewImpl(entity);
                entityViews.add(entityView);
                pane.getChildren().add(entityView.getNode());
            }
        }

        for (Renderable entity : renderables){
            if (!entity.isAlive()){
                for (EntityView entityView : entityViews){
                    if (entityView.matchesEntity(entity)){
                        entityView.markForDelete();
                    }
                }
                model.getPendingToRemoveGameObject().add((GameObject) entity);
                model.getPendingToRemoveRenderable().add(entity);
            }
        }

        for (EntityView entityView : entityViews) {
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        // Observer - capture shot projectiles to register observer to subjects (Enemy projectiles)
        for (Renderable ro : model.getPendingToAddRenderable()){
            if (ro.getClass().equals(EnemyProjectile.class)){
                ((EnemyProjectile) ro).attach(this);
            }
        }

        //Observer - capture all dead enemy (not projectile, coz us need to make sure it is destroyed by player projectile )
        for (Renderable ro : model.getPendingToRemoveRenderable()){
            if (ro.getClass().equals(Enemy.class)){
                ((Enemy) ro).notifyObservers();
            }
        }
        //Observer - capture all dead enemy projectile collides with player projectiles
        List<PlayerProjectile> playerProjectiles = new ArrayList<>();
        for (Renderable ro : model.getRenderables()){
            if (ro.getClass().equals(PlayerProjectile.class)){
                playerProjectiles.add((PlayerProjectile) ro);
            }
        }

        for (Renderable ro : model.getPendingToRemoveRenderable()){
            if (ro.getClass().equals(EnemyProjectile.class)){
                for (PlayerProjectile playerProjectile : playerProjectiles){
                    if (ro.isColliding(playerProjectile)){
                        ((EnemyProjectile) ro).notifyObservers();
                        break;
                    }
                }
            }
        }

        model.getGameObjects().removeAll(model.getPendingToRemoveGameObject());
        model.getGameObjects().addAll(model.getPendingToAddGameObject());
        model.getRenderables().removeAll(model.getPendingToRemoveRenderable());
        model.getRenderables().addAll(model.getPendingToAddRenderable());

        model.getPendingToAddGameObject().clear();
        model.getPendingToRemoveGameObject().clear();
        model.getPendingToAddRenderable().clear();
        model.getPendingToRemoveRenderable().clear();

        entityViews.removeIf(EntityView::isMarkedForDelete);


        /**Draw Duration of Game DYNAMICALLY**/
        double minutes = (double) gameTime.toMinutes();
        double seconds = (double) (gameTime.toSeconds()%60);

        String formattedTime  = String.format("Time: %02.0f:%02.0f", minutes, seconds);
        gameTimeLabel.setText(formattedTime);

        /**Draw Score Recorder DYNAMICALLY**/

        String formattedScore = String.format("Score: %d", gameScore);
        gameScoreLabel.setText(formattedScore);

        /**Check End Game Or Not**/
        determineEndGame();

    }

	public Scene getScene() {
        return scene;
    }

    @Override
    public void update(Subject subject) {
        if (subject.getClass().equals(Enemy.class)){
            if (((Enemy) subject).getProjectileStrategy().getClass().equals(SlowProjectileStrategy.class)){
                gameScore = gameScore + 3;
            } else if (((Enemy) subject).getProjectileStrategy().getClass().equals(FastProjectileStrategy.class)) {
                gameScore = gameScore + 4;
            }
        } else if (subject.getClass().equals(EnemyProjectile.class)) {
            if (((EnemyProjectile) subject).getStrategy().getClass().equals(SlowProjectileStrategy.class)){
                gameScore = gameScore + 1;
            } else if (((EnemyProjectile) subject).getStrategy().getClass().equals(FastProjectileStrategy.class)) {
                gameScore = gameScore + 2;
            }
        }
    }

    public SystemStatsMemento save(){
        return new SystemStatsMemento(gameTime, gameScore);
    }

    public void restore(SystemStatsMemento systemStatsMemento){
        this.gameTime = systemStatsMemento.getGameTime();
        this.gameScore = systemStatsMemento.getGameScore();
    }

    public GameEngine getModel() {
        return model;
    }

    public Map<String, Object> getCaretakers() {
        return caretakers;
    }

    public void determineEndGame(){
        //when all enemies are dead, end game
        int counter = 0;
		for (Renderable ro : model.getRenderables()){
			if (ro.isAlive() && ro.getRenderableObjectName().equals("Enemy")){
				counter++;
			}
		}
		if (counter <=0){
			System.out.println("Player Wins");
			System.out.println("Game Score: " + gameScore);
			double minutes = (double) gameTime.toMinutes();
			double seconds = (double) (gameTime.toSeconds()%60);

			String formattedTime  = String.format("%02.0f:%02.0f", minutes, seconds);
			System.out.println("Game Time: "+formattedTime);
			Platform.exit();
		}
        //when player is dead, end game
        counter = 0;
        for (Renderable ro: model.getRenderables()){
            if (ro.isAlive() && ro.getRenderableObjectName().equals("Player")){
                counter++;
            }
        }
        if (counter <=0 ){
			System.out.println("Aliens Win");
			System.out.println("Game Score: " + gameScore);
            double minutes = (double) gameTime.toMinutes();
            double seconds = (double) (gameTime.toSeconds()%60);

            String formattedTime  = String.format("%02.0f:%02.0f", minutes, seconds);
            System.out.println("Game Time: "+formattedTime);
            Platform.exit();
        }
    }
}
