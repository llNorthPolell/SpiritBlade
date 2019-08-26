package com.northpole.spiritblade.controllers;

import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.gameEntities.Collider;
import com.northpole.spiritblade.gameEntities.GameCharacter;
import com.northpole.spiritblade.gameEntities.Player;

public interface MapController {
	public static float TILE_SIZE = 32;
	public void render();
	public void dispose();
	public List<Collider> getAllColliders();
	public List<Collider> getNearbyColliders(Vector3 position, float distance);
	public List<GameCharacter> getAllMonsters();
	public List<GameCharacter> getAllCharacters();
	public List<GameCharacter> getNearbyCharacters(Vector3 position, float distance);
	public List<GameCharacter> getNearbyMonsters(Vector3 position, float distance);
	
	public void spawnPlayer(Player player);
	
	public void spawnMonsters();
}
