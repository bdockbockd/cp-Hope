package Controller;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Timer extends StackPane {
	public static int time;
	private static int milsec;
	private static int sec;
	private static int min;
	private static Text timerlb;
	private static String timerString;
	private static boolean isStop;
	private static boolean isHide;
	private static boolean isTerminate;

	public Timer() {
		super();
		time = 0;
		milsec = 0;
		sec = 0;
		min = 0;

		isStop = false;
		isHide = false;
		isTerminate = false;

		timerString = String.format("%02d", min) + ":" + String.format("%02d", sec) + ":" + (milsec);
		timerlb = new Text("TIME " + timerString);
		timerlb.setFill(Color.WHITE);
		timerlb.setFont(Font.font("Cornerstone", FontWeight.BOLD, 18));

		this.setAlignment(Pos.CENTER);
		this.setLayoutX(1250 - 130);
		this.setLayoutY(20);
		this.getChildren().addAll(timerlb);

		Thread t = new Thread(new Runnable() {
			public void run() {
				while (!isTerminate) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!isStop()) {
						time++;
					}
					convert();
				}
			}
		});
		t.start();
	}

	public static void terminate() {
		isTerminate = true;
	}

	public static boolean isStop() {
		return isStop;
	}

	public static boolean isHide() {
		return isHide;
	}

	public static void hide() {
		isHide = true;
	}

	public static void show() {
		isHide = false;
	}

	public static void stop() {
		isStop = true;
	}

	public static void play() {
		isStop = false;
	}

	public static void reset() {
		time = 0;
	}

	public static String getString() {
		return timerString;
	}

	private void convert() {
		milsec = time % 10;
		sec = (time / 10) % 60;
		min = time / 600;
		timerString = String.format("%02d", min) + ":" + String.format("%02d", sec) + ":" + (milsec);
		timerlb.setText("TIME " + timerString);
		if (isHide()) {
			timerlb.setText(null);
		}
		// timerlb = new Text("TIME "+timerString);
	}

	public static int getSec() {
		return sec;
	}

	public static int getMin() {
		return min;
	}

	public static int getMilSec() {
		return milsec;
	}

	public static int getTime() {
		return time;
	}
}
