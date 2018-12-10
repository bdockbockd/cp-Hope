package Controller;

import java.util.ArrayList;

import Enemy.BadHuman;
import Enemy.HumanSprite;
import Enemy.TrapMan;

public class KeyControlBot {
	public static final ArrayList<String> type2Key = new ArrayList<String>();
	public static ArrayList<String> input2;

	public static void keySpeed(HumanSprite bad1, long currentNanoTime) {
		// TODO Auto-generated method stub
//		bad1.printBoundary();
		if (input2.contains("A")) {
			// x 70
			bad1.addVelocity(-200, 0);
//            bad1.setFace("LEFT");
		}
		if (input2.contains("D")) {
			bad1.addVelocity(200, 0);
//            bad1.setFace("RIGHT");
		}
		if (input2.contains("W")) {
			// y 50
			bad1.addVelocity(0, -200);

		}
		if (input2.contains("S")) {
			bad1.addVelocity(0, 200);
		}

		if (input2.contains("E")) {
			if (bad1 instanceof TrapMan) {
				((TrapMan) bad1).throwTrap();
			}
		}

	}
}
