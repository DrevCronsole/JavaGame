package me.projectyh.main.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import me.projectyh.main.Game;
import me.projectyh.main.HUD.Hud;
import me.projectyh.main.Objects.cosm.Trail;
import me.projectyh.main.backend.Handler;
import me.projectyh.main.enums.ID;
import me.projectyh.main.utils.GameObject;
// Player Class
public class Player extends GameObject {

	Random r = new Random();
	public Handler handler;
	public Hud hud;
	
	public Player(int x, int y, ID id, Handler handler, Hud hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
	}
	
	public Rectangle getbounds() {return new Rectangle((int)x, (int)y, 32, 32);}
	
	public void tick() {
			x += velX;
			y += velY;
			
			
			
			handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.BLUE, 32, 32, 0.1f, handler));
			
			x = Game.clamp((int)x, 0, Game.WIDTH - 64);
			y = Game.clamp((int)y, 0, Game.HEIGHT - 64);
			
			collision();
	}

	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.BasicEnemy) {
				if(getbounds().intersects(tempObject.getbounds())) {
					//collision code
					Hud.HEALTH -= 1;
					handler.removeObject(tempObject);
				}
			}
			if(tempObject.getId() == ID.MassEnemy) {
				if(getbounds().intersects(tempObject.getbounds())) {
					//collision code
					Hud.HEALTH -= 6;
					handler.removeObject(tempObject);
				}
			}
			if(tempObject.getId() == ID.FastEnemy) {
				if(getbounds().intersects(tempObject.getbounds())) {
					//collision code
					Hud.HEALTH -= 7;
					handler.removeObject(tempObject);
				}
			}
			if(tempObject.getId() == ID.ALLY) {
				if(getbounds().intersects(tempObject.getbounds())) {
					//collision code
					Hud.HEALTH += 10;
				}	
			}
			if(tempObject.getId() == ID.SmortEnemy) {
				if(getbounds().intersects(tempObject.getbounds())) {
					//collision code
					Hud.HEALTH -= 10;
				}	
			}
			if(Hud.HEALTH == 0) {
				if(tempObject.getId() == ID.Player) {
					handler.removeObject(tempObject);
				}
			}
		}	
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, 32, 32); // g.fillRect(x, y, 32, 64);
	}
	
}
