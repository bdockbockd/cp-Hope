package Logic;

public class BlackPanther {
	//field
	private final double maxHealth = 1000;
	private double health;
	private double damage;
	private double armor;
	private int status; // 1 = normalBP, 2 = superBP, 3 = enragedBP
	
	//constructor
	public BlackPanther() {
		this.health = maxHealth;
		this.damage = 100;
		this.armor = 10;
		this.status = 1;
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
	public double getArmor() {
		return this.armor;
	}
	public void setArmor(double armor) {
		this.armor = armor;
	}
	public int getStatus()
	{
		return this.status;
	}
	public void setStatus(int status)
	{
		this.status = status;
		if(status == 1)
		{
			setDamage(100);
			setArmor(10);
		}
		if(status == 2)
		{
			setDamage(300);
			setArmor(20);
		}
		if(status == 3)
		{
			setDamage(150);
			setArmor(15);
		}
	}
	public void heal(double heal)
	{
		setHealth(getHealth()+heal);
		if(getHealth()>getMaxHealth())
		{
			setHealth(getMaxHealth());
		}
	}
	public boolean isDead()
	{
		return getHealth()<=0;
	}
}
