package lorann.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Monster_2 extends Mobile
{
	public int interval = 500;
	ArrayList <Point> moveable;
	private final Point lastPosition;

	public Monster_2() {
		super(new Sprite("  ", "monster_2.png"), "Monster_2");
		this.lastPosition = new Point();
		this.lastPosition.setLocation(this.getPosition().x, this.getPosition().y);
		defineNextPosition();
	}
	
	/*@Override
	public ActionOnHeroes getActionOnHeroes() 
	{
		return ActionOnHeroes.EAT_HERO;
	}*/
	
	public void defineNextPosition() 
	{
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() 
		{
			@Override
			public void run() 
			{
				if (dead) 
				{
					timer.cancel();
					timer.purge();
				}
				else 
				{
					// si le hero est vers la bas ..
					if((getNettleWorld().getHero().getY() - getY()) < 0)  
					{
						// s'il peut aller a bas, il y va
						if (isMovePossible(getX(), getY() - 1))
						{
							move(getX(), getY() - 1); 
						}
						// sinon il va a haut
						else 
						{
							move(getX(), getY() + 1); 
						}
					}
					else 
					{
						// s'il peut aller a droite, il y va
						if (isMovePossible(getX(), getY() + 1))
						{
							move(getX(), getY() + 1); 
						}
						// sinon il va a gauche
						else 
						{
							move(getX(), getY()); 
						}
					}
				}
			}
		}, 0, interval);
	}
	
	@Override
	public void move(int x, int y) {
		this.saveLastPosition();
		
		// si le hero est sur le monstre 
		if (this.getNettleWorld().getHero().getX() == x && this.getNettleWorld().getHero().getY() == y){
			// on tue le hero
			this.getNettleWorld().delHero(x, y);
		}
		
		super.move(x,y);
	}
	
	private void saveLastPosition() {
		if ((this.lastPosition.getX() != this.getPosition().getX()) || (this.lastPosition.getY() != this.getPosition().getY())) {
			this.lastPosition.setLocation(this.getPosition().x, this.getPosition().y);
		}
	}

}
