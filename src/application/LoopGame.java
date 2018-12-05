package application;

import Controller.Main;
import Enemy.BadHuman;
import Sprite.BlackTiger;
import Sprite.Meat;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class LoopGame {
	public static BlackTiger tiger1 = Main.tiger1;
	public static GraphicsContext gc = Main.gc;
	private static long lastNanoTime = System.nanoTime();
	
	public LoopGame() {
		
		new AnimationTimer()  {
        	
			@Override
			public void handle(long currentNanoTime) {
				// TODO Auto-generated method stub
				// calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;

                lastNanoTime = currentNanoTime;
                
                tiger1.setMove(false);
                tiger1.setVelocity(0,0);
//                bad1.setVelocity(0, 0);
                Main.keyActionToSpeed(tiger1, currentNanoTime, gc);
//            	Main.keySpeed(bad1, currentNanoTime);

//            	bad1.update(elapsedTime);
                tiger1.update(elapsedTime);
                
//                
                if(Main.ccheck) {
                Thread x = new Thread (()-> {
                	try {
                		Main.ccheck = false;
                		tiger1.nextPosition(tiger1.getFace());
                		Thread.sleep(50);
                		Main.ccheck = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                });
                x.start();
                }
               
                
				gc.drawImage((Images.stageMap)[0], 0, 0);
				if(Main.canUpdateBot == true && BadHuman.getbadList().size() != 0) {
					Thread delay = new Thread(()->{
						try {
							
								for(int i =0;i<BadHuman.getbadList().size();i++) {
									((BadHuman.getbadList()).get(i)).update(elapsedTime, tiger1);
								}
							
							
//							bad1.printBoundary();
//							tiger1.printBoundary();
							Main.canUpdateBot = false;
							Thread.sleep(1000);
							Main.canUpdateBot = true;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
					delay.start();
				}
//				gc.drawImage(Images.enemyTomb, 300, 300);

				BadHuman.checkAttackHuman(tiger1);
				for(int i =0;i<BadHuman.getbadList().size();i++) {
					
					((BadHuman.getbadList()).get(i)).update(elapsedTime);
				}
				
				
				for(int i =0;i<BadHuman.getbadList().size();i++) {
					((BadHuman.getbadList()).get(i)).render(gc);
				}
//				for(int i =0;i<Main.enemySprite.size();i++) {
//					if(tiger1.intersect(Main.enemySprite.get(i))) {
//						System.out.println("got enemy"+ i);
//					} 
//				}
				BadHuman.removeEnemy();
//				bad1.render(gc);
				tiger1.render( gc );
				Meat meat = new Meat(400,400);
				meat.render(gc);
			}	
        }.start();
	}
	
	public static void startGame() {
		
	}
}
