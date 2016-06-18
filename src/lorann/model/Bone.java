package lorann.model;

public class Bone extends MotionlessElement 
{

	public Bone() 
	{
		super(new Sprite("■▀", "bone.png"), Permeability.BLOCKING, 'S', "Mur");
	}

}
