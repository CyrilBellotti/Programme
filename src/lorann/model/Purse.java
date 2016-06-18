package lorann.model;


public class Purse extends MotionlessElement {


public Purse() 
{
	super(new Sprite("w", "purse.png"), Permeability.PENETRABLE, 'w', "Purse");
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() 
	{
		return ActionOnHeroes.EAT_PURSE;
	}
	
}
