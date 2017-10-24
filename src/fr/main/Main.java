package fr.main;


import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	
	public static void main(String[] args) throws SlickException {
		
		System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new Main(),1280,720,false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}





	public Main() {
		super("Bonjour à tous et à toutes");
		}



		@Override
		public void initStatesList(GameContainer container) throws SlickException {
			addState(new World());
		}


	}
