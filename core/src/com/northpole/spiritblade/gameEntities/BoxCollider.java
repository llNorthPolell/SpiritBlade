package com.northpole.spiritblade.gameEntities;

import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.controllers.CollisionController;
import com.northpole.spiritblade.controllers.impl.CollisionControllerImpl;

public class BoxCollider implements Collider {
	private Rectangle colliderArea;
	private CollisionController collisionController;
	
	public BoxCollider(Rectangle rectangle) {
		this.colliderArea= rectangle;
		this.collisionController = new CollisionControllerImpl();
	}
	
	public void setPosition(Vector3 position) {
		this.colliderArea.setPosition(position.x, position.y);
	}
	
	public Vector3 getPosition() {
		return new Vector3(this.colliderArea.x, this.colliderArea.y,0);
	}
	
	public Vector3 getColliderSize() {
		return new Vector3(this.colliderArea.width, this.colliderArea.height, 0);
	}

	public Rectangle getColliderArea() {
		return this.colliderArea;
	}

	
	public Collider getCollision(List<Collider> nearbyColliders) {
    	for (Collider nearbyCollider : nearbyColliders) 	
        	if(this.collisionController.checkCollision(this,nearbyCollider)) 
        		return nearbyCollider;

    	return null;
	}
}
