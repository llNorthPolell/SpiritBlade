package com.northpole.spiritblade.gameEntities;

import com.badlogic.gdx.math.Vector3;

public interface GameEntity {
	public void setPosition(Vector3 position);
	public Vector3 getPosition();
	public void render();
	public void dispose();
}
