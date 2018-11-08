package application;

import javafx.scene.image.Image;

public class Map {
	
	private Image imgBack;
	private int motionSpeed = 3;
	
	private double gravity = 0.8;
	
//	private MediaPlayer bgm;
	
	//@SafeVarargs
	public Map(Image image) {
		this.imgBack = image;
	}
	
//	public void Motion(Entity e) {
//		moveEntity(e);
////		pullGravity(e);
////		decelerate(e);
//		moveMap();
//		if (e.getVelocityY() >= 0 && isOnFloor(e)) {
//			e.setVelocity(0);
//			>> e Player set Jumping false
//		}
//	}
	
//	public void motionAll() {
//		for(all thing in sharedEntity )
//			motion(each thing)
//	}
	
	// public collide
	
	public void render(GraphicsContent gc) {
		
	}
	
	public void update() {

	}
}
