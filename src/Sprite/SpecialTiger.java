package Sprite;

import application.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class SpecialTiger extends TigerSprite{
	  private Image image;
	  private static final String name = "specialTiger";
	  public static Image[] tigerImage = Images.bigTigerMotion;

		public SpecialTiger() {
			super(tigerImage[0]);
		}
		
		public void setImage(Image image) {
			this.image = image;
	        setWidth(image.getWidth());
	        setHeight(image.getHeight());
		}
		@Override
		public Image getImage() {
			// TODO Auto-generated method stub
			return this.image;
		}
		
	    public Image nextPosition() {
	    	this.tigerPosition = (this.tigerPosition+1)%tigerImage.length;
	    	return (tigerImage)[tigerPosition];
	    }

		public String getName() {
			return SpecialTiger.name;
		}
		
	    public Rectangle2D getBoundary()
	    {
	        return new Rectangle2D(this.getPositionX(),this.getPositionY(),this.getWidth()+70,this.getHeight()+70);
	    }

}
