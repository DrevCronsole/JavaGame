package me.projectyh.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import me.projectyh.main.HUD.Hud;
import me.projectyh.main.Objects.BasicEnemy;
import me.projectyh.main.Objects.Player;
import me.projectyh.main.Objects.Player2;
import me.projectyh.main.Objects.SmortEnemy;
import me.projectyh.main.backend.Handler;
import me.projectyh.main.backend.Window;
import me.projectyh.main.enums.ID;
import me.projectyh.main.utils.FpsCounter;
import me.projectyh.main.utils.FpsCounter.Event;
import me.projectyh.main.utils.FpsCounter.Listener;
import me.projectyh.main.utils.KeyInput;
import me.projectyh.main.world.Spawner;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -1563280278977116136L;
	public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
	
	private boolean running = false;
	private Thread thread;
	private Random r;
	private Handler handler;
	private Hud hud;
	private Spawner spawner;
	private FpsCounter fpsc;
	private Listener i;
	private Event e;
	
	
	//Functions
	public Game() {
		r = new Random();
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		hud = new Hud();
		spawner = new Spawner(handler, hud, r);
		fpsc = new FpsCounter();
		e = new Event(fpsc);
		fpsc.addFPSCounterListener(i);
		new Window(WIDTH, HEIGHT, "Dodge!", this);
		
		handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler, hud));
		handler.addObject(new Player2(WIDTH/2-42, HEIGHT/2-32, ID.Player2, handler, hud));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		handler.addObject(new SmortEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.SmortEnemy, handler));
	}
	

	public static void main(String args[]) {	
		new Game();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		spawner.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		//Graphics Section
		Graphics g = bs.getDrawGraphics();
		
		//Background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g, e);
		
		g.dispose();
		bs.show();
		//Graphics Section
	}
	
	private void FramesAndTicks() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
				long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
					while(delta >= 1){
						tick();
						delta--;
					}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 100){
				timer += 100;
				frames = 0;
			}
		}
	}
		
	public void run() {
		this.requestFocus();
		FramesAndTicks(); 
		stop();
	}
	
	public static int clamp(int val, int min, int max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		fpsc.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			fpsc.stop();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Functions
	
}
