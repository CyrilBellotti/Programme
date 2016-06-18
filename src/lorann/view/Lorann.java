package lorann.view;

import java.io.IOException;

import javax.swing.SwingUtilities;

import lorann.contract.ILorannFrame;
import lorann.contract.ILorannWorld;
import lorann.controller.LorannPlay;
import lorann.model.LorannWorld;

public final class Lorann implements Runnable {
	private final ILorannWorld	lorann;
	private final LorannPlay lorannPlay;
	private ILorannFrame lorannFrame;

	public Lorann() throws IOException {
		this.lorann = new LorannWorld("nettleWorld.txt");
		this.lorannPlay = new LorannPlay(this.lorann);
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void run() {
		this.lorannFrame = new LorannFrame("Jeu Lorann", this.getNettleWorld(), this.getNettlePlay());
		this.lorannPlay.setLorannFrame(this.lorannFrame);
	}

	private ILorannWorld getNettleWorld() {
		return this.lorann;
	}

	private LorannPlay getNettlePlay() {
		return this.lorannPlay;
	}
}
