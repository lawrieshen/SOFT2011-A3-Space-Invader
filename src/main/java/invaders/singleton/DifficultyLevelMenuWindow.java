package invaders.singleton;

import invaders.engine.GameEngine;
import invaders.engine.GameWindow;
import invaders.entities.MenuBackground;
import invaders.rendering.Renderable;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DifficultyLevelMenuWindow {
    private Stage primaryStage;
    private final int width;
    private final int height;
    private Scene scene;
    private Pane pane;
    private Renderable background;
    /** Singleton instance -- Lazy Initialization Thread-Safe **/
    private static DifficultyLevelMenuWindow instance;

    public DifficultyLevelMenuWindow(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.width =  600;
        this.height = 800;
        pane = new Pane();
        scene = new Scene(pane, width, height);
        this.background = new MenuBackground(pane);

        /**Set up Difficulty Level Menu**/
        Menu difficultyMenu = createDifficultyMenu();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(difficultyMenu);
        VBox layoutForDLM = new VBox();
        layoutForDLM.getChildren().addAll(menuBar, pane);
        scene = new Scene(layoutForDLM, width, height);

    }

    public Scene getScene() {
        return scene;
    }

    public static DifficultyLevelMenuWindow getInstance(Stage primaryStage){
        return instance = new DifficultyLevelMenuWindow(primaryStage);
    }

    public void switchToGameWindow(GameEngine model, GameWindow window){
        window.run();

        primaryStage.setScene(window.getScene());
    }

    public Menu createDifficultyMenu() {

        Menu difficultyMenu = new Menu("Difficulty");

        MenuItem easyMenuItem = new MenuItem("Easy");
        MenuItem normalMenuItem = new MenuItem("Normal");
        MenuItem hardMenuItem = new MenuItem("Hard");

        easyMenuItem.setOnAction(e -> {
            // Set the game difficulty to easy
            LevelManager levelManager = LevelManager.getInstance();
            levelManager.easyConfiguration();

            switchToGameWindow(levelManager.getModel(), levelManager.getWindow());
        });

        normalMenuItem.setOnAction(e -> {
            // Set the game difficulty to normal
            LevelManager levelManager = LevelManager.getInstance();
            levelManager.mediumConfiguration();
            switchToGameWindow(levelManager.getModel(), levelManager.getWindow());
        });

        hardMenuItem.setOnAction(e -> {
            // Set the game difficulty to hard
            LevelManager levelManager = LevelManager.getInstance();
            levelManager.hardConfiguration();
            switchToGameWindow(levelManager.getModel(), levelManager.getWindow());
        });

        difficultyMenu.getItems().addAll(easyMenuItem, normalMenuItem, hardMenuItem);
        return difficultyMenu;
    }

}
