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

public class SmortEnemy extends GameObject{

	private Handler handler;
	private GameObject Player;
	
	Random r = new Random();
	
	
	public SmortEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				Player = handler.object.get(i);
			}
			if(handler.object.get(i).getId() == ID.Player2) {
				Player = handler.object.get(i);
			}
		}
		
		r.nextInt(x);
		r.nextInt(y);
		
		
	}

	public Rectangle getbounds() {return new Rectangle((int)x,(int) y, 16, 16);}
	
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - Player.getX() - 12;
		float diffY = y - Player.getY() - 12;
		float distance = (float) Math.sqrt((x-Player.getX())*(x-Player.getX())+(y-Player.getY()*(y-Player.getY())));
		
		velX = (float) ((-1.0/distance)*diffX);
		velY = (float) ((-1.0/distance)*diffY);
		
		handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.GRAY, 16, 16, 0.1f, handler));
		
		//if(y <= 0 || y >= Game.HEIGHT - 64) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH -64) velX *= -1;
	}

	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect((int)x, (int)y, 16, 16); // g.fillRect(x, y, 32, 64);
	}
	
}
