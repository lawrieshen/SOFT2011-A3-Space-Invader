package invaders.engine;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class DifficultyLevelMenu {
    private DifficultyLevelMenuWindow difficultyLevelMenuWindow;

    public DifficultyLevelMenu(DifficultyLevelMenuWindow difficultyLevelMenuWindow){
        this.difficultyLevelMenuWindow = difficultyLevelMenuWindow;
    };

    public Menu createDifficultyMenu() {

        Menu difficultyMenu = new Menu("Difficulty");

        MenuItem easyMenuItem = new MenuItem("Easy");
        MenuItem normalMenuItem = new MenuItem("Normal");
        MenuItem hardMenuItem = new MenuItem("Hard");

        easyMenuItem.setOnAction(e -> {
            // Set the game difficulty to easy
            GameEngine model = new GameEngine("src/main/resources/config_easy.json");
            GameWindow window = new GameWindow(model);

            difficultyLevelMenuWindow.switchToGameWindow(model, window);
        });

        normalMenuItem.setOnAction(e -> {
            // Set the game difficulty to normal
            GameEngine model = new GameEngine("src/main/resources/config_medium.json");
            GameWindow window = new GameWindow(model);

            difficultyLevelMenuWindow.switchToGameWindow(model, window);
        });

        hardMenuItem.setOnAction(e -> {
            // Set the game difficulty to hard
            GameEngine model = new GameEngine("src/main/resources/config_hard.json");
            GameWindow window = new GameWindow(model);

            difficultyLevelMenuWindow.switchToGameWindow(model, window);
        });

        difficultyMenu.getItems().addAll(easyMenuItem, normalMenuItem, hardMenuItem);
        return difficultyMenu;
    }

}

