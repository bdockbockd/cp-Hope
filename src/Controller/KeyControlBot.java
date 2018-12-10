package Controller;

import java.util.ArrayList;

import Enemy.HumanSprite;
import Enemy.TrapMan;

public class KeyControlBot {
	public static final ArrayList<String> type2Key = new ArrayList<String>();
	public static ArrayList<String> input2;

	public static void keySpeed(HumanSprite bad1, long currentNanoTime) {
		if (input2.contains("A")) {
			bad1.addVelocity(-200, 0);
		}
		if (input2.contains("D")) {
			bad1.addVelocity(200, 0);
		}
		if (input2.contains("W")) {
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
