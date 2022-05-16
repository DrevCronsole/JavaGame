package me.projectyh.main.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import me.projectyh.main.Game;
import me.projectyh.main.Objects.cosm.Trail;
import me.projectyh.main.backend.Handler;
import me.projectyh.main.enums.ID;
import me.projectyh.main.utils.GameObject;

public class BasicEnemy extends GameObject{

	private Handler handler;
	
	Random r = new Random();
	
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		r.nextInt(x);
		r.nextInt(y);
		
		
		velX = 4;
		velY = 4;
	}

	public Rectangle getbounds() {return new Rectangle((int) x, (int) y, 16, 16);}
	
	public void tick() {
		x += velX;
		y += velY;
		
		handler.addObject(new Trail((int)x, (int) y, ID.Trail, Color.RED, 16, 16, 0.1f, handler));
		
		if(y <= 0 || y >= Game.HEIGHT - 64) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH -64) velX *= -1;
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int) y, 16, 16); // g.fillRect(x, y, 32, 64);
	}
	
}
