package lorann.model;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import lorann.contract.ISprite;

public class Fire extends Mobile {

	public int interval = 200;
	private final Point lastPosition;
	private int randomNum;
	private String orientation;
	private String[] images = {"fireball_1.png", "fireball_2.png", "fireball_3.png", "fireball_4.png", "fireball_5.png"};
	private int nbImage = 0;
	
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
				// changement de l'image de la boule
				updateImage(new Sprite("☺!", images[nbImage]));
				if (nbImage >= images.length - 1) 
				{
					nbImage = 0; 
				} 
				else 
				{
					nbImage++;
				}
				
				// gestion du deplacement
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
		
		// si le hero est sur la boule
		if (this.getNettleWorld().getHero().getX() == x && this.getNettleWorld().getHero().getY() == y){
			// on supprime la boule
			this.getNettleWorld().delFire(x, y);
		}
		
		// pour chacun des elements mobiles
		for (Mobile mobile : this.getNettleWorld().getMobiles()) {
			// si l'element mobile est sur la position de la boule de feu
			if (mobile.getX()  == x && mobile.getY()  == y){
				// si l'element mobile est un monstre
				// alors il est mort et la boule reviens au hero
				switch (mobile.getCategory()) 
				{
					case "Monster_1" :
						mobile.isDead();
						this.getNettleWorld().getMobiles().remove(mobile);
						this.getNettleWorld().setExistFireball(false);
						break;
					case "Monster_2" : 
						mobile.isDead();
						this.getNettleWorld().getMobiles().remove(mobile);
						this.getNettleWorld().setExistFireball(false);
						break;
					case "Monster_3" : 
						mobile.isDead();
						this.getNettleWorld().getMobiles().remove(mobile);
						this.getNettleWorld().setExistFireball(false);
						break;
					case "Monster_4" : 
						mobile.isDead();
						this.getNettleWorld().getMobiles().remove(mobile);
						this.getNettleWorld().setExistFireball(false);
						break;
					default : 
						break;
				}
				this.getNettleWorld().delFire(x, y);
			}
		}
		
		// deplacement
		super.move(x,y);
	}
	
	private void saveLastPosition() {
		if ((this.lastPosition.getX() != this.getPosition().getX()) || (this.lastPosition.getY() != this.getPosition().getY())) {
			this.lastPosition.setLocation(this.getPosition().x, this.getPosition().y);
		}
	}
}
