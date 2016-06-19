package lorann.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Monster_3 extends Mobile
{
	public int interval = 500;
	ArrayList <Point> moveable;
	private final Point lastPosition;
	private int randomNum;

	public Monster_3() {
		super(new Sprite("  ", "monster_3.png"), "Monster_3");
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
					// on recupere la visibilite
					visibility();
					if (moveable.size() == 0) {
						randomNum = 0;
					} else {
						randomNum = (int)(Math.random() * ((moveable.size())));
					}
					
					// si le hero est vers le haut  ..
					if((getNettleWorld().getHero().getY() - getY()) < 0)  
					{
						System.out.println("--");
						// si le hero est vers la gauche
						if ((getNettleWorld().getHero().getX() - getX()) < 0)
						{
							if (isMovePossible(getX() - 1, getY() - 1))
							{
								move(getX() - 1, getY() - 1); 
							}
						}
						else
						{
							if (isMovePossible(getX() + 1, getY() - 1))
							{
								move(getX() + 1, getY() - 1); 
							}
						}
					}
					else 
					{
						System.out.println("**");
						// si le hero est vers la gauche
						if ((getNettleWorld().getHero().getX() - getX()) < 0)
						{
							if (isMovePossible(getX() - 1, getY() + 1))
							{
								move(getX() - 1, getY() + 1); 
							}
						}
						else
						{
							if (isMovePossible(getX() + 1, getY() + 1))
							{
								move(getX() + 1, getY() + 1); 
							}
						}
					}
					// si le hero est vers le bas droit
					/*else if((getNettleWorld().getHero().getY() - getY()) < 0 && (getNettleWorld().getHero().getX() + getX()) < 0)  
					{
						// s'il peut aller a bas gauche, il y va
						if (isMovePossible(getX() - 1, getY() + 1))
						{
							move(getX() - 1, getY() + 1); 
						}
						else 
						{
							move((int)moveable.get(randomNum).getX(), (int)moveable.get(randomNum).getY()); 
						}
					} 
					// si le hero est vers le haut droit
					else if((getNettleWorld().getHero().getY() + getY()) < 0 && (getNettleWorld().getHero().getX() + getX()) < 0)  
					{
						// s'il peut aller a haut droit, il y va
						if (isMovePossible(getX() + 1, getY() + 1))
						{
							move(getX() + 1, getY() + 1); 
						}
						else 
						{
							move((int)moveable.get(randomNum).getX(), (int)moveable.get(randomNum).getY()); 
						}
					}
					else 
					{
						// s'il peut aller a haut gauche, il y va
						if (isMovePossible(getX() + 1, getY() - 1))
						{
							move(getX() + 1, getY() - 1); 
						}
						else 
						{
							move((int)moveable.get(randomNum).getX(), (int)moveable.get(randomNum).getY()); 
						}
					}*/
				}
			}
		}, 0, interval);
	}
	
	public void visibility() 
	{
		moveable = new ArrayList();
		
		//regarde en diagonale
		if (this.isMovePossible(this.getX() + 1, this.getY() +1))
		{
			moveable.add(new Point(this.getX() + 1, this.getY() +1));
		}
		if (this.isMovePossible(this.getX() + 1, this.getY() -1))
		{
			moveable.add(new Point(this.getX() + 1, this.getY() -1));
		}
		if (this.isMovePossible(this.getX() - 1, this.getY() + 1))
		{
			moveable.add(new Point(this.getX() - 1, this.getY() + 1));
		}
		if (this.isMovePossible(this.getX() - 1, this.getY() - 1))
		{
			moveable.add(new Point(this.getX() - 1, this.getY() - 1));
		}
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
