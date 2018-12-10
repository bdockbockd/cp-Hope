package ModelMap;

import java.util.ArrayList;

import com.sun.javafx.geom.Rectangle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;

public abstract class Map extends Sprite.Rectangle {

	private Image imgStage;

	private static ArrayList<Rectangle> allObjects = new ArrayList<>();
	private MediaPlayer bgm;

//	@SafeVarargs
	public Map(Image img) {
		super(0, 0, img.getWidth(), img.getHeight());
//		this.bgm = new MediaPlayer(new Media(bgm.getSource()));
//		this.bgm.setCycleCount(MediaPlayer.INDEFINITE);
//		for(int i = 0;i)
	}

	public void motion(Sprite.Sprite e) {
//		moveEntity(e);
//		if (e.getVelocityY() >= 0 && isOnFloor(e)) {
//			e.setVelocityY(0);
//			if (e instanceof Player) {
//				((Player) e).setJumping(false);
//			}
//		}
	}

	public void moveAllRectangle() {
//		for (Entity i : SharedEntity.getInstance().getEntitiesOfMap(this)) {
//			motion(i);
//		}
	}

//	private void moveMap() {
//		// Move map by object inside
//		Player player = GameManager.getInstance().getPlayer();
//		if (player.getX() > Constants.WINDOW_WIDTH + x - player.getWidth() - 200)
//			x += movementSpeed;
//		else if (player.getX() < x + 200)
//			x -= movementSpeed;
//		if (player.getY() > Constants.WINDOW_HEIGHT + y - player.getHeight() - 150)
//			y += movementSpeed;
//		else if (player.getY() < y + 150)
//			y -= movementSpeed;
//
//		// Map boundary
//		if (x < 0)
//			x = 0;
//		else if (x > width - Constants.WINDOW_WIDTH)
//			x = width - Constants.WINDOW_WIDTH;
//		if (y < 0)
//			y = 0;
//		else if (y > height - Constants.WINDOW_HEIGHT)
//			y = height - Constants.WINDOW_HEIGHT;
//	}

	private void moveSprite(Sprite.Sprite e) {
//		e.move();
//		if (e.getX() < 0)
//			e.setX(0);
//		else if (e.getX() + e.getWidth() > width)
//			e.setX(width - e.getWidth());
//		if (e.getY() < 0)
//			e.setY(9);
//		else if (e.getY() + e.getHeight() > height)
//			e.setY(height - e.getHeight());
	}

//	public List<DamageableEntity> collideDamageableEntity(Rectangle r, int limit) {
//		List<DamageableEntity> list = new ArrayList<>();
//		for (Entity i : SharedEntity.getInstance().getEntitiesOfMap(this)) {
//			if (i instanceof DamageableEntity && r.collideWith(i)) {
//				list.add((DamageableEntity) i);
//			}
//			if (list.size() >= limit) {
//				break;
//			}
//		}
//		return list;
//	}

	// return collided things
	public Sprite.Sprite collideItemEntity(Rectangle r) {
//		for (Sprite.Rectangle i: SharedEntity.getInstance().getEntitiesOfMap(this)) {
//			if (i instanceof ItemEntity && r.collideWith(i)) {
//				return (ItemEntity) i;
//			}
//		}
		return null;
	}

	public void render(GraphicsContext gc) {
//		gc.drawImage(img, -x, -y);
//		GameManager.getInstance().getPlayer().render(gc);
//		for (Entity i : SharedEntity.getInstance().getEntitiesOfMap(this))
//			i.render(gc);
//		for (Portal i : portals)
//			i.render(gc);
//		for (IParticle i : particles)
//			i.render(gc);
	}

	public void update() {
//		Iterator<IParticle> it = particles.iterator();
//		while (it.hasNext()) {
//			if (it.next().isExpired()) {
//				it.remove();
//			}
//		}
//		for (Entity i: SharedEntity.getInstance().getEntitiesOfMap(this)) {
//			i.update();
//			if (i instanceof ItemEntity && ((ItemEntity) i).isExpired()) {
//				SharedEntity.getInstance().remove(i);
//			}
//		}
	}

	public void spawnRandom() {
//		int randMonster = Random.randInt(monsterTypes.size());
//		try {
//			Monster m = monsterTypes.get(randMonster).getConstructor(Map.class, double.class, double.class).newInstance(this, 0, 0);
//			List<Pair<StructureItem, Double>> list = new ArrayList<>();
//			for (StructureItem item: structure) {
//				if (item.isSpawnable() && item.getWidth() >= m.getWidth())
//					list.add(new Pair<StructureItem, Double>(item, item.getWidth()-m.getWidth()));
//			}
//			Pair<StructureItem, Double> randStructure = Random.weightedRandomInList(list);
//			double randX = randStructure.getKey().getX()+randStructure.getValue();
//			double randY = randStructure.getKey().getY()-m.getHeight();
//			m.move(randX, randY);
//			SharedEntity.getInstance().add(m);	
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//				| NoSuchMethodException | SecurityException e) {
//			System.out.println("map.Map.spawnRandom Error: No constructor");
//		} catch (ListEmptyException e) {
//			System.out.println("map.Map.spawnRandom Error: No structure to spawn");
//		} catch (NegativeWeightedRandomException e) {
//			System.out.println("map.Map.spawnRandom Error: Negative area of spawn");
//		}
	}

}
