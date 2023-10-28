package invaders.engine;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import invaders.ConfigReader;
import invaders.entities.EntityViewImpl;
import invaders.entities.SpaceBackground;
import javafx.scene.control.*;
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

        /**Set up Undo Button**/
        Button undoButton = new Button("Undo");
        undoButton.setLayoutX(10);
        undoButton.setLayoutY(10);
        undoButton.setFocusTraversable(false);
        undoButton.setOnAction(e ->{
            //implement Undo feature
        });
        /**Set up Cheat Button**/
        Button cheatButton = new Button("Cheat");
        cheatButton.setLayoutX(70);
        cheatButton.setLayoutY(10);
        cheatButton.setFocusTraversable(false);
        cheatButton.setOnAction(e ->{
            //implement Cheat feature
        });

        pane.getChildren().addAll(undoButton,cheatButton);

        /**Set up Game Time Label**/
        gameTimeLabel = new Label("Time: 0:00");
        gameTimeLabel.setLayoutX(10);
        gameTimeLabel.setLayoutY(40);
        gameTimeLabel.setTextFill(Paint.valueOf("WHITE"));
        pane.getChildren().add(gameTimeLabel);

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
    }

	public Scene getScene() {
        return scene;
    }
}
