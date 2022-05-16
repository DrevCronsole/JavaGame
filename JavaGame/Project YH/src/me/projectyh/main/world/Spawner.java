package me.projectyh.main.world;

import java.util.Random;

import me.projectyh.main.Game;
import me.projectyh.main.HUD.Hud;
import me.projectyh.main.Objects.ALLY;
import me.projectyh.main.Objects.BasicEnemy;
import me.projectyh.main.backend.Handler;
import me.projectyh.main.enums.ID;

public class Spawner {
	
	private Handler handler;
	private Hud hud;
	private Random r;
	
	private int scoreKeep = 0;
	
	public Spawner(Handler handler, Hud hud, Random r) {
		this.handler = handler;
		this.r = r;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		if(scoreKeep >= 150) {
			scoreKeep = 0;
			hud.setlevel(hud.getlevel() + 1);
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			if(hud.getlevel() == 15) {
				handler.addObject(new ALLY(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.ALLY, handler));
			}
		}
		
	}
	
}
