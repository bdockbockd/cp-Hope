package Item;

import Constant.Audio;
import Sprite.BlackPanther;
import Sprite.Moveable;
import javafx.scene.image.Image;

public class Trap extends Item implements Moveable, DisappearObject {
	private double velocityX = 0, velocityY = 0;

	public Trap(double x, double y, Image image) {
		super(x, y, image);
		if (x < 600) {
			this.setVelocityX(500);
		} else {
			this.setVelocityX(-500);
		}
		this.setVelocityY(0);
		itemList.add(this);
	}

	// getter and setter
	@Override
	public void itemUse(BlackPanther blackPanther) {
		Audio.STUN.play();
		Thread trap = new Thread(() -> {
			blackPanther.setVelocity(0, 0);
			blackPanther.setLocked(true);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			blackPanther.setLocked(false);
		});
		trap.start();
	}

	public double getVelocityX() {
		return this.velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return 0;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public void updateTrap(double time) {
		this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
		this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
	}

}
