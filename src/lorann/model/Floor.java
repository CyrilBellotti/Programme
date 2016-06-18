package lorann.model;

class Floor extends MotionlessElement {

	public Floor() {
		super(new Sprite("  ", "vide.png"), Permeability.PENETRABLE, ' ', "Floor");
	}

}
