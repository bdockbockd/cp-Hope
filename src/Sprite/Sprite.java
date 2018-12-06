package Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite extends Rectangle {
	
	private Image image;
	private Image[] imageL;
	private Image[] imageR;
	private Image[] imageList;
	protected String face;
	private int duration = 3;
	protected int positionL = 0;
	protected int positionR = 0;
	protected int skillPositionL = 0;
	protected int skillPositionR = 0;

	
    private double velocityX, velocityY;
    
    public Sprite(Image image, Image[] imageL, Image[] imageR) {
    	super(0,0,image.getWidth(),image.getHeight());
    	this.setFace("Right");
    	this.setImage(image);
    	this.setImageL(imageL);
    	this.setImageR(imageR);
    }
    
	public Image[] getImageL() {
		return imageL;
	}

	public int getSkillPositionL() {
		return skillPositionL;
	}

	public void setSkillPositionL(int skillPositionL) {
		this.skillPositionL = skillPositionL;
	}

	public int getSkillPositionR() {
		return skillPositionR;
	}

	public void setSkillPositionR(int skillPositionR) {
		this.skillPositionR = skillPositionR;
	}

	public int getPositionL() {
		return positionL;
	}


	public void setPositionL(int positionL) {
		this.positionL = positionL;
	}


	public int getPositionR() {
		return positionR;
	}


	public void setPositionR(int positionR) {
		this.positionR = positionR;
	}


	public void setImageL(Image[] imageL) {
		this.imageL = imageL;
	}
	
	public Image[] getImageR() {
		return imageR;
	}

	public void setImageR(Image[] imageR) {
		this.imageR = imageR;
	}



	public double getVelocityX() {
    	return this.velocityX;
    }
    public void setVelocityX(double velocityX) {
    	this.velocityX = velocityX;
    }
    public double getVelocityY() {
    	return this.velocityY;
    }
    public void setVelocityY(double velocityY) {
    	this.velocityY = velocityY;
    }
    
   
	
	public void setImage(Image i) {
		this.image = i;
	}
	public Image getImage() {
		 return this.image;
	}
	
	public abstract void update(double time);
	public abstract void render(GraphicsContext gc);



	public Image[] getImageList() {
		return imageList;
	}



	public void setImageList(Image[] imageList) {
		this.imageList = imageList;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		if (!(face.equals(this.getFace()))) {
			this.face = face;
			this.setPositionR(0);
			this.setPositionL(0);
			return;
		}
		if(face == "LEFT") {
			this.setPositionL((this.getPositionL()+1)%3);
		} else {
			this.setPositionR((this.getPositionR()+1)%3);
		}

	}
	
}
