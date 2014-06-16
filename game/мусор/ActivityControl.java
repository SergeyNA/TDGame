package TDGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ActivityControl extends KeyAdapter {
	
	public void keyPressed(KeyEvent e, boolean f) {
		/*if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = true;
		}*/
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			f = true;
		}
	}

	public void keyReleased(KeyEvent e, boolean f) {
		/*if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		}*/
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			f = false;
		}
	}
	
}