package Enemy;

import java.util.concurrent.CopyOnWriteArrayList;

import Constant.Images;
import Sprite.BlackPanther;
import Sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class HumanSprite extends Sprite {

	private String name = "HumanSprite";
	public int humanPosition = 0;
	private boolean isMove;
	private final double maxHealth = 500;
	private double health;
	private final double damage = 10;
	private boolean isDead = false;
	private boolean isTomb = false;
	private boolean waitToHit = false;
	private boolean knockBack = false;
	private boolean isFollowing = false;
	private boolean isGreedy = false;

	public static double BOT_GREEDY_CHANCE = 0.6;
	public static double BOT_FOLLOWING_CHANCE = 0.4;
	public static double BOT_STEADY_CHANCE = 0.2;
	public static double MAX_RANDOM_SPEED = 200;

	protected boolean attackStage;

	public HumanSprite(Image image, Image[] imageList, Image[] imageL, Image[] imageR) {
		super(image, imageL, imageR);
		this.setImageList(imageList);
		this.setVelocityX(0);
		this.setPositionX(0);
		this.setVelocityY(0);
		this.setPositionY(0);

		this.health = maxHealth;
	}

	public void update(double time) {
		if (this.isDead) {
			this.setVelocity(0, 0);
			this.setImage(Images.enemyTomb);
			return;
		}
		this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
		this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
		if (this.positionX < 0) {
//        	this.setPositionX(0);
		}
		if (this.positionX > 1250 - this.getWidth()) {
//        	this.setPositionX(1250-this.getWidth());
		}
		if (this.positionY < 210) {
			this.setPositionY(210);
		}
		if (this.positionY > 800 - this.getHeight()) {
			this.setPositionY(800 - this.getHeight());
		}

	}

	public void setBotType() {
		double random = Math.random();
		if (Math.random() < 0.15) {
			this.setFollowing(false);
		} else if (Math.random() >= 0.75) {
			this.setGreedy(true);
		} else {
			this.setFollowing(true);
		}
	}

	public void updateBotTypeFollowing(BlackPanther blackPanther) {
		if (this.intersect(blackPanther) == false) {
			if (blackPanther.getPositionX() + 120 < this.getPositionX()) {
				this.setFace("LEFT");
				this.setPositionL(0);
				this.nextPosition(this.getFace());
				if (blackPanther.getPositionY() + 75 < this.getPositionY()) {
					this.setVelocity(-Math.random() * 200, -Math.random() * 200);
				} else {
					this.setVelocity(-Math.random() * 200, Math.random() * 200);
				}

			} else {
				this.setFace("RIGHT");
				this.setPositionR(0);
				this.nextPosition(this.getFace());
				if (blackPanther.getPositionY() + 75 < this.getPositionY()) {
					this.setVelocity(Math.random() * 200, -Math.random() * 200);
				} else {
					this.setVelocity(Math.random() * 200, Math.random() * 200);

				}
			}
		}
	}

	public void updateBotTypeGreedy(BlackPanther blackPanther) {
		if (blackPanther.getPositionX() + 120 < this.getPositionX()) {
			this.setFace("LEFT");
			this.setPositionL(0);
			this.nextPosition(this.getFace());
			if (blackPanther.getPositionY() + 75 < this.getPositionY()) {
				this.setVelocity(-Math.random() * 200, -Math.random() * 200);
			} else {
				this.setVelocity(-Math.random() * 200, Math.random() * 200);
			}

		} else {
			this.setFace("RIGHT");
			this.setPositionR(0);
			this.nextPosition(this.getFace());
			if (blackPanther.getPositionY() + 75 < this.getPositionY()) {
				this.setVelocity(Math.random() * 200, -Math.random() * 200);
			} else {
				this.setVelocity(Math.random() * 200, Math.random() * 200);

			}
		}
	}

	public void updateSteady(BlackPanther blackPanther) {
		if (Math.random() > 0.3) {
			this.setVelocity(Math.random() * 100 - 50, Math.random() * 100 - 50);
			return;
		}
		if (blackPanther.getPositionX() + 120 < this.getPositionX()) {
			this.setFace("LEFT");
			this.setPositionL(0);
			this.nextPosition(this.getFace());
			if (blackPanther.getPositionY() + 75 < this.getPositionY()) {
				this.setVelocity(-Math.random() * 200, 0);
			} else {
				this.setVelocity(-Math.random() * 200, 0);
			}

		} else {
			this.setFace("RIGHT");
			this.setPositionR(0);
			this.nextPosition(this.getFace());
			if (blackPanther.getPositionY() + 75 < this.getPositionY()) {
				this.setVelocity(Math.random() * 200, 0);
			} else {
				this.setVelocity(Math.random() * 200, 0);

			}
		}
	}

	public void attack(BlackPanther blackPanther) {
		blackPanther.takeDamage(this.getDamage());
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(this.getImage(), this.getPositionX(), this.getPositionY());
		if (!this.isDead()) {
			gc.setFill(Color.BLACK);
			gc.fillRect(this.getPositionX(), this.getPositionY() - 10, this.getWidth(), 5);
			gc.setFill(Color.RED);
			gc.fillRect(this.getPositionX(), this.getPositionY() - 10, this.getWidth()*(getHealth()/getMaxHealth()), 5);

		}
	}

	// getter and setter
	public void setImage(String filename) {
		Image i = new Image(filename);
		setImage(i);
	}

	public void setMove(boolean tf) {
		this.isMove = tf;
	}

	public boolean getMove() {
		return this.isMove;
	}

	// add more type of setPosition
	public void setPosition(double x, double y) {
		this.setPositionX(x);
		this.setPositionY(y);
	}

	public void setVelocity(double x, double y) {
		this.setVelocityX(x);
		this.setVelocityY(y);
	}

	public void addVelocity(double x, double y) {
		this.setVelocity(x + this.getVelocityX(), y + this.getVelocityY());
		this.setMove(true);
	}

	public String toString() {
		return " Position: [" + this.getPositionX() + "," + this.getPositionY() + "]" + " Velocity: ["
				+ this.getVelocityX() + "," + this.getVelocityY() + "]";
	}

	public double getMaxHealth() {
		return this.maxHealth;
	}

	public double getHealth() {
		return this.health;
	}

	public void setHealth(double health) {
		if (health <= 0) {
			this.setDead(true);
			this.setVelocity(0, 0);
		}
		this.health = health;
	}

	public double getDamage() {
		return this.damage;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isWaitToHit() {
		return waitToHit;
	}

	public void setWaitToHit(boolean waitToHit) {
		if (this.waitToHit != waitToHit) {
			this.setFace(this.getFace());
			this.nextPosition(this.getFace());
		}
		this.waitToHit = waitToHit;
	}

	public boolean isTomb() {
		return isTomb;
	}

	public void setTomb(boolean isTomb) {
		this.isTomb = isTomb;
	}

	public boolean isKnockBack() {
		return knockBack;
	}

	public void setKnockBack(boolean knockBack) {
		this.knockBack = knockBack;
	}

	public boolean isFollowing() {
		return isFollowing;
	}

	public void setFollowing(boolean isFollowing) {
		this.isFollowing = isFollowing;
	}

	public boolean isGreedy() {
		return isGreedy;
	}

	public void setGreedy(boolean isGreedy) {
		this.isGreedy = isGreedy;
	}

	public abstract void update(double time, BlackPanther blackPanther);

	public abstract void knockBack(String direction, int stateSkill, boolean isBotHigher);

}
