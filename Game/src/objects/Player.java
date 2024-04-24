package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import core.Window;

public class Player {
	
	public Window w;
	public int width,height;
	public double x,y;
	public double velx , vely;
	public double speed = 2;
	
	public double JumpVelocity = 2;
	
	public boolean Falling = false;
	public boolean Fall = false;
	
	public Player(Window w, double x, double y, int width, int height) {
		this.w=w;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void tick() {
		x+=velx;
		y+=vely;
		
		Fall = false;
		if (vely < w.level.Gravity) {
			Fall = true;
			if (Falling)
				vely += 0.2;
		}else if (!Falling && vely > 0) 
			vely = 0;
		

		Collisions();
	}
	
public void Collisions()	{
		Falling = true;
		for (Item i : w.level.items)	{
			if (i.ID == ObjectIDS.Platform)	{
				Platform p = (Platform) i;
				
				Rectangle playerRect = new Rectangle((int)(x+velx),(int)(y+vely),width,height);
				// intersects : kiểm tra va chạm
				if (playerRect.intersects(p.x, p.y, p.width, p.height))	{
					// kiểm tra đứng trên Platform
					if (y+height <= p.y+1)	{
						Falling = false;
//						System.out.println(1);
						if (vely > 0)	{
							vely = 0;
							y = p.y-height+1;
							velx = 0;
						}
					} else if (vely < 0)	{
						// chạm dưới Platform
						Falling = true;
						velx = -1*velx;
						y -= (vely+1);
						vely = -1*vely;
//						System.out.println(2);
						}
					else	{
						// va đập Platform
						velx = -1*velx;
						Falling = true;
//						System.out.println(3);
					}
				} 
			}
		}
	}
	
	public void Render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x,(int) y, width, height);
	}
}


