package Enemy;

import java.util.ArrayList;

import Sprite.BlackTiger;
import Sprite.Sprite;
import application.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class BadHuman extends HumanSprite  {

	private String name;
    private static ArrayList<BadHuman> badList = new ArrayList<BadHuman>();
    private long sleepTime;
    public static BlackTiger instanceTiger;
    private boolean isDamaged;
    
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
    		this.setImage((this.getImageL())[this.getPositionL()]);
    	} else {
    		this.setImage((this.getImageR())[this.getPositionR()]);
    	}
    }
    
    public static BadHuman generateRandom() {
    	BadHuman bad = new BadHuman();
    	bad.setImage((Images.humanMotionL)[0]);
        double px = Math.random()*1180+100;
        double py = Math.random()*400+305;
        bad.setPosition(px, py);
        
        return bad;
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
    
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(this.getPositionX(),this.getPositionY(),this.getWidth(),this.getHeight());
    }
    public void update(double time, BlackTiger tiger)
    {
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
        
        this.setSleepTime((long)Math.random()*500);
        if(this.intersect(tiger) == false) {
//        Thread t = new Thread(()->{
//        		try {
        			if(tiger.getPositionX()+220 < this.getPositionX()) {
    					this.setFace("LEFT");
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
        


	public long getSleepTime() {
		return sleepTime;
	}


	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}
    
    
    
}



