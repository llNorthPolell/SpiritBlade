package com.northpole.spiritblade.controllers;

import com.northpole.spiritblade.enums.Direction;
import com.northpole.spiritblade.gameEntities.Collider;

public interface CollisionController {
	public boolean checkCollision(Collider a, Collider b);
	
	public boolean checkCollision(Collider a, Collider b, float step, Direction direction);
}
