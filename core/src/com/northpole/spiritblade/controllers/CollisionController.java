package com.northpole.spiritblade.controllers;

import com.northpole.spiritblade.gameEntities.Collider;

public interface CollisionController {
	public boolean checkCollision(Collider a, Collider b);
}
