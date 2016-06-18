package lorann.model;

class Gate_Open extends MotionlessElement {

	public Gate_Open() {
		super(new Sprite("╔╗", "gate_open.png"), Permeability.PENETRABLE, 'T', "Gate_open");
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		return ActionOnHeroes.ENTER_GATE;
	}
	
	
}
