package lorann.model;

import java.awt.Point;

public class Hero extends Mobile {
	
	private final Point lastPosition;

	public Hero() {
		super(new Sprite("☺!", "lorann_gif.gif"), "Hero");
		this.lastPosition = new Point();
		this.lastPosition.setLocation(this.getPosition().x, this.getPosition().y);
	}

	private void saveLastPosition() {
		if ((this.lastPosition.getX() != this.getPosition().getX()) || (this.lastPosition.getY() != this.getPosition().getY())) {
			this.lastPosition.setLocation(this.getPosition().x, this.getPosition().y);
		}
	}

	@Override
	public void moveUp() 
	{
		this.saveLastPosition();
		super.moveUp();
		

	}

	@Override
	public void moveLeft() {
		this.saveLastPosition();
		super.moveLeft();
	}
	
	@Override
	public void moveTL() {
		this.saveLastPosition();
		super.moveTL();
	}
	
	public void moveTR() {
		this.saveLastPosition();
		super.moveTR();
	}
	
	public void moveBL() {
		this.saveLastPosition();
		super.moveBL();
	}
	
	public void moveBR() {
		this.saveLastPosition();
		super.moveBR();
	}

	@Override
	public void moveDown() {
		this.saveLastPosition();
		super.moveDown();
	}

	@Override
	public void moveRight() {
		this.saveLastPosition();
		super.moveRight();
	}
	
	public void fire()
	{
		this.saveLastPosition();
//		super.fire();
	}

	public void moveBack() {
		this.setX(this.lastPosition.x);
		this.setY(this.lastPosition.y);
	}

}
