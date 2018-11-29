package Sprite;

public class Rectangle {
	
	protected double positionX;
	protected double positionY;
	protected double width;
	protected double height;

	public Rectangle() {
		this(0, 0, 0, 0);
	}
	
	public Rectangle(double x, double y, double width, double height) {
		this.positionX = x;
		this.positionY = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean collideWith(Rectangle r) {
		double x = this.getPositionX();
		double y = this.getPositionY();
		if (x < r.getPositionX() + r.width && x + width > r.getPositionX() && y < r.getPositionY() + r.height && height + y > r.getPositionY()) return true;
			return false;
	}
	
//	public boolean collideWith(double x, double y, double width, double height) {
//		if (this.x < x + width && this.x + this.width > x && this.y < y + height && this.height + this.y > y) return true;
//		return false;
//	}
	
//	public String toString() {
//		return String.format("%s [x=%f, y=%f, width=%f, height=%f]", getClass().getSimpleName(), x, y, width, height);
//	}
	
	public double getPositionX() {
		return this.positionX;
	}

	public void setPositionX(double x) {
		this.positionX = x;
	}

	public double getPositionY() {
		return this.positionY;
	}

	public void setPositionY(double y) {
		this.positionY = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
