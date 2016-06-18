package lorann.model;

public class Monster_1 extends MotionlessElement
{

	public Monster_1() {
		super(new Sprite("  ", "monster_1.png"), Permeability.MANGER, 'M', "Monster_1");
	}
	
	@Override
	public ActionOnHeroes getActionOnHeroes() 
	{
		return ActionOnHeroes.EAT_HERO;
	}
}
