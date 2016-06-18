package lorann.model;

public class CrystalBall extends MotionlessElement {

	public CrystalBall()
	{
		super(new Sprite(" ", "crystal_ball.png"), Permeability.PENETRABLE, 'C', "CrystalBall");
	}
	
	@Override
	public ActionOnHeroes getActionOnHeroes() 
	{
		return ActionOnHeroes.EAT; 
	}
	
}


