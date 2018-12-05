package Sprite;

import application.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HealthPotion extends Item {
	private static int healFactor = 100;
	public HealthPotion(double x, double y){
		super(x, y, Images.healthPotion);
	}
	public HealthPotion() {
		super(0, 0, Images.healthPotion);
	}
	public void itemUse() {
		
	}
	@Override
	public void update(double time) {
		// TODO Auto-generated method stub
		
	}
}
