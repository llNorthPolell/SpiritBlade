package com.northpole.spiritblade.controllers.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.controllers.CollisionController;
import com.northpole.spiritblade.controllers.WorldController;
import com.northpole.spiritblade.gameEntities.Player;

public class GameController {
	public static GameController instance;
	private OrthographicCamera camera;
	private Player player;
	
	private InputProcessor input;
	private WorldController worldController;
	
	private CollisionController collisionController;
	
	
	public GameController() {
		instance = this;
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		
		this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,w,h);
        this.camera.update();
        
		this.player = new Player(new Vector3(300,350,0));
		
		this.worldController = new WorldControllerImpl(this.camera);
		
		this.collisionController = new CollisionControllerImpl();
		
		this.input = new GameInputProcessor(this.camera, player);
	}
	
	public Player getPlayer() {
		return player;
	}

	public WorldController getWorldController() {
		return worldController;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	
	public CollisionController getCollisionController() {
		return collisionController;
	}

}
