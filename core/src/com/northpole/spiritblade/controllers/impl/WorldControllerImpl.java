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
import com.northpole.spiritblade.controllers.WorldController;
import com.northpole.spiritblade.gameEntities.BoxCollider;
import com.northpole.spiritblade.gameEntities.Collider;

public class WorldControllerImpl implements WorldController {
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera cam;
    
    private MapObjects mapCollidableObjects;
    private Map<Rectangle,Collider> mapObjectColliders;
    
    
    
    public WorldControllerImpl(OrthographicCamera cam) {
    	this.tiledMap = new TmxMapLoader().load("maps/Tut1.tmx");
    	this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        this.cam = cam;
        
        mapObjectColliders = new HashMap<Rectangle,Collider>();
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
		return nearbyColliders;
	}
    
	public void render() {
		 tiledMapRenderer.setView(cam);
	     tiledMapRenderer.render();
	}
	
	public void dispose() {
		tiledMap.dispose();
	}


	
}
