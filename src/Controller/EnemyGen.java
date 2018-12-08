package Controller;

import java.util.concurrent.CopyOnWriteArrayList;

import Constant.Audio;
import Constant.Images;
import Enemy.BadHuman;
import Enemy.HumanSprite;
import Sprite.BlackPanther;

public class EnemyGen {
    private static CopyOnWriteArrayList<BadHuman> badList = new CopyOnWriteArrayList<BadHuman>();
    public static BlackPanther instanceTiger;
    // addBOt

	   public static BadHuman generateRandom() {
	    	BadHuman badHuman = new BadHuman();
	    	badHuman.setImage((Images.humanMotionL)[0]);
	    	double px,py;
	    	if(Math.random() < 0.5) {
		        px = Math.random()*400+1180;
	    	} else {
	    		px = 0-Math.random()*400;
	    	}
	        py = Math.random()*600+215;
	        badHuman.setPosition(px, py);
	    
	        return badHuman;
	    }
	    
	    public static void addBot() {
	    	EnemyGen.getbadList().add(EnemyGen.generateRandom());
	    }
	    
	    public static void generatelistBot(int num) {
	    	CopyOnWriteArrayList<BadHuman> bad = new CopyOnWriteArrayList<BadHuman>();
	    	while(num !=0) {
	    		bad.add(generateRandom());
	    		num--;
	    	}
	    	EnemyGen.setbadList(bad);
	    }
	    public static void checkAttackHuman(BlackPanther tiger) {
	    	for(int i=0; i< EnemyGen.badList.size();i++) {
	    		HumanSprite enemy = EnemyGen.getbadList().get(i);
	    		if(enemy.intersect(tiger)) {
	    			if(!(enemy.isWaitToHit())) {
	    				enemy.attack(tiger);
	    				Thread a = new Thread (()->{
	    					try {
	    						enemy.setWaitToHit(true);
	    						Thread.sleep(2000);
	    						enemy.setWaitToHit(false);
	    					} catch (InterruptedException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	    				});
	    				a.start();
	    			}
	    		}
	    		
	    	}
	    	
	    }
	    public static void removeEnemy() {
			if(EnemyGen.getbadList().size()==0) return ;
			for(int i =0;i<EnemyGen.getbadList().size();i++) {
				HumanSprite enemy = EnemyGen.getbadList().get(i);
				if(enemy.isDead() && !(enemy.isTomb())) {
					Audio.ENEMY_DEAD.play();
					Controller.ScoreBoard.addScore(1000);
					enemy.setTomb(true);
					Thread t = new Thread(()-> {
								try {
									enemy.setImage(Images.enemyTomb);  
									ItemGen.genItem(enemy.getPositionX(), enemy.getPositionY());
									Thread.sleep(1000);
									EnemyGen.getbadList().remove(enemy);
								}
								catch(InterruptedException e){
									e.printStackTrace();
								}
					});
					t.start();
				}
			}
		}
	
	    public static CopyOnWriteArrayList<BadHuman> getbadList() {
			return EnemyGen.badList;
		}

		public static void setbadList(CopyOnWriteArrayList<BadHuman> list) {
			EnemyGen.badList = list;
		}
}
