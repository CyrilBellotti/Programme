package lorann.model;

import java.awt.Point;

public class Gate_Close extends MotionlessElement {

	
	private Point Pos;
	
	public Gate_Close() 
	{
		super(new Sprite(" ", "gate_closed.png"), Permeability.BLOCKING, 'Z', "GateClose");
//		this.Pos.setLocation(this.getPosition().x, this.getPosition().y);

	}
	


}
