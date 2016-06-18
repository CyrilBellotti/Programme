package lorann.view;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import lorann.controller.UserOrder;

public abstract class LorannView {
	public static int	MAP_ZOOM	= 11;
	public static int	MEETING_ZOOM = 11;
	public final static int	VIEW_MODE_MAP = 1;
	public final static int	VIEW_MODE_MEETING = 2;

	public static UserOrder keyCodeToUserOrder(final int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_UP:
				return UserOrder.UP;
			case KeyEvent.VK_RIGHT:
				return UserOrder.RIGHT;
			case KeyEvent.VK_DOWN:
				return UserOrder.DOWN;
			case KeyEvent.VK_LEFT:
				return UserOrder.LEFT;
			case KeyEvent.VK_EQUALS:
				return UserOrder.FIRE;
			default:
				return UserOrder.NOP;
		}
	}

	public static void displayMessage(final String message) 
	{
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static UserOrder topRightKeysCodeToUserOrder()
	{
		return UserOrder.TOPRIGHT;
	}

	public static UserOrder topLeftKeysCodeToUserOrder()
	{
		return UserOrder.TOPLEFT;
	}

	public static UserOrder bottomRightKeysCodeToUserOrder()
	{
		return UserOrder.BOTTOMRIGHT;
	}

	public static UserOrder bottomLeftKeysCodeToUserOrder()
	{
		return UserOrder.BOTTOMLEFT;
	}

}
