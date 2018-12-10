package Enemy;

import Constant.Audio;
import Constant.Images;
import Controller.LoopGame;
import Item.Bullet;
import Sprite.BlackPanther;
import UI.GamePause;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

public class GunMan extends BadHuman {
	
	private boolean isReadyToFire;
	public final static int SCORE = 5000;

	public GunMan() {
		this.setImage((Images.GUNMANR)[0]);
		this.setImageL(Images.GUNMANL);
		this.setImageR(Images.GUNMANR);
		this.setImageList(Images.GUNMANL);
		this.isReadyToFire = true;
		this.setMaximumKnockBackX(25000);
//		fire.start();
	}

	// update all time
	@Override
	public void update(double time) {
		if (this.isDead()) {
			this.setVelocity(0, 0);
			this.setImage(Images.enemyTomb);
			return;
		}
		this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
		this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
		if (this.getPositionX() > 5 && this.getPositionX() < 500) {
			this.setPositionX(5);
		} else if (this.getPositionX() < 1135 && this.getPositionX() > 800) {
			this.setPositionX(1135);
		}
		if (this.positionY < 180) {
			this.setPositionY(180);
		}
		if (this.positionY > 800 - this.getHeight() - 30) {
			this.setPositionY(800 - this.getHeight() - 30);
		}
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(this.getImage(), this.getPositionX(), this.getPositionY());
		if (!this.isDead()) {
			gc.setFill(Color.BLACK);
			gc.fillRect(this.getPositionX(), this.getPositionY() - 10, this.getImage().getWidth(), 10);
			gc.fillRect(this.getPositionX(), this.getPositionY() - 10, (this.getImage().getWidth())*(getHealth()/getMaxHealth()), 10);
			gc.setFill(Color.RED);
	    }
	}
	
	@Override
	public void update(double time, BlackPanther blackPanther) {
		if (this.isDead()) {
			this.setVelocity(0, 0);
			this.setImage(Images.enemyTomb);
			this.update(time);
			return;
		}
		this.setGreedy(false);
		this.setFollowing(false);


		double random = Math.random();
		if (random > HumanSprite.BOT_GREEDY_RATE ) {
			this.setGreedy(true);
			this.updateBotTypeGreedy(blackPanther);
		} else if (random > HumanSprite.BOT_FOLLOWING_RATE) {
			this.setFollowing(true);
			this.updateBotTypeFollowing(blackPanther);
		} else {
			this.randomBot(blackPanther);
		}
	}

	public void updateBotTypeGreedy(BlackPanther blackPanther) {
		double randomCount = (int) (Math.random() * 3);
		System.out.print("enemyGreedy bullet " + randomCount);
		for (int i = 0; i < (int)randomCount; i++) {
			System.out.print("enemyGreedy bullet " + i);
			if (this.getPositionX() > -50 && this.getPositionX() < 1275) {
				this.fireBullet();
			}
		}
	}

	public void updateBotTypeFollowing(BlackPanther blackPanther) {
		if ((this.getPositionY() > blackPanther.getPositionY())) {
			if (this.getPositionX() < 100) {
				this.setVelocityX(HumanSprite.MAX_RANDOM_SPEED);
			} else if (this.getPositionX() > 1175) {
				this.setVelocityX(-HumanSprite.MAX_RANDOM_SPEED);
			}
			this.setVelocityY(-Math.random() * HumanSprite.MAX_RANDOM_SPEED);
		} else {
			this.setVelocityY(Math.random() * HumanSprite.MAX_RANDOM_SPEED);
			if (this.getPositionX() < 100) {
				this.setVelocityX(Math.random() * HumanSprite.MAX_RANDOM_SPEED);
			} else if (this.getPositionX() > 1175) {
				this.setVelocityX(-Math.random() * HumanSprite.MAX_RANDOM_SPEED);
			}
		}
		if (this.getPositionX() > 0 && this.getPositionX() < 1250) {
			this.fireBullet();
		}
	}

	public void randomBot(BlackPanther blackPanther) {
		double random = Math.random();
		if (random > 0.90) {
			if (this.getFace() == "LEFT") {
				this.setVelocity(Math.random() * 100, Math.random() * 100 - 50);
			} else {
				this.setVelocity(+Math.random() * 100, Math.random() * 100 - 50);
			}
		} else {
			if (this.getPositionX() > 0 && this.getPositionX() < 1250) {
				this.fireBullet();
			}
		}
	}

	public void fireBullet() {
		if(this.isGreedy()) {
			this.setFire(true);
		}
		if (this.isReadyToFire) {
			Thread delayToFire = new Thread(() -> {
				try {
					this.isReadyToFire = false;
					Thread.sleep(4000);
					this.isReadyToFire = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			delayToFire.start();
		} else {
			return;
		}
		while (LoopGame.gamePause.isShowing()) {
			System.out.print("WAIT TO FIRE CAUS GAME PAUSED");
		}
		if (this.isDead()) {
			return;
		}
		if (this.getFace() == "LEFT") {
			new Bullet(this.getPositionX() - 100, this.getPositionY() + 50, (Images.BULLET)[0]);
		} else {
			new Bullet(this.getPositionX() + 100, this.getPositionY() + 50, (Images.BULLET)[1]);
		}
		Audio.ENEMY_FIRE.play();
	}

	public boolean isReadyToFire() {

		return this.isReadyToFire;
	}

	public void setFire(boolean isReady) {
		this.isReadyToFire = isReady;
	}

	public void knockBack(String direction, int stateSkill, boolean isBotHigher) {
		double veX = this.getVelocityX();
		double veY = this.getVelocityY();
		if (stateSkill == 2) {
			this.setVeKnockX(Math.random() * 500 + 10000);
			this.setVeKnockBackX(-Math.random() * 1500);
			if (isBotHigher) {
				this.setVeKnockY(Math.random() * 300 + 4000);
				this.setVeKnockBackY(-Math.random() * 1000);
			} else {
				this.setVeKnockY(-(Math.random() * 300 + 2000));
				this.setVeKnockBackY((Math.random() * 300));
			}
		} else {
			this.setVeKnockX(Math.random() * 300 + 2000);
			this.setVeKnockBackX(-(veKnockX - 500));
			this.setVeKnockY(((Math.random() > 0.5) ? -1 : 1) * (Math.random() * 300 + 200));
			this.setVeKnockBackY(-(veKnockY - 100));
		}
		if (this.isTomb() || this.isDead()) {
			this.setVelocity(0, 0);
			return;
		}

		LoopGame.CANUPDATEBOT = false;

		if (direction == "LEFT") {
			Thread knock = new Thread(() -> {
				try {
					this.setVelocity(-veKnockX, veKnockY);
					Thread.sleep(50);
					this.setVelocity(veKnockBackX, -veKnockBackY);
					Thread.sleep(50);
					this.setKnockBack(false);
					LoopGame.CANUPDATEBOT = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (this.isTomb()) {
					this.setVelocity(0, 0);
				} else {
					this.setVelocity(veX, veY);
				}
			});
			knock.start();
		} else {
			Thread knock = new Thread(() -> {
				try {
					this.setVelocity(veKnockX, -veKnockY);
					Thread.sleep(50);
					this.setVelocity(-veKnockBackX, veKnockBackY);
					Thread.sleep(50);
					this.setKnockBack(false);
					LoopGame.CANUPDATEBOT = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (this.isTomb()) {
					this.setVelocity(0, 0);
				} else {
					this.setVelocity(veX, veY);
				}
			});

			knock.start();
		}
	}
}
