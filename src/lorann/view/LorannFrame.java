package lorann.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import lorann.contract.ILorannFrame;
import lorann.contract.ILorannWorld;
import lorann.contract.IOrderPerformed;

public class LorannFrame extends JFrame implements KeyListener, ILorannFrame {
	private static final long	serialVersionUID	= 1500600853286674118L;
	private final LorannBoardPanel	mapPanel;
	private LorannBoardPanel	meetingPanel;
	private final IOrderPerformed	lorannPlay;
	private final LorannCardView	lorannCardView;
	ArrayList<Integer> touches = new ArrayList<Integer>();

	public LorannFrame(final String title, final ILorannWorld lorannWorld, final IOrderPerformed lorannPlay) {
		this.setTitle(title);
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.lorannPlay = lorannPlay;
	this.mapPanel = new LorannBoardPanel(new Dimension(lorannWorld.getWidth(), lorannWorld.getHeight()), lorannWorld.getElements(), lorannWorld.getMobiles(), new Point(10,6), LorannView.MAP_ZOOM);
		this.setResizable(false);
		lorannWorld.addObserver(this.mapPanel);
		this.addKeyListener(this);
		this.lorannCardView = new LorannCardView();
		this.getContentPane().setLayout(this.lorannCardView);
		this.getContentPane().add(this.mapPanel, "MAP");
		this.setVisible(true);
	}

	private IOrderPerformed getLorannPlay() {
		return this.lorannPlay;
	}

	@Override
	public void setMeeting(final ILorannWorld lorannWorld) {
		if (this.meetingPanel != null) {
			this.lorannCardView.removeLayoutComponent(this.meetingPanel);
		}
		this.meetingPanel = new LorannBoardPanel(new Dimension(lorannWorld.getWidth(), lorannWorld.getHeight()), lorannWorld.getElements(),
				lorannWorld.getMobiles(), new Point(10,6), LorannView.MEETING_ZOOM);
		lorannWorld.addObserver(this.meetingPanel);
		this.getContentPane().add(this.meetingPanel, "MEETING");
	}

	public void refresh(final Point center) {
		this.mapPanel.setCenter(center);
	}

	@Override
	public void setViewMode(final int viewMode) {
		switch (viewMode) {
			case LorannView.VIEW_MODE_MEETING:
				this.lorannCardView.show(this.getContentPane(), "MEETING");
				break;
			case LorannView.VIEW_MODE_MAP:
				this.lorannCardView.show(this.getContentPane(), "MAP");
				break;
			default:
				break;
		}
	}

	@Override
	public void keyPressed(final KeyEvent keyEvent) {
		try {
			if(touches.contains(keyEvent.getKeyCode()) == false)
			{
				touches.add(keyEvent.getKeyCode());
			}
			
		//	this.getLorannPlay().orderPerform(LorannView.keyCodeToUserOrder(keyEvent.getKeyCode()));
			if(touches.contains(38) == true && touches.contains(39) == true){
				this.getLorannPlay().orderPerform(LorannView.topRightKeysCodeToUserOrder());
			}
		else if(touches.contains(38) == true && touches.contains(37) == true){
			this.getLorannPlay().orderPerform(LorannView.topLeftKeysCodeToUserOrder());
		}
		else if(touches.contains(40) == true && touches.contains(39) == true){
			this.getLorannPlay().orderPerform(LorannView.bottomRightKeysCodeToUserOrder());
		}
 
		else if(touches.contains(40) == true && touches.contains(37) == true)
			 {
					this.getLorannPlay().orderPerform(LorannView.bottomLeftKeysCodeToUserOrder());
				}
				else this.getLorannPlay().orderPerform(LorannView.keyCodeToUserOrder(keyEvent.getKeyCode()));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(final KeyEvent keyEvent) 
	{
		System.out.println("touche relachée donc suppression de la touche :" + keyEvent.getKeyCode());
		touches.remove(touches.indexOf(keyEvent.getKeyCode()));
	}

	@Override
	public void keyTyped(final KeyEvent keyEvent) {
	}



}
