package invaders;

import invaders.engine.DifficultyLevelMenuWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import invaders.engine.GameEngine;
import invaders.engine.GameWindow;

import java.util.Map;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //GameEngine model = new GameEngine("src/main/resources/config_easy.json");
        //GameWindow window = new GameWindow(model);
        //window.run();
        DifficultyLevelMenuWindow difficultyLevelWindow = DifficultyLevelMenuWindow.getInstance(primaryStage);

        primaryStage.setTitle("Space Invaders");
        primaryStage.setScene(difficultyLevelWindow.getScene());
        primaryStage.show();

        //window.run();
    }
}
