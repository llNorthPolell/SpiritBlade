package com.northpole.spiritblade.controllers.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.controllers.CollisionController;
import com.northpole.spiritblade.controllers.MapController;
import com.northpole.spiritblade.gameEntities.Player;

public class GameController {
	public static GameController instance;
	private OrthographicCamera camera;
	private Player player;
	
	private InputProcessor input;
	private MapController mapController;
	
	private CollisionController collisionController;
	
	
	public GameController() {
		instance = this;
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		
		this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,w,h);
        this.camera.update();
        
		this.mapController = new MapControllerImpl(this.camera);
		
		mapController.spawnMonsters();
		
		this.player = new Player(new Vector3(300,350,0));
		
		mapController.spawnPlayer(player);
		
		this.collisionController = new CollisionControllerImpl();
		
		this.input = new GameInputProcessor(this.camera, player);
	}
	
	public Player getPlayer() {
		return player;
	}

	public MapController getMapController() {
		return this.mapController;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	
	public CollisionController getCollisionController() {
		return collisionController;
	}

}
