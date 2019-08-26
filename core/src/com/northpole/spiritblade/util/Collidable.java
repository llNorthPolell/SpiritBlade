package com.northpole.spiritblade.util;

import com.northpole.spiritblade.gameEntities.Collider;

public interface Collidable {
	public Collider getCollider();
	public void applyNormalForce(Collider incomingCollider);
}
