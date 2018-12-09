package Item;


import java.util.ArrayList;

import Constant.Images;
import Controller.LoopGame;
import Enemy.GunMan;
import Enemy.HumanSprite;
import Sprite.BlackPanther;
import Sprite.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Item extends Rectangle implements DisappearObject   {
	
	public static ArrayList<Item> itemList = new ArrayList<Item>();
	private Image transparentImage;
	private Image constantImage;
	private Image image;
	private int timeCount;
	
	public Item(double x, double y,Image image, Image transparentImage, boolean isSpecialItem, GunMan enemy) {
		super(x, y, image.getWidth(), image.getHeight());
		this.constantImage = image;
		this.image = image;
		this.transparentImage = transparentImage;
		
		itemList.add(this);
		Thread disappear = new Thread(()-> {
			this.timeCount = TIMES;
			while(this.timeCount!=0) {
				try {
					if(this.timeCount>=3) {
						Thread.sleep(1000);
					} else if (this.timeCount == 2){
						this.image = this.transparentImage;
						Thread.sleep(250);
						this.image = this.constantImage;
						Thread.sleep(250);
						this.image = this.transparentImage;
						Thread.sleep(250);
						this.image = this.constantImage;
						Thread.sleep(250);
						this.image = this.transparentImage;
					} else {
						this.image = this.constantImage;
						Thread.sleep(125);
						this.image = this.transparentImage;
						Thread.sleep(125);
						this.image = this.constantImage;
						Thread.sleep(125);
						this.image = this.transparentImage;
						Thread.sleep(125);
						this.image = this.constantImage;
						Thread.sleep(125);
						this.image = this.transparentImage;
						Thread.sleep(125);
						this.image = this.constantImage;
						Thread.sleep(125);
						this.image = this.transparentImage;
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
		
		if(isSpecialItem) {
//			appear.start();
		} else {
			disappear.start();
		}
		
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

	public static void update(double elapsedTime) {
		// TODO Auto-generated method stub
		for(int i = 0;i < itemList.size();i++){
			if(itemList.get(i) instanceof Bullet) {
				((Bullet)itemList.get(i)).updateBullet(elapsedTime);
			}
		}
	}
}
