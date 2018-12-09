package Enemy;

import Constant.Audio;
import Constant.Images;
import Controller.EnemyGen;
import Item.Bullet;
import Sprite.BlackPanther;
import UI.GamePause;

public class TrapMan extends BadHuman {
	private boolean isReadyToTrap;

	public TrapMan() {
		this.setImage((Images.TRAPMANL)[0]);
		this.setImageL(Images.TRAPMANL);
		this.setImageR(Images.TRAPMANR);
		this.setImageList(Images.TRAPMANR);
	}
	
	public void update(double time) {
		if(this.isDead()) {
			return;
		}
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
        if(this.getPositionX() > 25 && this.getPositionX() < 500) {
        	this.setPositionX(25);
        } else if(this.getPositionX()< 1100 && this.getPositionX() > 800) {
        	this.setPositionX(1100);
        }
        if(this.positionY < 180) {
        	this.setPositionY(180);
        }
        if(this.positionY > 800-this.getHeight()-30) {
        	this.setPositionY(800-this.getHeight()-30);
        }
    }
	
	//update every one time
		@Override
		public void update(double time, BlackPanther tiger) {
			if(this.isDead()) {
				this.setVelocity(0, 0);
				this.update(time);
				return;
			}
			if(this.isReadyToTrap) {
				Thread delayToFire = new Thread(()->{
					try {
						this.isReadyToTrap = false;
						Thread.sleep(1000);
						this.throwTrap();
						this.isReadyToTrap = true;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				delayToFire.start();
			}
//			this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
	        if( (this.getPositionY() > tiger.getPositionY())) {
	        	if(this.getPositionX() < 100) {
	        		this.setVelocityX(200);
	        	} else if (this.getPositionX() > 1175) {
	        		this.setVelocityX(-200);
	        	}
	        	this.setVelocityY(-200);
	        } else {
	        	this.setVelocityY(200);
	        	if(this.getPositionX() < 100) {
	        		this.setVelocityX(200);
	        	} else if (this.getPositionX() > 1175) {
	        		this.setVelocityX(-200);
	        	}
	        }
		}
		
		public void throwTrap() {
			if(this.isDead() || GamePause.isPause == true || GamePause.isReGame == true || EnemyGen.getbadList().size() == 0) {
				return;
			}
			Audio.ENEMY_FIRE.play();
			if(this.getFace() == "LEFT") {
				new Bullet(this.getPositionX()-100, this.getPositionY(), (Images.BULLET)[0]);
			} else {
				new Bullet(this.getPositionX()+100, this.getPositionY(), (Images.BULLET)[1]);
			}
		}
		
		public boolean isReadyToTrap() {
			return this.isReadyToTrap;
		}
		public void setFire(boolean isReady) {
			this.isReadyToTrap = isReady;
		}
}
