package Enemy;

import Constant.Audio;
import Constant.Images;
import Controller.EnemyGen;
import Item.Bullet;
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
	
	public void nextPosition(String direction) {
    	if(this.getFace().equals("LEFT")) {
    		this.setImage((this.getImageL())[this.positionL]);
    	} else {
    		this.setImage((this.getImageR())[this.positionR]);
    	}
    }
	
	public void update(double time) {
		if(this.isDead()) {
			this.setVelocity(0, 0);
			this.setImage(Images.enemyTomb);
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
				this.setImage(Images.enemyTomb);
				this.update(time);
				return;
			}
			if(this.isReadyToTrap) {
				Thread delayToThrow = new Thread(()->{
					try {
						this.isReadyToTrap = false;
						this.setFace(this.getFace());
						this.nextPosition(this.getFace());
						Thread.sleep(1000);
						this.throwTrap();
						this.isReadyToTrap = true;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				delayToThrow.start();
			}
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
			System.out.print("THROW");
			Audio.ENEMY_TRAP.play();
			if(this.getFace() == "LEFT") {
				new Trap(this.getPositionX()-100, this.getPositionY(), (Images.TRAPITEM)[0]);
			} else {
				new Trap(this.getPositionX()+100, this.getPositionY(), (Images.TRAPITEM)[1]);
			}
		}
		
		public boolean isReadyToTrap() {
			return this.isReadyToTrap;
		}
		public void setFire(boolean isReady) {
			this.isReadyToTrap = isReady;
		}
}
