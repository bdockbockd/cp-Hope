package Enemy;

import Constant.Audio;
import Constant.Images;
import Controller.EnemyGen;
import Controller.LoopGame;
import Item.Bullet;
import Item.Item;
import Item.Trap;
import Sprite.BlackPanther;
import UI.GamePause;

public class TrapMan extends BadHuman {
	private boolean isReadyToTrap;

	public TrapMan() {
		this.setImage((Images.TRAPMANL)[0]);
		this.setImageL(Images.TRAPMANL);
		this.setImageR(Images.TRAPMANR);
		this.setImageList(Images.TRAPMANR);
		this.isReadyToTrap = true;
	}

	public void update(double time) {
		if (this.isDead()) {
			this.setVelocity(0, 0);
			this.setImage(Images.enemyTomb);
			return;
		}
		this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
		this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
		if (this.getPositionX() > 25 && this.getPositionX() < 500) {
			this.setPositionX(25);
		} else if (this.getPositionX() < 1100 && this.getPositionX() > 800) {
			this.setPositionX(1100);
		}
		if (this.positionY < 205) {
			this.setPositionY(205);
		}
		if (this.positionY > 800 - this.getHeight() - 30) {
			this.setPositionY(800 - this.getHeight() - 30);
		}
	}

	// update every one time
	@Override
	public void update(double time, BlackPanther blackPanther) {
		if (this.isDead()) {
			this.setVelocity(0, 0);
			this.setImage(Images.enemyTomb);
			this.update(time);
			return;
		}

		// random to trap
		double random = Math.random();
		if (random > HumanSprite.BOT_GREEDY_CHANCE + 0.5) {
			this.updateBotTypeGreedy(blackPanther);
		} else if (random > HumanSprite.BOT_FOLLOWING_CHANCE) {
			this.updateBotTypeFollowing(blackPanther);
		} else {
			this.randomBot(blackPanther);
		}
	}

	public void updateBotTypeGreedy(BlackPanther blackPanther) {
		for (int i = 0; i < (int) Math.random() * 1; i++) {
			if (this.getPositionX() > 0 && this.getPositionX() < 1250) {
				this.throwTrap();
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
				this.setVelocityX(Math.random() * HumanSprite.MAX_RANDOM_SPEED - 50);
			} else if (this.getPositionX() > 1175) {
				this.setVelocityX(-Math.random() * HumanSprite.MAX_RANDOM_SPEED);
			}
		}
		if (this.getPositionX() > 0 && this.getPositionX() < 1250) {
			this.throwTrap();
		}
	}

	public void randomBot(BlackPanther blackPanther) {
		double random = Math.random();
		if (random < 0.90) {
			if (this.getFace() == "LEFT") {
				this.setVelocity(Math.random() * 100, Math.random() * 100 - 50);
			} else {
				this.setVelocity(+Math.random() * 100, Math.random() * 100 - 50);
			}
			return;
		} else {
			if (this.getPositionX() > 0 && this.getPositionX() < 1250) {
				this.throwTrap();
			}
		}
	}

	public void throwTrap() {
		if (LoopGame.gamePause.isShowing()) {
			while (LoopGame.gamePause.isShowing()) {
				System.out.print("WAIT TO THROW CAUS GAME PAUSED");
			}
		}
		if (this.isDead()) {
			return;
		}
		if (this.isReadyToTrap) {
			Thread delayToTrap = new Thread(() -> {
				try {
					this.isReadyToTrap = false;
					Thread.sleep(4000);
					this.isReadyToTrap = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			delayToTrap.start();
		} else {
			return;
		}
		this.setFace(this.getFace());
		this.nextPosition(this.getFace());
		System.out.print("THROW");
		Audio.ENEMY_TRAP.play();
		if (this.getFace() == "LEFT") {
			new Trap(this.getPositionX() - 100, this.getPositionY() + 50, (Images.TRAPITEM)[0]);
		} else {
			new Trap(this.getPositionX() + 100, this.getPositionY() + 50, (Images.TRAPITEM)[1]);
		}
	}

	public boolean isReadyToTrap() {
		return this.isReadyToTrap;
	}

	public void setFire(boolean isReady) {
		this.isReadyToTrap = isReady;
	}
}
