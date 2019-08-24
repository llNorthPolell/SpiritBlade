package com.northpole.spiritblade.gameEntities;

import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public interface Collider {
	public Vector3 getPosition();
	public Vector3 getColliderSize();
	public Rectangle getColliderArea();
	public void setPosition(Vector3 position);
	public Collider getCollision(List<Collider> nearbyColliders);
}
