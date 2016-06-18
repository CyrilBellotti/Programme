package lorann.model;

import lorann.contract.IDoActionOnHeroes;
import lorann.contract.ISprite;

public abstract class MotionlessElement extends Element implements IDoActionOnHeroes {
	private final char fileSymbol;

	public MotionlessElement(final ISprite sprite, final Permeability permeability, final char fileSymbol, final String category) {
		super(sprite, permeability, category);
		this.fileSymbol = fileSymbol;
	}

	public char getFileSymbol() {
		return this.fileSymbol;
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		return ActionOnHeroes.NOP;
	}
}