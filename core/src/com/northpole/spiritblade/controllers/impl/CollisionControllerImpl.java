package com.northpole.spiritblade.controllers.impl;

import com.badlogic.gdx.math.Vector3;
import com.northpole.spiritblade.controllers.CollisionController;
import com.northpole.spiritblade.enums.Direction;
import com.northpole.spiritblade.gameEntities.Collider;

public class CollisionControllerImpl implements CollisionController {
	public CollisionControllerImpl() {
		
	}

	public boolean checkCollision(Collider a, Collider b) {	
		if (a.equals(b)) return false;
		
		Vector3 positionA = a.getPosition();
		Vector3 positionB = b.getPosition();
		Vector3 sizeA = a.getColliderSize();
		Vector3 sizeB = b.getColliderSize();
		return positionA.x < positionB.x + sizeB.x && positionA.y < positionB.y + sizeB.y 
				&& positionA.x + sizeA.x > positionB.x && positionA.y + sizeA.y > positionB.y;
	}

	
	public boolean checkCollision(Collider a, Collider b, float step, Direction direction) {
		if (a.equals(b)) return false;
		
		Vector3 positionA = new Vector3(
				(direction==Direction.EAST)?a.getPosition().add(step,0,0)
					:(direction==Direction.WEST)?a.getPosition().sub(step,0,0)
					:(direction==Direction.NORTH)?a.getPosition().add(0,step,0)
					:(direction==Direction.SOUTH)?a.getPosition().sub(0,step,0)
					:a.getPosition()
				);
		Vector3 positionB = b.getPosition();
		
		Vector3 sizeA = a.getColliderSize();
		Vector3 sizeB = b.getColliderSize();
		
		return positionA.x < positionB.x + sizeB.x && positionA.y < positionB.y + sizeB.y 
				&& positionA.x + sizeA.x > positionB.x && positionA.y + sizeA.y > positionB.y;
	}
	
	
	

}
