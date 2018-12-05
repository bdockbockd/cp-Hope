package Controller;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
 
public class Timer extends StackPane{
	public static int time;
	private static int milsec;
	private static int sec;
	private static int min;
	private static Text timerlb;
	public Timer(){
		super();
		time = 0;
		milsec = 0;
		sec = 0;
		min = 0;
		
		timerlb = new Text("TIME "+String.format("%02d",min)+":"+String.format("%02d",sec) + ":"+Integer.toString(milsec));
		timerlb.setFill(Color.WHITE);
		timerlb.setFont(Font.font("Cornerstone", FontWeight.BOLD, 18));
		
		this.setAlignment(Pos.CENTER);
		this.setLayoutX(1250/2-45);
		this.setLayoutY(20);
		this.getChildren().addAll(timerlb);
		
		Thread t = new Thread(new Runnable() {
			public void run(){ 
				while(true) {
					try {
						Thread.sleep(100);
					}
					catch(InterruptedException e){
						e.printStackTrace();
					}
					time++;
					convert();
				}
			}
		});
		t.start();
	}
	
	private void convert(){
		milsec = time%10;
		sec = (time/10)%60;
		min = time/600;
		timerlb.setText("TIME "+String.format("%02d",min)+":"+String.format("%02d",sec) + ":"+Integer.toString(milsec));
	}
	public static int getSec()
	{
		return sec;
	}
	public static int getMin()
	{
		return min;
	}
}
