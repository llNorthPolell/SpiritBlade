package com.northpole.spiritblade.controllers;

import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.gameEntities.Collider;

public interface WorldController {
	public static float TILE_SIZE = 32;
	public void render();
	public void dispose();
	public List<Collider> getAllColliders();
	public List<Collider> getNearbyColliders(Vector3 position, float distance);
}
