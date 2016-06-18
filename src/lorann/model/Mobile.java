package lorann.model;

import java.awt.Point;

import aedt.showboard.IPawn;
import lorann.contract.ILorannWorld;
import lorann.contract.ISprite;

public abstract class Mobile extends Element implements IPawn {
	private final Point position;
	private final Point pos;

	public Mobile(final ISprite sprite, final String category) {
		super(sprite, Permeability.BLOCKING, category);
		this.position = new Point();
		this.pos = new Point();
	}
	
	

	@Override
	public int getX() {
		return this.position.x;
	}

	protected void setX(final int x) {
		if ((x >= 0) && (x < this.getNettleWorld().getWidth())) {
			this.position.x = x;
			this.getNettleWorld().setMobileHasChanged();
		}
	}

	@Override
	public int getY() {
		return this.position.y;
	}

	protected void setY(final int y) {
		if ((y >= 0) && (y < this.getNettleWorld().getHeight())) {
			this.position.y = y;
			this.getNettleWorld().setMobileHasChanged();
		}
	}

	@Override
	public Point getPosition() {
		return this.position;
	}
	
	
	public Point getPos()
	{
		return this.pos;
	}


	public void setLorannWorld(final ILorannWorld lorannWorld, final int x, final int y) {
		super.setNettleWorld(lorannWorld);
		this.setX(x);
		this.setY(y);
	}

	public boolean isMovePossible(final int x, final int y) {
		return (this.getNettleWorld().getElements(x, y).getPermeability() != Permeability.BLOCKING);
	}

	// Mouvenement
	public void move(int x, int y) {
		if (this.isMovePossible(x, y)) {
			if (this.getX() != x) {
				this.setX(x);
			}
			if (this.getY() != y) {
				this.setY(y);
			}
		}
	}
	
/*	public void fire() {
		if()
		{
			
		}
	}*/
}
