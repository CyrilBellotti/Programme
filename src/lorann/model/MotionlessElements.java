package lorann.model;

public abstract class MotionlessElements {
	public static final MotionlessElement	VERTIC_BONE	= new Vertical_Bone();
	public static final MotionlessElement	BONE	= new Bone();
	public static final MotionlessElement	GATE_OPEN	= new Gate_Open();
	public static final MotionlessElement	HORIZ_BONE	= new Horizontal_Bone();
	public static final MotionlessElement	FLOOR	= new Floor();
	public static final MotionlessElement	ESCAPE	= new Escape();
	public static final MotionlessElement	CrystalBall = new CrystalBall();
	public static final MotionlessElement	PURSE = new Purse();
	public static final MotionlessElement	GATE_CLOSE = new Gate_Close();
	public static final MotionlessElement	MONSTER1 = new Monster_1();


	private static MotionlessElement			motionlessElements[]	= { VERTIC_BONE, BONE, GATE_OPEN, FLOOR, HORIZ_BONE, ESCAPE, CrystalBall, PURSE, GATE_CLOSE, MONSTER1 };

	public static MotionlessElement getFromFileSymbol(final char fileSymbol) 
	{
		for (final MotionlessElement motionlessElement : motionlessElements) 
		{
			if (motionlessElement.getFileSymbol() == fileSymbol) {
				return motionlessElement;
			}
		}
		return FLOOR;
	}
}