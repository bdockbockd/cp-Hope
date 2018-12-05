package Enemy;

import java.util.ArrayList;

import Controller.Main;
import Controller.StatusBar;
import Sprite.BlackTiger;
import Sprite.Sprite;
import application.Audio;
import application.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class BadHuman extends HumanSprite  {

	private String name = "enemy";
    private static ArrayList<BadHuman> badList = new ArrayList<BadHuman>();
    private long sleepTime;
    public static BlackTiger instanceTiger;
    private boolean isDamaged;
    private boolean isTomb = false;
    private boolean waitToHit = false;
    public int time=0;
    
	public BadHuman() {
		super((Images.humanMotionR)[0], Images.humanMotionR, Images.humanMotionL, Images.humanMotionR);
		// TODO Auto-generated constructor stub
//		Thread a = new Thread(()-> {
//			try { 
//				Thread.sleep(3000);
//				this.update();
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
		
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
    
    public static void generatelistBot(int num) {
    	ArrayList<BadHuman> bad = new ArrayList<BadHuman>();
    	while(num !=0) {
    		bad.add(generateRandom());
    		num--;
    	}
    	BadHuman.setbadList(bad);
    }
    
    public static ArrayList<BadHuman> getbadList() {
		return BadHuman.badList;
	}

	public static void setbadList(ArrayList<BadHuman> list) {
		BadHuman.badList = list;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX()-100, this.getPositionY()-100 );
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
    	        + " Width: [" + this.getRealWidth() + "," + this.getRealHeight() + "]");
    }
    
    public double getRealWidth() {
    	return 64;
    }
    
    public double getRealHeight() {
    	return 89;
    }
    
    public double getRealX() {
    	return this.getPositionX()-100;
    	//73
    }
  
    public double getRealY() {
    	return this.getPositionY()-312;
    	//150
    }
    
    public void update(double time, BlackTiger tiger)
    {
    	if(this.isDead()) return;
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
        
        this.setSleepTime((long)Math.random()*500);
        if(this.intersect(tiger) == false) {
//        Thread t = new Thread(()->{
//        		try {
        			if(tiger.getPositionX()+220 < this.getPositionX()) {
    					this.setFace("LEFT");
        				this.nextPosition(this.getFace());

        				if(tiger.getPositionY()+175 < this.getPositionY() ) {
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
        				if(tiger.getPositionY()+175 < this.getPositionY()) {
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
        
    public static void checkAttackHuman(BlackTiger tiger) {
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

	private void attack(BlackTiger tiger) {
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

	public static void removeEnemy() {
		if(BadHuman.getbadList().size()==0) return ;
		for(int i =0;i<BadHuman.getbadList().size();i++) {
			BadHuman enemy = BadHuman.getbadList().get(i);
			if(enemy.isDead() && !enemy.isTomb) {
				Audio.ENEMY_DEAD.play();
				Controller.ScoreBoard.addScore(1000);
				enemy.isTomb = true;
				enemy.setImage(Images.enemyTomb);
				Thread t = new Thread(new Runnable() {
					public void run(){ 
						BadHuman enemy1 = enemy;

							try {
								Thread.sleep(3000);
							}
							catch(InterruptedException e){
								e.printStackTrace();
							}
							BadHuman.getbadList().remove(enemy1);
						
					}
				});
				t.start();
			}
		}
	} 
    
    
    
}



