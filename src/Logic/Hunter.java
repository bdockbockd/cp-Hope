package Logic;

public class Hunter extends Human {
	private static int hunterDead = 0;
	public Hunter(){
		super();
	}
	
	public boolean isDead(){
		if(getHealth() <= 0)
		{
			hunterDead++;
			return true;
		}
		return false;
	} 
}
