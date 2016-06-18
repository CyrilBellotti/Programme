package lorann.model;

import java.awt.Image;

import aedt.showboard.ISquare;
import lorann.contract.ILorannWorld;
import lorann.contract.ISprite;

public abstract class Element implements ISquare 
{
	private ISprite		sprite;
	private Permeability	permeability;
	private ILorannWorld	lorannWorld;
	private String category;

	public Element(final ISprite sprite, final Permeability permeability, final String category) 
	{
		this.setSprite(sprite);
		this.setPermeability(permeability);
		this.setCategory(category);
	}
	
	public String getCategory() 
	{
		return this.category;
	}
	
	private void setCategory(final String category) 
	{
		this.category = category;
	}

	public ISprite getSprite() 
	{
		return this.sprite;
	}

	private void setSprite(final ISprite sprite) 
	{
		this.sprite = sprite;
	}

	public Permeability getPermeability() 
	{
		return this.permeability;
	}

	public void setPermeability(final Permeability permeability) 
	{
		this.permeability = permeability;
	}

	protected ILorannWorld getNettleWorld() 
	{
		return this.lorannWorld;
	}

	public void setNettleWorld(final ILorannWorld lorannWorld) 
	{
		this.lorannWorld = lorannWorld;
	}

	@Override
	public Image getImage() 
	{
		return this.getSprite().getImage();
	}
}
