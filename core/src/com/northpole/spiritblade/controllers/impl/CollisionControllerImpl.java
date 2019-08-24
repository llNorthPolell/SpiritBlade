package com.northpole.spiritblade.controllers.impl;

import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.controllers.CollisionController;
import com.northpole.spiritblade.gameEntities.Collider;

public class CollisionControllerImpl implements CollisionController {
	public CollisionControllerImpl() {
		
	}

	public boolean checkCollision(Collider a, Collider b) {	
		Vector3 positionA = a.getPosition();
		Vector3 positionB = b.getPosition();
		Vector3 sizeA = a.getColliderSize();
		Vector3 sizeB = b.getColliderSize();
		return positionA.x < positionB.x + sizeB.x && positionA.y < positionB.y + sizeB.y 
				&& positionA.x + sizeA.x > positionB.x && positionA.y + sizeA.y > positionB.y;
	}
	
	
	

}
