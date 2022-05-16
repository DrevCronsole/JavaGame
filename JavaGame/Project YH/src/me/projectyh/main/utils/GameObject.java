package me.projectyh.main.utils;

import java.awt.Graphics;
import java.awt.Rectangle;

import me.projectyh.main.enums.ID;

public abstract class GameObject {
	
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getbounds();
	public static int clamp(int val, int min, int max) {return Math.max(min, Math.min(max, val));}
	//sets
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setId(ID id) {this.id = id;}
	public void setVelX(int velX) {this.velX = velX;}
	public void setVelY(int velY) {this.velY = velY;}
	//gets
	public int getX() {return (int)x;}
	public int getY() {return (int)y;}
	public ID getId() {return id;}
	public int getVelX() {return (int)velX;}
	public int getVelY() {return (int)velY;}
	
}
