package invaders.engine;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import invaders.ConfigReader;
import invaders.entities.EntityViewImpl;
import invaders.entities.SpaceBackground;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import invaders.entities.EntityView;
import invaders.rendering.Renderable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.json.simple.JSONObject;

public class GameWindow implements Serializable {
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

	public GameWindow(GameEngine model){
        this.model = model;
		this.width =  model.getGameWidth();
        this.height = model.getGameHeight();

        pane = new Pane();
        scene = new Scene(pane, width, height);
        this.background = new SpaceBackground(model, pane);

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(this.model);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

//        /**Set up Undo Button**/
//        Button undoButton = new Button("Undo");
//        undoButton.setLayoutX(10);
//        undoButton.setLayoutY(10);
//        undoButton.setFocusTraversable(false);
//        undoButton.setOnAction(e ->{
//            //implement Undo feature
//        });
//        /**Set up Cheat Button**/
//        Button cheatButton = new Button("Cheat");
//        cheatButton.setLayoutX(70);
//        cheatButton.setLayoutY(10);
//        cheatButton.setFocusTraversable(false);
//        cheatButton.setOnAction(e ->{
//            //implement Cheat feature
//        });
//
//        pane.getChildren().addAll(undoButton, cheatButton);
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

        HBox hbox = new HBox(undoButton, cheatMenuBar);

        HBox.setHgrow(cheatMenuBar, Priority.NEVER);
        HBox.setHgrow(undoButton, Priority.NEVER);
        hbox.setSpacing(10);
        hbox.setLayoutX(10);
        hbox.setLayoutY(10);

        pane.getChildren().add(hbox);

    }

	public void run() {
         Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17), t -> this.draw()));

         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.play();
    }


    private void draw(){
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
            }
        }

        for (EntityView entityView : entityViews) {
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
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
        double minutes = (double) model.getSystemStats().getGameTime().toMinutes();
        double seconds = (double) (model.getSystemStats().getGameTime().toSeconds()%60);

        String formattedTime  = String.format("Time: %02.0f:%02.0f", minutes, seconds);
        gameTimeLabel.setText(formattedTime);

        /**Draw Score Recorder DYNAMICALLY**/
        int score = model.getSystemStats().getGameScore();

        String formattedScore = String.format("Score: %d", score);
        gameScoreLabel.setText(formattedScore);
    }

	public Scene getScene() {
        return scene;
    }
}
