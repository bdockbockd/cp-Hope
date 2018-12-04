package Logic;

public class Human {
	//field
	private final double maxHealth = 500;
	private double health;
	private double damage;
	//constructor
	public Human() {
		this.health = maxHealth;
		this.damage = 10;
	}
	
	//getter & setter
	public double getMaxHealth() {
		return this.maxHealth;
	}
	public double getHealth() {
		return this.health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public double getDamage() {
		return this.damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}
	public boolean isDead()
	{
		return getHealth()<=0;
	}
}
