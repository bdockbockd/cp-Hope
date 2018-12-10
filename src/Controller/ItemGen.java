package Controller;

import Item.HealthPotion;
import Item.Meat;
import Item.SuperPotion;

public class ItemGen {

	public static void genItem(double positionX, double positionY) {
		int random = (int) (Math.random() * 3);
		if (Math.random() < 0.3) {
			if (random == 0) {
				new Meat(positionX, positionY);
			} else if (random == 1) {
				new HealthPotion(positionX, positionY);
			} else if (random == 2) {
				new SuperPotion(positionX, positionY);
			}
		}
	}
}
