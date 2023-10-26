package invaders.engine;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class DifficultyLevelMenu {

//    private final int width;
//    private final int height;
//    private Scene scene;
//    private Pane pane;
//    private GameEngine model;
//    private Renderable background;
//
//    private double xViewportOffset = 0.0;
//    private double yViewportOffset = 0.0;
//    // private static final double VIEWPORT_MARGIN = 280.0;
//
//    public DifficultyLevelMenu(GameEngine model){
//
//    }

    public static Menu createDifficultyMenu(GameEngine model) {
        Menu difficultyMenu = new Menu("Difficulty");

        MenuItem easyMenuItem = new MenuItem("Easy");
        MenuItem normalMenuItem = new MenuItem("Normal");
        MenuItem hardMenuItem = new MenuItem("Hard");

        easyMenuItem.setOnAction(e -> {
            // Set the game difficulty to easy
            //model.setGameDifficulty("config_easy.json");
        });

        normalMenuItem.setOnAction(e -> {
            // Set the game difficulty to normal
            //model.setGameDifficulty("config_medium.json");
        });

        hardMenuItem.setOnAction(e -> {
            // Set the game difficulty to hard
            //model.setGameDifficulty("config_hard.json");
        });

        difficultyMenu.getItems().addAll(easyMenuItem, normalMenuItem, hardMenuItem);
        return difficultyMenu;
    }
}

