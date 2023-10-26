package invaders.entities;

import invaders.engine.GameEngine;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MenuBackground implements Renderable {
	private Rectangle menu;
	private Pane pane;

	public MenuBackground(Pane pane){
		double width = pane.getWidth();
		double height = pane.getHeight();
		menu = new Rectangle(0, 0, width, height);
		menu.setFill(Paint.valueOf("WHITE"));
		menu.setViewOrder(1000.0);

		pane.getChildren().add(menu);
	}

	public Image getImage() {
		return null;
	}

	@Override
	public double getWidth() {
		return 0;
	}

	@Override
	public double getHeight() {
		return 0;
	}

	@Override
	public Vector2D getPosition() {
		return null;
	}

	@Override
	public Layer getLayer() {
		return Layer.BACKGROUND;
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public void takeDamage(double amount) {}

	@Override
	public double getHealth() {
		return 0;
	}

	@Override
	public String getRenderableObjectName() {
		return "whiteMenuBackground";
	}
}
