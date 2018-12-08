package Enemy;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.midi.ControllerEventListener;

import Constant.Audio;
import Constant.Images;
import Controller.LoopGame;
import Item.HealthPotion;
import Item.Item;
import Item.Meat;
import Item.SuperPotion;
import Sprite.BlackPanther;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class BadHuman extends HumanSprite  {

	private String name = "enemy";
    private static CopyOnWriteArrayList<BadHuman> badList = new CopyOnWriteArrayList<BadHuman>();
    private long sleepTime;
    public static BlackPanther instanceTiger;
    private boolean isDamaged;
    private boolean isTomb = false;
    private boolean waitToHit = false;
    public int time=0;
    private boolean knockBack = false;
	double veKnockX, veKnockY, veKnockBackX, veKnockBackY;

    
	public BadHuman() {
		super((Images.humanMotionR)[0], Images.humanMotionR, Images.humanMotionL, Images.humanMotionR);
		// TODO Auto-generated constructor stub
	}


	public void nextPosition(String direction) {
    	if(this.getFace().equals("LEFT")) {
    		this.setImage((this.getImageL())[0]);
    	} else {
    		this.setImage((this.getImageR())[0]);
    	}
    }
    
    public static BadHuman generateRandom() {
    	BadHuman badHuman = new BadHuman();
    	badHuman.setImage((Images.humanMotionL)[0]);
    	double px,py;
    	if(Math.random() < 0.5) {
	        px = Math.random()*400+1180;
    	} else {
    		px = 0-Math.random()*400;
    	}
        py = Math.random()*600+215;
        badHuman.setPosition(px, py);
    
        return badHuman;
    }
    
    public static void addBot() {
    	BadHuman.getbadList().add(BadHuman.generateRandom());
    }
    
    public static void generatelistBot(int num) {
    	CopyOnWriteArrayList<BadHuman> bad = new CopyOnWriteArrayList<BadHuman>();
    	while(num !=0) {
    		bad.add(generateRandom());
    		num--;
    	}
    	BadHuman.setbadList(bad);
    }
    
    public static CopyOnWriteArrayList<BadHuman> getbadList() {
		return BadHuman.badList;
	}

	public static void setbadList(CopyOnWriteArrayList<BadHuman> list) {
		BadHuman.badList = list;
	}


    
    //bot update
    public void update(double time, BlackPanther tiger)
    {
    	if(this.isDead()) return;
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
        
        this.setSleepTime((long)Math.random()*500);
        if(this.intersect(tiger) == false) {
        			if(tiger.getPositionX()+120 < this.getPositionX()) {
    					this.setFace("LEFT");
        				this.nextPosition(this.getFace());
        				if(tiger.getPositionY()+75 < this.getPositionY() ) {
            				this.setVelocity(-Math.random()*200,-Math.random()*200);
        				} else {
        					this.setVelocity(-Math.random()*200,Math.random()*200);

        				}
      
        			} else {
        				this.setFace("RIGHT");
        				this.nextPosition(this.getFace());
        				if(tiger.getPositionY()+75 < this.getPositionY()) {
            				this.setVelocity(Math.random()*200,-Math.random()*200);

        				} else {
        					this.setVelocity(Math.random()*200,Math.random()*200);

        				}	
        			}
        }
        
       
//        		}
//        		catch(Exception e) {
//        			
//        		}
//        });
//    t.start();
    }
        
    public static void checkAttackHuman(BlackPanther tiger) {
    	for(int i=0; i< BadHuman.badList.size();i++) {
    		BadHuman enemy = BadHuman.getbadList().get(i);
    		if(enemy.intersect(tiger)) {
    			if(!(enemy.waitToHit)) {
//    				enemy.setTimeSec(time);
    				enemy.attack(tiger);
    				Thread a = new Thread (()->{
    					try {
    						enemy.waitToHit = true;
    						Thread.sleep(2000);
    						enemy.waitToHit = false;
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				});
    				a.start();
    			}
    		}
    		
    	}
    	
    }

	private void attack(BlackPanther tiger) {
		// TODO Auto-generated method stub
		tiger.takeDamage(this.getDamage());
		//StatusBar.resetProgress();
	}


	public long getSleepTime() {
		return sleepTime;
	}


	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	public void genItem(double positionX, double positionY) {
		int random = (int)(Math.random()*3);
		if(random == 0)
		{
			new Meat(positionX,positionY);
		} else if(random == 1) {
			new HealthPotion(positionX,positionY);
		} else if(random == 2){
	     	 new SuperPotion(positionX,positionY);
		} 
	}

	public static void removeEnemy() {
		if(BadHuman.getbadList().size()==0) return ;
		for(int i =0;i<BadHuman.getbadList().size();i++) {
			BadHuman enemy = BadHuman.getbadList().get(i);
			if(enemy.isDead() && !(enemy.isTomb)) {
				Audio.ENEMY_DEAD.play();
				Controller.ScoreBoard.addScore(1000);
				enemy.isTomb = true;
				Thread t = new Thread(()-> {
							try {
								enemy.setImage(Images.enemyTomb);  
								enemy.genItem(enemy.getPositionX(), enemy.getPositionY());
								Thread.sleep(1000);
								BadHuman.getbadList().remove(enemy);
							}
							catch(InterruptedException e){
								e.printStackTrace();
							}
				});
				t.start();
			}
		}
	}


	public boolean isKnockBack() {
		return knockBack;
	}


	public void setKnockBack(boolean knockBack) {
		this.knockBack = knockBack;
	}


	public void knockBack(String direction, int stateSkill, boolean isBotHigher) {
		// TODO Auto-generated method stub
		double veX = this.getVelocityX();
		double veY = this.getVelocityY();
		System.out.print(stateSkill);
		if(stateSkill == 2) {
			veKnockX = Math.random()*500 + 6000;
			veKnockBackX = -Math.random()*500;
			if(isBotHigher) {
				veKnockY = Math.random()*300 + 2000;
				veKnockBackY = -Math.random()*300;
			 } else {
				veKnockY = -(Math.random()*300 + 2000);
				veKnockBackY = (Math.random()*300);
			 }
			
		} else {
			veKnockX = Math.random()*300 +700;
			veKnockBackX = -(veKnockX - 500);
			veKnockY = ((Math.random()>0.5) ? -1 : 1)*(Math.random()*300+100);
			veKnockBackY = -(veKnockY - 100);
		}
		if(this.isTomb || this.isDead())
		{
			this.setVelocity(0, 0);
			return;
		}
	
		LoopGame.CANUPDATEBOT = false;

		if(direction == "LEFT" ) {
		Thread knock = new Thread(()->{
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
			if(this.isTomb) {
				this.setVelocity(0, 0);
			} else {
				this.setVelocity(veX, veY);
			}
		});
		knock.start();
		} else {
			Thread knock = new Thread(()->{
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
				if(this.isTomb) {
					this.setVelocity(0, 0);
				} else {
					this.setVelocity(veX, veY);
				}			});
			
			knock.start();
		}
	}
		  
	public void setName(String name) {
		this.name = name;
	}

	public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX(), this.getPositionY() );
    }
    
    public String getName() {
    	return this.name;
    }
    @Override
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(this.getRealX(),this.getRealY(),this.getRealWidth(),this.getRealHeight());
    }
    
    @Override
    public void printBoundary() {
    	System.out.println( "name: "+this.getName()+" Position: [" + this.getRealX() + "," + this.getRealY() + "]" 
    	        + " Width: [" + this.getWidth() + "," + this.getHealth() + "]");
    }
    
    public double getRealWidth() {
    	return 64;
    }
    
    public double getRealHeight() {
    	return 89;
    }
    
    public double getRealX() {
    	return this.getPositionX();
    	//73
    }
  
    public double getRealY() {
    	return this.getPositionY();
    	//150
    }
    
}



