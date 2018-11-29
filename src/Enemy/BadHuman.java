package Enemy;

import java.util.ArrayList;

import Sprite.BlackTiger;
import Sprite.Sprite;
import application.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class BadHuman extends HumanSprite implements Runnable  {

	private String name;
    private static ArrayList<BadHuman> badList = new ArrayList<BadHuman>();
    
	public BadHuman() {
		super((Images.humanMotionR)[0], Images.humanMotionR, Images.humanMotionL, Images.humanMotionR);
		// TODO Auto-generated constructor stub
		Thread t = new Thread(()->{
			run();
		});
		t.start();
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
		try {
			this.setFace("LEFT");
			this.setVelocity(-100,0);
			Thread.sleep(1000);
			this.setFace("RIGHT");
			this.setVelocity(100, 0);
			Thread.sleep(1000);
		}
		catch(Exception e) {
		}
		}
	}

}

