package lorann.model;

public class Escape extends MotionlessElement {

	public Escape() {
		super(new Sprite("┌┐", "gate_open.png"), Permeability.PENETRABLE, 'e', "Escape");
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() 
	{
		return ActionOnHeroes.ESCAPE;
	}

}
