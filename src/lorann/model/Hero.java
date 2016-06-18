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
	public void move(int x, int y) {
		this.saveLastPosition();
		super.move(x,y);
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
