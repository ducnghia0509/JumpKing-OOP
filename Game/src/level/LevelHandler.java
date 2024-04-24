package level;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import core.Window;
import objects.Item;
import objects.ObjectIDS;
import objects.Platform;
import objects.Player;


public class LevelHandler  {
	
	// declare variables
	private Window w;
	public double Gravity = 5;
	
	public LinkedList<Item> items = new LinkedList<Item>();
	
	private int seed;
	
	public int CameraX=0, CameraY=0;
	// declare variables
	
	
	// Game Objects
	public Player player = null;
	// Game Objects
	
	// Runs when a new level is created!
	public LevelHandler(Window w) {
		this.w=w;
		
		// set random
		Random r = new Random();
		seed = r.nextInt();
		
		// add in player
		player = new Player(w,400,-900,15,35);
		
		// SINH RA CÁC ĐỐI TƯỢNG ĐỆM (x,y,width,height):
		// giới hạn khu vực nhảy
		items.add(new Platform(ObjectIDS.Platform,Color.gray, 0  , 500,600, 500));
		items.add(new Platform(ObjectIDS.Platform,Color.gray, 0  ,-1000, 100,1500));
		items.add(new Platform(ObjectIDS.Platform,Color.gray, 500  ,-1000, 100,1500));
		items.add(new Platform(ObjectIDS.Platform,Color.gray, 0  , -1500,600, 500));
		// giới hạn khu vực nhảy
		
		// các đối tượng đệm
		int plus=250;
		int x1=400, y1=400;
		int x3=300, y3=250;
		int x2=100, y2=300;
		int count = 6;
		for (int i=1; i<=count;i++) {
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x1, y1, 40, 20 ));
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x1-10, y1, 40, 20 ));
			//
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x2, y2, 40, 20 ));
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x2+10, y2, 40, 20 ));
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x2+20, y2+20, 40, 20 ));
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x2+30, y2+30, 40, 20 ));
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x2+50, y2+40, 40, 20 ));
			//
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x3, y3, 40, 20 ));
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x3-10, y3+10, 40, 20 ));
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x3+20, y3+20, 40, 20 ));
			items.add(new Platform(ObjectIDS.Platform,Color.gray, x3-20, y3+30, 40, 20 ));
			// end
			y1-=plus;y2-=plus;y3-=plus;
		}
		
		
		
		// các đối tượng đệm
	}
	
	public void Render(Graphics g) {
		g.translate(-CameraX, -CameraY);
		for (Item i : items) {
			i.Render(g);
		}
		player.Render(g);
		g.translate(CameraX, CameraY);
	}
	public void tick() {
		// cập nhật Camera
		while (player.y > CameraY+600) 
			CameraY += 3;
		while (player.y < CameraY+100) 
			CameraY -= 3;
		for (Item i : items)
			i.tick();
		player.tick();
	}
	
}
