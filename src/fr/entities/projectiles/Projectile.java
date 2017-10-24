package fr.entities.projectiles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.Movable;
import fr.entities.characters.Player;
import fr.util.Circle;

public class Projectile extends Movable implements Circle{

	protected int radius;
	
	protected Player p;
	
	protected boolean destructed;
	
	public Projectile(double centerPointX, double centerPointY, int radius,Player player) {
		x = centerPointX;
		y = centerPointY;
		this.radius = radius;
		speedY = 0.5;
		p = player;
		destructed = false;
	}
	
	
	
	@Override
	public int getRadius() {
		return radius;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	protected void outOfBounds(){
		if (this.speedY>0 && this.y>1300)
			destructed=true;;
	}
	
	public boolean isDestructed(){
		return destructed;
	}

}
