package Item;


import java.util.ArrayList;

import Constant.Images;
import Sprite.BlackPanther;
import Sprite.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Item extends Rectangle implements DisappearObject   {
	
	public static ArrayList<Item> itemList = new ArrayList<Item>();
	public static double spaceX = 2000;
	public static double spaceY = 2000;
	private Image disappearImage = Images.spacePic;
	private Image constantImage;
	private Image image;
	private int timeCount;
	
	public Item(double x, double y,Image image) {
		super(x, y, image.getWidth(), image.getHeight());
		this.constantImage = image;
		this.image = image;
		
		itemList.add(this);
		Thread disappear = new Thread(()-> {
			this.timeCount = TIMES;
			while(this.timeCount!=0) {
				try {
					if(this.timeCount>=3) {
						Thread.sleep(1000);
					} else if (this.timeCount == 2){
						this.image = this.disappearImage;
						Thread.sleep(250);
						this.image = this.constantImage;
						Thread.sleep(250);
						this.image = this.disappearImage;
						Thread.sleep(250);
						this.image = this.constantImage;
						Thread.sleep(250);
						this.image = this.disappearImage;
					} else {
						this.image = this.constantImage;
						Thread.sleep(125);
						this.image = this.disappearImage;
						Thread.sleep(125);
						this.image = this.constantImage;
						Thread.sleep(125);
						this.image = this.disappearImage;
						Thread.sleep(125);
						this.image = this.constantImage;
						Thread.sleep(125);
						this.image = this.disappearImage;
						Thread.sleep(125);
						this.image = this.constantImage;
						Thread.sleep(125);
						this.image = this.disappearImage;
						Thread.sleep(125);
						this.image = this.constantImage;
					}
					this.timeCount--;
					
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
			Item.itemList.remove(this);
		});
		disappear.start();
		
	}
	
	public abstract void itemUse(BlackPanther blackTiger);
	
	
	public static void checkItemUse(BlackPanther blackTiger) {
		for(int i = 0;i < itemList.size();i++){
			if(itemList.get(i).getBoundary().intersects(blackTiger.getBoundary())) {
				itemList.get(i).itemUse(blackTiger);
				
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
