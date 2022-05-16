package me.projectyh.main.HUD;

import java.awt.Color;
import java.awt.Graphics;

import me.projectyh.main.Game;
import me.projectyh.main.utils.FpsCounter;
import me.projectyh.main.utils.FpsCounter.Event;
import me.projectyh.main.utils.FpsCounter.Listener;

public class Hud {
	
	public FpsCounter fpsc;
	public Event e;
	public Listener i;
	
	public static int HEALTH = 100;
	public static int HEALTH2 = 100;
	private long score = 0;
	private long level = 1;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		HEALTH2 = Game.clamp(HEALTH2, 0, 100);
		score++;
	}
	
	public void render(Graphics g, Event e) { 
		if(HEALTH == 0) {
			g.setColor(Color.BLACK);
			g.drawString("Died!", 80, 15);
		}
		if(HEALTH2 == 0) {
			g.setColor(Color.BLACK);
			g.drawString("Died!", 200, 15);
		}
		
		if (HEALTH == 0 && HEALTH2 == 0) {
		   g.setColor(Color.BLACK);
		   g.drawString("BOTH PLAYERS DIED!, Game Over!", Game.WIDTH/2-22, Game.HEIGHT/2-32);
		}
		player2(g);
		
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 100, 16);
		g.setColor(new Color((int) (255 / (HEALTH / 27 + 1)), (int) (HEALTH * 2.55), 0));
		g.fillRect(15, 15,HEALTH, 16);;
		g.setColor(Color.BLACK);
		g.drawRect(15, 15, 100, 16);
		
		
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, 20, 45);
		g.drawString("Level: " + level, 20, 55);
		g.drawString("FPS: " + (int) e.getFPSCounter().nextFrame(), 20, 65);
		g.drawString("Player 1", 20, 15);
	}
	public void player2(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(150, 15, 100, 16);
		g.setColor(new Color((int) (255 / (HEALTH2 / 27 + 1)), (int) (HEALTH2 * 2.55), 0));
		g.fillRect(150, 15,HEALTH2, 16);;
		g.setColor(Color.BLACK);
		g.drawRect(150, 15, 100, 16);
		g.setColor(Color.BLACK);
		g.drawString("Player 2", 150, 15);
	}
	
	
	public void score(long score) {this.score = score;}
	public int getscore() {return (int) score;}
	public int getlevel() {return (int) level;}
	public void setlevel(int level) {this.level = level;}
}	

