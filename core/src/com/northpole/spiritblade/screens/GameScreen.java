package com.northpole.spiritblade.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.northpole.spiritblade.controllers.WorldController;
import com.northpole.spiritblade.gameEntities.Collider;
import com.northpole.spiritblade.gameEntities.Player;
import com.northpole.spiritblade.gameEntities.items.equipment.impl.PlateArmor;
import com.northpole.spiritblade.gameEntities.items.equipment.impl.PlateBoots;
import com.northpole.spiritblade.gameEntities.items.equipment.impl.PlateGauntlets;
import com.northpole.spiritblade.gameEntities.items.equipment.impl.PlateGreaves;
import com.northpole.spiritblade.gameEntities.items.equipment.impl.PlateHelmet;
import com.northpole.spiritblade.gameEntities.items.equipment.impl.PlateShoulders;
import com.northpole.spiritblade.gameEntities.items.equipment.impl.ShortSword;
import com.northpole.spiritblade.controllers.impl.GameController;

public class GameScreen implements Screen {
    private GameController gameController;
	private WorldController worldController;
	private Player player;
	private Camera camera;
	
	private ShapeRenderer shapeRenderer;
	
	
	public GameScreen() {
		this.gameController = new GameController();
		this.worldController = gameController.getWorldController();
		this.player = gameController.getPlayer();
		this.camera = gameController.getCamera();
		
		
		this.shapeRenderer = new ShapeRenderer();
		
		this.player.equip(new ShortSword(0));
		this.player.equip(new PlateHelmet(0));
		this.player.equip(new PlateArmor(0));
		this.player.equip(new PlateGreaves(0));
		this.player.equip(new PlateBoots(0));
		this.player.equip(new PlateShoulders(0));
		this.player.equip(new PlateGauntlets(0));
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
             
        
        camera.position.set(player.getPosition().x+Player.SPRITE_WIDTH/2, player.getPosition().y, 0);
        camera.update();
        
        worldController.render();
        
        player.setProjectionMatrix(camera.combined);
        player.render();
        
        //drawCollisionBorders();
        
        Collider incomingCollider = player.getCollider().getCollision(worldController.getNearbyColliders(player.getPosition(),100f));
        player.applyNormalForce(incomingCollider);

	}

	private void drawCollisionBorders() {
        Collider playerCollider = player.getCollider();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeType.Line);
        
        for (Collider worldObjectCollider : worldController.getNearbyColliders(player.getPosition(),100f)) {	
        	shapeRenderer.setColor(Color.BLUE);
    		shapeRenderer.rect(worldObjectCollider.getPosition().x, worldObjectCollider.getPosition().y, 
    				worldObjectCollider.getColliderSize().x, worldObjectCollider.getColliderSize().y);
        }
    
		
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(playerCollider.getPosition().x, playerCollider.getPosition().y, 
				playerCollider.getColliderSize().x, playerCollider.getColliderSize().y);
		shapeRenderer.end();

	}

	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		player.dispose();
		worldController.dispose();
	}

}
