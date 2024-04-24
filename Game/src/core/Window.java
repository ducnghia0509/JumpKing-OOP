package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import inputs.KeyboardHandler;
import level.LevelHandler;
import objects.Player;

public class Window extends Canvas implements Runnable{

	// Variables
	private static final long serialVersionUID = 6741505667874806983L;
	private Thread thread;
	private boolean running = false;
	
	public KeyboardHandler Klistener = new KeyboardHandler(this);
	public LevelHandler level = new LevelHandler(this);
	// Variables

	
	// Run when a new window is created
	public Window(String Title) {
		super();
		
		// khởi tạo frame
		JFrame frame = new JFrame(Title);
		
		// set size
		frame.setSize(614,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set visible /frame logic properties
		frame.setResizable(false); // cho phép thay đổi kích cỡ cửa sổ
		frame.setVisible(true);   // hiện thị cửa sổ
		frame.add(this);		// thêm lớp Window vào cửa sổ JFrame


		this.setBackground(Color.black);
		this.setFocusable(true);
		
		
	}
	
	// start game logic
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		
		
	}
	
	// stop game logic
	public void stop() {
		running = false;
		
		// stop thread
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() { // phương thức Run từ giao diện Runnable
		long lastTime = System.nanoTime();	// trả về tg hiện tại ở nano giây
		double amountOfTicks = 60.0; // fps frame per second
		// 1s = 1000000000 ns
		// 1s = 1000 milis
		double ns = 1000000000/amountOfTicks;
		double delta =0;
		long timer = System.currentTimeMillis();  // trả về thời gian hiện tại tính bằng mili giây
		int updates = 0;
		int frames = 0;
		
		while (running) 
		{
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			Render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer +=1000;
//				System.out.println("FPS: " + frames + " " + "TICK: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	// update window
	public void tick() {
		level.tick();
	}
	// paint to the window
	public void Render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			bs = this.getBufferStrategy();
		}
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		level.Render(g);
		
		
		bs.show();
		g.dispose(); // giải phóng tài nguyên đồ họa
		
	}
}
