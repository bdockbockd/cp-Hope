package Enemy;

import Constant.Audio;
import Constant.Images;
import Controller.EnemyGen;
import Controller.LoopGame;
import Item.Bullet;
import Item.Item;
import Sprite.BlackPanther;
import UI.GamePause;
import javafx.scene.media.AudioClip;

public class GunMan extends BadHuman {
	private boolean isReadyToFire;
	
	public GunMan() {
		this.setImage((Images.GUNMANR)[0]);
		this.setImageL(Images.GUNMANL);
		this.setImageR(Images.GUNMANR);
		this.setImageList(Images.GUNMANL);
		this.isReadyToFire = true;
		this.s
//		fire.start();
	}
	
	//update all time
	@Override
	public void update(double time) {
		if(this.isDead()) {
			this.setVelocity(0, 0);
			this.setImage(Images.enemyTomb);
			return;
		}
	        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
	        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
	        if(this.getPositionX() > 5 && this.getPositionX() < 500) {
	        	this.setPositionX(5);
	        } else if(this.getPositionX()< 1135 && this.getPositionX() > 800) {
	        	this.setPositionX(1135);
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
	public void update(double time, BlackPanther blackPanther) {
		if(this.isDead()) {
			this.setVelocity(0, 0);
			this.setImage(Images.enemyTomb);
			this.update(time);
			return;
		}

		double random = Math.random();
		if(random > HumanSprite.BOT_GREEDY_RATE + 0.5) {
			this.updateBotTypeGreedy(blackPanther);
		} else if(random > HumanSprite.BOT_FOLLOWING_RATE) {
			this.updateBotTypeFollowing(blackPanther);
		} else {
			this.randomBot(blackPanther);
		}
   
	}
	
	
	public void updateBotTypeGreedy(BlackPanther blackPanther) {
		for(int i =0;i<(int)Math.random()*1;i++) {
			if(this.getPositionX() > 0 && this.getPositionX() <1250) {
				this.fireBullet();
			}		
		}
	}
	
    public void updateBotTypeFollowing(BlackPanther blackPanther) {
		if((this.getPositionY() > blackPanther.getPositionY())) {
    		if(this.getPositionX() < 100) {
          		this.setVelocityX(HumanSprite.MAX_RANDOM_SPEED);
          	} else if (this.getPositionX() > 1175) {
          		this.setVelocityX(-HumanSprite.MAX_RANDOM_SPEED);
          	}
          	this.setVelocityY(-Math.random()*HumanSprite.MAX_RANDOM_SPEED);
    	} else {
          	this.setVelocityY(Math.random()*HumanSprite.MAX_RANDOM_SPEED);
          	if(this.getPositionX() < 100) {
          		this.setVelocityX(Math.random()*HumanSprite.MAX_RANDOM_SPEED);
          	} else if (this.getPositionX() > 1175) {
          		this.setVelocityX(-Math.random()*HumanSprite.MAX_RANDOM_SPEED);
          	}
        }
		if(this.getPositionX() > 0 && this.getPositionX() <1250) {
			this.fireBullet();
		}    	  
    }
	    
    public void randomBot(BlackPanther blackPanther) {
    	double random = Math.random();
	  	if(random > 0.90) {
	  		if(this.getFace() == "LEFT") {
		  		this.setVelocity(Math.random()*100, Math.random()*100-50);
	  		} else {
		  		this.setVelocity(+Math.random()*100, Math.random()*100-50);
	  		}
	    } else {
	    	if(this.getPositionX() > 0 && this.getPositionX() <1250) {
				this.fireBullet();
			}	    
	    }
	}
	public void fireBullet() {
		if(this.isReadyToFire ) {
			Thread delayToFire = new Thread(()->{
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
		while(LoopGame.gamePause.isShowing()) {
			System.out.print("WAIT TO FIRE CAUS GAME PAUSED");
		}
		if(this.isDead()) {
			return;
		}
		Audio.ENEMY_FIRE.play();
		if(this.getFace() == "LEFT") {
			new Bullet(this.getPositionX()-100, this.getPositionY()+50, (Images.BULLET)[0]);
		} else {
			new Bullet(this.getPositionX()+100, this.getPositionY()+50, (Images.BULLET)[1]);
		}
	}
	
	public boolean isReadyToFire() {
		
		return this.isReadyToFire;
	}
	public void setFire(boolean isReady) {
		this.isReadyToFire = isReady;
	}
}
