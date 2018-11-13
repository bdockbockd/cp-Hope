package Sprite;

import application.Images;
import javafx.scene.image.Image;

public class BlackTiger extends TigerSprite {
	
	private static final String name = "blackTiger";
	private static Image[] tigerImage = Images.blackTigerMotion;

	public BlackTiger() {
		super(tigerImage[0]);
	}
	
    public Image nextPosition() {
    	this.tigerPosition = (this.tigerPosition+1)%tigerImage.length;
    	return (tigerImage)[tigerPosition];
    }
    
    public String getName() {
    	return BlackTiger.name;
    }

}
