package lorann.model;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import lorann.contract.ISprite;

public class Fire extends Mobile {

	public int interval = 100;
	private final Point lastPosition;
	private int randomNum;
	private String orientation;
	
	public Fire(String orientation) {
		super(new Sprite("  ", "fireball_1.png"), "fireball");
		this.lastPosition = new Point();
		this.lastPosition.setLocation(this.getPosition().x, this.getPosition().y);
		this.orientation = orientation;
		defineNextPosition();
	}
	
	public void defineNextPosition() 
	{
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() 
		{

			@Override
			public void run() 
			{
				switch(orientation) {
					case "up" : 
						move(getX(), getY() - 1);
						break;
					case "down" : 
						move(getX(), getY() + 1);
						break;
					case "right" : 
						move(getX() + 1, getY());
						break;
					case "left" : 
						move(getX() - 1, getY());
						break;
					case "tr" : 
						move(getX() + 1, getY() - 1);
						break;
					case "tl" : 
						move(getX() -1, getY() - 1);
						break;
					case "dr" : 
						move(getX() + 1, getY() + 1);
						break;
					case "dl" : 
						move(getX() - 1, getY() + 1);
						break;
					default:
						break;
				}
			}
		}, 0, interval);
	}
	
	@Override
	public void move(int x, int y) {
		this.saveLastPosition();
		
		if (this.getNettleWorld().getElements(x, y).getPermeability() == Permeability.BLOCKING) {
			switch(this.orientation) {
				case "up" : 
					orientation = "down";
					break;
				case "down" : 
					orientation = "up";
					break;
				case "right" : 
					orientation = "left";
					break;
				case "left" : 
					orientation = "right";
					break;
				case "tr" : 
					orientation = "dl";
					break;
				case "tl" : 
					orientation = "dr";
					break;
				case "dr" : 
					orientation = "tl";
					break;
				case "dl" : 
					orientation = "tr";
					break;
				default:
					break;
			}
		}
		if (this.getNettleWorld().getHero().getX() == x && this.getNettleWorld().getHero().getY() == y){
			//alerter le controller pour supprimer l'objet
			this.getNettleWorld().delFire(x, y);
		}
		super.move(x,y);
	}
	
	private void saveLastPosition() {
		if ((this.lastPosition.getX() != this.getPosition().getX()) || (this.lastPosition.getY() != this.getPosition().getY())) {
			this.lastPosition.setLocation(this.getPosition().x, this.getPosition().y);
		}
	}
}
