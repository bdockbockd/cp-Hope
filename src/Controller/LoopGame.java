package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Constant.Audio;
import Constant.Images;
import Enemy.HumanSprite;
import Exception.GameOverException;
import Item.Item;
import Sprite.BlackPanther;
import UI.DeadScene;
import UI.GamePause;
import application.Main;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class LoopGame {
	public static BlackPanther blackPanther;
	private static long lastNanoTime;
	public static String playerName;
	public static GamePause gamePause;
	public static DeadScene deadScene;
	public static boolean CANUPDATEBOT;
	public static boolean CCHECK;
	public static double elapsedTime;
	public static GraphicsContext gc;
	public static boolean botHit;

	public static Thread DELAYBOT;

	public LoopGame(GraphicsContext gc, Scene theScene, String playerName) {

		LoopGame.startGame(gc, theScene, playerName);

		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				if (!blackPanther.isDead() && !GamePause.isPause) {

					// Input
					// drawMap
					// calculate time since last update.

					elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
					if (elapsedTime > 0.02) {
						elapsedTime = 0.016;
					}

					lastNanoTime = currentNanoTime;
					LoopGame.updateBlackPanther(currentNanoTime, this);
					LoopGame.updateBotAndItem();
					EnemyGen.modifiyBotLevel();
					Controller.ScoreBoard.update();
					Controller.StatusBar.resetProgress(blackPanther);

					gc.drawImage((Images.STAGEMAP)[blackPanther.getStatus()], 0, 0);

					Collections.sort(EnemyGen.getbadList(), new Comparator<HumanSprite>() {
						@Override
						public int compare(final HumanSprite o1, final HumanSprite o2) {
							if (o1.getPositionY() < o2.getPositionY()) {
								return -1;
							} else {
								return 1;
							}
						}
					});

					blackPanther.render(gc);
					EnemyGen.renderBotList(gc);
					Item.render(gc);

					// Game Over
					try {
						gameOverCheck();
					} catch (GameOverException e) {
						// stop ever thing must be implement here!
						deadScene = new DeadScene(playerName, ScoreBoard.getScore(), Timer.getString());
						deadScene.show(Main.stage);
						Audio.MENU_BGM.play();
						Item.itemList.clear();
						EnemyGen.getbadList().clear();
						// e.printStackTrace();
					}
				}
			}
		}.start();

	}

	protected static void gameOverCheck() throws GameOverException {
		if (blackPanther.isDead()) {
			Audio.GAME_BGM.stop();
			Audio.DEAD.play(1);
			Audio.ENEMY_FIRE.stop();
			EnemyGen.getbadList().clear();
			Timer.stop();
			Timer.hide();
			Timer.terminate();
			ScoreBoard.hide();
			ScoreBoard.addScore(Timer.getSec() * 1000);
			throw new GameOverException();
		}
	}

	public static void startGame(GraphicsContext gc, Scene theScene, String playerName) {
		LoopGame.playerName = playerName;
		LoopGame.gc = gc;
		gamePause = new GamePause();
		blackPanther = new BlackPanther();
		blackPanther.setPosition(1250 / 2 - 351 / 2, 800 / 2 + 100);
		lastNanoTime = System.nanoTime();
		KeyControlBlackPanther.input = new ArrayList<String>();
		KeyControlBlackPanther.directionInput = new ArrayList<String>();
		KeyControlBot.input2 = new ArrayList<String>();
		// scene detect
		LoopGame.setKey(theScene);
		gamePause = new GamePause();
		CANUPDATEBOT = true;
		CCHECK = true;
	}

	public static void updateBlackPanther(long currentNanoTime, AnimationTimer animation) {
		// set Velocity blackPanther
		blackPanther.checkStatus();

		if (!(blackPanther.isSkillOn())) {
			blackPanther.setVelocity(0, 0);
			KeyControlBlackPanther.keyActionToSpeed(blackPanther, currentNanoTime, animation);

			// checkPosition blackPanther
			if (CCHECK && blackPanther.getActionState() == 0) {
				Thread x = new Thread(() -> {
					try {
						CCHECK = false;
						blackPanther.nextPosition(blackPanther.getFace());
						Thread.sleep(50);
						CCHECK = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
				x.start();
			}
		}

		// update position from time and velocity
		blackPanther.update(elapsedTime);
	}

	public static void updateBotAndItem() {
		Item.checkItemUse(blackPanther);

		// update Item
		Item.update(elapsedTime);
		// update bot Quantity
		EnemyGen.checkQuantityBot();
		// updateBot random every 3 (VELOCITYXY)
		if (CANUPDATEBOT == true && EnemyGen.getbadList().size() != 0 && !gamePause.isShowing()) {
			DELAYBOT = new Thread(() -> {
				for (int i = 0; i < EnemyGen.getbadList().size(); i++) {
					if (!EnemyGen.getbadList().get(i).isDead() && !(EnemyGen.getbadList().get(i).isKnockBack())) {
						((EnemyGen.getbadList()).get(i)).update(elapsedTime, blackPanther);
					}
				}
				try {
					CANUPDATEBOT = false;
					Thread.sleep(3000);
					CANUPDATEBOT = true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			DELAYBOT.start();
		}

		// check bot attack
		EnemyGen.checkAttackHuman(blackPanther);

		// check bot get damaged and remove death
		EnemyGen.removeEnemy();

		// update bot all times
		for (int i = 0; i < EnemyGen.getbadList().size(); i++) {
			if (EnemyGen.getbadList().get(i).isKnockBack()) {
			}
			((EnemyGen.getbadList()).get(i)).update(elapsedTime);
		}
	}

	public static void setKey(Scene theScene) {

		theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (!KeyControlBlackPanther.input.contains(code)) {
					KeyControlBlackPanther.input.add(code);
				}
				if (!KeyControlBot.input2.contains(code)) {
					KeyControlBot.input2.add(code);
				}
				if (!KeyControlBlackPanther.directionInput.contains(code)
						&& Controller.KeyControlBlackPanther.DIRECTION_KEY.contains(code)) {
					KeyControlBlackPanther.directionInput.add(code);
				}
			}
		});

		theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (KeyControlBlackPanther.input.contains(code)) {
					if (code.equals(KeyControlBlackPanther.ATTACK_KEY)) {
						blackPanther.switchToWalk();

					} else if (code.equals(KeyControlBlackPanther.SPIN_KEY)) {
//	                        		BlackPanther.spinAttackDetected = false;
					}
					KeyControlBlackPanther.input.remove(code);
				}
				if (KeyControlBot.input2.contains(code)) {
					KeyControlBot.input2.remove(code);
				}
			}
		});
	}
}
