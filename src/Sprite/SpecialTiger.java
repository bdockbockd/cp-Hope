package Sprite;

import Constant.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class SpecialTiger extends BlackPantherSprite {
	private Image image;
	private static final String name = "specialTiger";
	public static Image[] blackPantherImage = Images.bigTigerMotion;

	public SpecialTiger() {
		super(blackPantherImage[0]);
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
		this.blackPantherPosition = (this.blackPantherPosition + 1) % blackPantherImage.length;
		return (blackPantherImage)[blackPantherPosition];
	}

	public String getName() {
		return SpecialTiger.name;
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.getPositionX(), this.getPositionY(), this.getWidth() + 70, this.getHeight() + 70);
	}

}
