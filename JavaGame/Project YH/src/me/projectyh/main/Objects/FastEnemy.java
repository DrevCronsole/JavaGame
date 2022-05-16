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

public class FastEnemy extends GameObject{

	public Handler handler;
	Random r = new Random();
	
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		r.nextInt(x);
		r.nextInt(y);
		
		
		
		
		velX = 2;
		velY = 9;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.CYAN, 16, 16, 0.1f, handler));
		
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
	}
	
	public Rectangle getbounds() {return new Rectangle((int)x,(int)y, 16, 16);}
	
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, 16, 16); // g.fillRect(x, y, 32, 64);
	}
	

}
