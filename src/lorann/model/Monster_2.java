package lorann.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Monster_2 extends Mobile
{
	public int interval = 500;
	ArrayList <Point> moveable;
	private final Point lastPosition;
	private int randomNum;

	public Monster_2() {
		super(new Sprite("  ", "monster_2.png"), "Monster_1");
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
				visibility();
				if (moveable.size() == 0) {
					randomNum = 0;
				} else {
					randomNum = (int)(Math.random() * ((moveable.size())));
				}
				move((int)moveable.get(randomNum).getX(), (int)moveable.get(randomNum).getY());  
			}
		}, 0, interval);
	}
	
	public void visibility() 
	{
		moveable = new ArrayList();
		
		//regarde en haut
		if (this.isMovePossible(this.getX(), this.getY() - 1))
		{
			moveable.add(new Point(this.getX(), this.getY() - 1));
		}
		
		//regarde en bas
		if (this.isMovePossible(this.getX(), this.getY() + 1))
		{
			moveable.add(new Point(this.getX(), this.getY() + 1));
		}
		
		//regarde a droite
		if (this.isMovePossible(this.getX() + 1, this.getY()))
		{
			moveable.add(new Point(this.getX() + 1, this.getY()));
		}
		
		//regarde a gauche
		if (this.isMovePossible(this.getX() - 1, this.getY()))
		{
			moveable.add(new Point(this.getX() - 1, this.getY()));
		}
		
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
		super.move(x,y);
	}
	
	private void saveLastPosition() {
		if ((this.lastPosition.getX() != this.getPosition().getX()) || (this.lastPosition.getY() != this.getPosition().getY())) {
			this.lastPosition.setLocation(this.getPosition().x, this.getPosition().y);
		}
	}

}
