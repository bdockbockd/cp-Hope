package Item;


import java.util.ArrayList;

import Sprite.BlackPanther;
import Sprite.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Item extends Rectangle{
	
	public static ArrayList<Item> itemList = new ArrayList<Item>();
	private Image image;
	
	public Item(double x, double y,Image image) {
		super(x, y, image.getWidth(), image.getHeight());
		this.image = image;
		itemList.add(this);
	}
	
	public abstract void itemUse(BlackPanther blackPanther);
	
	public static void checkItemUse(BlackPanther blackPanther) {
		for(int i = 0;i < itemList.size();i++){
			if(itemList.get(i).getBoundary().intersects(blackPanther.getBoundary())) {
				itemList.get(i).itemUse(blackPanther);
				itemList.remove(itemList.get(i));
			}
		}
	}
	
	public Image getImage() {
		return this.image;
	}
	public static void render(GraphicsContext gc) {
		for(int i = 0;i < itemList.size();i++){
			gc.drawImage(itemList.get(i).getImage(), itemList.get(i).getPositionX(), itemList.get(i).getPositionY());
		}
	}
}
