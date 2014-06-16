package TDGame;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

public class ScriptEvent {
	
	static final int xStartPosition = 300;
	static final int yStartPosition = 170;
	static final Font textFont = new Font("Dialog", Font.BOLD, 13);
	
	final Image dialogBackGround;
	
	private int delay;
	private String[] message;
	
	ScriptEvent(Image dialogBackGround, int delay, String[] message) {
		this.dialogBackGround = dialogBackGround;
		this.delay = delay;
		this.message = message;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public void draw(Graphics2D g) {
		Font temp = g.getFont();
		g.setFont(textFont);
		g.drawImage(dialogBackGround, 250, 100, null);
		for(int i = 0; i < message.length; i++) {
			g.drawString(message[i], xStartPosition, yStartPosition + i * 20);
		}
		g.setFont(temp);
	}
	
}
