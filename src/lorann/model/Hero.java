package lorann.model;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

public class Hero extends Mobile {
	
	private int interval = 300;
	private final Point lastPosition;
	private String[] images = {"lorann_b.png", "lorann_bl.png", "lorann_l.png", "lorann_ul.png", "lorann_u.png", "lorann_ur.png", "lorann_r.jpg", "lorann_br.png"};
	private int nbImage = 0;

	public Hero() {
		super(new Sprite("☺!", "lorann_gif.gif"), "Hero");
		this.lastPosition = new Point();
		this.lastPosition.setLocation(this.getPosition().x, this.getPosition().y);
		this.getY();
		changeImage();
	}
	
	private void changeImage() 
	{
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() 
		{
			@Override
			public void run() 
			{
				updateImage(new Sprite("☺!", images[nbImage]));
				if (nbImage >= images.length - 1) 
				{
					nbImage = 0; 
				} 
				else 
				{
					nbImage++;
				}
			}
		}, 0, interval);
	}

	private void saveLastPosition() {
		if ((this.lastPosition.getX() != this.getPosition().getX()) || (this.lastPosition.getY() != this.getPosition().getY())) {
			this.lastPosition.setLocation(this.getPosition().x, this.getPosition().y);
		}
	}
	
	@Override
	public void move(int x, int y) {
		this.saveLastPosition();

		// pour chacun des elements mobiles
		for (Mobile mobile : this.getNettleWorld().getMobiles()) {
			// si l'element mobile est sur la position de la boule de feu
			if (mobile.getX()  == x && mobile.getY()  == y){
				// si l'element mobile est un monstre
				// alors le hero est mort 
				switch (mobile.getCategory()) 
				{
					case "Monster_1" :
					case "Monster_2" :
					case "Monster_3" : 
					case "Monster_4" : 
						this.getNettleWorld().delHero(x, y);
						break;
					default : 
						break;
				}
				this.getNettleWorld().delFire(x, y);
			}
		}
		super.move(x,y);
	}

	public void fire()
	{
		this.saveLastPosition();
	}

	public void moveBack() {
		this.setX(this.lastPosition.x);
		this.setY(this.lastPosition.y);
	}

}
