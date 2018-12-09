package Enemy;



import Constant.Images;
import Controller.LoopGame;
import Sprite.BlackPanther;

public class BadHuman extends HumanSprite  {

    public int time=0;
	double veKnockX, veKnockY, veKnockBackX, veKnockBackY;

    
	public BadHuman() {
		super((Images.humanMotionR)[0], Images.humanMotionR, Images.humanMotionL, Images.humanMotionR);
		// TODO Auto-generated constructor stub
	}

	public void nextPosition(String direction) {
    	if(this.getFace().equals("LEFT")) {
    		this.setImage((this.getImageL())[0]);
    		this.setFace(direction);
    	} else {
    		this.setImage((this.getImageR())[0]);
    	}
    }

    //bot update
    public void update(double time, BlackPanther tiger)
    {
    	if(this.isDead()) return;
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);

//        if(this.intersect(tiger) == false) {
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
//        }
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
		if(this.isTomb() || this.isDead())
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
    
}



