package me.Elithous.Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import me.Elithous.Window.Window;
import me.Elithous.util.settings;

public class Main extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 762147416208638509L;

	public static int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	public static boolean fpsCounter = false;
	public static int fpsCount = 0;
	
	private Thread thread;
	private boolean running = false;
	
	public Main(){
		new Window(WIDTH, HEIGHT, "Change Me!", this);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		 long lastTime = System.nanoTime();
	     double amountOfTicks = 60.0;
	     double ns = 1000000000 / amountOfTicks;
	     double delta = 0;
	     long timer = System.currentTimeMillis();
	     int frames = 0;
	     while(running){
	    	 long now = System.nanoTime();
	         delta += (now - lastTime) / ns;
	         lastTime = now;
	         while(delta >=1){
	        	 tick();
	        	 delta--;
	        	 }
	         if(running)
	        	 render();
	         frames++;
	         if(System.currentTimeMillis() - timer > 1000){
	        	 timer += 1000;
	        	 fpsCount = frames;
	        	 frames = 0;
	        	 }
	        }
	     stop();
	}
	
	private void tick() {
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();	
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(fpsCounter) {
			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.SERIF, 0, 30));
			g.drawString("" + fpsCount, 0, 30);
		}		
		g.dispose();
		bs.show();
	}
	
	public static void init() {
		String[] sets = null;
		try {
			sets = settings.load();
		} catch(Exception e) {
			try {
				settings.resetToDefault();
				System.out.println(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			};
		}
		WIDTH = Integer.parseInt(sets[0]);
		
		int seperator = sets[1].indexOf(':');
		int widthRatio = Integer.parseInt(sets[1].substring(seperator+1));
		int heightRatio = Integer.parseInt(sets[1].substring(0, seperator));
		
		HEIGHT = WIDTH / heightRatio * widthRatio;
		
		if(Integer.parseInt(sets[2]) == 1) fpsCounter = true;
	}
	
	public static void main(String args[]) {
		init();
		new Main();
	}
}
