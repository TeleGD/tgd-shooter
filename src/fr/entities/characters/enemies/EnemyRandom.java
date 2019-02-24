package fr.entities.characters.enemies;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.entities.characters.Player;
import fr.entities.projectiles.Projectile;
import fr.entities.projectiles.StraightProjectile;

public class EnemyRandom extends Enemy{


	private double xbox;
	private double ybox;
	private double widthbox;
	private double heightbox;
	private double angle;
	private double oldAngularSpeed;
	private double angularSpeed;
	private double speed;
	private double alea;
	private double va; //vitesse angulaire fixée
	private Image image;
	private boolean outOfBox;
	private int i;
	private String nameSprite = "img/projectiles/shot.png";

	public EnemyRandom(double x, double y, Player player, ArrayList<Projectile> projectiles, double xbox, double ybox, double widthbox, double heightbox) {
		super(x, y, player,projectiles);
		this.angle=0;
		this.speed=0.25;
		this.oldAngularSpeed=0;
		this.angularSpeed=0;
		this.va=0.005;
		compt=0;
		this.life=10;
		this.lifeInit=10;
		this.width=60;
		this.height=60;
		this.xbox=xbox;
		this.ybox=ybox;
		this.widthbox=widthbox;
		this.heightbox=heightbox;
		this.outOfBox=false;
		this.score=40;
		this.i=0;
		try {
			image=new Image("img/ship/enemy4.png");
			image=image.getScaledCopy((float) 0.5);
		} catch (SlickException e) {
			// nous donne la trace de l'erreur si on ne peut charger l'image correctement
			e.printStackTrace();
		}
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.red);
		//arg2.fillRect((float)x, (float)y, (float)width, (float)height);
		arg2.drawImage(image, (float)(x+width/2-image.getWidth()/2), (float)(y+height/2-image.getHeight()/2));
		showLife(arg2);
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		if(compt>50){
			compt=0;

			projectiles.add(new StraightProjectile(nameSprite, x+width/2,y+height/2,2,player,false,0,0.2));


		}
		compt++;

		if ((dirX != 'a') && (dirY != 'a')) {  // Si on est encore en phase de placement :
			whereToGo(speedX, xbox+widthbox/2, this.x, dirX, 'x');
			whereToGo(speedY, ybox+heightbox/2, this.y, dirY, 'y');
		}
		else                 // Sinon on suis le patern
			move(delta);

		moveX(delta);
		moveY(delta);
		colProj();
	}

	public void move(int delta) {
		alea=Math.random();
		angularSpeed=oldAngularSpeed;

		if (outOfBox) {
			i+=1;
			if (i>=40) {outOfBox=false;i=0;};
		} else {
			if (x>=xbox && x<=xbox+widthbox && y>=ybox && y<=ybox+heightbox ) {
				if (oldAngularSpeed==0) {
					if (alea<0.1) angularSpeed=va;
					if (alea>0.9) angularSpeed=-va;
				}
				if (oldAngularSpeed==va) {
					if (alea<0.03) angularSpeed=0;
					if (alea>0.98) angularSpeed=-va;
				}
				if (oldAngularSpeed==-va) {
					if (alea<0.03) angularSpeed=0;
					if (alea>0.98) angularSpeed=va;
				}
			} else {
				outOfBox=true;
				angularSpeed=va;
				if (alea<0.5) angularSpeed=-va;
			}
		}
		oldAngularSpeed=angularSpeed;
		angle+=angularSpeed*delta;
		speedX = this.speed*Math.cos(angle);
		speedY= this.speed*Math.sin(angle);
	}
}
