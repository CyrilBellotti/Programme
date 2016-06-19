package lorann.model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JFrame;

import lorann.contract.ILorannWorld;
import lorann.view.LorannView;

public class LorannWorld extends Observable implements ILorannWorld {
	public MotionlessElement elements[][];
	public final ArrayList<Mobile>	mobiles;
	private int	width;
	private int	height;
	private Hero hero;
	private Fire fire;
	private Object lorann;
	public Boolean existFireball;

	private LorannWorld() {
		this.mobiles = new ArrayList<Mobile>();
		this.existFireball = false;
	}
	
	public Boolean getExistFireball() {
		return this.existFireball;
	}
	
	public void setExistFireball(Boolean exist) {
		this.existFireball = exist;
	}

	public LorannWorld(final String fileName) throws IOException {
		this();
		this.loadFile(fileName);
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public MotionlessElement getElements(final int x, final int y) {
		if ((x < 0) || (y < 0) || (x >= this.getWidth()) || (y >= this.getHeight())) {
			return null;
		}
		return this.elements[x][y];
	}
	
	@Override
	public Point getFirstPositionByCategory(String category) {
		Point point = new Point(-1, -1);
		for (int x = 0; x < this.getWidth(); x++)
		{
			for (int y = 0; y < this.getHeight(); y++)
			{
				if (elements[x][y].getCategory() == category) 
				{
					point = new Point(x,y);
				}
			}
		}
		return point;
	}

	@Override
	public Hero getHero() {
		return this.hero;
	}

	private void addElement(final MotionlessElement element, final int x, final int y) {
		this.elements[x][y] = element;
		if (element != null) {
			element.setNettleWorld(this);
		}
		this.setChanged();
	}
	
	public void delElement(final int x, final int y) {
		this.addElement(MotionlessElements.getFromFileSymbol(' '), x, y) ;
	}

	@Override
	public void addMobile(final Mobile mobile, final int x, final int y) {
		this.mobiles.add(mobile);
		mobile.setLorannWorld(this, x, y);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void addMobile(final Hero hero, final int x, final int y) {
		this.setHero(hero);
		this.addMobile((Mobile) hero, x, y);
	}

	private void loadFile(final String fileName) throws IOException {
		final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line;
		int numLine = 0;
		line = buffer.readLine();
		this.width = Integer.parseInt(line);
		line = buffer.readLine();
		this.height = Integer.parseInt(line);
		this.elements = new MotionlessElement[this.getWidth()][this.getHeight()];
		while ((line = buffer.readLine()) != null) {
			for (int x = 0; x < line.toCharArray().length; x++) {
				this.addElement(MotionlessElements.getFromFileSymbol(line.toCharArray()[x]), x, numLine);
			}
			numLine++;
		}
		buffer.close();
		this.setChanged();
	}

	private void setHero(final Hero hero) {
		this.hero = hero;
		this.setChanged();
	}

	@Override
	public void setMobileHasChanged() {
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}

	@Override
	public Element[][] getElements() {
		return this.elements;
	}

	@Override
	public ArrayList<Mobile> getMobiles() {
		return this.mobiles;
	}

	@Override
	public Gate_Close gate_close() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBoneOpen(int x, int y) {
		this.addElement(MotionlessElements.getFromFileSymbol('T'), x, y) ;
	}

	@Override
	public void delHero(int x, int y) {
	LorannView.displayMessage("Tu t'es fait manger par le monstre");
		this.addElement(MotionlessElements.getFromFileSymbol('M'), x, y);
	}
	
	@Override
	public void delFire(int x, int y) {
		for (Mobile mobile : this.mobiles) {
			if(mobile.getCategory() == "fireball")
			{
				this.mobiles.remove(mobile);
			}
			this.setExistFireball(false);
		}
	}

	@Override
	public Fire getFire() {
		return this.fire;
	}
	
}
