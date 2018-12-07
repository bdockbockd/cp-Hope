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
        double px = Math.random()*1180+100;
        double py = Math.random()*400+305;
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
    
    
    //bot update
    public void update(double time, BlackPanther tiger)
    {
    	if(this.isDead()) return;
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
        
        this.setSleepTime((long)Math.random()*500);
        if(this.intersect(tiger) == false) {
//        Thread t = new Thread(()->{
//        		try {
        			if(tiger.getPositionX()+120 < this.getPositionX()) {
    					this.setFace("LEFT");
        				this.nextPosition(this.getFace());
        				if(tiger.getPositionY()+75 < this.getPositionY() ) {
            				this.setVelocity(-Math.random()*200,-Math.random()*200);
//	          				try {
//								Thread.sleep((long) (Math.random()*1000));
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//            				this.setVelocity(0, 0);
        				} else {
        					this.setVelocity(-Math.random()*200,Math.random()*200);
//	          				try {
//								Thread.sleep((long) (Math.random()*1000));
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//            				this.setVelocity(0, 0);
        				}
      
        			} else {
        				this.setFace("RIGHT");
        				this.nextPosition(this.getFace());
        				if(tiger.getPositionY()+75 < this.getPositionY()) {
            				this.setVelocity(Math.random()*200,-Math.random()*200);
//	          				try {
//								Thread.sleep((long) (Math.random()*1000));
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//            				this.setVelocity(0, 0);
        				} else {
        					this.setVelocity(Math.random()*200,Math.random()*200);
//	          				try {
//								Thread.sleep((long) (Math.random()*1000));
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//          				this.setVelocity(0, 0);
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


	public void KnockBack(String direction) {
		// TODO Auto-generated method stub
		double veX = this.getVelocityX();
		double veY = this.getVelocityY();
		
		if(direction == "LEFT" ) {
		Thread knock = new Thread(()->{
			try {
				this.setVelocityX(-700);
				Thread.sleep(50);
				this.setVelocityX(700);
				Thread.sleep(50);
				this.setKnockBack(false);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setVelocity(veX, veY);
		});
		knock.start();
		} else {
			Thread knock = new Thread(()->{
				try {
					this.setVelocityX(700);
					Thread.sleep(50);
					this.setVelocityX(-700);
					Thread.sleep(50);
					this.setKnockBack(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.setVelocity(veX, veY);
			});
			
			knock.start();
		}
	}
		  
}



