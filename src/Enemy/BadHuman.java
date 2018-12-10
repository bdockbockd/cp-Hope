package Enemy;



import Constant.Images;
import Controller.LoopGame;
import Sprite.BlackPanther;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BadHuman extends HumanSprite  {

    public int time=0;
	double veKnockX, veKnockY, veKnockBackX, veKnockBackY;

	public BadHuman() {
		super((Images.humanMotionR)[0], Images.humanMotionR, Images.humanMotionL, Images.humanMotionR);
		// TODO Auto-generated constructor stub
	}

	public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX(), this.getPositionY() );
        if(!this.isDead())
        {
	        gc.setFill(Color.BLACK);
	        gc.fillRect(this.getPositionX()+10, this.getPositionY()-10, getMaxHealth()/10, 5);
	        gc.setFill(Color.RED);
	        gc.fillRect(this.getPositionX()+10, this.getPositionY()-10, getHealth()/10, 5);
	      
        }
    }
    //bot update
    public void update(double time, BlackPanther blackPanther)
    {
    	
    	if(this.isDead()) {
    		return;
    	}
    	if(this.isFollowing()) {
    		this.updateBotTypeFollowing(blackPanther);
    	} else if(this.isGreedy()) {
    		this.updateBotTypeGreedy(blackPanther);
    	} else {
    		this.updateSteady(blackPanther);
    	}
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
        
    }

	public void knockBack(String direction, int stateSkill, boolean isBotHigher) {
		double veX = this.getVelocityX();
		double veY = this.getVelocityY();
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
		if(this.isTomb() || this.isDead()){
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
			if(this.isTomb()) {
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
				if(this.isTomb()) {
					this.setVelocity(0, 0);
				} else {
					this.setVelocity(veX, veY);
				}			});
			
			knock.start();
		}
	}

	public double getVeKnockX() {
		return veKnockX;
	}

	public void setVeKnockX(double veKnockX) {
		this.veKnockX = veKnockX;
	}

	public double getVeKnockY() {
		return veKnockY;
	}

	public void setVeKnockY(double veKnockY) {
		this.veKnockY = veKnockY;
	}

	public double getVeKnockBackX() {
		return veKnockBackX;
	}

	public void setVeKnockBackX(double veKnockBackX) {
		this.veKnockBackX = veKnockBackX;
	}

	public double getVeKnockBackY() {
		return veKnockBackY;
	}

	public void setVeKnockBackY(double veKnockBackY) {
		this.veKnockBackY = veKnockBackY;
	}	
    
}



