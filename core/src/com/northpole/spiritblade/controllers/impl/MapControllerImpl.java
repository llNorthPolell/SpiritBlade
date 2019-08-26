package com.northpole.spiritblade.controllers.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.controllers.MapController;
import com.northpole.spiritblade.gameEntities.BoxCollider;
import com.northpole.spiritblade.gameEntities.Collider;
import com.northpole.spiritblade.gameEntities.GameCharacter;
import com.northpole.spiritblade.gameEntities.HumanoidMonster;
import com.northpole.spiritblade.gameEntities.Player;

public class MapControllerImpl implements MapController {
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera cam;
    
    private MapObjects mapCollidableObjects;
    private Map<Rectangle,Collider> mapObjectColliders;
    
    private Map<String,GameCharacter> gameCharacters;
    
    public MapControllerImpl(OrthographicCamera cam) {
    	this.tiledMap = new TmxMapLoader().load("maps/Tut1.tmx");
    	this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        this.cam = cam;
        
        mapObjectColliders = new HashMap<Rectangle,Collider>();
        gameCharacters = new HashMap<String, GameCharacter>();
        
        mapCollidableObjects = tiledMap.getLayers().get("Collision").getObjects();

        
        for (RectangleMapObject rectangleObject : mapCollidableObjects.getByType(RectangleMapObject.class)) {
        	Rectangle rectangle = rectangleObject.getRectangle();
        	mapObjectColliders.put(rectangle,new BoxCollider(rectangle));
        }
        
       
        
    }
    
    public List<Collider> getAllColliders(){
    	return new ArrayList<Collider>(mapObjectColliders.values());
    }
    
    public List<Collider> getNearbyColliders(Vector3 position, float distance) {
    	List<Collider> nearbyColliders = new ArrayList<Collider>();
    	mapObjectColliders.forEach((key,value) -> {		
    		if (position.x - distance < key.x + key.width 
    				&& position.y - distance < key.y + key.height 
    				&& position.x + distance > key.x 
    				&& position.y + distance > key.y)
    		nearbyColliders.add(value);
    		
    	});
    	
    	
    	//for (GameCharacter gc :  getNearbyCharacters(position, distance)) 
    	for (GameCharacter gc :  getAllCharacters()) 
    		nearbyColliders.add(gc.getCollider());
    	
    	
		return nearbyColliders;
	}
    
	public void render() {
		 tiledMapRenderer.setView(cam);
	     tiledMapRenderer.render();
	     
	     for (GameCharacter gc : gameCharacters.values()) {
	        gc.setProjectionMatrix(cam.combined);
	        gc.render();
	     }
	}
	
	public void dispose() {
		tiledMap.dispose();
		for (GameCharacter gc : gameCharacters.values())gc.dispose();
	}


	public List<GameCharacter> getAllCharacters() {
		return new ArrayList<GameCharacter>(gameCharacters.values());
	}

	public List<GameCharacter> getAllMonsters(){
    	List<GameCharacter> monsters = new ArrayList<GameCharacter>();
    	gameCharacters.forEach((key,value) -> {
    		if(key.contains("MONSTER"))
    			monsters.add(value);
	
    	});
		return monsters;
	}

	public List<GameCharacter> getNearbyCharacters(Vector3 position, float distance) {
    	List<GameCharacter> nearbyGameCharacters = new ArrayList<GameCharacter>();
    	gameCharacters.forEach((key,value) -> {
    		Vector3 characterPosition = value.getPosition();
    		
    		if (position.x - distance < characterPosition.x + 64 
    				&& position.y - distance < characterPosition.y + 64 
    				&& position.x + distance > characterPosition.x 
    				&& position.y + distance > characterPosition.y)
    			nearbyGameCharacters.add(value);
    		
    	});
		return nearbyGameCharacters;
	}

	public List<GameCharacter> getNearbyMonsters(Vector3 position, float distance){
    	List<GameCharacter> nearbyMonsters = new ArrayList<GameCharacter>();
    	gameCharacters.forEach((key,value) -> {
    		Vector3 characterPosition = value.getPosition();
    		
    		if (position.x - distance < characterPosition.x + 64 
    				&& position.y - distance < characterPosition.y + 64 
    				&& position.x + distance > characterPosition.x 
    				&& position.y + distance > characterPosition.y
    				&& key.contains("MONSTER"))
    			nearbyMonsters.add(value);
    		
    	});
		return nearbyMonsters;
	}
	

	public void spawnPlayer(Player player) {
		gameCharacters.put("PLAYER", player);
	}


	public void spawnMonsters() {
        MapObjects monsterSpawnPoints = tiledMap.getLayers().get("MonsterSpawns").getObjects();
        
        int ctr = 1;
        for (RectangleMapObject rectangleObject : monsterSpawnPoints.getByType(RectangleMapObject.class)) {
        	Rectangle rectangle = rectangleObject.getRectangle();
        	gameCharacters.put("MONSTER"+ctr,new HumanoidMonster(new Vector3(rectangle.x, rectangle.y,0)));
        	ctr++;
        }
	}
	
}
