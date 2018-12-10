package Sprite;

import javafx.geometry.Rectangle2D;

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

	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.getPositionX(), this.getPositionY(), this.getWidth(), this.getHeight());
	}

	public void printBoundary() {
		System.out.println(" Position: [" + this.getPositionX() + "," + this.getPositionY() + "]" + " Width: ["
				+ this.getWidth() + "," + this.getHeight() + "]");
	}

	public boolean intersect(Sprite s) {
		return this.getBoundary().intersects(s.getBoundary());
	}

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
