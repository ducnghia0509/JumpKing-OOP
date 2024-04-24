package objects;

import java.awt.Color;
import java.awt.Graphics;

public class Platform extends Item{
	
	public int x,y,width,height;
	public Color c;

	public Platform(byte ID,Color c, int x, int y, int width, int height) {
		super(ID);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.c=c;
	}

	public void Render(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}

	public void tick() {

	}
}
