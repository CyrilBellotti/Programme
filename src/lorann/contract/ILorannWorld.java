package lorann.contract;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;

import lorann.model.Element;
import lorann.model.Gate_Close;
import lorann.model.Hero;
import lorann.model.Mobile;
import lorann.model.MotionlessElement;

public interface ILorannWorld {

	public int getWidth();

	public int getHeight();

	public MotionlessElement getElements(int x, int y);

	public Hero getHero();

	public void addMobile(Mobile mobile, int x, int y);

	public void addMobile(Hero hero, int x, int y);

	public void setMobileHasChanged();

	public Element[][] getElements();

	public ArrayList<Mobile> getMobiles();

	public void addObserver(Observer o);
	
	public void delElement(final int x, final int y);
	
	public void delHero(final int x, final int y);
	
	public void addBoneOpen(final int x, final int y);
	
	public Gate_Close gate_close();

	public Point getFirstPositionByCategory(String category);
}