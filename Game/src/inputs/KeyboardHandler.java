package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Window;

public class KeyboardHandler implements KeyListener{
	
	private Window w;
	private long StartTime=0;
	private long StopTime;
	private long PressedTime;
	
	private boolean MovingLeft = false;
	private boolean HoldingKey = false;
	
	
	public KeyboardHandler(Window w) {
		this.w=w;
		w.addKeyListener(this);
	}

	public void keyTyped(KeyEvent e) {
		
	}	
	
	// nhấn xuống
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_Q && w.level.player.Fall) {
            w.level.player.velx = -w.level.player.speed;
            MovingLeft = true;
        }
        if (key == KeyEvent.VK_P && w.level.player.Fall) {
            w.level.player.velx = w.level.player.speed;
            MovingLeft = false;
        }

		if (key == KeyEvent.VK_SPACE && !HoldingKey) {
			StartTime = System.currentTimeMillis();
			HoldingKey = true;
		}
	}
	// nhả ra
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE && HoldingKey && !w.level.player.Falling) {
			StopTime = System.currentTimeMillis();
			long ElapsedTime = StopTime - StartTime; 
			PressedTime = Math.min(ElapsedTime, 1000);
			w.level.player.vely = - (double) PressedTime * 0.008;
			w.level.player.velx = 0;
			HoldingKey = false;
		}
		if ((key == KeyEvent.VK_Q && MovingLeft ) || (key == KeyEvent.VK_P && !MovingLeft)) {
			w.level.player.velx = 0;
		}
	}
	 
}
